/*
 * RunClassifiers.h
 *
 *  Created on: Jan 3, 2009
 *      Author: Maha
 */
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

#include "Timer.h"
#include <math.h>
#include <string.h>

#include<vector>
#include <stdio.h>
#include <stdlib.h>
using namespace Torch;
#include<fstream>
#include<iostream>
#include <string.h>
#include <sstream>
#include "LibraryFunctions.h"
using namespace std;
#ifndef RUNCLASSIFIERS_H_
#define RUNCLASSIFIERS_H_



class RunClassifiersOVO {
public:
//
//	string* myStrings;
//				int size;


	RunClassifiersOVO();
	virtual ~RunClassifiersOVO();
//	void svmTest(char* filename, int n_features, char* ifilename, float g,
//			float C);
//	void svmTrain(char * filename, int n_features, float g, float C);
	/*int getFeatureCount(char* filename);
	string getFile(int t);
	void getFiles(char* filename);
	void getMax(real* arr, long n_arr, real &max_value, long &max_index);*/
	void LinearTrain(char* train_file, int samples, int num_of_features, char* model);
	void LinearTest(char* test_file, int samples, int num_of_features,char* model);
	void FisherTest(char* test_file, int num_of_features , char* wsfile, char* bsfile);

	void svmRbfTest(char* test_file, int samples, float g, float C,
			int num_of_features, char* model);
	void svmRbfTrain(char* train_file, int samples, float g, float C,
			int num_of_features, char* model);
	void svmRbfSupportVector(int num_of_features,char* model);
	float svmRbfOptimize_C(char* train_file, int samples,int num_of_features, float g);
	float svmRbfOptimize_g(char* train_file, int samples,int num_of_features, float C);
	void neuralNetworkTrain(char* train_file, int samples,int num_of_features, int num_hidden_neuron,char* model);
	void neuralNetworkTest(char* test_file, int samples,int num_of_features, int num_hidden_neuron,char* model);
	int neuralNetworkOptimize_h(char* train_file,int samples,int num_of_features);
	void svmLinearTrain(char* train_file, int samples, float s , float C,int num_of_features,char* model);
	void  svmLinearTest(char* test_file, int samples,float s , float C,int num_of_features,char* model);
	float svmLinearOptimize(char* train_file, int samples, float s  ,int num_of_features);



};

#endif /* RUNCLASSIFIERS_H_ */
