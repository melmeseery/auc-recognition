#include "StdAfx.h"
#include "PCA_Quad.h"

PCA_Quad::PCA_Quad(int number_of_inputs, int number_of_pc_components, int number_of_outputs, char* pca_matrix_file, char* pca_mu_file)
{
	allocator= new Allocator;
	n_inputs=number_of_inputs;
	n_outputs=number_of_outputs;
	n_pca_components= number_of_pc_components;
	n_neural_inputs= n_pca_components*(n_pca_components-1)/2+n_pca_components;

	net= new(allocator) ConnectedMachine();
	Linear *c1= new(allocator) Linear(n_neural_inputs,n_outputs);
	Tanh *c2= new(allocator) Tanh(n_outputs);
	net->addFCL(c1);
	net->addFCL(c2);
	net->build();

	pca_matrix= (float **)allocator->alloc(sizeof(float*)*n_inputs);
	for(int i=0; i<n_inputs; i++)
		pca_matrix[i]= (float*)allocator->alloc(sizeof(float)*n_pca_components);

	ifstream pca_mat_fin(pca_matrix_file);
	if(pca_mat_fin.fail())
	{cout<<"PCA_Quad: Cannot open file: "<<pca_matrix_file<<endl; getch();}
	for(int n=0; n<n_inputs; n++)
	{
		for(int i=0; i<n_pca_components; i++)
			pca_mat_fin>>pca_matrix[n][i];
	}

	pca_mu= (float *)allocator->alloc(sizeof(float)*n_inputs);
	ifstream pca_mu_fin(pca_mu_file);
	if(pca_mu_fin.fail())
	{cout<<"PCA_Quad: Cannot open file: "<<pca_mu_file<<endl; getch();}
	for(int n=0; n<n_inputs; n++)
		pca_mu_fin>>pca_mu[n];
}

PCA_Quad::~PCA_Quad()
{
	delete(allocator);
}

void PCA_Quad::load(char* file_name)
{
	net->load(file_name);
}

void PCA_Quad::save(char* file_name)
{
	net->save(file_name);
}

void PCA_Quad::loadXFile(XFile* file)
{
	net->loadXFile(file);
}

void PCA_Quad::saveXFile(XFile* file)
{
	net->saveXFile(file);
}

void PCA_Quad::train(DataSet* train_set)
{
	Allocator* temp_allocator= new Allocator;

	if(this->options->size()<1)
	{
		cout<<"PCA_Quad: Invalid options vector!"<<endl;
		getch();
	}



	MemoryDataSet *transformed_train_set= new(temp_allocator) MemoryDataSet();
	Sequence **inputs= (Sequence **)temp_allocator->alloc(sizeof(Sequence*)*train_set->n_examples);;
	Sequence **targets= (Sequence **)temp_allocator->alloc(sizeof(Sequence*)*train_set->n_examples);;

	for(int n=0; n<train_set->n_examples; n++)
	{
		inputs[n]= new(temp_allocator) Sequence(1,n_neural_inputs);
		targets[n]= new(temp_allocator) Sequence(1,1);
	}
	transformed_train_set->setInputs(inputs, train_set->n_examples);
	transformed_train_set->setTargets(targets, train_set->n_examples);

	for(int k=0; k<train_set->n_examples; k++)
	{
		train_set->setExample(k);
		transformed_train_set->setExample(k);
		transformed_train_set->targets->frames[0][0] = train_set->targets->frames[0][0];


		vector<float>pca_sample(n_pca_components);

		for(int i=0; i<n_pca_components; i++)
		{
			float sum(0);
			for(int n=0; n<n_inputs; n++)
			{ sum+= (train_set->inputs->frames[0][n]-pca_mu[n])*pca_matrix[n][i];}

			pca_sample[i]=sum;
		}

		int c(0);
		for(int i=0; i<n_pca_components; i++)
			for(int j=i; j<n_pca_components; j++)
				transformed_train_set->inputs->frames[0][c++]= (pca_sample[i])*(pca_sample[j]);
	}


	Sequence class_labels(n_outputs,n_outputs);
	for(int i=0;i<n_outputs;i++)
	{
		for(int j=0;j<n_outputs;j++)
		{
			if(i==j)
				class_labels.frames[i][j]=1;
			else
				class_labels.frames[i][j]=-1;
		}
	}

	MSECriterion criterion(n_outputs);
	StochasticGradient trainer(net, &criterion);
	trainer.setIOption("max iter",(int)(*this->options)[0]);
	ClassFormatDataSet formatted_train_set(transformed_train_set,&class_labels);
	trainer.train(&formatted_train_set, NULL);

	delete(temp_allocator);

}

void PCA_Quad::forward(Sequence* seq)
{
	Sequence quad_test_sample(1,n_neural_inputs);
	Sequence pca_test_sample(1,n_pca_components);
	for(int i=0; i<n_pca_components; i++)
	{
		float sum(0);
		for(int n=0; n<n_inputs; n++)
		{ sum+= (seq->frames[0][n]-pca_mu[n])*pca_matrix[n][i];}

		pca_test_sample.frames[0][i]=sum;
	}

	int c(0);
	for(int i=0; i<n_pca_components; i++)
		for(int j=i; j<n_pca_components; j++)
			quad_test_sample.frames[0][c++]= (pca_test_sample.frames[0][i])*(pca_test_sample.frames[0][j]);
	
	net->forward(&quad_test_sample);
	outputs= new(allocator) Sequence(1,n_outputs);
	for(int i=0; i<n_outputs; i++)
		outputs->frames[0][i]= log((1+net->outputs->frames[0][i])/2);

}

void PCA_Quad::reset()
{
	net->reset();
}