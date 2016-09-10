clear
clc

load ('C:\Documents and Settings\ezzat\Desktop\AHDBase_TestingSet_pathes');
load testing_labels

testing_set=[];

load avg_bbox_area 

% forming testing set 
for n=1:10000
    if(mod(n,100)==0)
        n
    end
    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TestingSet_pathes{n}];
    I=~imread(path);

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Feature Extraction %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    [width height]=size(I);
    
    x= [FixedSizeProfile_2(I,10,10); width*height/avg_bbox_area];
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
%     updating testing set
    testing_set=[testing_set x];          
end

save testing_set testing_set 

load testing_set 

confusion_matrix=zeros(10,10);
load net 
%feedforwarding the neural network with testing set and getting results
for n=1:10000
    x= testing_set(:,n);
    d=testing_labels(n);
    
    decision_scores = mlpfwd(net, x');
    [v index_of_max]=max(decision_scores);
    decision_scores(index_of_max)=0;
    [v index_of_second_max]=max(decision_scores);
    
    confusion_matrix(d+1,index_of_max)= confusion_matrix(d+1,index_of_max)+1;   
end

confusion_matrix
accuracy= sum(sum(confusion_matrix.*eye(10,10)))/sum(sum(confusion_matrix))


save confusion_matrix confusion_matrix 
save accuracy accuracy


