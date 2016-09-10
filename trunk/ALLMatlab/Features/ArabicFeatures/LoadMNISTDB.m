function [Data,labels,Ssize]=LoadMNISTDB(  Database,  TrainTest)

if (Database==4)
    databpath=getADDataBasePath(3)
else 
Ssize=0;
databpath=getADDataBasePath(Database)
end 
%%5 get the count of the test and train sample to use
[trainCounts,testCount]= getMaxSampleCount()
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
if (Database==1||Database==2)
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
if (Database==3)
    
  DataBasePath=[databpath '\training_Set\TrainingData_Gray' ] ; 
    load (DataBasePath,'A');

Base=trainCounts/10;
end 

if (Database==4)
    
  DataBasePath=[databpath '\training_Set\TrainingData_Gray' ] ; 
    load (DataBasePath,'A');
    
      sizesBasePath=[databpath '\training_Set\TrainingData_Sizes' ] ; 
    load ( sizesBasePath,'Ssize');

Base=trainCounts/10;
end 

end 

%----------------------Case of testing------------------------------------------------
 if (TrainTest==1) 


if (Database==0)
Datapatch=[databpath '\MAHDBase_TestingSet_pathes' ];
load(Datapatch);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
DataBasePath=[databpath '\MAHDBase\']
end 
if (Database==1||Database==2)
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
if (Database==3)
    
  DataBasePath=[databpath '\Testing_Set\TestingData_Gray' ] ; 
  load (DataBasePath,'A');
  Base=testCount/10;
end 
if (Database==4)
    
  DataBasePath=[databpath '\Testing_Set\TestingData_Gray' ] ; 
  load (DataBasePath,'A');
  sizesBasePath=[databpath '\Testing_Set\TestingData_Sizes' ] ; 
    load ( sizesBasePath,'Ssize');
  Base=testCount/10;
end 

%TestingData_Sizes.mat|
 end 
%----------------------------------------------------------------------
if (Database==1||Database==2)
Data=A';

labels=B(9:end);
end 
if (Database==3||Database==4)
    Data=A;
    labels=Base;
end 