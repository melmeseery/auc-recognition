clear
clc

load ('C:\Documents and Settings\ezzat\Desktop\AHDBase_TrainingSet_pathes'); 

load training_labels

training_set=[];

load avg_bbox_area 

%forming training set and labels
for n=1:60000
    if(mod(n,100)==0)
        n
    end

    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TrainingSet_pathes{n}];
    I=~imread(path);

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Feature Extraction %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    [width height]=size(I);
    
    x= [FixedSizeProfile_2(I,10,10); width*height/avg_bbox_area];
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    %updating training set
    training_set=[training_set x]; 
end

save training_set training_set

load training_set

%creating and training the neural network
disp('training the neural network ...' );
net = mlp(21,50,10,'logistic');
[net, error] = mlptrain(net, training_set',training_labels', 500)

save net net

