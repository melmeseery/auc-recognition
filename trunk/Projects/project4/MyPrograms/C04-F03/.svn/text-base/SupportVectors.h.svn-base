
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



void SupportVectors()
{

	Allocator *allocator = new Allocator;

	int num_of_features=200;
	char train_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	DiskXFile model_file("model", "r");


	DataSet *train_set= new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	SVMClassification* svms[10][10];
	GaussianKernel kernel(0.1);

	ofstream sv_fout("SVs.txt");
	vector<float> SVs(0);
	for(int i=0; i<=8; i++)
		for(int j=i+1; j<=9; j++)
		{
			cout<<i<<" vs "<<j<<endl;
			svms[i][j] = new(allocator) SVMClassification(&kernel);
			svms[i][j]->loadXFile(&model_file);

			for(int n=0; n<svms[i][j]->n_support_vectors;n++)
			{

				Sequence* seq= svms[i][j]->sv_sequences[n];

				for(int k=0; k<train_set->n_examples; k++)
				{
					train_set->setExample(k);

					bool found(true);
					for(int f=0; f<num_of_features; f++)
						if(seq->frames[0][f]!=train_set->inputs->frames[0][f])
						{found=false; break;}

					if(!found)
						continue;

					bool considered_before(false);
					for(int m=0; m<SVs.size(); m++)
						if(SVs[m]==k+1)
						{considered_before=true; break;}

					if(!considered_before)
						SVs.push_back(k+1);


				}//end k
			}//end n

		}


	for(int m=0; m<SVs.size(); m++)
		sv_fout<<SVs[m]<<" ";


	delete(allocator);


}


