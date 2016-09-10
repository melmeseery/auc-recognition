
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

	int num_of_features= 945;

	char train_file[]="C:/datasets/all_concat_ourfeatures_latin_training_set_full.txt";
	char test_file[]="C:/datasets/all_concat_ourfeatures_latin_testing_set_full.txt";


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
	ofstream fout("C05-F14_full_results.txt");
	ofstream fout_acc("accuracy");
	Timer timer;
	timer.stop();
	for(int t = 0; t < test_data->n_examples; t++)
	{

		if(t%100==0)
			cout<<t<<endl;

		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		float votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;
		
		// forwarding test sample
		timer.resume();
		knn.forward(test_data->inputs);
		timer.stop();

		int indices[k];
		real norm=0;
		for(int j=0; j<k; j++)
		{
			train_data->setExample(knn.indices[j]);
			votes[(int)train_data->targets->frames[0][0]]++;
		}

		real first_max_value;
		long first_max_index;

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

	cout<<"Accuracy: "<<accuracy/100<<endl;
	fout_acc<<accuracy/100<<endl;

	delete(allocator);


}

