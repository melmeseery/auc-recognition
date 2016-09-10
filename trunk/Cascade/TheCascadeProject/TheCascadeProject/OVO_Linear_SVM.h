
#include "generalmachine.h"

#ifndef OVO_Linear_SVM_CLASS
#define OVO_Linear_SVM_CLASS


//#include "Common_Definitions.h"

class OVO_Linear_SVM: public GeneralMachine
{
private:
	Allocator* allocator;
	float *As, *Bs, **Ws, *bs;
	Kernel* kernel;
	SVMClassification ***svms;


public:
	int n_inputs, n_outputs;
	float C;
	void forward(Sequence* sequence);
	//void load(char* file);
	//void save(char* file);
	void loadXFile(XFile* file);
	void saveXFile(XFile* file);
	void train(DataSet* dataset);
	void reset();
	OVO_Linear_SVM(int n, int m, float C_);
	

	~OVO_Linear_SVM();

};




#endif