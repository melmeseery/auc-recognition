
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
#include<iostream>
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


void TEST(const int n_hu)
{
	 
	Allocator *allocator = new Allocator;

	char test_file[]="C:/datasets/mnist_pca_training_set.txt";


	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(40,n_hu);
	Tanh *c2= new(allocator) Tanh(n_hu);
	Linear *c3= new(allocator) Linear(n_hu,10);
	LogSoftMax *c4= new(allocator) LogSoftMax(10);
	mlp.addFCL(c1);
	mlp.addFCL(c2);
	mlp.addFCL(c3);
	mlp.addFCL(c4);
	mlp.build();
	mlp.setPartialBackprop();

	//loading the model
	mlp.load("model");


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
	
	DataSet *test_data =new(allocator) MatDataSet(test_file,40,1,false,-1,false);

	//selecting 10,000 patterns from the training set to be a validation set
	int subset[10000];
	for(int i=0; i<10000; i++)
		subset[i]=i+50000;
	test_data->pushSubset(subset,10000);


	//creating and initializing the confusion matrix
	real confusion_matrix[10][10];
	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++)
			confusion_matrix[i][j]=0;


	real actual_accuracy(0);
	real accuracy(0);//accuracy after rejection
	long n_rejected(0); // number of rejected samples
	long n_false_rejected(0); // number of falsely rejected samples


	for(int t = 0; t < test_data->n_examples; t++)
	{

		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		real decision_scores[10];// to hold decision scores

		mlp.forward(test_data->inputs);//forwarding sample

		// de-logging the decisions
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
		if(first_max_value<0.9 || second_max_value>0.05)
		{
			n_rejected++;
			if(first_max_index==d)
				n_false_rejected++;
			continue;
		}

		//updating confusion matrix
		confusion_matrix[d][first_max_index]=confusion_matrix[d][first_max_index]+1;


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

	// displaying confusion matrix
	for(int i=0; i<10; i++)
	{
		for(int j=0; j<10; j++)
			cout<<confusion_matrix[i][j]<<"      ";
		cout<<endl;
	}
	
	cout<<"Rejected: "<<n_rejected<<endl;
	cout<<"FR : "<<n_false_rejected<<endl;

	
	
	delete(allocator);


}