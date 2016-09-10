% 
% clear
% clc
% 
% fid= fopen('D:\MNIST\train-images.idx3-ubyte','r');
% A=fread(fid);
% fid2=fopen('D:\MNIST\train-labels.idx1-ubyte','r');
% B=fread(fid2);
% 


% means= cell(6,10);
% for k=1:6
%     for i=1:10
%         means{k,i}=zeros(28,28);
%     end
% end
% 
% count=zeros(10,1);
% k=1;
% start_of_row=17;
% label_index=9;
% for m=1:60e3
%     if(mod(m,100)==0)
%         m
%     end
%     I=zeros(28,28);
% 
%     for n=1:28
%         I(n,1:end)=A(start_of_row:start_of_row+27);
%         start_of_row=start_of_row+28;
%     end
%     d=B(label_index);
%     label_index= label_index+1;
% 
%     I=(double(I))/255;
% 
%     count(d+1)=count(d+1)+1;
%     means{k,d+1}= means{k,d+1}+I;
% 
%     if(mod(m,10e3)==0)
%         
%         for i=0:9
%             means{k,i+1}=means{k,i+1}/count(i+1);
%         end             
%         k=k+1;
%         count=zeros(10,1);
%    end
% 
% end


covs=cell(10,1);
for d=0:9
    covs{d+1}=zeros(28,28);
end

latin_scattering_measurements=[];

count=zeros(10,1);
k=1;
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

    count(d+1)=count(d+1)+1;
    covs{d+1}= covs{d+1} + (means{k,d+1}-I).^2;

    if(mod(m,10e3)==0)
        
        covs_sum=zeros(size(covs{1}));
        for i=0:9
            covs{i+1}=covs{i+1}/count(i+1);
            covs_sum=covs_sum+covs{i+1};
        end
        covs_sum=sum(sum(covs_sum))/10;
        
        latin_scattering_measurements=[latin_scattering_measurements, covs_sum];
        
        k=k+1;
        
        for i=1:10
            covs{i}=zeros(28,28);
        end

        count=zeros(10,1);

   end

end


save latin_scattering_measurements latin_scattering_measurements










