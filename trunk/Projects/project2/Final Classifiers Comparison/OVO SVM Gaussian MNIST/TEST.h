
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
#include<fstream>
#include<conio.h>
#include<vector>
using namespace Torch;
#include<fstream>
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


	ifstream calib_fin("calibrating_parameters.txt");
	float As[10][10];
	float Bs[10][10];
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{calib_fin>>As[i][j]; calib_fin>>Bs[i][j];}
	}


	int num_of_features=28*28;

	Allocator *allocator = new Allocator;

	char test_file[]="C:/datasets/mnist_testing_set.txt";

	DiskXFile model_file("model", "r");


	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);


	ifstream sv_fin("support_vectors.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors.txt"; getch();}
	int n;
	sv_fin>>n;
	vector<Sequence*> SupportVectors(n);
	for(int n=0; n<SupportVectors.size(); n++)
	{
		SupportVectors[n]= new Sequence(1,28*28);
		for(int f=0; f<28*28; f++)
		{sv_fin>>(SupportVectors[n]->frames[0][f]); /*cout<<(*SupportVectors[n])[f]<<endl; getch();*/}
	}


	ifstream sv_dist_fin("support_vectors_distribution.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors_distribution.txt"; getch();}
	sv_dist_fin>>n;
	vector<int> SupportVectorsDist(n);
	for(int n=0; n<SupportVectorsDist.size(); n++)
		sv_dist_fin>>SupportVectorsDist[n]; 



	// creating pairwise classifiers pointers
	float g=0.02;//0.0659
	float C=10;
	Kernel *kernel = new(allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];


	//loading pairwise classifiers in turn
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			cout<<i<<" vs "<<j<<endl;

			svms[i][j] = new(allocator) SVMClassification(kernel);

			svms[i][j]->loadXFile(&model_file);

		}//end i
	}//end j





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
	vector<float> KernelEvaluations(SupportVectors.size());
	for(int k = 0; k < test_set->n_examples; k++)
	{

		if(k%100==0)
			cout<<k<<endl;


		test_set->setExample(k);

		int d= test_set->targets->frames[0][0];

		for(int n=0; n<SupportVectors.size(); n++)
			KernelEvaluations[n]=kernel->eval(test_set->inputs,SupportVectors[n]);


		real binary_decisions[10][10];
		int count=0;
		for(int i=0; i<=8; i++)
		{
			for(int j=i+1; j<=9; j++)
			{
				float f=svms[i][j]->b;
				for(int n=0; n<svms[i][j]->n_support_vectors; n++)
					f += KernelEvaluations[SupportVectorsDist[count++]]*svms[i][j]->sv_alpha[n];

				binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j])); 
				binary_decisions[j][i]= 1-binary_decisions[i][j];
			}
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


		real first_max_value;
		long first_max_index;
		real second_max_value;
		long second_max_index;

		// finding value and index of first and second maxima
		getMax(decisions,10,first_max_value,first_max_index);
		decisions[first_max_index]=-99;
		getMax(decisions,10,second_max_value,second_max_index);

		if(first_max_index==d)
			actual_accuracy++;

		//cout<<d<<"->"<<first_max_index<<endl; getch();

		for(int t=0;t<Nt; t++)
		{
			float th= ((float)(t))/((float) Nt);
			if((first_max_value-second_max_value)<th)
				n_rejected[t]++;
			else if(first_max_index==d)
				correct[t]++;
		}

	}

	//cout<<correct[Nt-1]/(10000-n_rejected[Nt-1])*100<<endl; getch();

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
	//for(int k = 0; k < test_set->n_examples; k++)
	//{

	//	test_set->setExample(k);

	//	int d= test_set->targets->frames[0][0];

	//	for(int n=0; n<SupportVectors.size(); n++)
	//		KernelEvaluations[n]=kernel->eval(test_set->inputs,SupportVectors[n]);


	//	real binary_decisions[10][10];
	//	int count=0;
	//	for(int i=0; i<=8; i++)
	//	{
	//		for(int j=i+1; j<=9; j++)
	//		{
	//			float f=svms[i][j]->b;
	//			for(int n=0; n<svms[i][j]->n_support_vectors; n++)
	//				f += KernelEvaluations[SupportVectorsDist[count++]]*svms[i][j]->sv_alpha[n];

	//			binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j])); 
	//			binary_decisions[j][i]= 1-binary_decisions[i][j];
	//		}
	//	}

	//	real decisions[10];
	//	for(int i=0; i<=9; i++)
	//	{
	//		real prob_sum(0);
	//		for(int j=0; j<=9; j++)
	//		{
	//			if(i!=j)
	//			{
	//				prob_sum += 1/(binary_decisions[i][j]);
	//			}
	//		}

	//		decisions[i]= 1/(prob_sum - 8);
	//	}


	//	real first_max_value;
	//	long first_max_index;

	//	// finding value and index of first and second maxima
	//	getMax(decisions,10,first_max_value,first_max_index);

	//}
	//recog_time= timer.getTime();

	//fout<<"Recognition Time: "<<recog_time<<endl;
	//cout<<"Recognition Time: "<<recog_time<<endl;



	delete(allocator);


}

