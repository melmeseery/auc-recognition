
#include "MatDataSet.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "MSECriterion.h"
#include "OneHotClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "NPTrainer.h"
#include "KFold.h"
#include "KNN.h"
#include "MeanVarNorm.h"
#include "CmdLine.h"
#include "Random.h"
#include "ParzenMachine.h"
#include "Timer.h"
using namespace Torch;
#include<fstream>
#include<conio.h>
#include<iostream>
using namespace std;

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

	char train_file[]="C:/datasets/mnist_training_set.txt";
	char test_file[]="C:/datasets/mnist_testing_set.txt";

	Sequence class_labels(10,10);
	for(int i=0; i<10; i++)
	{
		for(int j=0; j<10; j++)
		{
			if(i==j)
				class_labels.frames[i][j]=1;
			else
				class_labels.frames[i][j]=0;
		}
	}


	// reading training and testing sets
	DataSet *temp1;
	temp1=new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,&class_labels);
	DataSet *temp2;
	temp2=new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);
	DataSet *test_data= new(allocator) ClassFormatDataSet(temp2,&class_labels);

	// creating the knn machine
	ParzenMachine parzen(28*28,10,0.7);

	// creating the trainer
	NPTrainer trainer(&parzen);

	//training ...
	trainer.train(train_data, NULL);


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

		if(k%100==0)
			cout<<k<<endl;

		test_data->setExample(k);
		int d= temp2->targets->frames[0][0];//actual value of sample

		real *decision_scores;// pointer first element of an array holding decision scores

		// forwarding test sample
		parzen.forward(test_data->inputs);
		decision_scores= parzen.outputs->frames[0];

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

	bool found(false);
	for(int t=0; t<Nt; t++)
	{
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

			found=true;
			break;

		}
	}

	if(!found)
		cout<<"99.5% not reached!"<<endl;



	//float recog_time(0);
	//Timer timer;
	//for(int k = 0; k < test_data->n_examples; k++)
	//{

	//	test_data->setExample(k);
	//	int d= temp2->targets->frames[0][0];//actual value of sample

	//	real *decision_scores;// pointer first element of an array holding decision scores

	//	// forwarding test sample
	//	parzen.forward(test_data->inputs);
	//	decision_scores= parzen.outputs->frames[0];

	//	real first_max_value;
	//	long first_max_index;

	//	// finding value and index of first and second maxima
	//	getMax(decision_scores,10,first_max_value,first_max_index);

	//}
	//recog_time= timer.getTime();

	//fout<<"Recognition Time: "<<recog_time<<endl;
	//cout<<"Recognition Time: "<<recog_time<<endl;


	delete(allocator);

}