
#include "StdAfx.h"
#include "OVA_RBF_SVM.h"





OVA_RBF_SVM::OVA_RBF_SVM(int number_of_inputs, int number_of_outputs, float g_, float C_)
{
	allocator= new Allocator;
	n_inputs=number_of_inputs;
	n_outputs=number_of_outputs;
	g=g_;
	C=C_;

	outputs= new(allocator) Sequence(1,n_outputs);

	alphas=new vector<float>(0);

	kernel= new(allocator) GaussianKernel(g);
	svms= (SVMClassification **)allocator->alloc(sizeof(SVMClassification *)*n_outputs);
	for(int i=0; i<n_outputs; i++)
	{
		svms[i]= new(allocator) SVMClassification(kernel);
		svms[i]->setROption("C", C);
	}

	SupportVectorsDist= new vector<int>(0);
	UniqueSupportVectors= new vector<Sequence*>(0);



}

OVA_RBF_SVM::~OVA_RBF_SVM()
{
	delete(allocator);
	delete(SupportVectorsDist);
	delete(UniqueSupportVectors);
}


void OVA_RBF_SVM::load(char* file)
{

	cout<<"Loading..."<<endl;

	ifstream fin(file);
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
	for(int n=0; n<n_outputs; n++)
	{fin>>bs[n];}


	//loading number of support vectors per class
	for(int n=0; n<n_outputs; n++)
	{fin>>num_of_sv_per_class[n];}

	cout<<"End loading."<<endl;
}


void OVA_RBF_SVM::save(char* file)
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
	for(int n=0; n<n_outputs; n++)
	{fout<<bs[n]<<endl;}


	//savin number of support vectors per class
	for(int n=0; n<n_outputs; n++)
	{fout<<num_of_sv_per_class[n]<<endl;}

	cout<<"End saving."<<endl;

}


//void OVA_RBF_SVM::loadXFile(XFile* file)
//{
//	for(int i=0; i<n_outputs; i++)
//		svms[i]->loadXFile(file);
//}
//
//void OVA_RBF_SVM::saveXFile(XFile* file)
//{
//	for(int i=0; i<n_outputs; i++)
//		svms[i]->saveXFile(file);
//}

void OVA_RBF_SVM::train(DataSet* train_set)
{
	train_set->n_inputs=n_inputs;

	//	int* labels= (int*)allocator->alloc(sizeof(int)*train_set->n_examples);
	for(int n=0; n<train_set->n_examples; n++)
	{
		train_set->setExample(n);
		//		labels[n]= train_set->targets->frames[0][0];
		train_set->inputs->frame_size=n_inputs;
	}


	for(int i = 0; i < n_outputs; i++)
	{

		cout<<"Class#"<<i<<endl;

		QCTrainer trainer(svms[i]);

		Sequence class_labels(n_outputs, 1);
		for(int j = 0; j < n_outputs; j++)
		{
			if(j == i)
				class_labels.frames[j][0] =  1;
			else
				class_labels.frames[j][0] = -1;
		}
		ClassFormatDataSet formatted_train_set(train_set, &class_labels);
		formatted_train_set.n_examples=train_set->n_examples;
		formatted_train_set.n_inputs=n_inputs;
		trainer.train(&formatted_train_set, NULL);

		bs[i]= svms[i]->b;

		//finding unique support vectors
		num_of_sv_per_class[i]= svms[i]->n_support_vectors;

		for(int n=0; n<svms[i]->n_support_vectors; n++)
		{

			alphas->push_back(svms[i]->sv_alpha[n]);


			bool found(false);
			int m(0);
			for(m=0; m<UniqueSupportVectors->size(); m++)
			{
				found=true;

				for(int f=0; f<n_inputs; f++)
					if(svms[i]->sv_sequences[n]->frames[0][f]!= (*UniqueSupportVectors)[m]->frames[0][f])
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
				UniqueSupportVectors->push_back(svms[i]->sv_sequences[n]);
				SupportVectorsDist->push_back(UniqueSupportVectors->size()-1);
			}


		}//end n

	}//end i


	number_of_unique_support_vectors= UniqueSupportVectors->size();
	number_of_support_vectors= SupportVectorsDist->size();



}

void OVA_RBF_SVM::forward(Sequence* sequence)
{
	//float norm(0);
	//for(int i=0; i<n_outputs; i++)
	//{
	//	svms[i]->forward(seq);
	//	float out= exp(svms[i]->outputs->frames[0][0]);
	//	outputs->frames[0][i]= out;
	//	norm+= out;
	//}

	//for(int i=0; i<n_outputs; i++)
	//	outputs->frames[0][i]= log(outputs->frames[0][i]/norm);


	sequence->frame_size=n_inputs;

	vector<float> KernelEvaluations(number_of_unique_support_vectors);
	for(int n=0; n<number_of_unique_support_vectors; n++)
		KernelEvaluations[n]=kernel->eval(sequence,(*UniqueSupportVectors)[n]);


	float decisions[10];
	float norm=0;
	int sv_count=-1;
	for(int i=0; i<10; i++)
	{
		float f=bs[i];
		for(int n=0; n<num_of_sv_per_class[i]; n++)
		{
			sv_count++;
			f += KernelEvaluations[(*SupportVectorsDist)[sv_count]]*((*alphas)[sv_count]);
		}

		decisions[i]= exp(f);
		norm+=decisions[i];
	}

	for(int i=0; i<10; i++)
	{
		outputs->frames[0][i]= log(decisions[i]/norm);
	}


}

void OVA_RBF_SVM::reset()
{
	for(int i=0; i<n_outputs; i++)
	{
		svms[i]->reset();
		svms[i]->setROption("C", C);
		svms[i]->kernel= kernel;
	}
}