
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
#include<conio.h>
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

	int N=50;

	char test_file[]="D:/datasets/arabic_wavelet_raw_ourfeatures_testing_set_full.txt";
	int number_of_features=64;
	char matrix_file[]="D:/datasets/arabic_wavelet_raw_pca_matrix.txt";
	char mu_file[]="D:/datasets/arabic_wavelet_raw_pca_mu.txt";


	//char test_file[]="D:/datasets/zc_arabic_wavelet_raw_ourfeatures_testing_set_full.txt";
	//int number_of_features=64;
	//char matrix_file[]="D:/datasets/zc_arabic_wavelet_raw_pca_matrix.txt";
	//char mu_file[]="D:/datasets/zc_arabic_wavelet_raw_pca_mu.txt";


	ifstream fin_mat(matrix_file);
	if(fin_mat.fail()){cout<<"Cannot open file "<<matrix_file; getch();}
	ifstream fin_mu(mu_file);
	if(fin_mu.fail()){cout<<"Cannot open file "<<mu_file; getch();};


	Sequence PCA_matrix(number_of_features,N);
	for(int n=0; n<number_of_features; n++)
		for(int i=0; i<N; i++)
		{float r; fin_mat>>r; PCA_matrix.frames[n][i]=r;}


		Sequence PCA_mu(1,number_of_features);
		for(int n=0; n<number_of_features; n++)
		{float r; fin_mu>>r; PCA_mu.frames[0][n]=r;}



		// creating the linear machine
		ConnectedMachine linear_net;
		Linear *c1= new(allocator) Linear(N*(N-1)/2+N,10);
		LogSoftMax *c2= new(allocator) LogSoftMax(10);
		linear_net.addFCL(c1);
		linear_net.addFCL(c2);
		linear_net.build();
		linear_net.setPartialBackprop();

		//loading the model
		linear_net.load("model");
		//linear_net.load("zc_model");


		DataSet *test_set=new(allocator) MatDataSet(test_file,number_of_features,1,false,-1,false);


		real accuracy(0);
		float n_iter=10;
		Timer timer;
		for(int iter=0; iter<n_iter; iter++)
		{
			accuracy=0;
			for(int t = 0; t < test_set->n_examples; t++)
			{

				test_set->setExample(t);
				int d= test_set->targets->frames[0][0];//actual value of sample


				Sequence quad_test_sample(1,N*(N-1)/2+N);
				Sequence pca_test_sample(1,N);
				for(int i=0; i<N; i++)
				{
					float sum(0);
					for(int n=0; n<number_of_features; n++)
					{ sum+= (test_set->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

					pca_test_sample.frames[0][i]=sum;
				}

				int c(0);
				for(int i=0; i<N; i++)
					for(int j=i; j<N; j++)
						quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);



				real decision_scores[10];// to hold decision scores

				linear_net.forward(&quad_test_sample);//forwarding sample
				for(int i=0; i<10; i++)
					decision_scores[i] = linear_net.outputs->frames[0][i];


				real first_max_value;
				long first_max_index;

				getMax(decision_scores,10,first_max_value,first_max_index);


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
