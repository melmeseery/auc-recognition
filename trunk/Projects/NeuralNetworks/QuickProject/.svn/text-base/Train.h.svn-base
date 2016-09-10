

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
using namespace Torch;

#include<iostream>
using namespace std;

void TRAIN_SVM_OVO(char * filename)
{
	int num_of_features=28*28;
	 
	Allocator *allocator = new Allocator;


	char train_file[]="C:/datasets/mahdbase_training_set.txt";
	//train_file
	DiskXFile model_file("modelSVM", "w");


	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	float g=0.02;//0.0659
	float C=100;
	Kernel *kernel = new(allocator) GaussianKernel(g);

	DataSet *train_set;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	//MeanVarNorm mv_norm(train_set);
	//train_set->preProcess(&mv_norm);


	MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for(int n=0; n<20000; n++)
	{
		inputs[n]= new(allocator) Sequence(1,num_of_features);
		targets[n]= new(allocator) Sequence(1,1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);


	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;
	

			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C", C);
			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			counter=0;
			for(long k=0; k<60000; k++)
			{

				train_set->setExample(k);
				int d= train_set->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<num_of_features; f++)
					{
						current_train_set->inputs->frames[0][f]=
							train_set->inputs->frames[0][f];
					}

					if(d==i)
						current_train_set->targets->frames[0][0]= 1;
					else
						current_train_set->targets->frames[0][0]= -1;

					counter++;
				}

			}
			cout<<counter<<endl;

			current_train_set->setExample(0);
			trainers[i][j]->train(current_train_set,NULL);
			current_train_set->n_examples=counter;
			svms[i][j]->saveXFile(&model_file);


		}
	}


	delete(allocator);


}
void TRAIN(char * filename, int n_features, float g, float C )
{
	 
	Allocator *allocator = new Allocator;

	//char train_file[]="C:/datasets/mahdbase_training_set.txt";

		char* train_file=filename;
	DiskXFile model_file("modelSVM", "w");

	

	// creating the SVMs
	//float g=0.03;//gamma
	//float C=50;
	Kernel *kernel = new(allocator) GaussianKernel(g);
//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
	SVM* svms;// = allocator->alloc(sizeof(SVM));
	svms=new (allocator) SVMClassification(kernel);
svms->setROption("C", C);

	//for(int i = 0; i <  NO_OF_CLASSES; i++)
	//{
	//	svms[i] = new(allocator) SVMClassification(kernel);
	//	svms[i]->setROption("C", C);
	//}

cout<<"reading the training set "<<endl;

	// reading the training set
	MatDataSet *train_data = new(allocator) MatDataSet(train_file,  n_features, 1, false, -1, false);
	

	// training the SVMs (One vs All)
	for(int i = 0; i <  NO_OF_CLASSES; i++)
	{
	if (i==DIGIT_TRAINED ){
		QCTrainer trainer(svms);

		Sequence class_labels( NO_OF_CLASSES, 1);
		for(int j = 0; j <  NO_OF_CLASSES; j++)
		{
			if(j == 1)
				class_labels.frames[j][0] =  1;
			else
				class_labels.frames[j][0] = -1;
		}
		ClassFormatDataSet formatted_train_data(train_data, &class_labels);
cout<<"traininng  "<<endl;
		trainer.train(&formatted_train_data, NULL);
cout<<"Saving file  "<<endl;
		// saving SVM model in turn
		svms->saveXFile(&model_file);
}

}
	delete(allocator);


}
void  TrainLinear(char* filename,int n_features){

Allocator *allocator = new Allocator;

	char* train_file=filename;
	

	cout<<"Creatign linear classifier"<<endl;
	// creating the linear machine
	ConnectedMachine linear_net;
	Linear *c1= new(allocator) Linear(n_features,1);
	//LogSoftMax *c2= new(allocator) LogSoftMax(10);
	Tanh *c2= new(allocator) Tanh(1);
	linear_net.addFCL(c1);
	linear_net.addFCL(c2);
	linear_net.build();
	linear_net.setPartialBackprop();

cout<<"Creatign linear class labels "<<endl;
	// reading training data from file and converting it to one hot class format
	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(j==5)
				class_labels->frames[0][j]=1;
			else
				class_labels->frames[0][j]=-1;
		}
	}
	DataSet *temp1;
	cout<<"Creatign data set. "<<endl;
	temp1=new(allocator) MatDataSet(train_file,n_features,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);



	
	// specifycout<<"Creatign linear classifier"<<endling cost function
	cout<<"Creatign host class formatin "<<endl;
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	//Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);
	Criterion *criterion = new(allocator) MSECriterion(10);

	// creating the trainer

	StochasticGradient trainer(&linear_net, criterion);
	trainer.setIOption("max iter",100);

	cout<<"Training "<<endl;
	// training...
	trainer.train(train_data, NULL);
	

	cout<<"Save file..........."<<endl;
	//saving model to file
	linear_net.save("model");


	delete(allocator);



}