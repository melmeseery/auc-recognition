% clear 
% clc
% 
% fid= fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\train-images.idx3-ubyte','r');
% A=fread(fid);
% 

%%%%%%%%%%%%%%%%%%%%%%%
%get paths 
[trainCounts,testCount]=getMaxSampleCount();


%%%%%%%%%%%%%%%%%%%%%%%%

set=zeros(testCount,28*28);
image_start=17;
for n=1:trainCounts
    if(mod(n,100)==0)
        n
    end
    m= mod(n,testCount);
    if(m==0)
        m=testCount;
    end
    set(m,:)=A(image_start:image_start+28*28-1)'; 
    if(m==testCount)
        save(['MNIST_train_Part' int2str(n/testCount)],'set');
        clear set
        set=zeros(testCount,28*28);
    end
    image_start= image_start+ 28*28; 
end
fclose(fid)