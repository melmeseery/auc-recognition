
%%%5% A=[1 1 1  1 1 ; 2  2 2 2 2 ; 3 3 3 3 3 ;
%4 4 4 4 4 ; 5  5 5 5 5 ; 6 6 6 6 6 ;  ];
%  [rightDiagonal,leftDiagonal]=BuildDiagonal(A);
 %    rowColumn=2;  %% 1 column, 2 rows fixed,
%    %  0 do not resize 
%    %%%%%%%   
%    constantSize=30;  %%5 the size of image (row, column or size 
%    ByColumn=0;  % 2  rows only, 1  columns only , 0 both.. ByColumn==3
%    rightDiagonal byColumn=4 leftDiagonal; 
%     NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
%     SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
%     sConstant=10;



  filename='ValMADBASE'
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Train test ... 
  
  x=[ 4    5    6    7   8   10   ];
  NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
  SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
  constantSize=40;  %%5 the size of image (row, column or size 
    
  %Options=[2   2   0;1   1   0;
      Options=[2  1  0;   2   2   0;  2  3  0;   2   4   0;   
               1  1  0;   1   2   0;  1  3  0;   1   4   0;]
  k=1;
  
  for j=1:size(Options,1)
    rowColumn=Options(j,1);
    ByColumn=Options(j,2); 
     BothRowAndColumn=Options(j,3);
    
  for i=1 :length(x)
 sConstant=x(i);
  
  if (   ByColumn==1)
      rowColname='Col'
  elseif (ByColumn==2)
     rowColname='Row'
  elseif (ByColumn==3) 
      rowColname='rightDiagonal'
  elseif (ByColumn==4)   
      rowColname='leftDiagonal'
  end
%    rightDiagonal byColumn=4 leftDiagonal; 
  if (BothRowAndColumn==1)
     rowColname='RowCol'
  end 
  
  if ( rowColumn==0)
  Resizename='NoResize';
  elseif ( rowColumn==1)
   Resizename=['ResizeCol'  int2str(constantSize)];
  elseif ( rowColumn==2)
  Resizename=['ResizeRow' int2str(constantSize)];
  end 
  testname=['Test' int2str(k) filename  Resizename rowColname  'NumberSplit' int2str(  sConstant)   ]
try
    
[result,correct,sampleSize,conf]=RunTest(testname,rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)

catch Me
  fid= fopen([testname  'Error.txt'],'wt');
fprintf(fid,' The test generated an error...');   
fprintf(fid,' The Exception is %s ', getReport(Me));   
fclose(fid);
end 
k=k+1;
  end 
  end

%     sConstant=5;
% [result,correct,sampleSize,conf]=RunTest('ValResize40RowNumberSplit5',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%     sConstant=7;
% [result,correct,sampleSize,conf]=RunTest('ValResize40RowNumberSplit7',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%       sConstant=4;
% [result,correct,sampleSize,conf]=RunTest('ValResize40RowNumberSplit4',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%         sConstant=1;
% [result,correct,sampleSize,conf]=RunTest('ValResize40RowNumberSplit2',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%   
%       sConstant=1;
% [result,correct,sampleSize,conf]=RunTest('ValResize40RowNumberSplit1',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%         sConstant=20;
% [result,correct,sampleSize,conf]=RunTest('ValResize40RowNumberSplit20',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55


%    rowColumn=0;  %% 1 column, 2 rows fixed,
%    %  0 do not resize 
%    %%%%%%%   
%    constantSize=30;  %%5 the size of image (row, column or size 
%    ByColumn=2;  % 2  rows only, 1  columns only , 0 both.. 
%     NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
%     SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
%  sConstant=10;
%   BothRowAndColumn=1;
%  
% % [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowColNumberSplit10',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% %     sConstant=5;
% % [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowColNumberSplit5',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% %     sConstant=7;
% % [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowColNumberSplit7',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%       sConstant=4;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowColNumberSplit4',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%         sConstant=1;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowColNumberSplit2',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%   
%       sConstant=1;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowColNumberSplit1',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%         sConstant=20;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowColNumberSplit20',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% 
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
% 
% 
%    rowColumn=0;  %% 1 column, 2 rows fixed,
%    %  0 do not resize 
%    %%%%%%%   
%    constantSize=40;  %%5 the size of image (row, column or size 
%    ByColumn=2;  % 2  rows only, 1  columns only , 0 both.. 
%     NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
%     SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
%  sConstant=10;
%   BothRowAndColumn=0;
%  
%  [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowNumberSplit10',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%      sConstant=5;
%  [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowNumberSplit5',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%      sConstant=7;
%  [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowNumberSplit7',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% 
%       sConstant=4;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowNumberSplit4',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%         sConstant=1;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowNumberSplit2',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%   
%       sConstant=1;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowNumberSplit1',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
%         sConstant=20;
% [result,correct,sampleSize,conf]=RunTest('ValNoResizeRowNumberSplit20',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% 
% 
% 
% %  rowColumn=2; 
% %  constantSize=40; 
% %  ByColumn=2;
% %  BothRowAndColumn=0;
% %  SizeORSplit=2;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
% %  sConstant=2;
% % [result,correct,sampleSize,conf]=RunTest('ValResize40RowSplitSize2',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% %     sConstant=3;
% % [result,correct,sampleSize,conf]=RunTest('ValResize40RowSplitSize3',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% %  sConstant=5;
% % [result,correct,sampleSize,conf]=RunTest('ValResize40RowSplitSize5',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% %    sConstant=7;
% % [result,correct,sampleSize,conf]=RunTest('ValResize40RowSplitSize7',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% %  
% %    sConstant=10;
% % [result,correct,sampleSize,conf]=RunTest('ValResize40RowSplitSize10',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant,BothRowAndColumn)
% % 
% 
% 
% 
% 
% % % 
% % % [result,correct,sampleSize,conf]=RunTest('Resize30ColNumberSplit3',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% % %    
% % %   sConstant=4;
% % %   
% % % [result,correct,sampleSize,conf]=RunTest('Resize30ColNumberSplit4',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  
% %   rowColumn=2;
% %   ByColumn=2;
% %   sConstant=1;
% %   
% % [result,correct,sampleSize,conf]=RunTest('Resize30RowNumberSplit1',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  
% % 
% %  %  sConstant=3;
% %   
% % %[result,correct,sampleSize,conf]=RunTest('Resize30RowNumberSplit3',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  
% % %    sConstant=4;
% % %   
% % % [result,correct,sampleSize,conf]=RunTest('Resize30RowNumberSplit4',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  
% % 
% %   
% %   
% % %[result,correct,sampleSize,conf]=RunTest('Resize40RowSplitsize7',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Train test ... 
% %    rowColumn=2;  %% 1 column, 2 rows fixed,
% %    %  0 do not resize 
% %    %%%%%%%   
% %    constantSize=30;  %%5 the size of image (row, column or size 
% %    ByColumn=0;  % 2  rows only, 1  columns only , 0 both.. 
% %     NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
% %     SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
% %    sConstant=1;
% % 
% %  
% % [result,correct,sampleSize,conf]=RunTest('Resize30RowColNumberSplit1',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %    
% %   sConstant=5;
% 
% %[result,correct,sampleSize,conf]=RunTest('Resize30RowColNumberSplit5',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
%    
% %  sConstant=10;
%   
% % [result,correct,sampleSize,conf]=RunTest('Resize30RowColNumberSplit10',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  
% %  
% %   sConstant=15;
% %   
% % [result,correct,sampleSize,conf]=RunTest('Resize30RowNumberSplit15',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  
% % 
% %    sConstant=20;
% %   
% % [result,correct,sampleSize,conf]=RunTest('Resize3RowNumberSplit20',rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %  
% % 
  