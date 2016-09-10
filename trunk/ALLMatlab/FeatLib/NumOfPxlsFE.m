function x=NumOfPxlsFE(I,r,c)
%This function extracts partition the image I into r vertical and c
%horizontal strips. Intersection between vetical and horizontal strips
%forms rxc celss. The area of on pixels in each cell divided by total
%number of on pixels in I is calculated and arranged in the feature vector
%x taking column by column up down and spanning each column from left to
%right.
%Note: I has to be inverted image of the character.

[a b]=size(I);
x_spacing=floor(b/c);
y_spacing=floor(a/r);

x=[];
for n=1:r
    for m=1:c
        cell=I((n-1)*y_spacing+1:n*y_spacing,...
            (m-1)*x_spacing+1:m*x_spacing);
%         f=regionprops(uint8(cell),'Area')
        
        x=[x; bwarea(cell)];
    end
end

% f=regionprops(uint8(I),'Area');
x=x/bwarea(I);







