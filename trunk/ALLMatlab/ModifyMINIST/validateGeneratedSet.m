clear
clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%c
%this for generatingthe miniset train set. 
%
%
%%%%%%%%%%%%%%%%%%%%%%%
%get paths 
databpath='';%getADDataBasePath(1);
%datapath='';
[trainCounts,testCount]=getMaxSampleCount();
Minist=[databpath '\train-images.idx3-ubyte' ]
trainLabels=[databpath '\train-labels.idx1-ubyte'];
%%%%%%%%%%%%%%%%%%%%%%%%
%fid= fopen('Z:\MNIST\train-images.idx3-ubyte','r');

fid = fopen(Minist,'r');
A=fread(fid);
%fid2=fopen('Z:\MNIST\train-labels.idx1-ubyte','r');
fid2=fopen(trainLabels ,'r');
B=fread(fid2);


fid= fopen('mnist_training_set.txt','wt');
fprintf(fid,'%d %d \n',trainCounts,28*28+1);
%fprintf(fid,'%d %d \n',60e3,28*28+1);

format=[repmat('%d ',1,28*28+1) '\n'];
image_start=17;
label_index=9;
%for n=1:60e3

for n=1:trainCounts
    if(mod(n,100)==0)
        n
    end
    d=B(label_index);

    x=[A(image_start:image_start+28*28-1)'/255 d];
    
    fprintf(fid,format,x');

    
    image_start= image_start+ 28*28;

    label_index=label_index+1;
    
end


fclose(fid);





clear
clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%c
%this for generatingthe miniset test set. 
%
%
%%%%%%%%%%%%%%%%%%%%%%%
%get paths 
databpath='';%getADDataBasePath(1);
[trainCounts,testCount]=getMaxSampleCount();


Minist=[databpath '\t10k-images.idx3-ubyte' ]
testLabels=[databpath '\t10k-labels.idx1-ubyte'];

%%%%%%%%%%%%%%%%%%%%%%%%

%fid= fopen('Z:\MNIST\t10k-images.idx3-ubyte','r');
%A=fread(fid);
%fid2=fopen('Z:\MNIST\t10k-labels.idx1-ubyte','r');
%B=fread(fid2);


fid= fopen(Minist,'r');
A=fread(fid);
fid2=fopen(testLabels,'r');
B=fread(fid2);


fid= fopen('mnist_testing_set.txt','wt');
fprintf(fid,'%d %d \n',testCount,28*28+1);
%fprintf(fid,'%d %d \n',10e3,28*28+1);

format=[repmat('%d ',1,28*28+1) '\n'];
image_start=17;
label_index=9;
%for n=1:10e3
for n=1:testCount
    if(mod(n,100)==0)
        n
    end
    d=B(label_index);

    x=[A(image_start:image_start+28*28-1)'/255 d];
    
    fprintf(fid,format,x');

    
    image_start= image_start+ 28*28;

    label_index=label_index+1;
    
end


fclose(fid);
