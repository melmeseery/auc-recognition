function mod_I=elastic_distortion(I,alpha,sigma)
% This function accepts an image (I) as an input, and the parameters alpha
% and sigma, which specify the strength of distortion and the variance of
% the Gausian filter used 
no_zeros=6;% 10 or 15
h=Generate_Gaussian_Mask([28,28],sigma);
x_shift=rand(28,28)*2-1;
y_shift=rand(28,28)*2-1;
x_shift=conv2(x_shift,h,'same')*alpha;
y_shift=conv2(y_shift,h,'same')*alpha;
x0=floor(x_shift);
y0=floor(y_shift);
dx=x_shift-x0;
dy=y_shift-y0;
I=[zeros(no_zeros,28+2*no_zeros);[zeros(28,no_zeros),I,zeros(28,no_zeros)];zeros(no_zeros,28+2*no_zeros)];
mod_I=ones(28,28);
for i=1:28
    for j=1:28
        mod_I(i,j)=I(x0(i,j)+i+no_zeros+1,y0(i,j)+j+no_zeros+1)*(1-dx(i,j))*(1-dy(i,j))+...
            I(x0(i,j)+i+no_zeros+1,y0(i,j)+j+no_zeros+1)*dx(i,j)*(1-dy(i,j))+...
            I(x0(i,j)+i+no_zeros+1,y0(i,j)+j+no_zeros+1)*dy(i,j)*(1-dx(i,j))+...
            I(x0(i,j)+i+no_zeros+1,y0(i,j)+j+no_zeros+1)*dx(i,j)*dy(i,j);
    end
end