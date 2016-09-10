function [result,correct,sampleSize,conf,predict_label, accuracy, dec_values,groups]=TestSVM ();

clear all
clc
  
%   [data,labels]=readImages(111 , 1 );
%  
%   save 'Images_data_Latin_TestValidation' 
%  save 'Images_Data_Arabic_TestValidation'   
load 'Images_Data_Arabic_TestValidation'   
  %load 'Images_data_Latin_TestValidation' 
 %load 'Images_data_Latin_TestValidation' 
 %save 'Images_Data_Arabic_Test' 
 %load 'Images_Data_Arabic_Test' 
 [dataTest, groups] = getData(data,labels);
 save dataForSVM_Test dataTest  groups
  clear
    load dataForSVM_Test
    load model
  % break  
   %OVO=1;
   disp(' Now Starting Testing -------------------------------------------------');
    [result,correct,sampleSize,conf,predict_label, accuracy, dec_values,groups]=TestSVMData (dataTest,groups,svmStruct,OVO1);
end 
   
   