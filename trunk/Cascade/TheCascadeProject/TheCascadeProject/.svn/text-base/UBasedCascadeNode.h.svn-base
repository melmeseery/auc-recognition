#pragma once
#include "cascadenode.h"
#include<vector>
#include<algorithm>



class UBasedCascadeNode :
	public CascadeNode
{
public:
	UBasedCascadeNode(void);
public:
	~UBasedCascadeNode(void);
	//number of rejected patterns correpsonding to the node threshold
	float n_rejected;

	int classifier_code;
	int feature_code;

	float uniqueness_score;
	float effeciency_score;
	UBasedCascadeNode::UBasedCascadeNode(int c, int f);
	UBasedCascadeNode::UBasedCascadeNode(CascadeNode* node, int c, int f);
};
//a class function used with sort algorithm to sort a vector of nodes in a descending order according to their rejection rates
class rejections_descend_compare_nodes
{
public:
	bool operator()(const UBasedCascadeNode* obj1, const UBasedCascadeNode* obj2)
	{return(obj1->n_rejected>obj2->n_rejected);} //descending order
};

//a class function used with sort algorithm to sort a vector of nodes in an ascending order according to their rejection rates
class rejections_ascend_compare_nodes
{
public:
	bool operator()(const UBasedCascadeNode* obj1, const UBasedCascadeNode* obj2)
	{return(obj1->n_rejected<obj2->n_rejected);} //ascending order
};