%originalPAW=PAW;
%%%% PAWPoints x,y (stroke points)
 
%%% draw line between each two points 
for i=1:length(PAWPoints(:,1))-1
    PAW=linept(PAW,PAWPoints(i,1),PAWPoints(i,2),PAWPoints(i+1,1),PAWPoints(i+1,2));
end

%%% Paw is the final image....
%% aftter this write this 
imwrite(PAW,'x.bmp','.bmp');
imread('');


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
L(1)=0;
for i=2:length(newPAWPointsWOResampling(:,1))
    L(i)=L(i-1)+sqrt((newPAWPointsWOResampling(i,1)-newPAWPointsWOResampling(i-1,1))^2+(newPAWPointsWOResampling(i,2)-newPAWPointsWOResampling(i-1,2))^2);
end

%NPoints=number of disired points - 1 
a= (L(length(newPAWPointsWOResampling(:,1)))/NPoints);
[newPAWPoints]=resam(newPAWPointsWOResampling,a);
