function y=ExtractRowColumnFeatures(L , rowColumn)

if (rowColumn==1)
[h  w] = size(L);
Feat=sum(L);

y=Feat/h;

elseif (rowColumn==2)
  [h  w] = size(L);
Feat=sum(L');

y=Feat/w;  
    
end 
