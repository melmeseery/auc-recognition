clear
clc

load('D:\WORK\AUC Research\Databases\Arabic Digits Databases\MAHDBase_TrainingSet_pathes');
load('D:\WORK\AUC Research\Databases\Arabic Digits Databases\training_set_labels');
training_labels=training_set_labels;



arabic_interclass_distance_measurements=[];

means= cell(10,1);
for i=1:10
    means{i}=zeros(28,28);
end


count=zeros(10,1);
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    path=['D:\WORK\AUC Research\Databases\Arabic Digits Databases\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;
    d= training_labels(n);

    count(d+1)=count(d+1)+1;

    means{d+1}= means{d+1}+I;


    if(mod(n,10e3)==0)

        for i=0:9
            means{i+1}=means{i+1}/count(i+1);
        end

        diffs=cell(10,10);
        euclidean_interclass_dist=zeros(10,10);
        for a=0:9
            for b=0:9
                diffs{a+1,b+1}= means{a+1}-means{b+1};
                euclidean_interclass_dist(a+1,b+1)=sqrt(sum(sum(diffs{a+1,b+1}.^2)));
            end
        end

        arabic_interclass_distance_measurements=[arabic_interclass_distance_measurements, sum(sum(euclidean_interclass_dist))];

        for i=1:10
            means{i}=zeros(28,28);
        end

        count=zeros(10,1);

    end

end


save arabic_interclass_distance_measurements arabic_interclass_distance_measurements








