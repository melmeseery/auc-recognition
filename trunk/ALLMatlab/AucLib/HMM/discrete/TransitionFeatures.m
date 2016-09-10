function feat=TransitionFeatures(I,SplitSize,NumberOfSplits,OverlapSize,zones)

%% get the boundry box of the image...
nI=getImageBox(I);


[H,W]=size(nI);

%now i have the digit only 

verticalI=SplitImage(nI,SplitSize,NumberOfSplits,OverlapSize);
[h v]=size(verticalI);
%comptue number of transition in each row, colmns. 