function y=Density(I,NumberOfZones)

blacks=sum(sum(I));% number of blacks in the image...
[h,w]=size(I);  % size of imageee....
%white=(w*h)-black;
percent=blacks/(w*h);
%%% also make
%col=sum(I); % this will have the sum at each column 
rows= sum(I')';  % sum of blacks in each row... 


if (NumberOfZones>0 & NumberOfZones<=h)

 hi=floor(h/NumberOfZones)
 
 %if (hi>2)
     for i=1:NumberOfZones
         starti=(i-1)*hi+1;
         endi = (i)*hi;
         sumOfPixels(i)=sum(rows(starti:endi));
         Densityi(i)= sumOfPixels(i)/(hi*w);
     end
     if (endi<h)
         i=i+1;
          starti=endi;
         endi = h;
         sumOfPixels(i)=sum(rows(starti:endi));
         Densityi(i)= sumOfPixels(i)/((h-endi)*w);
     end 
%  else
%      
%      halfh=floor(h/2);
%          for i=1:2
%          starti=(i-1)*halfh+1;
%          endi = (i)*halfh;
%          sumOfPixels(i)=sum(col(starti:endi));
%          Densityi(i)= sumOfPixels(i)/(halfh*w);
%          
%          end
% end 


   y=[percent  Densityi];
   
   %blacks;
   %sumOfPixels;
else
   % blacks;
    y=[percent];
    
end 
     