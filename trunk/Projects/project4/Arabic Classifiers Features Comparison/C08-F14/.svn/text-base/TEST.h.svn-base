
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

	int num_of_features=200;
	char test_file[]="D:/datasets/arabic_local_chain_ourfeatures_testing_set_full.txt";

	const int N=60;

	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);


	char pca_matrix_file[]="D:/datasets/arabic_local_chain_pca_matrix_for_GC.txt";
	char pca_mu_file[]="D:/datasets/arabic_local_chain_pca_mu_for_GC.txt";
	ifstream fin_mat(pca_matrix_file);
	if(fin_mat.fail()){cout<<"Cannot open file "<<pca_matrix_file; getch();}
	ifstream fin_mu(pca_mu_file);
	if(fin_mu.fail()){cout<<"Cannot open file "<<pca_mu_file; getch();};


	Sequence PCA_matrix(num_of_features,N);
	for(int n=0; n<num_of_features; n++)
		for(int i=0; i<N; i++)
		{float r; fin_mat>>r; PCA_matrix.frames[n][i]=r;}


		Sequence PCA_mu(1,num_of_features);
		for(int n=0; n<num_of_features; n++)
		{float r; fin_mu>>r; PCA_mu.frames[0][n]=r;}


		float log_det_SIGMA[10];
		float inv_SIGMA[10][N][N];
		float MU[10][N];
		ifstream log_det_sigma_fin("log_det_SIGMA.txt");
		if(log_det_sigma_fin.fail())
		{cout<<"Cannot open file: log_det_SIGMA.txt"<<endl; getch();}
		ifstream inv_sigma_fin("inv_SIGMA.txt");
		if(inv_sigma_fin.fail())
		{cout<<"Cannot open file: inv_SIGMA.txt"<<endl; getch();}
		ifstream mu_fin("MU.txt");
		if(mu_fin.fail())
		{cout<<"Cannot open file: MU.txt"<<endl; getch();}

		for(int i=0; i<10; i++)
		{
			log_det_sigma_fin>>log_det_SIGMA[i];

			for(int n=0; n<N; n++)
			{
				mu_fin>>MU[i][n];

				for(int m=0; m<N; m++)
					inv_sigma_fin>>inv_SIGMA[i][n][m];
			}
		}



		real accuracy(0);
		Timer timer;
		accuracy=0;
		for(int t = 0; t < test_set->n_examples; t++)
		{

			test_set->setExample(t);
			int d= test_set->targets->frames[0][0];

			Sequence pca_test_sample(1,N);
			for(int i=0; i<N; i++)
			{
				float sum(0);
				for(int n=0; n<num_of_features; n++)
				{ sum+= (test_set->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

				pca_test_sample.frames[0][i]=sum;
			}


			real first_max_value;
			long first_max_index;
			float decision_scores[10];
			for(int i=0; i<10; i++)
				decision_scores[i]=log_det_SIGMA[i];

			float x_minus_mu[10][N];
			for(int i=0; i<10; i++)
				for(int n=0; n<N; n++)
					x_minus_mu[i][n]=pca_test_sample.frames[0][n]-MU[i][n];

			for(int i=0; i<10; i++)
			{
				for(int n=0; n<N; n++)
				{
					float temp(0);
					for(int m=0; m<N; m++)
						temp+= (x_minus_mu[i][m]*inv_SIGMA[i][n][m]);

					decision_scores[i]+= temp*x_minus_mu[i][n];

				}
			}

			// finding value and index of first maximum
			getMax(decision_scores,10,first_max_value,first_max_index);


			if(first_max_index==d)
				accuracy++;

		}
		float recog_time= timer.getRunTime();
		accuracy/=100;

		cout<<"Accuracy = "<<accuracy<<endl;
		cout<<"Recognition Time = "<<recog_time<<endl;

		ofstream fout("results.txt");
		fout<<"Accuracy = "<<accuracy<<endl;
		fout<<"Recognition Time = "<<recog_time<<endl;


		delete(allocator);


}


