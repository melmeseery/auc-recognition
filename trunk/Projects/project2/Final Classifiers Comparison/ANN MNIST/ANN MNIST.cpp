// ANN MNIST.cpp : Defines the entry point for the console application.
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




//				Summary of Results (h=450)
// Accuracy(after applying thresholds): 
// Actual Accuracy: 97.89
// Rejected: 470
// Falsely Rejected: 314
// Recognition Time: 