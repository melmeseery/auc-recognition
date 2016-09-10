#include "StdAfx.h"
#include "CascadeNode.h"

CascadeNode::CascadeNode(CascadeNode* node)
{
		this->complexity= node->complexity;
		this->thresh= node->thresh;
		this->model= node->model;
		this->n_rejected= n_rejected;
		this->performance_list= node->performance_list;
		this->test_performance_list= node->test_performance_list;

		//this->zeo= node->complexity*n_rejected;

		this->ID= (char*) malloc((strlen(node->ID))*sizeof(char));
		strcpy(this->ID,node->ID);
}

CascadeNode::~CascadeNode(void)
{
}
