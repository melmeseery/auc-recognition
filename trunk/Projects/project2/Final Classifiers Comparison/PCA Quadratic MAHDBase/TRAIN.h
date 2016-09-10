
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
#include<conio.h>
#include<fstream>
#include<iostream>
using namespace std;
using namespace Torch;

void TRAIN()
{
	 
	Allocator *allocator = new Allocator;
	
	char train_file[]="D:/datasets/mahdbase_training_set.txt";
	
	char matrix_file[]="D:/datasets/mahdbase_pca_matrix.txt";
	char mu_file[]="D:/datasets/mahdbase_pca_mu.txt";
	ifstream fin_mat(matrix_file);
	if(fin_mat.fail()){cout<<"Cannot open file "<<matrix_file; getch();}
	ifstream fin_mu(mu_file);
	if(fin_mu.fail()){cout<<"Cannot open file "<<mu_file; getch();};
	

	Sequence PCA_matrix(28*28,40);
	for(int n=0; n<28*28; n++)
		for(int i=0; i<40; i++)
			{float r; fin_mat>>r; PCA_matrix.frames[n][i]=r;}

	
	Sequence PCA_mu(1,28*28);
	for(int n=0; n<28*28; n++)
	{float r; fin_mu>>r; PCA_mu.frames[0][n]=r;}

	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(820,10);
	//LogSoftMax *c2= new(allocator) LogSoftMax(10);
	Tanh *c2= new(allocator) Tanh(10);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();


	// reading training data from file and converting it to one hot class format
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
	DataSet *temp1;
	temp1=new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);

	MemoryDataSet *qpca_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[60000];
	Sequence *targets[60000];
	for(int k=0; k<60000; k++)
	{
		inputs[k]= new(allocator) Sequence(1,820);
		targets[k]= new(allocator) Sequence(1,1);
	}
	qpca_train_set->setInputs(inputs, 60000);
	qpca_train_set->setTargets(targets, 60000);

	for(int k=0; k<qpca_train_set->n_examples; k++)
	{
		qpca_train_set->setExample(k);
		temp1->setExample(k);
		qpca_train_set->targets->frames[0][0]= temp1->targets->frames[0][0];

		Sequence pca_train_sample(1,40);
		for(int i=0; i<40; i++)
		{
			float sum(0);
			for(int n=0; n<28*28; n++)
			{ sum+= (temp1->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

			pca_train_sample.frames[0][i]=sum;
		}

		int c(0);
		for(int i=0; i<40; i++)
			for(int j=i; j<40; j++)
				qpca_train_set->inputs->frames[0][c++]= (pca_train_sample.frames[0][i])*(pca_train_sample.frames[0][j]);

		
	}

	allocator->free(temp1);


	DataSet *train_data= new(allocator) ClassFormatDataSet(qpca_train_set,class_labels);


	
	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	//Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);
	Criterion *criterion = new(allocator) MSECriterion(10);


	// creating the trainer
	StochasticGradient trainer(&linear_net, criterion);
	trainer.setIOption("max iter",100);

	// training...
	trainer.train(train_data, NULL);
	
	//saving model to file
	linear_net.save("model");

	fin_mat.close();
	fin_mu.close();


	delete(allocator);


}