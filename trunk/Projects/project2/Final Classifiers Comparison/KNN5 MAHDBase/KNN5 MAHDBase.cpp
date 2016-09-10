// KNN3 MNIST.cpp : Defines the entry point for the console application.
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

//				Summary of Results (alternative thresholding)
// Accuracy(after applying thresholds): 99.5483%
// Actual Accuracy: 98.18%
// Rejected: 480
// Falsely Rejected: 346
// Recotnition Time: 6571 CPU seconds

