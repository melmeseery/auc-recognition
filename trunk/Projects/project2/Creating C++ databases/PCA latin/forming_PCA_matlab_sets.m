% clear
% clc
% 
% load training_set
% 
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
% 
% load mu mu
% load PCvec PCvec
% 
% PCA_training_set= PCvec'*training_set;
% 
% save PCA_training_set PCA_training_set


clear

[trainCounts,testCount]=getMaxSampleCount();
load testing_set

load mu mu
load PCvec PCvec

for i=1:testCount
    testing_set(:,i)=testing_set(:,i)- mu;
end

PCA_testing_set= PCvec'*testing_set;

save PCA_testing_set PCA_testing_set



