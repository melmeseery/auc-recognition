/*
 * TwoClassClassifiers.h
 *
 *  Created on: Jan 7, 2009
 *      Author: Maha
 */

#ifndef TWOCLASSCLASSIFIERS_H_
#define TWOCLASSCLASSIFIERS_H_
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
#define NO_OF_CLASSES 2
#define DIGIT_TRAINED 1

class TwoClassClassifiers {

	static const  int my_digit = 1;
	ofstream fsummary;
	char* ResultString;
	Machine* LoadMachine(int algorithm, char* model,Allocator* allocator,float &bs,Sequence* Ws);
  float GetResultFromMachine(Machine* classifier, Sequence* input,int alg,float bs, Sequence* Ws);


public:
	TwoClassClassifiers();
	virtual ~TwoClassClassifiers();
	void RunTests(char* test_file,char* model,char* note,char* Algorithm,int alg);

	void svmRbfTest(char* filename, int n_features, char* ifilename,float g, float C ,char* note, char* model);
	void svmRbfTrain(char * filename, int n_features, float g, float C, char* model);
	float svmRbfOptimize_C(char* train_file, int samples,int num_of_features,float g);
	float svmRbfOptimize_g(char* train_file, int samples,int num_of_features,float g);
//	void  svmRBFTest(char* filename, int n_features, char* ifilename,float g, float C ,char* note, char* model);
//	void  svmRBFTrain(char * filename, int n_features, float g, float C, char* model);
	void  svmLinearTest(char* filename, int n_features, char* ifilename,float g, float C ,char* note, char* model);
	void  svmLinearTrain(char * filename, int n_features, float g, float C, char* model);

	void neuralTest(char* test_file , int n_hu, char* filesn, int feat,char* note, char* model);
	void neuralTrain(char* train_file, int n_hu, int feat, char* model);
	void  LinearTrain(char* train_file,  int n_features, char* model);
	void  LinearTest(char* test_file,int n_features ,char* note,char* model);



    bool GenerateStateFile;
    bool GenerateScoreFiles;


	int HiddenNeurons;
	float C_variable;
	float g_variable;
	float s_variable;
	float Num_Of_Features;




      void writeResultString(char* note);

void static ConvertToSVMFormat(char* train_file,char* test_file,int  num_of_features);

void static  ConvertToCSVFormat(char* train_file,char* test_file,int  num_of_features);
void static  ConvertToARFFFormat(char* train_file,char* test_file,int  num_of_features);


};

#endif /* TWOCLASSCLASSIFIERS_H_ */
