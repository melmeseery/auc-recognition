function  feat=ComputeFeatures(image)

load settings 
%    rowColumn=2;  %% 1 column, 2 rows fixed,
%    %  0 do not resize 
%    %%%%%%%   
%    constantSize=30;  %%5 the size of image (row, column or size 
%    ByColumn=0;  % 2  rows only, 1  columns only , 0 both.. ByColumn==3
%    rightDiagonal byColumn=4 leftDiagonal; 
%     NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
%     SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
%     sConstant=10;
    
    if (BothRowAndColumn==0)
        
feat=OurFeatures(image,rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant);

    else
        
         feat1=OurFeatures(image,1,constantSize,NoSplits,1,SizeORSplit,sConstant);
        
        feat2=OurFeatures(image,2,constantSize,NoSplits,2,SizeORSplit,sConstant);
        
        feat =[feat1 feat2(3:end)];  % remove the first two in the second... 
    end 
