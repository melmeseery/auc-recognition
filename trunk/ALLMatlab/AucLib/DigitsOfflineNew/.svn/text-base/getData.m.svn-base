function [dataFeatures, labels]=getData(Data, labels)
warning off
disp('  Computing   features.. .....  ');
  i=1;
  dataTrains=[];
  for d=1:10
      Digit=Data{d};
      d
      for n=1:length(Digit)
        im=Digit{n};
         Feat=ComputeFeatures(im);
          dataTrains=[dataTrains; Feat];
           groups(i)=labels{d}{n};
          i=i+1;
      end 
  end
  
  
dataFeatures=dataTrains;
labels=groups;   