function y=PointMeans(Stroke)

x=Stroke(:,1);
y=Stroke(:,2);

meanx=mean(x);
meany=mean(y);
variancey=var(y);

xFeat=(x-meanx)/variancey;
yFeat=(y-meany)/variancey;

y=[xFeat ; yFeat];