clear
clc

load means means

diffs=cell(10,10);
euclidean_interclass_dist=zeros(10,10);
for m=0:9
    for n=0:9
        diffs{m+1,n+1}= means{m+1}-means{n+1};
        euclidean_interclass_dist(m+1,n+1)=sqrt(sum(sum(diffs{m+1,n+1}.^2)));
    end
end

arabic_interclass_dist= euclidean_interclass_dist;
save arabic_interclass_dist arabic_interclass_dist

