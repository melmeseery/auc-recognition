// Inspection.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "Get_Thresholds.h"
#include "MNIST_Generate_CascadeNodes_Vector.h"
#include "MNIST_Generate_Validation_Performance_Lists_Vector.h"
#include "MNIST_Generate_Test_Performance_Lists_Vector.h"


int _tmain(int argc, _TCHAR* argv[])
{

	vector<vector<ClassifiedSample*>*> performance_lists(0);
	MNIST_Generate_Validation_Performance_Lists_Vector(performance_lists);

	vector<vector<ClassifiedSample*>*> test_performance_lists(0);
	MNIST_Generate_Test_Performance_Lists_Vector(test_performance_lists);


	vector<CascadeNode*> all_nodes(0);
	MNIST_Generate_CascadeNodes_Vector(all_nodes);


	Get_Thresholds(
		/*vector<CascadeNode*>&*/ all_nodes,
		/*vector<vector<ClassifiedSample*>*>&*/ performance_lists
		);

	//Inspection 
	ofstream fout("inspection_1.txt");
	int N= all_nodes.size();
	float total_known_by_node_but_rejected_by_last(0);
	for(int i=0; i< N-1; i++)
	{
		float known_by_node_but_rejected_by_last(0);

		for(int k=0; k<10000; k++)
		{
			if((*performance_lists[N-1])[k]->score > all_nodes[N-1]->thresh || (*performance_lists[i])[k]->score < all_nodes[i]->thresh)
				continue;

			if((*performance_lists[i])[k]->err==0)
				known_by_node_but_rejected_by_last++;

		}
		total_known_by_node_but_rejected_by_last+=known_by_node_but_rejected_by_last;
		
		fout<<all_nodes[i]->ID<<endl;
		fout<<known_by_node_but_rejected_by_last<<endl<<endl;

	}
	fout<<"Total: "<<total_known_by_node_but_rejected_by_last<<endl;



	////Inspection 2
	//ofstream fout("inspection_2.txt");
	//int N= all_nodes.size();
	//float total_known_by_node_but_not_by_last(0);
	//for(int i=0; i< N-1; i++)
	//{
	//	float known_by_node_but_not_by_last(0);

	//	for(int k=0; k<10000; k++)
	//	{
	//		if((*performance_lists[i])[k]->score < all_nodes[i]->thresh || (*performance_lists[i])[k]->err==1)
	//			continue;

	//		if((*performance_lists[N-1])[k]->err==1)
	//			known_by_node_but_not_by_last++;

	//	}
	//	total_known_by_node_but_not_by_last+=known_by_node_but_not_by_last;
	//	
	//	fout<<all_nodes[i]->ID<<endl;
	//	fout<<known_by_node_but_not_by_last<<endl<<endl;

	//}
	//fout<<"Total: "<<total_known_by_node_but_not_by_last<<endl;


	cout<<"Done."<<endl;
	getch();

	return 0;
}

