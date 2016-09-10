function [DataFinal,NewLabel]=addMissingClasses(Data, Label, missing ,max)


% i=indeces(:,1);
% j=indeces(:,2);

for i=1:max
    
    DataFinal{i}={ { } } ;
    
end 

for i=1: length(Label)
    
    DataFinal{Label(i)+1}=Data{i};
    
end 
% for i=1:length(missing)
%   Data{missing(i)+1}=
% end 

f=length(Label)+length(missing);
NewLabel=zeros(1,f)';
for i =1:length(Label)
NewLabel(Label(i)+1)=Label(i);
end 
for i =1:length(missing)
NewLabel(missing(i)+1)=missing(i);
end

%DataFinal=Data;