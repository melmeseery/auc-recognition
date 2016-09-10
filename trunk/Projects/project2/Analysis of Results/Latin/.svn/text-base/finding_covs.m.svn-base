clear
clc


fid= fopen('C:\MNIST\train-images.idx3-ubyte','r');
A=fread(fid);
fid2=fopen('C:\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);

load means means

covs=cell(10,1);
for d=0:9
    covs{d+1}=zeros(28,28);
end

count=zeros(10,1);
start_of_row=17;
label_index=9;
for m=1:60e3
    if(mod(m,100)==0)
        m
    end
    I=zeros(28,28);
    
    for n=1:28
        I(n,1:end)=A(start_of_row:start_of_row+27);
        start_of_row=start_of_row+28;
    end
    d=B(label_index);
    label_index= label_index+1;
    
    I=(double(I))/255;
    
    count(d+1)= count(d+1)+1;
    
    covs{d+1}= covs{d+1} + (means{d+1}-I).^2;
    
end

save covs covs

covs_sums=zeros(10,1);
for d=0:9
    covs{d+1}=covs{d+1}/count(d+1);
    covs_sums(d+1)=sum(sum(covs{d+1}));
end

latin_covs_sums=covs_sums;
save latin_covs_sums latin_covs_sums

sum(latin_covs_sums)



