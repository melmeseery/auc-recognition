#pragma once
#include "generalmachine.h"

class OVA_RBF_SVM :
	public GeneralMachine
{
private:
	Allocator* allocator;
	Kernel* kernel;
	SVMClassification **svms;
	int n_inputs;
	int n_outputs;
	float g, C;
	vector<Sequence*>* UniqueSupportVectors;
	vector<int>* SupportVectorsDist;
	int num_of_sv_per_class[10];
	vector<float>* alphas;
	float bs[10];





public:
	OVA_RBF_SVM(){};
	OVA_RBF_SVM(int number_of_inputs, int number_of_outputs, float g, float C);
	~OVA_RBF_SVM();
	void train(DataSet* train_set);
	void forward(Sequence* seq);
	void load(char* file_name);
	void save(char* file_name);
	//void loadXFile(XFile* file);
	//void saveXFile(XFile* file);
	void reset();
	int number_of_support_vectors;
	int number_of_unique_support_vectors;


	
	
};
