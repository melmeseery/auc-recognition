
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
#include<fstream>
#include<conio.h>
#include<vector>
using namespace Torch;
#include<iostream>
#include<fstream>
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
	int num_of_features=945;

	Allocator *allocator = new Allocator;

	char test_file[]="C:/datasets/all_concat_ourfeatures_latin_testing_set_full.txt";

	DiskXFile model_file("model", "r");


	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



	// creating pairwise classifiers pointers
	float g=0.0005;//0.0275;//0.04;//gamma
	float C=100;
	Kernel *kernel = new(allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];

	//loading pairwise classifiers in turn
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			svms[i][j] = new(allocator) SVMClassification(kernel);

			//loading SVM models in turn
			svms[i][j]->loadXFile(&model_file);

		}
	}


	real accuracy(0);
	ofstream fout("C04-F05_full_results.txt");
	Timer timer;
	timer.stop();
	for(int t = 0; t < test_set->n_examples; t++)
	{
		if(t%100==0)
			cout<<t<<endl;

		real votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;

		test_set->setExample(t);
		int d= test_set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;


		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{
				timer.resume();
				svms[i][j]->forward(test_set->inputs);
				timer.stop();

				real decision = svms[i][j]->outputs->frames[0][0];

				if(decision>0)
					votes[i]=votes[i]+1;
				else
					votes[j]=votes[j]+1;

			}
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


	cout<<"Accuracy = "<<accuracy/100<<endl;



	delete(allocator);


}


