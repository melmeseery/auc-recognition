function y=getMultiFeatures(L,ByColumn,NoSplits,SizeORSplit,sConstant)


if (NoSplits==0)
    y=ExtractRowColumnFeatures(L, ByColumn);
elseif (NoSplits==1)
     if (ByColumn== 1) 
       y=splitFeatures(L,SizeORSplit,sConstant);
    elseif (ByColumn== 2) 
       y=splitFeatures(L',SizeORSplit,sConstant);
     elseif (ByColumn==3)
          [rightDiagonal,leftDiagonal]=BuildDiagonal(L);
           y=splitFeatures( rightDiagonal,SizeORSplit,sConstant);
            elseif (ByColumn==4)
          [rightDiagonal,leftDiagonal]=BuildDiagonal(L);
           y=splitFeatures(leftDiagonal,SizeORSplit,sConstant);
    elseif (ByColumn==0)
         y1=splitFeatures(L,SizeORSplit,sConstant);
          y2=splitFeatures(L',SizeORSplit,sConstant);
      
          y=[y1 y2];
    end 
    
end 

    