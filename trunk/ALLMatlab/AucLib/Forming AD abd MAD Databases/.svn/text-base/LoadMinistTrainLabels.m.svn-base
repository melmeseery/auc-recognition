function [trainlocation, testlocation]=LoadMinistTrainLabels(varargin)
%%%%%
%% this function read the labels from the mnnist database into the labesl 
%%%

if (nargin == 1)
 [x] = varargin{1}(1);
 databpath=x;
end 
if(nargin==0)
    
     databpath=getADDataBasePath(1);
end 
  

 %%%%%%%%%%%%%%%%% train 
trainLabels=[databpath '\train-labels.idx1-ubyte'];
[trainCounts,testCount]=getMaxSampleCount();

fid2=fopen(trainLabels,'r');
B=fread(fid2);
label_index=9;
training_set_labels=[];
for n=1:trainCounts
  
    d=B(label_index);

    
    training_set_labels=[training_set_labels d];
%    x=[A(image_start:image_start+28*28-1)'/255 d];
    
 %   fprintf(fid,format,x');

    
%    image_start= image_start+ 28*28;

    label_index=label_index+1;
    
end
training_set_labels=training_set_labels';
 save  training_set_labels    training_set_labels;
%it tackes the location of the mnist database  


testLabels=[databpath '\t10k-labels.idx1-ubyte']
%[trainCounts,testCount]=getMaxSampleCount();

fid2=fopen(testLabels,'r');
B=fread(fid2);
label_index=9;
testing_set_labels=[];
for n=1:testCount
    
    d=B(label_index);

    
    testing_set_labels=[testing_set_labels d];
%    x=[A(image_start:image_start+28*28-1)'/255 d];
    
 %   fprintf(fid,format,x');

    
%    image_start= image_start+ 28*28;

    label_index=label_index+1;
    
end

testing_set_labels=testing_set_labels';
 save  testing_set_labels    testing_set_labels;



 testlocation  ='testing_set_labels';
 trainlocation= 'training_set_labels';
 
end 
