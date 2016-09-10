/*
 * OVA_OneLayer_NN.cpp
 *
 *  Created on: Aug 13, 2008
 *      Author: Maha
 */
#include "StdAfx.h"

#include "OVA_OneLayer_NN.h"


OVA_OneLayer_NN::OVA_OneLayer_NN(int n, int m)
{
	allocator= new Allocator;
	n_inputs=n;
	n_outputs=m;
	net= new(allocator) ConnectedMachine();
	Linear *c1= new(allocator) Linear(n_inputs,n_outputs);
	LogSoftMax *c2= new(allocator) LogSoftMax(n_outputs);
	net->addFCL(c1);
	net->addFCL(c2);
	net->build();
}

OVA_OneLayer_NN::~OVA_OneLayer_NN()
{
	delete(allocator);
}

void OVA_OneLayer_NN::load(char* file_name)
{
	net->load(file_name);
}

void OVA_OneLayer_NN::save(char* file_name)
{
	net->save(file_name);
}

void OVA_OneLayer_NN::loadXFile(XFile* file)
{
	net->loadXFile(file);
}

void OVA_OneLayer_NN::saveXFile(XFile* file)
{
	net->saveXFile(file);
}

void OVA_OneLayer_NN::train(DataSet* train_set)
{
	if(this->options->size()<1)
	{
		cout<<"OVA_OneLayer_NN: Invalid options vector!"<<endl;
		int x;
		cin>>x;
		//getch();
	}

	Sequence class_labels(n_inputs,n_outputs);
	for(int i=0;i<n_outputs;i++)
	{
		for(int j=0;j<n_outputs;j++)
		{
			if(i==j)
				class_labels.frames[i][j]=1;
			else
				class_labels.frames[i][j]=0;
		}
	}

	OneHotClassFormat class_format(10);
	ClassNLLCriterion criterion(&class_format);
	StochasticGradient trainer(net, &criterion);
	trainer.setIOption("max iter",(int)(*this->options)[0]);
	ClassFormatDataSet formatted_train_set(train_set,&class_labels);
	trainer.train(&formatted_train_set, NULL);

}

void OVA_OneLayer_NN::forward(Sequence* seq)
{
	net->forward(seq);
	outputs= net->outputs;

}

void OVA_OneLayer_NN::reset()
{
	net->reset();
}


