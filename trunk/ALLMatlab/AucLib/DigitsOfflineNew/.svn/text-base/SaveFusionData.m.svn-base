 
function y=SaveFusionData(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr)

y=0

SaveNamearr={};
SaveNamearrTest={};
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



TestName
disp('Computing the  train data ');
load 'Images_Data_Arabic' 
[dataTrains, groups] = getData(data,labels);
dataForSVM=[TestName '_train'];
save (dataForSVM, 'dataTrains' , 'groups');

SaveNamearr{i}=dataForSVM; 

disp('Computing the  test data ');
load 'Images_Data_Arabic_Test'   
[dataTest, groups] = getData(data,labels);
dataForSVM_Test=[TestName '_test.mat'];
save (dataForSVM_Test,'dataTest',  'groups');
SaveNamearrTest{i}=dataForSVM_Test; 
 end 
 
 TrainFileNames=SaveNamearr  ;
 TestFileNames=SaveNamearrTest;
 
 save  SVMDataFiles   TrainFileNames  TestFileNames  Num
 
 y=1; 