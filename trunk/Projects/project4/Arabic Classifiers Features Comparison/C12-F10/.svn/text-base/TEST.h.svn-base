
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
	//char test_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");

	const int num_of_features=200;
	char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";

	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);
	

	float Ws[45][num_of_features];
	float bs[num_of_features];
	ifstream w_fin("Ws.txt");
	ifstream b_fin("bs.txt");
	int count(-1);
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			count++;
			b_fin>>bs[count];
			for(int n=0; n<num_of_features; n++)
				w_fin>>Ws[count][n];

		}
	}
			
	

	real accuracy(0);
	ofstream fout("C02-F03_full_results.txt");
	Timer timer;
	timer.stop();
	for(int t = 0; t < test_set->n_examples; t++)
	{
		real votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;

		test_set->setExample(t);
		int d= test_set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;

		int count(-1);
		for(int i=0; i<=8; i++)
			for(int j=i+1; j<=9; j++)
			{
				count++;

				float out(bs[count]);
				for(int n=0; n<num_of_features; n++)
					out+=test_set->inputs->frames[0][n]*Ws[count][n];

				if(out>0)
					votes[i]++;
				else
					votes[j]++;


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

			//cout<<d<<"->"<<first_max_index<<endl;
			//getch();




	}



	cout<<"Accuracy = "<<accuracy/100<<endl;

	delete(allocator);


}


