// PCA Quadratic MAHDBase.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "TRAIN.h"
#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	//TRAIN();
	TEST();

	cout<<"Finished."<<endl;
	getch();

}

//				Summary of Results
// Accuracy(after applying thresholds): 99.4278%
// Actual Accuracy: 97.91%
// Rejected: 2136
// Falsely Rejected: 1971
// Recognition Time: 2 CPU seconds.