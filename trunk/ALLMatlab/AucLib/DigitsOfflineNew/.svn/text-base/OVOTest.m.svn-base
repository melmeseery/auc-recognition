function  [predict_label, accuracy, dec_values,conf]=OVOTest(groups, dataTest, svmStruct, CatSize)



 classes=zeros(1,length(dataTest));
       correct=0;
   
        conf=zeros( CatSize, CatSize);
          errorC=0;
          error=0;
       CCKK=CatSize-2; 
 for s=1:length(dataTest)
     
      if(mod(s,1000)==0) 
          s
          correct
               errorC
               errorC=0;
          result=(correct/s)*100
      end 
           vote=zeros(1, CatSize);
        %OVo  for ovo train do the following... 
        decvalue=zeros(CatSize,CatSize);
  for i=1: CatSize
      for j=i+1: CatSize
          
%             labelData = svmclassify(svmStruct(i,j),dataTest(s,:));
%             accur=1; 
%             dec=labelData; 
          %  [predict_label, accuracy, dec_values] = svmpredict(heart_scale_label, heart_scale_inst, model);
            [labelData, accur, dec] = svmpredict(groups(s), dataTest(s,:), svmStruct(i,j));
            decvalue(i,j)=dec; 
            if(labelData>0)
                vote(i)=    vote(i)+1;
            else
                 vote(j)=    vote(j)+1;
            end 
      end  
  end  
  
  
  %%% get the decsioion of each class seperate.... 
    for l=1: CatSize
         decValueClass(l)=0;
         sumtemp=0;
      for k=1: CatSize
          if (k==l)
              continue ; 
          end 
          if ( decvalue(l,k)~=0)
          % other wise 
          sumtemp=sumtemp + ((1/decvalue(l,k))- CCKK);
          end 
      end
        decValueClass(l)=1/sumtemp;
    end 
           [ma mi]=max(vote);
              classes(s)= mi;
              predict_label(s)=classes(s);
              
              dec_values(s)= decValueClass(predict_label(s)); 
            %%% need to check how to classify 
            if (classes(s)==groups(s))
                correct=correct+1;
            else 
                error =error+1;
                     errorC=errorC+1;
                conf(classes(s),groups(s))= conf(classes(s),groups(s))+1;
            end 
            
  
       end  %%% for everky sample 
       
       
        sampleSize=length(dataTest );
result=(correct/length(dataTest ))*100;
 accuracy=result
