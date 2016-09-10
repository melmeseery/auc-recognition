// PCA ANN MAHDBase.cpp : Defines the entry point for the console application.
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



//				Summary of Results (h=120)
// Accuracy(after applying thresholds): 99.49
// Actual Accuracy: 98.18%
// Rejected: 391
// Falsely Rejected: 258
// Recognition Time: 2 CPU second.