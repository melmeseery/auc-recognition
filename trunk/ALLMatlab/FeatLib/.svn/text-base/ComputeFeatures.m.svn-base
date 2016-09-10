function f=ComputeFeatures(feat,I,h)


if (feat==1)

    f= Kirsh4_g(I,h);
end 
if (feat==2)
    f=GetChainCode4(I,h);
end 
if (feat==3)
    
    f= gmask_Local_Gradient_proj(I,h);
end 
if (feat==4)
      f=Local_Concavity_h(I,h);
end
if (feat==5)
f=  RawImage_Wavelet_FE(I);
    
end
if (feat==6) %low featture 
      temp=[Concavity(I)'; Down_Sample(I)'; Projections(I)'; Num_of_Crossings(I)';] ;
        f=[Centroid16FE(I);  temp; FixedSizeProfile_2(I,10,10);];
 %  f=[Centroid16FE(I) Concavity(I) Down_Sample(I) Projections(I)  Num_of_Crossings(I) FixedSizeProfile_2(I,10,10)]
end 
if (feat==7)
    % any other system  
    f=OVO35Features(I);
    %length (f)
end 
if (feat==8)
    f=OVO35FeaturesLatin(I);
end 