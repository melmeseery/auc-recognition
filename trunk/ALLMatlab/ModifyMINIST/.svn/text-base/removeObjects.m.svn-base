function [Im]=removeObjects(I,size)
%%
%This function  remove object that are smaller than size from a binray image I 
% the function first create labels of parts of the image 
% then in each labeled image size is computed if the 
%
%
%%
%create a figure to display 
%figure 
%create the labeled version of the image 
L = bwlabel(I);
%compute the centroids of the 
s = regionprops(L, 'Centroid');
%calculate the area of the group
A=regionprops(L,'Area');
% show the image before processing 
% imshow(I);
% title('Objects and their Bounding Box ')
%maximum area 
  maxA=0;
%index of maixm area   
  maxAi=1;
       %added so every drawn thing will be drawn in the same image .  
    %    hold on;
   removedRegions=[];
   addedRegions=[];
%for loop from 1 till number of resions found in the iamge
for k = 1:numel(A)
   % c = s(k).Centroid;
  %  Bt=B(k).BoundingBox;
    %check which is maximum area 
    Ak=abs(A(k).Area);
    if (Ak<=size) %if the size of the current region is less than size 
      
        % add the region (label image) to the regions to remove.    
       % disp('the are is smaller than size ')
         removedRegions=[removedRegions   k];
     
    end
     if (Ak>size)
        %  disp('the are is larger than size ')
        addedRegions=[addedRegions k];
end 
    
    if (maxA<Ak)
        maxA=Ak;
        maxAi=k;
    end 
    
    
%     text(c(1), c(2), sprintf('%d', k), ...
%         'HorizontalAlignment', 'center', ...
%         'VerticalAlignment', 'middle');
%    
%     x=Bt(1,1);
%     y=Bt(1,2);
%     w=Bt(1,3);
% %     h=Bt(1,4);
%     
%     
%     rectangle('Position',[x,y,w,h],'EdgeColor','r')
end
%hold off
%figure
%removedRegions;
%now loop on the size of removed region to null ist image and 
% add all other labled together. 

Im=I;
maxplot=numel(removedRegions);
k=1;
for j=1 :numel(removedRegions)
    
   label=removedRegions(j);
 %get the the object with label to be removed. 
   imLabeled= (L ==label);
%    
%    subplot(maxplot,2,k) 
%     imshow(imLabeled)
  % inverse the object image then use logial and operator to remove it from
  % the orignal image. 
   Im=Im&~imLabeled;
   
%    subplot(maxplot,2,k+1) 
%     imshow(Im)
%    k=k+2;
end 


% Ob=B;
% largest=(L==maxAi);