function f= Down_Sample(I)

I=I(5:24,5:24);

counter=1;
f=zeros(1,25);
for x=1:4:17
    for y=1:4:17
        f(counter)= mean(mean(I(x:x+3,y:y+3)));
        counter= counter+1;
    end
end
