clear

clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%c
%this for generatingthe miniset train set. 
%
%%%%%%%%%%%%%%%%%%%%%%%
%get paths 
threshold=0.5;  %createing the binary image threshold. 
size=15; % Minimum Object size (area)
colcount=28;  
% the path of the input mnist file. 
databpath=getADDataBasePath(1);  
% the count of sample in mnist file 
[trainCounts,testCount]=getMaxSampleCount()
 F=[databpath '\train-images.idx3-ubyte'];
%trainLabels=[databpath '\train-labels.idx1-ubyte'];

 %viewMnistImage(F,10,1, trainLabels);
%%%%%%%%%%%%%%%%%%%%%%%%
%fid= fopen('Z:\MNIST\train-images.idx3-ubyte','r');
% 
 SaveFile='train-images.idx3-ubyte';
 %trainLabels='train-labels.idx1-ubyte';
 %viewMnistImage(SaveFile,10,1, trainLabels);
 Minist=[databpath '\train-images.idx3-ubyte' ]
 ModifyDatabase(Minist,trainCounts,SaveFile,size,threshold);
 %for  k = 70:90
 

 %endc
 disp('Finished the training image now will start the test images');

Minist=[databpath '\t10k-images.idx3-ubyte' ];
SaveFile='t10k-images.idx3-ubyte';

ModifyDatabase(Minist,testCount,SaveFile,size,threshold);

