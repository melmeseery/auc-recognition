#include "StdAfx.h"
#include "TwoLayer_NN.h"

TwoLayer_NN::TwoLayer_NN(int n, int h, int m)
{
	allocator= new Allocator;
	n_inputs=n;
	n_outputs=m;
	n_hidden=h;
	net= new(allocator) ConnectedMachine();
	Linear *c1= new(allocator) Linear(n_inputs,n_hidden);
	Tanh *c2= new(allocator) Tanh(n_hidden);
	Linear *c3= new(allocator) Linear(n_hidden,n_outputs);
	LogSoftMax *c4= new(allocator) LogSoftMax(n_outputs);
	net->addFCL(c1);
	net->addFCL(c2);
	net->addFCL(c3);
	net->addFCL(c4);
	net->build();
}

TwoLayer_NN::~TwoLayer_NN()
{
	delete(allocator);
}

void TwoLayer_NN::load(char* file_name)
{
	net->load(file_name);
}

void TwoLayer_NN::save(char* file_name)
{
	net->save(file_name);
}

void TwoLayer_NN::loadXFile(XFile* file)
{
	net->loadXFile(file);
}

void TwoLayer_NN::saveXFile(XFile* file)
{
	net->saveXFile(file);
}

void TwoLayer_NN::train(DataSet* train_set)
{

	if(this->options->size()<1)
	{
		cout<<"TwoLayer_NN: Invalid options vector!"<<endl;
		getch();
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

	trainer.setIOption("max iter",(int) (*this->options)[0]);
	ClassFormatDataSet formatted_train_set(train_set,&class_labels);
	trainer.train(&formatted_train_set, NULL);

}

void TwoLayer_NN::forward(Sequence* seq)
{
	net->forward(seq);
	outputs= net->outputs;

}

void TwoLayer_NN::reset()
{
	net->reset();
}

