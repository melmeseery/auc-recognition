
#include "MatDataSet.h"
#include "OneHotClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "QCTrainer.h"
#include "CmdLine.h"
#include "Random.h"
#include "SVMClassification.h"
#include "DiskXFile.h"
#include "ClassFormatDataSet.h"
#include "MatDataSet.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "MSECriterion.h"
#include "OneHotClassFormat.h"
#include "MultiClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"
#include "StochasticGradient.h"
#include "KFold.h"
#include "ConnectedMachine.h"
#include "Linear.h"
#include "Tanh.h"
#include "LogSoftMax.h"
#include "MeanVarNorm.h"
#include "DiskXFile.h"
#include "MemoryXFile.h"
#include "CmdLine.h"
#include "Random.h"
#include "Sigmoid.h"
#include "math.h"
#include<vector>
#include<conio.h>
using namespace Torch;

#include<iostream>
#include<fstream>
using namespace std;
using namespace Torch;

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

void getMin(real* arr, long n_arr, real &min_value, long &min_index)
{
	min_index=0;
	min_value= arr[0];
	for(int i=1; i<n_arr; i++)
	{
		if(arr[i]<min_value)
		{
			min_index =i;
			min_value =arr[i];
		}
	}
}


void Estimating_Calibrating_Parameters()
{

	Allocator *allocator = new Allocator;

	int num_of_features=28*28;


	char train_file[]="D:/datasets/mahdbase_training_set.txt";
	DataSet *dataset;
	dataset=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);



	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	float g=0.02;
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


	ofstream fout("calibrating_parameters.txt");
	float out[5000];
	bool target[5000];

	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			kernel = new(allocator) GaussianKernel(g);
			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C",100);
			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			counter=0;
			for(long k=0; k<50000; k++)
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


			int count=0;
			float prior1(0), prior0(0);

			for(int t=50000; t<60000; t++)
			{

				dataset->setExample(t);
				int d= dataset->targets->frames[0][0];

				if(d==i)
				{target[count]=true; prior1++;} 
				else if(d==j)
				{target[count]=false; prior0++;}
				else
					continue;

				svms[i][j]->forward(dataset->inputs);

				real decision = svms[i][j]->outputs->frames[0][0];

				out[count]=decision;

				count++;
			}
			int len=count;

			float A(0), B(log((float)((prior0+1)/(prior1+1))));
			float hiTarget((prior1+1)/(prior1+2)), loTarget(1/(prior0+2));
			float lambda(pow((float)10,(int)-3));
			float olderr(pow((float)10,(int)300));

			float pp[5000];
			for(int k=0; k<len; k++)
				pp[k]=(prior1+1)/(prior0+prior1+2);

			count=0;
			cout<<"Iter: ";
			for(int it=0; it<100; it++)
			{
				//if(it%10==0)
					cout<<it<<",  ";

				float a(0), b(0), c(0), d(0), e(0);

				for(int k=0; k<len; k++)
				{
					float t;
					if(target[k])
						t= hiTarget;
					else
						t= loTarget;

					float d1=pp[k]-t;
					float d2=pp[k]*(1-pp[k]);
					a+= out[k]*out[k]*d2;
					b+= d2;
					c+= out[k]*d2;
					d+= out[k]*d1;
					e+= d1;
				}

				if(abs(d)<pow((float)10,(int)-9) && abs(e)<pow((float)10,(int)-9))
					break;

				float oldA(A), oldB(B);
				float err(0);

				while(true)
				{

					float det= (a+lambda)*(b+lambda)-c*c;
					if(det==0){lambda*=10; continue;}

					A= oldA + ((b+lambda)*d-c*e)/det;
					B= oldB + ((a+lambda)*e-c*d)/det;

					err=0;
					for(int k=0; k<len; k++)
					{					
						float t;
						if(target[k])
							t= hiTarget;
						else
							t= loTarget;

						float p= 1/(1+exp(out[k]*A+B));
						pp[k]=p;
						float log_p;
						if(p==0)
							log_p=-200;
						else
							log_p=log((float)p);
						float log_1_p;
						if(1-p==0)
							log_1_p=-200;
						else
							log_1_p=log((float)(1-p));

						err-= t*log_p+(1-t)*log_1_p;
					}

					if(err<olderr*(1+pow((float)10,(int)-7)))
					{lambda*=0.1; break;}

					lambda*=10;
					if(lambda>=pow((float)10,(int)6))
					{/*cout<<"Give Up!";*/ break;}

				}

				float diff=err-olderr;
				float scale=0.5*(err+olderr+1);
				if(diff>-pow((float)10,(int)-3)*scale && diff<pow((float)10,(int)-7)*scale)
					count++;
				else
					count=0;

				olderr=err;
				if(count==3)
					break;

			}
			cout<<endl;

			fout<<A<<endl<<B<<endl;
			cout<<"A= "<<A<<", B= "<<B<<endl;


		}
	}



	fout.close();

	delete(allocator);


}


