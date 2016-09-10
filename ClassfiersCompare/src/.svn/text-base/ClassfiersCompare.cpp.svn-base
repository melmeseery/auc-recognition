//============================================================================
// Name        : ClassfiersCompare.cpp
// Author      : Maha
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================


#include "RunClassifiers.h"
#include "TwoClassClassifiers.h"
#include "RunOVAClassifiers.h"
#include "RunFeaturesClassifiers.h"
#include<iostream>

#include "CmdLine.h"
#include <string>
using namespace std;

int SampleCount = 0;
//int featcount=200;
float g_d = 0.01;//gamma
float s_d = 1;//gamma
float C_d = 100;
int h_d = 70; //number of hidden layer neural network.
char* trainfile = "training_set.txt";
char* testfile = "testing_set.txt";
char* namesfile = NULL;//"files_names.txt";
char* modelfile=NULL;
char* wsfile="ws.txt";
char* bsfile="bs.txt";
char* pcaMat="pca_mat.txt";
char* pcaMu="pca_mu.txt";

char*  note="";
int numberofFeatures = 9;
int Algorithm = 1; /// 1= svm  linear, fisher,gc, svmrbf,nn, 2nn, knn,
float threshold_d = 0.2;

bool train = false, test = false, usenames = false, optimize = false,
		UseTwoClass = false;

bool RunAll=false;
bool FormateInputs=false;
bool GeneartePredict=false;
bool USeDifferentFeatures=false;
bool UseOVO=true;
bool UseOVA=false;
int GenerateStateFile=1;
int GenerateScoreFiles=0;

int formattype=1;
bool getRejectionRate = false;
//		   trainfile="training_set.txt";
//		   testfile=;
//		   namesfile=;
int getFeatureCount(char* filename) {
	ifstream inFile;
	inFile.open(filename);
	if (!inFile) {
		cout << "Unable to open file   " << filename << endl;
		exit(1); // terminate with error
	}
	char x[500];

	cout << "opening file " << filename << endl;

	int i = 0;
	if (inFile >> SampleCount) {
		cout << "  number of sampels  " << SampleCount << endl;

	}
	int nCount = 0;
	if (inFile >> nCount) {

		cout << "   feature count " << nCount - 1 << endl;

	}
	inFile.close();
	//cout<<"Size of file "<<size<<endl;
	// cout << "Sum = " << sum << endl;
	return (nCount - 1);
}

void DisplayDefaultMessage() {

	cout<< " ---------------------How to use ------------------------------------------------------- "	<< endl;
	cout<< " Argumenst  given in no particular order if items between brackets  "	<< endl;
	cout << "  are not provided the default settings will be used. " << endl;
	cout << "                                                      " << endl;
	cout<<"-train    [filepath]  do train with dataset found in filepath"<<endl<<"            -- default(training_set.txt)  "<< endl;
	cout<<"-test     [filepath]  do test with dataset found in filepath"<<endl<<"            -- default(testing_set.txt)  "
			<< endl;
	cout<<"-imagemap [filepath]  map images names from the list given in the file or the features files for featur classifiers"<<endl<<"        --default(files_names.txt)      "<< endl;
	cout <<"-nomap               no maping is done for images file names "<< endl;
	cout <<"-f       [number]    number of features in the data set-- default (9) "<< endl;
	cout <<"-c       [number]    change c paramter for svm   default (200)"<< endl;
	cout <<"-g       [number]    change gamma  paramter for svm  default (0.1)"<< endl;
	cout <<"-s       [number]    change s  paramter for linear  svm  default (1)"<< endl;
	cout <<"-h       [number]    change hidden layer paramter for neural network  default (50)"<< endl;
	cout <<"-alg     [string]    change the algorithm used from "<<endl<<"         (svm, linear, fisher,gc, svmrbf,nn, 2nn, knn,) "<< endl;
	cout <<"-a       [number]    change the algorithm used from  ( 1 ,2,3,5,6 ...)"<< endl;

  	cout <<"-t       [number]    threshold used in rejection rate programs)"<< endl;
	cout <<"-th      [number]    threshold used in rejection rate programs)"<< endl;
	cout <<"-r                   use get Rejection Rate functions " << endl;
	cout<<"-class    (number)    if ( 2 ) means used the two class problem functions "<< endl;
	cout <<"-O                   optimize the paramteres on the run time . " << endl;
	cout<<"-model    [filepath]  the path and filename of model file       "<<endl;
	cout<<"-ws       [filepath]  the path and filename  of ws file(fisher)      "<<endl;
	cout<<"-bs       [filepath]  the path and filename  of bs file  (fisher)        "<<endl;
	cout<<"-pcamat   [filepath]  the path and filename of pcamat file (PCA)            "<<endl;
	cout<<"-pcamu    [filepath]  the path and filename of  pcamu file   (PCA)         "<<endl;
	cout<<"-all                  use the train and test date to run against all clasiiferis          "<<endl;
	cout <<"-n       [string]    note to add to result text "<<endl;
	cout<<"-formate              formate the txt files to the svmligth format. "<<endl;
	cout<<"-flages    [number][number] number to set the files write flags "<<endl;
	cout<<"-perdict              generate the perdict files  "<<endl;
	cout<<"-diffeat              use differnet features    "<<endl;
	cout<<"-ovo                  use OVO files     "<<endl;
	cout<<"-ova                  use OVa files    "<<endl;


	cout<< "--------------------------------------------End of How to use ---------------------------------------------------- "
			<< endl;
	cout << endl;
	cout << endl;
	cout
			<< "IT IS RECOMENDED TO CHANGE THE C and G OPTION TO OPTAIN BETTER RESULT SSSSS "
			<< endl;
	cout << endl;
	cout << endl;
	cout << "****************************************************" << endl;
	///cout<<" number of argument is less then required "<<endl;
	cout << "Will use default settings " << endl;
	cout << "Default settings " << endl;
	cout << "Train file =  training_set.txt" << endl;
	cout << "Test file = testing_set.txt " << endl;
	cout << "Mapfile=  files_names.txt " << endl;
	cout << "Number of features = 200 features" << endl;

	cout << "----------------------------------------------" << endl;

	/////////////////////SVM
	//	cout<<"Start reading train..........."<<endl;
	//	TRAIN("training_set.txt", 9,g,C);
	//	cout<<"Finished "<<endl;
	//	cout<<"Start testing ..........."<<endl;
	//	TEST("testing_set.txt", 9,"files_names.txt",C,g);
	//	cout<<"Finished";
	//		cout<<"****************************************************"<<endl;


}
void getInputFromUser(int argc, char* argv[]) {

	cout << "Default settings " << endl;
//	cout << "If Train file =  training_set.txt" << endl;
//	cout << "If Test file = testing_set.txt " << endl;
//	cout << "Mapfile=  files_names.txt " << endl;
//	cout << "Number of features = 9 features" << endl;
	cout << "----------------------------------------------" << endl;
	cout << "Reading arguments " << endl;

	/// -tr test.text -test

	//classifer


	int j = 0;
	int indexfirst, indexlast;

	cout << "Change the following settings " << endl;

	//find arguments
	for (int i = 1; i < argc; i++) {
		if (strcmp(argv[i], "-train") == 0) {
			train = true;
			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					trainfile = argv[i + 1];
				}
			}
			cout << " Train file =  " << trainfile <<endl;
		}


		if (strcmp(argv[i], "-test") == 0) {
			test = true;
			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					testfile = argv[i + 1];
				}
			}
			cout << " Test file = " << testfile << "   ";

		}
		if (strcmp(argv[i], "-r") == 0) {

			getRejectionRate = true;
			cout << " use get Rejection Rate  function"<<endl;
			namesfile = NULL;

		}
		if (strcmp(argv[i], "-nomap") == 0) {

			usenames = false;
			cout << " Withoug using map "<<endl;
			namesfile = NULL;

		}
		if (strcmp(argv[i], "-imagemap") == 0) {

			usenames = true;
			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					namesfile = argv[i + 1];
				}
			}
			cout << "  Use map from  file " << namesfile<<endl;

		}

		if (strcmp(argv[i], "-f") == 0) {

			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					numberofFeatures = atoi(argv[i + 1]);
				}
			}
			cout << "  Using   " << numberofFeatures << " Features  ";

		}


		if (strcmp(argv[i], "-flags") == 0) {

						if ((i + 1) < argc) {
										if (argv[i + 1][0] != '-') {
											GenerateStateFile = atoi(argv[i + 1]);
										}
						}
						if ((i+2)<argc){
							if (argv[i + 1][0] != '-') {
								GenerateScoreFiles = atoi(argv[i + 1]);
													}

						}
						cout << " flags for the files  state file  " <<GenerateStateFile<< "  score files  "<<GenerateScoreFiles <<endl;

					}


		if (strcmp(argv[i], "-formate") == 0) {

					FormateInputs = true;
					if ((i + 1) < argc) {
									if (argv[i + 1][0] != '-') {
										formattype = atoi(argv[i + 1]);
									}
					}
					cout << " Formate inputs only " << FormateInputs << " and with format "<<formattype<<endl;

				}

		if (strcmp(argv[i], "-c") == 0) {

			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					C_d = atof(argv[i + 1]);
				}
			}
			cout << "  Using  C= " << C_d <<endl;

		}
		if (strcmp(argv[i], "-s") == 0) {

				if ((i + 1) < argc) {
					if (argv[i + 1][0] != '-') {
						s_d = atof(argv[i + 1]);
					}
				}
				cout << "  Using  s= " << s_d <<endl;

			}
		if (strcmp(argv[i], "-g") == 0) {

			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					g_d = atof(argv[i + 1]);
				}
			}
			cout << "  Using  g= " << g_d <<endl;

		}
		if (strcmp(argv[i], "-all") == 0) {
			RunAll=true;
			cout<<" Run all classifiers"<<endl;

		}
		if (strcmp(argv[i], "-alg") == 0) {
			char* algs;
			//svm, linear, fisher,gc, svmrbf,nn, 2nn, knn,
			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					algs = argv[i + 1];
					if (strcmp(algs, "svm")) {
						Algorithm = 1;
					} else if (strcmp(algs, "linear")) {
						Algorithm = 2;
					} else if (strcmp(algs, "fisher")) {
						Algorithm = 3;
					} else if (strcmp(algs, "gc")) {
						Algorithm = 4;
					} else if (strcmp(algs, "svmrbf")) {
						Algorithm = 5;
					} else if (strcmp(algs, "nn")) {
						Algorithm = 6;
					} else if (strcmp(algs, "knn")) {
						Algorithm = 7;
					}
				}
			}
			cout << "  algorithm =  " << algs <<endl;
		}
		if (strcmp(argv[i], "-a") == 0) {
			char* algs;
			//svm, linear, fisher,gc, svmrbf,nn, 2nn, knn,
			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					Algorithm = atoi(argv[i + 1]);

				}
			}
			cout << "  algorithm =  " << Algorithm <<endl;
								switch(Algorithm) {
								case 1:
									cout<<" Algoirthm is svm linear "<<endl;
									break;
								case 2:
																cout<<" Algoirthm is linear "<<endl;
																break;
								case 3:
																cout<<" Algoirthm is fisher "<<endl;
																break;
								case 4:
																cout<<" Algoirthm is GC   "<<endl;
																break;
								case 5:
																cout<<" Algoirthm is  svm rbf "<<endl;
																break;
								case 6:
																cout<<" Algoirthm is NN  "<<endl;
																break;
								case 7:
																cout<<" Algoirthm is PCA "<<endl;
																break;
								}

		}
		if (strcmp(argv[i], "-h") == 0) {

			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					h_d = atof(argv[i + 1]);
				}
			}
			cout << "  Using   h = " << h_d <<endl;

		}

		if (strcmp(argv[i], "-O") == 0) {
			optimize = true;

			cout << "  Optimize parameters = " << optimize <<endl;

		}


		if (strcmp(argv[i], "-perdict") == 0) {

             GeneartePredict=true;

			cout << "   perdict = " << GeneartePredict <<endl;

		}
		if (strcmp(argv[i], "-diffeat") == 0) {

	            USeDifferentFeatures=true;

				cout << "   USeDifferentFeatures  = " <<  USeDifferentFeatures<<endl;

			}


		if (strcmp(argv[i], "-ova") == 0) {

		            UseOVA=true;

					cout << "  UseOVA= " <<  UseOVA <<endl;

				}
		if (strcmp(argv[i], "-ovo") == 0) {

		            UseOVO=true;

					cout << "   UseOVO = " <<  UseOVO <<endl;

				}

		if (strcmp(argv[i], "-o") == 0) {
			optimize = true;

			cout << "  Optimize parameters = " << optimize <<endl;

		}
		if (strcmp(argv[i], "-class") == 0) {

			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					int tem = atof(argv[i + 1]);
					if (tem == 2) {
						UseTwoClass = true;
					}
				} else {
					UseTwoClass = true;
				}
			}
			cout << "  Using   two class code = " << UseTwoClass <<endl;

		}

		if (strcmp(argv[i], "-t") == 0) {

			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					threshold_d = atof(argv[i + 1]);

				}

			}
			cout << "  threshold is  = " << threshold_d <<endl;

		}
		if (strcmp(argv[i], "-th") == 0) {

			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					threshold_d = atof(argv[i + 1]);

				}

			}
			cout << "  threshold is  = " << threshold_d <<endl;

		}
		//char* modelfile=NULL;
//		char* wsfile="ws.txt";
//		char* bsfile="bs.txt";

		if (strcmp(argv[i], "-model") == 0) {
			test = true;
			if ((i + 1) < argc) {
				if (argv[i + 1][0] != '-') {
					modelfile = argv[i + 1];
				}
			}
			cout << " model file = " << modelfile <<endl;

		}
		if (strcmp(argv[i], "-ws") == 0) {
				test = true;
				if ((i + 1) < argc) {
					if (argv[i + 1][0] != '-') {
						wsfile = argv[i + 1];
					}
				}
				cout << " wsfile = " << wsfile<<endl;

			}
		if (strcmp(argv[i], "-bs") == 0) {
				test = true;
				if ((i + 1) < argc) {
					if (argv[i + 1][0] != '-') {
						bsfile = argv[i + 1];
					}
				}
				cout << " wsfile = " << wsfile<<endl;

			}
		//		char* pcaMat="pca_mat.txt";
		//		char* pcaMu="pca_mu.txt";
		if (strcmp(argv[i], "-pcamat") == 0) {
				test = true;
				if ((i + 1) < argc) {
					if (argv[i + 1][0] != '-') {
						pcaMat= argv[i + 1];
					}
				}
				cout << "pcaMat = " << pcaMat<<endl;

			}
		if (strcmp(argv[i], "-pcamu") == 0) {
				test = true;
				if ((i + 1) < argc) {
					if (argv[i + 1][0] != '-') {
						pcaMu= argv[i + 1];
					}
				}
				cout << " pca Mu = " << pcaMu<<endl;

			}


		if (strcmp(argv[i], "-n") == 0) {
				//train = true;
				if ((i + 1) < argc) {
					if (argv[i + 1][0] != '-') {
						note = argv[i + 1];
					}
				}
				cout << "  Note =  " << note <<endl;
			}



	}

}

void TEST(char* filename, int n_features, char* ifilename, float g, float C,
		int h) {
	// svm  linear, fisher,gc, svmrbf,nn, 2nn, knn,

	numberofFeatures = getFeatureCount(filename);

	RunClassifiersOVO cl;
	TwoClassClassifiers cl2;

	if (GenerateStateFile==1)
		cl2.GenerateStateFile =  true;
					 else
							cl2.GenerateStateFile =  false;


					 if(GenerateScoreFiles==1)
							cl2.GenerateScoreFiles =  true;
									 else
							cl2.GenerateScoreFiles =  false;
						cl2.C_variable=C;

								cl2.Num_Of_Features=numberofFeatures;
								cl2.s_variable=s_d;
								cl2.g_variable=g;
								cl2.HiddenNeurons=h;
	RunOVAClassifiers cl3;

	switch (Algorithm) {
	case 1:
		if (UseTwoClass) {
			cl2. RunTests(filename,modelfile,note,"_SVM_Linear_",1);
			//cl2.svmLinearTest(filename, n_features, ifilename, g, C,note,modelfile);

		} else { // int samples,float s , float C,int num_of_features
			cl.svmLinearTest(filename, SampleCount, g, C, numberofFeatures,modelfile);
		}

		break;
	case 2:
		if (UseTwoClass) {
			cl2. RunTests(filename,modelfile,note,"_Linear_",2);
			//cl2.LinearTest(filename,numberofFeatures,note,modelfile);
		} else {
			cl.LinearTest(filename, SampleCount, numberofFeatures,modelfile);
		}
		break;
	case 3:
		if (UseTwoClass) {
			} else {
		cl.FisherTest(filename, numberofFeatures,wsfile,bsfile);
			}
		break;
	case 4:
		break;
	case 5:
		if (UseTwoClass) {

			cl2. RunTests(filename,modelfile,note,"_SVM_RBF_",5);

			//cl2.svmRbfTest(filename, n_features, ifilename, g, C,note,modelfile);

			} else {
		// svmrbf
		//char* train_file, int samples, float g , float C,  int num_of_features
		cl.svmRbfTest(filename, SampleCount, g, C, numberofFeatures,modelfile);
		}
		break;
	case 6:
		if (UseTwoClass) {
			namesfile = NULL;
			cl2. RunTests(filename,modelfile,note,"_Neural_",6);
			//cl2.neuralTest(filename, h, namesfile, numberofFeatures,note,modelfile);
		} else {

			cl.neuralNetworkTest(filename, SampleCount, numberofFeatures, h,modelfile);
		}
		break;
	case 7:
		break;

	}

}
void TRAIN(char* filename, int n_features, float g, float C, int h) {
	// svm  linear, fisher,gc, svmrbf,nn, 2nn, knn,
	numberofFeatures = getFeatureCount(filename);

	RunClassifiersOVO cl;
	TwoClassClassifiers cl2;


	switch (Algorithm) {
	case 1:
		if (UseTwoClass) {




			cl2.svmLinearTrain(filename, n_features, s_d, C,modelfile);
		} else {
			if (optimize) {
				C = cl.svmLinearOptimize(filename, SampleCount, g,
						numberofFeatures);
			}
			cl. svmLinearTrain(filename, SampleCount, g, C, numberofFeatures,modelfile);
		}
		//svmLinearTest
		break;
	case 2:
		if (UseTwoClass) {
			cl2.LinearTrain(filename,numberofFeatures,modelfile);

		} else {

			cl.LinearTrain(filename, SampleCount, numberofFeatures,modelfile);
		}
		break;
	case 3:
		// no train in c ...
		break;
	case 4:

		break;
	case 5: // svmrbf
		if (UseTwoClass) {

			if (optimize) {

				C = cl2.svmRbfOptimize_C(filename, SampleCount, numberofFeatures, g);
				g = cl2.svmRbfOptimize_g(filename, SampleCount, numberofFeatures, C);
			}
			cl2.svmRbfTrain(filename, n_features, g, C,modelfile);
			} else {
		if (optimize) {
			C = cl.svmRbfOptimize_C(filename, SampleCount, numberofFeatures, g);
			g = cl.svmRbfOptimize_g(filename, SampleCount, numberofFeatures, C);
		}
		//char* train_file, int samples, float g , float C,  int num_of_features
		cl.svmRbfTrain(filename, SampleCount, g, C, numberofFeatures,modelfile);
		cl.svmRbfSupportVector(numberofFeatures,modelfile);
			}
		break;
	case 6:
		if (UseTwoClass) {
			cl2.neuralTrain(filename, h, numberofFeatures,modelfile);
		} else {
			if (optimize) {
				h = cl.neuralNetworkOptimize_h(filename, SampleCount,
						numberofFeatures);
			}
			cl.neuralNetworkTrain(filename, SampleCount, numberofFeatures, h,modelfile);
		}
		break;
	case 7:

		break;
	}

}
void TestRR(char* filename, int n_features, char* ifilename, float g, float C,
		int h, float threshold) {
	//RunOVAClassifiers cl3;
	// svm  linear, fisher,gc, svmrbf,nn, 2nn, knn,
	RunOVAClassifiers cl3;
	numberofFeatures = getFeatureCount(filename);

	switch (Algorithm) {
	case 1:
		cl3.svmLinearTest(filename, numberofFeatures, C, g, threshold,modelfile);
		break;
	case 2:
		cout << "----------------testing the system " << endl;
		cl3.linearTest(filename, numberofFeatures, threshold,modelfile);
		cout << "---------------- finished " << endl;
		break;
	case 3:
		cl3.FisherTest(filename, numberofFeatures,threshold,wsfile,bsfile);
		break;
	case 5:
		cl3.svmGaussianTest(filename, numberofFeatures, C, g, threshold,modelfile);

		break;
	case 6:
		cl3.ANNTest(filename, numberofFeatures, h, threshold,modelfile);
		break;
	case 7:
		cl3.PcaQuandTest(filename, numberofFeatures, threshold,modelfile, pcaMat, pcaMu);
		break;

	}
}
void TrainRR(char* filename, int n_features, float g, float C, int h) {
	//RunOVAClassifiers cl3;

	// svm  linear, fisher,gc, svmrbf,nn, 2nn, pca,
	RunOVAClassifiers cl3;
	numberofFeatures = getFeatureCount(filename);
	switch (Algorithm) {
	case 1:
		cl3.svmLinearTrain(filename, numberofFeatures, C, g,modelfile);
		cl3.svmLinearEstimating_Calibrating_Parameters(filename,
				numberofFeatures, SampleCount, C, g);

		break;
	case 2:
		cout << "----------------training the system   " << endl;
		cl3.linearTrain(filename, numberofFeatures,modelfile);
		cout << "----------------finished " << endl;
		break;
	case 5:
		cl3.svmGaussianTrain(filename, numberofFeatures, C, g,modelfile);

		break;

	case 6:
		cl3.ANNTrain(filename, numberofFeatures, h,modelfile);
		break;
	case 7:
		cl3.PcaQuadTrain(filename, numberofFeatures,modelfile, pcaMat, pcaMu);
		break;

	}

	//		cout<<"----------------training the system   "<<endl;
	//		cl.linearTrain("train.txt",200);
	//		cout<<"----------------finished "<<endl;
	//		cout<<"----------------testing the system "<<endl;
	//		cl.linearTest("test.txt",200,1.0);
	//		cout<<"---------------- finished "<<endl;
	//


	//	cout << "Hello World!!!" << endl; // prints Hello World!!!
}


void TrainFeatures(char* filename,char* filename2,float g, float C,float s,
		int h ) {

	numberofFeatures = getFeatureCount(filename);


		RunFeaturesClassifiers cl2;


								cl2.Var_C=C;

										cl2.num_of_features=numberofFeatures;
										cl2.Variable_s=s_d;
										cl2.Variable_g=g;
										cl2.HiddenNeurons=h;
										cl2.GeneratePredictFiles=GeneartePredict;

										if (filename2!=NULL){
											cout<<"*****filename =   *****************"<<filename2<<endl;
											cout<<"*************************"<<endl;
											cl2.ProcessFeaturesIndex(filename2);

										}



			switch (Algorithm) {
			case 1:
				if (UseOVO){
					cl2. TrainMachineOVO(filename,modelfile,1);
					//cl2.svmLinearTest(filename, n_features, ifilename, g, C,note,modelfile);
				}else if(UseOVA){
					cl2.TrainMachineOVA(filename,modelfile,1);
				}


				break;
			case 2:
				if (UseOVO){
				cl2.TrainMachineOVO(filename,modelfile,2);
			}else if(UseOVA){
				cl2.TrainMachineOVA(filename,modelfile,2);
					}
				break;
			case 3:

				break;
			case 4:
				break;
			case 5:
				if (UseOVO){
					cout<<" will train the ovo with svm rbf. "<<endl;
				cl2.TrainMachineOVO(filename,modelfile,5);
				}else if(UseOVA){
					cl2.TrainMachineOVA(filename,modelfile,5);
								}


				break;
			case 6:
				if (UseOVO){
				cl2.TrainMachineOVO(filename,modelfile,6);
				}
				else if(UseOVA){
					cl2.TrainMachineOVA(filename,modelfile,6);
				}

				break;
			case 7:
				break;

			}





}
void TestFeatures(char* filename,char* filename2,float g, float C,float s,
		int h ) {
	numberofFeatures = getFeatureCount(filename);


	RunFeaturesClassifiers cl2;


							cl2.Var_C=C;

									cl2.num_of_features=numberofFeatures;
									cl2.Variable_s=s_d;
									cl2.Variable_g=g;
									cl2.HiddenNeurons=h;
									cl2.GeneratePredictFiles=GeneartePredict;
									if (filename2!=NULL){
										cl2.ProcessFeaturesIndex(filename2);

									}



		switch (Algorithm) {
		case 1:
			if (UseOVO){
				cout<<" in the test "<<endl;
				cl2. RunTestsOVO(filename,modelfile,"_SVM_Linear_",1);
				//cl2.svmLinearTest(filename, n_features, ifilename, g, C,note,modelfile);
			}else if(UseOVA){
				cl2. RunTestsOVA(filename,modelfile,"_SVM_Linear_",1);
			}


			break;
		case 2:
			if (UseOVO){
			cl2. RunTestsOVO(filename,modelfile,"_Linear_",2);
		}else if(UseOVA){
			cl2. RunTestsOVA(filename,modelfile,"_Linear_",2);
				}
			break;
		case 3:

			break;
		case 4:
			break;
		case 5:
			if (UseOVO){
			cl2. RunTestsOVO(filename,modelfile,"_SVM_RBF_",5);
			}else if(UseOVA){
				cl2. RunTestsOVA(filename,modelfile,"_SVM_RBF_",5);
							}


			break;
		case 6:
			if (UseOVO){
			cl2. RunTestsOVO(filename,modelfile,"_Neural_",6);
			}
			else if(UseOVA){
				cl2. RunTestsOVA(filename,modelfile,"_Neural_",6);
			}

			break;
		case 7:
			break;

		}
}



void TestAll(char* filename, char* filename2, int n_features, char* ifilename, float g, float C,float s,
		int h, float threshold) {

	numberofFeatures = getFeatureCount(filename);

	//use tow case
	if (UseTwoClass){






			TwoClassClassifiers cl2;
			 if (GenerateStateFile==1)
			cl2.GenerateStateFile =  true;
			 else
					cl2.GenerateStateFile =  false;


			 if(GenerateScoreFiles==1)
					cl2.GenerateScoreFiles =  true;
							 else
					cl2.GenerateScoreFiles =  false;
				cl2.C_variable=C;

						cl2.Num_Of_Features=numberofFeatures;
						cl2.s_variable=s;
						cl2.g_variable=g;
						cl2.HiddenNeurons=h;

			cout << "The svm classifiers ." << endl;

			if (optimize) {

						C = cl2.svmRbfOptimize_C(filename, SampleCount, numberofFeatures, g);
						g = cl2.svmRbfOptimize_g(filename, SampleCount, numberofFeatures, C);
					}



			cl2.svmRbfTrain(filename, numberofFeatures, g, C,modelfile);

			//char* test_file,char* model,char* note,char* Algorithm,int alg
			cl2.RunTests(filename2,modelfile,note,"_SVM_RBF_",5);
			//cl2.svmRbfTest(filename2, numberofFeatures, ifilename, g, C,note,modelfile);


			cout << "The  LINEAR classifiers ." << endl;
			cl2.LinearTrain(filename,numberofFeatures,modelfile);
			//cl2.LinearTest(filename2,numberofFeatures,note,modelfile);
			cl2.RunTests(filename2,modelfile,note,"_Linear_",2);


			if (optimize) {

			}

			cout << "The Neural network classifiers ." << endl;
			cl2.neuralTrain(filename, h, numberofFeatures,modelfile);


			//cl2.neuralTest(filename2, h, namesfile, numberofFeatures,note,modelfile);
			cl2. RunTests(filename2,modelfile,note,"_Neural_",6);


//			cout << "The svm RBF classifiers ." << endl;
//			cl2.svmRBFTrain(filename, n_features, g, C,modelfile);
//			cl2.svmRBFTest(filename, n_features, ifilename, g, C,note,modelfile);


//
			cout << "The svm  Linear classifiers ." << endl;
		    cl2.svmLinearTrain(filename,numberofFeatures, s, C,modelfile);
			//cl2.svmLinearTest(filename2, numberofFeatures, ifilename, g, C,note,modelfile);
			cl2. RunTests(filename2,modelfile,note,"_SVM_Linear_",1);

			//cl2.writeResultString(note);


			cout << "Finished..........." << endl;
		}//use rejection



	else if (getRejectionRate){

	}
	else //general
	{

	}

}




int main(int argc, char* argv[]) {

	if (argc < 2) {
		DisplayDefaultMessage();
		return 0;
		//		cout<<"Start reading train..........."<<endl;
		//						TRAIN(trainfile, numberofFeatures,g,C);
		//						cout<<"Finished "<<endl;
		//						cout<<"Start testing ..........."<<endl;
		//						TEST(testfile, numberofFeatures,namesfile,C,g);
		//						cout<<"Finished";
		//						cout<<"****************************************************"<<endl;


	} else {
		getInputFromUser(argc, argv);
	}

	if (train == false && test == false) {

		train = true;
		test = true;

	}
	if (usenames == false) {
		namesfile = NULL;
	}



	if (RunAll){

				TestAll(trainfile,testfile, numberofFeatures, namesfile, g_d, C_d,s_d, h_d,
						threshold_d);


			}
	else if (FormateInputs){

		numberofFeatures = getFeatureCount(trainfile);
		if (formattype==1)

		TwoClassClassifiers::ConvertToSVMFormat(trainfile,testfile,numberofFeatures);
		else if (formattype==2)
		TwoClassClassifiers::ConvertToCSVFormat(trainfile,testfile,numberofFeatures);
		else if (formattype==3)
		TwoClassClassifiers::ConvertToARFFFormat(trainfile,testfile,numberofFeatures);
	}
	else if (USeDifferentFeatures) {


		cout<<"  in different features..........."<<endl;
		if (train) {
			cout<<"  now training ............"<<endl;
			TrainFeatures(trainfile,namesfile,g_d, C_d,s_d, h_d );

		}

		//if (test) {
			cout<<"  now testing............"<<endl;
		         TestFeatures(testfile,namesfile,g_d, C_d,s_d, h_d);
		//}

	}
	else {


	if (train) {

		cout << "Start the training ." << endl;


		if (getRejectionRate) {
			TrainRR(trainfile, numberofFeatures, g_d, C_d, h_d);

		} else {

			TRAIN(trainfile, numberofFeatures, g_d, C_d, h_d);


		}


		cout << "Finished Training ." << endl;

	}
	if (test) {

		cout << "Start testing " << endl;
		cout << "Start testing ..........." << endl;

		if (getRejectionRate) {
			TestRR(testfile, numberofFeatures, namesfile, g_d, C_d, h_d,
					threshold_d);

		} else {
			TEST(testfile, numberofFeatures, namesfile, g_d, C_d, h_d);
		}

		cout << "Finished";

	}
			}
	cout << endl;
	cout << endl;
	cout << "****************************************************" << endl;

	cout << "Hello World!!!" << endl; // prints Hello World!!!
	return 0;
}

