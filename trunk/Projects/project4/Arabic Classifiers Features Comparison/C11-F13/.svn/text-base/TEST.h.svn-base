
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

	int num_of_features=64;
	char test_file[]="D:/datasets/arabic_wavelet_raw_ourfeatures_testing_set_full.txt";
	DiskXFile model_file("model", "r");

	//int num_of_features=64+1;
	//char test_file[]="D:/datasets/zc_arabic_wavelet_raw_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");

	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



	// creating pairwise classifiers pointers
	float s=1;//0.0275;//0.04;//gamma
	Kernel *kernel = new(allocator) DotKernel(s);
	SVMClassification *svms[10][10];

	Sequence Ws(45,num_of_features);
	float bs[45];

	//loading pairwise classifiers in turn
	int count(0);
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			svms[i][j] = new(allocator) SVMClassification(kernel);


			//loading SVM models in turn
			svms[i][j]->loadXFile(&model_file);

			bs[count]= svms[i][j]->b;

			for(int n=0; n<num_of_features; n++)
				Ws.frames[count][n]=0;

			for(int s=0; s<svms[i][j]->n_support_vectors; s++)
			{
				for(int n=0; n<num_of_features; n++)
					Ws.frames[count][n]+= (svms[i][j]->sv_alpha[s])*(svms[i][j]->sv_sequences[s]->frames[0][n]);
			}

			count++;


		}
	}


	real accuracy(0);
	float n_iter=10;
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


			count=-1;
			for(int i=0;i<=8;i++)
			{
				for(int j=i+1;j<=9;j++)
				{
					count++;
					real decision(bs[count]);
					for(int n=0; n<num_of_features; n++)
						decision+= (test_set->inputs->frames[0][n])*(Ws.frames[count][n]);

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


