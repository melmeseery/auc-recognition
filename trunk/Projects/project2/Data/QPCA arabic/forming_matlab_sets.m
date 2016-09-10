clear
clc


load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');
load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TestingSet_pathes');
load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');

training_set=zeros(28*28,60e3);
training_labels=zeros(10,60e3);


for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    
    d=training_set_labels(n);

    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=(255-double(imread(path)))/255;
    
    training_set(:,n)=I(:);
    
    label=zeros(10,1);
    label(d+1)=1;
    training_labels(:,n)=label;
end

save training_set training_set
save training_labels training_labels




testing_set=zeros(28*28,10e3);
for n=1:10e3
    if(mod(n,100)==0)
        n
    end
    

    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TestingSet_pathes{n}];
    I=(255-double(imread(path)))/255;
    
    testing_set(:,n)=I(:);
    
end

save testing_set testing_set




