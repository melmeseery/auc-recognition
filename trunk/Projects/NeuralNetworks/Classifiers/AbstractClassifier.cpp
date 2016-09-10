#include "StdAfx.h"
#include "AbstractClassifier.h"

AbstractClassifier::AbstractClassifier(void)
{


	 accuracy=0;
     positiveSamples=0;
	 negatvieSamples=0;

	 num_of_features=10;

		allocator = new Allocator;


}

AbstractClassifier::~AbstractClassifier(void)
{
}
void AbstractClassifier::getMax(real* arr, long n_arr, real &max_value, long &max_index)
{
	max_index=0;
	max_value= arr[0];
	for(int i=1; i<n_arr; i++)
	{
		if(arr[i]>max_value)
		{
			max_index =i;
			max_value =arr[i];
		}
	}
}
void AbstractClassifier::TestExamplesForDigit(DataSet *test_data,  Machine* machine,int ExpectedDigit, char* StatFile){
ofstream fout(StatFile);
float n_iter=10;
	Timer timer;
	accuracy=0;
/*Testing...*/
	for(int t = 0; t < test_data->n_examples; t++)
	{
		if((t%100) == 0)
			cout<<t<<endl;

		test_data->setExample(t);
		int d = test_data->targets->frames[0][0];//actual value of sample
		
        /*Get the score from the corresponding NN*/
		machine->forward(test_data->inputs);
		real score = machine->outputs->frames[0][0];

		//	if (d==ExpectedDigit)
		//	fivesamples++;

        if(score > 0)
        {
           positiveSamples++;
            if(d == ExpectedDigit)
			{
                accuracy++;
			}
			else
			{
				char* filename;
				getImageFileName( t,filename);
				fout<<"### "<<t<<" "<<filename<<"; score = "<<score<<endl;
			}
        }
		else if(d == ExpectedDigit)
		{
                 char* filename;
			negatvieSamples++;
			 getImageFileName( t,filename);
			fout<<"--- "<<t<<" "<<filename<<"; score = ["<<score<<"]"<<endl;
		}
	}
	fout<<endl<<endl;
	//double samplerate=100.0/(double)fivesamples;
	fout<<" total number of samples "<<test_data->n_examples<<endl;//<<"Count of digit 5 samples "<<fivesamples<<endl;
	fout<<"Samples with result > 0: "<<positiveSamples<<endl;
    fout<<"Samples with the corresponding digit and result > 0: "<<accuracy<<endl;//<<"sample and ";<< accuracy*samplerate<<" % "<<endl;


}

 void AbstractClassifier::getImageFileName(int t, char* filename ){

			int writer, pass, digit;

			writer = t/100;
			pass = (t-(writer*100))/10;
			digit = (t-(writer*100))%10;
			sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);

		 
		

}

DataSet* AbstractClassifier::TrainExamplesForDigit(int TrainDigit,DataSet*  train_set){





	/*Training each digit*/
	for(int count = TrainDigit; count <= TrainDigit; count++)
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
			
	}
		return train_set;
}

void AbstractClassifier::InitClassifier(){
}

void AbstractClassifier::TrainFile(char* filename){
// set the test file then make train the classifier 

}

void AbstractClassifier::TestFile(char* filename){}

void AbstractClassifier::SetModelName(char* filename){}