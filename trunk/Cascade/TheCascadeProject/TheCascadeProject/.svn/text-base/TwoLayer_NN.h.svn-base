
#ifndef TWOLAYER_NN_CLASS
#define TWOLAYER_NN_CLASS

//#include "Common_Definitions.h"
#include "generalmachine.h"

class TwoLayer_NN: public  GeneralMachine
{
	// options[0] ----> number of iterations

private:
	Allocator* allocator;
	ConnectedMachine* net;
	int n_inputs;
	int n_outputs;
	int n_hidden;

public:
	TwoLayer_NN(){};
	TwoLayer_NN(int number_of_inputs, int number_of_hidden_units, int number_of_outputs);
	~TwoLayer_NN();
	void train(DataSet* train_set);
	void forward(Sequence* seq);
	void load(char* file_name);
	void save(char* file_name);
	void loadXFile(XFile* file);
	void saveXFile(XFile* file);
	void reset();

};


#endif