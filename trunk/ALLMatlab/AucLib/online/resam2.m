function [newPAWPoints]=resam(newPAWPointsWOResampling, alfa)

PAWPoints=newPAWPointsWOResampling.*100;
L(1)=0;
for i=2:length(PAWPoints(:,1))
    L(i)=L(i-1)+sqrt((PAWPoints(i,1)-PAWPoints(i-1,1))^2+(PAWPoints(i,2)-PAWPoints(i-1,2))^2);
end
% alfa
% L(length(PAWPoints(:,1)))
m=round((L(length(PAWPoints(:,1)))/alfa)); %Number of output points without first point
% m=50;
newPAWPoints(1,:)=PAWPoints(1,:);

j=2;
for i=1:m-1
    while (L(j)<i*alfa)
        j=j+1;
    end
    c=(i*alfa-L(j-1)) / ((L(j)-L(j-1)));
    newPAWPoints(i+1,1)=PAWPoints(j-1,1)+(PAWPoints(j,1)-PAWPoints(j-1,1))*c;
    newPAWPoints(i+1,2)=PAWPoints(j-1,2)+(PAWPoints(j,2)-PAWPoints(j-1,2))*c;
end

newPAWPoints(m+1,:)=PAWPoints(length(PAWPoints(:,1)),:);
newPAWPoints=(newPAWPoints./100);


% newPAWPoints(:,1)=(newPAWPoints(:,1)./10);
% newPAWPoints(:,2)=(newPAWPoints(:,2)./10);


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
% figure
% plot(oldL)
% hold on;
% plot(newL,'r');










% %Eman Thesis
% [newX newY] = solve('previousSUM-margin+sqrt( (x-PAWPoints(i,1))^2 +
% (y-PAWPoints(i,2))^2 )','((x-PAWPoints(i,1))/(y-PAWPoints(i,2)))-((PAWPoints(i+1,1)-PAWPoints(i,1))/(PAWPoints(i+1,2)-PAWPoints(i,2)))','x, y');
% newX=double(newX);
% newY=double(newY);



end












