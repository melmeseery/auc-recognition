// DFS_Cascade.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "Build_Cascade_DFS.h"
#include "MNIST_Generate_CascadeNodes_Vector.h"
#include "MNIST_Generate_Validation_Performance_Lists_Vector.h"
#include "MNIST_Generate_Test_Performance_Lists_Vector.h"



int _tmain(int argc, _TCHAR* argv[])
{

	//vector<GeneralMachine*> models_vec(0);
	//MNIST_Generate_Nodes_Models_Vector(models_vec);

	vector<vector<ClassifiedSample*>*> _performance_lists(0);
	MNIST_Generate_Validation_Performance_Lists_Vector(_performance_lists);
	vector<vector<ClassifiedSample*>*> performance_lists(0);
	for(int i=1; i<=7; i+=2)
	{performance_lists.push_back(_performance_lists[i]);}


	vector<CascadeNode*> _all_nodes(0);
	MNIST_Generate_CascadeNodes_Vector(_all_nodes);
	vector<CascadeNode*> all_nodes(0);
	for(int i=1; i<=7; i+=2)
	{all_nodes.push_back(_all_nodes[i]);}

	//cout<<endl<<endl<<"Show begins!"<<endl;
	//for(int i=0; i<all_nodes.size(); i++)
	//	cout<<all_nodes[i]->ID<<endl;
	//getch();


	
	vector<CascadeNode*> best_cascade(0);
	float best_cascade_complexity(-1);
	float best_cascade_error(0);
	Build_Cascade_DFS(
					   /*vector<CascadeNode*>&*/ all_nodes,
					   /*vector<CascadeNode*>&*/ best_cascade,
					   /*vector<vector<ClassifiedSample*>*>&*/ performance_lists,
					   /*float max_error*/190,
					   /*float Q*/32,
					   /*float&*/ best_cascade_complexity,
					   /*float&*/ best_cascade_error
					   );

	cout<<endl<<endl<<"Best Cascade:"<<endl;
	cout<<"Thresholds: ";
	for(int i=0; i<best_cascade.size(); i++)
		cout<<best_cascade[i]->thresh<<", ";

	cout<<endl<<"Complexity: "<<best_cascade_complexity<<endl;
	cout<<"Error: "<<best_cascade_error<<endl<<endl;
	

	cout<<"Done."<<endl;
	getch();

	return 0;
}

