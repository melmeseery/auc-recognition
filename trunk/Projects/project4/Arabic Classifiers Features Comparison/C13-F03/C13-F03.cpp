// C13-F03.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
#include "TRAIN.h"
#include "TEST.h"

#include "conio.h"
#include<iostream>
using namespace std;


int _tmain(int argc, _TCHAR* argv[])
//void main()
{
	
	TRAIN();
	TEST();

	cout<<"Finished."<<endl;
	getch();

}
