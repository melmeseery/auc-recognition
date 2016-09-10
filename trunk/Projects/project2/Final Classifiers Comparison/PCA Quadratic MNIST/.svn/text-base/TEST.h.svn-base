
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
#include<iostream>
#include<fstream>
//#include<conio.h>
#include<Timer.h>
#include<time.h>



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

	char test_file[]="D:/datasets/mnist_testing_set.txt";

	char matrix_file[]="D:/datasets/mnist_pca_matrix.txt";
	char mu_file[]="D:/datasets/mnist_pca_mu.txt";
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

	//loading the model
	linear_net.load("model");


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
	
	DataSet *test_data =new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);

	
	float actual_accuracy(0);
	const int Nt=100000;
	float n_rejected[Nt]; // number of rejected samples
	float correct[Nt];
	for(int t=0; t<Nt; t++)
	{
		correct[t]=0;
		n_rejected[t]=0;
	}

	ofstream fout("results.txt");

	for(int k = 0; k < test_data->n_examples; k++)
	{

		test_data->setExample(k);
		int d= test_data->targets->frames[0][0];//actual value of sample


		Sequence quad_test_sample(1,820);
		Sequence pca_test_sample(1,40);
		for(int i=0; i<40; i++)
		{
			float sum(0);
			for(int n=0; n<28*28; n++)
			{ sum+= (test_data->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

			pca_test_sample.frames[0][i]=sum;
		}

		int c(0);
		for(int i=0; i<40; i++)
			for(int j=i; j<40; j++)
				quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);
		


		real decision_scores[10];// to hold decision scores

		linear_net.forward(&quad_test_sample);//forwarding sample

		for(int i=0; i<10; i++)
			//decision_scores[i] = exp(linear_net.outputs->frames[0][i]);
			decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;

		real first_max_value;
		long first_max_index;
		real second_max_value;
		long second_max_index;

		// finding value and index of first and second maxima
		getMax(decision_scores,10,first_max_value,first_max_index);
		decision_scores[first_max_index]=-99;
		getMax(decision_scores,10,second_max_value,second_max_index);
		
		if(first_max_index==d)
			actual_accuracy++;


		// rejecting patterns that violates thresholding condittions
		for(int t=0;t<Nt; t++)
		{
			float th= ((float)(t))/((float) Nt);
			if((first_max_value-second_max_value)<th)
				n_rejected[t]++;
			else if(first_max_index==d)
				correct[t]++;
		}

		

	}

	//calculating time
	Timer timer;
	for(int k = 0; k < test_data->n_examples; k++)
	{

		test_data->setExample(k);
		int d= test_data->targets->frames[0][0];//actual value of sample


		Sequence quad_test_sample(1,820);
		Sequence pca_test_sample(1,40);
		for(int i=0; i<40; i++)
		{
			float sum(0);
			for(int n=0; n<28*28; n++)
			{ sum+= (test_data->inputs->frames[0][n]-PCA_mu.frames[0][n])*PCA_matrix.frames[n][i];}

			pca_test_sample.frames[0][i]=sum;
		}

		int c(0);
		for(int i=0; i<40; i++)
			for(int j=i; j<40; j++)
				quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);
		


		real decision_scores[10];// to hold decision scores

		linear_net.forward(&quad_test_sample);//forwarding sample

		for(int i=0; i<10; i++)
			//decision_scores[i] = exp(linear_net.outputs->frames[0][i]);
			decision_scores[i] = (linear_net.outputs->frames[0][i]+1)/2;

		real first_max_value;
		long first_max_index;

		// finding value and index of first and second maxima
		getMax(decision_scores,10,first_max_value,first_max_index);

	}
	float recog_time= timer.getTime();


	bool found(false);
	for(int t=0; t<Nt; t++)
		if(correct[t]/(10000-n_rejected[t])*100>99.5)
		{
			fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
			cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

			fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
			cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
			
			fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;		
			cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;

			float th= ((float)(t))/((float) Nt);
			fout<<"th= "<<th<<endl;
			cout<<"th= "<<th<<endl;


			//float recog_time= timer.getTime();
			fout<<"Recognition Time: "<<recog_time<<endl;
			cout<<"Recognition Time: "<<recog_time<<endl;

			found=true;
			break;

		}

		if(!found)
			cout<<"99.5% not reached!"<<endl;

		fout.close();
		fin_mat.close();
		fin_mu.close();

	delete(allocator);


}


