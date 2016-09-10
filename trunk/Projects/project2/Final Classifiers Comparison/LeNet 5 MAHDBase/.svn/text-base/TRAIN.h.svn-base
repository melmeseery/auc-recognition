

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
#include<iostream>
#include<fstream>
#include<conio.h>
#include "DigitResearchGlobals.h"

using namespace std;
using namespace Torch;

void TRAIN()
{
	 
	Allocator *allocator = new Allocator;

	char train_file[]=train_MAHD_32_file;
	//char train_file[]="C:/datasets/mahdbase32by32_training_set.txt";


	///////////////////////////////// creating LeNet-5 ////////////////////////////////////
	ConnectedMachine leNet_5;
	cout<<" creating the lenet "<<endl;
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
	Tanh out_s(10);
	leNet_5.addMachine(&out_s);
	leNet_5.connectOn(&out);

	leNet_5.build();
	leNet_5.setPartialBackprop();

	//////////////////////////////////////////////////////////////////////////////////////////////



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
	cout<<"REading data "<<endl;
	DataSet *temp1;
	temp1=new(allocator) MatDataSet(train_file,32*32,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);



	
	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) MSECriterion(10);

	// creating the trainer
	StochasticGradient trainer(&leNet_5, criterion);
	trainer.setIOption("max iter",100);
	//trainer.setROption("learning rate decay",0.1);
	trainer.setROption("learning rate",0.005);

		cout<<"Training .. "<<endl;
	// training...
	trainer.train(train_data, NULL);
	
	//saving model to file
	leNet_5.save("model");


	delete(allocator);


}

















//
//#include "MatDataSet.h"
//#include "ClassFormatDataSet.h"
//#include "ClassNLLCriterion.h"
//#include "MSECriterion.h"
//#include "OneHotClassFormat.h"
//#include "MultiClassFormat.h"
//#include "ClassMeasurer.h"
//#include "MSEMeasurer.h"
//
//#include "StochasticGradient.h"
//#include "KFold.h"
//
//#include "ConnectedMachine.h"
//#include "Linear.h"
//#include "Tanh.h"
//#include "LogSoftMax.h"
//
//#include "MeanVarNorm.h"
//#include "DiskXFile.h"
//#include "MemoryXFile.h"
//#include "CmdLine.h"
//#include "Random.h"
//#include "Sigmoid.h"
//#include "LogRBF.h"
//#include "EMTrainer.h"
//#include "KMeans.h"
//#include "SpatialSubSampling.h"
//#include "SpatialConvolution.h"
//
//using namespace Torch;
//
//using namespace Torch;
//
//void TRAIN()
//{
//	 
//	Allocator *allocator = new Allocator;
//
//	char train_file[]="C:/datasets/mnist32by32_training_set.txt";
//	
//
//	///////////////////////////////// creating LeNet-5 ////////////////////////////////////
//	ConnectedMachine leNet_5;
//	
//	SpatialConvolution* c1[6];
//	for(int i=0;i<6;i++)
//	{
//		c1[i]= new(allocator) SpatialConvolution(1,1,32,32,5,1,1);
//		leNet_5.addMachine(c1[i]);
//	}
//	leNet_5.addLayer();
//	Tanh* c1_t[6];
//	for(int i=0;i<6;i++)
//	{	
//		c1_t[i]= new(allocator) Tanh(28*28);
//		leNet_5.addMachine(c1_t[i]);
//		leNet_5.connectOn(c1[i]);
//	}
//	leNet_5.addLayer();
//
//	SpatialSubSampling* s2[6];
//	for(int i=0;i<6;i++)
//	{
//		s2[i]= new(allocator) SpatialSubSampling(1,28,28,2,2,2);
//		leNet_5.addMachine(s2[i]);
//		leNet_5.connectOn(c1_t[i]);
//	}
//	leNet_5.addLayer();
//	Tanh* s2_t[6];
//	for(int i=0;i<6;i++)
//	{	
//		s2_t[i]= new(allocator) Tanh(14*14);
//		leNet_5.addMachine(s2_t[i]);
//		leNet_5.connectOn(s2[i]);
//	}
//	leNet_5.addLayer();
//
//
//	SpatialConvolution* c3[16];
//	c3[0]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[0]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[2]);
//
//	c3[1]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[1]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[3]);
//
//	c3[2]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[2]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[4]);
//
//	c3[3]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[3]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[4]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[4]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[4]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[4]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[5]= new(allocator) SpatialConvolution(3,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[5]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[6]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[6]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[3]);
//
//	c3[7]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[7]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[4]);
//
//	c3[8]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[8]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[4]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[9]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[9]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[4]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[10]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[10]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[4]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[11]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[11]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[12]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[12]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[4]);
//
//	c3[13]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[13]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[4]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[14]= new(allocator) SpatialConvolution(4,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[14]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[5]);
//
//	c3[15]= new(allocator) SpatialConvolution(6,1,14,14,5,1,1);
//	leNet_5.addMachine(c3[15]);
//	leNet_5.connectOn(s2_t[0]);
//	leNet_5.connectOn(s2_t[1]);
//	leNet_5.connectOn(s2_t[2]);
//	leNet_5.connectOn(s2_t[3]);
//	leNet_5.connectOn(s2_t[4]);
//	leNet_5.connectOn(s2_t[5]);
//
//	leNet_5.addLayer();
//	Tanh* c3_t[16];
//	for(int i=0;i<16;i++)
//	{	
//		c3_t[i]= new(allocator) Tanh(10*10);
//		leNet_5.addMachine(c3_t[i]);
//		leNet_5.connectOn(c3[i]);
//	}
//	leNet_5.addLayer();
//
//
//	SpatialSubSampling* s4[16];
//	for(int i=0;i<16;i++)
//	{
//		s4[i]= new(allocator) SpatialSubSampling(1,10,10,2,2,2);
//		leNet_5.addMachine(s4[i]);
//		leNet_5.connectOn(c3_t[i]);
//	}
//	leNet_5.addLayer();
//	Tanh s4_t(16*5*5);
//	leNet_5.addMachine(&s4_t);
//	for(int i=0;i<16;i++)
//	{
//		leNet_5.connectOn(s4[i]);
//	}
//
//	leNet_5.addLayer();
//	Linear c5(16*5*5,120);
//	leNet_5.addMachine(&c5);
//	leNet_5.connectOn(&s4_t);
//
//	leNet_5.addLayer();
//	Tanh c5_t(120);
//	leNet_5.addMachine(&c5_t);
//	leNet_5.connectOn(&c5);
//
//	leNet_5.addLayer();
//	Linear f6(120,84);
//	leNet_5.addMachine(&f6);
//	leNet_5.connectOn(&c5_t);
//
//	leNet_5.addLayer();
//	Tanh f6_t(84);
//	leNet_5.addMachine(&f6_t);
//	leNet_5.connectOn(&f6);
//
//	leNet_5.addLayer();
//	Linear out(84,10);
//	leNet_5.addMachine(&out);
//	leNet_5.connectOn(&f6_t);
//
//	leNet_5.addLayer();
//	LogSoftMax out_s(10);
//	leNet_5.addMachine(&out_s);
//	leNet_5.connectOn(&out);
//
//	leNet_5.build();
//	leNet_5.setPartialBackprop();
//
//	//////////////////////////////////////////////////////////////////////////////////////////////
//
//
//
//	// reading training data from file and converting it to one hot class format
//	Sequence *class_labels= new(allocator) Sequence(10,10);
//	int i,j;
//	for(i=0;i<=9;i++)
//	{
//		for(j=0;j<=9;j++)
//		{
//			if(i==j)
//				class_labels->frames[i][j]=1;
//			else
//				class_labels->frames[i][j]=0;
//		}
//	}
//	DataSet *temp1;
//	temp1=new(allocator) MatDataSet(train_file,32*32,1,false,-1,false);
//	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);
//
//
//
//	
//	// specifying cost function
//	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
//	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);
//
//	// creating the trainer
//	StochasticGradient trainer(&leNet_5, criterion);
//	trainer.setIOption("max iter",100);
//	trainer.setROption("learning rate decay",0.1);
//
//	// training...
//	trainer.train(train_data, NULL);
//	
//	//saving model to file
//	leNet_5.save("model");
//
//
//	delete(allocator);
//
//
//}