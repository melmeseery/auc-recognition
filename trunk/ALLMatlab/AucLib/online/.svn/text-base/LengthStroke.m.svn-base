function [len,Npoints]=LengthStroke(Stroke);


 
    
 len=0;
for i=2:length(Stroke(:,1))
    len=len+sqrt((Stroke(i,1)-Stroke(i-1,1))^2+(Stroke(i,2)-Stroke(i-1,2))^2);
end

Npoints=length(Stroke(:,1));