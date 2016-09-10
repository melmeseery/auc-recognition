function y=FeatureVector(Data,labels,Ssizes,FeatName)

Features=[];

%run the feature s
for Digit1=0:9
% if (Digit1==0)    
% 
% [featureSetDigit1,AvgTime,TotalTime,AvgTotalTime]=ComputeFeatures(Data4,labels4,Ssizes4,Digit1,FeatName);
% 
% else 
Digit1
    [featureSetDigit1,AvgTime,TotalTime,AvgTotalTime]=ComputeFeatures(Data,labels,Ssizes,Digit1,FeatName);
%end 
 Features=[Features; featureSetDigit1];

end

y=Features;