// C04-F03.cpp : Defines the entry point for the console application.
//


// OVO SVM Classifier with Gradient

#include "stdafx.h"
//#include "Optimize_var.h"
//#include "Optimize_C.h"
//#include "TRAIN.h"
#include "TEST.h"
#include "Unique_Support.h"

#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	
	//Optimize_var();
	//Optimize_C();
	//TRAIN();
	Unique_Support();
	TEST();

	cout<<"Finished."<<endl;
	getch();

}

//				Summary of Results
// Accuracy: 