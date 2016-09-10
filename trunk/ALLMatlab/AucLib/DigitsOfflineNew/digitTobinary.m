function  lab=digitTobinary(labels)
cat=max(labels);
sizel=length(labels)/cat;
% sizel=50;
% cat=4;
lab=[];
for d=1:cat
s= zeros(1,cat);

a=repmat(s,sizel,1);

a(:,d)=1;

lab=[lab ; a];
end