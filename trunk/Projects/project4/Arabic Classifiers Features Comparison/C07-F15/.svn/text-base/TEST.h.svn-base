
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



void TEST()
{
	 
	Allocator *allocator = new Allocator;

	char test_file[]="C:/datasets/pca_all_concat_ourfeatures_latin_testing_set_full.txt";

	int N=50;
	int number_of_features=N*(N-1)/2+N;

	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(number_of_features,10);
	LogSoftMax *c2= new(allocator) LogSoftMax(10);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();

	//loading the model
	linear_net.load("model");


	DataSet *test_set=new(allocator) MatDataSet(test_file,N,1,false,-1,false);

	MemoryDataSet *expanded_test_set= new(allocator) MemoryDataSet();
	Sequence *inputs[10000];
	Sequence *targets[10000];
	for(int n=0; n<10000; n++)
	{
		test_set->setExample(n);
		inputs[n]= new(allocator) Sequence(1,number_of_features);
		targets[n]= new(allocator) Sequence(1,1);
		targets[n]->frames[0][0]= test_set->targets->frames[0][0];

		int counter=0;
		for(int i=0; i<N; i++)
		{
			for(int j=i; j<N; j++)
			{
				inputs[n]->frames[0][counter]= test_set->inputs->frames[0][i]*test_set->inputs->frames[0][j];
				counter++;

			}
		}
	}
	expanded_test_set->setInputs(inputs, 10000);
	expanded_test_set->setTargets(targets, 10000);
	

	real accuracy(0);
	ofstream fout("C07-F14_full_results.txt");
	for(int t = 0; t < expanded_test_set->n_examples; t++)
	{

		expanded_test_set->setExample(t);
		int d= expanded_test_set->targets->frames[0][0];//actual value of sample

		real decision_scores[10];// to hold decision scores

		linear_net.forward(expanded_test_set->inputs);//forwarding sample

		
		for(int i=0; i<10; i++)
			decision_scores[i] = linear_net.outputs->frames[0][i];

		real first_max_value;
		long first_max_index;

		getMax(decision_scores,10,first_max_value,first_max_index);
		//fout<<first_max_index<<endl;
		
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

	
	cout<<"Accuracy: "<<accuracy/100<<endl;
		
	delete(allocator);


}

