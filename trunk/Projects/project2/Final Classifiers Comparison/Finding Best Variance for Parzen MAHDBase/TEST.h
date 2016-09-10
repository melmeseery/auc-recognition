
#include "MatDataSet.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "MSECriterion.h"
#include "OneHotClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "NPTrainer.h"
#include "KFold.h"
#include "KNN.h"
#include "MeanVarNorm.h"
#include "CmdLine.h"
#include "Random.h"
#include "ParzenMachine.h"
using namespace Torch;

#include<conio.h>
#include<iostream>
using namespace std;

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

	char train_file[]="C:/datasets/mahdbase_training_set.txt";

	Sequence class_labels(10,10);
	for(int i=0; i<10; i++)
	{
		for(int j=0; j<10; j++)
		{
			if(i==j)
				class_labels.frames[i][j]=1;
			else
				class_labels.frames[i][j]=0;
		}
	}
	

	// reading training and testing sets
	DataSet *temp1;
	temp1=new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,&class_labels);
	DataSet *temp2;
	temp2=new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
	DataSet *test_data= new(allocator) ClassFormatDataSet(temp2,&class_labels);

	//Selecting Training Set
	int subset_1[50000];
	for(int i=0; i<50000; i++)
		subset_1[i]=i;
	temp1->pushSubset(subset_1,50000);
	train_data->pushSubset(subset_1,50000);

	//Selecting Validation Set
	int subset_2[10000];
	for(int i=0; i<10000; i++)
		subset_2[i]=i+50000;
	temp2->pushSubset(subset_2,10000);
	test_data->pushSubset(subset_2,10000);

	
	// creating the knn machine
	ParzenMachine parzen(28*28,10,1);

	// creating the trainer
	NPTrainer trainer(&parzen);

	//training ...
	trainer.train(train_data, NULL);



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

		cout<<t<<endl;
		test_data->setExample(t);
		int d= temp2->targets->frames[0][0];//actual value of sample

		real *decision_scores;// pointer first element of an array holding decision scores

		// forwarding test sample
		parzen.forward(test_data->inputs);
		decision_scores= parzen.outputs->frames[0];

		real first_max_value;
		long first_max_index;
		real second_max_value;
		long second_max_index;

		// finding value and index of first and second maxima
		getMax(decision_scores,10,first_max_value,first_max_index);
		//cout<<d<<"->"<<first_max_index<<endl;
		//for(int z=0;z<=9;z++)
		//	cout<<decision_scores[z]<<endl;
		//cout<<endl<<endl;
		decision_scores[first_max_index]=-99;
		getMax(decision_scores,10,second_max_value,second_max_index);

		if(first_max_index==d)
			actual_accuracy++;
		
		// rejecting patterns that violates thresholding condittions
		if(first_max_value<0.9 || second_max_value>0.01)
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

