/*
 * RunClassifiers.cpp
 *
 *  Created on: Jan 3, 2009
 *      Author: Maha
 */

#include "RunClassifiers.h"
#include "LibraryFunctions.h"
using namespace Torch;
#include <fstream>
//#include<ofstream>
using namespace std;
RunClassifiersOVO::RunClassifiersOVO() {
	// TODO Auto-generated constructor stub

}

RunClassifiersOVO::~RunClassifiersOVO() {
	// TODO Auto-generated destructor stub
}

void RunClassifiersOVO::LinearTrain(char* train_file, int samples,
		int num_of_features,char* model) {

	Allocator *allocator = new Allocator;

	//	int num_of_features= 200;
	if (model==NULL)
	{
		model="Linear_model";
	}
	DiskXFile model_file(model, "w");

	//int num_of_features= 200;
	//char train_file[]="C:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	//DiskXFile model_file("model", "w");


	ConnectedMachine *nets[10][10];
	Linear *lin_layers[10][10];
	Tanh *out_layers[10][10];
	StochasticGradient *trainers[10][10];

	DataSet *train_set;
	train_set
			= new (allocator) MatDataSet(train_file, num_of_features, 1, false, -1, false);

	MemoryDataSet *current_train_set = new (allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for (int n = 0; n < 20000; n++) {
		inputs[n] = new (allocator) Sequence(1, num_of_features);
		targets[n] = new (allocator) Sequence(1, 1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);

	OneHotClassFormat class_format(1);
	Criterion *criterion = new (allocator) MSECriterion(1);

	int counter;
	for (int i = 0; i <= 8; i++) {
		for (int j = i + 1; j <= 9; j++)

		{
			cout << "Training " << i << " vs " << j << " ." << endl;

			lin_layers[i][j] = new (allocator) Linear(num_of_features, 1);
			out_layers[i][j] = new (allocator) Tanh(1);
			nets[i][j] = new (allocator) ConnectedMachine();
			nets[i][j]->addFCL(lin_layers[i][j]);
			nets[i][j]->addFCL(out_layers[i][j]);
			nets[i][j]->build();
			trainers[i][j]
					= new (allocator) StochasticGradient(nets[i][j], criterion);
			trainers[i][j]->setIOption("max iter", 100);

			counter = 0;
			for (long k = 0; k < samples; k++) {

				train_set->setExample(k);
				int d = train_set->targets->frames[0][0];

				if (d == i || d == j) {

					current_train_set->setExample(counter);

					for (int f = 0; f < num_of_features; f++) {
						current_train_set->inputs->frames[0][f]
								= train_set->inputs->frames[0][f];
					}

					if (d == i)
						current_train_set->targets->frames[0][0] = 1;
					else
						current_train_set->targets->frames[0][0] = -1;

					counter++;
				}

			}
			cout << counter << endl;

			current_train_set->setExample(0);
			current_train_set->n_examples = counter;
			trainers[i][j]->train(current_train_set, NULL);
			nets[i][j]->saveXFile(&model_file);

		}
	}

	delete (allocator);
}
void RunClassifiersOVO::LinearTest(char* test_file, int samples,
		int num_of_features,char* model) {

	Allocator *allocator = new Allocator;

	//int num_of_features=200+1;
	//char test_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");

	//int num_of_features=200;
	//char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	if (model==NULL)
	{
		model="Linear_model";
	}
	DiskXFile model_file(model, "r");


	DataSet *test_set;
	test_set
			= new (allocator) MatDataSet(test_file, num_of_features, 1, false, -1, false);

	cout << "reading the test set " << endl;

	// creating pairwise classifiers pointers
	ConnectedMachine *nets[10][10];
	Linear *lin_layer[10][10];
	Tanh *out_layer[10][10];

	//loading pairwise classifiers in turn
	int counter;
	for (int i = 0; i <= 8; i++) {
		for (int j = i + 1; j <= 9; j++) {

			lin_layer[i][j] = new (allocator) Linear(num_of_features, 1);
			out_layer[i][j] = new (allocator) Tanh(1);

			nets[i][j] = new (allocator) ConnectedMachine();
			nets[i][j]->addFCL(lin_layer[i][j]);
			nets[i][j]->addFCL(out_layer[i][j]);
			nets[i][j]->build();
			nets[i][j]->loadXFile(&model_file);
		}
	}

	real accuracy(0);

	char * tempString = new char[50];

	stringstream ss;
	real confusion_matrix[10][10];
	string* confusionDigits[10][10];
	int maxConfusion = 20;

	float n_iter = 10;
	Timer timer;

	cout << "before main iteration " << endl;
	for (int iter = 0; iter < n_iter; iter++) {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				confusionDigits[i][j] = new string[maxConfusion];
			}
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < maxConfusion; k++) {
					confusionDigits[i][j][k] = "";
				}
			}
		//	real accuracy(0);
		//	real confusion_matrix[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				confusion_matrix[i][j] = 0;
		cout << "iteration " << iter << endl;
		accuracy = 0;
		for (int t = 0; t < test_set->n_examples; t++) {
			real votes[10];
			for (int i = 0; i < 10; i++)
				votes[i] = 0;

			test_set->setExample(t);
			int d = test_set->targets->frames[0][0];

			real first_max_value;
			long first_max_index;

			for (int i = 0; i <= 8; i++)
				for (int j = i + 1; j <= 9; j++) {
					nets[i][j]->forward(test_set->inputs);
					real out = nets[i][j]->outputs->frames[0][0];
					if (out > 0)
						votes[i]++;
					else
						votes[j]++;

				}

			// finding value and index of first maximum
			LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);

			if (first_max_index == d)
				accuracy++;
			confusion_matrix[d][first_max_index]++;

			//	    int currentvalue=confusion_matrix[d][first_max_index];
			//		//if ( confusion){
			//		if (d!=first_max_index) {
			//	         //    tempString=new char[16];
			//	 //ss<<t;
			//	 //cout<<tempString<<"   "<<endl;
			//		// itoa (t,tempString,10);
			//			string tempString=""+t;
			//		 confusionDigits[d][first_max_index][currentvalue]=tempString;
			//		////confusionDigits[d][first_max_index][currentvalue]= ("  "+t);
			//
			//		}
			//}
			//if (t>8000)
			//	cout<< "   t ==   "<<t<<endl;
		}
	}
	float time1 = timer.getRunTime();
	float recog_time = timer.getRunTime() / (float) n_iter;
	float recog_time2 = timer.getTime() / (float) n_iter;
	accuracy /= test_set->n_examples;

	cout << "Accuracy = " << accuracy << endl;
	cout << "Recognition Time = " << recog_time << endl;

	ofstream fout("results_linear.txt");
	fout << " This is the linear =  " << endl;
	fout << "Accuracy = " << accuracy << endl;
	fout << "timer.getRunTime()" << time1 << endl;
	fout << "Recognition Time = " << recog_time << endl;
	fout << "Recognition Time2 = " << recog_time2 << endl;

	ofstream conf_fout("confusion_matrix_linear.txt");
	conf_fout << "   ";

	for (int i = 0; i < 10; i++)
		conf_fout << i << "    ";

	conf_fout << endl;
	for (int i = 0; i < 10; i++) {
		conf_fout << i << "  ";
		for (int j = 0; j < 10; j++)
			conf_fout << confusion_matrix[i][j] << "  ";
		conf_fout << endl << endl;
	}

	conf_fout << "*********************************************************"
			<< endl;

	for (int i = 0; i < 10; i++) {
		conf_fout << "-----------------------------------------------" << endl;
		for (int j = 0; j < 10; j++) {

			if (confusion_matrix[i][j] > 0 && i != j) {
				conf_fout << " Confusing digit  " << i
						<< " (supposed to be) with the digit " << j
						<< " (result of classifier) are : " << endl;
				for (int k = 0; k < maxConfusion; k++) {
					string s = confusionDigits[i][j][k];
					if (s.length() > 0) {
						conf_fout << s.data() << endl;
						//cout<<s.date()<<endl;
					}//oof

				}//for
			}//if
		}//for

	}//for


	delete (allocator);

}

void RunClassifiersOVO::FisherTest(char* test_file, int num_of_features , char* wsfile, char* bsfile) {

	Allocator *allocator = new Allocator;

	//int num_of_features=200+1;
	//char test_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");

	//const int num_of_features=200;
	//char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";

	DataSet *test_set;
	test_set
			= new (allocator) MatDataSet(test_file, num_of_features, 1, false, -1, false);

	float Ws[45][num_of_features];
	float bs[num_of_features];
	//if (wsfile==NULL)

	ifstream w_fin(wsfile);
	ifstream b_fin(bsfile);
	int count(-1);
	for (int i = 0; i <= 8; i++) {
		for (int j = i + 1; j <= 9; j++) {
			count++;
			b_fin >> bs[count];
			for (int n = 0; n < num_of_features; n++)
				w_fin >> Ws[count][n];

		}
	}

	real accuracy(0);

	Timer timer;
	timer.stop();
	for (int t = 0; t < test_set->n_examples; t++) {
		real votes[10];
		for (int i = 0; i < 10; i++)
			votes[i] = 0;

		test_set->setExample(t);
		int d = test_set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;

		int count(-1);
		for (int i = 0; i <= 8; i++)
			for (int j = i + 1; j <= 9; j++) {
				count++;

				float out(bs[count]);
				for (int n = 0; n < num_of_features; n++)
					out += test_set->inputs->frames[0][n] * Ws[count][n];

				if (out > 0)
					votes[i]++;
				else
					votes[j]++;

			}

		// finding value and index of first maximum
		LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);

		long max_index;
		float max_value;
		for (int i = 0; i < 10; i++) {
			LibraryFunctions::getMax(votes, 10, max_value, max_index);
			cout << max_index << " ";
			votes[max_index] = -9999999;
		}
		cout << endl;

		if (first_max_index == d)
			accuracy++;

		//cout<<d<<"->"<<first_max_index<<endl;
		//getch();


	}

//	ofstream fout("C12-F03_full_results.txt");

	//cout<<"Accuracy = "<<accuracy/100<<endl;
	float time1 = timer.getRunTime();
	//float recog_time = timer.getRunTime() / (float) n_iter;
//	float recog_time2 = timer.getTime() / (float) n_iter;
	accuracy /= test_set->n_examples;

	cout << "Accuracy = " << accuracy * 100 << endl;
	cout << "Recognition Time = " << time1 << endl;

	ofstream fout2("results_linear.txt");
	fout2 << " This is the linear =  " << endl;
	fout2 << "Accuracy = " << accuracy * 100 << endl;
	fout2 << "timer.getRunTime()" << time1 << endl;
//	fout << "Recognition Time = " << recog_time << endl;
//	fout << "Recognition Time2 = " << recog_time2 << endl;

	delete (allocator);

}

void RunClassifiersOVO::svmRbfTrain(char* train_file, int samples, float g,
		float C, int num_of_features,char* model) {

	Allocator *allocator = new Allocator;

	//int num_of_features=200;
	//char train_file[]="C:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	//DiskXFile model_file("model", "w");

	//int num_of_features=200;
	//char train_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	if (model==NULL)
	{
		model="svm_model";
	}
	DiskXFile model_file(model, "w");

	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	//float g=0.1;//0.0275;//0.04;//gamma
	//float C=100;
	Kernel *kernel = new (allocator) GaussianKernel(g);

	DataSet *train_set;
	train_set
			= new (allocator) MatDataSet(train_file, num_of_features, 1, false, -1, false);

	//MeanVarNorm mv_norm(train_set);
	//train_set->preProcess(&mv_norm);


	MemoryDataSet *current_train_set = new (allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for (int n = 0; n < 20000; n++) {
		inputs[n] = new (allocator) Sequence(1, num_of_features);
		targets[n] = new (allocator) Sequence(1, 1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);

	int counter;
	for (int i = 0; i <= 8; i++) {
		for (int j = i + 1; j <= 9; j++) {
			cout << "Training " << i << " vs " << j << " ." << endl;

			svms[i][j] = new (allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C", C);
			//svms[i][j]->setROption("cache size",10)	;

			trainers[i][j] = new (allocator) QCTrainer(svms[i][j]);

			counter = 0;
			for (long k = 0; k < samples; k++) {

				train_set->setExample(k);
				int d = train_set->targets->frames[0][0];

				if (d == i || d == j) {

					current_train_set->setExample(counter);

					for (int f = 0; f < num_of_features; f++) {
						current_train_set->inputs->frames[0][f]
								= train_set->inputs->frames[0][f];
					}

					if (d == i)
						current_train_set->targets->frames[0][0] = 1;
					else
						current_train_set->targets->frames[0][0] = -1;

					counter++;
				}

			}
			cout << counter << endl;

			current_train_set->setExample(0);
			current_train_set->n_examples = counter;
			trainers[i][j]->train(current_train_set, NULL);
			svms[i][j]->saveXFile(&model_file);

		}
	}

	delete (allocator);

}

void RunClassifiersOVO::svmRbfTest(char* test_file, int samples, float g, float C,
		int num_of_features,char* model) {

	Allocator *allocator = new Allocator;

	//int num_of_features=200;
	//char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("model", "r");

	//int num_of_features=200;
	//char test_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("svm_model", "r");
	if (model==NULL)
	{
		model="svm_model";
	}
	DiskXFile model_file(model, "r");

	DataSet *test_set;
	test_set
			= new (allocator) MatDataSet(test_file, num_of_features, 1, false, -1, false);
	cout << " after reading all the file start reading support vector" << endl;
	ifstream sv_fin("support_vectors.txt");
	//cout<<"0"<<endl;
	//ifstream sv_fin("zc_support_vectors.txt");
	if (sv_fin.fail()) {
		cout << "Cannot Open file: support_vectors.txt";
		//getch();
	}
	int n;
	sv_fin >> n;
	vector<Sequence*> SupportVectors(n);
	//cout<<"1"<<endl;
	for (int n = 0; n < SupportVectors.size(); n++) {
		SupportVectors[n] = new Sequence(1, num_of_features);
		for (int f = 0; f < num_of_features; f++) {
			sv_fin >> (SupportVectors[n]->frames[0][f]); /*cout<<(*SupportVectors[n])[f]<<endl; getch();*/
		}
	}
	//cout<<"2"<<endl;

	//ifstream sv_dist_fin("support_vectors_distribution.txt");
	ifstream sv_dist_fin("support_vectors_distribution.txt");
	if (sv_fin.fail()) {
		cout << "Cannot Open file: support_vectors_distribution.txt";
		//getch();
	}
	sv_dist_fin >> n;
	vector<int> SupportVectorsDist(n);
	//cout<<"3"<<endl;
	for (int n = 0; n < SupportVectorsDist.size(); n++)
		sv_dist_fin >> SupportVectorsDist[n];

	cout << "  now start creating the kernel ans svm " << endl;
	// creating pairwise classifiers pointers
	//float g=0.1;//0.0275;//0.04;//gamma
	//float C=100;
	Kernel *kernel = new (allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];

	//loading pairwise classifiers in turn
	for (int i = 0; i <= 8; i++) {
		for (int j = i + 1; j <= 9; j++) {
			cout << " allocating  " << i << " vs " << j << " ." << endl;
			svms[i][j] = new (allocator) SVMClassification(kernel);

			//loading SVM models in turn
			svms[i][j]->loadXFile(&model_file);

		}
	}

	real accuracy(0);
	string* confusionDigits[10][10];
	int maxConfusion = 20;

	real confusion_matrix[10][10];

	//ofstream conf_fout("zc_confusion_matrix.txt");
	vector<float> KernelEvaluations(SupportVectors.size());
	Timer timer;
	float n_iter = 10;
	for (int iter = 0; iter < n_iter; iter++) {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				confusionDigits[i][j] = new string[maxConfusion];
			}
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < maxConfusion; k++) {
					confusionDigits[i][j][k] = "";
				}
			}
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				confusion_matrix[i][j] = 0;
		accuracy = 0;
		cout << " startign the examples " << endl;
		real votes[10];
		real first_max_value;
		long first_max_index;
		int count(0);

		for (int t = 0; t < test_set->n_examples; t++) {
			if (t % 100 == 0)
				cout << t << endl;
			for (int i = 0; i < 10; i++)
				votes[i] = 0;

			test_set->setExample(t);
			int d = test_set->targets->frames[0][0];
			for (int n = 0; n < SupportVectors.size(); n++)
				KernelEvaluations[n] = kernel->eval(test_set->inputs,
						SupportVectors[n]);

			count = 0;

			for (int i = 0; i <= 8; i++) {
				for (int j = i + 1; j <= 9; j++) {
					float decision = svms[i][j]->b;
					for (int n = 0; n < svms[i][j]->n_support_vectors; n++)
						decision
								+= KernelEvaluations[SupportVectorsDist[count++]]
										* svms[i][j]->sv_alpha[n];

					if (decision > 0)
						votes[i] = votes[i] + 1;
					else
						votes[j] = votes[j] + 1;

				}
			}

			// finding value and index of first maximum
			LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);

			if (first_max_index == d)
				accuracy++;

			/*	confusion_matrix[d][first_max_index]++;
			 if (d!=first_max_index) {
			 char* tempString=new char[50];
			 itoa (t,tempString,10);
			 int currentvalue=confusion_matrix[d][first_max_index];
			 confusionDigits[d][first_max_index][currentvalue]=tempString;
			 }*/

		}
	}
	float recog_time = timer.getRunTime();
	float Avg_recog_time = timer.getRunTime() / n_iter;
	accuracy /= test_set->n_examples;

	cout << "Accuracy = " << accuracy << endl;
	cout << "Recognition Time = " << recog_time << endl;

	//ofstream fout("results.txt");
	ofstream fout("results_svm_RBF.txt");

	fout << "SVM RBF Accuracy = " << accuracy << endl;
	fout << "Recognition Time = " << recog_time << endl;
	fout << "Avg Recognition Time = " << Avg_recog_time << endl;

	ofstream conf_fout("confusion_matrix_svm_RBF.txt");
	conf_fout << "   ";
	for (int i = 0; i < 10; i++)
		conf_fout << i << " ";

	conf_fout << endl;
	for (int i = 0; i < 10; i++) {
		conf_fout << i << "  ";
		for (int j = 0; j < 10; j++)
			conf_fout << confusion_matrix[i][j] << "  ";
		conf_fout << endl << endl;
	}

	conf_fout << "*********************************************************"
			<< endl;

	for (int i = 0; i < 10; i++) {
		conf_fout << "-----------------------------------------------" << endl;
		for (int j = 0; j < 10; j++) {

			if (confusion_matrix[i][j] > 0 && i != j) {
				conf_fout << " Confusing digit  " << i
						<< " (supposed to be) with the digit " << j
						<< " (result of classifier) are : " << endl;
				for (int k = 0; k < maxConfusion; k++) {
					string s = confusionDigits[i][j][k];
					if (s.length() > 0) {
						conf_fout << s.data() << endl;
						//cout<<s.date()<<endl;
					}//oof

				}//for
			}//if
		}//for

	}//for


	delete (allocator);

}


void RunClassifiersOVO::svmRbfSupportVector(int num_of_features,char* model){

	Allocator *allocator = new Allocator;

		//int num_of_features=200;
		//DiskXFile model_file("model", "r");
		//ofstream sv_fout("support_vectors.txt");
		//ofstream sv_dist_fout("support_vectors_distribution.txt");


		//int num_of_features=200;
		cout<<"reading the model"<<endl;

		if (model==NULL)
		{
			model="svm_model";
		}
		DiskXFile model_file(model, "r");

		//DiskXFile model_file("svm_model", "r");
		ofstream sv_fout("support_vectors.txt");
		ofstream sv_dist_fout("support_vectors_distribution.txt");




		vector<Sequence*> SupportVectors(0);
		vector<int> SupportVectorsDist(0);

		float g=0.1;//0.0659
		Kernel *kernel = new(allocator) GaussianKernel(g);
		SVMClassification *svms[10][10];


		for(int i=0; i<=8; i++)
		{
			for(int j=i+1; j<=9; j++)
			{
				cout<<i<<" vs "<<j<<endl;

				svms[i][j] = new(allocator) SVMClassification(kernel);

				svms[i][j]->loadXFile(&model_file);


				for(int n=0; n<svms[i][j]->n_support_vectors; n++)
				{
					bool found(false);
					int m(0);
					for(m=0; m<SupportVectors.size(); m++)
					{
						found=true;

						for(int f=0; f<num_of_features; f++)
							if(svms[i][j]->sv_sequences[n]->frames[0][f]!= SupportVectors[m]->frames[0][f])
							{found=false; break;}


						if(!found)
							continue;
						else
							break;


					}

					if(found)
						SupportVectorsDist.push_back(m);
					else
					{
						SupportVectors.push_back(svms[i][j]->sv_sequences[n]);
						SupportVectorsDist.push_back(SupportVectors.size()-1);
					}


				}//end n

			}//end i
		}//end j


		sv_fout<<SupportVectors.size()<<endl;
		for(int n=0; n<SupportVectors.size(); n++)
		{
			for(int f=0; f<num_of_features; f++)
				sv_fout<<SupportVectors[n]->frames[0][f]<<" ";

			sv_fout<<endl;
		}



		sv_dist_fout<<SupportVectorsDist.size()<<endl;
		for(int n=0; n<SupportVectorsDist.size(); n++)
			sv_dist_fout<<SupportVectorsDist[n]<<endl;

		delete(allocator);

}


float RunClassifiersOVO::svmRbfOptimize_C(char* train_file, int samples,int num_of_features,float g){


	Allocator *allocator = new Allocator;

		//int num_of_features=200;

	 int Allsamples=samples;
	 int trainSamples=0.85*samples;
	 int validateSamples=0.15*samples;
		ofstream fout("C_optimzation_results_stage2.txt");

	//	char train_file[]="C:/datasets/arabic_gradproj_ourfeatures_training_set_full.txt";
		DataSet *dataset;
		dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

		float maxC=100;
		real maxAccuracy(0);
		//int training_mask[50000];
		//for(int i=0; i<50000; i++)
		//	training_mask[i]=i;


		SVMClassification *svms[10][10];
		QCTrainer *trainers[10][10];
		//float g=0.1;
		Kernel *kernel;

		MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
		Sequence *inputs[20000];
		Sequence *targets[20000];
		for(int n=0; n<20000; n++)
		{
			inputs[n]= new(allocator) Sequence(1,num_of_features);
			targets[n]= new(allocator) Sequence(1,1);
		}
		current_train_set->setInputs(inputs, 20000);
		current_train_set->setTargets(targets, 20000);


		float C_values[]={33.333,50,100,200,300};

		for(int C_index=0; C_index<=4; C_index++)
		{
			cout<<"C="<<C_values[C_index]<<endl;


			int counter;
			for(int i=0;i<=8;i++)
			{
				for(int j=i+1;j<=9;j++)
				{
					cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

					kernel = new(allocator) GaussianKernel(g);
					svms[i][j] = new(allocator) SVMClassification(kernel);
					svms[i][j]->setROption("C", C_values[C_index]);
					trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

					counter=0;
					for(long k=0; k<trainSamples; k++)
					{

						dataset->setExample(k);
						int d= dataset->targets->frames[0][0];

						if(d==i || d==j)
						{

							current_train_set->setExample(counter);

							for(int f=0; f<num_of_features; f++)
							{
								current_train_set->inputs->frames[0][f]=
									dataset->inputs->frames[0][f];
							}

							if(d==i)
								current_train_set->targets->frames[0][0]= 1;
							else
								current_train_set->targets->frames[0][0]= -1;

							counter++;
						}

					}

					cout<<counter<<endl;
					current_train_set->n_examples= counter;
					current_train_set->setExample(0);
					trainers[i][j]->train(current_train_set,NULL);

				}
			}




			real accuracy(0);

			//testing on the validation set
			for(int t=trainSamples; t<samples; t++)
			{

				dataset->setExample(t);
				int d= dataset->targets->frames[0][0];
				real decision[10];// to hold decision scores
				real votes[10];
				for(int i=0; i<10; i++)
					votes[i]=0;


				real first_max_value;
				long first_max_index;


				for(int i=0;i<=8;i++)
				{
					for(int j=i+1;j<=9;j++)
					{

						svms[i][j]->forward(dataset->inputs);

						real decision = svms[i][j]->outputs->frames[0][0];

						if(decision>0)
							votes[i]=votes[i]+1;
						else
							votes[j]=votes[j]+1;

					}
				}

				// finding value and index of first maximum
				LibraryFunctions::getMax(votes,10,first_max_value,first_max_index);

				if(first_max_index==d)
					accuracy++;

			}

			 if (accuracy>=maxAccuracy)
			{
							 maxAccuracy=accuracy;
							 maxC=C_values[C_index];

			 }

			accuracy=accuracy/ (float)(samples-trainSamples);
			cout<<accuracy*100<<endl;

			for(int i=0; i<=8; i++)
				for(int j=i+1; j<=9; j++)
					allocator->free(svms[i][j]);

			fout<<"C="<<C_values[C_index]<<" : "<<accuracy*100<<"%"<<endl;


		}



		delete(allocator);

     return maxC;
}


float RunClassifiersOVO::svmRbfOptimize_g(char* train_file, int samples,int num_of_features, float C)
{

	Allocator *allocator = new Allocator;

	//int num_of_features=200;


	ofstream fout("var_optimzation_results_stage2.txt");


	//char train_file[]="C:/datasets/arabic_gradproj_ourfeatures_training_set_full.txt";
	DataSet *dataset;
	dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);


	//int training_mask[50000];
	//for(int i=0; i<50000; i++)
	//	training_mask[i]=i;
	 int Allsamples=samples;
	 int trainSamples=0.85*samples;
	 int validateSamples=0.15*samples;

	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	//float C=100;
	Kernel *kernel;

	MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for(int n=0; n<20000; n++)
	{
		inputs[n]= new(allocator) Sequence(1,num_of_features);
		targets[n]= new(allocator) Sequence(1,1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);
    float maxG=0;
	real maxAccuracy(0);
	float g_values[]={.0333,0.05,0.2,0.3};
	for(int g_index=0; g_index<4; g_index++)
	{
		cout<<"g="<<g_values[g_index]<<endl;


		int counter;
		for(int i=0;i<=8;i++)
		{
			for(int j=i+1;j<=9;j++)
			{
				cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

				kernel = new(allocator) GaussianKernel(g_values[g_index]);
				svms[i][j] = new(allocator) SVMClassification(kernel);
				svms[i][j]->setROption("C", C);
				trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

				counter=0;
				for(long k=0; k<trainSamples; k++)
				{

					dataset->setExample(k);
					int d= dataset->targets->frames[0][0];

					if(d==i || d==j)
					{

						current_train_set->setExample(counter);

						for(int f=0; f<num_of_features; f++)
						{
							current_train_set->inputs->frames[0][f]=
								dataset->inputs->frames[0][f];
						}

						if(d==i)
							current_train_set->targets->frames[0][0]= 1;
						else
							current_train_set->targets->frames[0][0]= -1;

						counter++;
					}

				}

				cout<<counter<<endl;
				current_train_set->n_examples= counter;
				current_train_set->setExample(0);
				trainers[i][j]->train(current_train_set,NULL);

			}
		}




		real accuracy(0);

		//testing on the validation set
		for(int t=trainSamples; t<samples; t++)
		{

			dataset->setExample(t);
			int d= dataset->targets->frames[0][0];
			real decision[10];// to hold decision scores
			real votes[10];
			for(int i=0; i<10; i++)
				votes[i]=0;


			real first_max_value;
			long first_max_index;


			for(int i=0;i<=8;i++)
			{
				for(int j=i+1;j<=9;j++)
				{

					svms[i][j]->forward(dataset->inputs);

					real decision = svms[i][j]->outputs->frames[0][0];

					if(decision>0)
						votes[i]=votes[i]+1;
					else
						votes[j]=votes[j]+1;

				}
			}

			// finding value and index of first maximum
			LibraryFunctions::getMax(votes,10,first_max_value,first_max_index);

			if(first_max_index==d)
				accuracy++;

		}

		 if (accuracy>=maxAccuracy)
				{
								 maxAccuracy=accuracy;
								 maxG=g_values[g_index];

				 }

		accuracy=accuracy/ (float)(samples-trainSamples);
				cout<<accuracy*100<<endl;
		//cout<<accuracy/100<<endl;

		for(int i=0; i<=8; i++)
			for(int j=i+1; j<=9; j++)
				allocator->free(svms[i][j]);

		fout<<"g="<<g_values[g_index]<<" : "<<accuracy*100<<"%"<<endl;
	}



	delete(allocator);

     return maxG;
}

void  RunClassifiersOVO::neuralNetworkTrain(char* train_file, int samples,int num_of_features, int num_hidden_neuron,char* model)
{

	Allocator *allocator = new Allocator;

	//int num_of_features=200;
	//char train_file[]="C:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";

	//int num_of_features=200+1;
	//char train_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";

	if (model==NULL)
	{
		model="NN_model";
	}
	//DiskXFile model_file(model, "w");

	//const int n_hu=50;
	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(num_of_features,num_hidden_neuron);
	Tanh *c2= new(allocator) Tanh(num_hidden_neuron);
	Linear *c3= new(allocator) Linear(num_hidden_neuron,10);
	LogSoftMax *c4= new(allocator) LogSoftMax(10);
	mlp.addFCL(c1);
	mlp.addFCL(c2);
	mlp.addFCL(c3);
	mlp.addFCL(c4);
	mlp.build();
	mlp.setPartialBackprop();


	// reading training data from file and converting it to one hot class format
	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(i==j)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=0;
		}
	}


	DataSet *temp1;
	temp1=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(temp1,class_labels);


	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);

	// creating the trainer
	StochasticGradient trainer(&mlp, criterion);
	trainer.setIOption("max iter",100);

	// training...
	trainer.train(train_data, NULL);

	//saving model to file
	//mlp.save("model");

	mlp.save(model);


	delete(allocator);


}



void  RunClassifiersOVO::neuralNetworkTest(char* test_file, int samples,int num_of_features, int num_hidden_neuron,char* model){

	Allocator *allocator = new Allocator;

	//int num_of_features=200;
	//char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";

	//int num_of_features=200+1;
	//char test_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";

///	const int n_hu=50;
	// creating the mlp
	ConnectedMachine mlp;
	Linear *c1= new(allocator) Linear(num_of_features,num_hidden_neuron);
	Tanh *c2= new(allocator) Tanh(num_hidden_neuron);
	Linear *c3= new(allocator) Linear(num_hidden_neuron,10);
	LogSoftMax *c4= new(allocator) LogSoftMax(10);
	mlp.addFCL(c1);
	mlp.addFCL(c2);
	mlp.addFCL(c3);
	mlp.addFCL(c4);
	mlp.build();
	mlp.setPartialBackprop();
	if (model==NULL)
	{
		model="NN_model";
	}
	//DiskXFile model_file(model, "w");
	//loading the model
	mlp.load(model);

	//mlp.load("zc_model");


	// reading testing data from file and converting it to one hot class format
	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(i==j)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=0;
		}
	}

	DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);


		real confusion_matrix[10][10];
	string*  confusionDigits[10][10];

	int maxConfusion=20;

	float accuracy(0);
	float n_iter=10;
	Timer timer;
		bool confusion;
    if (n_iter>1)
  confusion=false;
else
  confusion=true;

	for(int iter=0; iter<n_iter; iter++)
	{


 	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++){

          confusionDigits[i][j]=new string[maxConfusion];
		}
 	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++){
			for(int k=0; k<maxConfusion;k++){
					confusionDigits[i][j][k]="";
			}
		}
	//	real accuracy(0);
//	real confusion_matrix[10][10];
	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++)
			confusion_matrix[i][j]=0;


		accuracy=0;
		for(int t = 0; t < test_data->n_examples; t++)
		{

			test_data->setExample(t);
			int d= test_data->targets->frames[0][0];//actual value of sample

			real decision_scores[10];// to hold decision scores

			mlp.forward(test_data->inputs);//forwarding sample

			// de-logging the decisions
			for(int i=0; i<10; i++)
				decision_scores[i] = exp(mlp.outputs->frames[0][i]);

			real first_max_value;
			long first_max_index;


			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);
			//fout<<first_max_index<<endl;


			if(first_max_index==d)
				accuracy++;
				confusion_matrix[d][first_max_index]++;

	if (d!=first_max_index) {
	//	 int currentvalue=confusion_matrix[d][first_max_index];
     //       char* tempString=new char[50];
		// itoa (t,tempString,10);
		// confusionDigits[d][first_max_index][currentvalue]=tempString;
	}



		}
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++){

	        delete   confusionDigits[i][j];
			}

	}

	float r_time= timer.getRunTime();
	float recog_time= timer.getRunTime()/n_iter;
	accuracy/=test_data->n_examples;


	cout<<"Accuracy: "<<accuracy<<endl;
	cout<<"Recognition Time: "<<recog_time<<endl;

	ofstream fout("results_NN.txt");
	fout<<"Accuracy: "<<accuracy<<endl;
	fout<<"R Time: "<<r_time<<endl;
	fout<<"Recognition Time: "<<recog_time<<endl;

	ofstream conf_fout("confusion_matrix_NN.txt");
conf_fout<<"   ";
for(int i=0; i<10; i++)
  conf_fout<<i<<"    ";

conf_fout<<endl;
	for(int i=0; i<10; i++)
	{
			conf_fout<<i<<"  ";
		for(int j=0; j<10; j++)
			conf_fout<<confusion_matrix[i][j]<<"  ";
		conf_fout<<endl<<endl;
	}

conf_fout<<"*********************************************************"<<endl;

	for(int i=0; i<10; i++)
	{
	 	conf_fout<<"-----------------------------------------------"<<endl;
		for(int j=0; j<10; j++)
		{

			if ( confusion_matrix[i][j]>0 && i!=j){
			conf_fout<<" Confusing digit  "<<i<<" (supposed to be) with the digit "<<j<<" (result of classifier) are : "<<endl;
			for(int k=0; k<maxConfusion;k++){
				string s=confusionDigits[i][j][k];
				if (s.length()>0 )
				{
					conf_fout<<s.data()<<endl;
					//cout<<s.date()<<endl;
				}//oof

			}//for
		}//if
		}//for

	}//for

	delete(allocator);
}


int  RunClassifiersOVO::neuralNetworkOptimize_h(char* train_file,int samples,int num_of_features)

{

	Allocator *allocator = new Allocator;

//	int num_of_features=200;

	ofstream fout("number_of_hidden_units_optimzation_results.txt");

	real maxAccuracy(0);
	int maxH=0;
	Sequence *class_labels= new(allocator) Sequence(10,10);
	int i,j;
	for(i=0;i<=9;i++)
	{
		for(j=0;j<=9;j++)
		{
			if(i==j)
				class_labels->frames[i][j]=1;
			else
				class_labels->frames[i][j]=0;
		}
	}

//	char train_file[]="C:/datasets/arabic_gradproj_ourfeatures_training_set_full.txt";
	DataSet *unformated_dataset;
	unformated_dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
	DataSet *train_data= new(allocator) ClassFormatDataSet(unformated_dataset,class_labels);


	// specifying cost function
	OneHotClassFormat *class_format= new(allocator) OneHotClassFormat(10);
	Criterion *criterion = new(allocator) ClassNLLCriterion(class_format);

	//int Allsamples=samples;
		 int trainSamples=0.85*samples;
	//	 int validateSamples=0.15*samples;

	int training_mask[trainSamples];

	for(int i=0; i<trainSamples; i++)
		training_mask[i]=i;


	for(int h=50; h<=300; h+=50)
	{
		cout<<"h="<<h<<endl;

		ConnectedMachine mlp;
		Linear c1(num_of_features,h);
		Tanh c2(h);
		Linear c3(h,10);
		LogSoftMax c4(10);
		mlp.addFCL(&c1);
		mlp.addFCL(&c2);
		mlp.addFCL(&c3);
		mlp.addFCL(&c4);
		mlp.build();
		mlp.setPartialBackprop();


		// creating the trainer
		StochasticGradient trainer(&mlp, criterion);
		trainer.setIOption("max iter",100);

		// training...
		train_data->pushSubset(training_mask,50000);
		trainer.train(train_data, NULL);
		train_data->popSubset();

		real accuracy(0);
		//testing on the validation set
		for(int t=trainSamples; t<samples; t++)
		{
			unformated_dataset->setExample(t);
			int d=unformated_dataset->targets->frames[0][0];

			real decision_scores[10];// to hold decision scores

			mlp.forward(unformated_dataset->inputs);//forwarding sample

			for(int i=0; i<10; i++)
				decision_scores[i] = mlp.outputs->frames[0][i];

			real first_max_value;
			long first_max_index;


			// finding value and index of first maximum
			LibraryFunctions::getMax(decision_scores,10,first_max_value,first_max_index);


			if(first_max_index==d)
				accuracy++;
		}

		 if (accuracy>=maxAccuracy)
					{
									 maxAccuracy=accuracy;
									 maxH=h;

					 }

			accuracy=accuracy/ (float)(samples-trainSamples);



		cout<<accuracy*100<<endl;

		fout<<"h="<<h<<" : "<<accuracy*100<<"%"<<endl;



	}



	delete(allocator);


	return maxH;

}



void RunClassifiersOVO::svmLinearTrain(char* train_file, int samples, float s , float C,int num_of_features,char* model)
{

	Allocator *allocator = new Allocator;

	//int num_of_features=200;
	//char train_file[]="C:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	//DiskXFile model_file("model", "w");

	//int num_of_features=200;
	//char train_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	if (model==NULL)
	{
		model="SVMLinearmodel";
	}
	DiskXFile model_file(model, "w");

//	DiskXFile model_file("SVMLinearmodel", "w");


	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	//float s=1;//0.0275;//0.04;//gamma
	//float C=150;
	Kernel *kernel = new(allocator) DotKernel(s);

	DataSet *train_set;
	cout<<"Reading datafile "<<endl;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	//MeanVarNorm mv_norm(train_set);
	//train_set->preProcess(&mv_norm);


	MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for(int n=0; n<20000; n++)
	{
		inputs[n]= new(allocator) Sequence(1,num_of_features);
		targets[n]= new(allocator) Sequence(1,1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);


	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C", C);
			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			counter=0;
			for(long k=0; k<samples; k++)
			{

				train_set->setExample(k);
				int d= train_set->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<num_of_features; f++)
					{
						current_train_set->inputs->frames[0][f]=
							train_set->inputs->frames[0][f];
					}

					if(d==i)
						current_train_set->targets->frames[0][0]= 1;
					else
						current_train_set->targets->frames[0][0]= -1;

					counter++;
				}

			}
			cout<<counter<<endl;

			current_train_set->setExample(0);
			current_train_set->n_examples= counter;
			trainers[i][j]->train(current_train_set,NULL);
			cout<<"Saving model of classifier "<<endl;
			svms[i][j]->saveXFile(&model_file);


		}
	}


	delete(allocator);


}

void RunClassifiersOVO::svmLinearTest(char* test_file, int samples,float s , float C,int num_of_features,char* model)
{

	Allocator *allocator = new Allocator;

	//int num_of_features=200;
	//char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	if (model==NULL)
	{
		model="SVMLinearmodel";
	}
	DiskXFile model_file(model, "r");
	//DiskXFile model_file("SVMLinearmodel", "r");

	//int num_of_features=200+1;
	//char test_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
	//DiskXFile model_file("zc_model", "r");

	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);



	// creating pairwise classifiers pointers
//	float s=1;//0.0275;//0.04;//gamma
	//float C=150;
	Kernel *kernel = new(allocator) DotKernel(s);
	SVMClassification *svms[10][10];

	Sequence Ws(45,num_of_features);
	float bs[45];

	//loading pairwise classifiers in turn
	int count(0);
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			svms[i][j] = new(allocator) SVMClassification(kernel);


			//loading SVM models in turn
			svms[i][j]->loadXFile(&model_file);

			bs[count]= svms[i][j]->b;

			for(int n=0; n<num_of_features; n++)
				Ws.frames[count][n]=0;

			for(int s=0; s<svms[i][j]->n_support_vectors; s++)
			{
				for(int n=0; n<num_of_features; n++)
					Ws.frames[count][n]+= (svms[i][j]->sv_alpha[s])*(svms[i][j]->sv_sequences[s]->frames[0][n]);
			}

			count++;


		}
	}


		real confusion_matrix[10][10];





	real accuracy(0);
	float n_iter=10;
	Timer timer;
		string*  confusionDigits[10][10];
	int maxConfusion=20;

	for(int iter=0; iter<n_iter; iter++)
	{

					cout<<"iteration "<<iter<<endl;


	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++)
			confusion_matrix[i][j]=0;


		for(int i=0; i<10; i++)
		for(int j=0; j<10; j++){
          confusionDigits[i][j]=new string[maxConfusion];
		}
 	for(int i=0; i<10; i++)
		for(int j=0; j<10; j++){
			for(int k=0; k<maxConfusion;k++){
					confusionDigits[i][j][k]="";
			}
		}
		accuracy=0;
		for(int t = 0; t < test_set->n_examples; t++)
		{
			real votes[10];
			for(int i=0; i<10; i++)
				votes[i]=0;

			test_set->setExample(t);
			int d= test_set->targets->frames[0][0];

			real first_max_value;
			long first_max_index;


			count=-1;
			for(int i=0;i<=8;i++)
			{
				for(int j=i+1;j<=9;j++)
				{
					count++;
					real decision(bs[count]);
					for(int n=0; n<num_of_features; n++)
						decision+= (test_set->inputs->frames[0][n])*(Ws.frames[count][n]);

					if(decision>0)
						votes[i]=votes[i]+1;
					else
						votes[j]=votes[j]+1;

				}
			}

			// finding value and index of first maximum
			LibraryFunctions::getMax(votes,10,first_max_value,first_max_index);

			if(first_max_index==d)
				accuracy++;

           //cout<<"  d = "<<d<<"  Recognized as "<<first_max_index<<endl;

			confusion_matrix[d][first_max_index]++;




	/*				if (d!=first_max_index) {
            char* tempString=new char[50];
			int currentvalue=confusion_matrix[d][first_max_index];
		 itoa (t,tempString,10);
		 confusionDigits[d][first_max_index][currentvalue]=tempString;
	}*/
		}
	}
	float recog_time= timer.getRunTime();///n_iter;
	float recog_time_aveg= timer.getRunTime()/n_iter;
accuracy/=samples;


	cout<<"Accuracy = "<<accuracy<<endl;
	cout<<"Recognition Time = "<<recog_time<<endl;

	ofstream fout("results_SVM_linear.txt");
	fout<<"Accuracy = "<<accuracy<<endl;
	fout<<"Recognition Time = "<<recog_time<<endl;
	fout<<"Averge "<<recog_time_aveg<<endl;

ofstream conf_fout("confusion_matrix_SVM_linear.txt");
conf_fout<<"   ";
for(int i=0; i<10; i++)
  conf_fout<<i<<" ";

conf_fout<<endl;
	for(int i=0; i<10; i++)
	{
			conf_fout<<i<<"  ";
		for(int j=0; j<10; j++)
			conf_fout<<confusion_matrix[i][j]<<"  ";
		conf_fout<<endl<<endl;
	}

conf_fout<<"*********************************************************"<<endl;

	for(int i=0; i<10; i++)
	{
	 	conf_fout<<"-----------------------------------------------"<<endl;
		for(int j=0; j<10; j++)
		{

			if ( confusion_matrix[i][j]>0 && i!=j){
			conf_fout<<" Confusing digit  "<<i<<" (supposed to be) with the digit "<<j<<" (result of classifier) are : "<<endl;
			for(int k=0; k<maxConfusion;k++){
				string s=confusionDigits[i][j][k];
				if (s.length()>0 )
				{
					conf_fout<<s.data()<<endl;
					//cout<<s.date()<<endl;
				}//oof

			}//for
		}//if
		}//for

	}//for

	delete(allocator);


}

float RunClassifiersOVO::svmLinearOptimize(char* train_file, int samples, float s  ,int num_of_features){


	Allocator *allocator = new Allocator;

		//int num_of_features=200;

	int trainSamples=0.85*samples;
		ofstream fout("C_optimzation_results.txt");

		real maxAccuracy(0);
		float maxC=100;

		//char train_file[]="C:/datasets/arabic_gradproj_ourfeatures_training_set_full.txt";
		DataSet *dataset;
		dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);


		//int training_mask[50000];
		//for(int i=0; i<50000; i++)
		//	training_mask[i]=i;


		SVMClassification *svms[10][10];
		QCTrainer *trainers[10][10];
		//float s=1;
		Kernel *kernel;

		MemoryDataSet *current_train_set= new(allocator) MemoryDataSet();
		Sequence *inputs[20000];
		Sequence *targets[20000];
		for(int n=0; n<20000; n++)
		{
			inputs[n]= new(allocator) Sequence(1,num_of_features);
			targets[n]= new(allocator) Sequence(1,1);
		}
		current_train_set->setInputs(inputs, 20000);
		current_train_set->setTargets(targets, 20000);


		float C_values[]={0.1,1,10,100};
		for(int C_index=0; C_index<1; C_index++)
		{
			cout<<"C="<<C_values[C_index]<<endl;


			int counter;
			for(int i=0;i<=8;i++)
			{
				for(int j=i+1;j<=9;j++)
				{
					cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

					kernel = new(allocator) DotKernel(s);
					svms[i][j] = new(allocator) SVMClassification(kernel);
					svms[i][j]->setROption("C", C_values[C_index]);
					trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

					counter=0;
					for(long k=0; k<trainSamples; k++)
					{

						dataset->setExample(k);
						int d= dataset->targets->frames[0][0];

						if(d==i || d==j)
						{

							current_train_set->setExample(counter);

							for(int f=0; f<num_of_features; f++)
							{
								current_train_set->inputs->frames[0][f]=
									dataset->inputs->frames[0][f];
							}

							if(d==i)
								current_train_set->targets->frames[0][0]= 1;
							else
								current_train_set->targets->frames[0][0]= -1;

							counter++;
						}

					}

					cout<<counter<<endl;
					current_train_set->n_examples= counter;
					current_train_set->setExample(0);
					trainers[i][j]->train(current_train_set,NULL);

				}
			}




			real accuracy(0);

			//testing on the validation set
			for(int t=trainSamples; t<samples; t++)
			{

				dataset->setExample(t);
				int d= dataset->targets->frames[0][0];
				real decision[10];// to hold decision scores
				real votes[10];
				for(int i=0; i<10; i++)
					votes[i]=0;


				real first_max_value;
				long first_max_index;


				for(int i=0;i<=8;i++)
				{
					for(int j=i+1;j<=9;j++)
					{

						svms[i][j]->forward(dataset->inputs);

						real decision = svms[i][j]->outputs->frames[0][0];

						if(decision>0)
							votes[i]=votes[i]+1;
						else
							votes[j]=votes[j]+1;

					}
				}

				// finding value and index of first maximum
				LibraryFunctions::getMax(votes,10,first_max_value,first_max_index);

				if(first_max_index==d)
					accuracy++;

			}


			 if (accuracy>=maxAccuracy)
					{
									 maxAccuracy=accuracy;
									 maxC=C_values[C_index];

					 }

					accuracy=accuracy/ (float)(samples-trainSamples);
					cout<<accuracy*100<<endl;

			//cout<<accuracy/100<<endl;

			for(int i=0; i<=8; i++)
				for(int j=i+1; j<=9; j++)
					allocator->free(svms[i][j]);

			fout<<"C="<<C_values[C_index]<<" : "<<accuracy*100<<"%"<<endl;
		}



		delete(allocator);
		return maxC;

}
