function f= Num_of_Crossings(I)

I=dither(I(5:24,5:24));

f=[sum(abs(diff(I,1,1)),1) sum(abs(diff(I,1,2)),2)'];
f(f>10)=10;
f=f/10;

