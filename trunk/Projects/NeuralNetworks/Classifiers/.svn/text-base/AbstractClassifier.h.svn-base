#pragma once

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
#include "Timer.h"
#include<iostream>
#include<fstream>
#include<vector>

using namespace std;

using namespace Torch;
class AbstractClassifier
{
protected:
		/*Local variables & Initialization*/
	float accuracy;
    float positiveSamples;
	float negatvieSamples;
	char* train_file;
	char* test_file;
	char* model_file;
	int num_of_features;
	Allocator *allocator ;
public:
	AbstractClassifier(void);
	static const int TRAIN_TYPE_OVO=0;
	static const int TRAIN_TYPE_OVA=1;
	static const int NO_OF_CLASSES=10;

public:
	~AbstractClassifier(void);
	void getMax(real* arr, long n_arr,  real &max_value, long &max_index);
	//getMax(real* arr, long n_arr, real &max_value, long &max_index)
	void TestExamplesForDigit(DataSet *test_data,  Machine* machine,int ExpectedDigit, char* StatFile);
	DataSet* TrainExamplesForDigit(int TrainDigit,DataSet*  train_set);
	void InitClassifier();
	void TrainFile(char* filename);
	void TestFile(char* filename);
	//char*  getImageFileName(int t);
void getImageFileName(int t, char* filename );
	void SetModelName(char* filename);

	

};

