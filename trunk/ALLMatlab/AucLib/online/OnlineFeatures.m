function y=OnlineFeatures(Stroke, varargin)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55555
%This function comptue the features for the  online system. 
%
%
%Stroke
% USE this code if wrting to a file with fixedl lenght... 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
HMM=1; 
%HMM=0;

[Order,norm,Feature,ReSample]=unPackOnlineFeaturesVariables(varargin{:});
Stroke=Stroke;
%Stroke=Stroke';

%if(ReSample)
[ResampledOrginal,ResampledTransformed]=PreProcessStroke(Stroke,norm,ReSample);
%Stroke=ResampledOrginal;
Stroke=ResampledTransformed;
%end



if (Feature==1)
    [chain, norma] =chaincode(Stroke,norm);
    if (HMM)
   y=chain;
  %   y=chain';
 
    else
     y=chain;
    end 
elseif(Feature==2)
       [chain, norma] =chaincode(Stroke,norm);
         if (HMM)
     y=norma;
   %   y=norma';
    else
     y=norma;
         end 
 
elseif(Feature==3)
   [chain,norma]=newchaincode(Stroke,norm,Order);
     
              if (HMM)
     %y=chain;
     y=chain';
    else
     y=chain';
         end 
     
     
elseif(Feature==4)
   [chain,norma]=newchaincode(Stroke,norm,Order);
   
     if (HMM)
     %y=norma';   
     y=norma;
    else
     y=norma;
     end 
 
elseif(Feature==5)
    [alpha Beta]=writingdirection(Stroke);
%    y=[alpha' ; Beta'];
       
     if (HMM)
        %y=[alpha ; Beta];
          y=[alpha'  Beta'];
     else
        y=[alpha' ; Beta'];
     end 
     
     
     
    elseif(Feature==6)
    [alpha Beta]=writingdirection(Stroke);
    
   
      if (HMM)
      % y=[alpha  ];
        y=[alpha'  ];
    else
     y=[alpha'  ];
     end 
     
    
        elseif(Feature==7)
    [alpha Beta]=writingdirection(Stroke);
       if (HMM)
       % y=[Beta  ]; 
        y=[Beta' ] ;

    else
    y=[Beta' ] ;
       end 
elseif (Feature==8)
    [feature,rFSDs]=rfds(Stroke,Order);
      if (HMM)
      %y=feature';  
      y=feature;  
      else
      y=feature;  
      end 
elseif (Feature==9)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
          if (HMM)
          %y=[cos_alphaa' ;cos_betaa'];
          y=[cos_alphaa ;cos_betaa];
          else 
           y=[cos_alphaa ;cos_betaa];    
          end 
    elseif (Feature==10)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
    
    if (HMM)
   % y=[cos_alphaa' ];    
     y=[cos_alphaa ];    

    else 
    y=[cos_alphaa ];    
    end 
        elseif (Feature==11)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
    %%y=[cos_betaa];  
        if (HMM)
      % y=[cos_betaa' ];    
      y=[cos_betaa ]; 
    else 
    y=[cos_betaa ];    
    end 
    
elseif (Feature==12)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
    
        if (HMM)
               y=[sin_alphaa];
               %y=[sin_alphaa'];
               
        else
                y=[sin_alphaa];
        end 
  elseif (Feature==13)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
    
         if (HMM)
               y=[sin_betaa];
      %          y=[sin_betaa'];
         else
      y=[sin_betaa];
        end 
  
    
    
      elseif (Feature==14)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
    
             if (HMM)
                  %y=[sin_alphaa' ; sin_betaa'];  
                   y=[sin_alphaa   sin_betaa]; 
             else
                y=[sin_alphaa ; sin_betaa]; 
             end 
          elseif (Feature==15)
              %Stroke
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
    
    
      if (HMM)  y=[alphaa' ;betaa'];  else y=[alphaa ;betaa]; end 
      elseif (Feature==16)
    [ang fcos fsin f2 dista]=distancetomean(Stroke);
     if (HMM)
           %y=  [ang'; fcos'; fsin'; dista'];
            y=  [ang'   fcos'   fsin'   dista'];
     else
            y=  [ang;  f2 ;dista];
     end
elseif(Feature==17)
    [f fcos fsin f2]=distanceforall(Stroke); 
    if (HMM)
           %y=  [f' ; fcos'; fsin']; 
              y=  [f'   fcos'  fsin']; 
    else
     y=  [f ; f2];
    end 
   elseif(Feature==18) 
       [feature]=chainCode64(Stroke);
       
        if (HMM)
    y=feature';   % y=feature;
         else
       y=feature';
       
         end 
  elseif (Feature==19)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
   
    
       if (HMM)
     %y=[cos_alphaa' ; sin_alphaa' ];
          y=[cos_alphaa   sin_alphaa ];
       else 
     y=[cos_alphaa ; sin_alphaa ];
       end 
  %elseif(Feature==19) 
    elseif (Feature==20)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
 
      dalpha=alphaa(1:end-1)-alphaa(2:end);%% this is delta alpha and i can do the same for 
      cos_dalpha=cos(dalpha);
      sin_dalpha=sin(dalpha);
      
      %y=[ dalpha ; cos_dalpha ;    sin_dalpha ];
      
          if (HMM)
                 %y=[ dalpha' ; cos_dalpha' ;    sin_dalpha' ];
                       y=[ dalpha   cos_dalpha     sin_dalpha ];
          else
                 y=[ dalpha ; cos_dalpha ;    sin_dalpha ];
          end 
      
        elseif (Feature==21)
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
   
    
       if (HMM)
           y=[cos_betaa   sin_betaa ];
           %y=[cos_betaa'; sin_betaa' ];
       else y=[cos_betaa; sin_betaa ];
      end 
      
      elseif (Feature==22)
 
       featY=PointMeans(Stroke);
       
       if (HMM) 
           y=featY;
          %    y=featY';
       else
       
       y=featY;
       end 
       
        elseif (Feature==23)
              %Stroke
    [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke);
    
    
      if (HMM) 
            % y=[cos_alphaa' ;sin_alphaa'  ;cos_betaa'; sin_betaa' ;alphaa' ;betaa'];  
          y=[cos_alphaa  sin_alphaa cos_betaa  sin_betaa alphaa betaa ];  
      else y=[cos_alphaa; sin_alphaa;  cos_betaa;sin_betaa ;alphaa ;betaa ]; 
      end 
      
      
      
 elseif(Feature>=100)
     Feat=Feature-100;
     NumberOfSplits=Order;
     OverlapSize=norm;
     %Order,norm,Feature,ReSample
      image=Stroke2Image(Stroke);
       y= hmmOfflineFeatures(image,'Feature',Feat,'NumberOfSplits',NumberOfSplits,'OverlapSize',OverlapSize);
       
      
      
end 

