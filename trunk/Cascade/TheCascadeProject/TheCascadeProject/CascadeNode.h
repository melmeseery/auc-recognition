#pragma once
#include "generalmachine.h"
#include "ClassifiedSample.h"
class CascadeNode
{
public:
	CascadeNode(){};
	CascadeNode(CascadeNode* node);
public:
	~CascadeNode(void);

		//the threshold on the node maximum output ... a pattern having a score lower than this threshold should be rejected
	float thresh;

	//number of rejected patterns correpsonding to the node threshold
	float n_rejected;

	//the time complexity of the node
	float complexity;

	//float zeo;
	
	//a character array may be used to describe the nature of the node
	char* ID;

	//node's performance list
	vector<ClassifiedSample*>* performance_list;
	vector<ClassifiedSample*>* test_performance_list;

	//the node model
	GeneralMachine* model;
};
//a class function used with sort algorithm to sort a vector of nodes in a descending order according to their complexities
class complexity_descend_compare_nodes
{
public:
	bool operator()(const CascadeNode* obj1, const CascadeNode* obj2)
	{return(obj1->complexity > obj2->complexity);} //descending order
};

//a class function used with sort algorithm to sort a vector of nodes in an ascending order according to their complexities
class complexity_ascend_compare_nodes
{
public:
	bool operator()(const CascadeNode* obj1, const CascadeNode* obj2)
	{return(obj1->complexity  <  obj2->complexity);} //ascending order
};

//a class function used with sort algorithm to sort a vector of nodes in a descending order according to their rejections
class rejection_descend_compare_nodes
{
public:
	bool operator()(const CascadeNode* obj1, const CascadeNode* obj2)
	{return(obj1->n_rejected  > obj2->n_rejected);} //descending order
};

//a class function used with sort algorithm to sort a vector of nodes in an ascending order according to their rejections
class rejection_ascend_compare_nodes
{
public:
	bool operator()(const CascadeNode* obj1, const CascadeNode* obj2)
	{return(obj1->n_rejected  <  obj2->n_rejected);} //ascending order
};
