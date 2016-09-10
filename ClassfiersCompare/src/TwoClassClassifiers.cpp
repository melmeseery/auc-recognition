/*
 * TwoClassClassifiers.cpp
 *
 *  Created on: Jan 7, 2009
 *      Author: Maha
 */

#include "TwoClassClassifiers.h"

TwoClassClassifiers::TwoClassClassifiers() {
	// TODO Auto-generated constructor stub
	ResultString=new  char[5000];

	strcpy(ResultString,"");

	fsummary.open("Summary.txt");

	GenerateStateFile=true;
    GenerateScoreFiles=false;

}

TwoClassClassifiers::~TwoClassClassifiers() {
	// TODO Auto-generated destructor stub
}



void TwoClassClassifiers::writeResultString(char* note){

	char* storeFilename=new char[320];
		 strcpy( storeFilename,"");// strcat( statFilename,"");
		 strcat(storeFilename,"All_Results_");
		 strcat( storeFilename,note);
		 strcat(storeFilename,".txt");
		 ofstream fout(storeFilename);
		 fout<<"   Results of the features    "<<note<<endl;
		 fout<<ResultString<<endl;




}


void TwoClassClassifiers::ConvertToARFFFormat(char* train_file,char* test_file,int  num_of_features){

	Allocator *allocator = new Allocator;
		cout<<"Reading data........."<<endl;


	float data;
		char* storeFilename=new char[320];
		 strcpy( storeFilename,"");// strcat( statFilename,"");
		 strcat(storeFilename,"ARFF_");
		 strcat( storeFilename,train_file);
		 strcat(storeFilename,".arff");
		 ofstream fout(storeFilename);
		 ////now write thre trian fiel
		 DataSet *test_data =new(allocator) MatDataSet(train_file, num_of_features,1,false,-1,false);
         fout<<"@RELATION  DigitsClassifier" <<endl;
    	 fout<<endl;
		 fout<<"@attribute 'Class' numeric  "<<endl;
		 for (int f = 1; f <=num_of_features; f++) {

			 fout<<"@attribute '"<<f<<"' numeric  "<<endl;
		 }
		 fout<<endl;
		 fout<<"@data"<<endl;
				for(int t = 0; t < test_data->n_examples; t++)
			{
				test_data->setExample(t);
				int d= test_data->targets->frames[0][0];

				fout<<d<<"  ";
				for (int f = 0; f < num_of_features; f++) {

						data=test_data->inputs->frames[0][f];
					//if (data!=0){
						fout<<" , "<<data;
					//}
				}
				fout<<endl;


			}


	fout.close();


	char*  storeFilename2=new char[320];
		 strcpy( storeFilename2,"");// strcat( statFilename,"");
		  strcat(storeFilename2,"ARFF_");
		 strcat( storeFilename2,test_file);
		 strcat(storeFilename2,".arff");
		 ofstream fout2(storeFilename2);
		test_data =new(allocator) MatDataSet(test_file, num_of_features,1,false,-1,false);

		  fout2<<"@RELATION  DigitsClassifier" <<endl;
		    	 fout2<<endl;
		 fout2<<"@attribute 'Class' numeric  "<<endl;
			 for (int f = 1; f <=num_of_features; f++) {

				 fout2<<"@attribute '"<<f<<"' numeric  "<<endl;
			 }
			 fout2<<endl;
			 fout2<<"@data"<<endl;

			for(int t = 0; t < test_data->n_examples; t++)
			{
				test_data->setExample(t);
				int d= test_data->targets->frames[0][0];

				fout2<<d<<"  ";
				for (int f = 0; f < num_of_features; f++) {

						data=test_data->inputs->frames[0][f];
					//if (data!=0){
						fout2<<" , "<<data;

//					}
				}
				fout2<<endl;


			}


	fout2.close();


	delete allocator;

}



void TwoClassClassifiers::ConvertToCSVFormat(char* train_file,char* test_file,int  num_of_features){

	Allocator *allocator = new Allocator;
		cout<<"Reading data........."<<endl;


	float data;
		char* storeFilename=new char[320];
		 strcpy( storeFilename,"");// strcat( statFilename,"");
		 strcat(storeFilename,"For_CSV_");
		 strcat( storeFilename,train_file);
		 strcat(storeFilename,".csv");
		 ofstream fout(storeFilename);
		 ////now write thre trian fiel
		 DataSet *test_data =new(allocator) MatDataSet(train_file, num_of_features,1,false,-1,false);

		 for (int f = 0; f < num_of_features; f++) {

			 fout<<f<<" ,  ";
		 }
		 fout<<endl;
				for(int t = 0; t < test_data->n_examples; t++)
			{
				test_data->setExample(t);
				int d= test_data->targets->frames[0][0];

				fout<<d<<"  ";
				for (int f = 0; f < num_of_features; f++) {

						data=test_data->inputs->frames[0][f];
					//if (data!=0){
						fout<<" , "<<data;
					//}
				}
				fout<<endl;


			}


	fout.close();


	char*  storeFilename2=new char[320];
		 strcpy( storeFilename2,"");// strcat( statFilename,"");
		  strcat(storeFilename2,"For_CSV_");
		 strcat( storeFilename2,test_file);
		 strcat(storeFilename2,".csv");
		 ofstream fout2(storeFilename2);
		test_data =new(allocator) MatDataSet(test_file, num_of_features,1,false,-1,false);
		 for (int f = 0; f < num_of_features; f++) {

			 fout2<<f<<" ,  ";
		 }
		 fout2<<endl;
			for(int t = 0; t < test_data->n_examples; t++)
			{
				test_data->setExample(t);
				int d= test_data->targets->frames[0][0];

				fout2<<d<<"  ";
				for (int f = 0; f < num_of_features; f++) {

						data=test_data->inputs->frames[0][f];
					//if (data!=0){
						fout2<<" , "<<data;

//					}
				}
				fout2<<endl;


			}


	fout2.close();


	delete allocator;

}

void TwoClassClassifiers::ConvertToSVMFormat(char* train_file,char* test_file,int  num_of_features){
	Allocator *allocator = new Allocator;
	cout<<"Reading data........."<<endl;


float data;
	char* storeFilename=new char[320];
	 strcpy( storeFilename,"");// strcat( statFilename,"");
	 strcat(storeFilename,"Formated_");
	 strcat( storeFilename,train_file);

	 ofstream fout(storeFilename);
	 ////now write thre trian fiel
	 DataSet *test_data =new(allocator) MatDataSet(train_file, num_of_features,1,false,-1,false);
		for(int t = 0; t < test_data->n_examples; t++)
		{
			test_data->setExample(t);
			int d= test_data->targets->frames[0][0];

			fout<<d<<"  ";
			for (int f = 0; f < num_of_features; f++) {

					data=test_data->inputs->frames[0][f];
				if (data!=0){
					fout<<f<<":"<<data<<"  ";
				}
			}
			fout<<endl;


		}


fout.close();


char*  storeFilename2=new char[320];
	 strcpy( storeFilename2,"");// strcat( statFilename,"");
	  strcat(storeFilename2,"Formated_");
	 strcat( storeFilename2,test_file);
	 //strcat(storeFilename2,".csv");
	 ofstream fout2(storeFilename2);
	test_data =new(allocator) MatDataSet(test_file, num_of_features,1,false,-1,false);

		for(int t = 0; t < test_data->n_examples; t++)
		{
			test_data->setExample(t);
			int d= test_data->targets->frames[0][0];

			fout2<<d<<"  ";
			for (int f = 0; f < num_of_features; f++) {

					data=test_data->inputs->frames[0][f];
				if (data!=0){
					fout2<<f<<":"<<data<<"  ";

				}
			}
			fout2<<endl;


		}


fout2.close();


delete allocator;


}

//void TwoClassClassifiers::svmRBFTest(char* filename, int n_features, char* ifilename,float g, float C ,char* note, char* model){}
//
//void TwoClassClassifiers::svmRBFTrain(char * filename, int n_features, float g, float C, char* model){}



void TwoClassClassifiers::svmLinearTest(char* filename, int n_features, char* ifilename,float g, float C ,char* note, char* model){


	if ( ifilename!=NULL)
	{
		LibraryFunctions lib;
		lib.getFiles(ifilename);
	//getFiles(ifilename);
	}
	n_features=LibraryFunctions::getFeatureCount(filename);
	 	//ofstream fout("test_stats_SVM.txt");
	Allocator *allocator = new Allocator;

	//char test_file[]="C:/datasets/mahdbase_testing_set.txt";

	char* test_file=filename;
	if (model==NULL)
			{
			//	model="Linear_model_TwoClass";
		model="modelSVMLinear";
			}
	DiskXFile model_file(model, "r");

	Kernel *kernel = new(allocator) DotKernel(g);

	int total_num_of_support_vectors(0);

	SVM* svms;// = allocator->alloc(sizeof(SVM));
	svms=new (allocator) SVMClassification(kernel);
	svms->setROption("C", C);

	Sequence Ws(1,n_features);
	float bs;

	total_num_of_support_vectors+= svms->n_support_vectors;
		//loading SVM models in turn
		svms->loadXFile(&model_file);
		bs= svms->b;

		for(int n=0; n<n_features; n++)
		Ws.frames[0][n]=0;

		for(int s=0; s<svms->n_support_vectors; s++)
		{
			for(int n=0; n<n_features; n++)
				Ws.frames[0][n]+= (svms->sv_alpha[s])*(svms->sv_sequences[s]->frames[0][n]);
		}


	cout<<"number of support vectors = "<<total_num_of_support_vectors<<endl;



	// reading testing data from file and converting it to one hot class format
//	Sequence *class_labels= new(allocator) Sequence( NO_OF_CLASSES,1);
//	int i,j;
//	for(i=0;i<NO_OF_CLASSES;i++)
//	{
//		for(j=0;j<NO_OF_CLASSES;j++)
//		{
//			if(i==DIGIT_TRAINED )
//				class_labels->frames[i][j]=1;
//			else
//				class_labels->frames[i][j]=-1;
//		}
//}
	cout<<"Reading data........."<<endl;
	DataSet *test_data =new(allocator) MatDataSet(test_file, n_features,1,false,-1,false);



	//creating and initializing the confusion matrix
	/*real confusion_matrix[NO_OF_CLASSES][NO_OF_CLASSES];
	for(int i=0; i<NO_OF_CLASSES; i++)
		for(int j=0; j<NO_OF_CLASSES; j++)
			confusion_matrix[i][j]=0;
*/

	real actual_accuracy(0);
	real accuracy(0);//accuracy after rejection
	long n_rejected(0); // number of rejected samples
	long n_false_rejected(0); // number of falsely rejected samples
long postive,neg,pos,negative;
negative=0;
postive=0;
neg=0;
pos=0;
int c2Count,c1Count;
c2Count=0;
c1Count=0;

	Timer timer;
	timer.stop();

	long first_max_index=5;
	cout<<"Start forwding exapmles ...."<<endl;

 char* scoreFilename=new char[320];
 strcpy( scoreFilename,"");// strcat( statFilename,"");
  strcat( scoreFilename,"Test_Linear_SVM_Score_");
 strcat( scoreFilename,note);
 strcat( scoreFilename,".txt");
 ofstream fscore(scoreFilename);

	 char* statFilename=new char[200];
	 strcpy( statFilename,"");
	 strcat( statFilename,"Test_Linear_SVM_State_");
	  strcat( statFilename,note);
	  strcat( statFilename,".txt");
	ofstream fout(statFilename);
//	delete scoreFilename;
//	delete statFilename;

		  cout<<"Forwarding samples .......... ."<<endl;
		  	//long fivesamples=0;
           fscore<<"Result of the  svm testing for the two class of "<<note<<endl;
			fscore<<" ID "<<"   "<<"score"<<"       Target class"<<"     Recognized as "<<endl;



			//long numberOFSamples=test_data->n_examples;

	for(int t = 0; t < test_data->n_examples; t++)
	{

		if (t%100==0)
			cout<<t<<endl;


		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample



		if (d==DIGIT_TRAINED){
			c1Count++;
		}
		else {

			c2Count++;
		}

		//real decision_scores(0);//[NO_OF_CLASSES];// to hold decision scores

		// forwarding test sample in all SVMs
		real sum(0);
		for(int i = 0; i < NO_OF_CLASSES; i++)
		{
			if (i==DIGIT_TRAINED ){
			timer.resume();
			svms->forward(test_data->inputs);
			timer.stop();

			real decision_scores(bs);
			for(int n=0; n<n_features; n++)
				decision_scores+= (test_data->inputs->frames[0][n])*(Ws.frames[0][n]);

			//decision_scores =svms->outputs->frames[0][0]; //exp(svms[i]->outputs->frames[0][0]);




		//	fscore<<t<<"   "<<	decision_scores<<"   " <<d<<endl;
			//sum+=decision_scores;
			if (decision_scores>0)
			{
			postive++;
			fscore << t << "   " << decision_scores << "   "  << d << "  it is recognizied as class 1 " << endl;


			if(DIGIT_TRAINED==d){
           //supposed to be 5 and is 5
			actual_accuracy++;



			}
			else {//d != trained digit  it classifier as 5 and it is not 5
				pos++;
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				*/


				//cout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
				//fout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
           //+ve sample
			}///if +ve example



			}
		else{//score is less  !=d // supposed to be 5 but not classifed as 5
			fscore << t << "   " << decision_scores << "   "  << d << "  it is recognizied as class -1 " << endl;
			negative++;
			if (d==DIGIT_TRAINED)

			{

				neg++;
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				*/
			///	cout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
				//fout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
			//supposed to be 5 but defined as something else.
			// -ve sample
			}//if-ve example
			else{
				// it is classified as is -1 and recognized as -1
										//supposed to be 5 and is 5
											actual_accuracy++;
			}

			}//else for not recognizied


			}


	}
		}//for t samples

//	fout<<"  Total number of samples "<<test_data->n_examples<<endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
//	fout<<"  First Class Sample Count "<<c1Count<<" Second Class Count "<<c2Count <<endl;
//	fout<<"  Samples with score > 0:  "<<postive<<endl;
//    fout<<"  The false +ve count  = "<<pos<<"  and the false -ve count  = "<<neg<<endl;
//    fout<<"  Samples correctly recognized   : "<<actual_accuracy<<"   sample "<<endl;
//

	fout<<" Testing the Linear Kernel SVM with the "<<note<<"   feature "<<endl;


	fout << "  Total number of samples " << test_data->n_examples << endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
	fout << "  First Class Sample Count " << c1Count << " Second Class Count "<< c2Count << endl;
	fout << "  Samples with score > 0:  " << postive << " Recognized as from first class "<< endl;
	fout << "  Samples with score < 0:  " << negative << "Recognized as from second class"<< endl;
	fout << "  The count of classified wrongly as class 1  = " << pos	<< "  and the count of classified wrongly as class 2  = " << neg << endl;

	fout << "  Samples correctly recognized   = " << actual_accuracy
			<< "   samples " << endl;

	float accuracy11;
	accuracy11= (actual_accuracy/(float)test_data->n_examples)*100;
	fout << "  Accuracy %  = " << accuracy11
				<< "   % " << endl;

	strcat(ResultString,"\n--------------------------------------");
	strcat(ResultString,"\n  Testing the Linear Kernel SVM with the ");
	strcat(ResultString,note);


	strcat(ResultString,"  Feature ");

	char cVal2[32];

 sprintf(cVal2,"%f", g);
	strcat(ResultString," With g= ");
		strcat(ResultString,cVal2);
//			strcat(ResultString," and c=");
//			char cVal3[32];
//					sprintf(cVal2,"%f",C);
//
//					strcat(ResultString,cVal3);



	strcat(ResultString,"\n    Accuracy %  = " );


	char cVal[32];
	sprintf(cVal,"%f",accuracy11);

	strcat(ResultString,cVal);



	fout.close();
	fscore.close();

	delete(allocator);



}

//void TwoClassClassifiers::svmLinearPramters(char * filename, int n_features, float g, float C, char* model){
//
//
//
//}

void TwoClassClassifiers::svmLinearTrain(char * filename, int n_features, float g, float C, char* model){

	Allocator *allocator = new Allocator;

		//char train_file[]="C:/datasets/mahdbase_training_set.txt";

		char* train_file = filename;
		if (model==NULL)
		{
			model="modelSVMLinear";
		}

		DiskXFile model_file(model, "w");
		n_features = LibraryFunctions::getFeatureCount(filename);

            //float eps=1;
//            if (g==0.1) eps=3.1;


		//Kernel *kernel = new (allocator) GaussianKernel(g);
		Kernel *kernel = new(allocator) DotKernel(g);

		SVM* svms;
		svms = new (allocator) SVMClassification(kernel);
		svms->setROption("C", C);
		///svms->setROption();


		cout << "reading the training set " << endl;

		// reading the training set
		MatDataSet	*train_data =new (allocator) MatDataSet(train_file, n_features, 1, false, -1, false);

		QCTrainer trainer(svms);
		//trainer.setIOption("max iter",1000);
	//	trainer.setROption("end accuracy",eps);
		trainer.setIOption("iter message",50000);
		//trainer.setROption("eps shrink",eps);


		cout << "traininng  " << endl;
		trainer.train(train_data, NULL );
		cout << "Saving file  " << endl;
		// saving SVM model in turn
		svms->saveXFile(&model_file);
		//}

		//}
		delete (allocator);


}



void TwoClassClassifiers::svmRbfTest(char* filename, int n_features, char* ifilename,float g, float C ,char* note, char* model) {
	if ( ifilename!=NULL)
	{
		LibraryFunctions lib;
		lib.getFiles(ifilename);
	//getFiles(ifilename);
	}
	n_features=LibraryFunctions::getFeatureCount(filename);
	 	//ofstream fout("test_stats_SVM.txt");
	Allocator *allocator = new Allocator;

	//char test_file[]="C:/datasets/mahdbase_testing_set.txt";

	char* test_file=filename;
	if (model==NULL)
			{
			//	model="Linear_model_TwoClass";
		model="modelSVM";
			}
	DiskXFile model_file(model, "r");


	// creating the SVMs
	//float g=0.03;//gamma
//	float C=50;
	Kernel *kernel = new(allocator) GaussianKernel(g);

	int total_num_of_support_vectors(0);

	// creating the SVMs
	//float g=0.03;//gamma
	//float C=50;
	//Kernel *kernel = new(allocator) GaussianKernel(g);
//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
	SVM* svms;// = allocator->alloc(sizeof(SVM));
	svms=new (allocator) SVMClassification(kernel);
	svms->setROption("C", C);




	//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
//for(int i = 0; i <  NO_OF_CLASSES; i++)
	//{if (i==DIGIT_TRAINED ){
	//	svms= new(allocator) SVMClassification(kernel);

		//loading SVM models in turn
		svms->loadXFile(&model_file);

		total_num_of_support_vectors+= svms->n_support_vectors;
	//}
//}

	cout<<"number of support vectors = "<<total_num_of_support_vectors<<endl;



	// reading testing data from file and converting it to one hot class format
//	Sequence *class_labels= new(allocator) Sequence( NO_OF_CLASSES,1);
//	int i,j;
//	for(i=0;i<NO_OF_CLASSES;i++)
//	{
//		for(j=0;j<NO_OF_CLASSES;j++)
//		{
//			if(i==DIGIT_TRAINED )
//				class_labels->frames[i][j]=1;
//			else
//				class_labels->frames[i][j]=-1;
//		}
//}
	cout<<"Reading data........."<<endl;
	DataSet *test_data =new(allocator) MatDataSet(test_file, n_features,1,false,-1,false);



	//creating and initializing the confusion matrix
	/*real confusion_matrix[NO_OF_CLASSES][NO_OF_CLASSES];
	for(int i=0; i<NO_OF_CLASSES; i++)
		for(int j=0; j<NO_OF_CLASSES; j++)
			confusion_matrix[i][j]=0;
*/

	real actual_accuracy(0);
	real accuracy(0);//accuracy after rejection
	long n_rejected(0); // number of rejected samples
	long n_false_rejected(0); // number of falsely rejected samples
long postive,neg,pos,negative;
negative=0;
postive=0;
neg=0;
pos=0;
int c2Count,c1Count;
c2Count=0;
c1Count=0;

	Timer timer;
	timer.stop();

	long first_max_index=5;
	cout<<"Start forwding exapmles ...."<<endl;

 char* scoreFilename=new char[320];
 strcpy( scoreFilename,"");// strcat( statFilename,"");
  strcat( scoreFilename,"Test_SVM_Score_");
 strcat( scoreFilename,note);
 strcat( scoreFilename,".txt");
 ofstream fscore(scoreFilename);

	 char* statFilename=new char[200];
	 strcpy( statFilename,"");
	 strcat( statFilename,"Test_SVM_State_");
	  strcat( statFilename,note);
	  strcat( statFilename,".txt");
	ofstream fout(statFilename);
//	delete scoreFilename;
//	delete statFilename;

		  cout<<"Forwarding samples .......... ."<<endl;
		  	//long fivesamples=0;
           fscore<<"Result of the  svm testing for the two class of "<<note<<endl;
			fscore<<" ID "<<"   "<<"score"<<"       Target class"<<"     Recognized as "<<endl;



			//long numberOFSamples=test_data->n_examples;

	for(int t = 0; t < test_data->n_examples; t++)
	{

		if (t%100==0)
			cout<<t<<endl;


		test_data->setExample(t);
		int d= test_data->targets->frames[0][0];//actual value of sample



		if (d==DIGIT_TRAINED){
			c1Count++;
		}
		else {

			c2Count++;
		}

		real decision_scores(0);//[NO_OF_CLASSES];// to hold decision scores

		// forwarding test sample in all SVMs
		real sum(0);
		for(int i = 0; i < NO_OF_CLASSES; i++)
		{
			if (i==DIGIT_TRAINED ){
			timer.resume();
			svms->forward(test_data->inputs);
			timer.stop();

			decision_scores =svms->outputs->frames[0][0]; //exp(svms[i]->outputs->frames[0][0]);
		//	fscore<<t<<"   "<<	decision_scores<<"   " <<d<<endl;
			//sum+=decision_scores;
			if (decision_scores>0)
			{
			postive++;
			fscore << t << "   " << decision_scores << "   "  << d << "  it is recognizied as class 1 " << endl;


			if(DIGIT_TRAINED==d){
           //supposed to be 5 and is 5
			actual_accuracy++;



			}
			else {//d != trained digit  it classifier as 5 and it is not 5
				pos++;
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				*/


				//cout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
				//fout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
           //+ve sample
			}///if +ve example



			}
		else{//score is less  !=d // supposed to be 5 but not classifed as 5
			fscore << t << "   " << decision_scores << "   "  << d << "  it is recognizied as class -1 " << endl;
			negative++;
			if (d==DIGIT_TRAINED)

			{

				neg++;
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				*/
			///	cout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
				//fout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
			//supposed to be 5 but defined as something else.
			// -ve sample
			}//if-ve example
			else{
				// it is classified as is -1 and recognized as -1
										//supposed to be 5 and is 5
											actual_accuracy++;
			}

			}//else for not recognizied


			}


	}
		}//for t samples

//	fout<<"  Total number of samples "<<test_data->n_examples<<endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
//	fout<<"  First Class Sample Count "<<c1Count<<" Second Class Count "<<c2Count <<endl;
//	fout<<"  Samples with score > 0:  "<<postive<<endl;
//    fout<<"  The false +ve count  = "<<pos<<"  and the false -ve count  = "<<neg<<endl;
//    fout<<"  Samples correctly recognized   : "<<actual_accuracy<<"   sample "<<endl;
//

	fout<<" Testing the RBF SVM with the "<<note<<"   feature "<<endl;
	fout << "  Total number of samples " << test_data->n_examples << endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
	fout << "  First Class Sample Count " << c1Count << " Second Class Count "<< c2Count << endl;
	fout << "  Samples with score > 0:  " << postive << " Recognized as from first class "<< endl;
	fout << "  Samples with score < 0:  " << negative << "Recognized as from second class"<< endl;
	fout << "  The count of classified wrongly as class 1  = " << pos	<< "  and the count of classified wrongly as class 2  = " << neg << endl;

	fout << "  Samples correctly recognized   = " << actual_accuracy
			<< "   samples " << endl;

	float accuracy11;
	accuracy11= (actual_accuracy/(float)test_data->n_examples)*100;
	fout << "  Accuracy %  = " << accuracy11
				<< "   % " << endl;
	strcat(ResultString,"\n--------------------------------------");
	strcat(ResultString,"\n  Testing the RBF kernel SVM with the ");
	strcat(ResultString,note);
	strcat(ResultString,"  Feature ");
	//strcat(ResultString,"\n--------------------------------------");
	char cVal2[32];

 sprintf(cVal2,"%f", g);

	strcat(ResultString," With g = ");
	strcat(ResultString,cVal2);
	strcat(ResultString," and c = ");
	char cVal3[32];
	sprintf(cVal3,"%f",C);
	strcat(ResultString,cVal3);

	strcat(ResultString,"\n    Accuracy %  = " );

	char cVal[32];
	sprintf(cVal,"%f",accuracy11);

	strcat(ResultString,cVal);

	fout.close();
	fscore.close();

	delete(allocator);
//
//	if (ifilename != NULL) {
//		LibraryFunctions lib;
//		lib.getFiles(ifilename);
//	}
//	n_features = LibraryFunctions::getFeatureCount(filename);
//	//ofstream fout("test_stats_SVM.txt");
//	Allocator *allocator = new Allocator;
//
//	//char test_file[]="C:/datasets/mahdbase_testing_set.txt";
//
//	char* test_file = filename;
//	DiskXFile model_file("modelSVM", "r");
//
//	// creating the SVMs
//	//float g=0.03;//gamma
//	//	float C=50;
//	Kernel *kernel = new (allocator) GaussianKernel(g);
//
//	int total_num_of_support_vectors(0);
//	//ofstream foutd("debug_svm.txt");
//	// creating the SVMs
//	//float g=0.03;//gamma
//	//float C=50;
//	//Kernel *kernel = new(allocator) GaussianKernel(g);
//	//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
//	SVM* svms;// = allocator->alloc(sizeof(SVM));
//	svms = new (allocator) SVMClassification(kernel);
//	svms->setROption("C", C);
//
//	//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
//	//for(int i = 0; i <  NO_OF_CLASSES; i++)
//	//{if (i==DIGIT_TRAINED ){
//	//	svms= new(allocator) SVMClassification(kernel);
//
//	//loading SVM models in turn
//	svms->loadXFile(&model_file);
//
//	total_num_of_support_vectors += svms->n_support_vectors;
//	//}
//	//}
//
//	cout << "number of support vectors = " << total_num_of_support_vectors
//			<< endl;
//	//foutd<< "number of support vectors = " << total_num_of_support_vectors
////	<< endl;
//
//	// reading testing data from file and converting it to one hot class format
//	//	Sequence *class_labels= new(allocator) Sequence( NO_OF_CLASSES,1);
//	//	int i,j;
//	//	for(i=0;i<NO_OF_CLASSES;i++)
//	//	{
//	//		for(j=0;j<NO_OF_CLASSES;j++)
//	//		{
//	//			if(i==DIGIT_TRAINED )
//	//				class_labels->frames[i][j]=1;
//	//			else
//	//				class_labels->frames[i][j]=-1;
//	//		}
//	//}
//	cout << "Reading data........." << endl;
//
//	//foutd<< "Reading data........." << endl;
//	DataSet
//			*test_data =
//					new (allocator) MatDataSet(test_file, n_features, 1, false, -1, false);
//
//	//creating and initializing the confusion matrix
//	/*real confusion_matrix[NO_OF_CLASSES][NO_OF_CLASSES];
//	 for(int i=0; i<NO_OF_CLASSES; i++)
//	 for(int j=0; j<NO_OF_CLASSES; j++)
//	 confusion_matrix[i][j]=0;
//	 */
//
//	real actual_accuracy(0);
//	//real accuracy(0);//accuracy after rejection
//	long n_rejected(0); // number of rejected samples
//	long n_false_rejected(0); // number of falsely rejected samples
//	long postive, neg, pos;
//	postive = 0;
//	neg = 0;
//	pos = 0;
//	long negative=0;
//	int c2Count, c1Count;
//	c2Count = 0;
//	c1Count = 0;
//
//	Timer timer;
//	timer.stop();
//
//	long first_max_index = 5;
//	cout << "Start forwding exapmles ...." << endl;
//
//	ofstream fscore("Test_SVM_Scores.txt");
//	cout << "Forwarding samples .......... ." << endl;
//	//long fivesamples=0;
//
//	fscore << " ID " << "   " << "score" << "       Target class" << "     Recognized as "<< endl;
//	ofstream fout("test_stats_SVM.txt");
//
//	for (int t = 0; t < test_data->n_examples; t++) {
//
//		if (t % 100 == 0)
//			cout << t << endl;
//
//		test_data->setExample(t);
//		int d = test_data->targets->frames[0][0];//actual value of sample
//
//
//		if (d == DIGIT_TRAINED) {
//			c1Count++;  //first class
//		} else {
//
//			c2Count++;  // the other class
//		}
//
//		real decision_scores(0);//[NO_OF_CLASSES];// to hold decision scores
//
//		// forwarding test sample in all SVMs
//		real sum(0);
//	//	for (int i = 0; i < NO_OF_CLASSES; i++) {
//		//	if (i == DIGIT_TRAINED) {
//				timer.resume();
//				svms->forward(test_data->inputs);
//				timer.stop();
//
//				decision_scores = svms->outputs->frames[0][0]; //exp(svms[i]->outputs->frames[0][0]);
//
//				//
//				if (decision_scores > 0) {
//					postive++;
//					fscore << t << "   " << decision_scores << "   "  << d << "  it is recognizied as class 1 " << endl;
//
//					if ( d==DIGIT_TRAINED ) {
//						//supposed to be 5 and is 5
//						actual_accuracy++;
//
//					} else {   // else it is class -1 and recognized as 1 so it is false positive
//						//d != trained digit  it classifier as 5 and it is not 5
//						pos++;
//						/*char filename[50];
//						 int writer, pass, digit;
//
//						 writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
//						 pass = (t-(writer*100))/10;
//						 digit = (t-(writer*100))%10;
//						 sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
//						 */
//
//						//cout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
//						//fout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
//						//+ve sample
//					}///if +ve example
//
//
//				} else {//score is less  than 1 so it is recognizied as clas -1 or not 1
//					fscore << t << "   " << decision_scores << "   "  <<  d << "  it is recognizied as  -1 " << endl;
//					negative++;
//					if (d == DIGIT_TRAINED)  // it is supposed to be 1 and is classified as -1 ;
//
//					{
//
//						neg++;
//						/*char filename[50];
//						 int writer, pass, digit;
//
//						 writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
//						 pass = (t-(writer*100))/10;
//						 digit = (t-(writer*100))%10;
//						 sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
//						 */
//						///	cout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
//						//fout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = "<<decision_scores<<endl;
//						//supposed to be 5 but defined as something else.
//						// -ve sample
//					}//if-ve example
//					else{ //d =-1
//
//						// it is classified as is -1 and recognized as -1
//						//supposed to be 5 and is 5
//							actual_accuracy++;
//
//
//					}
//
//
//				}//else for not recognizied
//
//
//			//}
//
//		//}
//	}//for t samples
//
//	fout << "  Total number of samples " << test_data->n_examples << endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
//	fout << "  First Class Sample Count " << c1Count << " Second Class Count "
//			<< c2Count << endl;
//	fout << "  Samples with score > 0:  " << postive << " Recognized as from first class "<< endl;
//	fout << "  Samples with score < 0:  " << negative << "Recognized as from second class"<< endl;
//	fout << "  The false +ve count  = " << pos
//			<< "  and the false -ve count  = " << neg << endl;
//	fout << "  Samples correctly recognized   = " << actual_accuracy
//			<< "   samples " << endl;
//	float accuracy= (actual_accuracy/(float)test_data->n_examples)*100;
//		fout << "  Accuracy %  = " << accuracy
//					<< "   % " << endl;
//
//	delete (allocator);

}//function test


void TwoClassClassifiers::svmRbfTrain(char * filename, int n_features, float g, float C, char* model)
{

	Allocator *allocator = new Allocator;

	//char train_file[]="C:/datasets/mahdbase_training_set.txt";

	char* train_file = filename;
	if (model==NULL)
	{
		model="modelSVM";
	}

	DiskXFile model_file(model, "w");
	n_features = LibraryFunctions::getFeatureCount(filename);


	Kernel *kernel = new (allocator) GaussianKernel(g);

	SVM* svms;
	svms = new (allocator) SVMClassification(kernel);
	svms->setROption("C", C);


	cout << "reading the training set " << endl;

	// reading the training set
	MatDataSet	*train_data =new (allocator) MatDataSet(train_file, n_features, 1, false, -1, false);

	// training the SVMs (One vs All)
	//	for(int i = 0; i <  NO_OF_CLASSES; i++)
	//	{
	//if (i==DIGIT_TRAINED ){
	//TwoClassFormat *class_format= new(allocator)TwoClassFormat(train_data);
	//	Criterion* criterion = new(allocator) ClassNLLCriterion(class_format);
	QCTrainer trainer(svms);
	//Sequence class_labels( NO_OF_CLASSES, 1);
	//		for(int j = 0; j <  NO_OF_CLASSES; j++)
	//		{
	//			if(j == DIGIT_TRAINED )
	//				class_labels.frames[0][0] =  1;
	//			else
	//				class_labels.frames[0][0] = -1;
	//		}

	//		for (int t = 0; t < max; t++) {
	//
	//		}
	// Create the encoding format
	//	TwoClassFormat class_format(n_targets);
	// specifying cost function

	//		for(int t = 0; t < train_data->n_examples; t++)
	//			{
	//			 train_data->setExample(t);
	//			 int d= train_data->targets->frames[0][0];;
	//
	//			if (d== DIGIT_TRAINED){
	//				train_data->targets->frames[0][0]=1;;
	//			}
	//			else{
	//				train_data->targets->frames[0][0]=-1;;
	//			}
	//
	//
	//			}
	//	ClassFormatDataSet formatted_train_data(train_data, 2);
	cout << "traininng  " << endl;
	trainer.train(train_data, NULL );
	cout << "Saving file  " << endl;
	// saving SVM model in turn
	svms->saveXFile(&model_file);
	//}

	//}
	delete (allocator);

}


float TwoClassClassifiers::svmRbfOptimize_g(char* train_file, int samples,int num_of_features, float C){

	Allocator *allocator = new Allocator;

			//int num_of_features=200;

		 int Allsamples=samples;
		 int trainSamples=0.85*samples;
		 int validateSamples=0.15*samples;
			ofstream fout("g_optimzation_results_stage2.txt");

		//	char train_file[]="C:/datasets/arabic_gradproj_ourfeatures_training_set_full.txt";
			DataSet *dataset;
			dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

			float maxg=100;
			real maxAccuracy(0);

			Kernel *kernel ;
			SVM* svms;

			QCTrainer* trainers;


			//float g=0.1;

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


			for(long k=0; k<trainSamples; k++)
								{

									dataset->setExample(k);
									int d= dataset->targets->frames[0][0];


			//
										current_train_set->setExample(k);
			//
										for(int f=0; f<num_of_features; f++)
										{
											current_train_set->inputs->frames[0][f]=
												dataset->inputs->frames[0][f];
										}
										current_train_set->targets->frames[0][0]=dataset->targets->frames[0][0];


								}

			float g_values[]={33.333,50,100,150,200,300};

			for(int g_index=0; g_index<=5; g_index++)
			{
				cout<<"C="<<g_values[g_index]<<endl;


						kernel = new(allocator) GaussianKernel(g_values[g_index]);
						svms= new(allocator) SVMClassification(kernel);
						svms->setROption("C", C);
						trainers= new(allocator) QCTrainer(svms);


						current_train_set->n_examples= trainSamples;
						current_train_set->setExample(0);
						trainers->train(current_train_set,NULL);



				real accuracy(0);

				//testing on the validation set
				for(int t=trainSamples; t<samples; t++)
				{

					dataset->setExample(t);
					int d= dataset->targets->frames[0][0];
					//real decision[10];// to hold decision scores
//					real votes[10];
//					for(int i=0; i<10; i++)
//						votes[i]=0;
//
//
//					real first_max_value;
//					long first_max_index;


	//				for(int i=0;i<=8;i++)
	//				{
	//					for(int j=i+1;j<=9;j++)
	//					{

							svms->forward(dataset->inputs);

							real decision = svms->outputs->frames[0][0];

							if(decision>0)
								if (d==1)
									accuracy++;
							else
								if (d==-1)
									accuracy++;




				}

				 if (accuracy>=maxAccuracy)
				{
								 maxAccuracy=accuracy;
								 maxg=g_values[g_index];

				 }

				accuracy=accuracy/ (float)(samples-trainSamples);
				cout<<accuracy*100<<endl;

//				for(int i=0; i<=8; i++)
//					for(int j=i+1; j<=9; j++)
						allocator->free(svms);

				fout<<"C="<<g_values[g_index]<<" : "<<accuracy*100<<"%"<<endl;


			}



			delete(allocator);

	     return maxg;


}
float TwoClassClassifiers::svmRbfOptimize_C(char* train_file, int samples,int num_of_features,float g){


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

		Kernel *kernel ;
		SVM* svms;
		QCTrainer* trainers;



		//float g=0.1;

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


		for(long k=0; k<trainSamples; k++)
							{

								dataset->setExample(k);
								int d= dataset->targets->frames[0][0];


		//
									current_train_set->setExample(k);
		//
									for(int f=0; f<num_of_features; f++)
									{
										current_train_set->inputs->frames[0][f]=
											dataset->inputs->frames[0][f];
									}
									current_train_set->targets->frames[0][0]=dataset->targets->frames[0][0];


							}

		float C_values[]={33.333,50,100,150,200,300};

		for(int C_index=0; C_index<=5; C_index++)
		{
			cout<<"C="<<C_values[C_index]<<endl;


					kernel = new(allocator) GaussianKernel(g);
					svms= new(allocator) SVMClassification(kernel);
					svms->setROption("C", C_values[C_index]);
					trainers= new(allocator) QCTrainer(svms);


					current_train_set->n_examples= trainSamples;
					current_train_set->setExample(0);
					trainers->train(current_train_set,NULL);



			real accuracy(0);

			//testing on the validation set
			for(int t=trainSamples; t<samples; t++)
			{

				dataset->setExample(t);
				int d= dataset->targets->frames[0][0];
//				real decision[10];// to hold decision scores
//				real votes[10];
//				for(int i=0; i<10; i++)
//					votes[i]=0;
//
//
//				real first_max_value;
//				long first_max_index;


//				for(int i=0;i<=8;i++)
//				{
//					for(int j=i+1;j<=9;j++)
//					{

						svms->forward(dataset->inputs);

						real decision = svms->outputs->frames[0][0];

						if(decision>0)
							if (d==1)
								accuracy++;
						else
							if (d==-1)
								accuracy++;




			}

			 if (accuracy>=maxAccuracy)
			{
							 maxAccuracy=accuracy;
							 maxC=C_values[C_index];

			 }

			accuracy=accuracy/ (float)(samples-trainSamples);
			cout<<accuracy*100<<endl;

//			for(int i=0; i<=8; i++)
//				for(int j=i+1; j<=9; j++)
					allocator->free(svms);

			fout<<"C="<<C_values[C_index]<<" : "<<accuracy*100<<"%"<<endl;


		}



		delete(allocator);

     return maxC;
}


void TwoClassClassifiers::neuralTest(char* test_file, int n_hu, char* filesn, int feat ,char* note, char* model){
	if (filesn!=NULL)
		{
		LibraryFunctions lib;
			lib.getFiles(filesn);
		//getFiles(filesn);
	}
		//n_features=
		Allocator *allocator = new Allocator;
		int num_of_features = 	LibraryFunctions::getFeatureCount(test_file);

		  cout<<"Testing.......... ."<<endl;
		  //model


			if (model==NULL)
			{
				model="NeuralNetworkTwoClass";
				//model="modelSVM";
			}
		DiskXFile model_file(model, "r");

		// creating the mlp
		ConnectedMachine mlp[10];
		Linear *c1[10];
		Tanh *c2[10];
		Linear *c3[10];
		Tanh *c4[10];

		for(int count = my_digit; count <= my_digit; count++)
		{
			c1[count]= new(allocator) Linear(num_of_features,n_hu);
			c2[count]= new(allocator) Tanh(n_hu);
			c3[count]= new(allocator) Linear(n_hu,1);
			c4[count]= new(allocator) Tanh(1);
			mlp[count].addFCL(c1[count]);
			mlp[count].addFCL(c2[count]);
			mlp[count].addFCL(c3[count]);
			mlp[count].addFCL(c4[count]);
			mlp[count].build();
			mlp[count].setPartialBackprop();
			mlp[count].loadXFile(&model_file);
		}
		  cout<<"Reading data .......... ."<<endl;
		DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);

		/*Local variables & Initialization*/
		float actual_accuracy(0);
	    float positivesamples(0);
	    float negative(0);
		float n_iter=10;
		Timer timer;
		float pos,neg;
		pos=0;
		neg=0;
		actual_accuracy=0;
		int c2Count,c1Count;
		c2Count=0;
		c1Count=0;

//		ofstream fout("test_stats_Neural.txt");
//	      ofstream fscore("Test_Neural_Scores.txt");

	      char* scoreFilename=new char[300];
	      strcpy( scoreFilename,"");//
	       strcat( scoreFilename,"Test_Neural_Score_");
	       strcat( scoreFilename,note);
	       strcat( scoreFilename,".txt");
	       ofstream fscore(scoreFilename);
	      	 char* statFilename=new char[250];
	      	strcpy( statFilename,"");
	      	 strcat( statFilename,"Test_Neural_State_");
	      	 strcat( statFilename,note);
	      	 strcat( statFilename,".txt");
	      	ofstream fout(statFilename);


			  cout<<"Forwarding samples .......... ."<<endl;
			  	long fivesamples=0;

		fscore<<"Result of the neural testing for the two class of "<<note<<endl;
			fscore<<" ID "<<"   "<<"score"<<"       Target class"<<"     Recognized as "<<endl;

		/*Testing...*/
		for(int t = 0; t < test_data->n_examples; t++)
		{
			if((t%300) == 0)
				cout<<t<<endl;

			test_data->setExample(t);
			int d = test_data->targets->frames[0][0];//actual value of sample


			if (d==my_digit){
				c1Count++;
			}
			else {

				c2Count++;
			}

	        /*Get the score from the corresponding NN*/
			mlp[my_digit].forward(test_data->inputs);
			real score = mlp[my_digit].outputs->frames[0][0];
		//	fscore<<t<<"   "<<score <<endl;

//			if (d==my_digit)
//				fivesamples++;

	        if(score >= 0)
	        {
	        	fscore << t << "   " << score << "   "  << d << "  it is recognized as class 1 " << endl;

	            positivesamples++;
	            if(d == my_digit)
				{
	            	actual_accuracy++;
				}
				else
				{
					/*char filename[50];
					int writer, pass, digit;

					writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
					pass = (t-(writer*100))/10;
					digit = (t-(writer*100))%10;
					sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				//	fout<<"### "<<t<<" "<<filename<<"; score = "<<score<<endl;*/
	              pos++;


					//	cout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<score<<endl;
					//	fout<<"### "<<t<<" "<<getFile(t).data()<<"; score = "<<score<<endl;
				}
	        }
			else   if(score <0){
				fscore << t << "   " << score << "   "  << d << "  it is recognized as class -1 " << endl;
				negative++;
				if(d == my_digit)
			{
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				fo
				ut<<"--- "<<t<<" "<<filename<<"; score = ["<<score<<"]"<<endl;*/

				neg++;
				//	cout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = ["<< score<<"]" <<endl;
				//	fout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = ["<< score<<"]"<<endl;

			}
			else { // it is class 2 and recognizied as 2

				actual_accuracy++;

			}
			}

		}

		//fout<<endl<<endl;
		double samplerate=100.0/(double)fivesamples;

		fout<<" Testing the  neural  with the "<<note<<"   feature "<<endl;
			fout << "  Total number of samples " << test_data->n_examples << endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
			fout << "  First Class Sample Count " << c1Count << " Second Class Count "
					<< c2Count << endl;
			fout << "  Samples with score > 0:  " <<positivesamples << " Recognized as from first class "<< endl;
			fout << "  Samples with score < 0:  " << negative << "Recognized as from second class"<< endl;
			fout << "  The count of classified wrongly as class 1  = " << pos	<< "  and the count of classified wrongly as class 2  = " << neg << endl;
			fout << "  Samples correctly recognized   = " << actual_accuracy
					<< "   samples " << endl;
			float accuracy= (actual_accuracy/(float)test_data->n_examples)*100;
				fout << "  Accuracy %  = " << accuracy
							<< "   % " << endl;
				strcat(ResultString,"\n---------------------------------\n");
				strcat(ResultString,"\n  Testing the Neural  with the ");
				strcat(ResultString,note);

				strcat(ResultString,"  Feature  ");

				char cVal2[32];
				sprintf(cVal2,"%f",n_hu);
				strcat(ResultString," With ");
				strcat(ResultString,cVal2);
				strcat(ResultString," hidden layers");

				strcat(ResultString,"\n   Accuracy %  = " );

				char cVal[32];
				sprintf(cVal,"%f",accuracy);

				strcat(ResultString,cVal);

	//	fout<<"  Total number of samples "<<test_data->n_examples<<endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
	//	fout<<"  First Class Sample Count "<<c1Count<<" Second Class Count "<<c2Count <<endl;
		//fout<<"  Samples with score > 0:  "<<positivesamples<<endl;
	    //fout<<"  The false +ve count  = "<<pos<<"  and the false -ve count  = "<<neg<<endl;
	    //fout<<"  Samples correctly recognized   : "<<accuracy<<"   sample "<<endl;


				fout.close();
				fscore.close();

		delete(allocator);

}
void TwoClassClassifiers::neuralTrain(char* train_file, int n_hu, int feat, char* model){
	  cout<<"Training.......... ."<<endl;
	Allocator *allocator = new Allocator;
	int  num_of_features = LibraryFunctions::getFeatureCount(train_file);
  int my_digit = 1;
	if (model==NULL)
			{
				model="NeuralNetworkTwoClass";
				//model="modelSVM";
			}
	DiskXFile out_model_file(model, "w");

	// creating the mlp
	ConnectedMachine mlp[10];
	Linear *c1[10];
	Tanh *c2[10];
	Linear *c3[10];
	Tanh *c4[10];
	  cout<<"Addng layers......... ."<<endl;
	for(int count = my_digit; count <= my_digit; count++)
	{
		c1[count]= new(allocator) Linear(num_of_features,n_hu);
		c2[count]= new(allocator) Tanh(n_hu);
		c3[count]= new(allocator) Linear(n_hu,1);
		c4[count]= new(allocator) Tanh(1);

		mlp[count].addFCL(c1[count]);
		mlp[count].addFCL(c2[count]);
		mlp[count].addFCL(c3[count]);
		mlp[count].addFCL(c4[count]);
		mlp[count].build();
		mlp[count].setPartialBackprop();
  }
	  cout<<"Reading data.......... ."<<endl;
	DataSet *train_set;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	Criterion *criterion[10];
	// creating the trainer
	Trainer **trainers = (Trainer **)allocator->alloc(sizeof(Trainer *)*10);
	  cout<<"Training each digit.......... ."<<endl;
	/*Training each digit*/
	for(int count = my_digit; count <= my_digit; count++)
	{
		long trained_with_one = 0;
		long trainedAsNotFive=0;
		cout<<"Training digit "<<count<<endl;
		for(int t = 0; t < train_set->n_examples; t++)
		{

			train_set->setExample(t);
				if((t%500) == 0)  {
				cout<<"Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;

			}

			if(train_set->targets->frames[0][0] == count)
			{

              train_set->targets->frames[0][0] = 1;
				trained_with_one++;
				//cout<<"Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;
			}
			else {
		//		cout<<"---------------Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;
              train_set->targets->frames[0][0] = -1;
				trainedAsNotFive++;

			}
		}

		cout<<"examples trained with one: "<<trained_with_one<<endl;
		cout<<"examples trained not five: "<<trainedAsNotFive<<endl;
      cout<<"total number of examples: "<<train_set->n_examples<<endl;

		criterion[count] = new(allocator) MSECriterion(1);
		trainers[count] = new(allocator) StochasticGradient(&mlp[count], criterion[count]);

		trainers[count]->setIOption("max iter",100);
			  cout<<"Train digit.......... ."<<endl;
		trainers[count]->train(train_set, NULL);

			  cout<<"Save file.......... ."<<endl;
		mlp[count].saveXFile(&out_model_file);
	}
	delete(allocator);

}


void TwoClassClassifiers::LinearTrain(char* train_file,  int n_features, char* model){


	Allocator *allocator = new Allocator;

	//	int num_of_features= 200;
	//char train_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";

	if (model==NULL)
			{
				model="Linear_model_TwoClass";
				//model="modelSVM";
			}
	DiskXFile model_file(model, "w");

	//int num_of_features= 200;
	//char train_file[]="C:/datasets/arabic_gmask_gradproj_ourfeatures_training_set_full.txt";
	//DiskXFile model_file("model", "w");


	ConnectedMachine *nets;
	Linear *lin_layers;
	Tanh *out_layers;
	StochasticGradient *trainers;

	DataSet *train_set;
	train_set
			= new (allocator) MatDataSet(train_file, n_features, 1, false, -1, false);

//	MemoryDataSet *current_train_set = new (allocator) MemoryDataSet();
//	Sequence *inputs[20000];
//	Sequence *targets[20000];
//	for (int n = 0; n < 20000; n++) {
//		inputs[n] = new (allocator) Sequence(1, num_of_features);
//		targets[n] = new (allocator) Sequence(1, 1);
//	}
//	current_train_set->setInputs(inputs, 20000);
//	current_train_set->setTargets(targets, 20000);

	OneHotClassFormat class_format(1);
	Criterion *criterion = new (allocator) MSECriterion(1);

	int counter;

			lin_layers  = new (allocator) Linear( n_features, 1);
			out_layers  = new (allocator) Tanh(1);
			nets  = new (allocator) ConnectedMachine();
			nets ->addFCL(lin_layers);
			nets ->addFCL(out_layers);
			nets ->build();
			trainers= new (allocator) StochasticGradient(nets, criterion);
			//trainers->setIOption("max iter", 800);

//			counter = 0;
//			for (long k = 0; k < samples; k++) {
//
//				train_set->setExample(k);
//				int d = train_set->targets->frames[0][0];
//
//				if (d == i || d == j) {
//
//					current_train_set->setExample(counter);
//
//					for (int f = 0; f < num_of_features; f++) {
//						current_train_set->inputs->frames[0][f]
//								= train_set->inputs->frames[0][f];
//					}
//
//					if (d == i)
//						current_train_set->targets->frames[0][0] = 1;
//					else
//						current_train_set->targets->frames[0][0] = -1;
//
//					counter++;
//				}
//
//			}
//			cout << counter << endl;
//
//			current_train_set->setExample(0);
//			current_train_set->n_examples = counter;
			trainers->train(train_set, NULL);
			nets->saveXFile(&model_file);

	//	}
//	}

	delete (allocator);


}
void TwoClassClassifiers::LinearTest(char* test_file,int num_of_features ,char* note, char* model){

	Allocator *allocator = new Allocator;

		//int num_of_features=200+1;
		//char test_file[]="C:/datasets/zc_arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";
		//DiskXFile model_file("zc_model", "r");

		//int num_of_features=200;
		//char test_file[]="D:/datasets/arabic_gmask_gradproj_ourfeatures_testing_set_full.txt";

	if (model==NULL)
			{
				model="Linear_model_TwoClass";
				//model="modelSVM";
			}
		DiskXFile model_file(model, "r");





		ConnectedMachine *nets;
			Linear *lin_layers;
			Tanh *out_layers;

			lin_layers  = new (allocator) Linear(num_of_features, 1);
					out_layers  = new (allocator) Tanh(1);
					nets  = new (allocator) ConnectedMachine();
					nets ->addFCL(lin_layers);
					nets ->addFCL(out_layers);
					nets ->build();
					nets->loadXFile(&model_file);





					cout << "reading the test set " << endl;
					DataSet *test_data;
					test_data
								= new (allocator) MatDataSet(test_file, num_of_features, 1, false, -1, false);


					/*Local variables & Initialization*/
					float actual_accuracy(0);
				    float positivesamples(0);
				    float negative(0);
					float n_iter=10;
					Timer timer;
					float pos,neg;
					pos=0;
					neg=0;
					actual_accuracy=0;
					int c2Count,c1Count;
					c2Count=0;
					c1Count=0;

					//ofstream fout("test_stats_Linear.txt");
				      //ofstream fscore("Test_Linear_scores.txt");

				      char* scoreFilename=new char[200];
				      strcpy( scoreFilename,"");
				       strcat( scoreFilename,"Test_Linear_Score_");
				       strcat( scoreFilename,note);
				       strcat( scoreFilename,".txt");
				       ofstream fscore(scoreFilename);
				      	 char* statFilename=new char[100];
				      	strcpy( statFilename,"");
				      	 strcat( statFilename,"Test_Linear_Stat_");
				      	 strcat( statFilename,note);
				      	 strcat( statFilename,".txt");
				      	ofstream fout(statFilename);


						  cout<<"Forwarding samples .......... ."<<endl;
						  	long fivesamples=0;

							//fscore<<" ID "<<"   "<<"score"<<endl;

							fscore<<"Result of the linear  testing for the two class of "<<note<<endl;
								fscore<<" ID "<<"   "<<"score"<<"       Target class"<<"     Recognized as "<<endl;
					/*Testing...*/
					for(int t = 0; t < test_data->n_examples; t++)
					{
						if((t%300) == 0)
							cout<<t<<endl;

						test_data->setExample(t);
						int d = test_data->targets->frames[0][0];//actual value of sample


						if (d==my_digit){
							c1Count++;
						}
						else {

							c2Count++;
						}

				        /*Get the score from the corresponding NN*/
//						mlp[my_digit].forward(test_data->inputs);
//						real score = mlp[my_digit].outputs->frames[0][0];


						nets->forward(test_data->inputs);
						real score = nets->outputs->frames[0][0];

					//	fscore<<t<<"   "<<score <<endl;

			//			if (d==my_digit)
			//				fivesamples++;

				        if(score >= 0)
				        {
				        	fscore << t << "   " << score << "   "  << d << "  it is recognized as class 1 " << endl;

				            positivesamples++;
				            if(d == my_digit)
							{
				            	actual_accuracy++;
							}
							else
							{
								/*char filename[50];
								int writer, pass, digit;

								writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56
								pass = (t-(writer*100))/10;
								digit = (t-(writer*100))%10;
								sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
							//	fout<<"### "<<t<<" "<<filename<<"; score = "<<score<<endl;*/
				              pos++;


								//	cout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<score<<endl;
								//	fout<<"### "<<t<<" "<<getFile(t).data()<<"; score = "<<score<<endl;
							}
				        }
						else   if(score <0){
							fscore << t << "   " << score << "   "  << d << "  it is recognized as class -1 " << endl;
							negative++;
							if(d == my_digit)
						{
							/*char filename[50];
							int writer, pass, digit;

							writer = t/100;
							pass = (t-(writer*100))/10;
							digit = (t-(writer*100))%10;
							sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
							fo
							ut<<"--- "<<t<<" "<<filename<<"; score = ["<<score<<"]"<<endl;*/

							neg++;
							//	cout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = ["<< score<<"]" <<endl;
							//	fout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = ["<< score<<"]"<<endl;

						}
						else { // it is class 2 and recognizied as 2

							actual_accuracy++;

						}
						}

					}

					//fout<<endl<<endl;
					double samplerate=100.0/(double)fivesamples;

					fout<<" Testing the  linear with the "<<note<<"   feature "<<endl;
						fout << "  Total number of samples " << test_data->n_examples << endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
						fout << "  First Class Sample Count " << c1Count << " Second Class Count "
								<< c2Count << endl;
						fout << "  Samples with score > 0:  " <<positivesamples << " Recognized as from first class "<< endl;
						fout << "  Samples with score < 0:  " << negative << "  Recognized as from second class"<< endl;
						fout << "  The count of classified wrongly as class 1  = " << pos	<< "  and the count of classified wrongly as class 2  = " << neg << endl;
						fout << "  Samples correctly recognized   = " << actual_accuracy
								<< "   samples " << endl;
						float accuracy= (actual_accuracy/(float)test_data->n_examples)*100;
							fout << "  Accuracy %  = " << accuracy
										<< "   % " << endl;

							strcat(ResultString,"\n--------------------------------------");




							strcat(ResultString,"\n  Testing the Linear  with the ");
							strcat(ResultString,note);
							strcat(ResultString,"  Feature  \n");
							strcat(ResultString,"   Accuracy %  = " );

							char cVal[32];
							sprintf(cVal,"%f",accuracy);

							strcat(ResultString,cVal);


fout.close();
fscore.close();

				//	fout<<"  Total number of samples "<<test_data->n_examples<<endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
				//	fout<<"  First Class Sample Count "<<c1Count<<" Second Class Count "<<c2Count <<endl;
					//fout<<"  Samples with score > 0:  "<<positivesamples<<endl;
				    //fout<<"  The false +ve count  = "<<pos<<"  and the false -ve count  = "<<neg<<endl;
				    //fout<<"  Samples correctly recognized   : "<<accuracy<<"   sample "<<endl;


					delete(allocator);




}



void TwoClassClassifiers::RunTests(char* test_file,char* model,char* note,char* Algorithm,int alg){
	Allocator *allocator = new Allocator;

	Sequence* Ws=new(allocator) Sequence(1, Num_Of_Features);

	float bs;

	Machine* TestMachine=   LoadMachine(alg,model,allocator,bs,Ws);


						cout << " Reading the test set " << endl;
						DataSet *test_data;
						test_data
									= new (allocator) MatDataSet(test_file, Num_Of_Features, 1, false, -1, false);


						/*Local variables & Initialization*/
						float actual_accuracy(0);
					    float positivesamples(0);
					    float negative(0);
						float n_iter=10;
						Timer timer;
						float pos,neg;
						pos=0;
						neg=0;
						actual_accuracy=0;
						int c2Count,c1Count;
						c2Count=0;
						c1Count=0;
						ofstream fscore;
						//ofstream fout("test_stats_Linear.txt");
					      //ofstream fscore("Test_Linear_scores.txt");
                        if (GenerateScoreFiles){
					      char* scoreFilename=new char[200];
					      strcpy( scoreFilename,"");
					       strcat( scoreFilename,"Test_");
					       strcat( scoreFilename,Algorithm);
					       strcat( scoreFilename,"_Score_");
					       strcat( scoreFilename,note);
					       strcat( scoreFilename,".txt");


					       fscore.open(scoreFilename);
                        }



							  cout<<"Forwarding samples .......... ."<<endl;
							  	long fivesamples=0;

							    if (GenerateScoreFiles){
								fscore<<"Result of the linear  testing for the two class of "<<note<<endl;
									fscore<<" ID "<<"   "<<"score"<<"       Target class"<<"     Recognized as "<<endl;
							    }
						/*Testing...*/
									cout<<" count of samples  = "<<test_data->n_examples<<endl;
						for(int t = 0; t < test_data->n_examples; t++)
						{
							//cout<<" t = "<<t<<endl;
							if((t%300) == 0)
								cout<<t<<endl;

							test_data->setExample(t);
							int d = test_data->targets->frames[0][0];//actual value of sample

							//cout<<" d = "<<d<<endl;
							if (d==my_digit){
								c1Count++;
							}
							else {

								c2Count++;
							}



							float score = GetResultFromMachine(TestMachine,test_data->inputs,alg,bs,Ws);
							//cout<<"Score= "<<score<<endl;
					        if(score >= 0)
					        {

					        	  if (GenerateScoreFiles){
					        	fscore << t << "   " << score << "   "  << d << "  it is recognized as class 1 " << endl;
					        	  }
					            positivesamples++;
					            if(d == my_digit)
								{
					            	actual_accuracy++;
								}
								else
								{
					              pos++;
								}
					        }
							else   if(score <0){
								  if (GenerateScoreFiles){
								fscore << t << "   " << score << "   "  << d << "  it is recognized as class -1 " << endl;
								  }
								negative++;
								if(d == my_digit)
							{

								neg++;

							}
							else { // it is class 2 and recognizied as 2

								actual_accuracy++;

							}
							}

						}
						float accuracy= (actual_accuracy/(float)test_data->n_examples)*100;

						if(GenerateScoreFiles){
							fscore.close();
						}

                     if (GenerateStateFile){
						 char* statFilename=new char[100];
										      	strcpy( statFilename,"");
										        strcat(  statFilename,"Test_");
										  		 strcat(  statFilename,Algorithm);
										  		 strcat(  statFilename,"_Stat_");
										      	 //strcat( statFilename,"Test_Linear_Stat_");
										      	 strcat( statFilename,note);
										      	 strcat( statFilename,".txt");
										      	ofstream fout(statFilename);
						fout<<" Testing the  linear with the "<<note<<"   feature "<<endl;
							fout << "  Total number of samples " << test_data->n_examples << endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
							fout << "  First Class Sample Count " << c1Count << " Second Class Count "
									<< c2Count << endl;
							fout << "  Samples with score > 0:  " <<positivesamples << " Recognized as from first class "<< endl;
							fout << "  Samples with score < 0:  " << negative << "  Recognized as from second class"<< endl;
							fout << "  The count of classified wrongly as class 1  = " << pos	<< "  and the count of classified wrongly as class 2  = " << neg << endl;
							fout << "  Samples correctly recognized   = " << actual_accuracy
									<< "   samples " << endl;
							fout << "  Accuracy %  = " << accuracy
							<< "   % " << endl;
							fout.close();

                     }

                     fsummary<<"---------------------------------"<<Algorithm<<"---------------------------------------------"<<endl;
                     fsummary<<" Testing the  "<< note <<" feature pair using "<<Algorithm<<" Classifier"<<endl;
                     fsummary<<"Accuracy = "<<accuracy<<" % " << " ( "<<actual_accuracy <<"/"<<test_data->n_examples<<" ) "<<endl;
                     fsummary<<"Results achieved using follwoing parameters "<<endl;
                     fsummary<<" Number of Features = "<< Num_Of_Features <<"  C= "<<C_variable<<"  g = "<<g_variable<<" h = "<<HiddenNeurons<<endl;

                     cout<<"[Summary]"<<endl;
                     cout<<"[log] ---------------------------------"<<Algorithm<<"---------------------------------------------"<<endl;
                     cout<<"[log] Testing the  "<< note <<" feature pair using "<<Algorithm<<" Classifier"<<endl;
                     cout<<"[log] Accuracy = "<<accuracy<<" % " << " ( "<<actual_accuracy <<"/"<<test_data->n_examples<<" ) "<<endl;
                     cout<<"[log] Results achieved using follwoing parameters "<<endl;
                     cout<<"[log] Number of Features = "<< Num_Of_Features <<"  C= "<<C_variable<<"  g = "<<g_variable<<" h = "<<HiddenNeurons<<endl;
                     cout<<"[Summary]"<<endl;


						delete(allocator);

	}

Machine* TwoClassClassifiers::LoadMachine(int algorithm, char* model,Allocator * allocator,float &bs,Sequence* Ws){
if (algorithm==1){


	if (model==NULL)
			{
			//	model="Linear_model_TwoClass";
		model="modelSVMLinear";
			}
	DiskXFile model_file(model, "r");

	Kernel *kernel = new(allocator) DotKernel(s_variable);

	int total_num_of_support_vectors(0);

	SVM* svms;// = allocator->alloc(sizeof(SVM));
	svms=new (allocator) SVMClassification(kernel);
	svms->setROption("C", C_variable);



	total_num_of_support_vectors+= svms->n_support_vectors;
		//loading SVM models in turn
		svms->loadXFile(&model_file);
		bs= svms->b;

		for(int n=0; n<Num_Of_Features; n++)
		Ws->frames[0][n]=0;

		for(int s=0; s<svms->n_support_vectors; s++)
		{
			for(int n=0; n<Num_Of_Features; n++)
				Ws->frames[0][n]+= (svms->sv_alpha[s])*(svms->sv_sequences[s]->frames[0][n]);
		}



	cout<<"number of support vectors = "<<total_num_of_support_vectors<<endl;


}

else 	if (algorithm==2){
	if (model==NULL)
				{
					model="Linear_model_TwoClass";
					//model="modelSVM";
				}
			DiskXFile model_file(model, "r");



			ConnectedMachine *nets;
				Linear *lin_layers;
				Tanh *out_layers;

				lin_layers  = new (allocator) Linear( Num_Of_Features, 1);
						out_layers  = new (allocator) Tanh(1);
						nets  = new (allocator) ConnectedMachine();
						nets ->addFCL(lin_layers);
						nets ->addFCL(out_layers);
						nets ->build();
						nets->loadXFile(&model_file);
						return nets;

	}

else if (algorithm==5)//svm rbf
	{
	if (model==NULL)
				{

			model="modelSVM";
				}
		DiskXFile model_file(model, "r");

		Kernel *kernel = new(allocator) GaussianKernel(g_variable);

		int total_num_of_support_vectors(0);

	//SVM **svms = (SVM **)allocator->alloc(sizeof(SVM *)* NO_OF_CLASSES);
		SVM* svms;// = allocator->alloc(sizeof(SVM));
		svms=new (allocator) SVMClassification(kernel);
		svms->setROption("C", C_variable);

			svms->loadXFile(&model_file);

			total_num_of_support_vectors+= svms->n_support_vectors;

		cout<<"number of support vectors = "<<total_num_of_support_vectors<<endl;


		return svms;
	}
else if (algorithm==6){


	if (model==NULL)
			{
				model="NeuralNetworkTwoClass";
				//model="modelSVM";
			}
		DiskXFile model_file(model, "r");

		// creating the mlp
		ConnectedMachine* mlp=new(allocator) ConnectedMachine();
		Linear *c1;
		Tanh *c2;
		Linear *c3;
		Tanh *c4;


			c1= new(allocator) Linear(Num_Of_Features,HiddenNeurons);
			c2= new(allocator) Tanh(HiddenNeurons);
			c3= new(allocator) Linear(HiddenNeurons,1);
			c4= new(allocator) Tanh(1);
			mlp->addFCL(c1);
			mlp->addFCL(c2);
			mlp->addFCL(c3);
			mlp->addFCL(c4);
			mlp->build();
			mlp->setPartialBackprop();
			mlp->loadXFile(&model_file);


			return mlp;

}


	}

float TwoClassClassifiers::GetResultFromMachine(Machine* classifier, Sequence* input,int alg,float bs ,Sequence*  Ws){

	float score=0;
	if (alg==1){///svm linear....
			score=0;
				for(int n=0; n<Num_Of_Features; n++)
					score+= (input->frames[0][n])*(Ws->frames[0][n]);
	}
	else {
		 classifier->forward(input);
		 score =  classifier->outputs->frames[0][0];

	}
	return score;
}


