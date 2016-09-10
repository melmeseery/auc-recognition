clc
clear all
close all
load DataTest

load n
net=net1;
klkl=11;
h=0;
for i=2:length(Data)
    h=h+1;
    data{h}=Data{i};
end

for i=1:length(data)
    h=0;
    for j=1:length(data{i})
        if ~isempty(data{i}{j})
       if length(data{i}{j}(:,1))>3
           h=h+1;
           fdata{i}{h}=data{i}{j};
            feature1{i}{h} = mixing([data{i}{j}(:,1) data{i}{j}(:,2)],klkl);
               feature{i}{h} =  [feature1{i}{h};addedfeatures([data{i}{j}(:,1) data{i}{j}(:,2)])];
%            feature{i}{h}=chainCode64(data{i}{j})';
       end
        end
    end
end


fdata{10}=Data{1};
data{10}=Data{1};


for j=1:length(feature)
    j
  for i=1:length(feature{j})
% %%%%%%%%%%     a=check_alf_delayed1(fdata{j}{i}); PUT YOUR FUNCTION HERE
% , if you return zero it counted,else i will go for test 1:9..
%       if a==0
%           maxa(i)=10;
%       
%       else
      
    x=[];
    for f=1:9
        
        for ll=f:9
             if (f == ll)
            continue
             end
   x(f,ll)=svmclassify(net{1}(f,ll),feature{j}{i}') ;
       
        end
    end
    
    for gy=1:length(x(:,1))
        
      in(gy)=length(find(x(gy,:)==gy))+length(find(x(:,gy)==gy));  
    end
    in(gy+1)=0+length(find(x(:,gy+1)==gy+1)); 
zzz=find(in==max(in));
 if length(zzz)>1
     trbt=sort(zzz);
     
  s=svmclassify(net{1}((trbt(1)),(trbt(2))),feature{j}{i}') ;   
     maxa(i)=s;
 elseif length(zzz)==1 
     s1=zzz;
     in(find(in==max(in)))=0;
     s2=find(in==max(in));
     trbt2=sort([s1 s2]);
     
     
     if length(in==trbt2(2))-length(trbt2(1))<=1
     sx=svmclassify(net{1}((trbt2(1)),(trbt2(2))),feature{j}{i}') ;
     maxa(i)=sx;
     else
         maxa(i)=zzz(1);
     end
 else
     maxa(i)=zzz(1);
 end
%   end
  end
 
  zzz=[];
  true(j)=length(find(maxa==j));
  total(j)=length(maxa);

maxa=[];
end
for i=1:length(data{10})
result(i)=check_alf_delayed1(data{10}{i});
end
true(10)=length(find(result==0));
total(10)=length(data{10});
sum(true)/sum(total)