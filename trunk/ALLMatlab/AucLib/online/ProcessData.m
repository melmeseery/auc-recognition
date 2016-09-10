function  DataFinal=ProcessData(Data,MergingStrokes,Deletes,Display,Transpose)


% if (r==1)
%      Data{indeces(1)}{indeces(2)}=[];
% else 
% for i=1:length(indeces)
%   Data{indeces(i,1)}{indeces(i,2)}=[];
% end 
% end

% first merge strokeess  add it to end 
ind =Deletes;


len=length(MergingStrokes);
 StrokeNew=[];
for i=1:len
    md=MergingStrokes{i};
    [n  c]=size(md);
    DigitNumber=md(1,1);
    if (n==2)
        Stroke1=Data{md(1,1)}{md(1,2)};
        Stroke2=Data{md(2,1)}{md(2,2)};
        StrokeNew= MergeStrokes(Stroke1,Stroke2)   
    else 
    
       StrokeNew=Data{md(1,1)}{md(1,2)};
       for s=2:n
         Stroke1=Data{md(n,1)}{md(n,2)};
         StrokeNew= MergeStrokes( StrokeNew,Stroke1)    
       end 
   
    end 
    % adding the new stroke to the end of the the new strokes... 
             Data{DigitNumber}{length(Data{DigitNumber})+1}=StrokeNew;
               
               %adding the merged strokes from the data by adding it to
               %incdexs
     ind = [ ind  ; md;]
  
end 



% remove 
DataFinal=removeStrokeFromData(Data,ind , Display, Transpose)