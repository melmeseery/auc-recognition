function y = hmmfeatures(data,isOnline,varargin)

%-----------------------------------------------------------------------
%  hmm features is either Online or offfline 
% if online then the data is a single stroke 
% if offline then the data is a single image...
%-----------------------------------------------------------------------
if (isOnline)
    y=OnlineFeatures(data, varargin{:});
else 
    y= hmmOfflineFeatures(data,varargin{:});
end 