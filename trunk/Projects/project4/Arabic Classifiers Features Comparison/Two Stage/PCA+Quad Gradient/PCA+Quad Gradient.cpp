// PCA+Quad Gradient.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<conio.h>
#include<iostream>
#include "TRAIN.h"
//#include "Two_Stage_Validate.h"
#include "Two_Stage_Test.h"
using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	//TRAIN();
	//Two_Stage_Validate();
	Two_Stage_Test();

	cout<<"Done."<<endl;
	getch();

	return 0;
}
