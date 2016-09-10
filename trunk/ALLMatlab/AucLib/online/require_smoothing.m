function data=require_smoothing(data)
summ=sum(data(:,3));
data2=data(:,1)+sqrt(-1)*data(:,1);
if sum(summ)==1
    index=find(data(:,3)==1);
    end1stroke=index;
%     beg1stroke=1;
    beg2stroke=index+1;
    end2stroke=length(data(:,1)); 
    
 diffend1tofirst2=abs(data2(end1stroke)-data2(beg2stroke));
 diffend1toend2=abs(data2(end1stroke)-data2(end2stroke));      
end
x=[ diffend1tofirst2 diffend1toend2];
fi=find(x==min(x));
if fi==2
  
   temp=data(beg2stroke: end2stroke,1:2);
   ll=length(temp(:,1));
    for i=beg2stroke: end2stroke
        
       data(i,1:2)=temp(ll,1:2);
        ll=ll-1;
    end
    data=resam(data,20);
    
end

