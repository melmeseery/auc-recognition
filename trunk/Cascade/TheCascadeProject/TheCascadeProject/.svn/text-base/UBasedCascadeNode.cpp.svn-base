#include "StdAfx.h"
#include "UBasedCascadeNode.h"

UBasedCascadeNode::UBasedCascadeNode(void)
{
}

UBasedCascadeNode::~UBasedCascadeNode(void)
{
}
	UBasedCascadeNode::UBasedCascadeNode(int c, int f)
	{
		classifier_code=c;
		feature_code=f;

		uniqueness_score=0;
		effeciency_score=0;
	}

	UBasedCascadeNode::UBasedCascadeNode(CascadeNode* node, int c, int f)
	{
		this->complexity= node->complexity;
		this->ID= node->ID;
		this->model= node->model;

		classifier_code=c;
		feature_code=f;

		uniqueness_score=0;
		effeciency_score=0;

	}