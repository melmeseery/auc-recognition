
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
#include "LogRBF.h"
#include "EMTrainer.h"
#include "KMeans.h"
#include "SpatialSubSampling.h"
#include "SpatialConvolution.h"
#include "math.h"
#include<iostream>
#include<fstream>
#include<Timer.h>
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

	char test_file[]="D:/datasets/mnist32by32_testing_set.txt";


	///////////////////////////////// creating LeNet-5 ////////////////////////////////////
	ConnectedMachine leNet_5;
	
	SpatialConvolution* c1[6];
	for(int i=0;i<6;i++)
	{
		c1[i]= new(allocator) SpatialConvolution(1,1,32,32,5,1,1);
		leNet_5.addMachine(c1[i]);
	}
	leNet_5.addLayer();
	Tanh* c1_t[6];
	for(int i=0;i<6;i++)
	{	
		c1_t[i]= new(allocator) Tanh(28*28);
		leNet_5.addMachine(c1_t[i]);
		leNet_5.connectOn(c1[i]);
	}
	leNet_5.addLayer();

	SpatialSubSampling* s2[6];
	for(int i=0;i<6;i++)
	{
		s2[i]= new(allocator) SpatialSubSampling(1,28,28,2,2,2);
		leNet_5.addMachine(s2[i]);
		leNet_5.connectOn(c1_t[i]);
	}
	leNet_5.addLayer();
	Tanh* s2_t[6];
	for(int i=0;i<6;i++)
	{	
		s2_t[i]= new(allocator) Tanh(14*14);
		leNet_5.addMachine(s2_t[i]);
		leNet_5.connectOn(s2[i]);
	}
	leNet_5.addLayer();


	SpatialConvolution* c3[16];
	c3[0]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
	leNet_5.addMachine(c3[0]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[2]);

	c3[1]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
	leNet_5.addMachine(c3[1]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[3]);

	c3[2]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
	leNet_5.addMachine(c3[2]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[4]);

	c3[3]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
	leNet_5.addMachine(c3[3]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[4]);
	leNet_5.connectOn(s2_t[5]);

	c3[4]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
	leNet_5.addMachine(c3[4]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[4]);
	leNet_5.connectOn(s2_t[5]);

	c3[5]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
	leNet_5.addMachine(c3[5]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[5]);

	c3[6]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[6]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[3]);

	c3[7]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[7]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[4]);

	c3[8]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[8]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[4]);
	leNet_5.connectOn(s2_t[5]);

	c3[9]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[9]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[4]);
	leNet_5.connectOn(s2_t[5]);

	c3[10]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[10]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[4]);
	leNet_5.connectOn(s2_t[5]);

	c3[11]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[11]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[5]);

	c3[12]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[12]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[4]);

	c3[13]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[13]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[4]);
	leNet_5.connectOn(s2_t[5]);

	c3[14]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
	leNet_5.addMachine(c3[14]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[5]);

	c3[15]= new(allocator) SpatialConvolution(6,1,14,14,5,1,1);
	leNet_5.addMachine(c3[15]);
	leNet_5.connectOn(s2_t[0]);
	leNet_5.connectOn(s2_t[1]);
	leNet_5.connectOn(s2_t[2]);
	leNet_5.connectOn(s2_t[3]);
	leNet_5.connectOn(s2_t[4]);
	leNet_5.connectOn(s2_t[5]);

	leNet_5.addLayer();
	Tanh* c3_t[16];
	for(int i=0;i<16;i++)
	{	
		c3_t[i]= new(allocator) Tanh(10*10);
		leNet_5.addMachine(c3_t[i]);
		leNet_5.connectOn(c3[i]);
	}
	leNet_5.addLayer();


	SpatialSubSampling* s4[16];
	for(int i=0;i<16;i++)
	{
		s4[i]= new(allocator) SpatialSubSampling(1,10,10,2,2,2);
		leNet_5.addMachine(s4[i]);
		leNet_5.connectOn(c3_t[i]);
	}
	leNet_5.addLayer();
	Tanh s4_t(16*5*5);
	leNet_5.addMachine(&s4_t);
	for(int i=0;i<16;i++)
	{
		leNet_5.connectOn(s4[i]);
	}

	leNet_5.addLayer();
	Linear c5(16*5*5,120);
	leNet_5.addMachine(&c5);
	leNet_5.connectOn(&s4_t);

	leNet_5.addLayer();
	Tanh c5_t(120);
	leNet_5.addMachine(&c5_t);
	leNet_5.connectOn(&c5);

	leNet_5.addLayer();
	Linear f6(120,84);
	leNet_5.addMachine(&f6);
	leNet_5.connectOn(&c5_t);

	leNet_5.addLayer();
	Tanh f6_t(84);
	leNet_5.addMachine(&f6_t);
	leNet_5.connectOn(&f6);

	leNet_5.addLayer();
	Linear out(84,10);
	leNet_5.addMachine(&out);
	leNet_5.connectOn(&f6_t);

	leNet_5.addLayer();
	LogSoftMax out_s(10);
	leNet_5.addMachine(&out_s);
	leNet_5.connectOn(&out);

	leNet_5.build();
	leNet_5.setPartialBackprop();

	//////////////////////////////////////////////////////////////////////////////////////////////


	//loading the model
	leNet_5.load("model");


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
	
	DataSet *test_data =new(allocator) MatDataSet(test_file,32*32,1,false,-1,false);

	
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

	for(int t = 0; t < test_data->n_examples; t++)
	{

		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		real decision_scores[10];// to hold decision scores

		leNet_5.forward(test_data->inputs);//forwarding sample

		// de-logging the decisions
		for(int i=0; i<10; i++)
			decision_scores[i] = (1+leNet_5.outputs->frames[0][i])/2;

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


	//calculating recognition time
	float recog_time(0);
	Timer timer;
	for(int t = 0; t < test_data->n_examples; t++)
	{

		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample

		real decision_scores[10];// to hold decision scores

		leNet_5.forward(test_data->inputs);//forwarding sample

		// de-logging the decisions
		for(int i=0; i<10; i++)
			decision_scores[i] = exp(leNet_5.outputs->frames[0][i]);

		real first_max_value;
		long first_max_index;

		getMax(decision_scores,10,first_max_value,first_max_index);

	}
	recog_time= timer.getTime();

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

			fout<<"Recognition Time= "<<recog_time<<endl;
			cout<<"Recognition Time= "<<recog_time<<endl;


			found=true;
			break;

		}

		if(!found)
			cout<<"99.5% not reached!"<<endl;

		fout.close();

		delete(allocator);


}

