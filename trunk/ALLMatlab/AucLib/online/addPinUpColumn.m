function StrokeNew=addPinUpColumn(Stroke , locationOfPinUp)

StrokeNew(:,1)=Stroke(:,1);
StrokeNew(:,2)=Stroke(:,2);
StrokeNew(:,3)=0;

if (~isempty(locationOfPinUp))
if (locationOfPinUp~=0)
    
StrokeNew(locationOfPinUp,3)=1;
end 
end 