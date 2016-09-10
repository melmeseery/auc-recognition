
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


void TRAIN()
{
	 
	Allocator *allocator = new Allocator;

	char train_file[]="C:/datasets/mnist_training_set.txt";
	DiskXFile model_file("model", "w");

	

	// creating the SVMs
	float g=0.03;//gamma
	float C=50;
	Kernel *kernel = new(allocator) GaussianKernel(g);

	SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)*10);
	for(int i = 0; i < 10; i++)
	{
		svms[i] = new(allocator) SVMClassification(kernel);
		svms[i]->setROption("C", C);
	}



	// reading the training set
	MatDataSet *train_data = new(allocator) MatDataSet(train_file, 28*28, 1, false, -1, false);
	

	// training the SVMs (One vs All)
	for(int i = 0; i < 10; i++)
	{
		QCTrainer trainer(svms[i]);

		Sequence class_labels(10, 1);
		for(int j = 0; j < 10; j++)
		{
			if(j == i)
				class_labels.frames[j][0] =  1;
			else
				class_labels.frames[j][0] = -1;
		}
		ClassFormatDataSet formatted_train_data(train_data, &class_labels);

		trainer.train(&formatted_train_data, NULL);

		// saving SVM model in turn
		svms[i]->saveXFile(&model_file);
	}


	delete(allocator);


}