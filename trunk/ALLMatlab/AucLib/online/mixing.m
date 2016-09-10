function f=mixing(c,n)
L(1)=0;
    strokeses=c;
 for k=2:length(strokeses)
     L(k)=L(k-1)+sqrt((strokeses(k,1)-strokeses(k-1,1))^2+(strokeses(k,2)-strokeses(k-1,2))^2);
 end
     [data]=resam(strokeses, (L(length(strokeses(:,1)))/.79));
     data(:,1)=smooth(smooth(data(:,1)));
     data(:,2)=smooth(smooth(data(:,2)));
     
      data=resamplenew( data);
    
    data(:,1)=data(:,1)- mean(data(:,1));
     data(:,2)=data(:,2)-mean(data(:,2));
%      data(:,1)=data(:,1)./max(abs(data(:,1)));
%      data(:,2)=data(:,2)./max(abs(data(:,2)));
%      data=resamplenew( data);
%      data(:,1)=data(:,1)./var(data(:,2));
%      data=c;
%      data(:,2)=data(:,2)./var(data(:,2));
%      plot(data(:,1),data(:,2));
%      pause
     if n==1
         [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(data);
         f=[data(:,1);data(:,2);cos_alphaa];
     elseif n==2
         [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(data);
         f=[data(:,1);data(:,2);sin_alphaa];
     elseif n==3
         [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(data);
         f1=[data(:,1);data(:,2);cos_alphaa;sin_alphaa];
         f=(f1-mean(f1))./var(f1);
     elseif n==4
         [ang fcos fsin f2 dista]=distancetomean(data);
         f=[data(:,1);data(:,2);fcos;fsin];
         elseif n==5
         [ff fcos fsin f2]=distanceforall(data);
         f=[data(:,1);data(:,2);fcos;fsin];
     elseif n==6
          [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(data);
          [ff fcos fsin f2]=distanceforall(data);
          f=[cos_alphaa; sin_alphaa;fcos;fsin];
          elseif n==7
              [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(data);
              ch=chainCode64(data)';
              f=[cos_alphaa; sin_alphaa;ch];
     elseif n==8
         ch=chainCode64(data)';
         ch2=newchaincode(data,64,10);
         f=[ch;ch2];
     elseif n==9
          [ff fcos fsin f2]=distanceforall(data);
         ch=chainCode64(data)';
         f=[fcos ;fsin;ch];
     elseif n==10
          ch2=newchaincode(data,64,10);
          ch=chainCode64(data)';
         
         f=[data(:,1);data(:,2);ch;ch2];
         elseif n==11
         [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(data);
         f1=[cos_alphaa;sin_alphaa];
         f=(f1-mean(f1))./var(f1);
         elseif n==12
             f=[data(:,1)./var(data(:,1)) ;data(:,2)./var(data(:,2))];
             elseif n==13
             [ff fcos fsin f2]=distanceforall(data);
             f=[fcos;fsin];
              
     end
         
         
  
      