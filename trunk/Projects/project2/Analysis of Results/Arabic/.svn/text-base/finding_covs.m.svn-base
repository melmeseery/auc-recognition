clear
clc

load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');
load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
training_labels=training_set_labels;

load means means

covs=cell(10,1);
for d=0:9
    covs{d+1}=zeros(28,28);
end

count=zeros(10,1);
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;
    d= training_labels(n);
    
    count(d+1)= count(d+1)+1;
    
    covs{d+1}= covs{d+1} + (means{d+1}-I).^2;
    
end

save covs covs

covs_sums=zeros(10,1);
for d=0:9
    covs{d+1}=covs{d+1}/count(d+1);
    covs_sums(d+1)=sum(sum(covs{d+1}));
end

arabic_covs_sums=covs_sums;
save arabic_covs_sums arabic_covs_sums

sum(covs_sums)



