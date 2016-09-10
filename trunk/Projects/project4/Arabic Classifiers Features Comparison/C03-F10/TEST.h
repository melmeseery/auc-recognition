
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
#include "Timer.h"
#include<iostream>
#include<fstream>
#include<vector>
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

	int num_of_features=225;
	char test_file[]="D:/datasets/arabic_reduced_local_concavity_ourfeatures_testing_set_full.txt";

	//int num_of_features=100+1;
	//char test_file[]="D:/datasets/zc_arabic_reduced_local_concavity_ourfeatures_testing_set_full.txt";

	const int n_hu=100;
	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(num_of_features,n_hu);
	Tanh *c2= new(allocator) Tanh(n_hu);
	Linear *c3= new(allocator) Linear(n_hu,10);
	LogSoftMax *c4= new(allocator) LogSoftMax(10);
	mlp.addFCL(c1);
	mlp.addFCL(c2);
	mlp.addFCL(c3);
	mlp.addFCL(c4);
	mlp.build();
	mlp.setPartialBackprop();

	//loading the model
	mlp.load("model");

	//mlp.load("zc_model");


	// reading testing data from file and converting it to one hot class format
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

	DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);




	float accuracy(0);
	float n_iter=10;
	Timer timer;
	for(int iter=0; iter<n_iter; iter++)
	{
		accuracy=0;
		for(int t = 0; t < test_data->n_examples; t++)
		{

			test_data->setExample(t);
			int d= test_data->targets->frames[0][0];//actual value of sample

			real decision_scores[10];// to hold decision scores

			mlp.forward(test_data->inputs);//forwarding sample

			// de-logging the decisions
			for(int i=0; i<10; i++)
				decision_scores[i] = exp(mlp.outputs->frames[0][i]);

			real first_max_value;
			long first_max_index;


			getMax(decision_scores,10,first_max_value,first_max_index);
			//fout<<first_max_index<<endl;


			if(first_max_index==d)
				accuracy++;

		}
	}
	float recog_time= timer.getRunTime()/n_iter;
	accuracy/=100;


	cout<<"Accuracy: "<<accuracy<<endl;
	cout<<"Recognition Time: "<<recog_time<<endl;

	ofstream fout("results.txt");
	fout<<"Accuracy: "<<accuracy<<endl;
	fout<<"Recognition Time: "<<recog_time<<endl;

	delete(allocator);


}