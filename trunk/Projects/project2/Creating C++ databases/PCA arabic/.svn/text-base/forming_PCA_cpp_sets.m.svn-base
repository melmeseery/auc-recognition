clc
clear



databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
%load('training_set_labels')

load([databpath '\training_set_labels']);
%load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
load PCA_training_set PCA_training_set


fid_train= fopen('mahdbase_pca_training_set.txt','wt');
%fprintf(fid_train,'%d %d \n',trainCounts,40+1);

fprintf(fid_train,'%d %d \n',trainCounts,40+1);
format=[repmat('%d ',1,40+1) '\n'];
fprintf(fid_train,format,[PCA_training_set; training_set_labels']);

fclose(fid_train);

load([databpath '\testing_set_labels']);
%load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');
load PCA_testing_set PCA_testing_set

fid_test= fopen('mahdbase_pca_testing_set.txt','wt');
fprintf(fid_test,'%d %d \n',testCount,40+1);
format=[repmat('%d ',1,40+1) '\n'];
fprintf(fid_test,format,[PCA_testing_set; testing_set_labels']);

fclose(fid_test);
