#include "StdAfx.h"
#include "NeuralClassifier.h"



NeuralClassifier::NeuralClassifier(void)
{
}

NeuralClassifier::~NeuralClassifier(void)
{
}
void NeuralClassifier::InitClassifier(){
 
	int my_digit=5;  //this is only for testing. 
	if (allocator ==NULL){
	allocator = new Allocator;
	}

	// creating the mlp
	ConnectedMachine mlp[NO_OF_CLASSES];
	Linear *c1[NO_OF_CLASSES];
	Tanh *c2[NO_OF_CLASSES];
	Linear *c3[NO_OF_CLASSES];
	Tanh *c4[NO_OF_CLASSES];

	for(int count = my_digit; count <= my_digit; count++)
	{
		c1[count]= new(allocator) Linear(num_of_features,n_hu);
		c2[count]= new(allocator) Tanh(n_hu);
		c3[count]= new(allocator) Linear(n_hu,1);
		c4[count]= new(allocator) Tanh(1);
		mlp[count].addFCL(c1[count]);
		mlp[count].addFCL(c2[count]);
		mlp[count].addFCL(c3[count]);
		mlp[count].addFCL(c4[count]);
		mlp[count].build();
		mlp[count].setPartialBackprop();
	//	mlp[count].loadXFile(&model_file);
	}
}


void NeuralClassifier::TEST_DATA(char* test_file, char* model, int n_hu){

Allocator *allocator = new Allocator;
	int num_of_features = 9;
    int my_digit = 5;
	  cout<<"Testing.......... ."<<endl;
	DiskXFile model_file(model, "r");

	InitClassifier();



	  cout<<"Reading data .......... ."<<endl;
	DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);

	/*Local variables & Initialization*/

	
	Timer timer;
	accuracy=0;
	
	//ofstream fout("test_stats.txt");

		  cout<<"Forwarding samples .......... ."<<endl;
		  	
	//DataSet *test_data,  Machine* machine,int ExpectedDigit, char* StatFile
	TestExamplesForDigit(test_data,mlp, my_digit,"test_stat.txt");



	delete(allocator);


}
/*
void NeuralClassifier::InitClassifier(){

		// creating the mlp
	ConnectedMachine mlp[10];
	Linear *c1[10];
	Tanh *c2[10];
	Linear *c3[10];
	Tanh *c4[10];

	
	for(int count = 5; count <= 5; count++)
	{
		c1[count]= new(allocator) Linear(num_of_features,n_hu);
		c2[count]= new(allocator) Tanh(n_hu);
		c3[count]= new(allocator) Linear(n_hu,1);
		c4[count]= new(allocator) Tanh(1);
		mlp[count].addFCL(c1[count]);
		mlp[count].addFCL(c2[count]);
		mlp[count].addFCL(c3[count]);
		mlp[count].addFCL(c4[count]);
		mlp[count].build();
		mlp[count].setPartialBackprop();
		//mlp[count].loadXFile(&model_file);
	}

}*/
void NeuralClassifier::TEST_DATA_BITS(char* test_file, char* model, int n_hu)
{
	Allocator *allocator = new Allocator;
	int num_of_features = 9;
    int my_digit = 5;
	  cout<<"Testing.......... ."<<endl;
	DiskXFile model_file(model, "r");

	// creating the mlp
	ConnectedMachine mlp[10];
	Linear *c1[10];
	Tanh *c2[10];
	Linear *c3[10];
	Tanh *c4[10];

	for(int count = my_digit; count <= my_digit; count++)
	{
		c1[count]= new(allocator) Linear(num_of_features,n_hu);
		c2[count]= new(allocator) Tanh(n_hu);
		c3[count]= new(allocator) Linear(n_hu,1);
		c4[count]= new(allocator) Tanh(1);
		mlp[count].addFCL(c1[count]);
		mlp[count].addFCL(c2[count]);
		mlp[count].addFCL(c3[count]);
		mlp[count].addFCL(c4[count]);
		mlp[count].build();
		mlp[count].setPartialBackprop();
		mlp[count].loadXFile(&model_file);
	}
	  cout<<"Reading data .......... ."<<endl;
	DataSet *test_data =new(allocator) MatDataSet(test_file,num_of_features,1,false,-1,false);

	/*Local variables & Initialization*/
	float accuracy(0);
    float positivesamples(0);
	float n_iter=10;
	Timer timer;
	accuracy=0;
	
	ofstream fout("test_stats.txt");

		  cout<<"Forwarding samples .......... ."<<endl;
		  	long fivesamples=0;
	/*Testing...*/
	for(int t = 0; t < test_data->n_examples; t++)
	{
		if((t%100) == 0) cout<<t<<endl;

		test_data->setExample(t);
		int d = test_data->targets->frames[0][0];//actual value of sample
		
        /*Get the score from the corresponding NN*/
		mlp[my_digit].forward(test_data->inputs);
		real score = mlp[my_digit].outputs->frames[0][0];

			if (d==my_digit)
			fivesamples++;

        if(score > 0)
        {
            positivesamples++;
            if(d == my_digit)
			{
                accuracy++;
			}
			else
			{
				char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56  
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
				fout<<"### "<<t<<" "<<filename<<"; score = "<<score<<endl;
			}
        }
		else if(d == my_digit)
		{
			char filename[50];
			int writer, pass, digit;

			writer = t/100;
			pass = (t-(writer*100))/10;
			digit = (t-(writer*100))%10;
			sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
			fout<<"--- "<<t<<" "<<filename<<"; score = ["<<score<<"]"<<endl;
		}
	}

	fout<<endl<<endl;
	double samplerate=100.0/(double)fivesamples;
	fout<<" total number of samples "<<test_data->n_examples<<"Count of digit 5 samples "<<fivesamples<<endl;
	fout<<"Samples with result > 0: "<<positivesamples<<endl;
    fout<<"Samples with the corresponding digit and result > 0: "<<accuracy<<"sample and "<< accuracy*samplerate<<" % "<<endl;

	
	delete(allocator);
}
void NeuralClassifier::TRAIN_DATA_BITS(char* train_file, char* model, int n_hu)
{

	  cout<<"Training.......... ."<<endl;
	Allocator *allocator = new Allocator;
	int num_of_features = 9;
    int my_digit = 5;
	
	DiskXFile out_model_file(model, "w");

	// creating the mlp
	ConnectedMachine mlp[10];
	Linear *c1[10];
	Tanh *c2[10];
	Linear *c3[10];
	Tanh *c4[10];
	  cout<<"Addng layers......... ."<<endl;
	for(int count = my_digit; count <= my_digit; count++)
	{
		c1[count]= new(allocator) Linear(num_of_features,n_hu);
		c2[count]= new(allocator) Tanh(n_hu);
		c3[count]= new(allocator) Linear(n_hu,1);
		c4[count]= new(allocator) Tanh(1);

		mlp[count].addFCL(c1[count]);
		mlp[count].addFCL(c2[count]);
		mlp[count].addFCL(c3[count]);
		mlp[count].addFCL(c4[count]);
		mlp[count].build();
		mlp[count].setPartialBackprop();
    }
	  cout<<"Reading data.......... ."<<endl;
	DataSet *train_set;
	train_set=new(allocator) MatDataSet(train_file,num_of_features,1,false,-1,false);

	Criterion *criterion[10];
	// creating the trainer
	Trainer **trainers = (Trainer **)allocator->alloc(sizeof(Trainer *)*10);
	  cout<<"Training each digit.......... ."<<endl;
	/*Training each digit*/
	for(int count = my_digit; count <= my_digit; count++)
	{
		long trained_with_one = 0;
		long trainedAsNotFive=0;
		cout<<"Training digit "<<count<<endl;
		for(int t = 0; t < train_set->n_examples; t++)
		{
		
			train_set->setExample(t);
				if((t%500) == 0)  {
				cout<<"Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;
				
			}
            
			if(train_set->targets->frames[0][0] == count)
			{
				
                train_set->targets->frames[0][0] = 1;
				trained_with_one++;
				//cout<<"Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;
			}
			else {
		//		cout<<"---------------Example "<<t<<" Target is "<<train_set->targets->frames[0][0]<<endl;
                train_set->targets->frames[0][0] = -1;
				trainedAsNotFive++;

			}
		}
	 
		cout<<"examples trained with one: "<<trained_with_one<<endl;
		cout<<"examples trained not five: "<<trainedAsNotFive<<endl;
        cout<<"total number of examples: "<<train_set->n_examples<<endl;
				
		criterion[count] = new(allocator) MSECriterion(1);
		trainers[count] = new(allocator) StochasticGradient(&mlp[count], criterion[count]);

		trainers[count]->setIOption("max iter",100);
			  cout<<"Train digit.......... ."<<endl;
		trainers[count]->train(train_set, NULL);

			  cout<<"Save file.......... ."<<endl;
		mlp[count].saveXFile(&out_model_file);
	}
	delete(allocator);
}