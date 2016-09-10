#include "StdAfx.h"
#include "LinearClassifier.h"
#include "abstractclassifier.h"

LinearClassifier::LinearClassifier(void)
{
}

LinearClassifier::~LinearClassifier(void)
{
}
void LinearClassifier::TEST()
{

	Allocator *allocator = new Allocator;


	char test_file[]="D:/datasets/mahdbase_testing_set.txt";


	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(28*28,10);
	//LogSoftMax *c2= new(allocator) LogSoftMax(10);
	Tanh *c2= new(allocator) Tanh(10);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();

	//loading the model
	linear_net.load("model");


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

	DataSet *test_data =new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);



	float actual_accuracy(0);
	const int Nt=100000;
	float n_rejected[Nt]; // number of rejected samples
	float correct[Nt];
	for(int t=0; t<Nt; t++)
	{
		correct[t]=0;
		n_rejected[t]=0;
	}

	ofstream fout("results.txt");
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
		this->getMax(decision_scores,10,first_max_value,first_max_index);
		decision_scores[first_max_index]=-99;
		this->getMax(decision_scores,10,second_max_value,second_max_index);

		
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

	//cout<<correct[Nt-1]/(10000-n_rejected[Nt-1])*100<<endl; getch();


	////calculating time
	//Timer timer;
	float recog_time(0);
	//for(int iter=0; iter<10; iter++)
	//for(int k = 0; k < test_data->n_examples; k++)
	//{


	//	test_data->setExample(k);
	//	int d= test_data->targets->frames[0][0];//actual value of sample

	//	real decision_scores[10];// to hold decision scores

	//	linear_net.forward(test_data->inputs);//forwarding sample

	//	for(int i=0; i<10; i++)
	//		decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;

	//	real first_max_value;
	//	long first_max_index;

	//	// finding value and index of first and second maxima
	//	getMax(decision_scores,10,first_max_value,first_max_index);

	//}

	//recog_time= timer.getTime()/10;


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

			fout<<"Recognition Time: "<<recog_time<<endl;
			cout<<"Recognition Time: "<<recog_time<<endl;


			found=true;
			break;

		}

		if(!found)
			cout<<"99.5% not reached!"<<endl;

	delete(allocator);


}


void LinearClassifier::TRAIN()
{
	 
	Allocator *allocator = new Allocator;

	char train_file[]="D:/datasets/mahdbase_training_set.txt";
	

	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(28*28,10);
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
	temp1=new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
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
	linear_net.save("model");


	delete(allocator);


}