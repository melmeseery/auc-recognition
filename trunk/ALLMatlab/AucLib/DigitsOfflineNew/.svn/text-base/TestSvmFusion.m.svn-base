function result=TestSvmFusion(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr)



 for i = 1: Num
     
 TestName=TestNamearr{i};
 rowColumn=rowColumnarr(i);
 constantSize =constantSizearr(i);
 NoSplits=NoSplitsarr(i);
 ByColumn = ByColumnarr(i);
 SizeORSplit=SizeORSplitarr(i);
 sConstant  =sConstantarr(i);
 BothRowAndColumn=BothRowAndColumnarr(i);
 
 
save  'settings'  TestName  rowColumn  constantSize  NoSplits  ByColumn  SizeORSplit  sConstant  BothRowAndColumn
filename= ['Results' TestName  ];
TrainSvm ();

[result,correct,sampleSize,conf,predict_label, accuracy, dec_values,ActualLabels]=TestSVM ();

save(filename)

FinalResult(:,i)=predict_label;
DecisionValues(:,i)=dec_values;
accuracyForClassifier(i)=accuracy; 

 end 
 
 
 
 
save (['Fusion_Values_' TestNameFinal  ]);

 [result,correct,sizeOfTest]=ClassifierFusion(DecisionValues,FinalResult,ActualLabels,1) 
 
saveFusionData(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr,accuracyForClassifier,result,correct);
% save (['Result' TestNameFinal  ]);
% 
% 
% filename=['./Result/Results_Fuision' TestNameFinal ]
% 
% save( filename);
%  
%  
% fid= fopen([filename  '.txt'],'wt');
% 
% %%%%
% %%% @RELATION    TrainStrokeTrainF20.txt.arff
%  fprintf(fid,'@TestD Details   %s  \n \n',  TestNameFinal);
%  fprintf(fid,'The overall Results of Test %s \n is  %d  percent with %d correct \n -------------------- \n ',  TestNameFinal,result,correct); 
%  fprintf(fid,' ---------------------------\n  the detailed result of each classifier is as following.... '); 
%  for i = 1: Num
%      
%       TestName=TestNamearr{i};
%  rowColumn=rowColumnarr(i);
%  constantSize =constantSizearr(i);
%  NoSplits=NoSplitsarr(i);
%  ByColumn = ByColumnarr(i);
%  SizeORSplit=SizeORSplitarr(i);
%  sConstant  =sConstantarr(i);
%  BothRowAndColumn=BothRowAndColumnarr(i);
% 
% %% @attribute   'F_0'  numeric  
% fprintf(fid,'Results of Test %s are %d  percent \n  \n',TestName,accuracyForClassifier(i));
%  
%  fprintf(fid,' The settings for test is  \n rowColumn = %d \n  , constantSize=%d \n,NoSplits=%d \n,ByColumn=%d \n,SizeORSplit=%d \n,sConstant=%d \n ' , rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant);
%  
%  end 
% fclose(fid);
% disp('Finish writing the file');
