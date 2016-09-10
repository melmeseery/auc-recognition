
 clc
%read and show image 
%I=imread('writer002_pass06_digit9.bmp');
I=imread('test6.bmp');
size=15;
 [Im]=removeObjects(~I,size)
 figure 
 imshow(Im);
%getBiggestObject(~I)

[Bounding, largest]=getObjects(~I);
figure 
subplot(2,1,1);
imshow(largest)
title('Largest based on area ');
subplot(2,1,2)
I2 = imfill(largest,'holes');
imshow(I2)
getObjectsBoundry(~I);




%writer601_pass02_digit7.bmp
figure;
subplot(3,1,1);
imshow(I,'InitialMagnification', 250)
title('orignal image');


% %fill the isolated interior pixels (individual 0's that are surrounded
% % by 1's), such as the center pixel in this pattern.
% %---note the ~ inverse because in the image the 1 means backgroud and 0 foreground it must
% %be inverted to correctly fill the digit. 
% bw2=bwmorph(~I,'fill');
%  
% %inverse the image again to matcht the format
% BW=~bw2;
%display image 
 BW=removeHoles(I,1);
subplot(3,1,2);
imshow(BW,'InitialMagnification', 250);

title('After removing holes');

% %%%%%%%%%%%%%%%%another method 
% %Sets a pixel to 1 if five or more pixels in
% %its 3-by-3 neighborhood are 1's; otherwise, it sets the pixel to 0.
% 
% bw3=bwmorph(I,'majority');  %output may lead to thining the image so
% %bw4=bw3;
% bw4=bwmorph(bw3,'thin',1); % add an outer layer of 1 pixel to return as orginal. 
 bw=removeHoles(I,2);
subplot(3,1,3);
imshow(bw ,'InitialMagnification', 250);


 


