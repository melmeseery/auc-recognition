/*
 * GeneralMachine.h
 *
 *  Created on: Aug 13, 2008
 *      Author: Maha
 */

#ifndef GENERALMACHINE_H_
#define GENERALMACHINE_H_
#include "Common_Definitions.h"
#include "stdafx.h"
class GeneralMachine  :public Machine{

protected :
	Allocator* allocator;
	int n_inputs;
	int n_outputs;

public:
	vector<float>* options;
	virtual void train(DataSet* train_set);//{};
	virtual void froward(Sequence* seq);//{};
	GeneralMachine();
	~GeneralMachine();
};

#endif /* GENERALMACHINE_H_ */
