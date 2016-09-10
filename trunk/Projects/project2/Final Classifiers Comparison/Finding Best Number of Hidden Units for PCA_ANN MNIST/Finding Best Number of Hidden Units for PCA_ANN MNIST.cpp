// Finding Best Number of Hidden Units for PCA_ANN MNIST.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "TRAIN.h"
#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	const int n_hu=340; //number of hidden units
	TRAIN(n_hu);
	TEST(n_hu);

	cout<<"Finished."<<endl;
	getch();

}

//				Results
// Number of hidden units = 20   ----->  Accuracy: 94.38%
// Number of hidden units = 40   ----->  Accuracy: 96.04%
// Number of hidden units = 60   ----->  Accuracy: 96.57%
// Number of hidden units = 80   ----->  Accuracy: 96.96%
// Number of hidden units = 100  ----->  Accuracy: 96.86%
// Number of hidden units = 120  ----->  Accuracy: 97.16%
// Number of hidden units = 140  ----->  Accuracy: 97.15%
// Number of hidden units = 160  ----->  Accuracy: 97.18%
// Number of hidden units = 180  ----->  Accuracy: 97.39%
// Number of hidden units = 200  ----->  Accuracy: 97.37%
// Number of hidden units = 220  ----->  Accuracy: 97.57%
// Number of hidden units = 240  ----->  Accuracy: 97.73%*
// Number of hidden units = 260  ----->  Accuracy: 97.52%
// Number of hidden units = 280  ----->  Accuracy: 97.67%
// Number of hidden units = 300  ----->  Accuracy: 97.57%
// Number of hidden units = 320  ----->  Accuracy: 97.69%
// Number of hidden units = 340  ----->  Accuracy: 97.63%



