
% smothing order from 2 to 100 but the larger the number the distorted the
% image
% the minPixel will return if less than this number . 
function [newPAWPointsWOResampling]=preprocessing(PAWPoints,smoothingOrder, minPixels)
%Alfa is the New Distance betwean each pair of points (Resampling)


% Special Cases

if(length(PAWPoints(:,1))<minPixels) % &&If PAW is point on min. than 'minPixels'
    newPAWPoints=PAWPoints;
    newPAWPointsWOResampling=PAWPoints;
    %plot(PAWPoints(:,1),PAWPoints(:,2),'*')
    return;
end


% Smooting

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
