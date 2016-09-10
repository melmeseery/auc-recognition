function [BW,h,w]=PreProcessStrokeOffline(Stroke,si)



 x=Stroke(:,1);
   y=Stroke(:,2);
   %sm=SmoothPoints(Stroke,2,2);
   %   I=Stroke2Image(uint16(sm));
  I=Stroke2Image(Stroke);
   
    se = strel('square', 5);
   I2 = imdilate(I,se);   
    % I2 = imclose(I,se);   
   [h w]=size(I2);
   
   
    B =   imresize(I2, [si si],'bicubic');
    level = graythresh(B);
    BW = im2bw(B,level);
    
    