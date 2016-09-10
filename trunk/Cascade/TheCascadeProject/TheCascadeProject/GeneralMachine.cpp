/*
 * GeneralMachine.cpp
 *
 *  Created on: Aug 13, 2008
 *      Author: Maha
 */
#include "stdafx.h"
#include "GeneralMachine.h"
//#include "Common_Definitions.h"


GeneralMachine::GeneralMachine() {
	// TODO Auto-generated constructor stub
	options= new vector<float>(0);
}

GeneralMachine::~GeneralMachine() {
	delete(options);
}
void GeneralMachine::train(Torch::DataSet *train_set){


}
void GeneralMachine::froward(Torch::Sequence *seq){


}