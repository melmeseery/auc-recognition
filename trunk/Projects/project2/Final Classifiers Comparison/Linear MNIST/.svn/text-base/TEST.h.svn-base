
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
#include "Math.h"
#include<conio.h>
#include<iostream>
#include<fstream>
#include<timer.h>
using namespace std;

using namespace Torch;

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



void TEST()
{

	Allocator *allocator = new Allocator;


	char test_file[]="D:/datasets/mnist_testing_set.txt";


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
		getMax(decision_scores,10,first_max_value,first_max_index);
		decision_scores[first_max_index]=-99;
		getMax(decision_scores,10,second_max_value,second_max_index);

		
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


	//calculating time
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
		getMax(decision_scores,10,first_max_value,first_max_index);

	}

	recog_time= timer.getTime()/10;


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









//void TEST()
//{
//
//	Allocator *allocator = new Allocator;
//
//
//	char test_file[]="D:/datasets/mnist_testing_set.txt";
//	//DiskXFile *out_train_file= new(allocator) DiskXFile("out_train.txt","w");
//
//
//	// creating the linear machine
//	ConnectedMachine linear_net;
//	Linear *c1= new(allocator) Linear(28*28,10);
//	LogSoftMax *c2= new(allocator) LogSoftMax(10);
//	linear_net.addFCL(c1);
//	linear_net.addFCL(c2);
//	linear_net.build();
//	linear_net.setPartialBackprop();
//
//	//loading the model
//	linear_net.load("model");
//
//
//	// reading testing data from file and converting it to one hot class format
//	Sequence *class_labels= new(allocator) Sequence(10,10);
//	int i,j;
//	for(i=0;i<=9;i++)
//	{
//		for(j=0;j<=9;j++)
//		{
//			if(i==j)
//				class_labels->frames[i][j]=1;
//			else
//				class_labels->frames[i][j]=0;
//		}
//	}
//
//	DataSet *test_data =new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);
//
//
//
//	//creating and initializing the confusion matrix
//	real confusion_matrix[10][10];
//	for(int i=0; i<10; i++)
//		for(int j=0; j<10; j++)
//			confusion_matrix[i][j]=0;
//
//
//	float actual_accuracy(0);
//	long n_rejected[100]; // number of rejected samples
//	float correct[100];
//	for(int t=0; t<100; t++)
//	{
//		correct[t]=0;
//		n_rejected[t]=0;
//	}
//
//	ofstream fout("results.txt");
//	for(int k = 0; k < test_data->n_examples; k++)
//	{
//
//		test_data->setExample(k);
//		int d= test_data->targets->frames[0][0];//actual value of sample
//
//		real decision_scores[10];// to hold decision scores
//
//		linear_net.forward(test_data->inputs);//forwarding sample
//
//		// de-logging the decisions
//		for(int i=0; i<10; i++)
//			decision_scores[i] = exp(linear_net.outputs->frames[0][i]);
//
//		real first_max_value;
//		long first_max_index;
//		real second_max_value;
//		long second_max_index;
//
//		// finding value and index of first and second maxima
//		getMax(decision_scores,10,first_max_value,first_max_index);
//		decision_scores[first_max_index]=-99;
//		getMax(decision_scores,10,second_max_value,second_max_index);
//
//		if(first_max_index==d)
//			actual_accuracy++;
//
//		// rejecting patterns that violates thresholding condittions
//		for(int t=0;t<100; t++)
//		{
//			if(first_max_value<t*0.01)
//				n_rejected[t]++;
//			else if(first_max_index==d)
//				correct[t]++;
//		}
//
//	}
//
//	//for(int t=0; t<100; t++)
//	//{ cout<<correct[t]/(10000-n_rejected[t])*100<<endl; getch();}
//
//	bool found(false);
//	for(int t=0; t<100; t++)
//		if(correct[t]/(10000-n_rejected[t])*100>99.5)
//		{
//			fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
//			cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
//
//			fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
//			cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
//			
//			fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;		
//			cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;
//
//			fout<<"th= "<<t*0.01<<endl;
//			cout<<"th= "<<t*0.01<<endl;
//
//			found=true;
//			break;
//
//		}
//
//		if(!found)
//			cout<<"99.5% not reached!"<<endl;
//
//	delete(allocator);
//
//
//}
//




//void TEST()
//{
//	 
//	Allocator *allocator = new Allocator;
//
//	char test_file[]="C:/datasets/mnist_testing_set.txt";
//	//DiskXFile *out_train_file= new(allocator) DiskXFile("out_train.txt","w");
//
//
//	// creating the linear machine
//	ConnectedMachine linear_net;
//	Linear *c1= new(allocator) Linear(28*28,10);
//	LogSoftMax *c2= new(allocator) LogSoftMax(10);
//	linear_net.addFCL(c1);
//	linear_net.addFCL(c2);
//	linear_net.build();
//	linear_net.setPartialBackprop();
//
//	//loading the model
//	linear_net.load("model");
//
//
//	// reading testing data from file and converting it to one hot class format
//	Sequence *class_labels= new(allocator) Sequence(10,10);
//	int i,j;
//	for(i=0;i<=9;i++)
//	{
//		for(j=0;j<=9;j++)
//		{
//			if(i==j)
//				class_labels->frames[i][j]=1;
//			else
//				class_labels->frames[i][j]=0;
//		}
//	}
//	
//	DataSet *test_data =new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);
//
//	
//
//	//creating and initializing the confusion matrix
//	real confusion_matrix[10][10];
//	for(int i=0; i<10; i++)
//		for(int j=0; j<10; j++)
//			confusion_matrix[i][j]=0;
//
//
//	real accuracy(0), actual_accuracy(0);
//	long n_rejected(0); // number of rejected samples
//	long n_false_rejected(0); // number of falsely rejected samples
//
//
//	for(int t = 0; t < test_data->n_examples; t++)
//	{
//
//		test_data->setExample(t);
//		int d= test_data->targets->frames[0][0];//actual value of sample
//
//		real decision_scores[10];// to hold decision scores
//
//		linear_net.forward(test_data->inputs);//forwarding sample
//
//		// de-logging the decisions
//		for(int i=0; i<10; i++)
//			decision_scores[i] = exp(linear_net.outputs->frames[0][i]);
//
//		real first_max_value;
//		long first_max_index;
//		real second_max_value;
//		long second_max_index;
//
//		// finding value and index of first and second maxima
//		getMax(decision_scores,10,first_max_value,first_max_index);
//		decision_scores[first_max_index]=-99;
//		getMax(decision_scores,10,second_max_value,second_max_index);
//		
//		if(first_max_index==d)
//			actual_accuracy++;
//		
//		// rejecting patterns that violates thresholding condittions
//		if(first_max_value<0.99 || second_max_value>0.05)
//		{
//			n_rejected++;
//			if(first_max_index==d)
//				n_false_rejected++;
//			continue;
//		}
//
//		
//		//updating confusion matrix
//		confusion_matrix[d][first_max_index]=confusion_matrix[d][first_max_index]+1;
//
//
//	}
//
//
//	//calculating accuracy from confusion matrix
//	real n_examples(0);
//	real n_correct_examples(0);
//	for(int i=0; i<10; i++)
//		n_correct_examples += confusion_matrix[i][i];
//	for(int i=0; i<10; i++)
//		for(int j=0; j<10; j++)
//			n_examples+= confusion_matrix[i][j];
//	accuracy= n_correct_examples/n_examples;
//	cout<<"Accuracy: "<<accuracy<<endl;
//	cout<<"Actual Accuracy: "<<actual_accuracy/100<<endl;
//
//	// displaying confusion matrix
//	for(int i=0; i<10; i++)
//	{
//		for(int j=0; j<10; j++)
//			cout<<confusion_matrix[i][j]<<"      ";
//		cout<<endl;
//	}
//	
//	cout<<"Rejected: "<<n_rejected<<endl;
//	cout<<"FR : "<<n_false_rejected<<endl;
//
//	
//	
//	delete(allocator);
//
//
//}


