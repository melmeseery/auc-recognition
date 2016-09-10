
#pragma once
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
using namespace std;

using namespace Torch;
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
			cout<<"number of samples  "<<ssize<<endl;


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
void TRAIN_DATA_BITS(char* train_file, char* model, int n_hu, int feat)
{

	  cout<<"Training.......... ."<<endl;
	Allocator *allocator = new Allocator;
	int  num_of_features = getFeatureCount(train_file);
    int my_digit = 1;

	DiskXFile out_model_file(model, "w");

	// creating the mlp
	ConnectedMachine mlp[10];
	Linear *c1[10];
	Tanh *c2[10];
	Linear *c3[10];
	Tanh *c4[10];
	  cout<<"Addng layers......... ."<<endl;
	for(int count = my_digit; count <= my_digit; count++)
	{
		c1[count]= new(allocator) Linear(num_of_features,n_hu);
		c2[count]= new(allocator) Tanh(n_hu);
		c3[count]= new(allocator) Linear(n_hu,1);
		c4[count]= new(allocator) Tanh(1);

		mlp[count].addFCL(c1[count]);
		mlp[count].addFCL(c2[count]);
		mlp[count].addFCL(c3[count]);
		mlp[count].addFCL(c4[count]);
		mlp[count].build();
		mlp[count].setPartialBackprop();
    }
	  cout<<"Reading data.......... ."<<endl;
	DataSet *train_set;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	Criterion *criterion[10];
	// creating the trainer
	Trainer **trainers = (Trainer **)allocator->alloc(sizeof(Trainer *)*10);
	  cout<<"Training each digit.......... ."<<endl;
	/*Training each digit*/
	for(int count = my_digit; count <= my_digit; count++)
	{
		long trained_with_one = 0;
		long trainedAsNotFive=0;
		cout<<"Training digit "<<count<<endl;
		for(int t = 0; t < train_set->n_examples; t++)
		{

			train_set->setExample(t);
				if((t%500) == 0)  {
				cout<<"Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;

			}

			if(train_set->targets->frames[0][0] == count)
			{

                train_set->targets->frames[0][0] = 1;
				trained_with_one++;
				//cout<<"Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;
			}
			else {
		//		cout<<"---------------Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;
                train_set->targets->frames[0][0] = -1;
				trainedAsNotFive++;

			}
		}

		cout<<"examples trained with one: "<<trained_with_one<<endl;
		cout<<"examples trained not five: "<<trainedAsNotFive<<endl;
        cout<<"total number of examples: "<<train_set->n_examples<<endl;

		criterion[count] = new(allocator) MSECriterion(1);
		trainers[count] = new(allocator) StochasticGradient(&mlp[count], criterion[count]);

		trainers[count]->setIOption("max iter",100);
			  cout<<"Train digit.......... ."<<endl;
		trainers[count]->train(train_set, NULL);

			  cout<<"Save file.......... ."<<endl;
		mlp[count].saveXFile(&out_model_file);
	}
	delete(allocator);
}
