function [featureSet,Alltime]=OVO(A,Base,Ssize,digit)%digit=0;digit=3;digit=3;digit=3;
c1=0;c2=0;
th=20;
kb=digit*Base;
variance = (sqrt(2)*3/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);
%--------------------------------------------
kdgt=digit*Base*28;
for k=1:Base
  
  kb=kb+1;
    k2=kdgt+(k-1)*28+1;
    I=A(k2:k2+27,:);
   feat= tic;  %  this is ovo valid 
%---------------------------------------------
  f= gmask_Local_Gradient_proj(double(I),h);
  Alltime(k)=toc(feat);       
%--------------------------------------------------------------------------
Features=[f ];%Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
featureSet(k,:)=Features;

end