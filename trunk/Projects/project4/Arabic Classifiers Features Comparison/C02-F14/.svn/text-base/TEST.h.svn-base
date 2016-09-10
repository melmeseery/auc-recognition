
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
#include "Timer.h"
#include<conio.h>
using namespace Torch;
#include<fstream>
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


void TEST()
{

	Allocator *allocator = new Allocator;

	//int num_of_features=200+1;
	//char test_file[]="D:/datasets/zc_arabic_local_chain_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");

	int num_of_features=200;
	char test_file[]="D:/datasets/arabic_local_chain_ourfeatures_testing_set_full.txt";
	DiskXFile model_file("model", "r");

	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



	// creating pairwise classifiers pointers
	ConnectedMachine *nets[10][10];
	Linear *lin_layer[10][10];
	Tanh *out_layer[10][10];


	//loading pairwise classifiers in turn
	int counter;
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{

			lin_layer[i][j]= new(allocator) Linear(num_of_features,1);
			out_layer[i][j]= new(allocator) Tanh(1);

			nets[i][j]= new(allocator) ConnectedMachine();
			nets[i][j]->addFCL(lin_layer[i][j]);
			nets[i][j]->addFCL(out_layer[i][j]);
			nets[i][j]->build();
			nets[i][j]->loadXFile(&model_file);
		}
	}
	


	real accuracy(0);
	float n_iter=100;
	Timer timer;
	for(int iter=0; iter<n_iter; iter++)
	{
		accuracy=0;
		for(int t = 0; t < test_set->n_examples; t++)
		{
			real votes[10];
			for(int i=0; i<10; i++)
				votes[i]=0;

			test_set->setExample(t);
			int d= test_set->targets->frames[0][0];

			real first_max_value;
			long first_max_index;

			for(int i=0; i<=8; i++)
				for(int j=i+1; j<=9; j++)
				{
					nets[i][j]->forward(test_set->inputs);
					real out= nets[i][j]->outputs->frames[0][0]; 
					if(out>0)
						votes[i]++;
					else
						votes[j]++;


				}



				// finding value and index of first maximum
				getMax(votes,10,first_max_value,first_max_index);

				if(first_max_index==d)
					accuracy++;


		}
	}
	float recog_time= timer.getRunTime()/n_iter;
	accuracy/=100;

	cout<<"Accuracy = "<<accuracy<<endl;
	cout<<"Recognition Time = "<<recog_time<<endl;

	ofstream fout("results.txt");
	fout<<"Accuracy = "<<accuracy<<endl;
	fout<<"Recognition Time = "<<recog_time<<endl;


	delete(allocator);


}


