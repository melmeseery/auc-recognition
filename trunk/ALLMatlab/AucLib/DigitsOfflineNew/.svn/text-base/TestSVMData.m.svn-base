function [result,correct,sampleSize,conf,predict_label, accuracy, dec_values,groups]=TestSVMData (dataTest,groups,svmStruct,OVO1)
CatSize=10;
  CorrectLabel=groups;
   conf=zeros( CatSize, CatSize);
     if (OVO1==1)
      [predict_label, accuracy, dec_values,conf]=OVOTestMatlab(groups, dataTest, svmStruct, CatSize);
     % [predict_label, accuracy, dec_values] = svmpredict(groups, dataTest, model);
      correct=sum(predict_label==groups); 
     elseif (OVO1==2)
      [predict_label, accuracy, dec_values,conf]=OVOTest(groups, dataTest, svmStruct, CatSize);
     % [predict_label, accuracy, dec_values] = svmpredict(groups, dataTest, model);
      correct=sum(predict_label==groups'); 
      
  elseif (OVO1==3)
       [predict_label, accuracy, dec_values,conf]=OVOTestLibLinear(groups, dataTest, svmStruct, CatSize);
            correct=sum(predict_label==groups); 
  elseif (OVO1==4)
            [predict_label, result, dec_value] = svmpredict(groups', dataTest, svmStruct);
              correct=sum(predict_label==groups'); 
elseif (OVO1==5) 
           [predict_label, result, dec_value] = predict(groups',sparse(dataTest), svmStruct);
               correct=sum(predict_label==groups'); 
  else (OVO1==0)%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%OVAAAAA
       classes=zeros(1,length(dataTest));
       correct=0;
       error=0;
       errorC=0;
       conf=zeros( CatSize, CatSize);
       for s=1:length(dataTest)
        if(mod(s,1000)==0) 
          s
          correct
            errorC
             errorC=0;
          result=(correct/s)*100
      end 
           vote=zeros(1, CatSize);
        for i=1: CatSize
              labelData = svmclassify(svmStruct(i),dataTest(s,:));
            %[labelData, accur, dec_values] = svmpredict(j, dataTest(s,:), svmStruct(i));
              %labelData= svmclassify(svmStruct(i),dataTest);
              %classes=[ classes labelData ];
               if(labelData>0)
                   vote(i)=i;
               end 
        end 
        
            ind=find(vote);
            mi=vote(ind);
              classes(s)= mi(1);
            %%% need to check how to classify 
            if (classes(s)==groups(s))
                correct=correct+1;
            else 
                error =error+1;
                       errorC=errorC+1;
                conf(classes(s),groups(s))= conf(classes(s),groups(s))+1;
            end 
            
  
       end  %%% for everky sample 
      
  end
  
  
  
%  correct=0;
%  error=0;
%  correctTwoStage=0;
% for i=1:length(classes)
%     v=classes(i,:);
%      temp=zeros(1,9);
%     for i=0:9
%         
%         temp(i)=length(find(v==i))
%     end
%     maxi=max(temp);
%     
%     if (maxi==labels(i))
%         correct=correct+1;
%     else 
%      %   writeErrorFile(DataStrokes{i}, i,labels(i),classes(i),
%      %   CorrectLabel(i));
%       %  imshow()
%       error=error+1;
%     end
%  
% end 
% disp(' Result is ........ .....  ');
% correct
 sampleSize=length(dataTest );
 result=(correct/length(dataTest ))*100;
% eeeerrors=length( dataTest )-correct;