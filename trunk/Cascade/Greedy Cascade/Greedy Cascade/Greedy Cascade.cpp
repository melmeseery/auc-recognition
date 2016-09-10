// Greedy Cascade.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "Build_Greedy_Cascade.h"
#include "MNIST_Generate_CascadeNodes_Vector.h"
#include "MNIST_Generate_Validation_Performance_Lists_Vector.h"
#include "MNIST_Generate_Test_Performance_Lists_Vector.h"
#include "Evaluate_Cascade_Performance_OnTest.h"
#include "Evaluate_Cascade_Performance_OnValidation.h"


int _tmain(int argc, _TCHAR* argv[])
{

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
	Build_Greedy_Cascade(
		/*vector<CascadeNode*>&*/ all_nodes, //from which we build the best cascade
		/*vector<CascadeNode*>&*/ best_cascade,
		/*float&*/ _complexity
		);

	cout<<"check complexity: "<<_complexity/10000<<endl;



	cout<<endl<<endl<<"Best Cascade:"<<endl;
	cout<<"Thresholds: ";
	for(int i=0; i<best_cascade.size(); i++)
		cout<<best_cascade[i]->ID<<": "<<best_cascade[i]->thresh<<", "<<endl;



	ofstream fout("Greedy_Cascade_All_Stages.txt");

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


	

	cout<<"Done."<<endl;
	getch();

	return 0;
}
