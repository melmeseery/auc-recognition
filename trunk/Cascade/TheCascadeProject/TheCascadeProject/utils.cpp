#include "StdAfx.h"
#include "utils.h"

utils::utils(void)
{
}

utils::~utils(void)
{
}
//Selecting subpools from the original large pool of classifiers according to a file.
//The subpools are stored in a vector of subpools (a vector of vector of nodes).
//Each subpool should contain the last stage of the original pool as a last stage too.
void utils::Select_Subpools_From_Pool(char* file_name, vector<CascadeNode*>& pool,
							   vector<vector<CascadeNode*>*>& subpools
							   )
{
	ifstream fin(file_name);
	if(fin.fail())
	{cout<<"Select_Subpools_From_Pool:: Cannot open file: "<<file_name<<endl; getch(); }

	int N= pool.size();//number of classifiers in the pool

	int number_of_subpools, number_of_stages_per_subpool;
	fin>>number_of_subpools;
	fin>>number_of_stages_per_subpool;

	subpools.clear();
	for(int i=0; i<number_of_subpools; i++)
	{
		subpools.push_back(new vector<CascadeNode*>(0));

		//adding stages except the final stage
		for(int j=0; j<number_of_stages_per_subpool; j++)
		{
			int id;
			fin>>id;

			subpools[i]->push_back(pool[id-1]);

		}

		//adding the final stage
		subpools[i]->push_back(pool[N-1]);
		
	}

	fin.close();

	
}

void   utils::Select_Best_ModelBased_Cascade(
							  vector<CascadeNode*>& _all_nodes, //from which we build the best cascade
							  vector<CascadeNode*>& best_cascade,
							  float& best_cascade_theoretical_complexity
							  )
{

	int N= _all_nodes.size();
	N++;//considering the dummy node that will be added
	int n_examples= _all_nodes[0]->performance_list->size();

	vector<ModelBasedCascadeNode*> all_nodes(N);
	for(int i=1; i<N; i++)
	{
		all_nodes[i]= new ModelBasedCascadeNode(_all_nodes[i-1]);
		all_nodes[i]->complexity_of_best_path=0;
	}
	//the dummy starting node
	all_nodes[0]= new ModelBasedCascadeNode();
	all_nodes[0]->n_rejected= n_examples;


	vector<ModelBasedCascadeNode*>::iterator end_iter(all_nodes.end());
	vector<ModelBasedCascadeNode*>::iterator begin_iter(all_nodes.begin());
	sort(++begin_iter, --end_iter, rejections_descend_compare_nodes());



	all_nodes[N-2]->best_path.push_back(all_nodes[N-1]);
	all_nodes[N-2]->complexity_of_best_path= all_nodes[N-2]->n_rejected * all_nodes[N-1]->complexity;
	for(int i=N-3; i>=0; i--)
	{
		float min_complexity(-1);
		int stage_of_min_complexity;
		for(int j=i+1; j<N; j++)
		{
			float complexity= all_nodes[i]->n_rejected * all_nodes[j]->complexity + all_nodes[j]->complexity_of_best_path;
			if(min_complexity==-1)
			{min_complexity= complexity; stage_of_min_complexity=j+1;}
			else if(complexity<min_complexity)
			{min_complexity=complexity; stage_of_min_complexity=j+1;}
		}
		all_nodes[i]->complexity_of_best_path= min_complexity;
		all_nodes[i]->best_path.push_back(all_nodes[stage_of_min_complexity-1]);
		for(int s=0; s<all_nodes[stage_of_min_complexity-1]->best_path.size(); s++)
			all_nodes[i]->best_path.push_back(all_nodes[stage_of_min_complexity-1]->best_path[s]);
	}



	best_cascade_theoretical_complexity= all_nodes[0]->complexity_of_best_path/n_examples;


	//preparing the best cascade vector
	best_cascade.clear();
	for(int j=0; j<all_nodes[0]->best_path.size(); j++)
	{
		CascadeNode* node= new CascadeNode();
		node->thresh= all_nodes[0]->best_path[j]->thresh;
		node->complexity= all_nodes[0]->best_path[j]->complexity;
		node->model= all_nodes[0]->best_path[j]->model;
		node->performance_list= all_nodes[0]->best_path[j]->performance_list;
		node->test_performance_list= all_nodes[0]->best_path[j]->test_performance_list;

		node->ID= (char*) malloc((strlen(all_nodes[0]->best_path[j]->ID))*sizeof(char));
		strcpy(node->ID,all_nodes[0]->best_path[j]->ID);

		best_cascade.push_back(node);

	}



	for(int i=0; i<N; i++)
		delete(all_nodes[i]);
	

}
 DataSet*  utils::Create_Living_Set(vector<CascadeNode*>& cascade, Allocator* allocator)
{

	int n_classifiers= cascade.size();
	int n_examples= cascade[0]->performance_list->size();

	MemoryDataSet* set= new(allocator) MemoryDataSet();
	Sequence** inputs= (Sequence**)allocator->alloc(sizeof(Sequence*)*n_examples);
	for(int n=0; n<n_examples; n++)
	{
		inputs[n]= new(allocator) Sequence(2,n_classifiers);
	}
	set->setInputs(inputs, n_examples);
	set->n_examples=n_examples;

	for(int i=0; i<n_classifiers; i++)
	{

		for(int k=0; k<n_examples; k++)
		{
			set->setExample(k);

			set->inputs->frames[0][i]= (*cascade[i]->performance_list)[k]->score;
			set->inputs->frames[1][i]= (*cascade[i]->performance_list)[k]->err;

		}
	}

	return set;

}

  void utils::MNIST_Generate_CascadeNodes_Vector(vector<CascadeNode*>& vec)
{

	float complexity_normalizer= 10*(2*25+3.5);

        cout<<" Genearating the cascade nodes ............"<<endl;
	vec.clear();

	//linear classifiers
	for(int n=25; n<=200; n+=25)
	{
		cout<<"  for "<< n <<"  Features "<<endl;
		CascadeNode* new_node= new CascadeNode;
       // this code to change the string with each new lop 
		// to add the number of features to the stirng. 
		char* ID= new char[12];
		char temp[]="linear_n***";
		for(int i=0; i<12; i++)
			ID[i]=temp[i];

		int z;
		for(z=0;;z++)
		{
			if(ID[z]=='*')
				break;
		}
		ID[z++]=(int)'0' + n/100;
		ID[z++]=(int)'0' + (n%100)/10;
		ID[z++]= (int)'0' + n%10 ;
       // now creta eh new node 
		cout<<"ID = "<<ID<<endl;
		new_node->ID=ID;
		new_node->thresh=0;
		new_node->complexity= 10*(2*n+3.5)/complexity_normalizer;
		cout<<"	new_node->complexity = "<<	new_node->complexity<<endl;


		char linear_model_file_name[]="linear_n***";

		//char linear_model_file_name[]="C:/datasets/MNIST/Models/linear_n***";


		//change the file name 
		for(z=0;;z++)
		{
			if(linear_model_file_name[z]=='*')
				break;
		}

		linear_model_file_name[z++]=(int)'0' + n/100;
		linear_model_file_name[z++]=(int)'0' + (n%100)/10;
		linear_model_file_name[z++]= (int)'0' + n%10 ;
		cout<<" file name is ......... "<<endl;
		cout<<linear_model_file_name<<endl;

		//creating and loading the machine
		OVA_OneLayer_NN* machine= new OVA_OneLayer_NN(n,10);
		cout<<"  Machine now......."<<endl;
		DiskXFile file(linear_model_file_name,"r");
		machine->loadXFile(&file);
		new_node->model=machine;

		vec.push_back(new_node);

	}


	//neural networks
	for(int n=25; n<=200; n+=25)
	{
		for (int h=50; h<=200; h+=50)
		{

			CascadeNode* new_node= new CascadeNode;

			char* ID= new char[14];
			char temp[]="mlp_n***_h***";
			for(int i=0; i<14; i++)
				ID[i]=temp[i];

			int z;
			for(z=0;;z++)
			{
				if(ID[z]=='*')
					break;
			}
			ID[z++]=(int)'0' + n/100;
			ID[z++]=(int)'0' + (n%100)/10;
			ID[z++]= (int)'0' + n%10 ;
			z+=2;
			ID[z++]=(int)'0' + h/100;
			ID[z++]=(int)'0' + (h%100)/10;
			ID[z++]= (int)'0' + h%10 ;

			new_node->ID=ID;
			new_node->thresh=0;
			new_node->complexity= (h*(2*n+3.5)+10*(2*h+1.5))/complexity_normalizer;



			char mlp_model_file_name[]="C:/datasets/MNIST/Models/mlp_n***_h***";

			for(z=0;;z++)
			{
				if(mlp_model_file_name[z]=='*')
					break;
			}
			mlp_model_file_name[z++]=(int)'0' + n/100;
			mlp_model_file_name[z++]=(int)'0' + (n%100)/10;
			mlp_model_file_name[z++]= (int)'0' + n%10 ;
			z+=2;
			mlp_model_file_name[z++]=(int)'0' + h/100;
			mlp_model_file_name[z++]=(int)'0' + (h%100)/10;
			mlp_model_file_name[z++]= (int)'0' + h%10 ;
			cout<<mlp_model_file_name<<endl;


			//creating, training, and saving the machine
			TwoLayer_NN* machine= new TwoLayer_NN(n,h,10);
			DiskXFile file(mlp_model_file_name,"r");
			machine->loadXFile(&file);
			new_node->model= machine;

			vec.push_back(new_node);


		}
	}


	////svm
	//for(int n=25; n<=200; n+=25)
	//{

	//	CascadeNode* new_node= new CascadeNode;
	//	char* ID= new char[8];
	//	char temp[]="svm_***";
	//	for(int i=0; i<12; i++)
	//		ID[i]=temp[i];
	//	int z;

	//	for(z=0;;z++)
	//	{
	//		if(ID[z]=='*')
	//			break;
	//	}
	//	ID[z++]=(int)'0' + n/100;
	//	ID[z++]=(int)'0' + (n%100)/10;
	//	ID[z++]= (int)'0' + n%10 ;
	//	new_node->ID=ID;
	//	new_node->thresh=0;


	//	char svm_model_file_name[]="C:/datasets/MNIST/Models/svm_***";


	//	for(z=0;;z++)
	//	{
	//		if(svm_model_file_name[z]=='*')
	//			break;
	//	}
	//	svm_model_file_name[z++]=(int)'0' + n/100;
	//	svm_model_file_name[z++]=(int)'0' + (n%100)/10;
	//	svm_model_file_name[z++]= (int)'0' + n%10 ;
	//	cout<<svm_model_file_name<<endl;

	//	OVO_RBF_SVM* machine= new OVO_RBF_SVM(n,10,0.05,10);
	//	machine->load(svm_model_file_name);
	//	new_node->model=machine;
	//	float n_usv=machine->number_of_unique_support_vectors;
	//	new_node->complexity=(n_usv*(3*n+2.5)+2*n_usv)/complexity_normalizer;

	//	vec.push_back(new_node);

	//}


	////ova svm
	//for(int n=25; n<=200; n+=25)
	//{

	//	CascadeNode* new_node= new CascadeNode;
	//	char* ID= new char[8];
	//	char temp[]="ova_svm_***";
	//	for(int i=0; i<12; i++)
	//		ID[i]=temp[i];
	//	int z;

	//	for(z=0;;z++)
	//	{
	//		if(ID[z]=='*')
	//			break;
	//	}
	//	ID[z++]=(int)'0' + n/100;
	//	ID[z++]=(int)'0' + (n%100)/10;
	//	ID[z++]= (int)'0' + n%10 ;
	//	new_node->ID=ID;
	//	new_node->thresh=0;


	//	char svm_model_file_name[]="C:/datasets/MNIST/Models/ova_svm_***";


	//	for(z=0;;z++)
	//	{
	//		if(svm_model_file_name[z]=='*')
	//			break;
	//	}
	//	svm_model_file_name[z++]=(int)'0' + n/100;
	//	svm_model_file_name[z++]=(int)'0' + (n%100)/10;
	//	svm_model_file_name[z++]= (int)'0' + n%10 ;
	//	cout<<svm_model_file_name<<endl;

	//	OVA_RBF_SVM* machine= new OVA_RBF_SVM(n,10,0.05,10);
	//	machine->load(svm_model_file_name);
	//	new_node->model=machine;
	//	float n_usv=machine->number_of_unique_support_vectors;
	//	new_node->complexity=(n_usv*(3*n+2.5)+2*n_usv)/complexity_normalizer;

	//	vec.push_back(new_node);

	//}


}