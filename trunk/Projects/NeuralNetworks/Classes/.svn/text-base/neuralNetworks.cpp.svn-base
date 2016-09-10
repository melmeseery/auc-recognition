//  : Defines the entry point for the console application.
//

// ANN Classifier with Gradient

//#include "TRAIN_SCORES.h"
//#include "TEST_SCORES.h"
#include "TRAIN_DATA_BITS.h"
#include "TEST_DATA_BITS.h"
//#include "TRAIN.h"
//#include "TEST.h"
#include "conio.h"
#include<iostream>
using namespace std;

#define NUM_OF_HIDDEN_NEURONS 100

int main(int argc, char* argv[])
{
//	TRAIN("C:/datasets2/training_set_6.txt", "model", NUM_OF_HIDDEN_NEURONS);
	//TRAIN_SCORES("C:/datasets2/nn_training_set_model_3_rejection.txt", 
    //             "model_nn_training_set_model_1_and_model_3_rejection",
	//			 "C:/datasets2/model_nn_training_set_model_1",
	//             NUM_OF_HIDDEN_NEURONS,
	//			 true);

	//TRAIN_SCORES("C:/datasets2/nn_training_set_model_3_rejection.txt", 
    //             "C:/datasets2/model_nn_training_set_model_1_and_model_3_rejection",
	//			 "C:/datasets2/model_nn_training_set_model_1"
	//             NUM_OF_HIDDEN_NEURONS,
	//			 true);
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
		cout<<" ---------------------End of How to use ---------------------------------------------------- "<<endl;
 
	///cout<<" number of argument is less then required "<<endl;
		cout<<"Will use default settings "<<endl;
	
		cout<<"Train file =  training_set.txt"<<endl;
		cout<<"Test file = testing_set.txt "<<endl;
		cout<<"Mapfile=  files_names.txt "<<endl;
		cout<<"Number of features = 9 features"<<endl;
cout<<"----------------------------------------"<<endl;
		try{
		cout<<"Start the training ."<<endl;
		TRAIN_DATA_BITS("training_set.txt",
						"model_neural",
						NUM_OF_HIDDEN_NEURONS,9);
		cout<<"Finished Training ."<<endl;
	   
		TEST_DATA_BITS("testing_set.txt",
					   "model_neural",
					   NUM_OF_HIDDEN_NEURONS,NULL,9);
	   

		cout<<"Finished."<<endl;

		}
		catch (exception ex){
		
			cout<<"problem found "<<endl;
		
		}
	}

    

	else {

		cout<<"Default settings "<<endl;
		cout<<"If Train file =  training_set.txt"<<endl;
		cout<<"If Test file = testing_set.txt "<<endl;
		cout<<"Mapfile=  files_names.txt "<<endl;
		cout<<"Number of features = 9 features"<<endl;
		cout<<"-----------------------------------------"<<endl;
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
                     
	cout<<"Will change the following settings "<<endl;
	

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
                  cout<<"Test file = "<<testfile<<"   "; 


			  }
			  if (strcmp(argv[i],"-nomap")==0){
        
                   usenames=false;
				   cout<<"  Without using map  ";
				   namesfile=NULL;

			  }

				 if (strcmp(argv[i],"-imagemap")==0){

						 usenames=true;
						 if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
					   namesfile=argv[i+1];
					}}
					   cout<<"  Use map file =  "<<namesfile;

			  }
				 		 if (strcmp(argv[i],"-f")==0){

						 
						 if ((i+1) <argc){
					if (argv[i+1][0]!='-'){
					   numberofFeatures=atoi(argv[i+1]);
					}}
					   cout<<"  Using   "<<numberofFeatures<< " Features  ";

			  }
		  }

               
cout<<endl<<"-----------------------------------"<<endl;
     cout<<endl;
					cout<<endl;
				 if (train){
			
				 
                      	cout<<"Start the training ."<<endl;
						TRAIN_DATA_BITS(trainfile,
										"model_neural",
										NUM_OF_HIDDEN_NEURONS,numberofFeatures);
						cout<<"Finished Training ."<<endl;


				 }
				 if (test){
				 
						cout<<"Start testing "<<endl;
				 		TEST_DATA_BITS("testing_set.txt",
					   "model_neural",
					   NUM_OF_HIDDEN_NEURONS,namesfile,numberofFeatures);
	   

							cout<<"Finished."<<endl;
				 
				 }


			  
		  
		  
		 
		// -test test filename 

		// number of features, 
		//digit. 
	 
	}
	//TEST("C:/datasets2/validation_set_6.txt", "model", NUM_OF_HIDDEN_NEURONS);
	//TEST("C:/datasets/gradproj_ourfeatures_latin_testing_set_full.txt", "model", NUM_OF_HIDDEN_NEURONS);
	//TEST_SCORES("C:/datasets2/nn_testing_set_model_full.txt", 
	//	        "C:/datasets2/model_nn_training_set_model_1_and_model_3_rejection", 
	//			NUM_OF_HIDDEN_NEURONS);

	getch();
}