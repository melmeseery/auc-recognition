function [Ob,largest]=getObjects(I)

%%
%%
figure 
L = bwlabel(I);
s = regionprops(L, 'Centroid');
B=regionprops(L,'BoundingBox');
A=regionprops(L,'Area');
imshow(I);
title('Objects and their Bounding Box ')
%maximum area 
  maxA=0;
%index of maixm area   
  maxAi=1;
        
        hold on;


for k = 1:numel(s)
    c = s(k).Centroid;
    Bt=B(k).BoundingBox;
    %check which is maximum area 
    Ak=abs(A(k).Area);
    if (maxA<Ak)
        maxA=Ak;
        maxAi=k;
    end 
    text(c(1), c(2), sprintf('%d', k), ...
        'HorizontalAlignment', 'center', ...
        'VerticalAlignment', 'middle');
   
    x=Bt(1,1);
    y=Bt(1,2);
    w=Bt(1,3);
    h=Bt(1,4);
    
    
    rectangle('Position',[x,y,w,h],'EdgeColor','r')
end


hold off

Ob=B;
largest=(L==maxAi);