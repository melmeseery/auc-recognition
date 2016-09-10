
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
#include<algorithm>
using namespace Torch;
#include<conio.h>
#include<fstream>
#include<iostream>
using namespace std;

class index_score_pair
{
public:
	int index;
	float score;
	float err;
	index_score_pair(){index=0; score=0;}
	index_score_pair(int n, float s){index=n; score=s; err=0;}
};
class compare_index_score_pair
{
public:
	bool operator()(const index_score_pair& obj1, const index_score_pair& obj2)
	{return(obj1.score>obj2.score);} //descending order
};

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



void Two_Stage_Validate()
{

	Allocator *allocator = new Allocator;

	int num_of_features= 201;

	int training_mask[50000];
	for(int i=0; i<50000; i++)
		training_mask[i]=i;
	int validation_mask[10000];
	for(int i=0; i<10000; i++)
		validation_mask[i]= 50000+i;


	char train_file[]="D:/datasets/zc_arabic_gmask_gradproj_ourfeatures_training_set_full.txt";


	ConnectedMachine *nets[10][10];
	Linear *lin_layers[10][10];
	Tanh *out_layers[10][10];
	StochasticGradient *trainers[10][10];

	DataSet *set;
	set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);
	set->pushSubset(training_mask,50000);

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

	OneHotClassFormat class_format(1);
	Criterion *criterion = new(allocator) MSECriterion(1);

	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;

			lin_layers[i][j]= new(allocator) Linear(num_of_features,1);
			out_layers[i][j]= new(allocator) Tanh(1);
			nets[i][j]= new(allocator) ConnectedMachine();
			nets[i][j]->addFCL(lin_layers[i][j]);
			nets[i][j]->addFCL(out_layers[i][j]);
			nets[i][j]->build();
			trainers[i][j]= new(allocator) StochasticGradient(nets[i][j], criterion);
			trainers[i][j]->setIOption("max iter",100);

			counter=0;
			for(long k=0; k<set->n_examples; k++)
			{

				set->setExample(k);
				int d= set->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<num_of_features; f++)
					{
						current_train_set->inputs->frames[0][f]=
							set->inputs->frames[0][f];
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
			current_train_set->n_examples= counter;
			trainers[i][j]->train(current_train_set,NULL);

		}
	}

	set->popSubset();

	set->pushSubset(validation_mask,10000);

	vector<index_score_pair> score_pairs(10000);

	int last_stage_validation_errors[10000];
	ifstream fin("last_stage_validation_errors.txt");
	if(fin.fail())
	{cout<<"Cannot Open file last_stage_validation_errors.txt"<<endl; getch();}
	for(int i=0; i<10000; i++)
		fin>>last_stage_validation_errors[i];


	int top_candidates_errors[10];
	for(int i=0; i<10; i++)
		top_candidates_errors[i]=0;

	for(int t = 0; t < set->n_examples; t++)
	{

		set->setExample(t);
		int d= set->targets->frames[0][0];

		real first_max_value;
		long first_max_index;

		real binary_decisions[10][10];
		for(int i=0; i<=8; i++)
		{
			for(int j=i+1; j<=9; j++)
			{
				nets[i][j]->forward(set->inputs);
				binary_decisions[i][j]= (1+nets[i][j]->outputs->frames[0][0])/2; 
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


		getMax(decisions,10,first_max_value,first_max_index);
		score_pairs[t].index=t;
		score_pairs[t].score=first_max_value;

		if(first_max_index!=d && last_stage_validation_errors[t]==0)
			score_pairs[t].err=1;



		vector<index_score_pair> candidates(10);
		for(int i=0; i<10; i++)
		{
			candidates[i].index=i;
			candidates[i].score=decisions[i];
			if(i!=d && last_stage_validation_errors[t]==0)
				candidates[i].err=1;
			else
				candidates[i].err=0;
		}
		sort(candidates.begin(), candidates.end(), compare_index_score_pair());
		for(int i=0; i<10; i++)
		{
			bool correct_classification(false);
			for(int j=i; j>=0; j--)
			{
				if(candidates[j].err==0)
				{correct_classification=true; break;}
			}
			if(!correct_classification)
				top_candidates_errors[i]++;
		}


	}

	sort(score_pairs.begin(), score_pairs.end(), compare_index_score_pair());

	ofstream fout("validation_results.txt");

	int e_max=1;
	int e(0);
	float selected_threlshold(0);
	float rejection_rate(0);
	for(int i=0; i<10000; i++)
	{
		if(score_pairs[i].err==1)
			e++;
		if(e==e_max)
		{selected_threlshold= score_pairs[i].score; rejection_rate=10000-i; break;}
	}

	fout<<"Selected Threshold: "<<selected_threlshold<<", Corresponding Rejection Rate="<<rejection_rate<<endl;
	cout<<"Selected Threshold: "<<selected_threlshold<<", Corresponding Rejection Rate="<<rejection_rate<<endl;

	fout<<"Top Candidates Error: "<<endl;
	cout<<"Top Candidates Error: "<<endl;
	for(int i=0; i<10; i++)
	{
		fout<<"top "<<i+1<<" : "<<top_candidates_errors[i]<<endl;
		cout<<"top "<<i+1<<" : "<<top_candidates_errors[i]<<endl;
	}



	set->popSubset();
	delete(allocator);


}