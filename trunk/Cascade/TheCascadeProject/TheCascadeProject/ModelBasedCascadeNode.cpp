#include "StdAfx.h"
#include "ModelBasedCascadeNode.h"

ModelBasedCascadeNode::ModelBasedCascadeNode(void)
{
}

ModelBasedCascadeNode::~ModelBasedCascadeNode(void)
{
}


	ModelBasedCascadeNode::ModelBasedCascadeNode(CascadeNode* node)
	{
		this->complexity= node->complexity;
		this->model= node->model;
		this->performance_list= node->performance_list;
		this->test_performance_list= node->test_performance_list;
		this->thresh= node->thresh;
		this->n_rejected= node->n_rejected;

		this->ID= (char*) malloc((strlen(node->ID))*sizeof(char));
		strcpy(this->ID,node->ID);
		
	}

