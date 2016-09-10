clear 
clc

dataset=1;
    if (dataset==1)
load  trainSet
training_set=trainset;
load trainlabels
 training_set_labels=train_labels;
 
FeatName= 'Feat_all';
load  testSet
testing_set=test_set;
load testlabels
 testing_set_labels=test_labels;

    else
    
FeatName= 'kirsh4';
load latin_kirsh4_training_set training_set
    
load latin_kirsh4_testing_set testing_set
    end 





mode=0;


PCAsets(training_set,testing_set,FeatName,mode );

 PCA_GenerateTxtSet(FeatName ,mode)
 
 % PCA_GenerateTxtSet(FeatName ,mode)
  
  PCAMatrixAndMU(FeatName)