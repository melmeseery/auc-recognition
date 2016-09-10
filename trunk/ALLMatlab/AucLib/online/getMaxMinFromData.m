
function [maxNpoints ,minNpoints,mean1,variance] = getMaxMinFromData(Data)
Npoints=[];
for d=1:length(Data)
      Digit=Data{d};
      %d
      for n=1:length(Digit)
          
          [r  c]= size(Data{d}{n});
          
          Npoints=[ Npoints  ; r];
          
      end 
      
end 

maxNpoints=max(Npoints);
minNpoints=min(Npoints);

mean1=mean (Npoints);
variance=var(Npoints);

meanN=mean1;

save  DataState  maxNpoints  minNpoints  meanN   variance

