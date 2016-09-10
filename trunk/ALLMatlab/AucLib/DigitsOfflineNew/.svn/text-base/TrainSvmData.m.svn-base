function svmStruct=TrainSvmData(dataTrains,groups,OVO1)
% clear all
% clc
  
%   
%  [data,labels]=readImages(1,1);
%   save 'Images_data_Latin_Validation' 
  %load 'Images_Data_Latin_Validation' 
%save 'Images_Data_Arabic_validation'   
 %save 'Images_Data_Arabic'
 %  load 'Images_Data_Arabic_validation'  
 
 
 
% load 'Images_Data_Arabic' 
%   [dataTrains, groups] = getData(data,labels);
%     save dataForSVM dataTrains  groups
%    clear
%   disp(' Now Starting Training -------------------------------------------------');
%     load dataForSVM 
%     
%     load filename
%     
%     if (all)
    
   % OVO1=1;
%linear
%Parameters(1)= 1;%using RBF kernel
%Parameters(3)= 0.0078;%setting Gamma
%Parameters(5)= 152.2185;%setting C
% clear all 
% load  dataForSVM
% disp('Training the SVM ... ')
%%%%%%MATLAB SVM
opts = svmsmoset('Display','final','MaxIter',900000,'KernelCacheLimit',5000);

if (OVO1==1)
  %OVo  for ovo train do the following... 
  for i=1:10
      for j=i+1:10
          
          d1=i
          d2=j
         ind1=find(groups==d1);
         ind2=find(groups==d2);
         
         dataForOVO=[dataTrains(ind1,:); dataTrains(ind2,:)];
         g1=groups(ind1);
         g1(:)=1;
         g2=groups(ind2);
         g2(:)=-1;
         
         LabelsForOVO=[g1'; g2'];
         datlabel=size(dataForOVO)
         labelovo=size(LabelsForOVO)
         svmStruct(i,j)= svmtrain(dataForOVO,LabelsForOVO,'Method','SMO','SMO_Opts',opts);
         %model = svmtrain(heart_scale_label, heart_scale_inst, '-c 1 -g 0.07');
         % svmStruct(i,j) = svmtrainlib(LabelsForOVO,dataForOVO, '-c 1 -g
         % 0.10');
      end 
  end
elseif (OVO1==2)
  %OVo  for ovo train do the following... 
  for i=1:10
      for j=i+1:10
          
          d1=i
          d2=j
         ind1=find(groups==d1);
         ind2=find(groups==d2);
         
         dataForOVO=[dataTrains(ind1,:); dataTrains(ind2,:)];
         g1=groups(ind1);
         g1(:)=1;
         g2=groups(ind2);
         g2(:)=-1;
         
         LabelsForOVO=[g1'; g2'];
         %size(dataForOVO)
         %size(LabelsForOVO)
     %    svmStruct(i,j)= svmtrain(dataForOVO,LabelsForOVO,'Method','SMO','SMO_Opts',opts);
         %model = svmtrain(heart_scale_label, heart_scale_inst, '-c 1 -g 0.07');
          svmStruct(i,j) = svmtrainlib(LabelsForOVO,dataForOVO, '-c 1 -g 0.10');
         
      end 
  end
elseif (OVO1==3)
  %OVo  for ovo train do the following... 
  for i=1:10
      for j=i+1:10
          
          d1=i
          d2=j
         ind1=find(groups==d1);
         ind2=find(groups==d2);
         
         dataForOVO=[dataTrains(ind1,:); dataTrains(ind2,:)];
         g1=groups(ind1);
         g1(:)=1;
         g2=groups(ind2);
         g2(:)=-1;
         
         LabelsForOVO=[g1'; g2'];
         %size(dataForOVO)
         %size(LabelsForOVO)
     %    svmStruct(i,j)= svmtrain(dataForOVO,LabelsForOVO,'Method','SMO','SMO_Opts',opts);
         %model = svmtrain(heart_scale_label, heart_scale_inst, '-c 1 -g 0.07');
          %svmStruct(i,j) = svmtrainlib(LabelsForOVO,dataForOVO, '-c 1 -g 0.10');
          svmStruct(i,j)=train(LabelsForOVO,sparse(dataForOVO), '-s 4  -c 1.0 -e 0.01 -B 1.0 ');
         
      end 
  end
elseif (OVO1==4)
     
    svmStruct = svmtrainlib(groups',dataTrains, '-c 100 -g 0.1  -m 250 -q  ');%%  -m 250  -c -g  -t 0 (linear)
     
    elseif (OVO1==5)
       svmStruct=train(groups',sparse(dataTrains), '-s 4  -c 1.0 -e 0.01 -B 1.0 ');
       
elseif (OVO1==0)    %OVA.... 
    
  for i=1:10
          d1=i
         dataForOVA=dataTrains;
         
         LabelsForOVA=(groups==d1); % (0,VS 1)
         
         LabelsForOVA(LabelsForOVA==0)=-1;
         LabelsForOVA(LabelsForOVA==1)=d1;
       %  svmStruct(i)= svmtrain(dataForOVA,LabelsForOVA,'Method','SMO','SMO_Opts',opts);
          svmStruct(i) = svmtrainlib(LabelsForOVA,dataForOVA, '-c 1 -g 0.07');

  end
  
  
    
    
end 

  

