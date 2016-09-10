
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
#include<iostream>
#include<conio.h>
using namespace std;
using namespace Torch;


void TRAIN()
{
	 
	Allocator *allocator = new Allocator;

	int N=50;
	int number_of_features=N*(N-1)/2+N;

	//char train_file[]="C:/datasets/arabic_pca_reduced_local_concavity_ourfeatures_training_set_full.txt";
	char train_file[]="C:/datasets/zc_arabic_pca_reduced_local_concavity_ourfeatures_training_set_full.txt";


	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(number_of_features,10);
	LogSoftMax *c2= new(allocator) LogSoftMax(10);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();


	// reading training data from file and converting it to one hot class format
	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(i==j)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=0;
		}
	}
	DataSet *train_set=new(allocator) MatDataSet(train_file,N,1,false,-1,false);

	MemoryDataSet *expanded_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[60000];
	Sequence *targets[60000];
	for(int n=0; n<60000; n++)
	{
		train_set->setExample(n);
		inputs[n]= new(allocator) Sequence(1,number_of_features);
		targets[n]= new(allocator) Sequence(1,1);
		targets[n]->frames[0][0]= train_set->targets->frames[0][0];

		int counter=0;
		for(int i=0; i<N; i++)
		{
			for(int j=i; j<N; j++)
			{
				inputs[n]->frames[0][counter]= train_set->inputs->frames[0][i]*train_set->inputs->frames[0][j];
				counter++;

			}
		}
	}
	expanded_train_set->setInputs(inputs, 60000);
	expanded_train_set->setTargets(targets, 60000);

	allocator->free(train_set);

	DataSet *formatted_expanded_train_set= new(allocator) ClassFormatDataSet(expanded_train_set,class_labels);

	
	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);

	// creating the trainer
	StochasticGradient trainer(&linear_net, criterion);
	trainer.setIOption("max iter",100);

	// training...
	trainer.train(formatted_expanded_train_set, NULL);
	
	//saving model to file
	//linear_net.save("model");
	linear_net.save("zc_model");


	delete(allocator);


}


