#pragma once
#define NUM_OF_HIDDEN_NEURONS 100
#include "abstractclassifier.h"


class NeuralClassifier :
	public AbstractClassifier
{
private :
	int n_hu;

		// creating the mlp
	ConnectedMachine mlp[NO_OF_CLASSES];
	Linear *c1[NO_OF_CLASSES];
	Tanh *c2[NO_OF_CLASSES];
	Linear *c3[NO_OF_CLASSES];
	Tanh *c4[NO_OF_CLASSES];
public:
	NeuralClassifier(void);
public:
	~NeuralClassifier(void);
	void InitClassifier();
	void TRAIN_DATA_BITS(char* train_file, char* model, int n_hu);
	void TEST_DATA_BITS(char* test_file, char* model, int n_hu);
	void TEST_DATA(char* test_file, char* model, int n_hu);
};
