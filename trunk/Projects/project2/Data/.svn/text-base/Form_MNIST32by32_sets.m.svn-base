clear
clc

fid= fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-images.idx3-ubyte','r');
A=fread(fid);
fid2=fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);


fid= fopen('mnist32by32_training_set.txt','wt');
fprintf(fid,'%d %d \n',60e3,32*32+1);

format=[repmat('%d ',1,32*32+1) '\n'];
start_of_row=17;
label_index=9;
for n=1:60e3
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

fid= fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\t10k-images.idx3-ubyte','r');
A=fread(fid);
fid2=fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\t10k-labels.idx1-ubyte','r');
B=fread(fid2);


fid= fopen('mnist32by32_testing_set.txt','wt');
fprintf(fid,'%d %d \n',10e3,32*32+1);


format=[repmat('%d ',1,32*32+1) '\n'];
start_of_row=17;
label_index=9;
for n=1:10e3
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
