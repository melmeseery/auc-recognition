clear
clc

load training_labels training_labels

load arabic_wavelet_raw_training_set
training_set(training_set<0)=0;
training_set=(training_set).^(0.5);

MU_ = cell(10,1);
SIGMA_ = cell(10,1);

for i=0:9
    set_= training_set(training_labels==i,:);
    MU_{i+1} = mean(set_,1);
    SIGMA_{i+1}= (set_ - repmat(MU_{i+1},size(set_,1),1))'*(set_ - repmat(MU_{i+1},size(set_,1),1))/size(set_,1);
end



save MU_ MU_
save SIGMA_ SIGMA_
