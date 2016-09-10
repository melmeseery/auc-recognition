// QuickProject.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Train.h"
#include "TEST.h"
#include<iostream>
#include "conio.h"
#include "CmdLine.h"
#include <string>

using namespace std;


int main(int argc, char* argv[])
{
float g=0.03;//gamma
	float C=50;

if (argc<2){

       cout<<" ---------------------How to use ------------------------------------------------------- "<<endl;
	   cout<<" Argumenst  given in no particular order if items between brackets  "<<endl;
	   cout<<"  are not provided the default settings will be used. "<<endl;
       cout<<"   "<<endl;
	   cout<<"  -train [filepath]     do train with dataset found in filepath -- default(training_set.txt)  "<<endl;
	   cout<<"  -test [filepath]      do test with dataset found in filepath -- default(testing_set.txt)  "<<endl;
	   cout<<"  -imagemap [filepath]  map images names from the list given in the file  --default(files_names.txt)      "<<endl;
	   cout<<"  -nomap                no maping is done for images file names "<<endl;
	   cout<<"  -f  [number]          number of features in the data set-- default (9) "<<endl;
	   cout<<"  -c                     change c paramter for svm   default (50)"<<endl;
	   cout<<"-g                     change gamma  paramter for svm  default (0.03)"<<endl;
		cout<<" ---------------------End of How to use ---------------------------------------------------- "<<endl;
 

	///cout<<" number of argument is less then required "<<endl;
		cout<<"Will use default settings "<<endl;
		cout<<"Default settings "<<endl;
		cout<<"Train file =  training_set.txt"<<endl;
		cout<<"Test file = testing_set.txt "<<endl;
		cout<<"Mapfile=  files_names.txt "<<endl;
		cout<<"Number of features = 9 features"<<endl;
	
	cout<<"----------------------------------------------"<<endl;

			/////////////////////SVM
			cout<<"Start reading train..........."<<endl;
				TRAIN("training_set.txt", 9,g,C);
				cout<<"Finished "<<endl;
				cout<<"Start testing ..........."<<endl;
				TEST("testing_set.txt", 9,"files_names.txt",C,g);
				cout<<"Finished";
}
else {

cout<<"Default settings "<<endl;
		cout<<"If Train file =  training_set.txt"<<endl;
		cout<<"If Test file = testing_set.txt "<<endl;
		cout<<"Mapfile=  files_names.txt "<<endl;
		cout<<"Number of features = 9 features"<<endl;
cout<<"----------------------------------------------"<<endl;
cout<<"Reading arguments "<<endl;

		
    /// -tr test.text -test

		//classifer 
		 
       char* trainfile;
	   char* testfile;
	   char* namesfile;
	   trainfile="training_set.txt";
	   testfile="testing_set.txt";
	   namesfile="files_names.txt";
	   int numberofFeatures=9;
	   bool train=false, test=false, usenames=false;
			int j=0;
			int indexfirst,indexlast;
                     
	cout<<"Change the following settings "<<endl;
	

		//find arguments 
		  for (int i =1;i<argc;i++){
			  if (strcmp(argv[i],"-train")==0){
			    train=true;
				if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
						trainfile=argv[i+1];
					}
				}
				 cout<<" Train file =  "<<trainfile<<"  ";
			  }
			  if (strcmp(argv[i],"-test")==0){
				  test=true;
				  if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
				 testfile=argv[i+1];
					}}
                  cout<<" Test file = "<<testfile<<"   "; 


			  }
			  if (strcmp(argv[i],"-nomap")==0){
        
                   usenames=false;
				   cout<<" Withoug using map ";
				   namesfile=NULL;

			  }

				 if (strcmp(argv[i],"-imagemap")==0){

						 usenames=true;
						 if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
					   namesfile=argv[i+1];
					}}
					   cout<<"  Use map from  file "<<namesfile;

			  }
   
			 if (strcmp(argv[i],"-f")==0){

						 
						 if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
					   numberofFeatures=atoi(argv[i+1]);
					}}
					   cout<<"  Using   "<<numberofFeatures<< " Features  ";

			  }



			 		 if (strcmp(argv[i],"-c")==0){

						 
						 if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
					   C=atof(argv[i+1]);
					}}
					   cout<<"  Using   "<<numberofFeatures<< " C  ";

			  }

					 		 if (strcmp(argv[i],"-g")==0){

						 
						 if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
					 g=atof(argv[i+1]);
					}}
					   cout<<"  Using   "<<numberofFeatures<< " G  ";

			  }

		  }

               
cout<<endl<<"-------------------------------------------"<<endl;
     cout<<endl;
					cout<<endl;

					 if (train){
			
				 
                      	cout<<"Start the training ."<<endl;
				

						TRAIN(trainfile,numberofFeatures,g,C);
	

						cout<<"Finished Training ."<<endl;


				 }
				 if (test){
				 
						cout<<"Start testing "<<endl;
				cout<<"Start testing ..........."<<endl;
						TEST(testfile, numberofFeatures,namesfile,g,C);
						cout<<"Finished";
							
				 
				 }
















}
	

/*
//////////////linear classifier 
	cout<<"Start reading train..........."<<endl;
	//TrainLinear("training_set.txt", 9);
	cout<<"Start testing ..........."<<endl;
	//TESTLinear("testing_set.txt", 9);
	cout<<"Finished";
*/


//getFiles();

	return 0;
}

