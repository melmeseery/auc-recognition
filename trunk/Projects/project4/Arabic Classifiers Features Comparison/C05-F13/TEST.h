
#include "MatDataSet.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "MSECriterion.h"
#include "OneHotClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "NPTrainer.h"
#include "KFold.h"
#include "KNN.h"
#include "MeanVarNorm.h"
#include "CmdLine.h"
#include "Random.h"
#include "Timer.h"
using namespace Torch;
#include<fstream>
#include<conio.h>
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

	int num_of_features= 64;
	char train_file[]="D:/datasets/arabic_wavelet_raw_ourfeatures_training_set_full.txt";
	char test_file[]="D:/datasets/arabic_wavelet_raw_ourfeatures_testing_set_full.txt";

	//int num_of_features= 64+1;
	//char train_file[]="D:/datasets/zc_arabic_wavelet_raw_ourfeatures_training_set_full.txt";
	//char test_file[]="D:/datasets/zc_arabic_wavelet_raw_ourfeatures_testing_set_full.txt";

	// reading training and testing sets
	DataSet *train_data= new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
	DataSet *test_data= new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);

	// creating the knn machine
	const int k=3;
	KNN knn(10,k);

	// creating the trainer
	NPTrainer trainer(&knn);

	//training ...
	trainer.train(train_data, NULL);


	real accuracy(0);
	Timer timer;
	for(int t = 0; t < test_data->n_examples; t++)
	{

		//if(t%100==0)
		//	cout<<t<<endl;

		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		float votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;
		
		// forwarding test sample
		knn.forward(test_data->inputs);

		for(int j=0; j<k; j++)
		{
			train_data->setExample(knn.indices[j]);
			votes[(int)train_data->targets->frames[0][0]]++;
		}

		real first_max_value;
		long first_max_index;

		// finding value and index of first maximum
		getMax(votes,10,first_max_value,first_max_index);


		if(first_max_index==d)
			accuracy++;

	}

	float recog_time= timer.getRunTime();
	accuracy/=100;

	cout<<"Accuracy: "<<accuracy<<endl;
	cout<<"Recognition Time: "<<recog_time<<endl;

	ofstream fout("results.txt");
	fout<<"Accuracy: "<<accuracy<<endl;
	fout<<"Recognition Time: "<<recog_time<<endl;

	delete(allocator);


}

