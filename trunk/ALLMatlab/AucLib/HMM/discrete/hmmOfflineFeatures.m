function y= hmmOfflineFeatures(image,varargin)

%%%%%%%%%%%%%%%%%%55
%
%  hmmfeatures --> Feature extraction for HMM recognizer.
%
%
%  <Synopsis>
%    y = hmmfeatures(s)
%    y = hmmfeatures(s,'ParameterName',ParamValue,.......)
%   ParamterName==> can be  
%     'SplitSize' define split size in pixels  ( the vertical split).
%     'NumberOfSplits'  number of horizontal splits in a single image. 
%     'OverlapSize'   size of overlap in pixels where 0 is no overlap 
%     'Feature'   the id of feature to computes. 
%     'NumberOfZones'  number of zones per split ( window in each split)
%                           
%  <Description>
%    A frame based analysis of the speech signal, s, is performed to
%    give observation vectors (columns of y), which can be used to train
%    HMMs for speech recognition.
%
%    The speech signal is blocked into frames of N samples, and
%    consecutive frames are spaced deltaN samples apart. Each frame is
%    multiplied by an N-sample Hamming window, and Mth-order LP analysis
%    is performed. The LPC coefficients are then converted to Q cepstral
%    coefficients, which are weighted by a raised sine window. The result
%    is the first half of an observation vector, the second half is the
%    differenced cepstral coefficients used to add dynamic information.
%    Thus, the returned argument y is an 2Q-by-T matrix, where T is the
%    number of frames.
%
%  <See Also>
%    hmmcodebook --> Codebook generation for HMM recognizer.

%  <References>
%  [1] J.R Deller, J.G. Proakis and F.H.L. Hansen, "Discrete-Time
%      Processing of Speech Signals", IEEE Press, chapter 12, (2000).
%
%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------
variance = (sqrt(2)*3/pi)^2;
 h= Generate_Gaussian_Mask([5 5],variance);
if (nargin==1)
      f= (gmask_Local_Gradient_proj(im2double(image),h))';
      y=[f(1:25) f(26:50) f(51:75) f(76:100) f(1+100:25+100) f(26+100:50+100) f(51+100:75+100) f(76+100:100+100)];
  y=y+1;
else
    [SplitSize,OverlapSize,NumberOfZones,NumberOfSplits,Feature]=unPackFeaturesVariables(varargin{:});
   
if (Feature==1)  % compute gradient features.... 
      f= (gmask_Local_Gradient_proj(im2double(image),h))';
      y=[f(1:25) f(26:50) f(51:75) f(76:100) f(1+100:25+100) f(26+100:50+100) f(51+100:75+100) f(76+100:100+100)];
    
end 

y=[];

if (Feature==2)  % compute histogram of density features....
    verticalI=SplitImage(image,SplitSize,NumberOfSplits,OverlapSize);
    [h v]=size(verticalI); 
    for i=1:v
    Feat = DensityFeature(verticalI{i},NumberOfZones);
    y=[y Feat];
    end 
end 

if (Feature==3)  % compute transisiton Features.... 
   
    Feat = TransitionFeatures(image,SplitSize,NumberOfSplits,OverlapSize,NumberOfZones);
    y=Feat;
 
end 

if (Feature==4)
    Feat=OurFeatures(image);
    y=Feat;
end 
if (Feature==5)
    Feat=OVO(image);
    y=Feat';
end 
%%now i have all the information i need compute the features... 


end 
