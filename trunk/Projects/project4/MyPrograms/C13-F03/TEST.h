
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

	Allocator *allocator = new Allocator;

	//int num_of_features=201;
	//char test_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("model", "r");

	int num_of_features=200;
	char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	DiskXFile model_file("model", "r");


	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



	// creating pairwise classifiers pointers
	float g=0.1;//0.0275;//0.04;//gamma
	//float C=100;
	Kernel *kernel = new(allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];

	//loading pairwise classifiers in turn
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			svms[i][j] = new(allocator) SVMClassification(kernel);

			//loading SVM models in turn
			svms[i][j]->loadXFile(&model_file);

		}
	}

	////Claculating number of unique support vectors
	//ofstream nsv_fout("number_of_unique_SVs.txt");
	//vector<vector<float>*> unique_SVs;
	//for(int i=0; i<=8; i++)
	//	for(int j=i+1; j<=9; j++)
	//	{
	//		//SVs_fout<<endl<<"("<<i<<","<<j<<"): ";
	//		for(int n=0; n<svms[i][j]->n_support_vectors;n++)
	//		{

	//			Sequence* seq= svms[i][j]->sv_sequences[n];
	//			bool found_SV(false);
	//			for(int m=0; m<unique_SVs.size(); m++)
	//			{
	//				found_SV=true;
	//				for(int s=0; s<200; s++)
	//					if(seq->frames[0][s]!=(*unique_SVs[m])[s])
	//					{
	//						found_SV=false;
	//						break;
	//					}

	//					if(found_SV)
	//					{
	//						break;
	//					}

	//			}

	//			if(found_SV)
	//			{
	//				continue;
	//			}

	//			unique_SVs.push_back(new vector<float>());//new(temp_alloc) vector<real>());
	//			for(int k=0; k<200; k++)
	//			{
	//				unique_SVs[unique_SVs.size()-1]->push_back(seq->frames[0][k]);
	//			}

	//		}
	//		cout<<"("<<i<<","<<j<<")"<<endl;
	//		//cout<<"number of unique support vectors: "<<unique_SVs.size()<<endl;

	//	}
	//	nsv_fout<<"number of unique support vectors: "<<unique_SVs.size()<<endl;
	//	cout<<"number of unique support vectors: "<<unique_SVs.size()<<endl;
	//	
	//	getch();


	real accuracy(0);
	real confusion_matrix[10][10];
	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++)
			confusion_matrix[i][j]=0;

	ofstream fout("C04-F03_full_results.txt");
	//ofstream conf_fout("confusion_matrix.txt");
	ofstream conf_fout("zc_confusion_matrix.txt");
	Timer timer;
	timer.stop();
	for(int t = 0; t < test_set->n_examples; t++)
	{
		if(t%100==0)
			cout<<t<<endl;

		real votes[10];
		for(int i=0; i<10; i++)
			votes[i]=0;

		test_set->setExample(t);
		int d= test_set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;


		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{
				timer.resume();
				svms[i][j]->forward(test_set->inputs);
				timer.stop();

				real decision = svms[i][j]->outputs->frames[0][0];

				if(decision>0)
					votes[i]=votes[i]+1;
				else
					votes[j]=votes[j]+1;

			}
		}

		// finding value and index of first maximum
		getMax(votes,10,first_max_value,first_max_index);
		long max_index;
		float max_value;
		for(int i=0; i<10; i++)
		{
			getMax(votes,10,max_value,max_index);
			fout<<max_index<<" ";
			votes[max_index]=-9999999;
		}
		fout<<endl;

		if(first_max_index==d)
			accuracy++;

		confusion_matrix[d][first_max_index]++;

	}


	cout<<"Accuracy = "<<accuracy/100<<endl;

	for(int i=0; i<10; i++)
	{
		for(int j=0; j<10; j++)
			conf_fout<<confusion_matrix[i][j]<<"  ";
		conf_fout<<endl<<endl;
	}



	delete(allocator);


}


