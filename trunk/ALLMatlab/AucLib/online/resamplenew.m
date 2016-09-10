function [resampled]=resamplenew(c)
datac=c(:,1)+sqrt(-1).*c(:,2);
difference=sum(abs(datac(1:end-1,:)-datac(2:end,:)));
d=difference/(length(c(:,1))-1);

for jj=1:length(c)
    
    
end
% c=str{1,6};d=10;
i=1;
xx=[c(1,1)];
yy=[c(1,2)];
x1=c(i,1);y1=c(i,2);
x2=c(i+2,1);y2=c(i+1,2);
theta=angle(sqrt(-1)*(y2-y1)+(x2-x1));

if x2>c(i,1)
xx=[xx; x1+d/sqrt(1+tan(theta).^2)];

else
xx=[xx; x1-d/sqrt(1+tan(theta).^2)];
end

if y2>c(i,2)
yy=[yy; y1+d/sqrt(1+(1/tan(theta).^2))];

else
yy=[yy; y1-d/sqrt(1+(1/tan(theta).^2))];
end



i=2;
 while i<length(c(:,1))
    
x1=xx(i);y1=yy(i);
x2=c(i+1,1);y2=c(i+1,2);
theta=angle(sqrt(-1)*(c(i+1,2)-c(i,2))+(c(i+1,1)-c(i,1)));




if x2>c(i,1)
xx=[xx; x1+d/sqrt(1+tan(theta).^2)];

else
xx=[xx; x1-d/sqrt(1+tan(theta).^2)];
end

if y2>c(i,2)
yy=[yy; y1+d/sqrt(1+(1/tan(theta).^2))];

else
yy=[yy; y1-d/sqrt(1+(1/tan(theta).^2))];
end





i=i+1;
 end
 

%  for i=1:length(xx)
%  yyy(i)=[yy(length(xx)-i+1)]
%  end
%  
% 
%  for i=1:length(xx)
%  xxx(i)=[xx(length(xx)-i+1)]
%  end
%   xx = interpft(xx,50);
%   yy=  interpft(yy,50);

% newPAWPointsWOResampling=[xx yy];
% 
% L(1)=0;
% for i=2:length(newPAWPointsWOResampling(:,1))
%     L(i)=L(i-1)+sqrt((newPAWPointsWOResampling(i,1)-newPAWPointsWOResampling(i-1,1))^2+(newPAWPointsWOResampling(i,2)-newPAWPointsWOResampling(i-1,2))^2);
% end
% 
% m=L(length(newPAWPointsWOResampling(:,1)))/100; %Number of output points without first point
% [newPAWPoints]=resam(newPAWPointsWOResampling, ((L(length(newPAWPointsWOResampling(:,1)))/(points-1))));
%resampled=newPAWPoints;
resampled=[xx yy];
%   plot(resampled(:,1),resampled(:,2),'.')