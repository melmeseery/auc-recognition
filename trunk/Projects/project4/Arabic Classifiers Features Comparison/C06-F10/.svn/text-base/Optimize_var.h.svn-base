
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
#include<fstream>
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


void Optimize_var()
{

	Allocator *allocator = new Allocator;

	ofstream fout("Var_Optimizatin_Results.txt");

	int num_of_features=225;

	char train_file[]="C:/datasets/arabic_reduced_local_concavity_ourfeatures_training_set_full.txt";

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

	float vars[]={0.001,0.01, 0.1, 1, 10};

	for(int var_index=0 ; var_index<1
; var_index++)
	{
		// creating the knn machine
		ParzenMachine parzen(num_of_features,10,vars[var_index]);

		// creating the trainer
		NPTrainer trainer(&parzen);

		train_data->n_examples=50000;
		//training ...
		trainer.train(train_data, NULL);
		train_data->n_examples=60000;


		real accuracy(0);

		for(int t = 50000; t < train_data->n_examples; t++)
		{

			if(t%100==0)
				cout<<t<<endl;

			train_data->setExample(t);
			int d= temp1->targets->frames[0][0];//actual value of sample

			real *decision_scores;// pointer first element of an array holding decision scores

			// forwarding test sample
			parzen.forward(train_data->inputs);
			decision_scores= parzen.outputs->frames[0];

			real first_max_value;
			long first_max_index;


			// finding value and index of first maximum
			getMax(decision_scores,10,first_max_value,first_max_index);


			if(first_max_index==d)
				accuracy++;

		}



		cout<<"Accuracy: "<<accuracy/100<<endl;
		fout<<vars[var_index]<<" : "<<accuracy/100<<endl;
	}

	delete(allocator);


}

