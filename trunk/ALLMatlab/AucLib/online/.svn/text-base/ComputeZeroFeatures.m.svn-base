function feat=ComputeZeroFeatures(Stroke)
load 'DataState' 
[r c]=size(  Stroke);

if (c>2)
    Stroke=Stroke';
end 

   NormN=getNormalizedFeature(Stroke,maxNpoints ,minNpoints,meanN,variance);
   
   x=Stroke(:,1);
   y=Stroke(:,2);
   %subplot(2,2,1);
   %plot(x,y,'*');
   % title([' Stroke '  ]);
   variation_x=var(x);
   variation_y=var(y);

   [BW,h,w]=PreProcessStrokeOffline(Stroke , 50);
 
  %subplot(2,2,2);
  %imshow(BW);
  %subplot(2,2,3);
  %imshow(BW');
  
  
    
  % [Len,Npoints]= LengthStroke(Stroke);
    
   
  
   
  % Den =  DensityFeatures(BW,0,1,5,0,5);
     Den= Density(BW,0);
    %feat=[ w*h    h/w     Den];  %% 96.6 %
    
  %  feat=Den ;
     % feat=OVO2(BW,w,h);  %99.0926
      %feat1=OVO2(BW,w,h);  
      
      %feat =[feat1  Den  w*h    h/w     variation_x     variation_y]; %99.5224  10 errors 
     % 99.5224  with den ,h/w,  h*w feat1
     
     % feat=[feat2 Den]; %95.1289
     
     %feat=[feat3 Den  h/w ]; 
  %  [HProjection VProjection,ProjectionRightDLeft, ProjectionLeftDRight]=Projections(BW);
    %  y1=getMostThree(HProjection);
    %y2=getMostThree( VProjection); 
     %y3=getMostThree(ProjectionRightDLeft);
      %y4=getMostThree(ProjectionLeftDRight);
     [VerticalTransitions, HorizontalTransitions ,DigRLTransitions,DigLRTransitions]=Transitions(BW);
     y1=getMostThree(VerticalTransitions);
      y2=getMostThree( HorizontalTransitions); 
     y3=getMostThree(DigRLTransitions);
       y4=getMostThree(DigLRTransitions);
    
    %feat=[y1 y2 y3 y4];  % 95.8456    (87 errors);
    
      feat=[y1  y2  y3  y4   Den  w*h     h/w   variation_x     variation_y   NormN];  % 98.8456    // 99.28 %;
  
    
    %feat=[Den HProjection VProjection'];
   
  %feat=[VerticalTransitions, HorizontalTransitions ,DigRLTransitions,DigLRTransitions];
    