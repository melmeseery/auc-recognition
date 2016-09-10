function [DataFinal] =replace(Data,removeFrom,Addto)

% i=indeces(:,1);
% j=indeces(:,2);
removeFrom
for i=1:length(removeFrom)
    %i
    %r=removeFrom(i,1)
    %c=removeFrom(i,2)
  temp{i}=Data{removeFrom(i,1)}{removeFrom(i,2)};
  Data{removeFrom(i,1)}{removeFrom(i,2)}=[];
end 


for n=1:length(temp)
     Data{Addto(n)}= [    Data{Addto(n)} ; temp(n)];
end 
DataFinal=Data;