// SVM Gaussian MNIST.cpp : Defines the entry point for the console application.
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
// Accuracy(after applying thresholds): 99.4828%
// Actual Accuracy: 98.62%
// Rejected: 333
// Falsely Rejected: 245
// Recognition Time: 