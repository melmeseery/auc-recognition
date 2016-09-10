/*
 * RunOVAClassifiers.h
 *
 *  Created on: Jan 12, 2009
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
//#include <Math.h>
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

#ifndef RUNOVACLASSIFIERS_H_
#define RUNOVACLASSIFIERS_H_

class RunOVAClassifiers {
public:
	RunOVAClassifiers();
	virtual ~RunOVAClassifiers();
	void linearTrain(char* train_file, int num_of_features, char* model);
	//linearTest();
	float linearGetThreshold(char* train_file, int num_of_features,int samples, char* model);
	void linearTest(char* test_file,int num_of_features,double threshold, char* model);
	void svmGaussianTrain(char* train_file, int num_of_features,float C,float g, char* model);

	void  svmGaussianTest(char* test_file,int num_of_features,float C,float g,double threshold, char* model);

	void  svmLinearTrain(char* train_file, int num_of_features,float C,float g, char* model);
	void  svmLinearTest(char* test_file,int num_of_features,float C,float g,double threshold, char* model);
   void svmLinearEstimating_Calibrating_Parameters(char* train_file, int num_of_features,int samples,float C,float s);

   void ANNTrain(char* train_file, int num_of_features,int h, char* model);
   void ANNTest(char* test_file,int num_of_features,int n_hu,double threshold, char* model);
   void PcaQuadTrain(char* train_file, int num_of_features, char* model,char* matrix_file,char* mu_file);
   void PcaQuandTest(char* test_file,int num_of_features ,double threshold , char* model,char* matrix_file,char* mu_file);
   void FisherTest(char* test_file, int num_of_features,float threshold , char* wsfile, char* bsfile);

};

#endif /* RUNOVACLASSIFIERS_H_ */
