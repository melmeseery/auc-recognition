#ifndef OVO_RBF_SVM_CLASS
#define OVO_RBF_SVM_CLASS

#include "Common_Definitions.h"
#include "generalmachine.h"
class OVO_RBF_SVM: public GeneralMachine
{
private:
	Allocator* allocator;
	float As[45];
	float Bs[45];
	Kernel* kernel;
	SVMClassification ***svms;
	vector<Sequence*>* UniqueSupportVectors;
	vector<int>* SupportVectorsDist;
	vector<float>* alphas;
	int num_of_sv_per_pair[45];
	float bs[45];


public:
	int n_inputs, n_outputs;
	float g, C;
	int number_of_support_vectors;
	int number_of_unique_support_vectors;
	void forward(Sequence* sequence);
	void load(char* file);
	void save(char* file);
	void loadXFile(XFile* file);
	void saveXFile(XFile* file);
	void train(DataSet* dataset);
	//void calibrate(DataSet* dataset);
	void reset();
	OVO_RBF_SVM(int n, int m, float g_, float C_);

	~OVO_RBF_SVM();

};




#endif