// C03-F13.cpp : Defines the entry point for the console application.
//

// ANN with Raw Image Wavelet


#include "stdafx.h"
//#include "Optimize_h.h"
#include "TRAIN.h"
#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
//void main()
{
	//Optimize_h();
	TRAIN();
	TEST();

	cout<<"Finished."<<endl;
	getch();

}