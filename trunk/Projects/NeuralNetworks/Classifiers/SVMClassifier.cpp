#include "StdAfx.h"
#include "SVMClassifier.h"

SVMClassifier::SVMClassifier(void)
{
}

SVMClassifier::~SVMClassifier(void)
{
}
void SVMClassifier::TRAIN()
{
	int num_of_features=28*28;
	 
	Allocator *allocator = new Allocator;


	char train_file[]="C:/datasets/mahdbase_training_set.txt";
	DiskXFile model_file("model", "w");


	SVMClassification *svms[10][10];
	QCTrainer *trainers[10][10];
	float g=0.02;//0.0659
	float C=100;
	Kernel *kernel = new(allocator) GaussianKernel(g);

	DataSet *train_set;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	//MeanVarNorm mv_norm(train_set);
	//train_set->preProcess(&mv_norm);


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


	int counter;
	for(int i=0;i<=8;i++)
	{
		for(int j=i+1;j<=9;j++)
		{
			cout<<"Training "<<i<<" vs "<<j<<" ."<<endl;
	

			svms[i][j] = new(allocator) SVMClassification(kernel);
			svms[i][j]->setROption("C", C);
			trainers[i][j]= new(allocator) QCTrainer(svms[i][j]);

			counter=0;
			for(long k=0; k<60000; k++)
			{

				train_set->setExample(k);
				int d= train_set->targets->frames[0][0];

				if(d==i || d==j)
				{

					current_train_set->setExample(counter);

					for(int f=0; f<num_of_features; f++)
					{
						current_train_set->inputs->frames[0][f]=
							train_set->inputs->frames[0][f];
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
			trainers[i][j]->train(current_train_set,NULL);
			current_train_set->n_examples=counter;
			svms[i][j]->saveXFile(&model_file);


		}
	}


	delete(allocator);


}

void SVMClassifier::TEST()
{


	ifstream calib_fin("calibrating_parameters.txt");
	float As[10][10];
	float Bs[10][10];
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{calib_fin>>As[i][j]; calib_fin>>Bs[i][j];}
	}


	int num_of_features=28*28;

	Allocator *allocator = new Allocator;

	char test_file[]="C:/datasets/mahdbase_testing_set.txt";

	DiskXFile model_file("model", "r");


	DataSet *test_set;
	test_set=new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);


	ifstream sv_fin("support_vectors.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors.txt"; getch();}
	int n;
	sv_fin>>n;
	//cout<<n<<endl;
	//getch();
	vector<Sequence*> SupportVectors(n);
	for(int n=0; n<SupportVectors.size(); n++)
	{
		SupportVectors[n]= new Sequence(1,28*28);
		for(int f=0; f<28*28; f++)
		{sv_fin>>(SupportVectors[n]->frames[0][f]); /*cout<<(*SupportVectors[n])[f]<<endl; getch();*/}
	}


	ifstream sv_dist_fin("support_vectors_distribution.txt");
	if(sv_fin.fail()){cout<<"Cannot Open file: support_vectors_distribution.txt"; getch();}
	sv_dist_fin>>n;
	vector<int> SupportVectorsDist(n);
	for(int n=0; n<SupportVectorsDist.size(); n++)
		sv_dist_fin>>SupportVectorsDist[n]; 



	// creating pairwise classifiers pointers
	float g=0.02;//0.0659
	float C=100;
	Kernel *kernel = new(allocator) GaussianKernel(g);
	SVMClassification *svms[10][10];


	//loading pairwise classifiers in turn
	for(int i=0; i<=8; i++)
	{
		for(int j=i+1; j<=9; j++)
		{
			cout<<i<<" vs "<<j<<endl;

			svms[i][j] = new(allocator) SVMClassification(kernel);

			svms[i][j]->loadXFile(&model_file);

		}//end i
	}//end j





	float actual_accuracy(0);
	const int Nt=100000;
	float n_rejected[Nt]; // number of rejected samples
	float correct[Nt];
	for(int t=0; t<Nt; t++)
	{
		correct[t]=0;
		n_rejected[t]=0;
	}
	ofstream fout("results.txt");
	vector<float> KernelEvaluations(SupportVectors.size());
	for(int k = 0; k < test_set->n_examples; k++)
	{

		if(k%100==0)
			cout<<k<<endl;


		test_set->setExample(k);

		int d= test_set->targets->frames[0][0];

		for(int n=0; n<SupportVectors.size(); n++)
			KernelEvaluations[n]=kernel->eval(test_set->inputs,SupportVectors[n]);


		real binary_decisions[10][10];
		int count=0;
		for(int i=0; i<=8; i++)
		{
			for(int j=i+1; j<=9; j++)
			{
				float f=svms[i][j]->b;
				for(int n=0; n<svms[i][j]->n_support_vectors; n++)
					f += KernelEvaluations[SupportVectorsDist[count++]]*svms[i][j]->sv_alpha[n];

				binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j])); 
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


		real first_max_value;
		long first_max_index;
		real second_max_value;
		long second_max_index;

		// finding value and index of first and second maxima
		getMax(decisions,10,first_max_value,first_max_index);
		decisions[first_max_index]=-99;
		//getMax(decisions,10,second_max_value,second_max_index);

		if(first_max_index==d)
			actual_accuracy++;

		//cout<<d<<"->"<<first_max_index<<endl; getch();

		for(int t=0;t<Nt; t++)
		{
			float th= ((float)(t))/((float) Nt);
			if((first_max_value-second_max_value)<th)
				n_rejected[t]++;
			else if(first_max_index==d)
				correct[t]++;
		}

	}

	//cout<<correct[Nt-1]/(10000-n_rejected[Nt-1])*100<<endl; getch();

	bool found(false);
	for(int t=0; t<Nt; t++)
	{
		if(correct[t]/(10000-n_rejected[t])*100>99.5)
		{
			fout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;
			cout<<"Accuracy without rejection: "<<actual_accuracy/100<<"%"<<endl;

			fout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;
			cout<<"Accuracy after rejection: "<<correct[t]/(10000-n_rejected[t])*100<<"%"<<endl;

			fout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;		
			cout<<"RR= "<<n_rejected[t]/100<<"%"<<endl;

			float th= ((float)(t))/((float) Nt);
			fout<<"th= "<<th<<endl;
			cout<<"th= "<<th<<endl;

			found=true;
			break;

		}
	}

	if(!found)
		cout<<"99.5% not reached!"<<endl;

	
	
	//float recog_time(0);
	//Timer timer;
	//for(int k = 0; k < test_set->n_examples; k++)
	//{

	//	test_set->setExample(k);

	//	int d= test_set->targets->frames[0][0];

	//	for(int n=0; n<SupportVectors.size(); n++)
	//		KernelEvaluations[n]=kernel->eval(test_set->inputs,SupportVectors[n]);


	//	real binary_decisions[10][10];
	//	int count=0;
	//	for(int i=0; i<=8; i++)
	//	{
	//		for(int j=i+1; j<=9; j++)
	//		{
	//			float f=svms[i][j]->b;
	//			for(int n=0; n<svms[i][j]->n_support_vectors; n++)
	//				f += KernelEvaluations[SupportVectorsDist[count++]]*svms[i][j]->sv_alpha[n];

	//			binary_decisions[i][j]= 1/(1+exp(As[i][j]*f+Bs[i][j])); 
	//			binary_decisions[j][i]= 1-binary_decisions[i][j];
	//		}
	//	}

	//	real decisions[10];
	//	for(int i=0; i<=9; i++)
	//	{
	//		real prob_sum(0);
	//		for(int j=0; j<=9; j++)
	//		{
	//			if(i!=j)
	//			{
	//				prob_sum += 1/(binary_decisions[i][j]);
	//			}
	//		}

	//		decisions[i]= 1/(prob_sum - 8);
	//	}


	//	real first_max_value;
	//	long first_max_index;

	//	// finding value and index of first and second maxima
	//	getMax(decisions,10,first_max_value,first_max_index);

	//}
	//recog_time= timer.getTime();

	//fout<<"Recognition Time: "<<recog_time<<endl;
	//cout<<"Recognition Time: "<<recog_time<<endl;



	delete(allocator);


}
