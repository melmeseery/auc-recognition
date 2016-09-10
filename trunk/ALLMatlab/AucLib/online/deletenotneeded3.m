clc
clear all
close all
deletedd=[];

% load datatest
 load trainingset1asly



% delayedtest=delayed_asly;
% ntest=n_asly;
 datadata=datatest;
% 
% delayedtest2=delayed;
% ntest2=n;



%  load datadata
 clear deletedd
 clear deleted
 deletedd=[];
 variable=1;
 segment=1;
 data=datadata{1,segment}{variable};
 disp('length of characters')
 length(data)
 data2=datadata2{1,segment}{variable};
  del=delayedtest{1,segment}{variable};
%   del2=delayedtest2{1,segment}{variable};
 n=ntest{1,segment}{variable};
%  n2=ntest2{1,segment}{variable};
 add=(length(data)-length(n));
%  add2=(length(data2)-length(n2));
  for i=length(n)+1:length(n)+add
      n{i}=[];
      del{i}=[];
  end
  
%   for i=length(n2)+1:length(n2)+add2
%       n2{i}=[];
%       del2{i}=[];
%   end
nu=input('please insert number of plots   ');
xa=input('number of rows   ');
ya=input('number of columns   ');
%  data{1,segment}{variable}(:,2)= max(data{1,segment}{variable}(:,2))-data{1,segment}{variable}(:,2)+min(data{1,segment}{variable}(:,2));
% data=s{1};
numberofplots=nu;% kam sora fil plot
ll=length(data)/numberofplots;
repeat=floor(ll);
len=numberofplots*floor(ll);
remaining=length(data)-repeat*numberofplots;
km=0; 
% figure(1); set(gcf,'Position',[10,100,800,900]);
 
 figure(1); set(gcf,'Position',[0,0,1600,1600]);
 figure(2); set(gcf,'Position',[0,0,1600,1600]);
for i=1:repeat
     k=0;
%      j=i*numberofplots-numberofplots+1;
%      while(j<=i*numberofplots)

    
for j=i*numberofplots-numberofplots+1:i*numberofplots

if length(data{j})~=0
        k=k+1;
        figure(1); set(gcf,'Position',[0,0,800,800]);
%     figure(1)      
%   subplot(4,2,k)
 dx=(data{1,j}(2:end,1)-mean(data{1,j}(2:end,1)));
 dy=(data{1,j}(2:end,2)-mean(data{1,j}(2:end,2)));
  subplot(xa,ya,k)
  if length(data2{j})~=0
 if length(data2{1,j}(:,1))>2
 dx2=(data2{1,j}(2:end,1)-mean(data2{1,j}(2:end,1)));
 dy2=(data2{1,j}(2:end,2)-mean(data2{1,j}(2:end,2)));

 
 
% % % % % % % % %     plot(dx(1:3),dy(1:3),'r+')
% % % % % % % % %   hold on
% % % % % % % % %  plot(dx(4:end),dy(4:end),'.')
% %  plot(k+delta_x,e+delta_y,'xr')
% % % % % % % % %    text(0,0,num2str(k),'FontSize',15)
  [ image2 ]=offline100([(data2{1,j}(2:end,1)),(data2{1,j}(2:end,2))]);
 imshow(image2)
 end
  end
 [ image ]=offline100([(data{1,j}(2:end,1)),(data{1,j}(2:end,2))]);
  
figure(2); set(gcf,'Position',[700,0,800,1600]);
  subplot(xa,ya,k)
   imshow(image)
  text(0,0,num2str(k),'FontSize',10)
%   axis([-800 800 -800 800])
 
 
 
 
%      if x==1
%          disp('deleted')
%       km=km;
%       elseif x==2
%          j=j-2;
%          
%          k=k-2;
%          km=km-1;
%      else
%          km=km+1;
%          d{km}=data{j};
%      end   
 
else
    k=k+1;
end
 
end

%       x=input(' enter    ');
     
    x=input(' enter    ');
while(x<=numberofplots)
 deletedd=[deletedd (i)*numberofplots-numberofplots+x];

  x=input(' enter    ');
end
     close all
 end
 close all
  m=0;
  
  figure(1); set(gcf,'Position',[0,0,1600,1600]);
  figure(2); set(gcf,'Position',[0,0,1600,1600]);
  if j<length(data)
%      kk=j;
%      while (kk<=length(data))
    for kk=j+1:length(data)
%        figure
       m=m+1;
       figure(1); set(gcf,'Position',[0,0,800,800]);
        subplot(xa,ya,m)
%      plot(data{1,kk}(:,1),data{1,kk}(:,2),'*')
% %    
  
  
% %   subplot(4,2,m)
  dx=data{1,kk}(2:end,1)-mean(data{1,kk}(2:end,1));
   dy=data{1,kk}(2:end,2)-mean(data{1,kk}(2:end,2));
   if length(data2{j})~=0
   if length(data2{1,kk}(:,1))>2
   dx2=data2{1,kk}(2:end,1)-mean(data2{1,kk}(2:end,1));
   dy=data2{1,kk}(2:end,2)-mean(data2{1,kk}(2:end,2));
   
   
%   subplot(4,2,k)

% %   axis([-2000 2000 -2000 2000])
% % % % % % % % % % % %  plot(dx(1:3),dy(1:3),'r+')
% % % % % % % % % % % %   hold on
% % % % % % % % % % % %  plot(dx(4:end),dy(4:end),'.')
   [  image2 ]=offline100([(data2{1,kk}(2:end,1)+10),(data2{1,kk}(2:end,2)+10)]);
   imshow(image2) 
   end
   end
  [  image ]=offline100([(data{1,kk}(2:end,1)+10),(data{1,kk}(2:end,2)+10)]);
  
   text(0,0,num2str(m),'FontSize',18);
  figure(2); set(gcf,'Position',[700,0,800,1600]);
  subplot(xa,ya,m)
   imshow(image)
  text(0,0,num2str(m),'FontSize',10)
% x=input(' enter    ');
%      if x==1
%          disp('deleted')
%       km=km;
%      elseif x==2
%          kk=kk-2;
%          km=km-1;
%          m=m-2;
%      else
%          km=km+1;
%          d{km}=data{j};
    end
 x=input(' enter    ');
 while x<=numberofplots
  deletedd=[deletedd (i+1)*numberofplots+1-numberofplots+x];
  x=input(' enter    ');
     end
%  kk=kk+1;    
%    end
%  end
    close all
  end 

  
   if length(deletedd)>0
  ff=[1:length(data)];
      for yy=1:length(deletedd)
      ff(find(ff-deletedd(yy)==0))=0;
      
      end
      
  
nonzero=  find(ff~=0);
nonzero2=  find(ff~=0);
datadata{1,segment}{variable}=[];
datadata2{1,segment}{variable}=[];
datafinal={data{nonzero}};
datafinal2={data2{nonzero2}};
nfinal={n{nonzero}};
nfinal2={n2{nonzero2}};
definal={del{nonzero}};
definal2={del2{nonzero2}};
datax{1,segment}{variable}=datafinal;
datax2{1,segment}{variable}=datafinal2;

   end
   a=exist('datax');
   if a~=0
    datatest{1,segment}{variable}=[];
    datatest2{1,segment}{variable}=[];
    delayedtest{1,segment}{variable}=[];
    delayedtest2{1,segment}{variable}=[];
    ntest{1,segment}{variable};
    ntest2{1,segment}{variable};
    datatest{1,segment}{variable}=datax{1,segment}{variable};
    datatest2{1,segment}{variable}=datax2{1,segment}{variable};
    ntest{1,segment}{variable}=nfinal;
    ntest2{1,segment}{variable}=nfinal2;
    delayedtest{1,segment}{variable}=definal;
      delayedtest2{1,segment}{variable}=definal2;
    save('trainingset1asly', 'datatest', 'delayedtest', 'ntest');
    save('trainingset1', 'datatest2', 'delayedtest2', 'ntest2');
   else
       save('trainingset1asly', 'datatest', 'delayedtest', 'ntest');
       save('trainingset1', 'datatest2', 'delayedtest2', 'ntest2');
   end  
    
    
    
   