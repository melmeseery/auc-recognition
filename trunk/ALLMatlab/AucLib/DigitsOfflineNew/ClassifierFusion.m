function [result,correct,sizeOfTest]=ClassifierFusion(DecisionValues,FinalResult,ActualLabels,type)



clc 
 correct=0; 
 error=0;
 sizeOfTest=length(ActualLabels);
 %DecisionValues(DecisionValues==Inf)=-Inf; 
 for s=1:length(ActualLabels)
     
     [v c]=min(DecisionValues(s,:));
     
     label=FinalResult(s,c);
     if (label==ActualLabels(s))
         correct=correct+1; 
     else 
         ActualLabels(s)
          ds=DecisionValues(s,:)
          f=FinalResult(s,:)
          l=label
         error=error+1 
         
     end 
 end 
 
 result=correct/length(ActualLabels)

 
 
result 
correct 

end 