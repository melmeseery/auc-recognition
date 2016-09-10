clear
clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
MAHDBase=[databpath '\MAHDBase\']
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

load([databpath  '\MAHDBase_TrainingSet_pathes']);
load([databpath  '\MAHDBase_TestingSet_pathes']);
load([databpath '\training_set_labels']);

training_set=zeros(28*28,trainCounts);
training_labels=zeros(10,trainCounts);


for n=1:trainCounts
    if(mod(n,100)==0)
        n
    end
    
    d=training_set_labels(n);

    path=[MAHDBase  MAHDBase_TrainingSet_pathes{n}];
    I=(255-double(imread(path)))/255;
    
    training_set(:,n)=I(:);
    
    label=zeros(10,1);
    label(d+1)=1;
    training_labels(:,n)=label;
end

save training_set training_set
save training_labels training_labels




testing_set=zeros(28*28,testCount);
for n=1:testCount
    if(mod(n,100)==0)
        n
    end
    

    path=[MAHDBase MAHDBase_TestingSet_pathes{n}];
    I=(255-double(imread(path)))/255;
    
    testing_set(:,n)=I(:);
    
end

save testing_set testing_set




