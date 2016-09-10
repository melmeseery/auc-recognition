clear
clc

load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');
load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
training_labels=training_set_labels;

means= cell(10,1);
for i=1:10
    means{i}=zeros(28,28);
end


count=zeros(10,1);
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;
    d= training_labels(n);
    
    count(d+1)=count(d+1)+1;
    
    means{d+1}= means{d+1}+I;
    
end

for d=0:9
    means{d+1}= means{d+1}/count(d+1);
end

save means means

