// Inspection.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "MNIST_Generate_CascadeNodes_Vector.h"
#include "MNIST_Generate_Validation_Performance_Lists_Vector.h"
#include "MNIST_Generate_Test_Performance_Lists_Vector.h"
#include "Adjust_Thresholds_WithRespectToFinal.h"
#include "CascadeNode.h"




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


	int N= all_nodes.size();


	vector<float> permissible_errors(N-1);
	for(int i=0; i<N-1; i++)
		permissible_errors[i]=1;

	Adjust_Thresholds_WithRespectToFinal(
										/*vector<CascadeNode*>&*/ all_nodes, //from which we build the best cascade
										/*vector<float>&*/ permissible_errors
										);

	vector<CascadeNode*>::iterator end_iter(all_nodes.end());
	vector<CascadeNode*>::iterator begin_iter(all_nodes.begin());
	sort(begin_iter, --end_iter, rejection_descend_compare_nodes());


	ofstream fout("RejectionsDiff_vs_NumOfViolations.txt");
	for(int i=0; i<N-2; i++)
	{
		for(int j=i+1; j<N-1; j++)
		{

			float rejections_diff= all_nodes[i]->n_rejected - all_nodes[j]->n_rejected;
			float number_of_violations(0);

			for(int n=0; n<all_nodes[j]->performance_list->size(); n++)
			{
				bool rejected_by_node_Sj= (*all_nodes[j]->performance_list)[n]->score < all_nodes[j]->thresh ;
				bool rejected_by_node_Si= (*all_nodes[i]->performance_list)[n]->score < all_nodes[i]->thresh ;
				bool error_by_last(false);
				if((*all_nodes[N-1]->performance_list)[n]->err == 1)
					error_by_last=true;
				
				
				if( rejected_by_node_Sj && !rejected_by_node_Si /*&& !error_by_last*/)
					number_of_violations++;
					
			}

			fout<<rejections_diff<<" "<<number_of_violations<<endl;
			
		}
	}

	


	cout<<"Done."<<endl;
	getch();

	return 0;
}

