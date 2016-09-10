
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
using namespace std;

void TRAIN()
{
	 
	Allocator *allocator = new Allocator;

	//int num_of_features= 225;
	//char train_file[]="C:/datasets/arabic_reduced_local_concavity_ourfeatures_training_set_full.txt";
	//DiskXFile model_file("model", "w");

	int num_of_features= 225+1;
	char train_file[]="D:/datasets/zc_arabic_reduced_local_concavity_ourfeatures_training_set_full.txt";
	DiskXFile model_file("zc_model", "w");

	ConnectedMachine *nets[10][10];
	Linear *lin_layers[10][10];
	Tanh *out_layers[10][10];
	StochasticGradient *trainers[10][10];

	DataSet *train_set;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

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

	OneHotClassFormat class_format(1);
	Criterion *criterion = new(allocator) MSECriterion(1);

	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;
	

			lin_layers[i][j]= new(allocator) Linear(num_of_features,1);
			out_layers[i][j]= new(allocator) Tanh(1);
			nets[i][j]= new(allocator) ConnectedMachine();
			nets[i][j]->addFCL(lin_layers[i][j]);
			nets[i][j]->addFCL(out_layers[i][j]);
			nets[i][j]->build();
			trainers[i][j]= new(allocator) StochasticGradient(nets[i][j], criterion);
			trainers[i][j]->setIOption("max iter",100);

			counter=0;
			for(long k=0; k<60000; k++)
			{

				train_set->setExample(k);
				int d= train_set->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<num_of_features; f++)
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
			nets[i][j]->saveXFile(&model_file);


		}
	}


	delete(allocator);


}