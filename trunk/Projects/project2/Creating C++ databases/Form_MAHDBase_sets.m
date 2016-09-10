
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
%%%now for training 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
clc

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
MAHD=[databpath '\MAHDBase_TrainingSet_pathes' ];
load(MAHD);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
MAHDBase=[databpath '\MAHDBase\']
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');
%load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');

fid= fopen('mahdbase_training_set.txt','wt');
fprintf(fid,'%d %d \n',trainCounts,28*28+1);
%fprintf(fid,'%d %d \n',60e3,28*28+1);
format=[repmat('%d ',1,28*28+1) '\n'];

for n=1:trainCounts
%for n=1:60e3
    if(mod(n,100)==0)
        n
    end

   % path=[ 'C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    path=[MAHDBase MAHDBase_TrainingSet_pathes{n}];
    I=(255-double(imread(path)))/255;
    % the data set is the image it self. 
    x=[I(:)' training_set_labels(n)];
    % save the image + train set 
    fprintf(fid,format,x');
    
end


fclose(fid);
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
%%%now for test set. 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear
clc
[trainCounts,testCount]=getMaxSampleCount();
databpath=getADDataBasePath();

MAHD_Test=[databpath '\MAHDBase_TestingSet_pathes' ];
load(MAHD_Test);
testLabels=[databpath '\testing_set_labels'];
MAHDBase=[databpath '\MAHDBase\'];
load(testLabels);
%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TestingSet_pathes');
%load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');

fid= fopen('mahdbase_testing_set.txt','wt');
fprintf(fid,'%d %d \n',testCount,28*28+1);
%fprintf(fid,'%d %d \n',10e3,28*28+1);

format=[repmat('%d ',1,28*28+1) '\n'];
for n=1:testCount

%for n=1:10e3
    if(mod(n,100)==0)
        n
    end

 %   path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TestingSet_pathes{n}];
 path=[MAHDBase  MAHDBase_TestingSet_pathes{n}];
    I=(255-double(imread(path)))/255;
    
    x=[I(:)' testing_set_labels(n)];
    
    fprintf(fid,format,x');
    
end

fclose(fid);

