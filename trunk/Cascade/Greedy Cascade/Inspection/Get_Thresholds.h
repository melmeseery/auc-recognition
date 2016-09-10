
#ifndef GET_THRESHOLDS
#define GET_THRESHOLDS

#include "Common_Definitions.h"
#include "GeneralMachine.h"
#include "ClassifiedSample.h"
#include "ModelBasedCascadeNode.h"

////For inspectin 2
//void Get_Thresholds(	vector<CascadeNode*>& all_nodes, vector<vector<ClassifiedSample*>*>& _performance_lists)
//						
//{
//	int N= all_nodes.size();
//	int n_examples= _performance_lists[0]->size();
//
//
//	vector<vector<ClassifiedSample*>*> performance_lists(N);
//	for(int i=0; i<N; i++)
//	{
//		performance_lists[i]= new vector<ClassifiedSample*>(n_examples);
//		for(int k=0; k<n_examples; k++)
//		{
//			(*performance_lists[i])[k]= new ClassifiedSample;
//			(*performance_lists[i])[k]->err= (*_performance_lists[i])[k]->err;
//			(*performance_lists[i])[k]->score= (*_performance_lists[i])[k]->score;
//			(*performance_lists[i])[k]->index= (*_performance_lists[i])[k]->index;
//
//		}
//	}
//
//
//	for(int i=0; i<N-1; i++)
//		sort(performance_lists[i]->begin(), performance_lists[i]->end(), score_descend_compare_samples());
//
//
//	//Finding thresholds
//	int max_e=1;
//	for(int i=0; i<N-1; i++)
//	{
//		int e(0);
//		for(int k=0; k<n_examples; k++)
//		{
//			if((*performance_lists[i])[k]->err==1 && (*performance_lists[N-1])[(*performance_lists[i])[k]->index]->err==0)
//				e++;
//
//			if(e==max_e)
//			{
//				all_nodes[i]->thresh= (*performance_lists[i])[k]->score;
//				//cout<<"ID: "<<all_nodes[i]->ID<<endl;
//				//cout<<"thresh: "<<all_nodes[i]->thresh<<endl;
//				//getch();
//				all_nodes[i]->n_rejected= (n_examples-k);
//				break;
//			}
//		}
//	}
//
//	all_nodes[N-1]->thresh=0;
//
//
//}


// For inspection 1
void Get_Thresholds(	vector<CascadeNode*>& all_nodes, vector<vector<ClassifiedSample*>*>& _performance_lists)
						
{
	int N= all_nodes.size();
	int n_examples= _performance_lists[0]->size();


	vector<vector<ClassifiedSample*>*> performance_lists(N);
	for(int i=0; i<N; i++)
	{
		performance_lists[i]= new vector<ClassifiedSample*>(n_examples);
		for(int k=0; k<n_examples; k++)
		{
			(*performance_lists[i])[k]= new ClassifiedSample;
			(*performance_lists[i])[k]->err= (*_performance_lists[i])[k]->err;
			(*performance_lists[i])[k]->score= (*_performance_lists[i])[k]->score;
			(*performance_lists[i])[k]->index= (*_performance_lists[i])[k]->index;

		}
	}


	for(int i=0; i<N-1; i++)
		sort(performance_lists[i]->begin(), performance_lists[i]->end(), score_descend_compare_samples());


	//Finding thresholds
	int max_e=3;
	for(int i=0; i<N; i++)
	{
		int e(0);
		for(int k=0; k<n_examples; k++)
		{
			if((*performance_lists[i])[k]->err==1)
				e++;

			if(e==max_e)
			{
				all_nodes[i]->thresh= (*performance_lists[i])[k]->score;
				//cout<<"ID: "<<all_nodes[i]->ID<<endl;
				//cout<<"thresh: "<<all_nodes[i]->thresh<<endl;
				//getch();
				all_nodes[i]->n_rejected= (n_examples-k);
				break;
			}
		}
	}


}



#endif