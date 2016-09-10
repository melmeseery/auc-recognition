
#include "MatDataSet.h"
#include "OneHotClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "QCTrainer.h"
#include "CmdLine.h"
#include "Random.h"
#include "SVMClassification.h"
#include "DiskXFile.h"
#include "ClassFormatDataSet.h"
#include "MatDataSet.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "MSECriterion.h"
#include "OneHotClassFormat.h"
#include "MultiClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "StochasticGradient.h"
#include "KFold.h"
#include "ConnectedMachine.h"
#include "Linear.h"
#include "Tanh.h"
#include "LogSoftMax.h"
#include "MeanVarNorm.h"
#include "DiskXFile.h"
#include "MemoryXFile.h"
#include "CmdLine.h"
#include "Random.h"
#include "Sigmoid.h"
#include "math.h"
#include "Timer.h"

#include <string.h>
#include<conio.h>
#include<vector>
using namespace Torch;
#include<fstream>
#include<iostream>
using namespace std;

int size ;

string* myStrings;

string getFile(int t ){

	if (myStrings==NULL){
	
	// do the old stuff 
		char filename[50];
			int writer, pass, digit;

			writer = t/100;
			pass = (t-(writer*100))/10;
			digit = (t-(writer*100))%10;
			sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
			string s=filename;
			return s;
	
	}
	else 
	{
	 
		return myStrings[t];
	
	}


}
void getFiles(char* filename){



	ifstream inFile;
     // inFile.open("files_names.txt");
    inFile.open(filename);
    if (!inFile) {
        cout << "Unable to open file";
        exit(1); // terminate with error
    }
    char x[500];
	

	//int size;
	int i=0;
	if (inFile>>size){
		cout<<"Size of file "<<size<<endl;
		myStrings=new string[size];
		
		while (inFile >> x) {

			myStrings[i]=x;
          if (i%400==0)
			cout<<" reading "<<myStrings[i].data()<<endl;
			i++;
		}
	}
    inFile.close();
		cout<<"Size of file "<<size<<endl;
   // cout << "Sum = " << sum << endl; 
    
}
void getMax(real* arr, long n_arr, real &max_value, long &max_index)
{
	max_index=0;
	max_value= arr[0];
	for(int i=1; i<n_arr; i++)
	{
		if(arr[i]>max_value)
		{
			max_index =i;
			max_value =arr[i];
		}
	}
}


void TEST_SVM_OVO()
{


	ifstream calib_fin("calibrating_parameters.txt");
	float As[10][10];
	float Bs[10][10];
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{calib_fin>>As[i][j]; calib_fin>>Bs[i][j];}
	}


	int num_of_features=28*28;

	Allocator *allocator = new Allocator;

	char test_file[]="C:/datasets/mahdbase_testing_set.txt";

	DiskXFile model_file("model", "r");


	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);


	ifstream sv_fin("support_vectors.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors.txt"; getch();}
	int n;
	sv_fin>>n;
	//cout<<n<<endl;
	//getch();
	vector<Sequence*> SupportVectors(n);
	for(int n=0; n<SupportVectors.size(); n++)
	{
		SupportVectors[n]= new Sequence(1,28*28);
		for(int f=0; f<28*28; f++)
		{sv_fin>>(SupportVectors[n]->frames[0][f]); /*cout<<(*SupportVectors[n])[f]<<endl; getch();*/}
	}


	ifstream sv_dist_fin("support_vectors_distribution.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors_distribution.txt"; getch();}
	sv_dist_fin>>n;
	vector<int> SupportVectorsDist(n);
	for(int n=0; n<SupportVectorsDist.size(); n++)
		sv_dist_fin>>SupportVectorsDist[n]; 



	// creating pairwise classifiers pointers
	float g=0.02;//0.0659
	float C=100;
	Kernel *kernel = new(allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];


	//loading pairwise classifiers in turn
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			cout<<i<<" vs "<<j<<endl;

			svms[i][j] = new(allocator) SVMClassification(kernel);

			svms[i][j]->loadXFile(&model_file);

		}//end i
	}//end j





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
	vector<float> KernelEvaluations(SupportVectors.size());
	for(int k = 0; k < test_set->n_examples; k++)
	{

		if(k%100==0)
			cout<<k<<endl;


		test_set->setExample(k);

		int d= test_set->targets->frames[0][0];

		for(int n=0; n<SupportVectors.size(); n++)
			KernelEvaluations[n]=kernel->eval(test_set->inputs,SupportVectors[n]);


		real binary_decisions[10][10];
		int count=0;
		for(int i=0; i<=8; i++)
		{
			for(int j=i+1; j<=9; j++)
			{
				float f=svms[i][j]->b;
				for(int n=0; n<svms[i][j]->n_support_vectors; n++)
					f += KernelEvaluations[SupportVectorsDist[count++]]*svms[i][j]->sv_alpha[n];

				binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j])); 
				binary_decisions[j][i]= 1-binary_decisions[i][j];
			}
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
		getMax(decisions,10,first_max_value,first_max_index);
		decisions[first_max_index]=-99;
		getMax(decisions,10,second_max_value,second_max_index);

		if(first_max_index==d)
			actual_accuracy++;

		//cout<<d<<"->"<<first_max_index<<endl; getch();

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

	if(!found)
		cout<<"99.5% not reached!"<<endl;

	
	
	//float recog_time(0);
	//Timer timer;
	//for(int k = 0; k < test_set->n_examples; k++)
	//{

	//	test_set->setExample(k);

	//	int d= test_set->targets->frames[0][0];

	//	for(int n=0; n<SupportVectors.size(); n++)
	//		KernelEvaluations[n]=kernel->eval(test_set->inputs,SupportVectors[n]);


	//	real binary_decisions[10][10];
	//	int count=0;
	//	for(int i=0; i<=8; i++)
	//	{
	//		for(int j=i+1; j<=9; j++)
	//		{
	//			float f=svms[i][j]->b;
	//			for(int n=0; n<svms[i][j]->n_support_vectors; n++)
	//				f += KernelEvaluations[SupportVectorsDist[count++]]*svms[i][j]->sv_alpha[n];

	//			binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j])); 
	//			binary_decisions[j][i]= 1-binary_decisions[i][j];
	//		}
	//	}

	//	real decisions[10];
	//	for(int i=0; i<=9; i++)
	//	{
	//		real prob_sum(0);
	//		for(int j=0; j<=9; j++)
	//		{
	//			if(i!=j)
	//			{
	//				prob_sum += 1/(binary_decisions[i][j]);
	//			}
	//		}

	//		decisions[i]= 1/(prob_sum - 8);
	//	}


	//	real first_max_value;
	//	long first_max_index;

	//	// finding value and index of first and second maxima
	//	getMax(decisions,10,first_max_value,first_max_index);

	//}
	//recog_time= timer.getTime();

	//fout<<"Recognition Time: "<<recog_time<<endl;
	//cout<<"Recognition Time: "<<recog_time<<endl;



	delete(allocator);


}

void TEST(char* filename,int n_features,char*  ifilename , float g,float C)
{
	if ( ifilename!=NULL)
	{
	getFiles(ifilename);
	}
	 	ofstream fout("test_stats_SVM.txt");
	Allocator *allocator = new Allocator;

	//char test_file[]="C:/datasets/mahdbase_testing_set.txt";
	
	char* test_file=filename;
	DiskXFile model_file("modelSVM", "r");


	// creating the SVMs
	//float g=0.03;//gamma
//	float C=50;
	Kernel *kernel = new(allocator) GaussianKernel(g);

	int total_num_of_support_vectors(0);

	// creating the SVMs
	//float g=0.03;//gamma
	//float C=50;
	//Kernel *kernel = new(allocator) GaussianKernel(g);
//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
	SVM* svms;// = allocator->alloc(sizeof(SVM));
	svms=new (allocator) SVMClassification(kernel);
	svms->setROption("C", C);




	//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
	///for(int i = 0; i <  NO_OF_CLASSES; i++)
	//{if (i==DIGIT_TRAINED ){
	//	svms[i] = new(allocator) SVMClassification(kernel);
		
		//loading SVM models in turn
		svms->loadXFile(&model_file);

		total_num_of_support_vectors+= svms->n_support_vectors;
//	}
	//}

	cout<<"number of support vectors = "<<total_num_of_support_vectors<<endl;
	


	// reading testing data from file and converting it to one hot class format
	Sequence *class_labels= new(allocator) Sequence( NO_OF_CLASSES,1);
	int i,j;
	//for(i=0;i<NO_OF_CLASSES;i++)
	//{
		for(j=0;j<2;j++)
		{
			if(i==1)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=-1;
		}
//	}
	cout<<"Reading data........."<<endl;
	DataSet *test_data =new(allocator) MatDataSet(test_file, n_features,1,false,-1,false);

	

	//creating and initializing the confusion matrix
	/*real confusion_matrix[NO_OF_CLASSES][NO_OF_CLASSES];
	for(int i=0; i<NO_OF_CLASSES; i++)
		for(int j=0; j<NO_OF_CLASSES; j++)
			confusion_matrix[i][j]=0;
*/

	real actual_accuracy(0);
	real accuracy(0);//accuracy after rejection
	long n_rejected(0); // number of rejected samples
	long n_false_rejected(0); // number of falsely rejected samples
long postive,negative,pos;
postive=0;
negative=0;
pos=0;

	Timer timer;
	timer.stop();

	long first_max_index=5;
	cout<<"Start forwding exapmles ...."<<endl;
	for(int t = 0; t < test_data->n_examples; t++)
	{

		if (t%100==0)
			cout<<t<<endl;


		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		real decision_scores(0);//[NO_OF_CLASSES];// to hold decision scores

		// forwarding test sample in all SVMs
		real sum(0);
		for(int i = 0; i < NO_OF_CLASSES; i++)
		{
			if (i==DIGIT_TRAINED ){
			timer.resume();
			svms->forward(test_data->inputs);
			timer.stop();

			decision_scores =svms->outputs->frames[0][0]; //exp(svms[i]->outputs->frames[0][0]);
			//sum+=decision_scores;
			if (decision_scores>0)
			{
			postive++;


			if(1==d){
           //supposed to be 5 and is 5 
			actual_accuracy++;



			}
			else {//d != trained digit  it classifier as 5 and it is not 5 
				pos++;
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56  
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				*/

			
				//cout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
				//fout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
           //+ve sample 
			}///if +ve example 



			}
		else{//score is less  !=d // supposed to be 5 but not classifed as 5 
			if (d==1)
				
			{	
				
				negative++;
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56  
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				*/
			///	cout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
				//fout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
			//supposed to be 5 but defined as something else. 
			// -ve sample 
			}//if-ve example 
			

			}//else for not recognizied





	}//for t samples 
			} 

	fout<<endl<<endl;
	//double samplerate=100.0/(double)fivesamples;
	fout<<" total number of samples    "<<test_data->n_examples<<endl;
	fout<<"Samples with result > 0:    "<<postive<<endl;
	fout<<"Samples of crrospending digit and less than 0 "<<negative <<"  false +ve samples "<<pos<<endl;
	
    fout<<"Samples with the corresponding digit and result > 0: "<<actual_accuracy<<"sample and "<<endl;//<< actual_accuracy*samplerate<<" % "<<endl;

	
		}
		}
		/*// to create probabilistic output
		for(int i=0; i<NO_OF_CLASSES; i++)
		{
			decision_scores[i]= decision_scores[i]/sum;
		}

		real first_max_value;
		long first_max_index;
		real second_max_value;
		long second_max_index;*/
/*
		// finding value and index of first and second maxima
		getMax(decision_scores,10,first_max_value,first_max_index);
		decision_scores[first_max_index]=-99;
		getMax(decision_scores,10,second_max_value,second_max_index);
*/
		


		
	//	}//function test . 
		
		// rejecting patterns that violates thresholding condittions
	/*	if(second_max_value>0.17)
		{
			n_rejected++;
			if(first_max_index==d)
				n_false_rejected++;
			continue;
		}
*/
		//updating confusion matrix
		//confusion_matrix[d][first_max_index]=confusion_matrix[d][first_max_index]+1;


	


	//calculating accuracy from confusion matrix
	/*real n_examples(0);
	real n_correct_examples(0);
	for(int i=0; i<10; i++)
		n_correct_examples += confusion_matrix[i][i];
	for(int i=0; i<NO_OF_CLASSES; i++)
		for(int j=0; j<NO_OF_CLASSES; j++)
			n_examples+= confusion_matrix[i][j];
	accuracy= n_correct_examples/n_examples;
	cout<<"Accuracy: "<<accuracy<<endl;
	cout<<"Actual Accuracy : "<<actual_accuracy/10000<<endl;

	// displaying confusion matrix
	for(int i=0; i<10; i++)
	{
		for(int j=0; j<10; j++)
			cout<<confusion_matrix[i][j]<<"      ";
		cout<<endl;
	}
	
	cout<<"Rejected: "<<n_rejected<<endl;
	cout<<"FR : "<<n_false_rejected<<endl;
	cout<<"Recognition Time :"<<timer.getTime()<<endl;

	*/
	
	delete(allocator);


}//function test 
void TESTLinear(char * filename, int n_features)
{
	float actual_accuracy(0);
	float accuracy(0);//accuracy after rejection
	long n_rejected(0); // number of rejected samples
	long n_false_rejected(0); // number of falsely rejected samples
long postive,negative;
postive=0;
negative=0;


	Allocator *allocator = new Allocator;


	char* test_file=filename;


	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(n_features,1);
	//LogSoftMax *c2= new(allocator) LogSoftMax(10);
	Tanh *c2= new(allocator) Tanh(1);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();

	//loading the model
	linear_net.load("modelSVM");


	// reading testing data from file and converting it to one hot class format
	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(j==5)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=0;
		}
	}

	DataSet *test_data =new(allocator) MatDataSet(test_file,n_features,1,false,-1,false);



	//float actual_accuracy(0);
	//const int Nt=100000;
	//float n_rejected[Nt]; // number of rejected samples
	//float correct[Nt];
	/*for(int t=0; t<Nt; t++)
	{
		correct[t]=0;
		n_rejected[t]=0;
	}*/

	ofstream fout("results.txt");
	for(int t = 0; t < test_data->n_examples; t++)
	{


		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		real decision_scores[10];// to hold decision scores

		linear_net.forward(test_data->inputs);//forwarding sample

		/*for(int i=0; i<10; i++)
			decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;
*/
int out=linear_net.outputs->frames[0][5];




//sum+=decision_scores;
			if (out>0)
			{
				postive++;


			if(DIGIT_TRAINED==d){
           //supposed to be 5 and is 5 
			actual_accuracy++;



			}
			else {//d != trained digit  it classifier as 5 and it is not 5 
				//postive++;
				char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56  
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				cout<<"### "<<t<<" "<<filename<<"; score = "<<decision_scores<<endl;
				fout<<"### "<<t<<" "<<filename<<"; score = "<<decision_scores<<endl;
           //+ve sample 
			}///if +ve example 



			}
		else{//score is less  !=d // supposed to be 5 but not classifed as 5 
			if (d==DIGIT_TRAINED)
				
			{	
				
				negative++;
				char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56  
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				cout<<"--- "<<t<<" "<<filename<<"; score = "<<decision_scores<<endl;
				fout<<"--- "<<t<<" "<<filename<<"; score = "<<decision_scores<<endl;
			//supposed to be 5 but defined as something else. 
			// -ve sample 
			}//if-ve example 
			

			}//else for not recognizied









	/*
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
		}*/

	}////trainign sampels 


		fout<<endl<<endl;
	//double samplerate=100.0/(double)fivesamples;
	fout<<"total number of samples    ="<<test_data->n_examples<<endl;
	fout<<"Samples with result > 0:    "<<postive<<endl;
	fout<<"Samples of crrospending digit and less than 0 ="<<negative<<endl;
    fout<<"Samples with the corresponding digit and result > 0: "<<actual_accuracy<<endl;
	
	
	//<<"sample and "<<endl;//<< actual_accuracy*samplerate<<" % "<<endl;


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

/*
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
			cout<<"99.5% not reached!"<<endl;*/

	delete(allocator);


}






