function  [image]=offline100(c)
%  function  [image]=offline(c)
% c=[3800,5334;3794,5334;3789,5334;3781,5334;3771,5334;3762,5332;3750,5332;3742,5332;3734,5332;3727,5332;3720,5331;3715,5330;3720,5331;3726,5331;3735,5330;3742,5330;3754,5330;3768,5330;3782,5331;3792,5330;3802,5329;3812,5329;3820,5329;3829,5328;3836,5327;3841,5327;3847,5326;3855,5327;3860,5327;3865,5327;3870,5330;3875,5332;3880,5333;3885,5333;3891,5333;3884,5332;3876,5331;3868,5329;3858,5327;3849,5326;3842,5325;3837,5325;3831,5324;3824,5323;3819,5322;3812,5322;3807,5321;3801,5321;3796,5321;3791,5319;3785,5317;3780,5315;3773,5310;3768,5306;3764,5301;3761,5293;3757,5287;3753,5280;3749,5274;3746,5268;3744,5263;3740,5258;3738,5252;3736,5247;3734,5242;3732,5237;3731,5231;3731,5226;3731,5221;3733,5216;3738,5210;3744,5205;3750,5202;3754,5197;3760,5194;3767,5191;3773,5188;3780,5185;3785,5183;3791,5181;3797,5180;3802,5179;3807,5179;3812,5180;3820,5180;3825,5181;3831,5183;3839,5185;3848,5188;3856,5192;3864,5195;3871,5197];
% [newPAWPointsWOResampling]=preprocessing(c,4, 1);

  newPAWPointsWOResampling=c;
 L(1)=0;
 for i=2:length(newPAWPointsWOResampling(:,1))
     L(i)=L(i-1)+sqrt((newPAWPointsWOResampling(i,1)-newPAWPointsWOResampling(i-1,1))^2+(newPAWPointsWOResampling(i,2)-newPAWPointsWOResampling(i-1,2))^2);
 end
% % % 
% % % 
   [newPAWPoints]=resam(newPAWPointsWOResampling, (L(length(newPAWPointsWOResampling(:,1)))/10));
   
 
   
   
   
   
   data=newPAWPoints;
% data=c;
% length(data)
% cs=min(data(:,2)); ce=max(data(:,2));  rs=min(data(:,1));  re=max(data(:,1));
%  ly=re-rs;
%  lx=ce-cs;
% lxnew=100;lynew=100;
%   data(:,1)=(data(:,1)*((lynew/ly))); data(:,2)=(data(:,2)*((lxnew/lx)));
newPAWPoints=data*6;
% c=[smooth(smooth(newPAWPoints(:,1))) smooth(smooth(newPAWPoints(:,2)))];
data(:,1)=round(newPAWPoints(:,1));
 data(:,2)=round(newPAWPoints(:,2));
% c=[smooth(smooth(newPAWPoints(:,1))) smooth(smooth(newPAWPoints(:,2)))];
% data=newPAWPoints;
  data=[data(:,1)- min(data(:,1))+1  data(:,2)- min(data(:,2))+1];
% mini=min(min([min(data(:,1)) min(data(:,2))]));
% data=[data(:,1)-mini+1  data(:,2)-mini+1];
% img(data(:,1),data(:,2))=1;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
str=1;
s=100;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
for i=1:length(data(:,1))
     img(data(i,1),data(i,2))=str;
 end
%   figure
%   imshow(img)
% return
  % % % 
 img=img';
% % % j=length(img(:,1));
% % % for i=1:length(img(:,1))
% % %     
% % %     x(i,:)=img(j,:);
% % %     j=j-1;
% % % end
% x=calc(newPAWPoints);
% img=x;
% x=[];
j=length(img(:,1));
for i=1:length(img(:,1))
    
    x(i,:)=img(j,:);
    j=j-1;
end
 image=x;
%  imshow(image)

 % figure
% [ra ca]=size(x);
% nimg = imresample([200 200],x,[ra ca] ,'spline');
% imshow(nimg)
%  for i=1:length(data(:,1))
%      if(i==1)
%         PAW(data(i,2),data(i,1))=1;
%      else
%          PAW(data(i,2),data(i,1))=1;
%      end
%  end
%  for kl=1:length(data(:,1))-1
%      x=linept(PAW,data(kl,2),data(kl,1),data(kl+1,2),data(kl+1,1));
%  end
% x
% figure


% f=[];image=[];
%  return
% imshow(x);
% NH = imresample([.5,.5],x,[1,1],'spline');
% figure
% imshow(NH);

[r c]=find(x==str);
rs=min(r);rf=max(r);cs=min(c);cf=max(c);
h=rf-rs+1;
w=cf-cs+1;
im=x(rs:rf,cs:cf);
if h>w
    z=0;
    wnew=ceil(s*(w/h));
    hnew=s;
    if (mod(wnew,2))==0;
    image=imresize(im,[hnew wnew]);
    
    
    else
       wnew=wnew+1;
       image=imresize(im,[hnew wnew]);
    end
    
    
    
else
    z=1;
    hnew=ceil(s*(h/w));
    wnew=s;
    if (mod(hnew,2))==0;
    image=imresize(im,[hnew wnew]);
    else
        hnew=hnew+1;
     image=imresize(im,[hnew wnew]);
    end
        
    
end

if z==1
    image=[zeros((s-hnew)/2,s); image; zeros((s-hnew)/2,s)];
else
    image=[zeros(s,(s-wnew)/2) image zeros(s,(s-wnew)/2) ];
end

% [rro cco]=find(image~=0);
%  for kj=1:length(rro)
%  image(rro,cco)=1;    
%  end
% H = fspecial('unsharp');
% sharpened = imfilter(image,H,'replicate');


    



image=[zeros(s,4) image zeros(s,4)];
image=[zeros(4,s+8); image; zeros(4,s+8)];


 image = imadjust(image);
 image=image>.3;
%     imshow(image)
    
%     imwrite(image,'x.bmp','bmp')
%     image2=imread('x.bmp');
%  imagen=imread(filepath,'jpg');
% threshold = graythresh(image);
% imagen =im2bw(image,threshold);
% figure
%   imshow(image)
%   imshow(image);
%   f=OVO35Features(image)';
%   f2=numberofpixels(image)';
% f=off(imagen);
%   variance = (sqrt(2)*3/pi)^2;
%  h= Generate_Gaussian_Mask([5 5],variance);
%  f= (gmask_Local_Gradient_proj(im2double(image),h))';


