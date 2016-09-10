clear
clc

load means means

diffs=cell(10,10);
euclidean_interclass_dist=zeros(10,10);
for m=0:9
    for n=0:9
        diffs{m+1,n+1}=zeros(28,28);
        f=2;
        diffs{m+1,n+1}=(means{m+1}-means{n+1})*f;
        euclidean_interclass_dist(m+1,n+1)=sqrt(sum(sum(diffs{m+1,n+1}.^2)));
        diffs{m+1,n+1}=(diffs{m+1,n+1}+f)/(2*f);
        
       imwrite(diffs{m+1,n+1},['arabic diffs\' int2str(m) '_' int2str(n) '.bmp'],'bmp');
    end
end
