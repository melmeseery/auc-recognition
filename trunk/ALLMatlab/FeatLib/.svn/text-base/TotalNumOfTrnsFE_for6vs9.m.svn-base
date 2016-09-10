function f=TotalNumOfTrnsFE(I)

[r c]=size(I);

x1=0;
x2=0;
counter_1=1;
avg_hole_length_1=0;
for n=1:r
    L=bwlabel(I(n,:));
    x1=x1+max(max(L));
    if(max(max(L))==2)
        avg_hole_length_1=avg_hole_length_1+ (find(L==2,1,'first')-find(L==1,1,'last'))/c;
        counter_1=counter_1+1;
    end
end
if(counter_1>0)
    avg_hole_length_1= avg_hole_length_1/counter_1;
end

avg_hole_length_2=0;
counter_2=0;
for m=1:c
    L=bwlabel(I(:,m));
    x2=x2+max(max(L));
    if(max(max(L))==2)
        avg_hole_length_2=avg_hole_length_2+ (find(L==2,1,'first')-find(L==1,1,'last'))/r;
        counter_2=counter_2+1;
    end
end
if(counter_2>0)
    avg_hole_length_2= avg_hole_length_2/counter_2;
end

x1=x1/(2*r);
x2=x2/(2*c);


f=[x1; x2; avg_hole_length_1; avg_hole_length_2];