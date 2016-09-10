// Finding Best Num of Hidden Units for PCA_ANN MAHDBase.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "TRAIN.h"
#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	int n_hu=280; //number of hidden units
	TRAIN(n_hu);
	TEST(n_hu);

	cout<<"Finished."<<endl;
	getch();

}

//				Results
// Number of hidden units = 20   ----->  Accuracy: 98.69%
// Number of hidden units = 40   ----->  Accuracy: 99.04%
// Number of hidden units = 60   ----->  Accuracy: 99.16%
// Number of hidden units = 80   ----->  Accuracy: 99.20%
// Number of hidden units = 100  ----->  Accuracy: 99.11%
// Number of hidden units = 120  ----->  Accuracy: 99.21%*
// Number of hidden units = 140  ----->  Accuracy: 99.15%
// Number of hidden units = 160  ----->  Accuracy: 99.15%
// Number of hidden units = 180  ----->  Accuracy: 99.21%
// Number of hidden units = 200  ----->  Accuracy: 99.14%
// Number of hidden units = 220  ----->  Accuracy: 99.21%
// Number of hidden units = 240  ----->  Accuracy: 99.20%
// Number of hidden units = 260  ----->  Accuracy: 99.22%
// Number of hidden units = 280  ----->  Accuracy: 99.16%
// Number of hidden units = 300  ----->  Accuracy: 99.19%
