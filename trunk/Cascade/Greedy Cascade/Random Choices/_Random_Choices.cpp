// Random_Choices.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "Build_Cascade_ModelBased.h"
#include "MNIST_Generate_CascadeNodes_Vector.h"
#include "MNIST_Generate_Validation_Performance_Lists_Vector.h"
#include "MNIST_Generate_Test_Performance_Lists_Vector.h"
#include "Select_Subpools_From_Pool.h"



int _tmain(int argc, _TCHAR* argv[])
{

	vector<vector<ClassifiedSample*>*> performance_lists(0);
	MNIST_Generate_Validation_Performance_Lists_Vector(performance_lists);

	vector<vector<ClassifiedSample*>*> test_performance_lists(0);
	MNIST_Generate_Test_Performance_Lists_Vector(test_performance_lists);



	vector<CascadeNode*> all_nodes(0);
	MNIST_Generate_CascadeNodes_Vector(all_nodes);

	vector<vector<CascadeNode*>*> selected_subpools;
	vector<vector<vector<ClassifiedSample*>*>*> selected_performance_lists;
	char random_choices_file_name[]= "cost_ordered_random_cascades.txt";
	Select_Subpools_From_Pool(random_choices_file_name, all_nodes, performance_lists, selected_subpools, selected_performance_lists);
	cout<<"number of subpools: "<<selected_subpools.size()<<endl;
	cout<<"number of stages per subpool: "<<selected_subpools[0]->size()<<endl;


	vector<float> validation_subpools_complexities(0);
	vector<float> validation_subpools_errors(0);
	vector<float> validation_number_of_stages(0);
	vector<float> test_subpools_complexities(0);
	vector<float> test_subpools_errors(0);
	vector<float> test_number_of_stages(0);
	vector<float> theoretical_complexities(0);
	

	for(int pool_id=0; pool_id<selected_subpools.size(); pool_id++)
	{

		cout<<"Processing Subpool #"<<pool_id+1<<endl;

		vector<CascadeNode*> best_cascade(0);
		float best_cascade_theoretical_complexity;
		Build_Cascade_ModelBased(
			/*vector<CascadeNode*>&*/ *(selected_subpools[pool_id]), //from which we build the best cascade
			/*vector<CascadeNode*>&*/ best_cascade,
			/*vector<vector<ClassifiedSample*>*>&*/ *(selected_performance_lists[pool_id]),
			/*float&*/ best_cascade_theoretical_complexity
			);

		theoretical_complexities.push_back(best_cascade_theoretical_complexity);


		//Calculating actual complexity and error on validation set
		int n_examples= performance_lists[0]->size();
		int N= all_nodes.size();
		float actual_error(0);
		float actual_complexity(0);
		for(int k=0; k<n_examples; k++)
		{

			for(int i=0; i<best_cascade.size(); i++)
			{
				int stage_original_index;
				bool found(false);
				for(int j=0; j<N; j++)
				{
					if(strcmp(all_nodes[j]->ID,best_cascade[i]->ID)>=0)
					{ stage_original_index= j; found=true; break;}
				}

				if(!found)
				{cout<<"ERROR!"<<endl; getch();}

	
				actual_complexity+= best_cascade[i]->complexity;
				if((*performance_lists[stage_original_index])[k]->score > best_cascade[i]->thresh)
				{
					actual_error+= (*performance_lists[stage_original_index])[k]->err;
					break;
				}

			}


		}

		validation_subpools_complexities.push_back(actual_complexity/n_examples);
		validation_subpools_errors.push_back(actual_error);
		validation_number_of_stages.push_back(best_cascade.size());



		//Calculating actual complexity and error on test set
		actual_error=0;
		actual_complexity=0;
		for(int k=0; k<n_examples; k++)
		{

			for(int i=0; i<best_cascade.size(); i++)
			{
				int stage_original_index;
				bool found(false);
				for(int j=0; j<N; j++)
					if(strcmp(all_nodes[j]->ID,best_cascade[i]->ID)>=0)
					{ stage_original_index= j; found=true; break;}


				if(!found)
				{cout<<"ERROR!"<<endl; getch();}
				

				actual_complexity+= best_cascade[i]->complexity;
				if((*test_performance_lists[stage_original_index])[k]->score > best_cascade[i]->thresh)
				{
					actual_error+= (*test_performance_lists[stage_original_index])[k]->err;
					break;
				}

			}


		}

		test_subpools_complexities.push_back(actual_complexity/n_examples);
		test_subpools_errors.push_back(actual_error);
		test_number_of_stages.push_back(best_cascade.size());

		for(int i=0; i<best_cascade.size(); i++)
			delete(best_cascade[i]);


	}

	float avg_complexity(0), avg_error(0), avg_num_of_stages(0);
	for(int i=0; i<15; i++)
	{
		cout<<"Subpool #:"<<i+1<<endl;
		cout<<"theoretical complexity: "<<theoretical_complexities[i]<<endl;
		cout<<"complexity: "<<test_subpools_complexities[i]<<endl;
		cout<<"error: "<<test_subpools_errors[i]<<endl;
		cout<<"number of stages: "<<test_number_of_stages[i]<<endl<<endl;

		avg_complexity+= test_subpools_complexities[i]/15;
		avg_error+= test_subpools_errors[i]/15;
		avg_num_of_stages+= test_number_of_stages[i]/15;
	}

	cout<<"Average Complexity: "<<avg_complexity<<endl;
	cout<<"Average Error: "<<avg_error<<endl;
	cout<<"Average Number of Stages: "<<avg_num_of_stages<<endl;

	
	cout<<"Done."<<endl;
	getch();

	return 0;
}

