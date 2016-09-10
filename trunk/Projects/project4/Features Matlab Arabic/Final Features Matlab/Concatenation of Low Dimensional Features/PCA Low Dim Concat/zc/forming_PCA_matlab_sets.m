clear
clc

load arabic_low_dim_concat_training_set training_set

set= training_set;

load arabic_bbox_area_training_set training_set
training_set=training_set.^(0.5);
m=mean(training_set);
v=sqrt(var(training_set));
save m m
save v v
training_set=(training_set-m)/v;

training_set=[set training_set];


mu= mean(training_set,1);
for k=1:6
    k
    training_set((k-1)*10e3+1:k*10e3,:)= training_set((k-1)*10e3+1:k*10e3,:) - repmat(mu,10e3,1);
end
save mu mu

N=50;
disp('applying PCA...')
[PCcoeff, PCvec] = pca(training_set,N);
save PCvec PCvec


PCA_training_set= training_set*PCvec;


save zc_arabic_PCA_low_dim_concat_training_set PCA_training_set



clear
N=50;

load arabic_low_dim_concat_testing_set testing_set
set=testing_set;

load mu mu
load PCvec PCvec

load arabic_bbox_area_testing_set testing_set
testing_set=testing_set.^(0.5);
load m m
load v v
testing_set=(testing_set-m)/v;

testing_set=[set testing_set];

testing_set= (testing_set-repmat(mu,10e3,1)); %./repmat(vars,10e3,1);
PCA_testing_set= (testing_set*PCvec);


save zc_arabic_PCA_low_dim_concat_testing_set PCA_testing_set



