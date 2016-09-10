function f=TotalNumOfTrnsFE(I)

[r c]=size(I);

x1=0;
x2=0;
counter=1;
avg_hole_length=0;
for n=1:r
    L=bwlabel(I(n,:));
    x1=x1+max(max(L));
        if(max(max(L))==2)
            avg_hole_length=avg_hole_length+ (find(L==2,1,'first')-find(L==1,1,'last'))/c;
            counter=counter+1;
        end
end
x1=x1/(2*r);

[height width]=size(I);
I=I(:,uint8(floor(width/2)):end);
P=regionprops(double(I),'Image');
if(isempty(P))
    x2=0;
else
    I=P.Image;
    [r c]=size(I);
    for n=1:r
        L=bwlabel(I(n,:));
        x2=x2+max(max(L));
            if(max(max(L))==2)
                avg_hole_length=avg_hole_length+ (find(L==2,1,'first')-find(L==1,1,'last'))/c;
                counter=counter+1;
            end
    end
end
x2=x2/(2*r);

avg_hole_length= avg_hole_length/counter;
f= [x1; x2; avg_hole_length];