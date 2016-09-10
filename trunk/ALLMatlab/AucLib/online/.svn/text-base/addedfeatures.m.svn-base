function f=addedfeatures(data)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%% ASPECT RATIO %%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


rmin=min(data(:,1));rmax=(max(data(:,1)));
cmin=min(data(:,2));cmax=(max(data(:,2)));
% plot(data(:,1),data(:,2),'.')
% pause
%%%% ROWS IS X
%%%% COLUMN IS Y

Ho=abs(cmax-cmin)+1;Wo=abs(rmax-rmin)+1;
ASPECT=Ho/Wo; % FEATURE 

% 
data4dist=data(:,1)+sqrt(-1).*data(:,2);
distance_each=sum(abs(data4dist(2:end)-data4dist(1:end-1)));
all=abs(data4dist(1)-data4dist(end));
str=all./distance_each;






m=offline100(data);
mm=m(5:34,5:34);
mm=mm>0;
[rr cc]=find(mm==1);

rb=min(rr);
re=max(rr);
cb=min(cc);
ce=max(cc);
% m=[];
% m=mm;
% mm=[];
% count=0;
% features1=zeros(1,3);
% features2=zeros(1,3);
% features3=zeros(1,4);
% features4=zeros(1,4);
% v=[5 14 15 16 25 ];
count=0;
for i=rb:re
    
    count=count+1;
firstrows(count,:)=mm(i,:);
inc_counter=0; intersections=0;
for j=1:length(firstrows(count,:))
        if (firstrows(count,j)==1 && inc_counter==0)
            intersections=intersections+1;
            inc_counter=1;
        elseif (firstrows(count,j)==1 &&inc_counter==1)
            intersections=intersections;
        elseif (firstrows(count,j)==0)
            inc_counter=0;
        end
        features1(count)=intersections;
end
end
[rr cc]=size(m);

count=0;

for i=cb:ce
    
    count=count+1;
    endrows(count,:)=mm(:,i)' ;
    inc_counter=0; intersections=0;
for j=1:length(endrows(count,:))

        if (endrows(count,j)==1 && inc_counter==0)
            intersections=intersections+1;
            inc_counter=1;
        elseif (endrows(count,j)==1 &&inc_counter==1)
            intersections=intersections;
        elseif (endrows(count,j)==0)
            inc_counter=0;
        end
        features2(count)=intersections;

end
end
features1;
features2;


count=0;
for i=-5:3:6
    diaga=[];
    count=count+1;
    diaga=diag(mm,i)'; 
    inc_counter=0; intersections=0;
for j=1:length(diaga)

        if (diaga(j)==1 && inc_counter==0)
            intersections=intersections+1;
            inc_counter=1;
        elseif (diaga(j)==1 &&inc_counter==1)
            intersections=intersections;
        elseif (diaga(j)==0)
            inc_counter=0;
        end
        features3(count)=intersections;

end
    
    
end
zzz=length(mm(1,:));
for t=1:length(mm(1,:))
   m22(:,zzz)=mm(:,t);   
    zzz=zzz-1;
end
count=0;

for i=-5:3:6
    diaga=[];
    count=count+1;
    diaga=diag(m22,i)'; 
    inc_counter=0; intersections=0;
for j=1:length(diaga)

        if (diaga(j)==1 && inc_counter==0)
            intersections=intersections+1;
            inc_counter=1;
        elseif (diaga(j)==1 &&inc_counter==1)
            intersections=intersections;
        elseif (diaga(j)==0)
            inc_counter=0;
        end
        features4(count)=intersections;
end
end
f2=OVO(mm);

llp=fix(length(features1)/3);
% f=OVO35Features(mm)';
%   f=[Ho Wo ASPECT str.*100 mean(features2) mean(features1(1:llp)) mean(features1(llp:llp*2)) mean(features1(2*llp:end)) f2 ]'; 98.82%  
% f=[Ho Wo ASPECT  f2 ]';% 98.88
% f=[f2]';[w2r,wu,w4lft,w4,wrb,wd,wre,wce,wcb] 98.93
% f=[f2]'; Features=[w2r,wu,w4lft,w4,wrb,wd,wre,wce,wcb,lxrb,wre,wce] 98,93
f=[f2]';