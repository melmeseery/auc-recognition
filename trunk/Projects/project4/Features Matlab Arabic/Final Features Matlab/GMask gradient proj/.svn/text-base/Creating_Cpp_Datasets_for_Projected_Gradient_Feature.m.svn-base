clear
clc

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount();

trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

fid= fopen('arabic_gmask_gradproj_ourfeatures_training_set_full.txt','wt');
fprintf(fid,'%d %d \n',trainCounts,200+1);

%load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');

format=[repmat('%d ',1,200+1) '\n'];


load arabic_gmask_grad_proj_training_set training_set
training_set= training_set.^(0.5);
%%%%%%%%%%%%%%%%%%%%%%%%%  i have changed this 
training_set_labels_small=training_set_labels(1:trainCounts);


fprintf(fid,format,[training_set training_set_labels_small]');
fclose(fid);


clear
clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
%
databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
%MAHD=[databpath '\MAHDBase_TrainingSet_pathes' ];
%load(MAHD);
testLabels=[databpath '\testing_set_labels'];
load(testLabels);
%testLabels=[databpath '\MAHDBase_TestingSet_pathes'];

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
fid= fopen('arabic_gmask_gradproj_ourfeatures_testing_set_full.txt','wt');
fprintf(fid,'%d %d \n',testCount,200+1);

%load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');


format=[repmat('%d ',1,200+1) '\n'];
load arabic_gmask_grad_proj_testing_set testing_set

testing_set= testing_set.^(0.5);
testing_set_labels_small=testing_set_labels(1:testCount);
fprintf(fid,format,[testing_set testing_set_labels_small]');

fclose(fid);

