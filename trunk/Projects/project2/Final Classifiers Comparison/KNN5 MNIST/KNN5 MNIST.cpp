// KNN5 MNIST.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	TEST();

	cout<<"Finished."<<endl;
	getch();

}

//				Summary of Results
// Accuracy(after applying thresholds): 99.5712%
// Actual Accuracy: 96.88%
// Rejected: 1371
// Falsely Rejected: 1096
// Recotnition Time: 6689 CPU seconds