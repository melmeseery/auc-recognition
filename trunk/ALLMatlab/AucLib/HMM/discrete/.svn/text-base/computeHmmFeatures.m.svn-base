function  [Y,Samples,FeatureSize,labelPerSample]=computeHmmFeatures( data,isOnline,varargin )
%%%%%%%%%%%%%%%%%%%%%%%%%55555
% data is the input is the  images  in form of  [ digit1{ image1(28X28)
% image2...}  digit2{----}.......]
% feat is the name (label)of feature feature 
% 1 gaussian 2 for chain code 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%555
%% computer features and concatenate all data into single matrix
FeatureSize=0;
Samples=0;

R = length(data);               % Number of words to be recognized.
labelPerSample=[];
Y = [];
for (i = 1:R)  % for each category 
    i
  L = length(data{i});                % Number of occurrences of i'th word.
  for (j = 1:L)  %%% for each sample in this category or word.
    s = data{i}{j}; % Get category i sample j
     labelPerSample=[labelPerSample; i];
      Samples=Samples+1;
    y = hmmfeatures(s,isOnline,varargin{:});  % Extract feature vectors.
        [r c]=size(y);
        FeatureSize=r;
    Y = [Y y];                        % Concatenate vectors for all sequences.
    
  if(mod(j,500)==0) 
      digit=i
      sample=j
      feature=y
     SizeOfFeatureVector= size(y)
  end 
    
  end
end

[rr cc]=size(Y)


%%% end of files... 