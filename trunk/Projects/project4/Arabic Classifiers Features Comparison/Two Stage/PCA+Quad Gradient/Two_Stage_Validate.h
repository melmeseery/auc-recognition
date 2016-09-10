
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
#include<vector>
#include<algorithm>
using namespace Torch;
#include<conio.h>
#include<fstream>
#include<iostream>
using namespace std;

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



void Two_Stage_Validate()
{

	Allocator *allocator = new Allocator;

	int num_of_features= 201;
	const int N=50;

	int training_mask[50000];
	for(int i=0; i<50000; i++)
		training_mask[i]=i;
	int validation_mask[10000];
	for(int i=0; i<10000; i++)
		validation_mask[i]= 50000+i;


	char train_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";


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


	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(i==j)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=-1;
		}
	}
	DataSet *set;
	set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
	set->pushSubset(training_mask,50000);

	MemoryDataSet *qpca_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[50000];
	Sequence *targets[50000];
	for(int k=0; k<50000; k++)
	{
		inputs[k]= new(allocator) Sequence(1,N*(N-1)/2+N);
		targets[k]= new(allocator) Sequence(1,1);
	}
	qpca_train_set->setInputs(inputs, 50000);
	qpca_train_set->setTargets(targets, 50000);


	for(int k=0; k<qpca_train_set->n_examples; k++)
	{
		qpca_train_set->setExample(k);
		set->setExample(k);
		qpca_train_set->targets->frames[0][0]= set->targets->frames[0][0];

		Sequence pca_train_sample(1,N);
		for(int i=0; i<50; i++)
		{
			float sum(0);
			for(int n=0; n<num_of_features; n++)
			{ sum+= (set->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

			pca_train_sample.frames[0][i]=sum;
		}

		int c(0);
		for(int i=0; i<N; i++)
			for(int j=i; j<N; j++)
				qpca_train_set->inputs->frames[0][c++]= (pca_train_sample.frames[0][i])*(pca_train_sample.frames[0][j]);
		
	}


	DataSet *train_data= new(allocator) ClassFormatDataSet(qpca_train_set,class_labels);
	
	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) MSECriterion(10);


	// creating the trainer
	StochasticGradient trainer(&linear_net, criterion);
	trainer.setIOption("max iter",100);

	// training...
	trainer.train(train_data, NULL);
	
	allocator->free(qpca_train_set);

	set->popSubset();


	set->pushSubset(validation_mask,10000);

	vector<index_score_pair> score_pairs(10000);

	int last_stage_validation_errors[10000];
	ifstream fin("last_stage_validation_errors.txt");
	if(fin.fail())
	{cout<<"Cannot Open file last_stage_validation_errors.txt"<<endl; getch();}
	for(int i=0; i<10000; i++)
		fin>>last_stage_validation_errors[i];


	int top_candidates_errors[10];
	for(int i=0; i<10; i++)
		top_candidates_errors[i]=0;

	float acc(0);
	for(int t = 0; t < set->n_examples; t++)
	{

		set->setExample(t);
		int d= set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;

		Sequence quad_test_sample(1,N*(N-1)/2+N);
		Sequence pca_test_sample(1,N);
		for(int i=0; i<N; i++)
		{
			float sum(0);
			for(int n=0; n<num_of_features; n++)
			{ sum+= (set->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

			pca_test_sample.frames[0][i]=sum;
		}

		int c(0);
		for(int i=0; i<N; i++)
			for(int j=i; j<N; j++)
				quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);

		float decisions[10];

		linear_net.forward(&quad_test_sample);//forwarding sample

		for(int i=0; i<10; i++)
			decisions[i] = (1+linear_net.outputs->frames[0][i])/2;


		getMax(decisions,10,first_max_value,first_max_index);
		if(first_max_index==d)
			acc++;
		score_pairs[t].index=t;
		score_pairs[t].score=first_max_value;

		if(first_max_index!=d && last_stage_validation_errors[t]==0)
			score_pairs[t].err=1;

		vector<index_score_pair> candidates(10);
		for(int i=0; i<10; i++)
		{
			candidates[i].index=i;
			candidates[i].score=decisions[i];
			if(i!=d && last_stage_validation_errors[t]==0)
				candidates[i].err=1;
			else
				candidates[i].err=0;
		}
		sort(candidates.begin(), candidates.end(), compare_index_score_pair());
		for(int i=0; i<10; i++)
		{
			bool correct_classification(false);
			for(int j=i; j>=0; j--)
			{
				if(candidates[j].err==0)
				{correct_classification=true; break;}
			}
			if(!correct_classification)
				top_candidates_errors[i]++;
		}

	}

	sort(score_pairs.begin(), score_pairs.end(), compare_index_score_pair());

	ofstream fout("validation_results.txt");

	int e_max=1;
	int e(0);
	float selected_threlshold(0);
	float rejection_rate(0);
	for(int i=0; i<10000; i++)
	{
		if(score_pairs[i].err==1)
			e++;
		if(e==e_max)
		{selected_threlshold= score_pairs[i].score; rejection_rate=10000-i; break;}
	}

	fout<<"Selected Threshold: "<<selected_threlshold<<", Corresponding Rejection Rate="<<rejection_rate<<endl;
	cout<<"Selected Threshold: "<<selected_threlshold<<", Corresponding Rejection Rate="<<rejection_rate<<endl;

	fout<<"Top Candidates Error: "<<endl;
	cout<<"Top Candidates Error: "<<endl;
	for(int i=0; i<10; i++)
	{
		fout<<"top "<<i+1<<" : "<<top_candidates_errors[i]<<endl;
		cout<<"top "<<i+1<<" : "<<top_candidates_errors[i]<<endl;
	}
	cout<<"Accuracy: "<<acc/10000<<endl;



	set->popSubset();


	fin_mat.close();
	fin_mu.close();

	delete(allocator);


}