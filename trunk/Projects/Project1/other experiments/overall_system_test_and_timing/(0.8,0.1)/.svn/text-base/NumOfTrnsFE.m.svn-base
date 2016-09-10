function x=NumOfTrnsFE(I,r,c)
%This function scales the image I to rxc size then calculates number of
%objects in each row and number of objects in each column. Such numbers
%forms a feature vector x.
%Note: I has to be inverted image of the character.

% T=maketform('box',size(I),[1 1],[r c]);
% I=imtransform(I,T,'Size',[r c]);

I=imresize(I,[r c],'nearest');

x=[];
for n=1:r
    row=I(n,:);
    L=bwlabel(row);
    [number_of_objects index]=max(L);
    x=[x; number_of_objects];
end
for m=1:c
    column=I(m,:);
    L=bwlabel(column);
    [number_of_objects index]=max(L);
    x=[x; number_of_objects];
end

x=x/3;


    
    

