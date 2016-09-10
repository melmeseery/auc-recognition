% clear
% clc
% 
% training_set=[];
% for n=1:6    
%    load( ['MNIST_train_Part' int2str(n)] );
%    training_set= [training_set double(set)'/255];
% %    input('zeo:')
% end
% 
% save training_set training_set



clear

fid= fopen('C:\Documents and Settings\ezzat\Desktop\MNIST\t10k-images.idx3-ubyte','r');
A=fread(fid);

testing_set= zeros(28*28,10e3);

image_start=17;
for n=1:10e3
    if(mod(n,100)==0)
        n
    end

    testing_set(:,n)= double(A(image_start:image_start+28*28-1))/255;
    
    
    image_start= image_start+ 28*28;


    
end

save testing_set testing_set
