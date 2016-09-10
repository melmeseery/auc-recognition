
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
#include<iostream>
#include <fstream>
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

	int number_of_features=64;

	ofstream fout("C11-F13_ensemble_validation_results.txt");


	char train_file[]="C:/datasets/arabic_wavelet_raw_ourfeatures_training_set_full.txt";
	
	DataSet *train_set=new(allocator) MatDataSet(train_file,number_of_features,1,false,-1,false);

	int training_mask[50000];
	for(int i=0; i<50000; i++)
		training_mask[i]=i;
	
	train_set->pushSubset(training_mask,50000);


	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	float s=1;//0.0275;//0.04;//gamma
	float C=0.1;
	Kernel *kernel = new(allocator) DotKernel(s);
	
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


	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C", C);
			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			counter=0;
			for(long k=0; k<train_set->n_examples; k++)
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


	train_set->popSubset();

	real accuracy(0);
	//testing on the validation set
	for(int t=0; t<10000; t++)
	{
		train_set->setExample(50000+t);
		int d=train_set->targets->frames[0][0];
		
		if(t%100==0)
			cout<<t<<endl;

		real votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;

		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{

				svms[i][j]->forward(train_set->inputs);

				real decision = svms[i][j]->outputs->frames[0][0];

				if(decision>0)
					votes[i]=votes[i]+1;
				else
					votes[j]=votes[j]+1;

			}
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
			accuracy++;
	}

	cout<<accuracy/100<<endl;




	delete(allocator);


}