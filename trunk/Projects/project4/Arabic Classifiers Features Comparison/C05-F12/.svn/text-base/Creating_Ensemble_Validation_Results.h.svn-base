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


void Create_Ensemble_Validation_Results()
{

	Allocator *allocator = new Allocator;

	int number_of_features=156;

	ofstream fout("C05-F12_ensemble_validation_results.txt");

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



	// creating the knn machine
	const int k=3;
	KNN knn(10,k);
	
	// creating the trainer
	NPTrainer trainer(&knn);

	//training ...
	trainer.train(train_set, NULL);

	real accuracy(0);
	//testing on the validation set
	for(int t=0; t<10000; t++)
	{
		if(t%100==0)
			cout<<t<<endl;

		set->setExample(50000+t);
		int d=set->targets->frames[0][0];

		float votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;
		
		// forwarding test sample
		knn.forward(set->inputs);

		int indices[k];
		real norm=0;
		for(int j=0; j<k; j++)
		{
			train_set->setExample(knn.indices[j]);
			votes[(int)train_set->targets->frames[0][0]]++;
		}

		real first_max_value;
		long first_max_index;

		// finding value and index of first maximum
		getMax(votes,10,first_max_value,first_max_index);
		long max_index;
		float max_value;
		for(int i=0; i<10; i++)
		{
			getMax(votes,10,max_value,max_index);
			fout<<max_index<<" ";
			votes[max_index]=-9999999;
		}
		fout<<endl;


		if(first_max_index==d)
			accuracy++;	}

	cout<<accuracy/100<<endl;




	delete(allocator);


}