function [result,correct,sizeOfTest]=ClassifierFusionTraining(type,train_perdict,train_dec,train_labels,test_predict,test_dec,test_labels)

if (type<100)

 [result,correct,sizeOfTest]=ClassifierFusion(test_dec,test_predict,test_labels,type);
elseif(type>100)
    
    %use the neural network for train and test... 
    
    
    %[result,correct]=trainTestNeuralNetwork(train_perdict,train_labels,test_predict,test_labels);
    sizeOfTest=length(test_labels);
   [result,correct]=trainTestNeuralNetwork(train_dec,train_labels,test_dec,test_labels);
    
  %  [result,correct]=trainTestNeuralNetwork([train_perdict train_dec],train_labels,[ test_predict test_dec],test_labels);
    
end 