%%%originalPAW=PAW;
%%%% PAWPoints x,y (stroke points)
function im=Stroke2Image(Stroke)
%Stroke=Strokes{6}';



%tempx=Stroke(:,1);
%tempy=Stroke(:,2);
%Stroke(:,1)=tempy;
%Stroke(:,2)=tempx;
if (length(Stroke(:,1))<=4)
    %if (Stroke(1,1)==Stroke(2,1))
        Stroke(length(Stroke(:,1))+1,1)=Stroke(1,1)+1;
        Stroke(length(Stroke(:,1))+1,1)=Stroke(1,1)+1;
        
        Stroke(length(Stroke(:,2))+1,2)=Stroke(1,2)+1;
        Stroke(length(Stroke(:,2))+1,2)=Stroke(1,2)+1;
        
          Stroke(length(Stroke(:,1))+1,1)=Stroke(1,1)+1;
        Stroke(length(Stroke(:,1))+1,1)=Stroke(1,1)+1;
        
        Stroke(length(Stroke(:,2))+1,2)=Stroke(1,2)+1;
        Stroke(length(Stroke(:,2))+1,2)=Stroke(1,2)+1;
        
    %end 
end 



lmaxX=max(Stroke(:,1));
lminX=min(Stroke(:,1));

lmaxY=max(Stroke(:,2));
lminY=min(Stroke(:,2));

sX=lmaxX-lminX;
sY=lmaxY-lminY;

 PAW=zeros(sX,sY);
 
 %maxY=max(Stroke(:,2)-lminY)+2;
 
%%% draw line between each two points 
for i=1:length(Stroke(:,1))-1
    PAW=linept(PAW,Stroke(i,1),Stroke(i,2),Stroke(i+1,1),Stroke(i+1,2),lminX-1,lminY-1);
end

%%% Paw is the final image....
%% aftter this write this 
%imwrite(~PAW,'x.bmp');
%im=imread('x.bmp');
im=rot90(PAW);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
% L(1)=0;
% for i=2:length(newPAWPointsWOResampling(:,1))
%     L(i)=L(i-1)+sqrt((newPAWPointsWOResampling(i,1)-newPAWPointsWOResampling(i-1,1))^2+(newPAWPointsWOResampling(i,2)-newPAWPointsWOResampling(i-1,2))^2);
% end
% 
% %NPoints=number of disired points - 1 
% a= (L(length(newPAWPointsWOResampling(:,1)))/NPoints);
% [newPAWPoints]=resam(newPAWPointsWOResampling,a);
