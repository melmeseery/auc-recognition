% clear 
% clc
% 
% fid= fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-images.idx3-ubyte','r');
% A=fread(fid);
% 
set=zeros(10e3,28*28);
image_start=17;

for n=1:60e3
    if(mod(n,100)==0)
        n
    end

    m= mod(n,10e3);
    if(m==0)
        m=10e3;
    end
    set(m,:)=A(image_start:image_start+28*28-1)'; 
    if(m==10e3)
        save(['MNIST_train_Part' int2str(n/10e3)],'set');
        clear set
        set=zeros(10e3,28*28);
    end

    
    image_start= image_start+ 28*28;

    
end

fclose(fid)