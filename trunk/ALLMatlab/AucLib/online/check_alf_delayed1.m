function result=check_alf_delayed1(Stroke)
 
% variation_x=var(delayed(:,1));
% variation_y=var(delayed(:,2));
% v=[(variation_x)  (variation_y)];
%  
% wmin=min(delayed(:,1));wmax=max(delayed(:,1));
% hmin=min(delayed(:,2));hmax=max(delayed(:,2));
% 
% H=hmax-hmin+1;
% W=wmax-wmin+1;
% a=H/W;
%  
% if  (mean([variation_y  variation_x]) >800 ) || (variation_y >100 &&variation_x>10) 
%     result=1;
% else
%     result=0;
% end

load model;


  Feat=ComputeZeroFeatures(Stroke);
        % fea=getNormalizedFeature(Data{d}{n},maxNpoints ,minNpoints ,mean,variance);
        % Feat=[Feat fea];

 result = svmclassify(svmStruct,Feat);
  
   
