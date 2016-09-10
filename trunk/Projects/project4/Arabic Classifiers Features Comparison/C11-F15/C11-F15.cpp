// C11-F15.cpp : Defines the entry point for the console application.
//

// OVO Linear SVM Classifier with All Features Concat

#include "stdafx.h"
//#include "Creating_Ensemble_Validation_Results.h"
#include "Optimize_C.h"
//#include "TRAIN.h"
//#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
//void main()
{
	//Create_Ensemble_Validation_Results();
	Optimize_C();
	//TRAIN();
	//TEST();

	cout<<"Finished."<<endl;
	getch();

}

//				Summary of Results
// Accuracy: 