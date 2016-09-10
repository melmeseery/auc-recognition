
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


void Create_Ensemble_Validation_Results()
{

	Allocator *allocator = new Allocator;

	int number_of_features=156;

	ofstream fout("C06-F12_ensemble_validation_results.txt");

	char train_file[]="C:/datasets/arabic_low_dim_concat_ourfeatures_training_set_full.txt";

	DataSet *set=new(allocator) MatDataSet(train_file,number_of_features,1,false,-1,false);

	MemoryDataSet *train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[50000];
	Sequence *targets[50000];
	for(int n=0; n<50000; n++)
	{
		inputs[n]= new(allocator) Sequence(1,number_of_features);
		targets[n]= new(allocator) Sequence(1,1);
	}
	train_set->setInputs(inputs, 50000);
	train_set->setTargets(targets, 50000);
	train_set->n_examples=50000;
	for(int n=0; n<50000; n++)
	{
		train_set->setExample(n);
		set->setExample(n);

		train_set->targets->frames[0][0] = set->targets->frames[0][0];
		for(int i=0; i<number_of_features; i++)
			train_set->inputs->frames[0][i]=set->inputs->frames[0][i];
	}
	
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

	DataSet *formatted_train_set= new(allocator) ClassFormatDataSet(train_set,&class_labels);

	float var=1;
	// creating the knn machine
	ParzenMachine parzen(number_of_features,10,var);

	// creating the trainer
	NPTrainer trainer(&parzen);

	//training ...
	trainer.train(formatted_train_set, NULL);

	real accuracy(0);
	//testing on the validation set
	for(int t=0; t<10000; t++)
	{
		if(t%100==0)
			cout<<t<<endl;

		set->setExample(50000+t);
		int d=set->targets->frames[0][0];

		real *decision_scores;// pointer first element of an array holding decision scores

		// forwarding test sample
		parzen.forward(set->inputs);
		decision_scores= parzen.outputs->frames[0];

		real first_max_value;
		long first_max_index;

		// finding value and index of first maximum
		getMax(decision_scores,10,first_max_value,first_max_index);
		long max_index;
		float max_value;
		for(int i=0; i<10; i++)
		{
			getMax(decision_scores,10,max_value,max_index);
			fout<<max_index<<" ";
			decision_scores[max_index]=-9999999;
		}
		fout<<endl;


		if(first_max_index==d)
			accuracy++;	}

	cout<<accuracy/100<<endl;




	delete(allocator);


}