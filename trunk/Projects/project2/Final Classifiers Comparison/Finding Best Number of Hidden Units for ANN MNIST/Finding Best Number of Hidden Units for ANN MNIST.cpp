// Finding Best Number of Hidden Units for ANN MNIST.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
#include "TRAIN.h"
#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	const int n_hu=500; //number of hidden units
	TRAIN(n_hu);
	TEST(n_hu);

	cout<<"Finished."<<endl;
	getch();

}

//				Results
// Number of hidden units = 50   ----->  Accuracy: 96.67%
// Number of hidden units = 100  ----->  Accuracy: 97.24%
// Number of hidden units = 150  ----->  Accuracy: 97.78%
// Number of hidden units = 200  ----->  Accuracy: 97.71%
// Number of hidden units = 250  ----->  Accuracy: 97.66%
// Number of hidden units = 300  ----->  Accuracy: 97.75%
// Number of hidden units = 350  ----->  Accuracy: 97.87%
// Number of hidden units = 400  ----->  Accuracy: 97.87%
// Number of hidden units = 450  ----->  Accuracy: 97.91%*
// Number of hidden units = 500  ----->  Accuracy: 97.65%