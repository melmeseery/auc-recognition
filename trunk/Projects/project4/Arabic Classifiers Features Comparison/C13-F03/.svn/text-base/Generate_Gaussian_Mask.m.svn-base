function h =Generate_Gaussian_Mask(mask_size,variance)

h = zeros(mask_size);

mask_center = ceil(mask_size/2);
x0= mask_center(1);
y0= mask_center(2);

for x=1:mask_size(1)
    for y=1:mask_size(2)
        h(x,y)= 1/(2*pi*variance)*exp(-((x-x0)^2+(y-y0)^2)/(2*variance));
    end
end

