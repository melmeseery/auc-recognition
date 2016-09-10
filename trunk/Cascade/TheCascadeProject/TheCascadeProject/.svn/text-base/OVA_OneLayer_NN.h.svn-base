/*
 * OVA_OneLayer_NN.h
 *
 *  Created on: Aug 13, 2008
 *      Author: Maha
 */

#ifndef OVA_ONELAYER_NN_H_
#define OVA_ONELAYER_NN_H_

#include "GeneralMachine.h"

class OVA_OneLayer_NN: public GeneralMachine {
	//options[0] ----> number of iterations

	private:
		//Allocator* allocator;
		ConnectedMachine* net;
	//	int n_inputs;
	//	int n_outputs;

	public:
		OVA_OneLayer_NN(){};
		OVA_OneLayer_NN(int number_of_inputs, int number_of_outputs);
		~OVA_OneLayer_NN();
		void train(DataSet* train_set);
		void forward(Sequence* seq);
		void load(char* file_name);
		void save(char* file_name);
		void loadXFile(XFile* file);
		void saveXFile(XFile* file);
		void reset();
};

#endif /* OVA_ONELAYER_NN_H_ */
