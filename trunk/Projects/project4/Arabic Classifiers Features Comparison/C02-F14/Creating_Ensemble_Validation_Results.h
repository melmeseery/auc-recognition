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
#include <fstream>
#include <iostream>
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


void Create_Ensemble_Validation_Results()
{

	Allocator *allocator = new Allocator;

	int number_of_features=200;

	ofstream fout("C02-F14_ensemble_validation_results.txt");


	char train_file[]="C:/datasets/arabic_local_chain_ourfeatures_training_set_full.txt";
	DataSet *train_set =new(allocator) MatDataSet(train_file,number_of_features,1,false,-1,false);


	ConnectedMachine *nets[10][10];
	Linear *lin_layers[10][10];
	Tanh *out_layers[10][10];
	StochasticGradient *trainers[10][10];


	MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for(int n=0; n<20000; n++)
	{
		inputs[n]= new(allocator) Sequence(1,number_of_features);
		targets[n]= new(allocator) Sequence(1,1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);

	OneHotClassFormat class_format(1);
	Criterion *criterion = new(allocator) MSECriterion(1);

	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			lin_layers[i][j]= new(allocator) Linear(number_of_features,1);
			out_layers[i][j]= new(allocator) Tanh(1);
			nets[i][j]= new(allocator) ConnectedMachine();
			nets[i][j]->addFCL(lin_layers[i][j]);
			nets[i][j]->addFCL(out_layers[i][j]);
			nets[i][j]->build();
			trainers[i][j]= new(allocator) StochasticGradient(nets[i][j], criterion);
			trainers[i][j]->setIOption("max iter",100);

			counter=0;
			for(long k=0; k<50000; k++)
			{

				train_set->setExample(k);
				int d= train_set->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<number_of_features; f++)
					{
						current_train_set->inputs->frames[0][f]=
							train_set->inputs->frames[0][f];
					}

					if(d==i)
						current_train_set->targets->frames[0][0]= 1;
					else
						current_train_set->targets->frames[0][0]= -1;

					counter++;
				}

			}
			cout<<counter<<endl;

			current_train_set->setExample(0);
			current_train_set->n_examples= counter;
			trainers[i][j]->train(current_train_set,NULL);

		}
	}

	real accuracy(0);
	//testing on the validation set
	for(int t=0; t<10000; t++)
	{
		train_set->setExample(50000+t);
		int d=train_set->targets->frames[0][0];

		real votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;

		real first_max_value;
		long first_max_index;
	
		for(int i=0; i<=8; i++)
			for(int j=i+1; j<=9; j++)
			{
				nets[i][j]->forward(train_set->inputs);
				real out= nets[i][j]->outputs->frames[0][0]; 
				if(out>0)
					votes[i]++;
				else
					votes[j]++;


			}		
			
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
			accuracy++;
	}

	cout<<accuracy/100<<endl;




	delete(allocator);


}