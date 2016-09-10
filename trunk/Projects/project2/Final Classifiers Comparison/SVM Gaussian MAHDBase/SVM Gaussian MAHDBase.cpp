// SVM Gaussian MAHDBase.cpp : Defines the entry point for the console application.
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
// Accuracy(after applying thresholds): 99.4873%
// Actual Accuracy: 98.88%
// Rejected: 247
// Falsely Rejected: 185
// Recognition Time: 1845 CPU seconds.
