clear
clc

load training_labels training_labels

N=64;
full_training_set= zeros(60e3,N);

load arabic_wavelet_raw_training_set
training_set(training_set<0)=0;
training_set=(training_set).^(0.5);

mu = mean(training_set,1);
save mu mu
training_set=training_set-repmat(mu,60e3,1);

P=26;
disp('applying PCA...')
[PCcoeff, PCvec] = pca(training_set,P);
training_set=training_set*PCvec;
save PCvec PCvec

MU_ = cell(10,1);
SIGMA_ = cell(10,1);

for i=0:9
    set_= training_set(training_labels==i,:);
    MU_{i+1} = mean(set_,1);
    SIGMA_{i+1}= (set_ - repmat(MU_{i+1},size(set_,1),1))'*(set_ - repmat(MU_{i+1},size(set_,1),1))/size(set_,1);
end



save MU_ MU_
save SIGMA_ SIGMA_
