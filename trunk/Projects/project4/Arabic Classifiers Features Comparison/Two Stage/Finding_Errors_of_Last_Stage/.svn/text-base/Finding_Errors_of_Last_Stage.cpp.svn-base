// Finding_Errors_of_Last_Stage.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

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
using namespace Torch;
#include<conio.h>
#include<iostream>
#include<fstream>
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



int _tmain(int argc, _TCHAR* argv[])
{
	Allocator *allocator = new Allocator;

	int num_of_features=201;

	ofstream fout("last_stage_validation_errors.txt");

	char train_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	DataSet *dataset;
	dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);


	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	Kernel *kernel;

	MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for(int n=0; n<20000; n++)
	{
		inputs[n]= new(allocator) Sequence(1,num_of_features);
		targets[n]= new(allocator) Sequence(1,1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);


	float g=0.1;
	float C=100;

	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			kernel = new(allocator) GaussianKernel(g);
			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C", C);
			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			counter=0;
			for(long k=0; k<50000; k++)
			{

				dataset->setExample(k);
				int d= dataset->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<num_of_features; f++)
					{
						current_train_set->inputs->frames[0][f]=
							dataset->inputs->frames[0][f];
					}

					if(d==i)
						current_train_set->targets->frames[0][0]= 1;
					else
						current_train_set->targets->frames[0][0]= -1;

					counter++;
				}

			}

			cout<<counter<<endl;
			current_train_set->n_examples= counter;
			current_train_set->setExample(0);
			trainers[i][j]->train(current_train_set,NULL);

		}
	}




	real accuracy(0);
	//testing on the validation set
	for(int t=50000; t<60000; t++)
	{
		//if(t%1000)
		//	cout<<t<<endl;

		dataset->setExample(t);
		int d= dataset->targets->frames[0][0];
		real decision[10];// to hold decision scores
		real votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;


		real first_max_value;
		long first_max_index;


		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{

				svms[i][j]->forward(dataset->inputs);

				real decision = svms[i][j]->outputs->frames[0][0];

				if(decision>0)
					votes[i]=votes[i]+1;
				else
					votes[j]=votes[j]+1;

			}
		}

		// finding value and index of first maximum
		getMax(votes,10,first_max_value,first_max_index);

		if(first_max_index==d)
		{fout<<0<<endl; accuracy++;}
		else
			fout<<1<<endl;

	}
	cout<<"Accuracy: "<<accuracy<<endl;


	delete(allocator);

	cout<<"Done."<<endl;
	getch();


	return 0;
}

