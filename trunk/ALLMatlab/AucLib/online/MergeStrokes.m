function StrokeNew=MergeStrokes(Stroke1,Stroke2)


StrokeNew=[Stroke1; Stroke2;];

[r c]=size(StrokeNew)
if (c==2)
    StrokeNew=addPinUpColumn( StrokeNew,length(Stroke1(:,1)));
    
elseif(c>2)
    StrokeNew (length(Stroke1(:,1)),3)=1;
    
end 
 

end 