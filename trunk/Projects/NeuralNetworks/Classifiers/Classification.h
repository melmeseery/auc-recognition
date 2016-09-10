#pragma once
#include "AbstractClassifier.h"
class Classification
{
	public:
	static const int CLASSIFIER_SVM_RBF =0;
	static const int CLASSIFIER_SVM_LINEAR =1;
	static const int CLASSIFIER_lINEAR =2;
	static const int CLASSIFIER_NN =3;
	static const int CLASSIFIER_KNN =4;


private :
int classfier_type ;
int train_type;	
AbstractClassifier*  classifier;
public:
	
	Classification(void);
	void UseClassifier(int classfier);
	void TrainFile(char* filename);
	void TestFile(char* filename);
	AbstractClassifier* getUsedClassfier();
public:
	~Classification(void);
};
