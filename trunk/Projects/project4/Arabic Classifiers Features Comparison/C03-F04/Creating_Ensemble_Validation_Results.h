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
#include <fstream>
#include <iostream>
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


void Create_Ensemble_Validation_Results()
{

	Allocator *allocator = new Allocator;

	int number_of_features=100;

	ofstream fout("C03-F04_ensemble_validation_results.txt");


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

	char train_file[]="C:/datasets/arabic_kirsh4_ourfeatures_training_set_full.txt";
	DataSet *unformated_dataset;
	unformated_dataset=new(allocator) MatDataSet(train_file,number_of_features,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(unformated_dataset,class_labels);


	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);


	int training_mask[50000];
	for(int i=0; i<50000; i++)
		training_mask[i]=i;


	const int n_hu=100;

	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(number_of_features,n_hu);
	Tanh *c2= new(allocator) Tanh(n_hu);
	Linear *c3= new(allocator) Linear(n_hu,10);
	LogSoftMax *c4= new(allocator) LogSoftMax(10);
	mlp.addFCL(c1);
	mlp.addFCL(c2);
	mlp.addFCL(c3);
	mlp.addFCL(c4);
	mlp.build();
	mlp.setPartialBackprop();

	// creating the trainer
	StochasticGradient trainer(&mlp, criterion);
	trainer.setIOption("max iter",100);


	// training...
	train_data->pushSubset(training_mask,50000);
	trainer.train(train_data, NULL);
	train_data->popSubset();

	real accuracy(0);
	//testing on the validation set
	for(int t=0; t<10000; t++)
	{
		unformated_dataset->setExample(50000+t);
		int d=unformated_dataset->targets->frames[0][0];

		real decision_scores[10];// to hold decision scores

		mlp.forward(unformated_dataset->inputs);//forwarding sample

		for(int i=0; i<10; i++)
			decision_scores[i] = mlp.outputs->frames[0][i];

		real first_max_value;
		long first_max_index;


		// finding value and index of first maximum
		getMax(decision_scores,10,first_max_value,first_max_index);
		long max_index;
		float max_value;
		for(int i=0; i<10; i++)
		{
			getMax(decision_scores,10,max_value,max_index);
			fout<<max_index<<" ";
			decision_scores[max_index]=-9999999;
		}
		fout<<endl;


		if(first_max_index==d)
			accuracy++;
	}

	cout<<accuracy/100<<endl;




	delete(allocator);


}