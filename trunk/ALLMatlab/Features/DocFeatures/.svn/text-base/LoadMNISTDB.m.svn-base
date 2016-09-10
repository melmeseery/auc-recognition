function [Data,labels]=LoadMNISTDB(  Database,  TrainTest)

databpath=getADDataBasePath(Database)
%%5 get the count of the test and train sample to use
[trainCounts,testCount]=getMaxSampleCount()
%%%
if ( TrainTest==0)
%%%
if (Database==0)
Datapatch=[databpath '\MAHDBase_TrainingSet_pathes' ];
load(Datapatch);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
DataBasePath=[databpath '\MAHDBase\' ];
end 
if (Database>=1)
    %Datapatch=[databpath '\MAHDBase_TrainingSet_pathes' ];
%load(Datapatch);
%trainLabels=[databpath '\training_set_labels'];
%load(trainLabels);
%DataBasePath=[databpath '\MAHDBase\']
DataBasePath=[databpath '\train-images.idx3-ubyte' ]

fid= fopen(DataBasePath,'r');
A=fread(fid);
trainLabels=[databpath '\train-labels.idx1-ubyte'];
%fid2=fopen('Z:\MNIST\train-labels.idx1-ubyte','r');
fid2=fopen(trainLabels ,'r');
B=fread(fid2);
end 
end 
 if (TrainTest==1) 


if (Database==0)
Datapatch=[databpath '\MAHDBase_TestingSet_pathes' ];
load(Datapatch);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
DataBasePath=[databpath '\MAHDBase\']
end 
if (Database>=1)
    %Datapatch=[databpath '\MAHDBase_TrainingSet_pathes' ];
%load(Datapatch);
%trainLabels=[databpath '\training_set_labels'];
%load(trainLabels);
%DataBasePath=[databpath '\MAHDBase\']
DataBasePath=[databpath '\t10k-images.idx3-ubyte' ]
testLabels=[databpath '\t10k-labels.idx1-ubyte'];

fid= fopen(DataBasePath,'r');
A=fread(fid);
%fid2=fopen('Z:\MNIST\train-labels.idx1-ubyte','r');
fid2=fopen(testLabels ,'r');
B=fread(fid2);

end 

end 

Data=A';

labels=B(9:end);