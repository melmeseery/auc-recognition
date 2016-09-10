function [name, num]=getFeatureNameCount(f)

if (f==1)
% f= Kirsh4_g(I,h);
    name= 'kirsh4';
    num=100;
end 
if (f==2)
    % f=GetChainCode4(I,h);
    name='chain_code';
    num=200;
end 
if (f==3)
    
   % f= gmask_Local_Gradient_proj(I,h);
        name='gmask_Local_Gradient';
    num=200;
end 
if (f==4)
      %f=Local_Concavity_h(I,h);
          name='Local_Concavity';
    num=225;
end
if (f==5)
   % awImage_Wavelet_FE(I);
     name='WaveLet_Raw';
     num=64;
end

if (f==6) %low featture 
    name='Low_dim_feat';
%     I=imread('t.bmp');
%     Centroid=Centroid16FE(I) 
%     Concav=Concavity(I)'
%        Down_Sam=Down_Sample(I)'
%        Project=Projections(I)'
%        Num_of_Cro=Num_of_Crossings(I)'
%        FixedSizeP=FixedSizeProfile_2(I,10,10)
%        temp=[Concavity(I)'; Down_Sample(I)'; Projections(I)'; Num_of_Crossings(I)';] 
     % temp=[Concavity(I)' Down_Sample(I)' Projections(I)' Num_of_Crossings(I)'] 
      %Concavity(I)' Down_Sample(I)' Projections(I)' Num_of_Crossings(I)'
    %f=[Centroid16FE(I)  Concav Down_Sam Project  Num_of_Cro FixedSizeProfile_2(I,10,10)]
    %size(temp)
    %length(temp)
    %  f=[Centroid16FE(I);  temp; FixedSizeProfile_2(I,10,10);]

   num=15+16+25+40+40+20;%156;
   
   %num=length(f)  
end 
if (f==7)
    % any other system 
    num=35;
    name='OurFeature35';
end 

if (f==8)
    % any other system 
    num=35;
    name='OurFeature';
end 