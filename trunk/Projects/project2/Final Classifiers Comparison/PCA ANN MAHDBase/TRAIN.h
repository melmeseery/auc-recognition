
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

using namespace Torch;

void TRAIN()
{
	 
	Allocator *allocator = new Allocator;

	char train_file[]="D:/datasets/mahdbase_pca_training_set.txt";
	

	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(40,120);
	Tanh *c2= new(allocator) Tanh(120);
	Linear *c3= new(allocator) Linear(120,10);
	LogSoftMax *c4= new(allocator) LogSoftMax(10);
	mlp.addFCL(c1);
	mlp.addFCL(c2);
	mlp.addFCL(c3);
	mlp.addFCL(c4);
	mlp.build();
	mlp.setPartialBackprop();


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
				class_labels->frames[i][j]=0;
		}
	}
	DataSet *temp1;
	temp1=new(allocator) MatDataSet(train_file,40,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);



	
	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);

	// creating the trainer
	StochasticGradient trainer(&mlp, criterion);
	trainer.setIOption("max iter",100);

	// training...
	trainer.train(train_data, NULL);
	
	//saving model to file
	mlp.save("model");


	delete(allocator);


}