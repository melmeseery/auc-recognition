clc
clear

fid=fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid);
training_set_labels= B(9:end);
load PCA_all_concat_training_set PCA_training_set

N=50;

fid_train= fopen('pca_all_concat_ourfeatures_latin_training_set_full.txt','wt');
fprintf(fid_train,'%d %d \n',60e3,N+1);
format=[repmat('%d ',1,N+1) '\n'];
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    fprintf(fid_train,format,[PCA_training_set(n,:)'; training_set_labels(n)']);  
end

fclose(fid);
fclose(fid_train);

clear
N=50;

fid=fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\t10k-labels.idx1-ubyte','r');
B=fread(fid);
testing_set_labels=B(9:end);
load PCA_all_concat_testing_set PCA_testing_set

fid_test= fopen('pca_all_concat_ourfeatures_latin_testing_set_full.txt','wt');
fprintf(fid_test,'%d %d \n',10e3,N+1);
format=[repmat('%d ',1,N+1) '\n'];
fprintf(fid_test,format,[PCA_testing_set'; testing_set_labels']);

fclose(fid);
fclose(fid_test);

