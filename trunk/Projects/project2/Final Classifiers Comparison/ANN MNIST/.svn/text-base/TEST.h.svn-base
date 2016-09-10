
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
#include "Timer.h"
#include<iostream>
#include<fstream>
#include<conio.h>
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

	char test_file[]=test_mnist_file;

     //char test_file[]="D:/datasets/mnist_testing_set.txt";

	const int n_hu=450;
	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(28*28,n_hu);
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

	//loading the model
	mlp.load("model");



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

	for(int t = 0; t < test_data->n_examples; t++)
	{

		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		real decision_scores[10];// to hold decision scores

		mlp.forward(test_data->inputs);//forwarding sample

		for(int i=0; i<10; i++)
			decision_scores[i] = exp(mlp.outputs->frames[0][i]);

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


	//calculatin recognition time
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

		getMax(decision_scores,10,first_max_value,first_max_index);
		
	}
	recog_time= timer.getTime();


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

		if(!found)
			cout<<"99.5% not reached!"<<endl;

	delete(allocator);


}




//void TEST()
//{
//	 
//	Allocator *allocator = new Allocator;
//
//	char test_file[]="C:/datasets/mnist_testing_set.txt";
//
//
//	const int n_hu=450;
//	// creating the mlp
//	ConnectedMachine mlp;
//	Linear *c1= new(allocator) Linear(28*28,n_hu);
//	Tanh *c2= new(allocator) Tanh(n_hu);
//	Linear *c3= new(allocator) Linear(n_hu,10);
//	LogSoftMax *c4= new(allocator) LogSoftMax(10);
//	mlp.addFCL(c1);
//	mlp.addFCL(c2);
//	mlp.addFCL(c3);
//	mlp.addFCL(c4);
//	mlp.build();
//	mlp.setPartialBackprop();
//
//	//loading the model
//	mlp.load("model");
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
//	//creating and initializing the confusion matrix
//	real confusion_matrix[10][10];
//	for(int i=0; i<10; i++)
//		for(int j=0; j<10; j++)
//			confusion_matrix[i][j]=0;
//
//
//	real actual_accuracy(0);
//	real accuracy(0);//accuracy after rejection
//	long n_rejected(0); // number of rejected samples
//	long n_false_rejected(0); // number of falsely rejected samples
//
//
//	Timer timer;
//	timer.stop();
//	for(int t = 0; t < test_data->n_examples; t++)
//	{
//
//		test_data->setExample(t);
//		int d= test_data->targets->frames[0][0];//actual value of sample
//
//		real decision_scores[10];// to hold decision scores
//
//		timer.resume();
//		mlp.forward(test_data->inputs);//forwarding sample
//		timer.stop();
//
//		// de-logging the decisions
//		for(int i=0; i<10; i++)
//			decision_scores[i] = exp(mlp.outputs->frames[0][i]);
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
//		if(first_max_value<0.9 || second_max_value>0.05)
//		{
//			n_rejected++;
//			if(first_max_index==d)
//				n_false_rejected++;
//			continue;
//		}
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
//	cout<<"Actual Accuracy : "<<actual_accuracy/10000<<endl;
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
//	cout<<"Recognition Time : "<<timer.getTime()<<endl;
//
//	
//	
//	delete(allocator);
//
//
//}