function [train,trainlabel,test,testlabel]=extractTrainValidation(data,labels,classes,startv,endv,sizeAll)
%clear all 
% load dataForSVM
% data=dataTrains; 
% labels=groups'; 
% classes=10;  
% startv=5000; 
% endv=6000;


% endall=6000; 
%    startall=1; 
train =[];
test=[];
 trainlabel=[]; 
 testlabel=[];
   indtrain=[];
   indtest=[];
 
  
  % if (endall==endv)
   
for d=1:classes
    s=(d-1)*sizeAll; 
     tt= startv+1:endv ;
   
      all=1:sizeAll;
      all(tt)=[];
      indtest=[indtest   tt+s ];
      indtrain=[indtrain     all+s ];

end
 
   train=data( indtrain,:);
   trainlabel= labels(:,indtrain);
% 
  test= data( indtest,:) ; 
  testlabel=labels(:,indtest);


