#pragma once
#include "StdAfx.h"
#include "abstractclassifier.h"



class SVMClassifier :
	public AbstractClassifier
{
public:

	SVMClassifier(void);
		void TRAIN();
	void TEST();

public:
	~SVMClassifier(void);
};
