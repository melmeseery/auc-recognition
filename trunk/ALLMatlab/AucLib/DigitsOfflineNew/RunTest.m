function [result,correct,sampleSize,conf]=RunTest(TestName,rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
  OVO1=1; 
save  'settings' 

TrainSvm ();
[result,correct,sampleSize,conf,predict_label, accuracy, dec_values,groups]=TestSVM ();
result 
correct 

filename=['./Result/Results_' TestName ]

save( filename);
 
 
fid= fopen([filename  '.txt'],'wt');

%%%%
%%% @RELATION    TrainStrokeTrainF20.txt.arff
fprintf(fid,'@TestD Details   %s  \n \n', TestName);
 
%% @attribute   'F_0'  numeric  
fprintf(fid,'Results of Test %s are %d  percent with %d correct samples out of %d \n  \n',TestName,result,correct,sampleSize);
fprintf(fid,'  Confusion matrix is divided as following.... \n');
 [r c]=size(conf);

%fprintf(fid,'%d %d \n',trainCounts,numFeat+1);

 format=[repmat('%d ',1,c) '   \n'];
  
 fprintf(fid,format,conf);
 
 fprintf(fid,'-----------------------------------------------');
 fprintf(fid,' The settings for this run is  \n rowColumn = %d \n  , constantSize=%d \n,NoSplits=%d \n,ByColumn=%d \n,SizeORSplit=%d \n,sConstant=%d \n ' , rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant);
 
fclose(fid);
disp('Finish writing the file');
