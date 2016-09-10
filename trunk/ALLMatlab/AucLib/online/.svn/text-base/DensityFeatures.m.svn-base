function [feat] =  DensityFeatures(I,useWindow,SplitSize,NumberOfSplits,OverlapSize,NumberOfZones)
%%this code compute the density of  in upper/lower , left and write in the
%%image.
if (useWindow==1)    
      featWhole= Density(I,0);
    verticalI=SplitImage(I,SplitSize,NumberOfSplits,OverlapSize);
     [h v]=size(verticalI);
    y=[];
    for i=1:v
        if (~isempty(verticalI{i}))
         Feat = Density(verticalI{i},NumberOfZones);
         y=[y Feat];
        end 
    end 
    
    feat=[featWhole  y];

    
else 
    feat= Density(I,0);
    
    
end 


%feat=[Feat];
%feat=Feat+1;


