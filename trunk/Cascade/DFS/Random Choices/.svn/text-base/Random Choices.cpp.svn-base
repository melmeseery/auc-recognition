
// Random_Choices.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "Build_Cascade_DFS.h"
#include "MNIST_Generate_CascadeNodes_Vector.h"
#include "MNIST_Generate_Validation_Performance_Lists_Vector.h"
#include "MNIST_Generate_Test_Performance_Lists_Vector.h"
#include "Select_Subpools_From_Pool.h"
#include "Evaluate_Cascade_Performance_OnValidation.h"
#include "Evaluate_Cascade_Performance_OnTest.h"


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


	vector<vector<CascadeNode*>*> selected_subpools;
	vector<vector<vector<ClassifiedSample*>*>*> selected_performance_lists;
	char random_choices_file_name[]= "cost_ordered_random_cascades.txt";
	Select_Subpools_From_Pool(random_choices_file_name, all_nodes, selected_subpools);
	cout<<"number of subpools: "<<selected_subpools.size()<<endl;
	cout<<"number of stages per subpool: "<<selected_subpools[0]->size()<<endl;


	vector<float> validation_subpools_complexities(0);
	vector<float> validation_subpools_errors(0);
	vector<float> validation_number_of_stages(0);
	vector<float> validation_final_stage_complexities(0);
	vector<float> validation_final_stage_errors(0);
	vector<float> test_subpools_complexities(0);
	vector<float> test_subpools_errors(0);
	vector<float> test_number_of_stages(0);
	vector<float> test_final_stage_complexities(0);
	vector<float> test_final_stage_errors(0);


	float avg_complexity(0), avg_error(0), avg_num_of_stages(0);

	ofstream fout("DFS_Random_Choices_Results.txt");

	Timer timer;
	for(int pool_id=0; pool_id<15/*selected_subpools.size()*/; pool_id++)
	{

		cout<<"Processing Subpool #"<<pool_id+1<<endl;

		vector<CascadeNode*> best_cascade(0);
		float aaa, bbb;
		Build_Cascade_DFS(
			/*vector<CascadeNode*>&*/ *selected_subpools[pool_id], //from which we build the best cascade
			/*vector<CascadeNode*>&*/ best_cascade,
			/*float max_error*/ 76,//the maximum permissible margin of error
			/*float Q*/16, //number of quantization level of thresholds
			/*float& best_cascade_complexity*/aaa,
			/*float& best_cascade_error*/bbb
			);



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

		validation_subpools_complexities.push_back(complexity);
		validation_subpools_errors.push_back(error);
		validation_number_of_stages.push_back(best_cascade.size());
		validation_final_stage_complexities.push_back(final_stage_complexity);
		validation_final_stage_errors.push_back(final_stage_error);



		//Calculating actual complexity and error on test set
		Evaluate_Cascade_Performance_OnTest(
			best_cascade,
			complexity,
			error,
			final_stage_complexity,
			final_stage_error,
			n_rejected
			);
		test_subpools_complexities.push_back(complexity);
		test_subpools_errors.push_back(error);
		test_number_of_stages.push_back(best_cascade.size());
		test_final_stage_complexities.push_back(final_stage_complexity);
		test_final_stage_errors.push_back(final_stage_error);

		cout<<"Subpool #:"<<pool_id+1<<endl;
		cout<<"complexity: "<<test_subpools_complexities[pool_id]<<endl;
		cout<<"error: "<<test_subpools_errors[pool_id]<<endl;
		cout<<"final stage complexity: "<<test_final_stage_complexities[pool_id]<<endl;
		cout<<"final stage error: "<<test_final_stage_errors[pool_id]<<endl;
		cout<<"number of stages: "<<test_number_of_stages[pool_id]<<endl<<endl;

		fout<<"Subpool #:"<<pool_id+1<<endl;
		fout<<"complexity: "<<test_subpools_complexities[pool_id]<<endl;
		fout<<"error: "<<test_subpools_errors[pool_id]<<endl;
		fout<<"final stage complexity: "<<test_final_stage_complexities[pool_id]<<endl;
		fout<<"final stage error: "<<test_final_stage_errors[pool_id]<<endl;
		fout<<"number of stages: "<<test_number_of_stages[pool_id]<<endl<<endl;

		avg_complexity+= test_subpools_complexities[pool_id]/15;
		avg_error+= test_subpools_errors[pool_id]/15;
		avg_num_of_stages+= test_number_of_stages[pool_id]/15;

		for(int i=0; i<best_cascade.size(); i++)
			delete(best_cascade[i]);


	}
	float elapsed_time= timer.getRunTime()/15;

	cout<<endl<<"Elapsed Time: "<<elapsed_time<<endl<<endl;
	fout<<endl<<"Elapsed Time: "<<elapsed_time<<endl<<endl;

	//float avg_complexity(0), avg_error(0), avg_num_of_stages(0);

	//ofstream fout("DFS_Random_Choices_Results.txt");

	//for(int i=0; i<15; i++)
	//{
	//	cout<<"Subpool #:"<<i+1<<endl;
	//	cout<<"complexity: "<<test_subpools_complexities[i]<<endl;
	//	cout<<"error: "<<test_subpools_errors[i]<<endl;
	//	cout<<"final stage complexity: "<<test_final_stage_complexities[i]<<endl;
	//	cout<<"final stage error: "<<test_final_stage_errors[i]<<endl;
	//	cout<<"number of stages: "<<test_number_of_stages[i]<<endl<<endl;

	//	fout<<"Subpool #:"<<i+1<<endl;
	//	fout<<"complexity: "<<test_subpools_complexities[i]<<endl;
	//	fout<<"error: "<<test_subpools_errors[i]<<endl;
	//	fout<<"final stage complexity: "<<test_final_stage_complexities[i]<<endl;
	//	fout<<"final stage error: "<<test_final_stage_errors[i]<<endl;
	//	fout<<"number of stages: "<<test_number_of_stages[i]<<endl<<endl;

	//	avg_complexity+= test_subpools_complexities[i]/15;
	//	avg_error+= test_subpools_errors[i]/15;
	//	avg_num_of_stages+= test_number_of_stages[i]/15;
	//}

	//cout<<"Average Complexity: "<<avg_complexity<<endl;
	//cout<<"Average Error: "<<avg_error<<endl;
	//cout<<"Average Number of Stages: "<<avg_num_of_stages<<endl;


	fout<<"Average Complexity: "<<avg_complexity<<endl;
	fout<<"Average Error: "<<avg_error<<endl;
	fout<<"Average Number of Stages: "<<avg_num_of_stages<<endl;


	cout<<"Done."<<endl;
	getch();

	return 0;
}

