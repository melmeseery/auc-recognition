/*
 * OVO_OneLayer_NN.cpp
 *
 *  Created on: Aug 13, 2008
 *      Author: Maha
 */
#include "StdAfx.h"
#include "OVO_OneLayer_NN.h"
/*OVO_OneLayer_NN::OVO_OneLayer_NN(){
}*/

OVO_OneLayer_NN::OVO_OneLayer_NN(int n, int m)
{
	allocator= new Allocator;
	n_inputs=n;
	n_outputs=m;
	outputs= new(allocator) Sequence(1,n_outputs);

	nets= (ConnectedMachine ***)allocator->alloc(sizeof(ConnectedMachine **)*n_outputs);
	for(int i=0; i<n_outputs; i++)
		nets[i]= (ConnectedMachine **)allocator->alloc(sizeof(ConnectedMachine *)*n_outputs);

	for(int i=0; i<n_outputs-1; i++)
		for(int j=i+1; j<n_outputs; j++)
		{
			nets[i][j]= new(allocator) ConnectedMachine();
			nets[i][j]->addFCL(new(allocator) Linear(n_inputs,1));
			nets[i][j]->addFCL(new(allocator) Tanh(1));
			nets[i][j]->build();

		}

}

OVO_OneLayer_NN::~OVO_OneLayer_NN()
{
	delete(allocator);
}

void OVO_OneLayer_NN::load(char* file_name)
{
	cout<<"OVO_OneLayer_NN: load() is not available for this class ... use loadXFile!"<<endl;
	int x;
	cin>>x;
//	getch();
}

void OVO_OneLayer_NN::save(char* file_name)
{
	cout<<"OVO_OneLayer_NN: save() is not available for this class ... use saveXFile!"<<endl;
	int x;
	cin>>x;
//	getch();
}

void OVO_OneLayer_NN::loadXFile(XFile* file)
{
	for(int i=0; i<n_outputs-1; i++)
		for(int j=i+1; j<n_outputs; j++)
			nets[i][j]->loadXFile(file);
}

void OVO_OneLayer_NN::saveXFile(XFile* file)
{
	for(int i=0; i<n_outputs-1; i++)
		for(int j=i+1; j<n_outputs; j++)
			nets[i][j]->saveXFile(file);
}

void OVO_OneLayer_NN::train(DataSet* train_set)
{
	if(this->options->size()<1)
	{
		cout<<"OVO_OneLayer_NN: Invalid options vector!"<<endl;
		int x;
		cin>>x;
	//	getch();
	}

	MSECriterion criterion(1);
	Allocator* temp_allocator= new Allocator;

	MemoryDataSet *current_train_set= new(temp_allocator) MemoryDataSet();
	current_train_set->n_real_examples=train_set->n_examples;
	Sequence **inputs = (Sequence **)temp_allocator->alloc(sizeof(Sequence *)*train_set->n_examples);
	Sequence **targets = (Sequence **)temp_allocator->alloc(sizeof(Sequence *)*train_set->n_examples);
	for(int k=0; k<train_set->n_examples; k++)
		targets[k]= new(temp_allocator) Sequence(1,1);

	for(int i=0; i<n_outputs-1; i++)
		for(int j=i+1; j<n_outputs; j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			StochasticGradient trainer(nets[i][j],&criterion);
			trainer.setIOption("max iter",(*this->options)[0]);


			int counter=0;
			for(long k=0; k<train_set->n_examples; k++)
			{

				train_set->setExample(k);
				int d= train_set->targets->frames[0][0];

				if(d==i || d==j)
				{
					inputs[counter]= train_set->inputs;

					int label;
					if(d==i)
						label= 1;
					else
						label= -1;

					targets[counter]->frames[0][0]=label;

					counter++;
				}

			}
			cout<<counter<<endl;

			current_train_set->n_real_examples=counter;
			current_train_set->setInputs(inputs,counter);
			current_train_set->setTargets(targets,counter);
			current_train_set->n_examples=counter;
			trainer.train(current_train_set,NULL);

		}

	delete(temp_allocator);
}

void OVO_OneLayer_NN::forward(Sequence* seq)
{

	Allocator* temp_allocator= new Allocator;

	float** binary_decisions= (float**)temp_allocator->alloc(sizeof(float *)*n_outputs);
	for(int i=0; i<n_outputs; i++)
		binary_decisions[i]= (float*)temp_allocator->alloc(sizeof(float)*n_outputs);


	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			nets[i][j]->forward(seq);
			binary_decisions[i][j]= (1+nets[i][j]->outputs->frames[0][0])/2;
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

void OVO_OneLayer_NN::reset()
{
	for(int i=0; i<n_outputs-1; i++)
		for(int j=i+1; j<n_outputs; j++)
			nets[i][j]->reset();
}
