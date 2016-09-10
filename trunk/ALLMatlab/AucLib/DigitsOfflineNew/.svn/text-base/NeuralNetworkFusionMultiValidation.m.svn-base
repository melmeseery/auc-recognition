

clear all 
clc 
Num=4; 

TestNameFinal='TestAllFourMajours';
 TestNamearr ={};
  TestName2='dataForSVMVertical';
  rowColumn=1;  %% 1 column, 2 rows fixed,
  constantSize=40;  %%5 the size of image (row, column or size 
  ByColumn=1;  % 2  rows only, 1  columns only , 0 both.. 
  NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
  SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
  sConstant=5;
 BothRowAndColumn=0;
i=1; 
 
TestNamearr{i} = TestName2;
rowColumnarr(i)= rowColumn;
constantSizearr(i)= constantSize;
NoSplitsarr(i)= NoSplits;
ByColumnarr(i)= ByColumn ;
SizeORSplitarr(i)= SizeORSplit;
sConstantarr(i)=sConstant;
BothRowAndColumnarr(i)= BothRowAndColumn;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
  TestName='dataForSVMHorizontal';
  rowColumn=2;  %% 1 column, 2 rows fixed,
  constantSize=40;  %%5 the size of image (row, column or size 
  ByColumn=2;  % 2  rows only, 1  columns only , 0 both.. 
  NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
  SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
  sConstant=5;
 BothRowAndColumn=0;
i=2; 
 
TestNamearr{i} = [TestName ];
rowColumnarr(i)= rowColumn;
constantSizearr(i)= constantSize;
NoSplitsarr(i)= NoSplits;
ByColumnarr(i)= ByColumn ;
SizeORSplitarr(i)= SizeORSplit;
sConstantarr(i)=sConstant;
BothRowAndColumnarr(i)= BothRowAndColumn;
 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
  
  TestName='dataForSVMrightDiagonal';
  rowColumn=2;  %% 1 column, 2 rows fixed,
  constantSize=40;  %%5 the size of image (row, column or size 
  ByColumn=3;  % 2  rows only, 1  columns only , 0 both.. 
  NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
  SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
  sConstant=5;
 BothRowAndColumn=0;
i=3; 
 
TestNamearr{i} = [TestName  ''];
rowColumnarr(i)= rowColumn;
constantSizearr(i)= constantSize;
NoSplitsarr(i)= NoSplits;
ByColumnarr(i)= ByColumn ;
SizeORSplitarr(i)= SizeORSplit;
sConstantarr(i)=sConstant;
BothRowAndColumnarr(i)= BothRowAndColumn;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
  
  TestName='dataForSVMleftDiagonal';
  rowColumn=2;  %% 1 column, 2 rows fixed,
  constantSize=40;  %%5 the size of image (row, column or size 
  ByColumn=4;  % 2  rows only, 1  columns only , 0 both.. 
  NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
  SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
  sConstant=5;
 BothRowAndColumn=0;
i=4; 
 
TestNamearr{i} = [TestName  ''];
rowColumnarr(i)= rowColumn;
constantSizearr(i)= constantSize;
NoSplitsarr(i)= NoSplits;
ByColumnarr(i)= ByColumn ;
SizeORSplitarr(i)= SizeORSplit;
sConstantarr(i)=sConstant;
BothRowAndColumnarr(i)= BothRowAndColumn;

%result=SaveFusionData(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr)
%   vSets=[];
%   
% x=0;
%  for t=1:5
%      if (t==1)
%  startv=1+x;
%      else 
%      startv=x;  
%      end
%  endv=1000+x; 
%  startt=1001+x;
%  endt=2000+x;
%  vSets= [  vSets; startv    endv   startt  endt]
%  
%  x=x+1000;
%  end
%   vSets= [  vSets; 5000    6000   1  1000]
  
% save  SVMDataFiles   TrainFileNames  TestFileNames  Num vSets startt
% startv endv endt
 load  SVMDataFiles   
 useValidation=0; 
Net=[];
  OVO1=1; 
 DecisionValues_validation=[];
 DecisionValues=[];
 vSize=size(vSets,1);
 for val=1:vSize
     FinalResult_validation=[];
     FinalResult=[];
 DecisionValues_validation=[];
 DecisionValues=[];
  startv= vSets(val,1);
 endv=vSets(val,2);
 startt=vSets(val,3);
 endt=vSets(val,4);
 
 for i=1:Num
     
   TestName=TestNamearr{i};   
     
     
 filename= ['Results' TestName  ];

 load(TrainFileNames{i})  % load the data... 
 %% supoose i want only to the validation.. so 
    [dataTrains1,groups1,test,testlabel]=extractTrainValidation(dataTrains,groups,10,startt,endt,6000);

  [train,trainlabel,test,testlabel]=extractTrainValidation(dataTrains1,groups1,10,startt,endt,5000);
  [train,trainlabel,validation,validationlabel]=extractTrainValidation(train,trainlabel,10,startv,endv,4000);
 
 svmStruct=TrainSvmData(train,trainlabel,OVO1);
    
  [result_validation,correct_validation,sampleSize_validation,conf_validation,predict_label_validation, accuracy_validation, dec_values_validation,groups_validation]=TestSVMData(validation,validationlabel,svmStruct,OVO1);

 [result,correct,sampleSize,conf,predict_label, accuracy, dec_values,groups]=TestSVMData (test,testlabel,svmStruct,OVO1);

 %clear dataTest groups
%save(['./Result/Results_Fuision'  filename]);
FinalResult_validation(:,i)=predict_label_validation;
DecisionValues_validation=[DecisionValues_validation dec_values_validation];
accuracyForClassifier_validation(i)=accuracy_validation; 


FinalResult(:,i)=predict_label;
DecisionValues=[DecisionValues dec_values];
accuracyForClassifier(i)=accuracy; 


 end
   
 [result,correct,Net]=trainTestNeuralNetwork( DecisionValues_validation,groups_validation, DecisionValues,groups,Net,1);
 ff=[ 'TempDataWorkingVal'  int2str(val) '.mat']

save ff
 end
 
 
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Testing the neural
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%networkkkk.............
 FinalResult_validation=[];
     FinalResult=[];
 DecisionValues_validation=[];
 DecisionValues=[];
  for i=1:Num
   TestName=TestNamearr{i};  
 filename= ['Results' TestName  ];
 load(TrainFileNames{i})  % load the data... 
   [train,trainlabel,test,testlabel]=extractTrainValidation(dataTrains,groups,10,5000,6000,6000);   
 svmStruct=TrainSvmData(train,trainlabel,OVO1);
   %% modelfile=['model' TrainFileNames{i}   '.mat'];  
 [result,correct,sampleSize,conf,predict_label, accuracy, dec_values,groups]=TestSVMData (test,testlabel,svmStruct,OVO1);

FinalResult(:,i)=predict_label;
DecisionValues=[DecisionValues dec_values];
accuracyForClassifier(i)=accuracy; 

   
  end 
 
 [result,correct,Net]=trainTestNeuralNetwork(DecisionValues,groups, DecisionValues,groups,Net,2)
 
%%%%% [result,correct,sizeOfTest]=ClassifierFusionTraining(200,FinalResult_validation,DecisionValues_validation,groups_validation,FinalResult,DecisionValues,groups);

saveFusionData(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr,accuracyForClassifier,result,correct);




y=result*100; 