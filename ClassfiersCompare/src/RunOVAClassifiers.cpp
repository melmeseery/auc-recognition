/*
 * RunOVAClassifiers.cpp
 *
 *  Created on: Jan 12, 2009
 *      Author: Maha
 */

#include "RunOVAClassifiers.h"
#include "LibraryFunctions.h"
using namespace Torch;
//#include <fstream>
//#include<ofstream>
using namespace std;

RunOVAClassifiers::RunOVAClassifiers() {
	// TODO Auto-generated constructor stub

}

RunOVAClassifiers::~RunOVAClassifiers() {
	// TODO Auto-generated destructor stub
}


void RunOVAClassifiers::linearTrain(char* train_file, int num_of_features, char* model)
{
	if (model==NULL)
	{
		model="LinearOVAmodel";
	}
	//DiskXFile model_file(model, "w");
	Allocator *allocator = new Allocator;

	//char train_file[]="D:/datasets/mnist_training_set.txt";


	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear( num_of_features,10);
	//LogSoftMax *c2= new(allocator) LogSoftMax(10);
	Tanh *c2= new(allocator) Tanh(10);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();


	// reading training data from file and converting it to one hot class format
	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(i==j)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=-1;
		}
	}
	DataSet *temp1;
	temp1=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);




	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	//Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);
	Criterion *criterion = new(allocator) MSECriterion(10);

	// creating the trainer
	StochasticGradient trainer(&linear_net, criterion);
	trainer.setIOption("max iter",100);

	// training...
	trainer.train(train_data, NULL);



	//saving model to file
	linear_net.save(model);


	delete(allocator);


}
float RunOVAClassifiers::linearGetThreshold(char* train_file, int num_of_features,int samples, char* model){

	int trainSamples=0.85*samples;
	if (model==NULL)
	{
		model="LinearOVAmodel";
	}
	float th=0;
	/////first train ...........................
	Allocator *allocator = new Allocator;

		//char train_file[]="D:/datasets/mnist_training_set.txt";


		// creating the linear machine
		ConnectedMachine linear_net;
		Linear *c1= new(allocator) Linear( num_of_features,10);
		//LogSoftMax *c2= new(allocator) LogSoftMax(10);
		Tanh *c2= new(allocator) Tanh(10);
		linear_net.addFCL(c1);
		linear_net.addFCL(c2);
		linear_net.build();
		linear_net.setPartialBackprop();


		// reading training data from file and converting it to one hot class format
		Sequence *class_labels= new(allocator) Sequence(10,10);
		int i,j;
		for(i=0;i<=9;i++)
		{
			for(j=0;j<=9;j++)
			{
				if(i==j)
					class_labels->frames[i][j]=1;
				else
					class_labels->frames[i][j]=-1;
			}
		}
		DataSet *temp1;
		temp1=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
		DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);




		// specifying cost function
		OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
		//Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);
		Criterion *criterion = new(allocator) MSECriterion(10);

		// creating the trainer
		StochasticGradient trainer(&linear_net, criterion);
		trainer.setIOption("max iter",100);

		// training...
		trainer.train(train_data, NULL);

		//saving model to file
		linear_net.save(model);


		delete(allocator);


		return th;


}
void RunOVAClassifiers::linearTest(char* test_file,int num_of_features,double threshold, char* model){



		Allocator *allocator = new Allocator;

		if (model==NULL)
			{
				model="LinearOVAmodel";
			}
		//char test_file[]="D:/datasets/mnist_testing_set.txt";


		// creating the linear machine
		ConnectedMachine linear_net;
		Linear *c1= new(allocator) Linear(num_of_features,10);
		//LogSoftMax *c2= new(allocator) LogSoftMax(10);
		Tanh *c2= new(allocator) Tanh(10);
		linear_net.addFCL(c1);
		linear_net.addFCL(c2);
		linear_net.build();
		linear_net.setPartialBackprop();

		//loading the model
		linear_net.load(model);


		// reading testing data from file and converting it to one hot class format
		Sequence *class_labels= new(allocator) Sequence(10,10);
		int i,j;
		for(i=0;i<=9;i++)
		{
			for(j=0;j<=9;j++)
			{
				if(i==j)
					class_labels->frames[i][j]=1;
				else
					class_labels->frames[i][j]=0;
			}
		}

		DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



		float actual_accuracy(0);
		const int Nt=100000;
		float n_rejected[Nt]; // number of rejected samples
		float correct[Nt];
		for(int t=0; t<Nt; t++)
		{
			correct[t]=0;
			n_rejected[t]=0;
		}

		ofstream fout("LinearOVA_RR_results.txt");
		for(int k = 0; k < test_data->n_examples; k++)
		{


			test_data->setExample(k);
			int d= test_data->targets->frames[0][0];//actual value of sample

			real decision_scores[10];// to hold decision scores

			linear_net.forward(test_data->inputs);//forwarding sample

			for(int i=0; i<10; i++)
				decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;

			real first_max_value;
			long first_max_index;
			real second_max_value;
			long second_max_index;

			// finding value and index of first and second maxima
			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			decision_scores[first_max_index]=-99;
			LibraryFunctions::getMax(decision_scores,10,second_max_value,second_max_index);


			if(first_max_index==d)
				actual_accuracy++;


			// rejecting patterns that violates thresholding condittions
			for(int t=0;t<Nt; t++)
			{
				float th= ((float)(t))/((float) Nt);
				if((first_max_value-second_max_value)<th)
					n_rejected[t]++;
				else if(first_max_index==d)
					correct[t]++;
			}

		}

		cout<<correct[Nt-1]/(10000-n_rejected[Nt-1])*100<<endl;///getch();


		//calculating time
		float Accuracy(0);
		int numRejected(0);
		float errorCount(0);
		Timer timer;
		float recog_time(0);
		for(int iter=0; iter<10; iter++)
		for(int k = 0; k < test_data->n_examples; k++)
		{


			test_data->setExample(k);
			int d= test_data->targets->frames[0][0];//actual value of sample

			real decision_scores[10];// to hold decision scores

			linear_net.forward(test_data->inputs);//forwarding sample

			for(int i=0; i<10; i++)
				decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;

			real first_max_value;
			long first_max_index;

			// finding value and index of first and second maxima
			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			if (first_max_value>threshold)
				{
				if (first_max_index==d)
				{
					Accuracy++;
				}
				else
				{
					//wrong classified
					errorCount++;
				}
				}else
				{
					//less than threshold so it will be rejected.
					numRejected++;

				}




		}

		recog_time= timer.getTime()/10;

		fout<<endl;
		fout<<"Linear classifier using  "<<test_file<<endl;
		fout<<"-------------------------------------------------"<<endl;
		fout<<"Accuracy of the classification using the threshold=  "<<threshold<<endl;
		fout<<"number of correct samples = "<<Accuracy  <<endl;
		fout<<"Accuracy = "<<Accuracy*(100.0/(float)test_data->n_examples)<<" %"<<endl;
		fout<<"Actual Accuracy after rejection = "<<Accuracy*(100.0/(float)(test_data->n_examples-numRejected))<<" %"<<endl;

		fout<<"number of rejected samples ="<< numRejected<<" sample "<<endl;
		fout<<" number of error samples = "<< errorCount <<"  with percentage = "<< errorCount*(100.0/(float)test_data->n_examples)<<" %"<<endl;
		fout<<"-------------------------------------------------"<<endl;

		bool found(false);
		for(int t=0; t<Nt; t++)
			if(correct[t]/(10000-n_rejected[t])*100>99.5)
			{
				fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
				cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

				fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
				cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
				fout<<"number of RR= "<< n_rejected[t] <<endl;
				fout<<"RR= "<< n_rejected[t] /100<<"%"<<endl;
				cout<<"RR= "<< n_rejected[t] /100<<"%"<<endl;

				float th= ((float)(t))/((float) Nt);
				fout<<"th= "<<th<<endl;
				cout<<"th= "<<th<<endl;

				fout<<"Recognition Time: "<<recog_time<<endl;
				cout<<"Recognition Time: "<<recog_time<<endl;


				found=true;
				break;

			}

			if(!found){
				fout<<" 99.5% not reached!"<<endl;
				cout<<"99.5% not reached!"<<endl;

			}
		delete(allocator);


	}
void RunOVAClassifiers::svmGaussianTrain(char* train_file, int num_of_features,float C,float g, char* model){
	Allocator *allocator = new Allocator;

	if (model==NULL)
		{
			model="SVM_OVAmodel";
		}

		DiskXFile model_file(model, "w");



		// creating the SVMs
		//float g=0.03;//gamma
		//float C=50;
		Kernel *kernel = new(allocator) GaussianKernel(g);

		SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)*10);
		for(int i = 0; i < 10; i++)
		{
			svms[i] = new(allocator) SVMClassification(kernel);
			svms[i]->setROption("C", C);
			//svms[i]->setIOption("max iter",100);
		}



		// reading the training set
		MatDataSet *train_data = new(allocator) MatDataSet(train_file, num_of_features, 1, false, -1, false);


		// training the SVMs (One vs All)
		for(int i = 0; i < 10; i++)
		{
			cout<<"  *** training the digit "<<i<<endl;
			QCTrainer trainer(svms[i]);
			trainer.setBOption("unshrink",true);

			Sequence class_labels(10, 1);
			for(int j = 0; j < 10; j++)
			{
				if(j == i)
					class_labels.frames[j][0] =  1;
				else
					class_labels.frames[j][0] = -1;
			}
			ClassFormatDataSet formatted_train_data(train_data, &class_labels);

			trainer.train(&formatted_train_data, NULL);

			// saving SVM model in turn
			svms[i]->saveXFile(&model_file);
		}


		delete(allocator);
}

void RunOVAClassifiers::svmGaussianTest(char* test_file,int num_of_features,float C,float g,double threshold,char* model){

	Allocator *allocator = new Allocator;

	//	char test_file[]="C:/datasets/mahdbase_testing_set.txt";
		//DiskXFile model_file("SVM_OVAmodel", "r");
		if (model==NULL)
			{
				model="SVM_OVAmodel";
			}

			DiskXFile model_file(model, "r");

		// creating the SVMs
		//float g=0.03;//gamma
		//float C=50;
		Kernel *kernel = new(allocator) GaussianKernel(g);

		int total_num_of_support_vectors(0);
		SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)*10);
		for(int i = 0; i < 10; i++)
		{
			svms[i] = new(allocator) SVMClassification(kernel);

			//loading SVM models in turn
			svms[i]->loadXFile(&model_file);

			total_num_of_support_vectors+= svms[i]->n_support_vectors;
		}

		cout<<"number of support vectors = "<<total_num_of_support_vectors<<endl;
		//getch();


		// reading testing data from file and converting it to one hot class format
		Sequence *class_labels= new(allocator) Sequence(10,10);
		int i,j;
		for(i=0;i<=9;i++)
		{
			for(j=0;j<=9;j++)
			{
				if(i==j)
					class_labels->frames[i][j]=1;
				else
					class_labels->frames[i][j]=0;
			}
		}

		DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



		//creating and initializing the confusion matrix
		real confusion_matrix[10][10];
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
				confusion_matrix[i][j]=0;


		real actual_accuracy(0);
		real accuracy(0);//accuracy after rejection
		long n_rejected(0); // number of rejected samples
		long n_false_rejected(0); // number of falsely rejected samples

		const int Nt=100000;
		float n_rejectednt[Nt]; // number of rejected samples
		float correct[Nt];
		for(int t=0; t<Nt; t++)
		{
			correct[t]=0;
			n_rejectednt[t]=0;
		}
		Timer timer;
		timer.stop();
          if (threshold>0)
 		for(int t = 0; t < test_data->n_examples; t++)
		{
              if (t%500==0)
			cout<<t<<endl;
			test_data->setExample(t);
			int d= test_data->targets->frames[0][0];//actual value of sample

			real decision_scores[10];// to hold decision scores

			// forwarding test sample in all SVMs
			real sum(0);
			for(int i = 0; i < 10; i++)
			{
				timer.resume();
				svms[i]->forward(test_data->inputs);
				timer.stop();

				decision_scores[i] = exp(svms[i]->outputs->frames[0][0]);
				sum+=decision_scores[i];
			}
			// to create probabilistic output
			for(int i=0; i<10; i++)
			{
				decision_scores[i]= decision_scores[i]/sum;
			}

			real first_max_value;
			long first_max_index;
			real second_max_value;
			long second_max_index;

			// finding value and index of first and second maxima
			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			decision_scores[first_max_index]=-99;
			LibraryFunctions::getMax(decision_scores,10,second_max_value,second_max_index);

			if(first_max_index==d)
				actual_accuracy++;
			if (threshold!=-1){
			for(int t=0;t<Nt; t++)
			{
							float th= ((float)(t))/((float) Nt);
							if((first_max_value-second_max_value)<th)
								n_rejectednt[t]++;
							else if(first_max_index==d)
								correct[t]++;
			}
			}
			// rejecting patterns that violates thresholding condittions
			if(second_max_value>0.17)
			{
				n_rejected++;
				if(first_max_index==d)
					n_false_rejected++;
				continue;
			}

			//updating confusion matrix
			confusion_matrix[d][first_max_index]=confusion_matrix[d][first_max_index]+1;


		}




		float Accuracy(0);
			int numRejected(0);
			float errorCount(0);
			////calculatin recognition time
			float recog_time(0);
			Timer timer22;
			for(int t = 0; t < test_data->n_examples; t++)
			{

			    if (t%500==0)
						cout<<t<<endl;

				test_data->setExample(t);
				int d= test_data->targets->frames[0][0];//actual value of sample

				real decision_scores[10];// to hold decision scores

				real sum(0);

				for(int i = 0; i < 10; i++)
							{

								svms[i]->forward(test_data->inputs);


								decision_scores[i] = exp(svms[i]->outputs->frames[0][0]);
								sum+=decision_scores[i];
							}
							// to create probabilistic output
							for(int i=0; i<10; i++)
							{
								decision_scores[i]= decision_scores[i]/sum;
							}


				real first_max_value;
				long first_max_index;

				LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
				if (first_max_value>threshold)
				{
				if (first_max_index==d)
				{
					Accuracy++;
				}
				else
				{
					//wrong classified
					errorCount++;
				}
				}else
				{
					//less than threshold so it will be rejected.
					numRejected++;

				}


			}
			recog_time= timer22.getTime();








		ofstream fout("SVMOVA_RR_results.txt");

		fout<<endl;
				fout<<"Gausssian SVM OVA using  "<<test_file<<endl;
				fout<<"-------------------------------------------------"<<endl;
				fout<<"Accuracy of the classification using the threshold=  "<<threshold<<endl;
				fout<<"number of correct samples = "<<Accuracy  <<endl;
				fout<<"Accuracy = "<<Accuracy*(100.0/(float)test_data->n_examples)<<" %"<<endl;
				fout<<"Actual Accuracy after rejection = "<<Accuracy*(100.0/(float)(test_data->n_examples-numRejected))<<" %"<<endl;
				fout<<"number of rejected samples ="<< numRejected<<" sample "<<endl;
				fout<<" number of error samples = "<< errorCount <<"  with percentage = "<< errorCount*(100.0/(float)test_data->n_examples)<<" %"<<endl;
				cout<<"Recognition Time :"<<recog_time<<endl;
				fout<<"-------------------------------------------------"<<endl;



			bool found(false);
			if(threshold>0)
			for(int t=0; t<Nt; t++)
			{
				if(correct[t]/(10000-n_rejectednt[t])*100>99.5)
				{
					fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
					cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

					fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejectednt[t])*100<<"%"<<endl;
					cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejectednt[t])*100<<"%"<<endl;

					fout<<"RR= "<<n_rejectednt[t]/100<<"%"<<endl;
					cout<<"RR= "<<n_rejectednt[t]/100<<"%"<<endl;

					float th= ((float)(t))/((float) Nt);
					fout<<"th= "<<th<<endl;
					cout<<"th= "<<th<<endl;

					found=true;
					break;

				}
			}

			if(!found){
				fout<<"99.5% not reached!"<<endl;
				cout<<"99.5% not reached!"<<endl;
			}


		//calculating accuracy from confusion matrix
		real n_examples(0);
		real n_correct_examples(0);
		for(int i=0; i<10; i++)
			n_correct_examples += confusion_matrix[i][i];
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
				n_examples+= confusion_matrix[i][j];
		accuracy= n_correct_examples/n_examples;
		cout<<"Accuracy: "<<accuracy<<endl;
		cout<<"Actual Accuracy : "<<actual_accuracy/10000<<endl;

		fout<<"Accuracy: "<<accuracy<<endl;
		fout<<"Actual Accuracy : "<<actual_accuracy/10000<<endl;

		// displaying confusion matrix
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<10; j++){
				cout<<confusion_matrix[i][j]<<"      ";
				fout<<confusion_matrix[i][j]<<"      ";

			}
			cout<<endl;
			fout<<endl;
		}

		cout<<"Rejected: "<<n_rejected<<endl;
		cout<<"FR : "<<n_false_rejected<<endl;
		cout<<"Recognition Time :"<<timer.getTime()<<endl;



		fout<<"Rejected: "<<n_rejected<<endl;
		fout<<"Rejected: "<<n_rejected/100<<"%"<<endl;
		fout<<"False R : "<<n_false_rejected<<endl;
		fout<<"False R : "<<n_false_rejected/100<<"%"<<endl;

		fout<<"Recognition Time :"<<timer.getTime()<<endl;


		delete(allocator);




}

void RunOVAClassifiers::svmLinearTrain(char* train_file, int num_of_features,float C,float s,char* model){

	//int num_of_features=28*28;

		Allocator *allocator = new Allocator;


	//	char train_file[]="D:/datasets/mahdbase_training_set.txt";
		//DiskXFile model_file("SVMLinearmodel", "w");
		if (model==NULL)
			{
				model="SVMLinearmodel";
			}

			DiskXFile model_file(model, "w");


		SVMClassification *svms[10][10];
		QCTrainer *trainers[10][10];
		//float s=1;//0.0275;//0.04;//gamma
		//float C=0.05;
		Kernel *kernel = new(allocator) DotKernel(s);

		DataSet *train_set;
		train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);


		MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
		Sequence *inputs[20000];
		Sequence *targets[20000];
		for(int n=0; n<20000; n++)
		{
			inputs[n]= new(allocator) Sequence(1,num_of_features);
			targets[n]= new(allocator) Sequence(1,1);
		}
		current_train_set->setInputs(inputs, 20000);
		current_train_set->setTargets(targets, 20000);


		int counter;
		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{
				cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;


				svms[i][j] = new(allocator) SVMClassification(kernel);
				svms[i][j]->setROption("C", C);
				//svms[i][j]->setIOption("max iter",100);
				//trainers[i][j]->setBOption("unshrink",true);
				trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

				counter=0;
				for(long k=0; k<60000; k++)
				{

					train_set->setExample(k);
					int d= train_set->targets->frames[0][0];

					if(d==i || d==j)
					{

						current_train_set->setExample(counter);

						for(int f=0; f<num_of_features; f++)
						{
							current_train_set->inputs->frames[0][f]=
								train_set->inputs->frames[0][f];
						}

						if(d==i)
							current_train_set->targets->frames[0][0]= 1;
						else
							current_train_set->targets->frames[0][0]= -1;

						counter++;
					}

				}
				cout<<counter<<endl;

				current_train_set->setExample(0);
				current_train_set->n_examples=counter;
				trainers[i][j]->train(current_train_set,NULL);
				svms[i][j]->saveXFile(&model_file);


			}
		}


		delete(allocator);
}
void RunOVAClassifiers::svmLinearEstimating_Calibrating_Parameters(char* train_file, int num_of_features,int samples,float C,float s)
{
	int trainSamples=0.85*samples;

	Allocator *allocator = new Allocator;

	//int num_of_features=28*28;


//	char train_file[]="D:/datasets/mahdbase_training_set.txt";
	DataSet *dataset;
	dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);



	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	//float s=1;
	Kernel *kernel;

	MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for(int n=0; n<20000; n++)
	{
		inputs[n]= new(allocator) Sequence(1,num_of_features);
		targets[n]= new(allocator) Sequence(1,1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);


	ofstream fout("calibrating_parameters.txt");
	float out[5000];
	bool target[5000];

	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			kernel = new(allocator) DotKernel(s);
			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C",C);
			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			counter=0;
			for(long k=0; k<trainSamples; k++)
			{

				dataset->setExample(k);
				int d= dataset->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<num_of_features; f++)
					{
						current_train_set->inputs->frames[0][f]=
							dataset->inputs->frames[0][f];
					}

					if(d==i)
						current_train_set->targets->frames[0][0]= 1;
					else
						current_train_set->targets->frames[0][0]= -1;

					counter++;
				}

			}

			cout<<counter<<endl;
			current_train_set->n_examples= counter;
			current_train_set->setExample(0);
			trainers[i][j]->train(current_train_set,NULL);


			int count=0;
			float prior1(0), prior0(0);

			for(int t=trainSamples; t<samples; t++)
			{

				dataset->setExample(t);
				int d= dataset->targets->frames[0][0];

				if(d==i)
				{target[count]=true; prior1++;}
				else if(d==j)
				{target[count]=false; prior0++;}
				else
					continue;

				svms[i][j]->forward(dataset->inputs);

				real decision = svms[i][j]->outputs->frames[0][0];

				out[count]=decision;

				count++;
			}
			int len=count;

			float A(0), B(log((float)((prior0+1)/(prior1+1))));
			float hiTarget((prior1+1)/(prior1+2)), loTarget(1/(prior0+2));
			float lambda(pow((float)10,(int)-3));
			float olderr(pow((float)10,(int)300));

			float pp[5000];
			for(int k=0; k<len; k++)
				pp[k]=(prior1+1)/(prior0+prior1+2);

			count=0;
			cout<<"Iter: ";
			for(int it=0; it<100; it++)
			{
				//if(it%10==0)
					cout<<it<<",  ";

				float a(0), b(0), c(0), dl(0), e(0);

				for(int k=0; k<len; k++)
				{
					float t;
					if(target[k])
						t= hiTarget;
					else
						t= loTarget;

					float d1=pp[k]-t;
					float d2=pp[k]*(1-pp[k]);
					a+= out[k]*out[k]*d2;
					b+= d2;
					c+= out[k]*d2;
					dl+= out[k]*d1;
					e+= d1;
				}


                float tempd=fabs(dl);

				if(tempd<pow((float)10,(int)-9) && fabs(e)<pow((float)10,(int)-9))
					break;

				float oldA(A), oldB(B);
				float err(0);

				while(true)
				{

					float det= (a+lambda)*(b+lambda)-c*c;
					if(det==0){lambda*=10; continue;}

					A= oldA + ((b+lambda)*dl-c*e)/det;
					B= oldB + ((a+lambda)*e-c*dl)/det;

					err=0;
					for(int k=0; k<len; k++)
					{
						float t;
						if(target[k])
							t= hiTarget;
						else
							t= loTarget;

						float p= 1/(1+exp(out[k]*A+B));
						pp[k]=p;
						float log_p;
						if(p==0)
							log_p=-200;
						else
							log_p=log((float)p);
						float log_1_p;
						if(1-p==0)
							log_1_p=-200;
						else
							log_1_p=log((float)(1-p));

						err-= t*log_p+(1-t)*log_1_p;
					}

					if(err<olderr*(1+pow((float)10,(int)-7)))
					{lambda*=0.1; break;}

					lambda*=10;
					if(lambda>=pow((float)10,(int)6))
					{/*cout<<"Give Up!";*/ break;}

				}

				float diff=err-olderr;
				float scale=0.5*(err+olderr+1);
				if(diff>-pow((float)10,(int)-3)*scale && diff<pow((float)10,(int)-7)*scale)
					count++;
				else
					count=0;

				olderr=err;
				if(count==3)
					break;

			}
			cout<<endl;

			fout<<A<<endl<<B<<endl;
			cout<<"A= "<<A<<", B= "<<B<<endl;


		}
	}



	fout.close();

	delete(allocator);


}

void RunOVAClassifiers::svmLinearTest(char* test_file,int num_of_features,float C,float s,double threshold,char* model){
	//SVMLinearmodel
	ifstream fin("calibrating_parameters.txt");
		float As[10][10];
		float Bs[10][10];
		for(int i=0; i<=8; i++)
		{
			for(int j=i+1; j<=9; j++)
			{fin>>As[i][j]; fin>>Bs[i][j];}
		}


		//int num_of_features=28*28;
//
		Allocator *allocator = new Allocator;

		//char test_file[]="D:/datasets/mahdbase_testing_set.txt";

		if (model==NULL)
			{
				model="SVMLinearmodel";
			}

			DiskXFile model_file(model, "r");
		//DiskXFile model_file("SVMLinearmodel", "r");


		DataSet *test_set;
		test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



		// creating pairwise classifiers pointers
		//float s=1;//0.0275;//0.04;//gamma
		Kernel *kernel = new(allocator) DotKernel(s);
		SVMClassification *svms[10][10];

		Sequence Ws(45,num_of_features);
		float bs[45];

		//loading pairwise classifiers in turn
		int count(0);
		for(int i=0; i<=8; i++)
		{
			for(int j=i+1; j<=9; j++)
			{
				svms[i][j] = new(allocator) SVMClassification(kernel);


				//loading SVM models in turn
				svms[i][j]->loadXFile(&model_file);

				bs[count]= svms[i][j]->b;

				for(int n=0; n<num_of_features; n++)
					Ws.frames[count][n]=0;

				for(int s=0; s<svms[i][j]->n_support_vectors; s++)
				{
					for(int n=0; n<num_of_features; n++)
						Ws.frames[count][n]+= (svms[i][j]->sv_alpha[s])*(svms[i][j]->sv_sequences[s]->frames[0][n]);
				}

				count++;


			}
		}

		float actual_accuracy(0);
		const int Nt=100000;
		float n_rejected[Nt]; // number of rejected samples
		float correct[Nt];
		for(int t=0; t<Nt; t++)
		{
			correct[t]=0;
			n_rejected[t]=0;
		}


		for(int k = 0; k < test_set->n_examples; k++)
		{
			if(k%100==0)
				cout<<k<<endl;


			test_set->setExample(k);
			int d= test_set->targets->frames[0][0];


			real binary_decisions[10][10];
			count=0;
			for(int i=0; i<=8; i++)
				for(int j=i+1; j<=9; j++)
				{
					real f(bs[count]);
					for(int n=0; n<num_of_features; n++)
						f+= (test_set->inputs->frames[0][n])*(Ws.frames[count][n]);

					binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j]));
					binary_decisions[j][i]= 1-binary_decisions[i][j];

					count++;

				}

				real decisions[10];
				for(int i=0; i<=9; i++)
				{
					real prob_sum(0);
					for(int j=0; j<=9; j++)
					{
						if(i!=j)
						{
							prob_sum += 1/(binary_decisions[i][j]);
						}
					}

					decisions[i]= 1/(prob_sum - 8);
				}

				real first_max_value;
				long first_max_index;
				real second_max_value;
				long second_max_index;

				// finding value and index of first and second maxima
				LibraryFunctions::getMax(decisions,10,first_max_value,first_max_index);
				decisions[first_max_index]=-99;
				LibraryFunctions::getMax(decisions,10,second_max_value,second_max_index);

				if(first_max_index==d)
					actual_accuracy++;

				for(int t=0;t<Nt; t++)
				{
					float th= ((float)(t))/((float) Nt);
					if((first_max_value-second_max_value)<th)
						n_rejected[t]++;
					else if(first_max_index==d)
						correct[t]++;
				}

		}

		//cout<<correct[Nt-1]/(10000-n_rejected[Nt-1])*100<<endl; getch();
		ofstream fout("SVM_linear_RR_results.txt");
		bool found(false);
		for(int t=0; t<Nt; t++)
		{
			if(correct[t]/(10000-n_rejected[t])*100>99.5)
			{
				fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
				cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

				fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
				cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;

				fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;
				cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;

				float th= ((float)(t))/((float) Nt);
				fout<<"th= "<<th<<endl;
				cout<<"th= "<<th<<endl;

				found=true;
				break;

			}
		}

		if(!found){
			fout<<"99.5% not reached!"<<endl;
			cout<<"99.5% not reached!"<<endl;
		}




		float Accuracy(0);
			int numRejected(0);
			float errorCount(0);
		float recog_time(0);
		Timer timer;
		for(int iter=0; iter<10; iter++)
		{
			Accuracy=0;
			numRejected=0;
			 errorCount=0;
			for(int k = 0; k < test_set->n_examples; k++)
			{


				test_set->setExample(k);
				int d= test_set->targets->frames[0][0];


				real binary_decisions[10][10];
				count=0;
				for(int i=0; i<=8; i++)
					for(int j=i+1; j<=9; j++)
					{
						real f(bs[count]);
						for(int n=0; n<num_of_features; n++)
							f+= (test_set->inputs->frames[0][n])*(Ws.frames[count][n]);

						binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j]));
						binary_decisions[j][i]= 1-binary_decisions[i][j];

						count++;

					}

					real decisions[10];
					for(int i=0; i<=9; i++)
					{
						real prob_sum(0);
						for(int j=0; j<=9; j++)
						{
							if(i!=j)
							{
								prob_sum += 1/(binary_decisions[i][j]);
							}
						}

						decisions[i]= 1/(prob_sum - 8);
					}

					real first_max_value;
					long first_max_index;

					LibraryFunctions::getMax(decisions,10,first_max_value,first_max_index);
					if (first_max_value>threshold)
						{
						if (first_max_index==d)
						{
							Accuracy++;
						}
						else
						{
							//wrong classified
							errorCount++;
						}
						}else
						{
							//less than threshold so it will be rejected.
							numRejected++;

						}


			}
		}
		recog_time= timer.getTime()/10;
		fout<<endl;
				fout<<"SVM Linear using  "<<test_file<<endl;
				fout<<"-------------------------------------------------"<<endl;
				fout<<"Accuracy of the classification using the threshold=  "<<threshold<<endl;
				fout<<"number of correct samples = "<<Accuracy  <<endl;
				fout<<"Accuracy = "<<Accuracy*(100.0/(float)test_set->n_examples)<<" %"<<endl;
				fout<<"Actual Accuracy after rejection = "<<Accuracy*(100.0/(float)(test_set->n_examples-numRejected))<<" %"<<endl;
				fout<<"number of rejected samples ="<< numRejected<<" sample "<<endl;
				fout<<" number of error samples = "<< errorCount <<"  with percentage = "<< errorCount*(100.0/(float)test_set->n_examples)<<" %"<<endl;
				fout<<"-------------------------------------------------"<<endl;
		fout<<"Recognition Time: "<<recog_time<<endl;
		cout<<"Recognition Time: "<<recog_time<<endl;


		fin.close();
		fout.close();

		delete(allocator);


}


void RunOVAClassifiers::ANNTrain(char* train_file, int num_of_features,int h,char* model){


	if (model==NULL)
		{
			model="ANNmodel";
		}

		//DiskXFile model_file(model, "w");
	// create the allocator opject
		Allocator *allocator = new Allocator;

		//char train_file[]=train_MAHD_file;
		//	char train_file[]="C:/datasets/mahdbase_training_set.txt";

		if (debugLog)
			cout<<" creating mlp "<<endl;
		// creating the mlp
		ConnectedMachine mlp;
		Linear *c1= new(allocator) Linear(num_of_features,h);
		Tanh *c2= new(allocator) Tanh(h);
		Linear *c3= new(allocator) Linear(h,10);
		LogSoftMax *c4= new(allocator) LogSoftMax(10);
		mlp.addFCL(c1);
		mlp.addFCL(c2);
		mlp.addFCL(c3);
		mlp.addFCL(c4);
		mlp.build();
		mlp.setPartialBackprop();



		// reading training data from file and converting it to one hot class format
		//means  for 4 the label will be 0 0 0 0 1 0 0 0 0  0
		Sequence *class_labels= new(allocator) Sequence(10,10);
		int i,j;

		for(i=0;i<=9;i++)
		{
			for(j=0;j<=9;j++)
			{
				if(i==j)
					class_labels->frames[i][j]=1;
				else
					class_labels->frames[i][j]=0;
			}
		}

		DataSet *temp1;
		if (debugLog)
			cout<<" Reading dataset from files "<<endl;
		// read the data set formm files
		temp1=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
		DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);




		// specifying cost function
		OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
		Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);

		// creating the trainer
		StochasticGradient trainer(&mlp, criterion);
		trainer.setIOption("max iter",100);

		if (debugLog)
			cout<<" Training ---- "<<endl;
		// training...
		trainer.train(train_data, NULL);

		if (debugLog)
			cout<<" Saving "<<endl;
		//saving model to file
		mlp.save(model);


		delete(allocator);


}
void RunOVAClassifiers::ANNTest(char* test_file,int num_of_features,int n_hu,double threshold,char* model){
	Allocator *allocator = new Allocator;
	if (model==NULL)
		{
			model="ANNmodel";
		}

		//DiskXFile model_file(model, "w");
		//char test_file[]=test_MAHD_file;
			real first_max_value;
			long first_max_index;
			real second_max_value;
			long second_max_index;

	//char test_file[]="D:/datasets/mahdbase_testing_set.txt";

		if (debugLog)
			cout<<" creating mlp "<<endl;
	//	const int n_hu=150;
		// creating the mlp
		ConnectedMachine mlp;
		Linear *c1= new(allocator) Linear(num_of_features,n_hu);
		Tanh *c2= new(allocator) Tanh(n_hu);
		Linear *c3= new(allocator) Linear(n_hu,10);
		LogSoftMax *c4= new(allocator) LogSoftMax(10);
		//Tanh *c4= new(allocator) Tanh(10);
		mlp.addFCL(c1);
		mlp.addFCL(c2);
		mlp.addFCL(c3);
		mlp.addFCL(c4);
		mlp.build();
		mlp.setPartialBackprop();

		if (debugLog)
			cout<<" reading model  "<<endl;
		//loading the model
		mlp.load(model);



		if (debugLog)
			cout<<" reading test data  "<<endl;
		DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);


		float actual_accuracy(0);
		const int Nt=100000;
		float n_rejected[Nt]; // number of rejected samples
		float correct[Nt];
		for(int t=0; t<Nt; t++)
		{
			correct[t]=0;
			n_rejected[t]=0;
		}

		ofstream fout("ANN_RR_results.txt");

		if (debugLog)
			cout<<" Start testing set  "<<endl;




		for(int t = 0; t < test_data->n_examples; t++)
		{
//			if ( t%200==0)
//			{
//
//			if (debugLog)
//			//	cout<<"  In example  "<<t<< "  current accuracy = "<<actual_accuracy <<endl;
//			}

			//set current example
			test_data->setExample(t);
			int d= test_data->targets->frames[0][0];//actual value of sample

			// each output classifier have one value
			real decision_scores[10];// to hold decision scores


			mlp.forward(test_data->inputs);//forwarding sample

			//get decision into array
			for(int i=0; i<10; i++)
				decision_scores[i] = exp(mlp.outputs->frames[0][i]);



			// finding value and index of first and second maxima
			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			decision_scores[first_max_index]=-99;
			LibraryFunctions::getMax(decision_scores,10,second_max_value,second_max_index);
			//check if max score is the corrrect class
			if(first_max_index==d)
				actual_accuracy++;


			// rejecting patterns that violates thresholding condittions
			for(int t=0;t<Nt; t++)
			{
				float th= ((float)(t))/((float) Nt);
				if((first_max_value-second_max_value)<th)
					n_rejected[t]++;
				else if(first_max_index==d)
					correct[t]++;
			}

		}

		float Accuracy(0);
		int numRejected(0);
		float errorCount(0);
		////calculatin recognition time
		float recog_time(0);
		Timer timer;
		for(int t = 0; t < test_data->n_examples; t++)
		{

			test_data->setExample(t);
			int d= test_data->targets->frames[0][0];//actual value of sample

			real decision_scores[10];// to hold decision scores

			timer.resume();
			mlp.forward(test_data->inputs);//forwarding sample
			timer.stop();

			for(int i=0; i<10; i++)
				decision_scores[i] = exp(mlp.outputs->frames[0][i]);

			real first_max_value;
			long first_max_index;

			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			if (first_max_value>threshold)
			{
			if (first_max_index==d)
			{
				Accuracy++;
			}
			else
			{
				//wrong classified
				errorCount++;
			}
			}else
			{
				//less than threshold so it will be rejected.
				numRejected++;

			}


		}
		recog_time= timer.getTime();


		fout<<endl;
		fout<<"Neural network classifier using  "<<test_file<<endl;
		fout<<"-------------------------------------------------"<<endl;
		fout<<"Accuracy of the classification using the threshold=  "<<threshold<<endl;
		fout<<"number of correct samples = "<<Accuracy  <<endl;
					fout<<"Accuracy = "<<Accuracy*(100.0/(float)test_data->n_examples)<<" %"<<endl;
					fout<<"Actual Accuracy after rejection = "<<Accuracy*(100.0/(float)(test_data->n_examples-numRejected))<<" %"<<endl;

		fout<<"number of rejected samples ="<< numRejected<<" sample "<<endl;
		fout<<" number of error samples = "<< errorCount <<"  with percentage = "<< errorCount*(100.0/(float)test_data->n_examples)<<" %"<<endl;
		fout<<"-------------------------------------------------"<<endl;

		bool found(false);
		for(int t=0; t<Nt; t++)
			if(correct[t]/(10000-n_rejected[t])*100>99.5)
			{
				fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
				cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

				fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
				cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;

				fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;
				cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;

				float th= ((float)(t))/((float) Nt);
				fout<<"th= "<<th<<endl;
				cout<<"th= "<<th<<endl;

				fout<<"Recognition Time= "<<recog_time<<endl;
				cout<<"Recognition Time= "<<recog_time<<endl;






				found=true;
				break;

			}

			if(!found){
				fout<<"99.5% not reached!"<<endl;
				cout<<"99.5% not reached!"<<endl;
			}
		delete(allocator);


}
void RunOVAClassifiers::PcaQuadTrain(char* train_file, int num_of_features,char* model,char* matrix_file,char* mu_file){
	Allocator *allocator = new Allocator;
	if (model==NULL)
		{
			model="PACQuadmodel";
		}

		//DiskXFile model_file(model, "w");
	//	char train_file[]="D:/datasets/mahdbase_training_set.txt";
		int numPCA=40;
		int FeatInput=820;
//		char matrix_file[]="mahdbase_pca_matrix.txt";
//		char mu_file[]="pca_mu.txt";
		ifstream fin_mat(matrix_file);
		if(fin_mat.fail()){cout<<"Cannot open file "<<matrix_file; return; }
		ifstream fin_mu(mu_file);
		if(fin_mu.fail()){cout<<"Cannot open file "<<mu_file; return ; };


		Sequence PCA_matrix(num_of_features,numPCA);
		for(int n=0; n<num_of_features; n++)
			for(int i=0; i<numPCA; i++)
				{float r; fin_mat>>r; PCA_matrix.frames[n][i]=r;}


		Sequence PCA_mu(1,num_of_features);
		for(int n=0; n<num_of_features; n++)
		{float r; fin_mu>>r; PCA_mu.frames[0][n]=r;}

		// creating the linear machine
		ConnectedMachine linear_net;
		Linear *c1= new(allocator) Linear(FeatInput,10);
		//LogSoftMax *c2= new(allocator) LogSoftMax(10);
		Tanh *c2= new(allocator) Tanh(10);
		linear_net.addFCL(c1);
		linear_net.addFCL(c2);
		linear_net.build();
		linear_net.setPartialBackprop();


		// reading training data from file and converting it to one hot class format
		Sequence *class_labels= new(allocator) Sequence(10,10);
		int i,j;
		for(i=0;i<=9;i++)
		{
			for(j=0;j<=9;j++)
			{
				if(i==j)
					class_labels->frames[i][j]=1;
				else
					class_labels->frames[i][j]=-1;
			}
		}
		DataSet *temp1;
		temp1=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

		MemoryDataSet *qpca_train_set= new(allocator) MemoryDataSet();
		Sequence *inputs[60000];
		Sequence *targets[60000];
		for(int k=0; k<60000; k++)
		{
			inputs[k]= new(allocator) Sequence(1,FeatInput);
			targets[k]= new(allocator) Sequence(1,1);
		}
		qpca_train_set->setInputs(inputs, 60000);
		qpca_train_set->setTargets(targets, 60000);

		for(int k=0; k<qpca_train_set->n_examples; k++)
		{
			qpca_train_set->setExample(k);
			temp1->setExample(k);
			qpca_train_set->targets->frames[0][0]= temp1->targets->frames[0][0];

			Sequence pca_train_sample(1,numPCA);
			for(int i=0; i<numPCA; i++)
			{
				float sum(0);
				for(int n=0; n<num_of_features; n++)
				{ sum+= (temp1->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

				pca_train_sample.frames[0][i]=sum;
			}

			int c(0);
			for(int i=0; i<numPCA; i++)
				for(int j=i; j<numPCA; j++)
					qpca_train_set->inputs->frames[0][c++]= (pca_train_sample.frames[0][i])*(pca_train_sample.frames[0][j]);


		}

		allocator->free(temp1);


		DataSet *train_data= new(allocator) ClassFormatDataSet(qpca_train_set,class_labels);



		// specifying cost function
		OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
		//Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);
		Criterion *criterion = new(allocator) MSECriterion(10);


		// creating the trainer
		StochasticGradient trainer(&linear_net, criterion);
		trainer.setIOption("max iter",100);

		// training...
		trainer.train(train_data, NULL);

		//saving model to file
		linear_net.save(model);

		fin_mat.close();
		fin_mu.close();


		delete(allocator);



}
void RunOVAClassifiers::PcaQuandTest(char* test_file,int num_of_features ,double threshold ,char* model,char* matrix_file,char* mu_file){

	Allocator *allocator = new Allocator;
	int numPCA=40;
	int FeatInput=820;//num_of_features+numPCA
	if (model==NULL)
		{
			model="PACQuadmodel";
		}

		//char test_file[]="D:/datasets/mahdbase_testing_set.txt";

//		char matrix_file[]="pca_matrix.txt";
//		char mu_file[]="pca_mu.txt";
		ifstream fin_mat(matrix_file);
		if(fin_mat.fail()){cout<<"Cannot open file "<<matrix_file; return; }
		ifstream fin_mu(mu_file);
		if(fin_mu.fail()){cout<<"Cannot open file "<<mu_file;  return;};


		Sequence PCA_matrix( num_of_features,numPCA);
		for(int n=0; n< num_of_features; n++)
			for(int i=0; i<numPCA; i++)
				{float r; fin_mat>>r; PCA_matrix.frames[n][i]=r;}


		Sequence PCA_mu(1, num_of_features);
		for(int n=0; n< num_of_features; n++)
		{float r; fin_mu>>r; PCA_mu.frames[0][n]=r;}


		// creating the linear machine
		ConnectedMachine linear_net;
		Linear *c1= new(allocator) Linear(FeatInput,10);
		//LogSoftMax *c2= new(allocator) LogSoftMax(10);
		Tanh *c2= new(allocator) Tanh(10);
		linear_net.addFCL(c1);
		linear_net.addFCL(c2);
		linear_net.build();
		linear_net.setPartialBackprop();

		//loading the model
		linear_net.load(model);



		// reading testing data from file and converting it to one hot class format
		Sequence *class_labels= new(allocator) Sequence(10,10);
		int i,j;
		for(i=0;i<=9;i++)
		{
			for(j=0;j<=9;j++)
			{
				if(i==j)
					class_labels->frames[i][j]=1;
				else
					class_labels->frames[i][j]=0;
			}
		}

		DataSet *test_data =new(allocator) MatDataSet(test_file, num_of_features,1,false,-1,false);


		float actual_accuracy(0);
		const int Nt=100000;
		float n_rejected[Nt]; // number of rejected samples
		float correct[Nt];
		for(int t=0; t<Nt; t++)
		{
			correct[t]=0;
			n_rejected[t]=0;
		}

		ofstream fout("PCA_Quad_RR_results.txt");

		for(int k = 0; k < test_data->n_examples; k++)
		{

			test_data->setExample(k);
			int d= test_data->targets->frames[0][0];//actual value of sample


			Sequence quad_test_sample(1,FeatInput);
			Sequence pca_test_sample(1,numPCA);
			for(int i=0; i<numPCA; i++)
			{
				float sum(0);
				for(int n=0; n<num_of_features; n++)
				{ sum+= (test_data->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

				pca_test_sample.frames[0][i]=sum;
			}

			int c(0);
			for(int i=0; i<numPCA; i++)
				for(int j=i; j<numPCA; j++)
					quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);



			real decision_scores[10];// to hold decision scores

			linear_net.forward(&quad_test_sample);//forwarding sample

			for(int i=0; i<10; i++)
				//decision_scores[i] = exp(linear_net.outputs->frames[0][i]);
				decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;

			real first_max_value;
			long first_max_index;
			real second_max_value;
			long second_max_index;

			// finding value and index of first and second maxima
			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			decision_scores[first_max_index]=-99;
			LibraryFunctions::getMax(decision_scores,10,second_max_value,second_max_index);

			if(first_max_index==d)
				actual_accuracy++;


			// rejecting patterns that violates thresholding condittions
			for(int t=0;t<Nt; t++)
			{
				float th= ((float)(t))/((float) Nt);
				if((first_max_value-second_max_value)<th)
					n_rejected[t]++;
				else if(first_max_index==d)
					correct[t]++;
			}



		}
		float Accuracy(0);
		int numRejected(0);
		float errorCount(0);
		//calculating time
		Timer timer;
		for(int k = 0; k < test_data->n_examples; k++)
		{

			test_data->setExample(k);
			int d= test_data->targets->frames[0][0];//actual value of sample


			Sequence quad_test_sample(1,FeatInput);
			Sequence pca_test_sample(1,numPCA);
			for(int i=0; i<numPCA; i++)
			{
				float sum(0);
				for(int n=0; n< num_of_features; n++)
				{ sum+= (test_data->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

				pca_test_sample.frames[0][i]=sum;
			}

			int c(0);
			for(int i=0; i<numPCA; i++)
				for(int j=i; j<numPCA; j++)
					quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);



			real decision_scores[10];// to hold decision scores

			linear_net.forward(&quad_test_sample);//forwarding sample

			for(int i=0; i<10; i++)
				//decision_scores[i] = exp(linear_net.outputs->frames[0][i]);
				decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;

			real first_max_value;
			long first_max_index;

			// finding value and index of first and second maxima
			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);


			if (first_max_value>threshold)
					{
					if (first_max_index==d)
					{
						Accuracy++;
					}
					else
					{
						//wrong classified
						errorCount++;
					}
					}else
					{
						//less than threshold so it will be rejected.
						numRejected++;

					}



		}
		float recog_time=timer.getTime();
		fout<<endl;
		fout<<"PCA + quad classifier using  "<<test_file<<endl;
		fout<<"-------------------------------------------------"<<endl;
		fout<<"Accuracy of the classification using the threshold=  "<<threshold<<endl;
	fout<<"number of correct samples = "<<Accuracy  <<endl;
		fout<<"Accuracy = "<<Accuracy*(100.0/(float)test_data->n_examples)<<" %"<<endl;
		fout<<"Actual Accuracy after rejection = "<<Accuracy*(100.0/(float)(test_data->n_examples-numRejected))<<" %"<<endl;

		fout<<"number of rejected samples ="<< numRejected<<" sample "<<endl;
		fout<<" number of error samples = "<< errorCount <<"  with percentage = "<< errorCount*(100.0/(float)test_data->n_examples)<<" %"<<endl;
		fout<<"-------------------------------------------------"<<endl;


		bool found(false);
		for(int t=0; t<Nt; t++)
			if(correct[t]/(10000-n_rejected[t])*100>99.5)
			{
				fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
				cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

				fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
				cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;

				fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;
				cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;

				float th= ((float)(t))/((float) Nt);
				fout<<"th= "<<th<<endl;
				cout<<"th= "<<th<<endl;


				//float recog_time= timer.getTime();
				fout<<"Recognition Time: "<<recog_time<<endl;
				cout<<"Recognition Time: "<<recog_time<<endl;

				found=true;
				break;

			}

			if(!found){
				cout<<"99.5% not reached!"<<endl;
				fout<<"99.5% not reached!"<<endl;
			}
			fout.close();
			fin_mat.close();
			fin_mu.close();

		delete(allocator);


}
void RunOVAClassifiers::FisherTest(char* test_file, int num_of_features,float threshold , char* wsfile, char* bsfile) {

	Allocator *allocator = new Allocator;

	//int num_of_features=200+1;
	//char test_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");

	//const int num_of_features=200;
	//char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";

	DataSet *test_set;
	test_set
			= new (allocator) MatDataSet(test_file, num_of_features, 1, false, -1, false);

	float Ws[45][num_of_features];
	float bs[num_of_features];
	//if (wsfile==NULL)

	ifstream w_fin(wsfile);
	ifstream b_fin(bsfile);
	int count(-1);
	for (int i = 0; i <= 8; i++) {
		for (int j = i + 1; j <= 9; j++) {
			count++;
			b_fin >> bs[count];
			for (int n = 0; n < num_of_features; n++)
				w_fin >> Ws[count][n];

		}
	}




	float actual_accuracy(0);
		const int Nt=100000;
		float n_rejected[Nt]; // number of rejected samples
		float correct[Nt];
		for(int t=0; t<Nt; t++)
		{
			correct[t]=0;
			n_rejected[t]=0;
		}

		ofstream fout("Fisher_RR_results.txt");

		for(int t = 0; t < test_set->n_examples; t++)
		{

			 test_set->setExample(t);
			int d= test_set->targets->frames[0][0];//actual value of sample

			real votes[10];
						for (int i = 0; i < 10; i++)
							votes[i] = 0;


					int count(-1);
					for (int i = 0; i <= 8; i++)
						for (int j = i + 1; j <= 9; j++) {
							count++;

							float out(bs[count]);
							for (int n = 0; n < num_of_features; n++)
								out += test_set->inputs->frames[0][n] * Ws[count][n];

							if (out > 0)
								votes[i]++;
							else
								votes[j]++;

						}

					// finding value and index of first maximum






			real first_max_value;
			long first_max_index;
			real second_max_value;
			long second_max_index;

			// finding value and index of first and second maxima
			LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);
			//LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			votes[first_max_index]=-99;
			//LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);
			LibraryFunctions::getMax(votes,10,second_max_value,second_max_index);

			if(first_max_index==d)
				actual_accuracy++;


			// rejecting patterns that violates thresholding condittions
			for(int t=0;t<Nt; t++)
			{
				float th= ((float)(t))/((float) Nt);
				if((first_max_value-second_max_value)<th)
					n_rejected[t]++;
				else if(first_max_index==d)
					correct[t]++;
			}



		}



		real accuracy(0);
		int numRejected(0);
			float errorCount(0);

			Timer timer;
			//timer.stop();
			for (int t = 0; t < test_set->n_examples; t++) {
				real votes[10];
				for (int i = 0; i < 10; i++)
					votes[i] = 0;

				test_set->setExample(t);
				int d = test_set->targets->frames[0][0];

				real first_max_value;
				long first_max_index;

				int count(-1);
				for (int i = 0; i <= 8; i++)
					for (int j = i + 1; j <= 9; j++) {
						count++;

						float out(bs[count]);
						for (int n = 0; n < num_of_features; n++)
							out += test_set->inputs->frames[0][n] * Ws[count][n];

						if (out > 0)
							votes[i]++;
						else
							votes[j]++;

					}

				// finding value and index of first maximum
				LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);

				long max_index;
				float max_value;
//				for (int i = 0; i < 10; i++) {
//					LibraryFunctions::getMax(votes, 10, max_value, max_index);
//					//cout << max_index << " ";
//					votes[max_index] = -9999999;
//				}
			//	cout << endl;
				if (first_max_value>threshold)
									{
									if (first_max_index==d)
									{
										accuracy++;
									}
									else
									{
										//wrong classified
										errorCount++;
									}
									}else
									{
										//less than threshold so it will be rejected.
										numRejected++;

									}


//				if (first_max_index == d)
//					accuracy++;

				//cout<<d<<"->"<<first_max_index<<endl;
				//getch();


			}
			float time1 = timer.getRunTime();
		//	accuracy /= test_set->n_examples;
		fout<<"Fisher classifier using  "<<test_file<<endl;
			fout<<"-------------------------------------------------"<<endl;
			fout<<"Accuracy of the classification using the threshold=  "<<threshold<<endl;
			fout<<"number of correct samples = "<<accuracy  <<endl;
			fout<<"Accuracy = "<<accuracy*(100.0/(float)test_set->n_examples)<<" %"<<endl;
			fout<<"Actual Accuracy after rejection = "<<accuracy*(100.0/(float)(test_set->n_examples-numRejected))<<" %"<<endl;

			fout<<"number of rejected samples ="<< numRejected<<" sample "<<endl;
			fout<<" number of error samples = "<< errorCount <<"  with percentage = "<< errorCount*(100.0/(float)test_set->n_examples)<<" %"<<endl;
			cout << "Recognition Time = " << time1 << endl;
			fout<<"-------------------------------------------------"<<endl;


			bool found(false);
			for(int t=0; t<Nt; t++)
				if(correct[t]/(10000-n_rejected[t])*100>99.5)
				{
					fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
					cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

					fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
					cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;

					fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;
					cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;

					float th= ((float)(t))/((float) Nt);
					fout<<"th= "<<th<<endl;
					cout<<"th= "<<th<<endl;


					//float recog_time= timer.getTime();
					fout<<"Recognition Time: "<<time1<<endl;
					cout<<"Recognition Time: "<<time1<<endl;

					found=true;
					break;

				}

				if(!found){
					cout<<"99.5% not reached!"<<endl;
					fout<<"99.5% not reached!"<<endl;
				}
				fout.close();











//	ofstream fout("C12-F03_full_results.txt");

	//cout<<"Accuracy = "<<accuracy/100<<endl;

	//float recog_time = timer.getRunTime() / (float) n_iter;
//	float recog_time2 = timer.getTime() / (float) n_iter;


	//cout << "Accuracy = " << accuracy * 100 << endl;
	//cout << "Recognition Time = " << time1 << endl;
//
//	ofstream fout2("results_linear.txt");
//	fout2 << " This is the linear =  " << endl;
//	fout2 << "Accuracy = " << accuracy * 100 << endl;
//	fout2 << "timer.getRunTime()" << time1 << endl;
//	fout << "Recognition Time = " << recog_time << endl;
//	fout << "Recognition Time2 = " << recog_time2 << endl;

	delete (allocator);

}
//void RunOVAClassifiers::svmGaussianTrain(char* train_file, int num_of_features,float C,float g){}
//void RunOVAClassifiers::svmGaussianTest(char* test_file,int num_of_features,float C,float g,double threshold){}
