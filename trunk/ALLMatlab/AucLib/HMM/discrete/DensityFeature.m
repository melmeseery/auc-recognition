function  Feat =  DensityFeature(I,NumberOfZones)
% compute density function on each vertical split....
%in all the density of black pixels... 
%in each column % density of black pixels. 


Ibw=I>0.025;
blacks=sum(sum(Ibw));
[h,w]=size(I);
%white=(w*h)-black;
percent=blacks/(w*h);
%%% also make
columns=sum(Ibw); % this will have the sum at each column 
rows= sum(Ibw');
halfw=floor(length(columns)/2);
halfh=floor(length(rows)/2);



halfupC=sum(columns(1:halfw))/(h*halfw);
halfdC=sum(columns(halfw:end))/(h*(length(columns)-halfw));



halfupd=sum(rows(1:halfh))/(w*halfh);

halfdd=sum(rows(halfh:end))/(w*halfh);

Feat=[percent ; halfupC; halfdC ; halfupd; halfdd ];
Feat=Feat+1;


