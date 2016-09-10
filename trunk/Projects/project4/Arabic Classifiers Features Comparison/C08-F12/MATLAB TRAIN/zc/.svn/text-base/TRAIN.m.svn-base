clear
clc

load training_labels training_labels


load arabic_low_dim_concat_training_set
set=training_set;
load arabic_bbox_area_training_set training_set
m= mean(training_set);
v= sqrt(var(training_set));
save m m
save v v
training_set= (training_set-m)/v;
training_set=[set training_set];


mu = mean(training_set,1);
save mu mu
for k=1:6
   k
   training_set((k-1)*10e3+1:k*10e3,:)=training_set((k-1)*10e3+1:k*10e3,:)-repmat(mu,10e3,1);
end

P=141;
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
