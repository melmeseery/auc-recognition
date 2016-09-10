#include "StdAfx.h"
#include "OVO_Linear_SVM.h"

OVO_Linear_SVM::OVO_Linear_SVM(int n, int m, float C_)
{
		C=C_;

		n_inputs=n;
		n_outputs=m;

		allocator= new(Allocator);

		kernel= new(allocator) DotKernel(); 

		svms= (SVMClassification ***)allocator->alloc(sizeof(SVMClassification **)*n_outputs);
		for(int i=0; i<n_outputs; i++)
			svms[i]= (SVMClassification **)allocator->alloc(sizeof(SVMClassification *)*n_outputs);

		Ws= (float**)allocator->alloc(sizeof(float*)*n_outputs*(n_outputs-1)/2);
		for(int i=0; i<n_outputs*(n_outputs-1)/2; i++)
			Ws[i]= (float*)allocator->alloc(sizeof(float)*n_inputs);

		As= (float*)allocator->alloc(sizeof(float)*n_outputs*(n_outputs-1)/2);
		Bs= (float*)allocator->alloc(sizeof(float)*n_outputs*(n_outputs-1)/2);
		bs= (float*)allocator->alloc(sizeof(float)*n_outputs*(n_outputs-1)/2);


		for(int i=0; i<n_outputs-1; i++)
		{
			for(int j=i+1; j<n_outputs; j++)
			{
				svms[i][j] = new (allocator) SVMClassification(kernel);
				svms[i][j]->setROption("C", C);	
			}
		}
}

OVO_Linear_SVM::~OVO_Linear_SVM(void)
{

			delete(allocator);
}
void OVO_Linear_SVM::loadXFile(XFile* file)
{
	for(int i=0; i<n_outputs*(n_outputs-1)/2; i++)
	{
		file->read(&bs[i],sizeof(float),1);

		for(int f=0; f<n_inputs; f++)
			file->read(&Ws[i][f],sizeof(float),1);
	}
}

void OVO_Linear_SVM::saveXFile(XFile* file)
{
	for(int i=0; i<n_outputs*(n_outputs-1)/2; i++)
	{
		file->write(&bs[i],sizeof(float),1);

		for(int f=0; f<n_inputs; f++)
			file->write(&Ws[i][f],sizeof(float),1);
	}
}


void OVO_Linear_SVM::train(DataSet* train_set)
{
	Allocator* temp_allocator= new Allocator;

	n_inputs= train_set->n_inputs;

	QCTrainer *trainers[10][10];


	MemoryDataSet *current_train_set= new(temp_allocator) MemoryDataSet();
	Sequence *inputs[20000];
	Sequence *targets[20000];
	for(int n=0; n<20000; n++)
	{
		inputs[n]= new(temp_allocator) Sequence(1,train_set->n_inputs);
		targets[n]= new(temp_allocator) Sequence(1,1);
	}
	current_train_set->setInputs(inputs, 20000);
	current_train_set->setTargets(targets, 20000);

	float out[5000];
	bool target[5000];


	int class_count=0;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{

			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			int counter=0;
			for(long k=0; k<train_set->n_examples; k++)
			{

				train_set->setExample(k);
				int d= train_set->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<train_set->n_inputs; f++)
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
			current_train_set->n_examples=counter;
			trainers[i][j]->train(current_train_set,NULL);


			cout<<"Calibrating..."<<endl;

			int count=0;
			float prior1(0), prior0(0);

			for(int t=0; t<5000/*train_set->n_examples*/; t++)
			{

				train_set->setExample(t);
				int d= train_set->targets->frames[0][0];

				if(d==i)
				{target[count]=true; prior1++;} 
				else if(d==j)
				{target[count]=false; prior0++;}
				else
					continue;

				svms[i][j]->forward(train_set->inputs);

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
			for(int it=0; it<100; it++)
			{
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

			As[class_count]=A;
			Bs[class_count]=B;
			class_count++;


			cout<<"End calibrating."<<endl;


		}
	}


	class_count=0;
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{

			bs[class_count]= svms[i][j]->b;

			for(int n=0; n<n_inputs; n++)
				Ws[class_count][n]=0;

			for(int s=0; s<svms[i][j]->n_support_vectors; s++)
			{
				for(int n=0; n<n_inputs; n++)
					Ws[class_count][n]+= (svms[i][j]->sv_alpha[s])*(svms[i][j]->sv_sequences[s]->frames[0][n]);
			}

			class_count++;


		}
	}



	delete(temp_allocator);
	cout<<"End training."<<endl;

}

void OVO_Linear_SVM::forward(Sequence* sequence)
{
	sequence->frame_size=n_inputs;

	Allocator* temp_allocator= new Allocator;

	float** binary_decisions= (float**)temp_allocator->alloc(sizeof(float *)*n_outputs);
	for(int i=0; i<n_outputs; i++)
		binary_decisions[i]= (float*)temp_allocator->alloc(sizeof(float)*n_outputs);


	int class_count(0);
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{

			float decision(bs[class_count]);
			for(int n=0; n<n_inputs; n++)
				decision+= (sequence->frames[0][n])*(Ws[class_count][n]);

			binary_decisions[i][j]= 1/(1+exp(As[class_count]*decision+Bs[class_count])); 
			binary_decisions[j][i]= 1-binary_decisions[i][j];

		}
	}

	for(int i=0; i<=9; i++)
	{
		float prob_sum(0);
		for(int j=0; j<=9; j++)
		{
			if(i!=j)
			{
				prob_sum += 1/(binary_decisions[i][j]);
			}
		}

		outputs->frames[0][i]= log(1/(prob_sum - (n_outputs-2)));

	}

	delete(temp_allocator);

}

void OVO_Linear_SVM::reset()
{

	for(int i=0; i<n_outputs-1; i++)
		for(int j=i+1; j<n_outputs; j++)
			svms[i][j]->reset();
}