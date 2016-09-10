// KNN3 MAHDBase.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
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



//    Alternative thresholding
// Accuracy(after applying thresholds): 99.4124
// Actual Accuracy: 98.13%
// Rejected: 1150
// Falsely Rejected: 1015
// Recotnition Time:  6743 CPU seconds
