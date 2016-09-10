

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
#include<algorithm>
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

class index_score_pair
{
public:
	int index;
	float score;
	float err;
	index_score_pair(){index=0; score=0;}
	index_score_pair(int n, float s){index=n; score=s; err=0;}
};
class compare_index_score_pair
{
public:
	bool operator()(const index_score_pair& obj1, const index_score_pair& obj2)
	{return(obj1.score>obj2.score);} //descending order
};



void Two_Stage_Test()
{	
	int num_of_features=201;
	const int N=50;

	Allocator *allocator = new Allocator;

	char test_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";

	DiskXFile svm_model_file("zc_svm_model", "r");
	DiskXFile qpca_model_file("zc_qpca_model", "r");

	char matrix_file[]="D:/datasets/zc_arabic_gmask_grad_proj_ourfeatures_PCAvec.txt";
	char mu_file[]="D:/datasets/zc_arabic_gmask_grad_proj_ourfeatures_PCmu.txt";
	ifstream fin_mat(matrix_file);
	if(fin_mat.fail()){cout<<"Cannot open file "<<matrix_file; getch();}
	ifstream fin_mu(mu_file);
	if(fin_mu.fail()){cout<<"Cannot open file "<<mu_file; getch();};

	Sequence PCA_matrix(num_of_features,N);
	for(int n=0; n<num_of_features; n++)
		for(int i=0; i<N; i++)
			{float r; fin_mat>>r; PCA_matrix.frames[n][i]=r;}

	
	Sequence PCA_mu(1,num_of_features);
	for(int n=0; n<num_of_features; n++)
	{float r; fin_mu>>r; PCA_mu.frames[0][n]=r;}

	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(N*(N-1)/2+N,10);
	Tanh *c2= new(allocator) Tanh(10);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();
	linear_net.loadXFile(&qpca_model_file);
	
	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);


	// creating pairwise classifiers pointers
	float g=0.1;
	Kernel *kernel = new(allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];

	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->loadXFile(&svm_model_file);

		}
	}

	ofstream fout("overall_system_results.txt");

	fout<<"Using k-top:"<<endl;
	cout<<"Using k-top:"<<endl;
	float accuracy(0);
	float thresh=0.955687;
	int K=6;
	vector<index_score_pair> decisions(10);
	Timer timer;
	for(int t = 0; t < test_set->n_examples; t++)
	{
		//if(t%100==0)
		//	cout<<t<<endl;
		//cout<<t<<endl;

		test_set->setExample(t);
		int d= test_set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;

		Sequence quad_test_sample(1,N*(N-1)/2+N);
		Sequence pca_test_sample(1,N);
		for(int i=0; i<N; i++)
		{
			float sum(0);
			for(int n=0; n<num_of_features; n++)
			{ sum+= (test_set->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

			pca_test_sample.frames[0][i]=sum;
		}

		int c(0);
		for(int i=0; i<N; i++)
			for(int j=i; j<N; j++)
				quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);


		linear_net.forward(&quad_test_sample);//forwarding sample

		for(int i=0; i<10; i++)
		{decisions[i].score = (1+linear_net.outputs->frames[0][i])/2; decisions[i].index=i;}


		sort(decisions.begin(), decisions.end(), compare_index_score_pair());

		if(decisions[0].score>thresh)
		{if(decisions[0].index==d)accuracy++;}
		else
		{
			float votes[10];
			for(int i=0; i<10; i++)
				votes[i]=0;


			real first_max_value;
			long first_max_index;

			for(int i=0;i<K-1;i++)
			{
				for(int j=i+1;j<K;j++)
				{
					int a,b;
					if(decisions[i].index<decisions[j].index)
					{a=decisions[i].index; b=decisions[j].index;}
					else
					{b=decisions[i].index; a=decisions[j].index;}


					svms[a][b]->forward(test_set->inputs);

					real out = svms[a][b]->outputs->frames[0][0];

					if(out>0)
						votes[a]=votes[a]+1;
					else
						votes[b]=votes[b]+1;

				}
			}

			// finding value and index of first maximum
			getMax(votes,10,first_max_value,first_max_index);
			if(first_max_index==d)
				accuracy++;

		}

		

	}
	float elapsed_time= timer.getRunTime();


	cout<<"Accuracy = "<<accuracy/100<<endl;
	cout<<"Recognition Time = "<<elapsed_time<<endl;

	fout<<"Accuracy = "<<accuracy/100<<endl;
	fout<<"Recognition Time = "<<elapsed_time<<endl;




	fout<<endl<<endl<<"Without Using k-top:"<<endl;
	cout<<endl<<endl<<"Without Using k-top:"<<endl;
	accuracy=0;
	K=10;
	timer.reset();
	for(int t = 0; t < test_set->n_examples; t++)
	{
		//if(t%100==0)
		//	cout<<t<<endl;
		//cout<<t<<endl;

		test_set->setExample(t);
		int d= test_set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;

		Sequence quad_test_sample(1,N*(N-1)/2+N);
		Sequence pca_test_sample(1,N);
		for(int i=0; i<N; i++)
		{
			float sum(0);
			for(int n=0; n<num_of_features; n++)
			{ sum+= (test_set->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

			pca_test_sample.frames[0][i]=sum;
		}

		int c(0);
		for(int i=0; i<N; i++)
			for(int j=i; j<N; j++)
				quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);


		linear_net.forward(&quad_test_sample);//forwarding sample

		for(int i=0; i<10; i++)
		{decisions[i].score = (1+linear_net.outputs->frames[0][i])/2; decisions[i].index=i;}


		sort(decisions.begin(), decisions.end(), compare_index_score_pair());

		if(decisions[0].score>thresh)
		{if(decisions[0].index==d)accuracy++;}
		else
		{
			float votes[10];
			for(int i=0; i<10; i++)
				votes[i]=0;


			real first_max_value;
			long first_max_index;

			for(int i=0;i<K-1;i++)
			{
				for(int j=i+1;j<K;j++)
				{
					int a,b;
					if(decisions[i].index<decisions[j].index)
					{a=decisions[i].index; b=decisions[j].index;}
					else
					{b=decisions[i].index; a=decisions[j].index;}


					svms[a][b]->forward(test_set->inputs);

					real out = svms[a][b]->outputs->frames[0][0];

					if(out>0)
						votes[a]=votes[a]+1;
					else
						votes[b]=votes[b]+1;

				}
			}

			// finding value and index of first maximum
			getMax(votes,10,first_max_value,first_max_index);
			if(first_max_index==d)
				accuracy++;

		}

		

	}
	elapsed_time= timer.getRunTime();


	cout<<"Accuracy = "<<accuracy/100<<endl;
	cout<<"Recognition Time = "<<elapsed_time<<endl;

	fout<<"Accuracy = "<<accuracy/100<<endl;
	fout<<"Recognition Time = "<<elapsed_time<<endl;


	delete(allocator);

}