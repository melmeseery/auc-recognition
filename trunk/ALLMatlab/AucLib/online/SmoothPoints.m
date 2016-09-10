function [newPAWPoints]=SmoothPoints(PAWPoints, smoothingOrder, minPixels)
%Alfa is the New Distance betwean each pair of points (Resampling)
% PAWPoints=10*PAWPoints;
%==========================================================================
% Special Cases
%==========================================================================
if(length(PAWPoints(:,1))<minPixels) % &&If PAW is point on min. than 'minPixels'
    newPAWPoints=PAWPoints;
    %plot(PAWPoints(:,1),PAWPoints(:,2),'*')
    return;
end

%==========================================================================
% Smooting
%==========================================================================
n=smoothingOrder;
ak=(1/(2*n+1))*ones((2*n+1),1);

for i=1:n
    PAWPoints=[PAWPoints(1,:); PAWPoints; PAWPoints(length(PAWPoints(:,1)),:)];
end
for i=n+1:length(PAWPoints(:,1))-n
	newPAWPoints(i,1)=(sum(ak.*PAWPoints(i-n:i-n+length(ak)-1,1)));
    newPAWPoints(i,2)=(sum(ak.*PAWPoints(i-n:i-n+length(ak)-1,2)));
end
for i=1:n
    newPAWPoints(1,:)=[];
end

newPAWPointsWOResampling=newPAWPoints;
PAWPoints=newPAWPoints;
%==========================================================================
% Resampling
%==========================================================================
% L(1)=0;
% for i=2:length(PAWPoints(:,1))
%     L(i)=L(i-1)+sqrt((PAWPoints(i,1)-PAWPoints(i-1,1))^2+(PAWPoints(i,2)-PAWPoints(i-1,2))^2);
% end
% m=round(L(length(PAWPoints(:,1)))/alfa); %Number of output points without first point
% 
% newPAWPoints(1,:)=PAWPoints(1,:);
% 
% j=2;
% for i=1:m-1
%     while (L(j)<i*alfa)
%         j=j+1;
%     end
%     c=(i*alfa-L(j-1)) / (L(j)-L(j-1));
%     newPAWPoints(i+1,1)=PAWPoints(j-1,1)+(PAWPoints(j,1)-PAWPoints(j-1,1))*c;
%     newPAWPoints(i+1,2)=PAWPoints(j-1,2)+(PAWPoints(j,2)-PAWPoints(j-1,2))*c;
% end
% 
% newPAWPoints(m+1,:)=PAWPoints(length(PAWPoints(:,1)),:);
% newPAWPoints=round(newPAWPoints);
%==========================================================================
% To check that new distances multiples of alfa
%==========================================================================
% for i=1:length(newPAWPoints(:,1))-1
%     newL(i)=sqrt((newPAWPoints(i,1)-newPAWPoints(i+1,1))^2+(newPAWPoints(i,2)-newPAWPoints(i+1,2))^2);
% end
% for i=1:length(PAWPoints(:,1))-1
%     oldL(i)=sqrt((PAWPoints(i,1)-PAWPoints(i+1,1))^2+(PAWPoints(i,2)-PAWPoints(i+1,2))^2);
% end
% 
% % figure
% % plot(oldL)
% hold on;
% plot(newL,'r');
% keyboard

%==========================================================================
% Eng. Eman Thesis
%==========================================================================
% [newX newY] = solve('previousSUM-margin+sqrt( (x-PAWPoints(i,1))^2 +
% (y-PAWPoints(i,2))^2 )','((x-PAWPoints(i,1))/(y-PAWPoints(i,2)))-((PAWPoints(i+1,1)-PAWPoints(i,1))/(PAWPoints(i+1,2)-PAWPoints(i,2)))','x, y');
% newX=double(newX);
% newY=double(newY);


% newPAWPoints=newPAWPoints/10;












