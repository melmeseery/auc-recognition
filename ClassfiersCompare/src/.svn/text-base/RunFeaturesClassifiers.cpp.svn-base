/*
 * RunFeaturesClassifiers.cpp
 *
 *  Created on: Feb 3, 2009
 *      Author: Maha
 */

#include "RunFeaturesClassifiers.h"

RunFeaturesClassifiers::RunFeaturesClassifiers() {
	// TODO Auto-generated constructor stub
	classes = 10;
	useDifferentFeatures = false;
	fsummary.open("Summary.txt");
	fscores.open("Scores.txt");
	WriteScores = true;
	GeneratePredictFiles = false;
	allocator = new Allocator();
	classifiedWrongNoProblem = 0;
	classifiedWrongError = 0;
	for (int i = 0; i < classes; i++) {
		DigitsAccuracy[i] = 0;;
		for (int j = 0; j < classes; j++) {

			classifiersAccuracyForTheirDigits[i][j] = 0;
			classifiersAccuracy[i][j] = 0;
			ClassifiersErrors[i][j] = 0;

		}

	}

}

RunFeaturesClassifiers::~RunFeaturesClassifiers() {
//	int Cmax = classes;
//	delete allocator;
//	if (useDifferentFeatures) {
//		// TODO Auto-generated destructor stub
//
//		for (int i = 0; i < Cmax; i++) {
//
//			delete FeatCountMat[i];
//			for (int j = 0; j < Cmax; j++) {
//				delete FeaturesIndex[i][j];
//			}
//
//			delete OVAFeaturesIndex[i];
//			delete FeatCountMat[i];
//			delete FeaturesIndex[i];
//
//		}
//		delete OVAFeatCountArray;
//		delete OVAFeaturesIndex;
//		delete FeatCountMat;
//		delete FeaturesIndex;
//	}

}

void RunFeaturesClassifiers::ProcessFeaturesIndex(char* filename) {

	int Cmax = classes;

	FeaturesIndex = new int**[Cmax];
	FeatCountMat = new int*[Cmax];

	for (int i = 0; i < Cmax; i++) {
		FeaturesIndex[i] = new int*[Cmax];
		FeatCountMat[i] = new int[Cmax];
		for (int j = 0; j < Cmax; j++) {
			FeatCountMat[i][j] = 0;
		}
	}

	fstream inFile;
	// inFile.open("files_names.txt");
	inFile.open(filename);
	if (!inFile) {
		cout << "Unable to open file    " << filename << endl;
		return;
		// exit(1); // terminate with error
	}

	//int size;
	double x = 0;

	int count = 0;
	int FeatCount = 0;
	int C1 = 0;
	int C2 = 0;

	OVAFeatCountArray = new int[Cmax];
	for (int i = 0; i < Cmax; i++) {
		OVAFeatCountArray[i] = 0;
	}

	cout << " reading the file ............." << endl;

	while (!inFile.eof()) {

		if (inFile >> C1) {
			if (inFile >> C2) {
			//	cout << " line   " << C1 << "  and " << C2 << endl;

				inFile >> FeatCount;

			//	cout << "number of features is " << FeatCount << endl;

				if (C1 < Cmax && C2 < Cmax) {
					FeatCountMat[C1][C2] = FeatCount;
					OVAFeatCountArray[C1] = OVAFeatCountArray[C1] + FeatCount;
					FeaturesIndex[C1][C2] = new int[FeatCount];
					for (int k = 0; k < FeatCount; k++) {
						if (inFile >> x) {

							FeaturesIndex[C1][C2][k] = x;
						}

//						cout << " k = " << k << " x = " << x
//								<< " and the c1 = " << C1 << endl;

					}

				}
			}
		}

	}

	inFile.close();

	//checking the feature index.

//	for (int i = 0; i < Cmax; i++) {
//
//		for (int j = i + 1; j < Cmax; j++) {
//
//			FeatCount = FeatCountMat[i][j];
//			cout << " feat of  " << i << " vs " << j << " are " << FeatCount
//					<< "  ";
//			for (int k = 0; k < FeatCount; k++) {
//
//				cout << FeaturesIndex[i][j][k] << "  ";
//			}
//			cout << endl;
//
//		}
//	}

	OVAFeaturesIndex = new int*[Cmax];

	int l = 0;

	for (int i = 0; i < Cmax; i++) {

		OVAFeaturesIndex[i] = new int[OVAFeatCountArray[i]];
		l = 0;
		for (int j = i + 1; j < Cmax; j++) {

			for (int k = 0; k < FeatCountMat[i][j]; k++) {

				OVAFeaturesIndex[i][l] = FeaturesIndex[i][j][k];
				l++;

			}
		}
	}
	useDifferentFeatures = true;
}
void RunFeaturesClassifiers::GeneratePerdictFilesOVO(DataSet* test_data,
		int alg) {

	for (int i = 0; i < 9; i++) {
		for (int j = i + 1; j <= 9; j++) {

			char* name = new char[200];
			strcpy(name, "");
			strcat(name, "Predict_");
			char cVal[32];
			sprintf(cVal, "%i", alg);
			strcat(name, cVal);
			strcat(name, "_Classifier_");

			sprintf(cVal, "%i", i);
			strcat(name, cVal);

			strcat(name, "_");

			sprintf(cVal, "%i", j);
			strcat(name, cVal);

			strcat(name, ".txt");

			ofstream fpredict(name);
			cout << " Testing  " << i << " vs " << j << " ." << endl;

			for (int t = 0; t < test_data->n_examples; t++) {
				//cout<<" t = "<<t<<endl;
				if ((t % 300) == 0)
					cout << t << endl;

				test_data->setExample(t);
				int d = test_data->targets->frames[0][0];//actual value of sample
				float score = GetResultFromPredictMachineOVO(test_data->inputs,
						alg, machines[i][j]);
				if (score > 0) {

					fpredict << "1" << endl;
				} else {

					fpredict << "-1" << endl;
				}

			}
			fpredict.close();
			delete name;

		}
	}

}
float RunFeaturesClassifiers::GetResultFromPredictMachineOVO(Sequence* input,
		int alg, Machine* classifier) {

	float score = 0;
	if (alg == 1) {///svm linear....
		score = 0;
		//	for(int n=0; n<Num_Of_Features; n++)
		//	score+= (input->frames[0][n])*(Ws->frames[0][n]);
	} else {
		classifier->forward(input);
		score = classifier->outputs->frames[0][0];

	}
	return score;
}

void RunFeaturesClassifiers::TrainMachineOVO(char* train_file, char* model,
		int alg) {
    cout<<" Running the "<< alg<<"  with c= "<<Var_C<<endl;
    cout<<" and  g = "<<Variable_g;
       cout<<" and s = "<<Variable_s<<endl;
       cout.flush();
	//int num_of_features=200;
	//char train_file[]="C:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	//DiskXFile model_file("model", "w");

	//int num_of_features=200;
	//char train_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	if (model == NULL) {
		if (alg == 5) {
			model = "svm_model";
		} else if (alg == 1) {
			model = "svm_linear_model";
		} else if (alg == 2) {
			model = "linear_model";
		}
	}
	//	SVMClassification*** machinesSVM = new (allocator) SVMClassification**[classes];


	DiskXFile model_file(model, "w");
	LoadMachineForTrainOVO(alg);
	//cout<<" the current"<<endl;
	//cout<<" the current trainers has "<<this->trainers[0][1]->n_options<<endl;

	DataSet *train_set;
	train_set
			= new (allocator) MatDataSet(train_file, num_of_features, 1, false, -1, false);

	for (int i = 0; i < 9; i++) {
		for (int j = i + 1; j <= 9; j++) {

			cout <<" Training " << i << " vs " << j << " ." << endl;
		    cout.flush();
			MemoryDataSet *current = GetCurretDataSetOVO(i, j, train_set);
			cout << " current size after function = " << current->n_examples<< endl;
			trainers[i][j]->train(current, NULL);

			machines[i][j]->saveXFile(&model_file);
			// allocator->free(current);
		}
	}

	cout << " finished trainig........................" << endl;

	//freeTrainMachinesOVO();
	// allocator->free(train_set);
	//delete allocator;
}
void RunFeaturesClassifiers::freeTrainMachinesOVO() {

	for (int i = 0; i < classes; i++) {

		for (int j = 0; j < classes; i++) {
			delete machines[i][j];
			delete trainers[i][j];
		}
		//	machinesSVM[i] = new (allocator)  SVMClassification*[classes];
		delete machines[i];
		delete trainers[i];
	}

	delete machines;
	delete trainers;

}

void RunFeaturesClassifiers::LoadMachineForTrainOVO(int alg) {
     if (alg==2){
    		      machines = new   Machine**[classes];
    				trainers = new   Trainer**[classes];
    				Linear ***lin_layers=new Linear**[classes];
    				Tanh ***out_layers =new Tanh**[classes];
    				for (int i = 0; i < classes; i++) {
    					machines[i] = new  Machine*[classes];
    					trainers[i] = new   Trainer*[classes];
    					lin_layers[i]=new Linear*[classes];
    					out_layers[i]=new Tanh*[classes];

    				}

    			//ConnectedMachine *nets[10][10];

    			//Tanh *out_layers[10][10];
    			//StochasticGradient *trainers[10][10];

    			Criterion *criterion = new   MSECriterion(1);

    				int counter;
    				for (int i = 0; i <= 8; i++) {
    					for (int j = i + 1; j <= 9; j++)

    					{
    						cout << "Training " << i << " vs " << j << " ." << endl;

    						lin_layers[i][j] = new   Linear(num_of_features, 1);
    						out_layers[i][j] = new   Tanh(1);
    						ConnectedMachine* nets = new  ConnectedMachine();
    						nets->addFCL(lin_layers[i][j]);
    						nets->addFCL(out_layers[i][j]);
    						nets->build();
    						machines[i][j]=nets;
    						trainers[i][j]= new  StochasticGradient(nets, criterion);
    						trainers[i][j]->setIOption("max iter", 500);
    					}}


     }
	if (alg == 5) {

		machines = new Machine**[classes];
		trainers = new Trainer**[classes];
		for (int i = 0; i < classes; i++) {
			//	machinesSVM[i] = new (allocator)  SVMClassification*[classes];
			machines[i] = new Machine*[classes];
			trainers[i] = new Trainer*[classes];
		}
		//}

		//float g=0.1;//0.0275;//0.04;//gamma
		//float C=100;
		Kernel *kernel = new GaussianKernel(Variable_g);

		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				cout << " creating " << i << " vs " << j << " ." << endl;;

				//				machinesSVM[i][j] = new (allocator) SVMClassification(kernel);
				//
				//				cout<<"after the machines"<<endl;

				SVMClassification* svms = new SVMClassification(kernel);
				svms->setROption("C", Var_C);

				//SVMClassification* svm;
				//svm=(SVMClassification*)machines[i][j];
				machines[i][j] = svms;
				//				cout<<" casting "<<endl;
				//				cout<<"  C  =  "<<Var_C<<endl;
				//machinesSVM[i][j]->setROption("C", (real)Var_C);
				//   cout<<" seting the c "<<endl;

				//svms[i][j]->setROption("cache size",10)	;

				trainers[i][j] = new QCTrainer(svms);

				//	cout<<" creating the trainers  "<<endl;


			}
		}

	}
	if (alg == 1) {

		machines = new Machine**[classes];
		trainers = new Trainer**[classes];
		for (int i = 0; i < classes; i++) {
			//	machinesSVM[i] = new (allocator)  SVMClassification*[classes];
			machines[i] = new Machine*[classes];
			trainers[i] = new Trainer*[classes];
		}
		//}

		//float g=0.1;//0.0275;//0.04;//gamma
		//float C=100;
		Kernel *kernel = new DotKernel(Variable_s);

		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
			//	cout << " creating " << i << " vs " << j << " ." << endl;;

				//				machinesSVM[i][j] = new (allocator) SVMClassification(kernel);
				//
				//				cout<<"after the machines"<<endl;

				SVMClassification* svms = new SVMClassification(kernel);
				svms->setROption("C", Var_C);

				//SVMClassification* svm;
				//svm=(SVMClassification*)machines[i][j];
				machines[i][j] = svms;
				//				cout<<" casting "<<endl;
				//				cout<<"  C  =  "<<Var_C<<endl;
				//machinesSVM[i][j]->setROption("C", (real)Var_C);
				//   cout<<" seting the c "<<endl;

				//svms[i][j]->setROption("cache size",10)	;

				trainers[i][j] = new QCTrainer(svms);

				//	cout<<" creating the trainers  "<<endl;


			}
		}
	}

}

MemoryDataSet* RunFeaturesClassifiers::GetCurretDataSetOVO(int i, int j,
		DataSet* train_set) {
	cout << "  getting training set for ovo " << i << " vs " << j << endl;

	int Classifier_Number_Feat;
	if (useDifferentFeatures) {
		Classifier_Number_Feat = FeatCountMat[i][j];
	} else {
		Classifier_Number_Feat = num_of_features;
	}
	int index = 0;

	/***********Now based on the features comptue the ************/
	MemoryDataSet *current_train_set = new MemoryDataSet();
	Sequence *inputs[15000];
	Sequence *targets[15000];
	//inputs=new (allocator)  Sequence[15000];
	//targets=new (allocator)  Sequence[15000];


	for (int n = 0; n < 15000; n++) {
		targets[n] = new Sequence(1, 1);
		inputs[n] = new Sequence(1, Classifier_Number_Feat);

		//cout<<"Frame siZe = "<< inputs[n]->frame_size <<endl;
		//cout<<"Frame siZe targets = "<< targets[n]->frame_size <<endl;
	}

	cout << " generating th output sequence ............" << endl;

	current_train_set->setTargets(targets, 15000);

	current_train_set->setInputs(inputs, 15000);

	cout << "finished inputs now targets        " << endl;

	int counter = 0;

	cout << "Now for the generating th current train set " << endl;
	for (long k = 0; k < train_set->n_examples; k++) {

		//	cout<<"  Sample. .... "<<k<<endl;
		train_set->setExample(k);
		int d = train_set->targets->frames[0][0];

		if (d == i || d == j) {
			//	cout<<"  d  =  "<<d<<endl;
			current_train_set->setExample(counter);
			////**************************************************change here...............
			//	cout<<"Setting the featuers ............"<<endl;
			if (useDifferentFeatures) {
				for (int f = 0; f < Classifier_Number_Feat; f++) {
					index = FeaturesIndex[i][j][f];
					current_train_set->inputs->frames[0][f]
							= train_set->inputs->frames[0][index];
				}
			} else {
				for (int f = 0; f < num_of_features; f++) {

					current_train_set->inputs->frames[0][f]
							= train_set->inputs->frames[0][f];
				}

			}

			//cout<<"  now targests ............"<<endl;

			if (d == i)
				current_train_set->targets->frames[0][0] = 1;
			else
				current_train_set->targets->frames[0][0] = -1;

			counter++;
		}

	}
	current_train_set->setExample(0);
	current_train_set->n_examples = counter;
	cout << " count of samples in " << i << " vs " << j << "  is   " << counter
			<< endl;

	//cout<<"to return.............."<<endl;
	return current_train_set;

}

void RunFeaturesClassifiers::RunTestsOVO(char* test_file, char* model,
		char* Algorithm, int alg) {

	// allocator = new Allocator;
         cout<<"  Running the test "<< Algorithm<<"  with c= "<<Var_C<<endl<<" and  g = "<<Variable_g;
         cout<<" and s = "<<Variable_s<<endl;
	if (model == NULL) {
		if (alg == 5) {
			model = "svm_model";
		} else if (alg == 1) {
			model = "svm_linear_model";
		} else if (alg == 2) {
			model = "linear_model";
		}
	}

	LoadMachineForTestOVO(alg, model);//,  bs, Ws

	cout << " Reading the test set " << endl;
	DataSet *test_data;
	test_data
			= new (allocator) MatDataSet(test_file, num_of_features, 1, false, -1, false);

	/*Local variables & Initialization*/
	float actual_accuracy(0);
	float positivesamples(0);
	float negative(0);
	float n_iter = 10;
	Timer timer;
	float pos, neg;
	pos = 0;
	neg = 0;
	actual_accuracy = 0;
	int c2Count, c1Count;
	c2Count = 0;
	c1Count = 0;
	if (GeneratePredictFiles) {
		GeneratePerdictFilesOVO(test_data, alg);
	}
	cout
			<< "After generateing the predict computing the result form my program "
			<< endl;
	for (int t = 0; t < test_data->n_examples; t++) {
		//cout<<" t = "<<t<<endl;
		//		if ((t % 300) == 0) {
		//			float currentAccuracy = (actual_accuracy / (float) t) * 100;
		//			cout << t << "  currentAccuracy  = " << currentAccuracy << endl;
		//		}

		test_data->setExample(t);
		int d = test_data->targets->frames[0][0];//actual value of sample

		float *votes = new float[10];
		float score = GetResultFromMachinesOVO(test_data->inputs, d, t, alg,
				votes);

		//		for(int l=0; l<10;l++)
		//		{
		//		    cout<<votes[l]<<" "<<endl;
		//		}
		//		cout<<endl;


		if (score == d) {

			actual_accuracy++;
		} else {
			//check votes..................
			//errrorssssssss.
			if (WriteScores) {
				cout << "Wrong sample= " << t << " and the was supposed to be "
						<< d << " recognized as  " << score << "";
				cout << "(" << d << "  has " << votes[d] << " votes and "
						<< score << "  has " << votes[(int) score]
						<< " votes ) " << endl;
				fscores << "Wrong sample= " << t
						<< " and the was supposed to be " << d
						<< " recognized as  " << score << "";
				fscores << "(" << d << "  has " << votes[d] << " votes and "
						<< score << "  has " << votes[(int) score]
						<< " votes ) " << endl;
			}
		}

		delete votes;

	}

	float accuracy = (actual_accuracy / (float) test_data->n_examples) * 100;
	float digitsSamPercent = (1.0 / 1000.0) * 100.0;
	float classifiersSamPercent = (1.0 / 2000.0) * 100.0;

	cout << "summary -------------------------------------------------" << endl;

	cout << classifiedWrongError << " classified Wrong then lead to Error "
			<< endl;
	fsummary << classifiedWrongError << " classified Wrong then lead to Error "
			<< endl;
	cout << classifiedWrongNoProblem
			<< " classified Wrong but lead to no Error " << endl;
	fsummary << classifiedWrongNoProblem
			<< " classified Wrong but lead to no Error " << endl;
	cout << " The accuracy of each classifier   = " << endl;
	fsummary << " The accuracy of each classifier   = " << endl;
	int samcount;
	for (int i = 0; i < classes; i++) {

		for (int j = i + 1; j < classes; j++) {
			samcount = classifiersAccuracyForTheirDigits[i][j];
			cout << " accuracy of  " << i << " vs " << j << " ";
			cout << "( " << samcount << " / 2000 ) =  " << samcount
					* classifiersSamPercent << " [ with  " << (2000 - samcount)
					<< " classified wrong ]" << endl;
			fsummary << " accuracy of  " << i << " vs " << j << " ";
			fsummary << "( " << samcount << " / 2000 ) =  " << samcount
					* classifiersSamPercent << " [ with  " << (2000 - samcount)
					<< " classified wrong ]" << endl;

		}
	}
	cout << " The accuracy of each digit  = " << endl;
	for (int i = 0; i < classes; i++) {
		samcount = DigitsAccuracy[i];
		cout << "digit  " << i << "  ( " << samcount << " / 1000 ) = "
				<< samcount * digitsSamPercent << endl;
		fsummary << "digit  " << i << "  ( " << samcount << " / 1000 ) = "
				<< samcount * digitsSamPercent << endl;

	}

	fsummary << "---------------------------------" << Algorithm
			<< "---------------------------------------------" << endl;
	fsummary << "  Testing  " << test_file << " features pair using "
			<< Algorithm << " Classifier" << endl;
	fsummary << "Accuracy = " << accuracy << " % " << " ( " << actual_accuracy
			<< "/" << test_data->n_examples << " ) " << endl;
	fsummary << "Results achieved using follwoing parameters " << endl;
	fsummary << " Number of Features = " << num_of_features << "  C= " << Var_C
			<< "  g = " << Variable_g << " h = " << HiddenNeurons << endl;

	cout << "[Summary]" << endl;
	cout << "[log] ---------------------------------" << Algorithm
			<< "---------------------------------------------" << endl;
	cout << "[log] Testing  " << test_file << " features pair using "
			<< Algorithm << " Classifier" << endl;
	cout << "[log] Accuracy = " << accuracy << " % " << " ( "
			<< actual_accuracy << "/" << test_data->n_examples << " ) " << endl;
	cout << "[log] Results achieved using follwoing parameters " << endl;
	cout << "[log] Number of Features = " << num_of_features << " C= " << Var_C
			<< "  g = " << Variable_g << " h = " << HiddenNeurons << endl;
	cout << "[Summary]" << endl;
    cout.flush();
	//allocator->free(test_data);
	//freeTestMachineOVO();

}

void RunFeaturesClassifiers::freeTestMachineOVO() {

	for (int i = 0; i < classes; i++) {

		for (int j = 0; j < classes; i++) {
			delete machines[i][j];

		}
		//	machinesSVM[i] = new (allocator)  SVMClassification*[classes];
		delete machines[i];

	}

	delete machines;

}
void RunFeaturesClassifiers::LoadMachineForTestOVO(int alg, char* model) {
	cout << "inside load machine ,,,,,,,,,,,,," << endl;
	cout << " the model file is " << model << endl;
	DiskXFile model_file(model, "r");
	if (alg == 5) {

		machines = new Machine**[classes];
		//cout<<"creating the machines .............."<<endl;
		for (int i = 0; i < classes; i++) {
			machines[i] = new Machine*[classes];

		}

		//float g=0.1;//0.0275;//0.04;//gamma
		//float C=100;
		Kernel *kernel = new GaussianKernel(Variable_g);
		//   cout<<"creating the kernels.............."<<endl;
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				cout << " loading  " << i << " vs " << j << " ." << endl;

				machines[i][j] = new SVMClassification(kernel);
				machines[i][j]->setROption("C", Var_C);
				//svms[i][j]->setROption("cache size",10)	;
				machines[i][j]->loadXFile(&model_file);
			}
		}
		return;
	}

	if (alg == 2) {

		machines = new Machine**[classes];
		Linear ***lin_layer = new Linear**[classes];
		Tanh ***out_layer = new Tanh**[classes];

		for (int i = 0; i < classes; i++) {
			machines[i] = new Machine*[classes];
			lin_layer[i] = new Linear*[classes];
			out_layer[i] = new Tanh*[classes];

		}
		for (int i = 0; i <= 8; i++) {
			for (int j = i + 1; j <= 9; j++) {

				lin_layer[i][j] = new Linear(num_of_features, 1);
				out_layer[i][j] = new Tanh(1);

				machines[i][j] = new ConnectedMachine();
				((ConnectedMachine*) machines[i][j])->addFCL(lin_layer[i][j]);
				((ConnectedMachine*) machines[i][j])->addFCL(out_layer[i][j]);
				((ConnectedMachine*) machines[i][j])->build();
				machines[i][j]->loadXFile(&model_file);
			}
		}

	}
	if (alg == 1) {

		machines = new Machine**[classes];

		bs=new float*[classes];
		//cout<<"creating the machines .............."<<endl;
		for (int i = 0; i < classes; i++) {
			machines[i] = new Machine*[classes];

			bs[i]=new float[classes];
			for (int j = 0; j <classes; j++) {
				bs[i][j]=0;
			}

		}


		//float g=0.1;//0.0275;//0.04;//gamma
		//float C=100;
		Kernel *kernel = new DotKernel(Variable_s);

		 Ws=new Sequence(45,num_of_features);


         int count=0;
		//   cout<<"creating the kernels.............."<<endl;
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				cout << " loading  " << i << " vs " << j << " ." << endl;

				SVMClassification* svm = new SVMClassification(kernel);
				machines[i][j]=svm;
				machines[i][j]->setROption("C", Var_C);

				//svms[i][j]->setROption("cache size",10)	;
				machines[i][j]->loadXFile(&model_file);

				bs[i][j]=svm->b;


				//bs[count]= svms[i][j]->b;

							for(int n=0; n<num_of_features; n++)
								Ws->frames[count][n]=0;

							for(int s=0; s<svm->n_support_vectors; s++)
							{
								for(int n=0; n<num_of_features; n++)
									Ws->frames[count][n]+= (svm->sv_alpha[s])*(svm->sv_sequences[s]->frames[0][n]);
							}
							count++;
			}
		}
		return;
	}

}
float RunFeaturesClassifiers::GetResultFromMachinesOVO(Sequence* input,
		int target, int t, int alg, float* votes) {

	float score = 0;
	int Classifier_Number_Feat;
//	if (alg == 1) {///svm linear....
//		//			score = 0;
//		//			for (int n = 0; n < Num_Of_Features; n++)
//		//				score += (input->frames[0][n]) * (Ws->frames[0][n]);
//	} else if (alg == 5) {
		//			classifier->forward(input);
		//			score = classifier->outputs->frames[0][0];

		for (int i = 0; i < 10; i++)
			votes[i] = 0;
		real first_max_value;
		long first_max_index;
		int sampleError = 0;
		int SampleErrori;
		int SampleErrorj;
           int countClassifier=-1;
		for (int i = 0; i < 9; i++)
			for (int j = i + 1; j <= 9; j++) {
				countClassifier++;
				if (useDifferentFeatures) {
					Classifier_Number_Feat = FeatCountMat[i][j];
				} else {
					Classifier_Number_Feat = num_of_features;
				}
				int index = 0;

				Sequence* Featinputs = new Sequence(1, Classifier_Number_Feat);
				if (useDifferentFeatures) {
					for (int f = 0; f < Classifier_Number_Feat; f++) {
						index = FeaturesIndex[i][j][f];
						Featinputs->frames[0][f] = input->frames[0][index];
					}
				} else {
					for (int f = 0; f < num_of_features; f++) {
						Featinputs->frames[0][f] = input->frames[0][f];
					}

				}

				//(****************************************************chabnge here for the features...........
				//ForwardOVOInput
				real out = ForwardOVOInput(Featinputs,alg,i,j,countClassifier);
			//	machines[i][j]->forward(Featinputs);
			//	real out = machines[i][j]->outputs->frames[0][0];



				///********** i need to cont the acurracy for this classiferi i vs j
				if (target == i) {
					if (out > 0) {
						classifiersAccuracyForTheirDigits[i][j]++;
					} else {
						sampleError++;
						SampleErrori = i;
						SampleErrorj = j;
						ClassifiersErrors[i][j]++;
						if (WriteScores) {

							cout << " Sample  " << t << " has " << sampleError
									<< "  in classifier " << SampleErrori
									<< " vs. " << SampleErrorj << endl;
							//<<"  Error in its lead to ";
							fscores << " Sample  " << t << " has "
									<< sampleError << "  in classifier "
									<< SampleErrori << " vs. " << SampleErrorj
									<< endl;// <<"  Error in its lead to ";

						}

					}

				}
				if (target == j) {
					if (out < 0) {
						classifiersAccuracyForTheirDigits[i][j]++;
					} else {
						sampleError++;
						SampleErrori = i;
						SampleErrorj = j;
						ClassifiersErrors[i][j]++;
						if (WriteScores) {
							cout << " Sample  " << t << " has " << sampleError
									<< "  in classifier " << SampleErrori
									<< " vs. " << SampleErrorj << endl;
							//<<"  Error in its lead to ";
							fscores << " Sample  " << t << " has "
									<< sampleError << "  in classifier "
									<< SampleErrori << " vs. " << SampleErrorj
									<< endl;// <<"  Error in its lead to ";

						}
					}

				}
				if (out > 0)
					votes[i]++;
				else
					votes[j]++;

				delete Featinputs;

			}

		// finding value and index of first maximum
		LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);

		score = first_max_index;
		if (WriteScores) {
			if (sampleError > 0) {

				cout << " Sample  " << t << " has " << sampleError
						<< "  in classifiers " << "  Errors in its lead to ";
				fscores << " Sample  " << t << " has " << sampleError
						<< "  in classifiers " << "  Errors in its lead to ";

				if (score != target) {
					cout << "  un correct classifying of  " << target
							<< "  to " << score << endl;
					fscores << "  un correct classifying of  " << target
							<< "  to " << score << endl;
					classifiedWrongError++;
				} else {
					cout << "no porblem .. " << endl;
					fscores << "no porblem .. " << endl;
					classifiedWrongNoProblem++;
				}
			}
		}

		if (score == target) {
			DigitsAccuracy[target]++;
		} else {

		}

	//}



	return score;
}


float RunFeaturesClassifiers::ForwardOVOInput(Sequence* input, int alg, int i, int j , int count ){


	if (alg==1){

	     real decision_scores(bs[i][j]);
				for(int n=0; n<num_of_features; n++)
					decision_scores+= (input->frames[0][n])*(Ws->frames[count][n]);

				cout<<"  decision_scores "<<decision_scores<<endl;
			return decision_scores;
	}
	if (alg==5){


		        machines[i][j]->forward(input);
			   real out = machines[i][j]->outputs->frames[0][0];
                    return out;
	}

}

/////////////////////////////////////////////OVAAAAAAAAAAAAAAAAAAAAAAAA///////////////////////////////////////////////////////////////////
void RunFeaturesClassifiers::GeneratePerdictFilesOVA(DataSet* test_data,
		int alg) {

	for (int i = 0; i < 9; i++) {
		for (int j = i + 1; j <= 9; j++) {

			char* name = new char[200];
			strcpy(name, "");
			strcat(name, "Predict_");
			char cVal[32];
			sprintf(cVal, "%i", alg);
			strcat(name, cVal);
			strcat(name, "_Classifier_");

			sprintf(cVal, "%i", i);
			strcat(name, cVal);

			strcat(name, "_");

			sprintf(cVal, "%i", j);
			strcat(name, cVal);

			strcat(name, ".txt");

			ofstream fpredict(name);
			cout << " Teseting  " << i << " vs " << j << " ." << endl;

			for (int t = 0; t < test_data->n_examples; t++) {
				//cout<<" t = "<<t<<endl;
				if ((t % 500) == 0)
					cout << t << endl;

				test_data->setExample(t);
				int d = test_data->targets->frames[0][0];//actual value of sample
				float score = GetResultFromPredictMachineOVA(test_data->inputs,
						alg, machines[i][j]);
				if (score > 0) {

					fpredict << "1" << endl;
				} else {

					fpredict << "-1" << endl;
				}

			}
			fpredict.close();
			delete name;

		}
	}

}
float RunFeaturesClassifiers::GetResultFromPredictMachineOVA(Sequence* input,
		int alg, Machine* classifier) {

	float score = 0;
	if (alg == 1) {///svm linear....
		score = 0;
		//	for(int n=0; n<Num_Of_Features; n++)
		//	score+= (input->frames[0][n])*(Ws->frames[0][n]);
	} else {
		classifier->forward(input);
		score = classifier->outputs->frames[0][0];

	}
	return score;
}

void RunFeaturesClassifiers::TrainMachineOVA(char* train_file, char* model,
		int alg) {

	//Allocator *allocator = new Allocator;

	//int num_of_features=200;
	//char train_file[]="C:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	//DiskXFile model_file("model", "w");

	//int num_of_features=200;
	//char train_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	if (model == NULL) {
		if (alg == 5) {
			model = "svm_model";
		} else if (alg == 1) {
			model = "svm_linear_model";
		} else if (alg == 2) {
			model = "linear_model";
		}
	}
	DiskXFile model_file(model, "w");

	DataSet *train_set;
	train_set
			= new (allocator) MatDataSet(train_file, num_of_features, 1, false, -1, false);

	LoadMachineForTrainOVA(alg);

	for (int i = 0; i < 9; i++) {
		for (int j = i + 1; j <= 9; j++) {

			cout << " Training " << i << " vs " << j << " ." << endl;

			MemoryDataSet *current_train_set = GetCurretDataSetOVA(i, j,
					train_set);

			trainers[i][j]->train(current_train_set, NULL);
			machines[i][j]->saveXFile(&model_file);

		}
	}
	// delete allocator;
}

void RunFeaturesClassifiers::LoadMachineForTrainOVA(int alg) {

	if (alg == 5) {

		machines = new (allocator) Machine**[classes];
		trainers = new (allocator) Trainer**[classes];
		for (int i = 0; i < classes; i++) {
			machines[i] = new (allocator) Machine*[classes];
			trainers[i] = new (allocator) Trainer*[classes];
		}

		//float g=0.1;//0.0275;//0.04;//gamma
		//float C=100;
		Kernel *kernel = new (allocator) GaussianKernel(Variable_g);

		for (int i = 0; i <= 8; i++) {
			for (int j = i + 1; j <= 9; j++) {
				cout << " Init " << i << " vs " << j << " ." << endl;

				machines[i][j] = new (allocator) SVMClassification(kernel);
				SVMClassification* svm = (SVMClassification*) machines[i][j];
				machines[i][j]->setROption("C", Var_C);
				//svms[i][j]->setROption("cache size",10)	;

				trainers[i][j] = new (allocator) QCTrainer(svm);
			}
		}

	}
	if (alg == 2) {


	}
	if(alg==1){

	}
	if(alg==6){

	}

}

MemoryDataSet * RunFeaturesClassifiers::GetCurretDataSetOVA(int i, int j,
		DataSet* train_set) {

	/***********Now based on the features comptue the ************/
	MemoryDataSet *current_train_set = new (allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for (int n = 0; n < 20000; n++) {
		inputs[n] = new (allocator) Sequence(1, num_of_features);
		targets[n] = new (allocator) Sequence(1, 1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);

	int counter = 0;
	for (long k = 0; k < train_set->n_examples; k++) {

		train_set->setExample(k);
		int d = train_set->targets->frames[0][0];

		if (d == i || d == j) {

			current_train_set->setExample(counter);
			////**************************************************change here...............
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
	current_train_set->setExample(0);
	current_train_set->n_examples = counter;
	cout << counter << endl;
	return current_train_set;

}

void RunFeaturesClassifiers::RunTestsOVA(char* test_file, char* model,
		char* Algorithm, int alg) {

	//Allocator *allocator = new Allocator;


	LoadMachineForTestOVA(alg, model);//,  bs, Ws

	cout << " Reading the test set " << endl;
	DataSet *test_data;
	test_data
			= new (allocator) MatDataSet(test_file, num_of_features, 1, false, -1, false);

	/*Local variables & Initialization*/
	float actual_accuracy(0);
	float positivesamples(0);
	float negative(0);
	float n_iter = 10;
	Timer timer;
	float pos, neg;
	pos = 0;
	neg = 0;
	actual_accuracy = 0;
	int c2Count, c1Count;
	c2Count = 0;
	c1Count = 0;
	if (GeneratePredictFiles) {
		GeneratePerdictFilesOVA(test_data, alg);
	}
	cout
			<< "After generateing the predict computing the result form my program "
			<< endl;
	for (int t = 0; t < test_data->n_examples; t++) {
		//cout<<" t = "<<t<<endl;
		if ((t % 300) == 0)
			cout << t << endl;

		test_data->setExample(t);
		int d = test_data->targets->frames[0][0];//actual value of sample

		float *votes = new float[10];
		float score = GetResultFromMachinesOVA(test_data->inputs, alg, votes);

		if (score == d) {
			actual_accuracy++;
		} else {
			//check votes..................
			//errrorssssssss.

		}

	}

	float accuracy = (actual_accuracy / (float) test_data->n_examples) * 100;

	fsummary << "---------------------------------" << Algorithm
			<< "---------------------------------------------" << endl;
	fsummary << "  Testing  " << test_file << " features pair using "
			<< Algorithm << " Classifier" << endl;
	fsummary << "Accuracy = " << accuracy << " % " << " ( " << actual_accuracy
			<< "/" << test_data->n_examples << " ) " << endl;
	fsummary << "Results achieved using follwoing parameters " << endl;
	fsummary << " Number of Features = " << num_of_features << "  C= " << Var_C
			<< "  g = " << Variable_g << " h = " << HiddenNeurons << endl;

	cout << "[Summary]" << endl;
	cout << "[log] ---------------------------------" << Algorithm
			<< "---------------------------------------------" << endl;
	cout << "[log] Testing  " << test_file << " features pair using "
			<< Algorithm << " Classifier" << endl;
	cout << "[log] Accuracy = " << accuracy << " % " << " ( "
			<< actual_accuracy << "/" << test_data->n_examples << " ) " << endl;
	cout << "[log] Results achieved using follwoing parameters " << endl;
	cout << "[log] Number of Features = " << num_of_features << " C= " << Var_C
			<< "  g = " << Variable_g << " h = " << HiddenNeurons << endl;
	cout << "[Summary]" << endl;

}

void RunFeaturesClassifiers::LoadMachineForTestOVA(int alg, char* model) {

	DiskXFile model_file(model, "r");
	if (alg == 5) {

		machines = new (allocator) Machine**[classes];

		for (int i = 0; i < classes; i++) {
			machines[i] = new (allocator) Machine*[classes];

		}

		//float g=0.1;//0.0275;//0.04;//gamma
		//float C=100;
		Kernel *kernel = new (allocator) GaussianKernel(Variable_g);

		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				cout << " Init " << i << " vs " << j << " ." << endl;

				machines[i][j] = new (allocator) SVMClassification(kernel);
				machines[i][j]->setROption("C", Var_C);
				//svms[i][j]->setROption("cache size",10)	;
				machines[i][j]->loadXFile(&model_file);
			}
		}

	}
	if (alg == 2) {

		machines = new (allocator) Machine**[classes];
		Linear ***lin_layer = new (allocator) Linear**[classes];
		Tanh ***out_layer = new (allocator) Tanh**[classes];

		for (int i = 0; i < classes; i++) {
			machines[i] = new (allocator) Machine*[classes];
			lin_layer[i] = new (allocator) Linear*[classes];
			out_layer[i] = new (allocator) Tanh*[classes];

		}
		for (int i = 0; i <= 8; i++) {
			for (int j = i + 1; j <= 9; j++) {

				lin_layer[i][j] = new (allocator) Linear(num_of_features, 1);
				out_layer[i][j] = new (allocator) Tanh(1);

				machines[i][j] = new (allocator) ConnectedMachine();
				((ConnectedMachine*) machines[i][j])->addFCL(lin_layer[i][j]);
				((ConnectedMachine*) machines[i][j])->addFCL(out_layer[i][j]);
				((ConnectedMachine*) machines[i][j])->build();
				machines[i][j]->loadXFile(&model_file);
			}
		}

	}
	if (alg == 1) {

	}

}
float RunFeaturesClassifiers::GetResultFromMachinesOVA(Sequence* input,
		int alg, float* votes) {

	float score = 0;
	if (alg == 1) {///svm linear....
		//			score = 0;
		//			for (int n = 0; n < Num_Of_Features; n++)
		//				score += (input->frames[0][n]) * (Ws->frames[0][n]);
	} else if (alg == 5) {
		//			classifier->forward(input);
		//			score = classifier->outputs->frames[0][0];

		for (int i = 0; i < 10; i++)
			votes[i] = 0;
		real first_max_value;
		long first_max_index;

		for (int i = 0; i < 9; i++)
			for (int j = i + 1; j <= 9; j++) {
				//(****************************************************chabnge here for the features...........
				machines[i][j]->forward(input);
				real out = machines[i][j]->outputs->frames[0][0];
				if (out > 0)
					votes[i]++;
				else
					votes[j]++;

			}

		// finding value and index of first maximum
		LibraryFunctions::getMax(votes, 10, first_max_value, first_max_index);

		score = first_max_index;

	}
	return score;
}

