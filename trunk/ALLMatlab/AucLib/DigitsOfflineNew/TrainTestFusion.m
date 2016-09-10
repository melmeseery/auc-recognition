function y=TrainTestFusion(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr)




%result=SaveFusionData(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr)

% save  SVMDataFiles   TrainFileNames  TestFileNames  Num
 load  SVMDataFiles   
 useValidation=0; 

 startv=4000;
 endv=5000; 
 startt=5000;
 endt=6000;
  OVO1=1; 
 DecisionValues_validation=[];
 DecisionValues=[];
 for i=1:Num
     
   TestName=TestNamearr{i};   
     
     
 filename= ['Results' TestName  ];

 load(TrainFileNames{i})  % load the data... 
 %% supoose i want only to the validation.. so 
 %%% 

  if ( useValidation==1)  
  [train,trainlabel,test,testlabel]=extractTrainValidation(dataTrains,groups,10,startt,endt,6000);
  [train,trainlabel,validation,validationlabel]=extractTrainValidation(train,trainlabel,10,startv,endv,5000);
   %svmStruct=TrainSvmData(dataTrains,groups,OVO1);
  else
      [train,trainlabel,test,testlabel]=extractTrainValidation(dataTrains,groups,10,startt,endt,6000);
      validation=train;
      validationlabel=trainlabel;
  end 
 %svmStruct=TrainSvmData(train,trainlabel,OVO1);
 
 modelfile=['model' TrainFileNames{i}   '.mat'];
 %save (modelfile, 'OVO1','svmStruct');
 
 load (modelfile);
 %[result,correct,sampleSize,conf,predict_label, accuracy, dec_values,groups]=TestSVMData (dataTest,groups,svmStruct,OVO1);
 [result_validation,correct_validation,sampleSize_validation,conf_validation,predict_label_validation, accuracy_validation, dec_values_validation,groups_validation]=TestSVMData (validation,validationlabel,svmStruct,OVO1);

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
  
 save (['Fusion_Values_' TestNameFinal  ]);
 
[result,correct,sizeOfTest]=ClassifierFusionTraining(200,FinalResult_validation,DecisionValues_validation,groups_validation,FinalResult,DecisionValues,groups);
 %[result,correct,sizeOfTest]=ClassifierFusion(DecisionValues,FinalResult,ActualLabels,1) ;


saveFusionData(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr,accuracyForClassifier,result,correct);
y=result*100; 
