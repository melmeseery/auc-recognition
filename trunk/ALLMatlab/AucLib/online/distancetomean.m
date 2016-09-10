function [ang fcos fsin f2 dista]=distancetomean(data)
c=data(:,1)+sqrt(-1).*data(:,2);
dist=abs(c);
dist=dist;
ang=angle(c);
ang=ang./pi;
fcos=cos(ang);
fsin=sin(ang);
f2=[fcos ; fsin];
dista=abs(c);

