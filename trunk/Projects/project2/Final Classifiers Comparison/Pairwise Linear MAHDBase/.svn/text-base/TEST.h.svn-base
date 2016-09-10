
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
#include "Timer.h"
#include<conio.h>
using namespace Torch;
#include<iostream>
#include<fstream>
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

	char test_file[]="D:/datasets/mahdbase_testing_set.txt";
	DiskXFile model_file("model", "r");


	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);


	// creating pairwise classifiers pointers
	ConnectedMachine *nets[10][10];
	Linear *lin_layer[10][10];
	Tanh *out_layer[10][10];


	//loading pairwise classifiers in turn
	int counter;
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{

			lin_layer[i][j]= new(allocator) Linear(28*28,1);
			out_layer[i][j]= new(allocator) Tanh(1);

			nets[i][j]= new(allocator) ConnectedMachine();
			nets[i][j]->addFCL(lin_layer[i][j]);
			nets[i][j]->addFCL(out_layer[i][j]);
			nets[i][j]->build();
			nets[i][j]->loadXFile(&model_file);
		}
	}


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
	for(int t = 0; t < test_set->n_examples; t++)
	{

		test_set->setExample(t);
		int d= test_set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;
		real second_max_value;
		long second_max_index;



		real binary_decisions[10][10];
		for(int i=0; i<=8; i++)
			for(int j=i+1; j<=9; j++)
			{
				nets[i][j]->forward(test_set->inputs);
				binary_decisions[i][j]= (1+nets[i][j]->outputs->frames[0][0])/2; 
				binary_decisions[j][i]= 1-binary_decisions[i][j];

			}

			real decisions[10];
			for(int i=0; i<=9; i++)
			{
				real prob_sum(0);
				for(int j=0; j<=9; j++)
				{
					if(i!=j)
					{
						prob_sum += 1/(binary_decisions[i][j]);
					}
				}

				decisions[i]= 1/(prob_sum - 8);

			}


			// finding value and index of first and second maxima
			getMax(decisions,10,first_max_value,first_max_index);
			decisions[first_max_index]=-99;
			getMax(decisions,10,second_max_value,second_max_index);

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


	////calculating recognition time
	float recog_time(0);
	//Timer timer;
	//for(int iter=0; iter<100; iter++)
	//{
	//	for(int t = 0; t < test_set->n_examples; t++)
	//	{

	//		test_set->setExample(t);
	//		int d= test_set->targets->frames[0][0];

	//		real first_max_value;
	//		long first_max_index;

	//		real binary_decisions[10][10];
	//		for(int i=0; i<=8; i++)
	//			for(int j=i+1; j<=9; j++)
	//			{
	//				nets[i][j]->forward(test_set->inputs);
	//				binary_decisions[i][j]= (1+nets[i][j]->outputs->frames[0][0])/2; 
	//				binary_decisions[j][i]= 1-binary_decisions[i][j];

	//			}

	//			real decisions[10];
	//			for(int i=0; i<=9; i++)
	//			{
	//				real prob_sum(0);
	//				for(int j=0; j<=9; j++)
	//				{
	//					if(i!=j)
	//					{
	//						prob_sum += 1/(binary_decisions[i][j]);
	//					}
	//				}

	//				decisions[i]= 1/(prob_sum - 8);

	//			}


	//			getMax(decisions,10,first_max_value,first_max_index);
	//	}
	//}

	//recog_time=timer.getTime()/100;


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

		delete(allocator);


}



