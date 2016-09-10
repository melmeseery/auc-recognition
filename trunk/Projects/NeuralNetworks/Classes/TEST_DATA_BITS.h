
#include "MatDataSet.h"
#include "ClassFormatDataSet.h"
#include "ClassNLLCriterion.h"
#include "MSECriterion.h"
#include "OneHotClassFormat.h"
#include "MultiClassFormat.h"
#include "ClassMeasurer.h"
#include "MSEMeasurer.h"

#include "StochasticGradient.h"
#include "KFold.h"

#include "ConnectedMachine.h"
#include "Linear.h"
#include "Tanh.h"
#include "LogSoftMax.h"

#include "MeanVarNorm.h"
#include "DiskXFile.h"
#include "MemoryXFile.h"
#include "CmdLine.h"
#include "Random.h"
#include "Math.h"
#include "Timer.h"
#include<iostream>
#include<fstream>
#include<vector>
using namespace std;

using namespace Torch;

void getMax(real* arr, long n_arr, real &max_value, long &max_index)
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
int size ;

string* myStrings;
void getFiles(char* filename){



	ifstream inFile;
    
	//filename="files_names.txt";
    inFile.open(filename);
    if (!inFile) {
        cout << "Unable to open file";
        exit(1); // terminate with error
    }
    char x[500];
	

	//int size;
	int i=0;
	if (inFile>>size){
		cout<<"Size of file "<<size<<endl;
		myStrings=new string[size];
		
		while (inFile >> x) {

			myStrings[i]=x;
          if (i%400==0)
			cout<<" reading "<<myStrings[i].data()<<endl;
			i++;
		}
	}
    inFile.close();
		cout<<"Size of file "<<size<<endl;
}


string getFile(int t ){

	if (myStrings==NULL){
	
	// do the old stuff 
		char filename[50];
			int writer, pass, digit;

			writer = t/100;
			pass = (t-(writer*100))/10;
			digit = (t-(writer*100))%10;
			sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
			string s=filename;
			return s;
	
	}
	else 
	{
	 
		return myStrings[t];
	
	}


}
void TEST_DATA_BITS(char* test_file, char* model, int n_hu, char* filesn, int feat)
{
 
	if (filesn!=NULL)
	{
	getFiles(filesn);
}
	Allocator *allocator = new Allocator;
	int num_of_features = feat;
    int my_digit = 1;
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
	float pos,neg;
	pos=0;
	neg=0;
	accuracy=0;
	
	ofstream fout("test_stats_Neural.txt");
      ofstream fscore("Test_Neural_Scores.txt");
		  cout<<"Forwarding samples .......... ."<<endl;
		  	long fivesamples=0;

			fscore<<" ID "<<"   "<<"score"<<endl;
	/*Testing...*/
	for(int t = 0; t < test_data->n_examples; t++)
	{
		if((t%300) == 0)
			cout<<t<<endl;

		test_data->setExample(t);
		int d = test_data->targets->frames[0][0];//actual value of sample
		
        /*Get the score from the corresponding NN*/
		mlp[my_digit].forward(test_data->inputs);
		real score = mlp[my_digit].outputs->frames[0][0];
		fscore<<t<<"   "<<score<<endl;

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
				/*char filename[50];
				int writer, pass, digit;

				writer = t/100;// the t 600 * 10 * 10 = writer 5670 /100 = 56  
				pass = (t-(writer*100))/10;
				digit = (t-(writer*100))%10;
				sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
			//	fout<<"### "<<t<<" "<<filename<<"; score = "<<score<<endl;*/
              pos++;
               

				//	cout<<"### "<<t<<" "<<	getFile(t).data()<<"; score = "<<score<<endl;
				//	fout<<"### "<<t<<" "<<getFile(t).data()<<"; score = "<<score<<endl;
			}
        }
		else if(d == my_digit)
		{
			/*char filename[50];
			int writer, pass, digit;

			writer = t/100;
			pass = (t-(writer*100))/10;
			digit = (t-(writer*100))%10;
			sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1 + pass, digit);
			fo
			ut<<"--- "<<t<<" "<<filename<<"; score = ["<<score<<"]"<<endl;*/

			neg++;
			//	cout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = ["<< score<<"]" <<endl;
			//	fout<<"--- "<<t<<" "<<	getFile(t).data()<<"; score = ["<< score<<"]"<<endl;

		}
	}

	fout<<endl<<endl;
	double samplerate=100.0/(double)fivesamples;
	fout<<" total number of samples "<<test_data->n_examples<<endl;//<<"Count of digit 1 samples "<<fivesamples<<endl;
	fout<<"  Samples with result > 0:  "<<positivesamples<<endl;
    fout<<"  The false +ve count  = "<<pos<<"  and the false -ve count  = "<<neg<<endl;
    fout<<"  Samples with the corresponding digit and result > 0: "<<accuracy<<"   sample and "<< accuracy*samplerate<<" % "<<endl;

	
	delete(allocator);
}
