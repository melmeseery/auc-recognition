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

	int N=50;
	int number_of_features=N*(N-1)/2+N;

	ofstream fout("C07-F12_ensemble_validation_results.txt");


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

	char train_file[]="C:/datasets/arabic_pca_low_dim_concat_ourfeatures_training_set_full.txt";
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

	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);

	DataSet *formatted_expanded_train_set= new(allocator) ClassFormatDataSet(expanded_train_set,class_labels);

	int training_mask[50000];
	for(int i=0; i<50000; i++)
		training_mask[i]=i;


	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(number_of_features,10);
	LogSoftMax *c2= new(allocator) LogSoftMax(10);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();


	// creating the trainer
	StochasticGradient trainer(&linear_net, criterion);
	trainer.setIOption("max iter",100);


	// training...
	formatted_expanded_train_set->pushSubset(training_mask,50000);
	trainer.train(formatted_expanded_train_set, NULL);
	formatted_expanded_train_set->popSubset();

	real accuracy(0);
	//testing on the validation set
	for(int t=0; t<10000; t++)
	{
		expanded_train_set->setExample(50000+t);
		int d=expanded_train_set->targets->frames[0][0];

		real decision_scores[10];// to hold decision scores

		linear_net.forward(expanded_train_set->inputs);//forwarding sample

		for(int i=0; i<10; i++)
			decision_scores[i] = linear_net.outputs->frames[0][i];

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
			accuracy++;
	}

	cout<<accuracy/100<<endl;




	delete(allocator);


}

