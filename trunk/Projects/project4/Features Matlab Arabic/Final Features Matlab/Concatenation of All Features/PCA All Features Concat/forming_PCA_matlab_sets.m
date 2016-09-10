clear
clc


full_training_set= zeros(60e3,945);
load all_concat_training_set

mu= mean(training_set,1);
training_set= training_set - repmat(mu,60e3,1);
save mu mu

N=50;
disp('applying PCA...')
[PCcoeff, PCvec] = pca(training_set,N);
save PCvec PCvec


PCA_training_set= training_set*PCvec;


save PCA_all_concat_training_set PCA_training_set


clear
N=50;

load all_concat_testing_set testing_set


load mu mu
load PCvec PCvec

testing_set= (testing_set-repmat(mu,10e3,1)); %./repmat(vars,10e3,1);
PCA_testing_set= (testing_set*PCvec);


save PCA_all_concat_testing_set PCA_testing_set



