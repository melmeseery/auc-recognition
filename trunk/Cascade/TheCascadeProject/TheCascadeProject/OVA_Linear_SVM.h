/*
 * OVA_Linear_SVM.h
 *
 *  Created on: Aug 13, 2008
 *      Author: Maha
 */

#ifndef OVA_LINEAR_SVM_H_
#define OVA_LINEAR_SVM_H_

#include "GeneralMachine.h"

class OVA_Linear_SVM: public GeneralMachine {


private:
	//Allocator* allocator;
	Kernel* kernel;
	SVMClassification **svms;
	//int n_inputs;
	//int n_outputs;
	int C;
	float **Ws;
	float *bs;


public:
	OVA_Linear_SVM(){};
	OVA_Linear_SVM(int number_of_inputs, int number_of_outputs, float C);
	virtual ~OVA_Linear_SVM();
	void train(DataSet* train_set);
	void forward(Sequence* seq);
	//void load(char* file_name);
	//void save(char* file_name);
	void loadXFile(XFile* file);
	void saveXFile(XFile* file);
	void reset();


};

#endif /* OVA_LINEAR_SVM_H_ */
