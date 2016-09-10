clc
clear


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
load([databpath '\training_set_labels']);
%load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
load QPCA_training_set QPCA_training_set


fid_train= fopen('mahdbase_qpca_training_set.txt','wt');
fprintf(fid_train,'%d %d \n',trainCounts,820+1);
format=[repmat('%d ',1,820+1) '\n'];
fprintf(fid_train,format,[QPCA_training_set; training_set_labels']);



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
load([databpath '\testing_set_labels']);
%load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');
load QPCA_testing_set QPCA_testing_set

fid_test= fopen('mahdbase_qpca_testing_set.txt','wt');
fprintf(fid_test,'%d %d \n',testCount,820+1);
format=[repmat('%d ',1,820+1) '\n'];
fprintf(fid_test,format,[QPCA_testing_set; testing_set_labels']);


