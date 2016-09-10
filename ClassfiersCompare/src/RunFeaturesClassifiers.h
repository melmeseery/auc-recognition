/*
 * RunFeaturesClassifiers.h
 *
 *  Created on: Feb 3, 2009
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
#ifndef RUNFEATURESCLASSIFIERS_H_
#define RUNFEATURESCLASSIFIERS_H_

class RunFeaturesClassifiers {

private :
	 int*** FeaturesIndex;
	 int** OVAFeaturesIndex;
	 int ** FeatCountMat;
	 int*   OVAFeatCountArray;

	 int classifiersAccuracyForTheirDigits[10][10];
	 int classifiersAccuracy[10][10];

	 int ClassifiersErrors[10][10];
	 int DigitsAccuracy[10];

	 int classifiedWrongError;
	 int classifiedWrongNoProblem;

	 ofstream fsummary;
	 ofstream 	 fscores;
	 Allocator *allocator ;
		Machine ***machines ;
		Trainer ***trainers;
		float** bs;
		Sequence* Ws;

       int classes;
       float GetResultFromMachinesOVO(Sequence* input,int target,int sample, int alg,float*  votes);
       float GetResultFromMachinesOVA(Sequence* input, int alg,float*  votes);

       float GetResultFromPredictMachineOVO(Sequence* input, int alg,Machine* classifier );
       float GetResultFromPredictMachineOVA(Sequence* input, int alg,Machine* classifier );

   	void LoadMachineForTrainOVO( int alg);
   	void LoadMachineForTestOVO( int alg,char* model);


   	void LoadMachineForTrainOVA( int alg);
   	void LoadMachineForTestOVA(  int alg,
   			char* model);



   	void GeneratePerdictFilesOVO(DataSet* test_data, int alg);
   	void GeneratePerdictFilesOVA(DataSet* test_data, int alg);

   	MemoryDataSet *  GetCurretDataSetOVO(int i,int j, DataSet* train_set );
 	MemoryDataSet *  GetCurretDataSetOVA(int i,int j, DataSet* train_set );

 	float ForwardOVOInput(Sequence* input, int alg, int i, int j , int count );

 	void freeTrainMachinesOVO();
 	void freeTestMachineOVO();

public:
	float Variable_g;
	bool useDifferentFeatures;
bool GeneratePredictFiles;
bool 	WriteScores;
	float  Var_C;
	float Variable_s;
	int HiddenNeurons;
	 int num_of_features;

	RunFeaturesClassifiers();
	virtual ~RunFeaturesClassifiers();

	void ProcessFeaturesIndex(char* filename);

	void TrainMachineOVO(char* train_file, char* model,
			int alg);
	void TrainMachineOVA(char* train_file, char* model,
			int alg);

	void RunTestsOVO(char* test_file, char* model,
			char* Algorithm, int alg);

	void RunTestsOVA(char* test_file, char* model,
			char* Algorithm, int alg);



};

#endif /* RUNFEATURESCLASSIFIERS_H_ */
