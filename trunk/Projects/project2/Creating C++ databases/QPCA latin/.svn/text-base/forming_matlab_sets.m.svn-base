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
%%%%%%%%%%%%%%%%%%%%%%%
%get paths 
databpath=getADDataBasePath(1);
[trainCounts,testCount]=getMaxSampleCount();
Minist_test=[databpath '\t10k-images.idx3-ubyte' ]
%%%%%%%%%%%%%%%%%%%%%%%%
fid= fopen(Minist_test ,'r');
A=fread(fid);
testing_set= zeros(28*28,testCount);
image_start=17;
for n=1:testCount
    if(mod(n,100)==0)
        n
    end
    testing_set(:,n)= double(A(image_start:image_start+28*28-1))/255;
    image_start= image_start+ 28*28;    
end

save testing_set testing_set
