input('zeo:')

clear
clc

fid= fopen('D:\MNIST\train-images.idx3-ubyte','r');
A=fread(fid);
fid2=fopen('D:\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);



latin_interclass_distance_measurements=[];

means= cell(10,1);
for i=1:10
    means{i}=zeros(28,28);
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

    count(d+1)=count(d+1)+1;
    means{d+1}= means{d+1}+I;

    if(mod(m,10e3)==0)
        
        for i=0:9
            means{i+1}=means{i+1}/count(i+1);
        end
        
        diffs=cell(10,10);
        euclidean_interclass_dist=zeros(10,10);
        for a=0:9
            for b=0:9
                diffs{a+1,b+1}= means{a+1}-means{b+1};
                euclidean_interclass_dist(a+1,b+1)=sqrt(sum(sum(diffs{a+1,b+1}.^2)));
            end
        end
        
        latin_interclass_distance_measurements=[latin_interclass_distance_measurements, sum(sum(euclidean_interclass_dist))];
        
        for i=1:10
            means{i}=zeros(28,28);
        end
        
        count=zeros(10,1);

   end

end


save latin_interclass_distance_measurements latin_interclass_distance_measurements










