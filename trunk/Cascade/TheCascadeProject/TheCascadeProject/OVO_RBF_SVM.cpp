#include "StdAfx.h"
#include "OVO_RBF_SVM.h"




OVO_RBF_SVM::OVO_RBF_SVM(int n, int m, float g_, float C_){
g=g_;
		C=C_;

		n_inputs=n;
		n_outputs=m;

		allocator= new(Allocator);

		outputs=new (allocator)Sequence(1,n_outputs);
		alphas=new vector<float>(0);
		SupportVectorsDist= new vector<int>(0);
		UniqueSupportVectors= new vector<Sequence*>(0);
		kernel = new(allocator) GaussianKernel(g);

		svms= (SVMClassification ***)allocator->alloc(sizeof(SVMClassification **)*n_outputs);
		for(int i=0; i<n_outputs; i++)
			svms[i]= (SVMClassification **)allocator->alloc(sizeof(SVMClassification *)*n_outputs);

		for(int i=0; i<n_outputs-1; i++)
		{
			for(int j=i+1; j<n_outputs; j++)
			{
				svms[i][j] = new (allocator) SVMClassification(kernel);
				svms[i][j]->setROption("C", C);	
			}
		}

}
OVO_RBF_SVM::~OVO_RBF_SVM(){

delete(SupportVectorsDist);
		delete(UniqueSupportVectors);
		delete(alphas);
		delete(allocator);

}

void OVO_RBF_SVM::load(char* file)
{

	cout<<"Loading..."<<endl;

	ifstream fin(file);
	if(fin.fail()){cout<<"Cannot Open file!"; getch();}
	//loading number of features
	fin>>n_inputs;
	//loading number of support vectors
	fin>>number_of_support_vectors;
	//loading number of 'unique' support vectors
	fin>>number_of_unique_support_vectors;
	//loading unique support vectors
	for(int n=0; n<number_of_unique_support_vectors; n++)
	{
		UniqueSupportVectors->push_back(new (allocator)Sequence(1,n_inputs));
		for(int f=0; f<n_inputs; f++)
		{fin>>((*UniqueSupportVectors)[n]->frames[0][f]); /*cout<<(*SupportVectors[n])[f]<<endl; getch();*/}
	}

	//loading support vectors distribution	
	for(int n=0; n<number_of_support_vectors; n++)
	{
		int k;
		fin>>k;
		SupportVectorsDist->push_back(k);
	}

	//loading alphas
	for(int n=0; n<number_of_support_vectors; n++)
	{
		float a;
		fin>>a;
		alphas->push_back(a);
	}

	//loading bs
	for(int n=0; n<45; n++)
	{fin>>bs[n];}

	//loading As
	for(int n=0; n<45; n++)
	{fin>>As[n];}

	//loading Bs
	for(int n=0; n<45; n++)
	{fin>>Bs[n];}

	//loading number of support vectors per pair
	for(int n=0; n<45; n++)
	{fin>>num_of_sv_per_pair[n];}


	cout<<"End loading."<<endl;
}

void OVO_RBF_SVM::loadXFile(XFile* file)
{

	cout<<"Loading..."<<endl;

	//loading number of features
	file->read(&n_inputs,sizeof(int),1);
	//loading number of support vectors
	file->read(&number_of_support_vectors,sizeof(int),1);
	//loading number of 'unique' support vectors
	file->read(&number_of_unique_support_vectors,sizeof(int),1);
	//loading unique support vectors
	for(int n=0; n<number_of_unique_support_vectors; n++)
	{
		UniqueSupportVectors->push_back(new Sequence(1,n_inputs));
		for(int f=0; f<n_inputs; f++)
		{file->read(&((*UniqueSupportVectors)[n]->frames[0][f]),sizeof(float),1); }
	}

	//loading support vectors distribution	
	for(int n=0; n<number_of_support_vectors; n++)
	{
		int k;
		file->read(&k,sizeof(int),1);
		SupportVectorsDist->push_back(k);
	}

	//loading alphas
	for(int n=0; n<number_of_support_vectors; n++)
	{
		float a;
		file->read(&a,sizeof(float),1);
		alphas->push_back(a);
	}

	//loading bs
	for(int n=0; n<45; n++)
	{file->read(&bs[n],sizeof(float),1);}

	//loading As
	for(int n=0; n<45; n++)
	{file->read(&As[n],sizeof(float),1);}

	//loading Bs
	for(int n=0; n<45; n++)
	{file->read(&Bs[n],sizeof(float),1);}

	//loading number of support vectors per pair
	for(int n=0; n<45; n++)
	{file->read(&num_of_sv_per_pair[n],sizeof(int),1);}


	cout<<"End loading."<<endl;
}

void OVO_RBF_SVM::save(char* file)
{
	cout<<"Saving..."<<endl;

	ofstream fout(file);
	//writing number of features
	fout<<n_inputs<<endl;
	//writing number of support vectors
	fout<<number_of_support_vectors<<endl;
	//writing number of 'unique' support vectors
	fout<<number_of_unique_support_vectors<<endl;
	//saving unique support vectors
	for(int n=0; n<number_of_unique_support_vectors; n++)
	{
		for(int f=0; f<n_inputs; f++)
			fout<<((*UniqueSupportVectors)[n]->frames[0][f])<<endl;

	}

	//saving support vectors distribution	
	for(int n=0; n<number_of_support_vectors; n++)
		fout<<(*SupportVectorsDist)[n]<<endl;

	//saving alphas
	for(int n=0; n<number_of_support_vectors; n++)
		fout<<(*alphas)[n]<<endl;

	//saving bs
	for(int n=0; n<45; n++)
	{fout<<bs[n]<<endl;}

	//saving As
	for(int n=0; n<45; n++)
	{fout<<As[n]<<endl;}

	//saving Bs
	for(int n=0; n<45; n++)
	{fout<<Bs[n]<<endl;}

	//savin number of support vectors per pair
	for(int n=0; n<45; n++)
	{fout<<num_of_sv_per_pair[n]<<endl;}

	cout<<"End saving."<<endl;


}

void OVO_RBF_SVM::saveXFile(XFile* file)
{
	cout<<"Saving..."<<endl;

	//writing number of features
	file->write(&n_inputs,sizeof(int),1);
	//writing number of support vectors
	file->write(&number_of_support_vectors,sizeof(int),1);
	//writing number of 'unique' support vectors
	file->write(&number_of_unique_support_vectors,sizeof(int),1);
	//saving unique support vectors
	for(int n=0; n<number_of_unique_support_vectors; n++)
	{
		for(int f=0; f<n_inputs; f++)
			file->write(&((*UniqueSupportVectors)[n]->frames[0][f]),sizeof(int),1);

	}

	//saving support vectors distribution	
	for(int n=0; n<number_of_support_vectors; n++)
		file->write(&(*SupportVectorsDist)[n],sizeof(int),1);

	//saving alphas
	for(int n=0; n<number_of_support_vectors; n++)
		file->write(&(*alphas)[n],sizeof(int),1);

	//saving bs
	for(int n=0; n<45; n++)
	{file->write(&bs[n],sizeof(int),1);}

	//saving As
	for(int n=0; n<45; n++)
	{file->write(&As[n],sizeof(int),1);}

	//saving Bs
	for(int n=0; n<45; n++)
	{file->write(&Bs[n],sizeof(int),1);}

	//saving number of support vectors per pair
	for(int n=0; n<45; n++)
	{file->write(&num_of_sv_per_pair[n],sizeof(int),1);}

	cout<<"End saving."<<endl;


}


void OVO_RBF_SVM::train(DataSet* train_set)
{
	Allocator* temp_allocator= new Allocator;

	int original_n_inputs= train_set->n_inputs;

	train_set->n_inputs=n_inputs;

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


	int counter(-1);
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			counter++;
			bs[counter]= svms[i][j]->b;
			num_of_sv_per_pair[counter]= svms[i][j]->n_support_vectors;

			for(int n=0; n<svms[i][j]->n_support_vectors; n++)
			{
				alphas->push_back(svms[i][j]->sv_alpha[n]);

				bool found(false);
				int m(0);
				for(m=0; m<UniqueSupportVectors->size(); m++)
				{
					found=true;

					for(int f=0; f<n_inputs; f++)
						if(svms[i][j]->sv_sequences[n]->frames[0][f]!= (*UniqueSupportVectors)[m]->frames[0][f])
						{found=false; break;}


						if(!found)
							continue;
						else
							break;


				}

				if(found)
					SupportVectorsDist->push_back(m);
				else
				{
					UniqueSupportVectors->push_back(svms[i][j]->sv_sequences[n]);
					SupportVectorsDist->push_back(UniqueSupportVectors->size()-1);
				}


			}//end n



		}//end i
	}//end j

	number_of_unique_support_vectors= UniqueSupportVectors->size();
	number_of_support_vectors= SupportVectorsDist->size();


	train_set->n_inputs=original_n_inputs;


	delete(temp_allocator);
	cout<<"End training."<<endl;

}

void OVO_RBF_SVM::forward(Sequence* sequence)
{
	sequence->frame_size=n_inputs;

	vector<float> KernelEvaluations(number_of_unique_support_vectors);
	for(int n=0; n<number_of_unique_support_vectors; n++)
		KernelEvaluations[n]=kernel->eval(sequence,(*UniqueSupportVectors)[n]);


	real binary_decisions[10][10];
	int sv_count=-1;
	int class_count=-1;
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			class_count++;

			float f=bs[class_count];
			for(int n=0; n<num_of_sv_per_pair[class_count]; n++)
			{/*cout<<++sv_count<<" : "<<(*SupportVectorsDist)[sv_count]<<endl;*/
				//cout<<"zeo"<<endl;
				sv_count++;
				f += KernelEvaluations[(*SupportVectorsDist)[sv_count]]*((*alphas)[sv_count]);
			}

			binary_decisions[i][j]= 1/(1+exp(As[class_count]*f+Bs[class_count])); 
			binary_decisions[j][i]= 1-binary_decisions[i][j];
		}
	}

	real decisions[10];
	for(int i=0; i<=9; i++)
	{
		real prob_sum(0);
		for(int j=0; j<=9; j++)
		{
			if(i!=j)
			{
				prob_sum += 1/(binary_decisions[i][j]);
			}
		}

		decisions[i]= 1/(prob_sum - 8);
	}
	for(int i=0; i<10; i++)
		outputs->frames[0][i]= log(decisions[i]);

}

void OVO_RBF_SVM::reset()
{
	alphas->clear();
	SupportVectorsDist->clear();
	UniqueSupportVectors->clear();

	for(int i=0; i<n_outputs-1; i++)
	{
		for(int j=i+1; j<n_outputs; j++)
		{
			svms[i][j]->reset();
			svms[i][j]->setROption("C", C);
			svms[i][j]->kernel= this->kernel;
		}
	}

}
