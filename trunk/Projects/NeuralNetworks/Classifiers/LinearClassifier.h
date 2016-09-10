#pragma once
#include "abstractclassifier.h"

class LinearClassifier :
	public AbstractClassifier
{
public:
	LinearClassifier(void);;
	void TRAIN();
	void TEST();
public:
	~LinearClassifier(void);

};
