
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



void Unique_Support()
{

	Allocator *allocator = new Allocator;

	int num_of_features=225;
	DiskXFile model_file("model", "r");
	ofstream sv_fout("support_vectors.txt");
	ofstream sv_dist_fout("support_vectors_distribution.txt");


	//int num_of_features=225+1;
	//DiskXFile model_file("zc_model", "r");
	//ofstream sv_fout("zc_support_vectors.txt");
	//ofstream sv_dist_fout("zc_support_vectors_distribution.txt");




	vector<Sequence*> SupportVectors(0);
	vector<int> SupportVectorsDist(0);

	float g=0.1;//0.0659
	Kernel *kernel = new(allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];


	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			cout<<i<<" vs "<<j<<endl;

			svms[i][j] = new(allocator) SVMClassification(kernel);

			svms[i][j]->loadXFile(&model_file);


			for(int n=0; n<svms[i][j]->n_support_vectors; n++)
			{
				bool found(false);
				int m(0);
				for(m=0; m<SupportVectors.size(); m++)
				{
					found=true;

					for(int f=0; f<num_of_features; f++)
						if(svms[i][j]->sv_sequences[n]->frames[0][f]!= SupportVectors[m]->frames[0][f])
						{found=false; break;}
							

					if(!found)
						continue;
					else
						break;


				}

				if(found)
					SupportVectorsDist.push_back(m);
				else
				{
					SupportVectors.push_back(svms[i][j]->sv_sequences[n]);
					SupportVectorsDist.push_back(SupportVectors.size()-1);
				}


			}//end n

		}//end i
	}//end j


	sv_fout<<SupportVectors.size()<<endl;
	for(int n=0; n<SupportVectors.size(); n++)
	{
		for(int f=0; f<num_of_features; f++)
			sv_fout<<SupportVectors[n]->frames[0][f]<<" ";
		
		sv_fout<<endl;
	}



	sv_dist_fout<<SupportVectorsDist.size()<<endl;
	for(int n=0; n<SupportVectorsDist.size(); n++)
		sv_dist_fout<<SupportVectorsDist[n]<<endl;

	delete(allocator);


}


