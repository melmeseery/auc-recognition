
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

	int num_of_features=200;
	char test_file[]="D:/datasets/arabic_local_chain_ourfeatures_testing_set_full.txt";
	DiskXFile model_file("model", "r");

	//int num_of_features=200+1;
	//char test_file[]="D:/datasets/zc_arabic_local_chain_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");


	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);

	ifstream sv_fin("support_vectors.txt");
	//ifstream sv_fin("zc_support_vectors.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors.txt"; getch();}
	int n;
	sv_fin>>n;
	vector<Sequence*> SupportVectors(n);
	for(int n=0; n<SupportVectors.size(); n++)
	{
		SupportVectors[n]= new Sequence(1,num_of_features);
		for(int f=0; f<num_of_features; f++)
		{sv_fin>>(SupportVectors[n]->frames[0][f]); /*cout<<(*SupportVectors[n])[f]<<endl; getch();*/}
	}


	ifstream sv_dist_fin("support_vectors_distribution.txt");
	//ifstream sv_dist_fin("zc_support_vectors_distribution.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors_distribution.txt"; getch();}
	sv_dist_fin>>n;
	vector<int> SupportVectorsDist(n);
	for(int n=0; n<SupportVectorsDist.size(); n++)
		sv_dist_fin>>SupportVectorsDist[n]; 


	// creating pairwise classifiers pointers
	float g=0.1;//0.0275;//0.04;//gamma
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
	real confusion_matrix[10][10];
	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++)
			confusion_matrix[i][j]=0;

	ofstream conf_fout("confusion_matrix.txt");
	//ofstream conf_fout("zc_confusion_matrix.txt");
	vector<float> KernelEvaluations(SupportVectors.size());
	Timer timer;
	for(int t = 0; t < test_set->n_examples; t++)
	{
		//if(t%100==0)
		//	cout<<t<<endl;


		real votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;

		test_set->setExample(t);
		int d= test_set->targets->frames[0][0];
				for(int n=0; n<SupportVectors.size(); n++)
		KernelEvaluations[n]=kernel->eval(test_set->inputs,SupportVectors[n]);


		real first_max_value;
		long first_max_index;


		int count(0);
		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{
				float decision=svms[i][j]->b;
				for(int n=0; n<svms[i][j]->n_support_vectors; n++)
					decision += KernelEvaluations[SupportVectorsDist[count++]]*svms[i][j]->sv_alpha[n];

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

		confusion_matrix[d][first_max_index]++;

	}
	float recog_time= timer.getRunTime();
	accuracy/=100;


	cout<<"Accuracy = "<<accuracy<<endl;
	cout<<"Recognition Time = "<<recog_time<<endl;

	ofstream fout("results.txt");
	fout<<"Accuracy = "<<accuracy<<endl;
	fout<<"Recognition Time = "<<recog_time<<endl;

	for(int i=0; i<10; i++)
	{
		for(int j=0; j<10; j++)
			conf_fout<<confusion_matrix[i][j]<<"  ";
		conf_fout<<endl<<endl;
	}



	delete(allocator);


}


