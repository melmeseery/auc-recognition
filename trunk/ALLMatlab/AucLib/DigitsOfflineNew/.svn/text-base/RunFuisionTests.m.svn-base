
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
 y=TrainTestFusion(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr)

%result=TestSvmFusion(Num,TestNameFinal,TestNamearr,rowColumnarr,constantSizearr,NoSplitsarr,ByColumnarr,SizeORSplitarr,sConstantarr,BothRowAndColumnarr)

