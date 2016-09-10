function I=h_image(filepath)
imagen=imread(filepath,'jpg');
I=imagen;
I=im2double(I);
dim = ndims(I);
if(dim == 3)
    %Input is a color image
    I = rgb2gray(I);
end