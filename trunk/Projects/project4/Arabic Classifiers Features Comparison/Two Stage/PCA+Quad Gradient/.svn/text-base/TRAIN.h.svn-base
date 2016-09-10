
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



void TRAIN()
{

	Allocator *allocator = new Allocator;

	int num_of_features= 201;
	const int N=50;

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
	DataSet *train_set;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	MemoryDataSet *qpca_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[60000];
	Sequence *targets[60000];
	for(int k=0; k<60000; k++)
	{
		inputs[k]= new(allocator) Sequence(1,N*(N-1)/2+N);
		targets[k]= new(allocator) Sequence(1,1);
	}
	qpca_train_set->setInputs(inputs, 60000);
	qpca_train_set->setTargets(targets, 60000);


	for(int k=0; k<qpca_train_set->n_examples; k++)
	{
		qpca_train_set->setExample(k);
		train_set->setExample(k);
		qpca_train_set->targets->frames[0][0]= train_set->targets->frames[0][0];

		Sequence pca_train_sample(1,N);
		for(int i=0; i<50; i++)
		{
			float sum(0);
			for(int n=0; n<num_of_features; n++)
			{ sum+= (train_set->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

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

	linear_net.save("zc_qpca_model");
	
	fin_mat.close();
	fin_mu.close();

	delete(allocator);


}