function [result,correct,FS_net]=trainTestNeuralNetwork(data,labels,test,testLabels,Net,State)
% 
 

lab =digitTobinary(labels);
 inputSize=size(data,2);
if (State<=1)
% 
 disp('training the neural network ...' );
 %nin, nhidden, nout
 if(isempty(Net)|State==0)
  FS_net = mlp(inputSize,80,10,'logistic');%'softmax');
 else 
     FS_net=Net;
 end 
 [FS_net, error] = mlptrain(FS_net, data, lab, 500);
 error
end
 testlab =digitTobinary(testLabels);
 
 decision_scores = mlpfwd(FS_net,  test);
 %size(decision_scores)
 
 [v_1 index_of_max]=max(decision_scores');
 
 %size(index_of_max)
 correct=(index_of_max==testLabels);
 result = sum(correct)/length(correct)
 
 accuracy=result*100
% 
% save('FS_net','FS_net');
% 
% end%creating and training the neural network

 
