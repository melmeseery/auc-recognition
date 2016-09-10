

#pragma once
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
#include "TwoClassFormat.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "Criterion.h"
#include "CmdLine.h"
#include "Random.h"
#include "Sigmoid.h"
#include "math.h"
#define NO_OF_CLASSES 2
#define DIGIT_TRAINED 1

using namespace Torch;

#include<iostream>
#include<fstream>
using namespace std;
int getFeatureCount(char* filename){
	ifstream inFile;
	inFile.open(filename);
	    if (!inFile) {
	        cout << "Unable to open file"<<filename<<endl;
	        exit(1); // terminate with error
	    }
	    char x[500];


		int ssize;
		int i=0;
		if (inFile>>ssize){
			cout<<"number of sampels  "<<ssize<<endl;


		}
		int nCount=0;
		if (inFile>>nCount){

			cout<<"   feature count "<<nCount<<endl;

		}
	    inFile.close();
			//cout<<"Size of file "<<size<<endl;
	   // cout << "Sum = " << sum << endl;
	    return (nCount-1);
}
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
	n_features=getFeatureCount(filename);


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
//	for(int i = 0; i <  NO_OF_CLASSES; i++)
//	{
	//if (i==DIGIT_TRAINED ){
	//TwoClassFormat *class_format= new(allocator)TwoClassFormat(train_data);
		//	Criterion* criterion = new(allocator) ClassNLLCriterion(class_format);
		QCTrainer trainer(svms);




		//Sequence class_labels( NO_OF_CLASSES, 1);
//		for(int j = 0; j <  NO_OF_CLASSES; j++)
//		{
//			if(j == DIGIT_TRAINED )
//				class_labels.frames[0][0] =  1;
//			else
//				class_labels.frames[0][0] = -1;
//		}

//		for (int t = 0; t < max; t++) {
//
//		}
		// Create the encoding format
	//	TwoClassFormat class_format(n_targets);
		// specifying cost function

//		for(int t = 0; t < train_data->n_examples; t++)
//			{
//			 train_data->setExample(t);
//			 int d= train_data->targets->frames[0][0];;
//
//			if (d== DIGIT_TRAINED){
//				train_data->targets->frames[0][0]=1;;
//			}
//			else{
//				train_data->targets->frames[0][0]=-1;;
//			}
//
//
//			}
	//	ClassFormatDataSet formatted_train_data(train_data, 2);
		cout<<"traininng  "<<endl;
		trainer.train(train_data,  NULL );
			cout<<"Saving file  "<<endl;
		// saving SVM model in turn
		svms->saveXFile(&model_file);

	delete(allocator);


}


