#pragma once
#include "cascadenode.h"

class ModelBasedCascadeNode :
	public CascadeNode
{
public:
	ModelBasedCascadeNode(void);
public:
	~ModelBasedCascadeNode(void);
	ModelBasedCascadeNode(CascadeNode* node);
		//number of rejected patterns correpsonding to the node threshold
	float n_rejected;

	//a vector of best path nodes
	vector<ModelBasedCascadeNode*> best_path;

	//a vector of best paths of different lengths
	vector<vector<ModelBasedCascadeNode*>*> best_paths_of_different_lengths;

	//the complexity of the best path
	float complexity_of_best_path;

	//a vector that holds the complexities of best paths of different lengths
	vector<float> complexities_of_best_paths_of_different_lengths;

};

//a class function used with sort algorithm to sort a vector of nodes in a descending order according to their rejection rates
class rejections_descend_compare_nodes
{
public:
	bool operator()(const ModelBasedCascadeNode* obj1, const ModelBasedCascadeNode* obj2)
	{return(obj1->n_rejected>obj2->n_rejected);} //descending order
};

//a class function used with sort algorithm to sort a vector of nodes in an ascending order according to their rejection rates
class rejections_ascend_compare_nodes
{
public:
	bool operator()(const ModelBasedCascadeNode* obj1, const ModelBasedCascadeNode* obj2)
	{return(obj1->n_rejected<obj2->n_rejected);} //ascending order
};