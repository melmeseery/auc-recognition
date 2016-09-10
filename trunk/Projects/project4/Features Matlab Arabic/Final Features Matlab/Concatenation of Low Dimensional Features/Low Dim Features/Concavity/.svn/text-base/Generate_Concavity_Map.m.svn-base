function directional_map= Generate_Concavity_Map(I)

I= dither(I);
directional_map= zeros(size(I));

[Background_x Background_y]= find(I==0);

for n=1:length(Background_x)
    x=Background_x(n);
    y=Background_y(n);
    upper=I(1:x,y);
    lower=I(x:end,y);
    right=I(x,y:end);
    left=I(x,1:y);
    
    directional_code=~[isempty(find(upper==1)) isempty(find(lower==1)) isempty(find(right==1)) isempty(find(left==1))];
    
    directional_map(x,y)= sum(directional_code.*2.^(0:3));
    
end


