clear
clc

load training_set training_set



% mu=sum(training_set,2)/60e3;
% save mu mu
% load mu mu
% for i=1:60e3
%     training_set(:,i)=training_set(:,i)- mu;
% end
% 
% 
% disp('applying PCA...')
% [PCcoeff, PCvec] = pca(training_set(:,1:10e3)',40);
% save PCvec PCvec
[trainCounts,testCount]=getMaxSampleCount();
load mu mu
load PCvec PCvec

new_training_set= PCvec'*training_set;

save new_training_set new_training_set


QPCA_training_set=zeros(820,trainCounts);

start=1;
for i=1:40
    i
    A=new_training_set(1:end-i+1,:).*new_training_set(i:end,:);
    QPCA_training_set(start:start+40-i,:)=A;
    start=(start+40-i)+1;
end

save QPCA_training_set QPCA_training_set


clear

load testing_set testing_set


load mu mu
load PCvec PCvec

new_testing_set= PCvec'*testing_set;

save new_testing_set new_testing_set


QPCA_testing_set=zeros(820,testCount);

start=1;
for i=1:40
    i
    A=new_testing_set(1:end-i+1,:).*new_testing_set(i:end,:);
    QPCA_testing_set(start:start+40-i,:)=A;
    start=(start+40-i)+1;
end

save QPCA_testing_set QPCA_testing_set



