/*
 * OVA_Linear_SVM.cpp
 *
 *  Created on: Aug 13, 2008
 *      Author: Maha
 */
#include "stdafx.h"
#include "OVA_Linear_SVM.h"


OVA_Linear_SVM::OVA_Linear_SVM(int number_of_inputs, int number_of_outputs, float C_)
{
	allocator= new Allocator;
	n_inputs=number_of_inputs;
	n_outputs=number_of_outputs;
	C=C_;

	outputs= new(allocator) Sequence(1,n_outputs);

	kernel= new(allocator) DotKernel();
	svms= (SVMClassification **)allocator->alloc(sizeof(SVMClassification *)*n_outputs);
	for(int i=0; i<n_outputs; i++)
	{
		svms[i]= new(allocator) SVMClassification(kernel);
		svms[i]->setROption("C", C);
	}

	Ws= (float **)allocator->alloc(sizeof(float *)*n_outputs);
	for(int i=0; i<n_outputs; i++)
		Ws[i]= (float*)allocator->alloc(sizeof(float)*n_inputs);

	bs= (float *)allocator->alloc(sizeof(float)*n_outputs);

}

OVA_Linear_SVM::~OVA_Linear_SVM()
{
	delete(allocator);
}

//void OVA_Linear_SVM::load(char* file_name)
//{
//	net->load(file_name);
//}
//
//void OVA_Linear_SVM::save(char* file_name)
//{
//	net->save(file_name);
//}

void OVA_Linear_SVM::loadXFile(XFile* file)
{
	for(int i=0; i<n_outputs; i++)
	{
		file->read(&bs[i],sizeof(float),1);

		for(int f=0; f<n_inputs; f++)
			file->read(&Ws[i][f],sizeof(float),1);
	}
}

void OVA_Linear_SVM::saveXFile(XFile* file)
{
	for(int i=0; i<n_outputs; i++)
	{
		file->write(&bs[i],sizeof(float),1);

		for(int f=0; f<n_inputs; f++)
			file->write(&Ws[i][f],sizeof(float),1);
	}
}

void OVA_Linear_SVM::train(DataSet* train_set)
{
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

		trainer.train(&formatted_train_set, NULL);

		bs[i]= svms[i]->b;
		for(int f=0; f<n_inputs; f++)
				Ws[i][f]= 0;
		for(int n=0; n<svms[i]->n_support_vectors; n++)
		{
			float alpha= svms[i]->sv_alpha[n];
			for(int f=0; f<n_inputs; f++)
				Ws[i][f]= Ws[i][f] + alpha* svms[i]->sv_sequences[n]->frames[0][f];
		}
	}


}

void OVA_Linear_SVM::forward(Sequence* seq)
{
	float norm(0);
	for(int i=0; i<n_outputs; i++)
	{
		float out(bs[i]);
		for(int f=0; f<n_inputs; f++)
			out+= Ws[i][f]*seq->frames[0][f];
		out=exp(out);
		outputs->frames[0][i]= out;
		norm+= out;
	}

	for(int i=0; i<n_outputs; i++)
		outputs->frames[0][i]= log(outputs->frames[0][i]/norm);


}

void OVA_Linear_SVM::reset()
{
	for(int i=0; i<n_outputs; i++)
	{
		svms[i]->reset();
		svms[i]->setROption("C", C);
		svms[i]->kernel= kernel;
	}
}
