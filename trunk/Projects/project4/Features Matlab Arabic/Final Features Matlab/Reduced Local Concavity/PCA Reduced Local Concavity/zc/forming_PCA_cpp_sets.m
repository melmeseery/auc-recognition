clc
clear

load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');

load zc_arabic_PCA_reduced_local_concavity_training_set PCA_training_set

N=50;

fid_train= fopen('zc_arabic_pca_reduced_local_concavity_ourfeatures_latin_training_set_full.txt','wt');
fprintf(fid_train,'%d %d \n',60e3,N+1);
format=[repmat('%d ',1,N+1) '\n'];
for n=1:60e3
    if(mod(n,1000)==0)
        n
    end
    fprintf(fid_train,format,[PCA_training_set(n,:)'; training_set_labels(n)']);  
end

fclose(fid_train);

clear
N=50;

load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');


load zc_arabic_PCA_reduced_local_concavity_testing_set PCA_testing_set

fid_test= fopen('zc_arabic_pca_reduced_local_concavity_ourfeatures_latin_testing_set_full.txt','wt');
fprintf(fid_test,'%d %d \n',10e3,N+1);
format=[repmat('%d ',1,N+1) '\n'];
fprintf(fid_test,format,[PCA_testing_set'; testing_set_labels']);

fclose(fid_test);

