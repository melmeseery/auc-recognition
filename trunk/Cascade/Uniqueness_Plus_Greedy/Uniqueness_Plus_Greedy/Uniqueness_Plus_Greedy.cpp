// Uniqueness_Plus_Greedy.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "MNIST_Generate_CascadeNodes_Vector.h"
#include "MNIST_Generate_Validation_Performance_Lists_Vector.h"
#include "MNIST_Generate_Test_Performance_Lists_Vector.h"
#include "Evaluate_Cascade_Performance_OnTest.h"
#include "Evaluate_Cascade_Performance_OnValidation.h"
#include "Build_UniquenessPlusGreedy_Cascade.h"
#include "Evaluate_MaxVotes_Performance.h"


int _tmain(int argc, _TCHAR* argv[])
{

	Allocator* allocator= new Allocator;

	ofstream fout("Results.txt");


	//loading the test set
	char test_file[]="C:/datasets/MNIST/gradient_testing_set.txt";
	DataSet *test_set;
	test_set=new MatDataSet(test_file,200,1,false,-1,false);

	//reading the featuers ranking file
	char ranks_file[]=   "C:/datasets/MNIST/gradient_releif_class_independent_ranked_features.txt";
	ifstream ranks_fin(ranks_file);
	if(ranks_fin.fail()){cout<<"Unable to open file: "<<ranks_file<<endl; getch();}
	int Ranks[200];
	for(int z=0; z<200; z++)
		ranks_fin>>Ranks[z]; 

	//reording the features according to their ranks 
	for(int k=0; k<10000; k++)
	{
		Sequence seq(1,test_set->n_inputs);

		test_set->setExample(k);
		for(int j=0; j<test_set->n_inputs; j++)
			seq.frames[0][j]= test_set->inputs->frames[0][Ranks[j]-1];
		for(int j=0; j<test_set->n_inputs; j++)
			test_set->inputs->frames[0][j]=seq.frames[0][j];

	}


	vector<vector<ClassifiedSample*>*> performance_lists(0);
	MNIST_Generate_Validation_Performance_Lists_Vector(performance_lists);

	vector<vector<ClassifiedSample*>*> test_performance_lists(0);
	MNIST_Generate_Test_Performance_Lists_Vector(test_performance_lists);


	vector<CascadeNode*> all_nodes(0);
	MNIST_Generate_CascadeNodes_Vector(all_nodes);
	for(int i=0; i<all_nodes.size(); i++)
	{
		all_nodes[i]->performance_list= performance_lists[i];
		all_nodes[i]->test_performance_list= test_performance_lists[i];
	}


	vector<CascadeNode*> best_cascade(0);
	float _complexity;
	Build_UniquenessPlusGreedy_Cascade(
		//Build_Greedy_Cascade(
		/*vector<CascadeNode*>&*/ all_nodes, //from which we build the best cascade
		/*vector<CascadeNode*>&*/ best_cascade,
		/*float&*/ _complexity,
		1
		);

	cout<<"check complexity: "<<_complexity/10000<<endl;



	cout<<endl<<endl<<"Best Cascade:"<<endl;
	cout<<"Thresholds: ";
	for(int i=0; i<best_cascade.size(); i++)
		cout<<best_cascade[i]->ID<<": "<<best_cascade[i]->thresh<<", "<<endl;

	fout<<endl<<endl<<"Best Cascade:"<<endl;
	fout<<"Thresholds: ";
	for(int i=0; i<best_cascade.size(); i++)
		fout<<best_cascade[i]->ID<<": "<<best_cascade[i]->thresh<<", "<<endl;


	float complexity(0);
	float error(0);
	float final_stage_complexity(0);
	float final_stage_error(0);
	float n_rejected(0);
	//Calculating actual complexity and error on validation set
	Evaluate_Cascade_Performance_OnValidation(
		best_cascade,
		complexity,
		error,
		final_stage_complexity,
		final_stage_error,
		n_rejected
		);


	cout<<"On Validation:"<<endl;
	cout<<"Error of most complex classifier: "<<final_stage_error<<endl;
	cout<<"Complexity of most complex classifier: "<<final_stage_complexity<<endl;
	cout<<"Actual Error= "<<error<<endl;
	cout<<"Actual Complexity= "<<complexity<<endl;
	cout<<endl<<endl;

	fout<<"On Validation:"<<endl;
	fout<<"Error of most complex classifier: "<<final_stage_error<<endl;
	fout<<"Complexity of most complex classifier: "<<final_stage_complexity<<endl;
	fout<<"Actual Error= "<<error<<endl;
	fout<<"Actual Complexity= "<<complexity<<endl;
	fout<<endl<<endl;



	//Calculating actual complexity and error on test set
	Evaluate_Cascade_Performance_OnTest(
		best_cascade,
		complexity,
		error,
		final_stage_complexity,
		final_stage_error,
		n_rejected
		);
	cout<<"On Test:"<<endl;
	cout<<"Error of most complex classifier: "<<final_stage_error<<endl;
	cout<<"Complexity of most complex classifier: "<<final_stage_complexity<<endl;
	cout<<"Actual Error= "<<error<<endl;
	cout<<"Actual Complexity= "<<complexity<<endl;
	cout<<endl<<endl;


	fout<<"On Test:"<<endl;
	fout<<"Error of most complex classifier: "<<final_stage_error<<endl;
	fout<<"Complexity of most complex classifier: "<<final_stage_complexity<<endl;
	fout<<"Actual Error= "<<error<<endl;
	fout<<"Actual Complexity= "<<complexity<<endl;
	fout<<endl<<endl;


	Evaluate_MaxVotes_Performance(	  test_set,
		all_nodes,//best_cascade,
		complexity,
		error,
		final_stage_complexity,
		final_stage_error
		);

	cout<<"Max Votes Results on Test Set:"<<endl;
	cout<<"Error: "<<error<<endl;
	cout<<"Complexity: "<<complexity<<endl;

	fout<<"Max Votes Results on Test Set:"<<endl;
	fout<<"Error: "<<error<<endl;
	fout<<"Complexity: "<<complexity<<endl;





	delete(allocator);

	cout<<"Done."<<endl;
	getch();

	return 0;
}

