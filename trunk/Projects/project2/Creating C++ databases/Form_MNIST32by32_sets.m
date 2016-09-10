clear
clc
databpath=getADDataBasePath(1);
[trainCounts,testCount]=getMaxSampleCount();
Minist=[databpath '\train-images.idx3-ubyte' ]
trainLabels=[databpath '\train-labels.idx1-ubyte'];

%fid= fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-images.idx3-ubyte','r');
%A=fread(fid);
%fid2=fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-labels.idx1-ubyte','r');
%B=fread(fid2);



fid= fopen( Minist,'r');
A=fread(fid);
fid2=fopen(trainLabels,'r');
B=fread(fid2);


fid= fopen('mnist32by32_training_set.txt','wt');
%fprintf(fid,'%d %d \n',60e3,32*32+1);

fprintf(fid,'%d %d \n',trainCounts,32*32+1);

format=[repmat('%d ',1,32*32+1) '\n'];
start_of_row=17;
label_index=9;
for n=1:trainCounts
    if(mod(n,100)==0)
        n
    end
    I=zeros(28,28);
    for m=1:28
        I(m,1:end)=A(start_of_row:start_of_row+27);
        start_of_row=start_of_row+28;
    end
    I=double(I)/255;
    d=B(label_index);
    label_index=label_index+1;
    
    I=[zeros(2,28); I; zeros(2,28)];
    I=[zeros(32,2) I zeros(32,2)];
%     imshow(I);
%     d
%     input('zeo:');
    
    I_=I';
    x=[I_(:)' d];
    fprintf(fid,format,x');

    
    
end


fclose(fid);





clear



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%c
%this for generatingthe miniset test set. 
%
%
%%%%%%%%%%%%%%%%%%%%%%%
%get paths 
databpath=getADDataBasePath(1);
[trainCounts,testCount]=getMaxSampleCount();


Minist=[databpath '\t10k-images.idx3-ubyte' ]
testLabels=[databpath '\t10k-labels.idx1-ubyte'];

%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%

%fid= fopen('Z:\MNIST\t10k-images.idx3-ubyte','r');
%A=fread(fid);
%fid2=fopen('Z:\MNIST\t10k-labels.idx1-ubyte','r');
%B=fread(fid2);


fid= fopen(Minist,'r');
A=fread(fid);
fid2=fopen(testLabels,'r');
B=fread(fid2);


fid= fopen('mnist32by32_testing_set.txt','wt');
fprintf(fid,'%d %d \n',testCount,32*32+1);


format=[repmat('%d ',1,32*32+1) '\n'];
start_of_row=17;
label_index=9;
for n=1:testCount
    if(mod(n,100)==0)
        n
    end
    I=zeros(28,28);
    for m=1:28
        I(m,1:end)=A(start_of_row:start_of_row+27);
        start_of_row=start_of_row+28;
    end
    I=double(I)/255;
    d=B(label_index);
    label_index=label_index+1;
    
    I=[zeros(2,28); I; zeros(2,28)];
    I=[zeros(32,2) I zeros(32,2)];
%    imshow(I);
%     d
%     input('zeo:');
    
    I_=I';
    x=[I_(:)' d];
    fprintf(fid,format,x');

    
    
end


fclose(fid);
