function [SplitSize,OverlapSize,NumberOfZones,NumberOfSplits,Feature]=unPackFeaturesVariables(varargin)

 noSplitSize=1;
  noOverlapSize=1;
  noNumberOfZones=1;
  noFeature=1;
    noNumberOFSplits=1;
  
 % the variable number of variables. 
 % first unpack the variables...
 %number of variable to in vargin.
 nVar=nargin;
 for n=1:2:nVar
   text= varargin{n};
   value=varargin{n+1};
   if (strcmp(text,'SplitSize'))
       SplitSize=value;
  noSplitSize=0;
   elseif (strcmp(text,'NumberOfSplits'))
       NumberOfSplits=value;
  noNumberOfSplits=0;
   elseif (strcmp(text,'OverlapSize'))
       OverlapSize=value;
       noOverlapSize=0;
   elseif (strcmp(text,'Feature'))
       Feature=value;
        noFeature=0;
   elseif (strcmp(text,'NumberOfZones'))
       NumberOfZones=value; 
       noNumberOfZones=0;
   end 
 end  
 
 if(noSplitSize)
     % the default size is as following ...    
     SplitSize=5;
 end 
 if (noNumberOFSplits)
     NumberOfSplits=8;
 end
 if (noNumberOfZones)
     NumberOfZones=1;
 end 
 if (noOverlapSize)
     OverlapSize=0;
 end 
 
%     'SplitSize' define split size in pixels  ( the vertical split).
%     'NumberOfSplits'  number of horizontal splits in a single image. 
%     'OverlapSize'   size of overlap in pixels where 0 is no overlap 
%     'Feature'   the id of feature to computes. 
%     'NumberOfZones'  number of zones per split ( window in each split)
 
