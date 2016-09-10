// Finding Best Variance for Parzen MAHDBase.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	int variance=0.1;
	TEST(variance);

	cout<<"Finished."<<endl;
	getch();

}

//				Results
// Variance= 0.1 ----> Accuracy= 
// Variance= 0.2 ----> Accuracy= 
// Variance= 0.3 ----> Accuracy= 
// Variance= 0.4 ----> Accuracy= 
// Variance= 0.5 ----> Accuracy= 
// Variance= 0.6 ----> Accuracy= 
// Variance= 0.7 ----> Accuracy= 
// Variance= 0.8 ----> Accuracy= 
// Variance= 0.9 ----> Accuracy= 
// Variance= 1.0 ----> Accuracy= 
// Variance= 1.1 ----> Accuracy= 
// Variance= 1.2 ----> Accuracy= 
// Variance= 1.3 ----> Accuracy= 
// Variance= 1.4 ----> Accuracy= 
// Variance= 1.5 ----> Accuracy= 
