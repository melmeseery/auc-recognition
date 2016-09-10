clc
clear

%%%%%%%%%%%%%%%%%%%%%%%
%get paths 
databpath=getADDataBasePath(1);
[trainCounts,testCount]=getMaxSampleCount();

Minist=[databpath '\train-images.idx3-ubyte' ]
trainLabels=[databpath '\train-labels.idx1-ubyte'];
Minist_test=[databpath '\t10k-images.idx3-ubyte' ]
testLabels=[databpath '\t10k-labels.idx1-ubyte'];

%%%%%%%%%%%%%%%%%%%%%%%%


fid=fopen(Minist,'r');
B=fread(fid);
training_set_labels=B(9:end);

load PCA_training_set PCA_training_set


fid_train= fopen('mnist_pca_training_set.txt','wt');
fprintf(fid_train,'%d %d \n',trainCounts,40+1);
format=[repmat('%d ',1,40+1) '\n'];
fprintf(fid_train,format,[PCA_training_set; training_set_labels']);

fclose(fid);
fclose(fid_train);



fid=fopen(Minist_test,'r');
B=fread(fid);
testing_set_labels=B(9:end);

load PCA_testing_set PCA_testing_set

fid_test= fopen('mnist_pca_testing_set.txt','wt');
fprintf(fid_test,'%d %d \n',testCount,40+1);
format=[repmat('%d ',1,40+1) '\n'];
fprintf(fid_test,format,[PCA_testing_set; testing_set_labels']);

fclose(fid);
fclose(fid_test);
