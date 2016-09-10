clear
clc

fid= fopen('Z:\MNIST\train-images.idx3-ubyte','r');
A=fread(fid);
fid2=fopen('Z:\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);


fid= fopen('mnist_training_set.txt','wt');
fprintf(fid,'%d %d \n',60e3,28*28+1);

format=[repmat('%d ',1,28*28+1) '\n'];
image_start=17;
label_index=9;
for n=1:60e3
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

fid= fopen('Z:\MNIST\t10k-images.idx3-ubyte','r');
A=fread(fid);
fid2=fopen('Z:\MNIST\t10k-labels.idx1-ubyte','r');
B=fread(fid2);


fid= fopen('mnist_testing_set.txt','wt');
fprintf(fid,'%d %d \n',10e3,28*28+1);


format=[repmat('%d ',1,28*28+1) '\n'];
image_start=17;
label_index=9;
for n=1:10e3
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
