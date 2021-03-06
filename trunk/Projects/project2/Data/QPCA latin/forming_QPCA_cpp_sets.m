clc
clear

fid=fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid);
training_set_labels= B(9:end);
load QPCA_training_set QPCA_training_set


fid_train= fopen('mnist_qpca_training_set.txt','wt');
fprintf(fid_train,'%d %d \n',60e3,820+1);
format=[repmat('%d ',1,820+1) '\n'];
fprintf(fid_train,format,[QPCA_training_set; training_set_labels']);

fclose(fid);
fclose(fid_train);

clear

fid=fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\t10k-labels.idx1-ubyte','r');
B=fread(fid);
testing_set_labels=B(9:end);
load QPCA_testing_set QPCA_testing_set

fid_test= fopen('mnist_qpca_testing_set.txt','wt');
fprintf(fid_test,'%d %d \n',10e3,820+1);
format=[repmat('%d ',1,820+1) '\n'];
fprintf(fid_test,format,[QPCA_testing_set; testing_set_labels']);

fclose(fid);
fclose(fid_test);
