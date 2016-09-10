function [f fcos fsin f2]=distanceforall(data)

% % % % % % % % % % % % % % % % ang=[];
% % % % % % % % % % % % % % %   z=1:3:100;
% % % % % % % % % % % % % % %   datan=data(z,:);
% % % % % % % % % % % % % % % % for i=1:1;
% % % % % % % % % % % % % % % %  x=[i+1 length(z)];
% % % % % % % % % % % % % % % % for k=1:length(x(:,1))
% % % % % % % % % % % % % % % %     distall(k)=abs((c(x(k,1),1)-c(x(k,2),1))+sqrt(-1)*(c(x(k,1),2)-c(x(k,2),2)));
% % % % % % % % % % % % % % % %     distp(k)=sum(abs((c(x(k,1):x(k,2)-1,1)-c(x(k,1)+1:x(k,2),1))+sqrt(-1)*(c(x(k,1):x(k,2)-1,2)-c(x(k,1)+1:x(k,2),2))));
% % % % % % % % % % % % % % % %     s=(distall(k)/distp(k));
% % % % % % % % % % % % % % % %     straightness(k,i)=100*((exp(s)-1)/(exp(1)-1));
% % % % % % % % % % % % % % % %    
% % % % % % % % % % % % % % % %     segmentdirection(k,i)=angle((c(x(k,2),1)-c(x(k,1),1))+sqrt(-1)*(c(x(k,2),2)-c(x(k,1),2)));
% % % % % % % % % % % % % % % %     
% % % % % % % % % % % % % % % %     segmentdirection(k,i)=(segmentdirection(k)*180)/pi;
% % % % % % % % % % % % % % % % 
% % % % % % % % % % % % % % % %      if(segmentdirection(k,i)>180)
% % % % % % % % % % % % % % % %      segmentdirection(k,i)=segmentdirection(k,i);
% % % % % % % % % % % % % % % %      end
% % % % % % % % % % % % % % % % end
% % % % % % % % % % % % % % % % end
% % % % % % % % % % % % % % %  
% % % % % % % % % % % % % % %  
% % % % % % % % % % % % % % %  xy=datan(:,1)+sqrt(-1).*datan(:,2);
% % % % % % % % % % % % % % % f=angle(xy);
% % % % % % % % % % % % % % % f(find(f<0))=f(find(f<0))+2.*pi;
% % % % % % % % % % % % % % %  xy1=.05+sqrt(-1).*.05;
% % % % % % % % % % % % % % % xy2=.05-sqrt(-1).*.05;
% % % % % % % % % % % % % % % xy3=-.05-sqrt(-1).*.05;
% % % % % % % % % % % % % % % xy4=-.05+sqrt(-1).*.05;
% % % % % % % % % % % % % % % xy5=.05+0;
% % % % % % % % % % % % % % % xy6=-sqrt(-1).*.05;
% % % % % % % % % % % % % % % xy7=-.05+0;
% % % % % % % % % % % % % % % xy8=0+sqrt(-1).*.05;
% % % % % % % % % % % % % % % f1=[];f2=f1;f3=f1;f4=f1;f5=f1;f6=f1;f7=f1;f8=f1;
% % % % % % % % % % % % % % % % for i=1:length(datan(:,1))
% % % % % % % % % % % % % % %      xy=datan(:,1)+sqrt(-1).*datan(:,2);
% % % % % % % % % % % % % % %      z1=angle((xy1-xy));
% % % % % % % % % % % % % % %      f1=[z1 ];
% % % % % % % % % % % % % % %      z2=angle(-(xy2-xy));
% % % % % % % % % % % % % % %       f2=[z2 ];
% % % % % % % % % % % % % % %      z3=angle(-(xy3-xy));
% % % % % % % % % % % % % % %      f3=[z3 ];
% % % % % % % % % % % % % % %      z4=angle(-(xy4-xy));
% % % % % % % % % % % % % % %      f4=[z4 ];
% % % % % % % % % % % % % % %      z5=angle(-(xy5-xy));
% % % % % % % % % % % % % % %      f5=[z5 ];
% % % % % % % % % % % % % % %      z6=angle(-(xy6-xy));
% % % % % % % % % % % % % % %      f6=[z6 ];
% % % % % % % % % % % % % % %      z7=angle(-(xy7-xy));
% % % % % % % % % % % % % % %      f7=[z7];
% % % % % % % % % % % % % % %      z8=angle(-(xy8-xy));
% % % % % % % % % % % % % % %      f8=[z8 ];
% % % % % % % % % % % % % % % % %      z1=[];z2=z1;z3=z1;z4=z1;z5=z1;z6=z1;z7=z1;z8=z1;
% % % % % % % % % % % % % % % % % end
% % % % % % % % % % % % % % % % % f=[cos([f1 f2 f3 f4])';sin([f1 f2 f3 f4])'];
% % % % % % % % % % % % % % f=[f1 ;f5 ;f2 ;f6 ;f3 ;f7 ;f4 ;f8];
% % % % % % % % % % % % % % f(find(f<0))=f(find(f<0))+2.*pi;
% % % % % % % % % % % % % % return
ang=[];
 L(1)=0;
    strokeses=data;
 for k=2:length(strokeses)
     L(k)=L(k-1)+sqrt((strokeses(k,1)-strokeses(k-1,1))^2+(strokeses(k,2)-strokeses(k-1,2))^2);
 end
     [data]=resam(strokeses, (L(length(strokeses(:,1)))/.14));
%      plot(data(:,1),data(:,2),'.');
%      pause
% z=1:4:100;
%   data=data(z,:);
for i=1:length(data(:,1))-1;
    for j=i+1:length(data(:,1))
     xy=data(j,1)-data(i,1)+sqrt(-1).*(data(j,2)-data(i,2));
     ang=[ang angle(xy)];
     xy=[];
    end
end
ang=ang.*180/pi;
tt=find(ang<0);
ang(tt)=ang(tt)+360;
f=(ang.*pi/180)';
fcos=cos(ang.*pi/180)';
fsin=sin(ang.*pi/180)';
f2=[fcos ;fsin];
