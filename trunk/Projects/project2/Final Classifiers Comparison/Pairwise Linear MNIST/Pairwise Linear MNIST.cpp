// Pairwise Linear MNIST.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"


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
// Actual Accuracy: 94.12%
// Accuracy: 99.4457%
// Rejected: 4046
// FR: 3491
// Recognition Time:  5 CPU seconds.