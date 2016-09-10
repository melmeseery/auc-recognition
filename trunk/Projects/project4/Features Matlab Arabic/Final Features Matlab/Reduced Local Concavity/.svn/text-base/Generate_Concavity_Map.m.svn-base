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
    
    if(directional_code==[1 1 1 1])%closed from all directins
        directional_map(x,y)=1;
    elseif(directional_code==[0 1 1 1])% open up
        directional_map(x,y)=2;
    elseif(directional_code==[1 0 1 1])% open down
        directional_map(x,y)=3;
    elseif(directional_code==[1 1 0 1])% open right
        directional_map(x,y)=4;
    elseif(directional_code==[1 1 1 0])% open left
        directional_map(x,y)=5;
    elseif(directional_code==[0 1 0 1])% open up and right
        directional_map(x,y)=6;
    elseif(directional_code==[0 1 1 0])% open up and left
        directional_map(x,y)=7;
    elseif(directional_code==[1 0 0 1])% open down and right
        directional_map(x,y)=8;
    elseif(directional_code==[1 0 1 0])% open down and left
        directional_map(x,y)=9;
    end
    
   
end


