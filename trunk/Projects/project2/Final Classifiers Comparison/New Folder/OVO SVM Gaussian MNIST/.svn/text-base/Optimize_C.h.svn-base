
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


void Optimize_C()
{

	Allocator *allocator = new Allocator;

	int num_of_features=28*28;


	ofstream fout("C_optimzation_results_stage2.txt");

	char train_file[]="C:/datasets/mnist_training_set.txt";
	DataSet *dataset;
	dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);


	//int training_mask[50000];
	//for(int i=0; i<50000; i++)
	//	training_mask[i]=i;


	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	float g=0.02;
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


	float C_values[]={3.33, 5, 20, 30};
	for(int C_index=0; C_index<4; C_index++)
	{
		cout<<"C="<<C_values[C_index]<<endl;


		int counter;
		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{
				cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

				kernel = new(allocator) GaussianKernel(g);
				svms[i][j] = new(allocator) SVMClassification(kernel);
				svms[i][j]->setROption("C", C_values[C_index]);
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
				accuracy++;

		}

		cout<<accuracy/100<<endl;

		for(int i=0; i<=8; i++)
			for(int j=i+1; j<=9; j++)
				allocator->free(svms[i][j]);

		fout<<"C="<<C_values[C_index]<<" : "<<accuracy/100<<"%"<<endl;
	}



	delete(allocator);


}