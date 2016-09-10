#include "StdAfx.h"
#include "Classification.h"

Classification::Classification(void)
{
AbstractClassifier t;
	classfier_type=CLASSIFIER_SVM_RBF ;
 train_type=t.TRAIN_TYPE_OVO;
}

Classification::~Classification(void)
{
}
void Classification::UseClassifier(int classfier){

	this->classfier_type=classfier;
}
	void Classification::TrainFile(char* filename){
	//according to the classifier type create an object of classfier then train it 
	
	}
	void Classification::TestFile(char* filename){
	
		//according to the classifier type create an object of classfier then test it it 
		if ( this->classfier_type==this->CLASSIFIER_NN){
		
		//	this->classifier=new NeuralClassifier();
		//	this->classifier->train_file( filename);
		
		}
	
	
	
	}
		AbstractClassifier* Classification::getUsedClassfier(){
	
	// return the currently used classfiier. 
		return this-> classifier;
	
	
	}