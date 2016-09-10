clear
clc

load ('C:\Documents and Settings\ezzat\Desktop\AHDBase_TrainingSet_pathes') 

load training_labels

training_set=[];


%forming training set and labels
for n=1:60000
    if(mod(n,100)==0)
        n
    end

    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TrainingSet_pathes{n}];
    I=~imread(path);

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Feature Extraction %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    [height width]= size(I);
    Iup=I(1:floor(height/2),:);
    P= regionprops(double(Iup),'Image');
    Iup=P.Image;
    
    x= [NumOfPxlsFE(I,5,5); NumOfPxlsFE(Iup,5,5)];
    
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    %updating training set
    training_set=[training_set x]; 
end

save training_set training_set

load training_set

%creating and training the neural network
disp('training the neural network ...' );
net = mlp(50,100,10,'logistic');
[net, error] = mlptrain(net, training_set',training_labels', 500)

save net net


