
#ifndef PCA_QUAD_CLASS
#define PCA_QUAD_CLASS

//#include "Common_Definitions.h"
#include "generalmachine.h"

class PCA_Quad: public  GeneralMachine
{
	//options[0] ----> number of iterations

private:
	Allocator* allocator;
	ConnectedMachine* net;
	int n_inputs;
	int n_outputs;
	int n_pca_components;
	int n_neural_inputs;
	float **pca_matrix;
	float *pca_mu;

public:
	PCA_Quad(){};
	PCA_Quad(int number_of_inputs, int number_of_pc_components, int number_of_outputs, char* pca_matrix_file, char* pca_mu_file);
	~PCA_Quad();
	void train(DataSet* train_set);
	void forward(Sequence* seq);
	void load(char* file_name);
	void save(char* file_name);
	void loadXFile(XFile* file);
	void saveXFile(XFile* file);
	void reset();
	

};



#endif