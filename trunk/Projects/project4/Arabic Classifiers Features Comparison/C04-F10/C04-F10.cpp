// C04-F10.cpp : Defines the entry point for the console application.
//

// OVO SVM Classifier with local concavity

#include "stdafx.h"
//#include "Optimize_var.h"
//#include "Optimize_C.h"
//#include "Creating_Ensemble_Validation_Results.h"
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
	//Create_Ensemble_Validation_Results();
	//TRAIN();
	Unique_Support();
	TEST();

	cout<<"Finished."<<endl;
	getch();

}

//				Summary of Results
// Accuracy: 