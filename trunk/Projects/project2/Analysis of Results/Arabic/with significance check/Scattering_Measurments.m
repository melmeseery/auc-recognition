
clear
clc

load('D:\WORK\AUC Research\Databases\Arabic Digits Databases\MAHDBase_TrainingSet_pathes');
load('D:\WORK\AUC Research\Databases\Arabic Digits Databases\training_set_labels');
training_labels=training_set_labels;



means= cell(6,10);
for k=1:6
    for i=1:10
        means{k,i}=zeros(28,28);
    end
end

count=zeros(10,1);
k=1;
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    path=['D:\WORK\AUC Research\Databases\Arabic Digits Databases\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;
    d= training_labels(n);

    
    count(d+1)=count(d+1)+1;
    means{k,d+1}= means{k,d+1}+I;

    if(mod(n,10e3)==0)
        
        for i=0:9
            means{k,i+1}=means{k,i+1}/count(i+1);
        end             
        k=k+1;
        count=zeros(10,1);
   end

end


covs=cell(10,1);
for d=0:9
    covs{d+1}=zeros(28,28);
end

arabic_scattering_measurements=[];

count=zeros(10,1);
k=1;
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    path=['D:\WORK\AUC Research\Databases\Arabic Digits Databases\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;
    d= training_labels(n);

    count(d+1)=count(d+1)+1;
    covs{d+1}= covs{d+1} + (means{k,d+1}-I).^2;

    if(mod(n,10e3)==0)
        
        covs_sum=zeros(size(covs{1}));
        for i=0:9
            covs{i+1}=covs{i+1}/count(i+1);
            covs_sum=covs_sum+covs{i+1};
        end
        covs_sum=sum(sum(covs_sum))/10;
        
        arabic_scattering_measurements=[arabic_scattering_measurements, covs_sum];
        
        k=k+1;
        
        for i=1:10
            covs{i}=zeros(28,28);
        end

        count=zeros(10,1);

   end

end


save arabic_scattering_measurements arabic_scattering_measurements










