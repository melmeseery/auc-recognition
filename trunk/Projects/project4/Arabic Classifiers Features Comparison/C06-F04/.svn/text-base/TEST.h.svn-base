
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
#include "Timer.h"
using namespace Torch;
#include<fstream>
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

	int num_of_features=100;

	char train_file[]="D:/datasets/arabic_kirsh4_ourfeatures_training_set_full.txt";
	char test_file[]="D:/datasets/arabic_kirsh4_ourfeatures_testing_set_full.txt";

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
	temp1=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,&class_labels);
	DataSet *temp2;
	temp2=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);
	DataSet *test_data= new(allocator) ClassFormatDataSet(temp2,&class_labels);
	
	float var=0.1;
	// creating the knn machine
	ParzenMachine parzen(num_of_features,10,var);

	// creating the trainer
	NPTrainer trainer(&parzen);

	//training ...
	trainer.train(train_data, NULL);


	real accuracy(0);
	Timer timer;
	for(int t = 0; t < test_data->n_examples; t++)
	{

		//if(t%100==0)
		//	cout<<t<<endl;
		
		test_data->setExample(t);
		int d= temp2->targets->frames[0][0];//actual value of sample

		real *decision_scores;// pointer first element of an array holding decision scores

		// forwarding test sample
		parzen.forward(test_data->inputs);
		decision_scores= parzen.outputs->frames[0];

		real first_max_value;
		long first_max_index;


		// finding value and index of first maximum
		getMax(decision_scores,10,first_max_value,first_max_index);
		

		if(first_max_index==d)
			accuracy++;
		
	}
	float recog_time= timer.getRunTime();
	accuracy/=100;


	cout<<"Accuracy: "<<accuracy<<endl;
	cout<<"Recognition Time: "<<recog_time<<endl;

	ofstream fout("results.txt");
	fout<<"Accuracy: "<<accuracy<<endl;
	fout<<"Recognition Time: "<<recog_time<<endl;

	delete(allocator);


}

