
#include "MatDataSet.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "MSECriterion.h"
#include "OneHotClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "NPTrainer.h"
#include "KFold.h"
#include "KNN.h"
#include "MeanVarNorm.h"
#include "CmdLine.h"
#include "Random.h"
#include "Timer.h"
using namespace Torch;
#include<fstream>
#include<conio.h>
#include<iostream>
using namespace std;

void getMax(real* arr, long n_arr, real &max_value, long &max_index)
{
	max_index=0;
	max_value= arr[0];
	for(int i=1; i<n_arr; i++)
	{
		if(arr[i]>max_value)
		{
			max_index =i;
			max_value =arr[i];
		}
	}
}



void TEST()
{

	Allocator *allocator = new Allocator;

	char train_file[]="C:/datasets/mnist_training_set.txt";
	char test_file[]="C:/datasets/mnist_testing_set.txt";


	// reading training and testing sets
	DataSet *train_data= new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
	DataSet *test_data= new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);

	// creating the knn machine
	const int k=5;
	KNN knn(10,k);

	// creating the trainer
	NPTrainer trainer(&knn);

	//training ...
	trainer.train(train_data, NULL);


	float actual_accuracy(0);
	const int Nt=41;
	float n_rejected[Nt]; // number of rejected samples
	float correct[Nt];
	for(int t=0; t<Nt; t++)
	{
		correct[t]=0;
		n_rejected[t]=0;
	}

	ofstream fout("results.txt");
	for(int m = 0; m < test_data->n_examples; m++)
	{

		if(m%100==0)
			cout<<m<<endl;

		test_data->setExample(m);
		int d= test_data->targets->frames[0][0];//actual value of sample

		// forwarding test sample
		knn.forward(test_data->inputs);

		float decisions[10];
		for(int i=0; i<10; i++)
			decisions[i]=0;

		for(int j=0; j<=k-1; j++)
		{
			train_data->setExample(knn.indices[j]);
			int label= train_data->targets->frames[0][0];
			decisions[label]++;
		}

		real first_max_value;
		long first_max_index;
		getMax(decisions,10,first_max_value,first_max_index);
		if(first_max_index==d)
			actual_accuracy++;

		float big_d=knn.distances[0]+knn.distances[1]+knn.distances[2];

		for(int t=0;t<Nt; t++)
		{
			if(!(first_max_value==k && big_d<120-t) )
				n_rejected[t]++;
			else if(first_max_index==d)
				correct[t]++;
		}

	}

	//cout<<correct[Nt-1]/(10000-n_rejected[Nt-1])*100<<endl; getch();


	bool found(false);
	for(int t=0; t<Nt; t++)
	{
		if(correct[t]/(10000-n_rejected[t])*100>99.5)
		{
			fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
			cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

			fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
			cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;

			fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;		
			cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;

			float th= 120-t;
			fout<<"th= "<<th<<endl;
			cout<<"th= "<<th<<endl;

			found=true;
			break;

		}
	}

	if(!found)
		cout<<"99.5% not reached!"<<endl;


	//float recog_time(0);
	//Timer timer;
	//for(int m = 0; m < test_data->n_examples; m++)
	//{

	//	test_data->setExample(m);
	//	int d= test_data->targets->frames[0][0];//actual value of sample

	//	// forwarding test sample
	//	knn.forward(test_data->inputs);

	//	float decisions[10];
	//	for(int i=0; i<10; i++)
	//		decisions[i]=0;

	//	for(int j=0; j<=k-1; j++)
	//	{
	//		train_data->setExample(knn.indices[j]);
	//		int label= train_data->targets->frames[0][0];
	//		decisions[label]++;
	//	}
	//	
	//	real first_max_value;
	//	long first_max_index;
	//	getMax(decisions,10,first_max_value,first_max_index);

	//}
	//recog_time= timer.getTime();
	//fout<<"Recognition Time: "<<recog_time<<endl;
	//cout<<"Recognition Time: "<<recog_time<<endl;

	fout.close();

	delete(allocator);


}










//void TEST()
//{
//
//	Allocator *allocator = new Allocator;
//
//	char train_file[]="D:/datasets/mnist_training_set.txt";
//	char test_file[]="D:/datasets/mnist_testing_set.txt";
//
//
//	// reading training and testing sets
//	DataSet *train_data= new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
//	DataSet *test_data= new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);
//
//	// creating the knn machine
//	const int k=3;
//	KNN knn(10,k);
//
//	// creating the trainer
//	NPTrainer trainer(&knn);
//
//	//training ...
//	trainer.train(train_data, NULL);
//
//
//
//	float actual_accuracy(0);
//	const int Nt=100000;
//	long n_rejected[Nt]; // number of rejected samples
//	float correct[Nt];
//	for(int t=0; t<Nt; t++)
//	{
//		correct[t]=0;
//		n_rejected[t]=0;
//	}
//
//	ofstream fout("results.txt");
//	float zeo(0);
//	for(int t = 0; t < test_data->n_examples; t++)
//	{
//
//		//if(t%100==0)
//		//{cout<<t<<endl; cout<<"zeo: "<<zeo<<endl;}
//
//		test_data->setExample(t);
//		int d= test_data->targets->frames[0][0];//actual value of sample
//
//
//		// forwarding test sample
//		knn.forward(test_data->inputs);
//
//
//
//		float decisions[10];
//		for(int i=0; i<10; i++)
//			decisions[i]=0;
//
//		real norm=0;
//		float total_distanc(0);
//		for(int j=0; j<=k-1; j++)
//		{
//			//cout<<knn.distances[j]<<endl;
//
//			train_data->setExample(knn.indices[j]);
//			int label= train_data->targets->frames[0][0];
//			decisions[label]+= 1/knn.distances[j];
//			norm += 1/knn.distances[j];
//			//			norm += exp(-knn.distances[j]);
//
//		}
//		//getch();
//		//cout<<endl<<endl;
//
//		//cout<<"d: "<<d<<endl;
//		for(int i=0; i<10; i++)
//		{decisions[i]/=norm; /*cout<<decisions[i]<<endl;*/}
//		//getch();
//
//
//		real first_max_value;
//		long first_max_index;
//		real second_max_value;
//		long second_max_index;
//
//		// finding value and index of first and second maxima
//		getMax(decisions,10,first_max_value,first_max_index);
//		decisions[first_max_index]=-99;
//		getMax(decisions,10,second_max_value,second_max_index);
//		decisions[first_max_index]=first_max_value;
//
//	
//		cout<<knn.distances[0]+knn.distances[1]+knn.distances[2]<<endl;	
//	
//		if(first_max_index==d)
//			actual_accuracy++;
//		else
//		{
//			cout<<"error"<<endl;
//			getch();
//		}
//
//
//			if(first_max_value==1 && d!=first_max_index)
//			{cout<<"pathological case # : "<<++zeo<<endl; getch();}
//			//else
//			//{		
//			//	for(int i=0; i<10; i++)
//			//		cout<<decisions[i]<<endl;
//			//	getch();
//
//			//}
//
//
//			for(int t=0;t<Nt; t++)
//			{
//				float th= ((float)(t))/((float) Nt);
//				if((first_max_value-second_max_value)<th)
//					n_rejected[t]++;
//				else if(first_max_index==d)
//					correct[t]++;
//			}
//
//	}
//
//	cout<<correct[Nt-1]/(10000-n_rejected[Nt-1])*100<<endl; getch();
//
//	bool found(false);
//	for(int t=0; t<Nt; t++)
//		if(correct[t]/(10000-n_rejected[t])*100>99.5)
//		{
//			fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
//			cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
//
//			fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
//			cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
//
//			fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;		
//			cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;
//
//			float th= ((float)(t))/((float) Nt);
//			fout<<"th= "<<th<<endl;
//			cout<<"th= "<<th<<endl;
//
//			found=true;
//			break;
//
//		}
//
//		if(!found)
//			cout<<"99.5% not reached!"<<endl;
//
//
//
//		fout.close();
//
//		delete(allocator);
//
//
//}
//
//
//
//





//void TEST()
//{
//
//	Allocator *allocator = new Allocator;
//
//	char train_file[]="C:/datasets/mnist_training_set.txt";
//	char test_file[]="C:/datasets/mnist_testing_set.txt";
//
//
//	// reading training and testing sets
//	DataSet *train_data= new(allocator) MatDataSet(train_file,28*28,1,false,-1,false);
//	DataSet *test_data= new(allocator) MatDataSet(test_file,28*28,1,false,-1,false);
//
//	// creating the knn machine
//	const int k=3;
//	KNN knn(10,k);
//
//	// creating the trainer
//	NPTrainer trainer(&knn);
//
//	//training ...
//	trainer.train(train_data, NULL);
//
//
//
//	//creating and initializing the confusion matrix
//	real confusion_matrix[10][10];
//	for(int i=0; i<10; i++)
//		for(int j=0; j<10; j++)
//			confusion_matrix[i][j]=0;
//
//
//	real actual_accuracy(0);
//	real accuracy(0);//accuracy after rejection
//	long n_rejected(0); // number of rejected samples
//	long n_false_rejected(0); // number of falsely rejected samples
//
//
//	Timer timer;
//	timer.stop();
//	for(int t = 0; t < test_data->n_examples; t++)
//	{
//
//		cout<<t<<endl;
//		test_data->setExample(t);
//		int d= test_data->targets->frames[0][0];//actual value of sample
//
//
//		// forwarding test sample
//		timer.resume();
//		knn.forward(test_data->inputs);
//		timer.stop();
//
//	
//
//		int indices[k];
//		real norm=0;
//		for(int j=0; j<=k-1; j++)
//		{
//			train_data->setExample(knn.indices[j]);
//			indices[j]= train_data->targets->frames[0][0];
////			norm += 1/knn.distances[j];
//			norm += exp(-knn.distances[j]);
//
//		}
//
//		//cout<<endl;
//		//cout<<"d: "<<d<<endl;
//		//for(int j=0; j<=k-1; j++)
//		//{
//		//	cout<<indices[j]<<" : "<<knn.distances[j]<<endl;
//		//}
//		//cout<<endl;
//		//getch();
//		
//		real decisions[10];
//		for(int i=0; i<=9;i++)
//		{
//			decisions[i]=0;
//			for(int j=0; j<=k-1;j++)
//				if(i==indices[j])
//					decisions[i]=decisions[i]+ 1;//exp(-knn.distances[j]);
//
//		}
//
//		for(int i=0; i<=9; i++)
//			decisions[i]/= 3;//norm;
//
//
//
//		real first_max_value;
//		long first_max_index;
//		real second_max_value;
//		long second_max_index;
//
//		//real temp[10];
//		//for(int zeo=0; zeo<=9; zeo++)
//		//	temp[zeo]=decisions[zeo];
//
//		// finding value and index of first and second maxima
//		getMax(decisions,10,first_max_value,first_max_index);
//		decisions[first_max_index]=-99;
//		getMax(decisions,10,second_max_value,second_max_index);
//
//		if(first_max_index==d)
//			actual_accuracy++;
//		//else
//		//{
//		//	cout<<endl<<"d: "<<d<<endl;
//		//	for(int zeo=0; zeo<=9;zeo++)
//		//		cout<<temp[zeo]<<endl;
//		//	cout<<endl<<endl;
//		//	getch();
//		//}
//
//		
//		//cout<<d<<" -> ";
//		//cout<<first_max_index<<endl<<endl;
//		//for(int z=0; z<=9; z++)
//		//	cout<<knn.outputs->frames[0][z]<<endl;
//		//cout<<endl;
//		//getch();
//
//
//		// rejecting patterns that violates thresholding condittions
//		if(!(first_max_value==1 && knn.distances[0]<30))
//		{
//			n_rejected++;
//			if(first_max_index==d)
//				n_false_rejected++;
//			continue;
//		}
//		//else if(first_max_index!=d)
//		//{
//		//	getch();
//		//}
//
//		//updating confusion matrix
//		confusion_matrix[d][first_max_index]=confusion_matrix[d][first_max_index]+1;
//
//
//	}
//
//
//	//calculating accuracy from confusion matrix
//	real n_examples(0);
//	real n_correct_examples(0);
//	for(int i=0; i<10; i++)
//		n_correct_examples += confusion_matrix[i][i];
//	for(int i=0; i<10; i++)
//		for(int j=0; j<10; j++)
//			n_examples+= confusion_matrix[i][j];
//	accuracy= n_correct_examples/n_examples;
//	cout<<"Accuracy: "<<accuracy<<endl;
//	cout<<"Actual Accuracy : "<<actual_accuracy/10000<<endl;
//
//	// displaying confusion matrix
//	for(int i=0; i<10; i++)
//	{
//		for(int j=0; j<10; j++)
//			cout<<confusion_matrix[i][j]<<"      ";
//		cout<<endl;
//	}
//
//	cout<<"Rejected: "<<n_rejected<<endl;
//	cout<<"FR : "<<n_false_rejected<<endl;
//	cout<<"Recognition Time : "<<timer.getTime()<<endl;
//
//
//
//	delete(allocator);
//
//
//}
//
