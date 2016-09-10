
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
#include "conio.h"
#include "stdio.h"
#include <iostream>
#include "DigitResearchGlobals.h"

#define debugLog true 
using namespace std;
using namespace Torch;

void TRAIN()
{
	// create the allocator opject 
	Allocator *allocator = new Allocator;

	char train_file[]=train_MAHD_file;
	//	char train_file[]="C:/datasets/mahdbase_training_set.txt";

	if (debugLog)
		cout<<" creating mlp "<<endl;
	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(28*28,150);
	Tanh *c2= new(allocator) Tanh(150);
	Linear *c3= new(allocator) Linear(150,10);
	LogSoftMax *c4= new(allocator) LogSoftMax(10);
	mlp.addFCL(c1);
	mlp.addFCL(c2);
	mlp.addFCL(c3);
	mlp.addFCL(c4);
	mlp.build();
	mlp.setPartialBackprop();



	// reading training data from file and converting it to one hot class format
	//means  for 4 the label will be 0 0 0 0 1 0 0 0 0  0
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

	DataSet *temp1;
	if (debugLog)
		cout<<" Reading dataset from files "<<endl;
	// read the data set formm files
	temp1=new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);



	
	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);

	// creating the trainer
	StochasticGradient trainer(&mlp, criterion);
	trainer.setIOption("max iter",10);
     
	if (debugLog)
		cout<<" Training ---- "<<endl;
	// training...
	trainer.train(train_data, NULL);
	
	if (debugLog)
		cout<<" Saving "<<endl;
	//saving model to file
	mlp.save("model");


	delete(allocator);


}