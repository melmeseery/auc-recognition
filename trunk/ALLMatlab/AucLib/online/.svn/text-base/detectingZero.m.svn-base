clear all 
close all 
 
  [Data,label]=readStrokesDataSet(1,1,0);
  
  i=7;
  j=60;
  
%     for i=1:10
%         for j=1:length(Data{i})
            
  Stroke = Data{i}{j};
     x=Stroke(:,1);
   y=Stroke(:,2);
   subplot(2,2,1);
     plot(x,y,'*');
    title([' Stroke '  ]);
   [BW,h,w]=PreProcessStrokeOffline(Stroke , 10);
 
  subplot(2,2,2);
  imshow(BW);
  subplot(2,2,3);
  imshow(BW');
  
  
    
   [Len,Npoints]= LengthStroke(Stroke);
    
   
   Feat=[h   w    w*h    h/w     Len/Npoints]
   
   %[feat] =  DensityFeatures(BW,0,1,1,1,0);
       feat= Density(BW,0);
   
    [HProjection VProjection,ProjectionRightDLeft, ProjectionLeftDRight]=Projections(BW);
    
    
    [VerticalTransitions, HorizontalTransitions ,DigRLTransitions,DigLRTransitions]=Transitions(BW)
  
%         end 
%     end 
   

 
   