function [Order,NormalizedSize,Feature,ReSample]=unPackOnlineFeaturesVariables(varargin)

 noOrder=1;
  noNormalizedSize=1;
  noFeature=1;
    noReSample=1;
  
 % the variable number of variables. 
 % first unpack the variables...
 %number of variable to in vargin.
 nVar=nargin;
 for n=1:2:nVar
   text= varargin{n};
   value=varargin{n+1};
    if (strcmp(text,'Order'))
     Order=value;
  noOrder=0;
    elseif (strcmp(text,'NormalizedSize'))
       NormalizedSize=value;
        noNormalizedSize=0;
    elseif(strcmp(text,'ReSample'))
        ReSample=value;
    noReSample=0;
   elseif (strcmp(text,'Feature'))
       Feature=value;
        noFeature=0;
   
   end 
 end
 
 
 
 
 if(noOrder)
     % the default size is as following ...    
     Order=5;
 end 
 
 if(noFeature)
     % the default size is as following ...    
     Feature=1;
 end 
  if(noNormalizedSize)
     % the default size is as following ...    
    NormalizedSize=20;
  end 
 if(noReSample)
     ReSample=0;
 end 
