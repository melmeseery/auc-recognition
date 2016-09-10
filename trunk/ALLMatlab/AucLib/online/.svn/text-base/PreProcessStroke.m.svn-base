
function [ResampledOrginal,ResampledTransformed]=PreProcessStroke(Stroke, NPoints,ReSample)

%%%% normalize the stroke...
%xmean=mean(Stroke(:,1));
%ymean=mean(Stroke(:,2));
%%% lets try to 
 
%[Stroke]=preprocessing(Stroke,1, 1);
small=0;
if (length(Stroke(:,1))<=4)
    %if (Stroke(1,1)==Stroke(2,1))
        Stroke(length(Stroke(:,1))+1,1)=Stroke(1,1)+1;
        Stroke(length(Stroke(:,1))+1,1)=Stroke(1,1)+1;
        
        Stroke(length(Stroke(:,2))+1,2)=Stroke(1,2)+1;
        Stroke(length(Stroke(:,2))+1,2)=Stroke(1,2)+1;
    %end 
    small=1;
    
    
    
    
end 


% if(length(Stroke(:,1))<3) % &&If PAW is point on min. than 'minPixels'
% ResampledOrginal=Stroke;
% ResampledTransformed=Stroke;
%     %plot(PAWPoints(:,1),PAWPoints(:,2),'*')
%     return;
% end

minx=min(Stroke(:,1));
miny=min(Stroke(:,2));

Strokel(:,1) =Stroke(:,1)- minx ;
Strokel(:,2) = Stroke(:,2)- miny;

%%%%% This code will do the resampling ...
 L(1)=0;
for i=2:length(Stroke(:,1))
    L(i)=L(i-1)+sqrt((Stroke(i,1)-Stroke(i-1,1))^2+(Stroke(i,2)-Stroke(i-1,2))^2);
end
% 
% %NPoints=number of disired points - 1 

len=L(length(Stroke(:,1)))*100;

if (len==0)
    %%% do something to get
end 

% a=(len/(NPoints-1)) ;
%a= (L(length(newPAWPointsWOResampling(:,1)))/NPoints);
%ResampledTransformed=resam(Strokel,a);
 
 if (ReSample )
      a= (len/(NPoints-1));
    ResampledOrginal=resam(Stroke,a);
    ResampledTransformed=resam(Strokel,a);
 else 
     if (small==1)
          a= (len/(10-1));
         ResampledOrginal=resam(Stroke,a);
         ResampledTransformed=resam(Strokel,a);
          % ResampledTransformed=
     else 
         ResampledOrginal=Stroke;
     ResampledTransformed=Strokel;
     end 
     
   
end
