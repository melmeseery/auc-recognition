#pragma once
#include "CascadeNode.h"
#include <vector>
#include "Common_Definitions.h"
#include "ModelBasedCascadeNode.h"
#include "GeneralMachine.h"
#include "OVA_OneLayer_NN.h"
#include "OVO_RBF_SVM.h"
#include "OVA_RBF_SVM.h"
#include "TwoLayer_NN.h"
class utils
{
public:
	utils(void);
public:
	~utils(void);
	void static Select_Subpools_From_Pool(char* file_name, vector<CascadeNode*>& pool,
							   vector<vector<CascadeNode*>*>& subpools );
	void static Select_Best_ModelBased_Cascade(
							  vector<CascadeNode*>& _all_nodes, //from which we build the best cascade
							  vector<CascadeNode*>& best_cascade,
							  float& best_cascade_theoretical_complexity
							  );

	static  DataSet*  Create_Living_Set(vector<CascadeNode*>& cascade, Allocator* allocator);

	void static  MNIST_Generate_CascadeNodes_Vector(vector<CascadeNode*>& vec);
};
