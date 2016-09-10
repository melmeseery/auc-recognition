clear
clc


databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
load([databpath '\training_set_labels']);


%load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
load training_set training_set

%mu=sum(training_set,2)/60e3;


mu=sum(training_set,2)/trainCounts;
save mu mu
load mu mu
for i=1:trainCounts
    training_set(:,i)=training_set(:,i)- mu;
end

%
disp('applying PCA...')
[PCcoeff, PCvec] = pca(training_set(:,1:testCount)',40);%in net lib library  (add it to the matlab directory )
save PCvec PCvec

load mu mu
load PCvec PCvec

PCA_training_set= PCvec'*training_set;

save PCA_training_set PCA_training_set


load([databpath '\testing_set_labels']);

%load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');
load testing_set testing_set



load mu mu
load PCvec PCvec

for i=1:testCount
    testing_set(:,i)=testing_set(:,i)- mu;
end

PCA_testing_set= PCvec'*testing_set;

save PCA_testing_set PCA_testing_set



