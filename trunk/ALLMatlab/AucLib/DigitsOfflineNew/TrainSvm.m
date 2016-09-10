function svmStruct=TrainSvm()

% clear all
% clc
%  [data,labels]=readImages(1,1);
%   save 'Images_data_Latin_Validation' 
  %load 'Images_Data_Latin_Validation' 
%save 'Images_Data_Arabic_validation'   
 %save 'Images_Data_Arabic'
 %  load 'Images_Data_Arabic_validation'
 
 load 'Images_Data_Arabic' 
%   [dataTrains, groups] = getData(data,labels);
%     save dataForSVM dataTrains  groups
%    clear
%   disp(' Now Starting Training -------------------------------------------------');
     load dataForSVM 
%     
%     load filename
%     
%     if (all)
    
    OVO1=1;
 
 
svmStruct=TrainSvm(dataTrains,groups,OVO1);

  save model svmStruct  OVO1