function [featureSet]=a57(A,Base,digit)
%digit=7;
c1=0;c2=0;
th=20;
%--------------------------------------------
kdgt=digit*Base*28;
for k=1:Base
    k2=kdgt+(k-1)*28+1;
    I=A(k2:k2+27,:);
%---------------------------------------------
        %m28=I;
        m28=I>th;  % 28*28 binary matrix of the digit
        I=double(I).*m28;
        for i=1:28
            r=m28(i,:);
            if (sum(r)>0)
                rb=i;break;
            end
        end   
        for i=28:-1:1
            r=m28(i,:);
            if (sum(r)>0)
                re=i;break;
            end
        end   
        for i=1:28
            r=m28(:,i);
            if (sum(r)>0)
                cb=i;break;
            end
        end   
        for i=28:-1:1
            r=m28(:,i);
            if (sum(r)>0)
                ce=i;break;
            end
        end   
        m=m28(rb:re,cb:ce);             % binary matrix of the digit with H rows and W columns
        H=re-rb+1;W=ce-cb+1;
        W_1=W-1;H_1=H-1;
        rb2=rb-1;cb2=cb-1;
        H2=fix(H/2);H3=fix(2*H/3);W2=fix(W/2);H1=fix(H/3);
        H4=fix(3*H/4);
%--------------------------------------------------------------------------
HW=H/W;
%area=H*W;
%bp=sum(sum(m));
%wp=area-bp;
lcb=zeros(1,W);lce=lcb;lrb=zeros(1,H);lre=lrb;lr2b=lrb;lr2e=lre;lc2b=lcb;lc2e=lce;
for i=1:W
    lc=find(m(:,i));llc=length(lc);
    if (llc)lcb(i)=lc(1);lce(i)=lc(length(lc));end
end
for i=1:H
    lr=find(m(i,:));llr=length(lr);
    if (llr)lrb(i)=lr(1);lre(i)=lr(length(lr));end
end
%--------------------------------------------------------------------------
wu=0;W4=0.25*W;im=0;w4=0;fl5=0;fl7=0;
for i=1:H
    for j=1:W
        if (~m(i,j)&j>lrb(i)&j<lre(i))
           if(i<lcb(j))wu=wu+1;im=i;
           elseif (i>lcb(j)&i<lce(j))w4=w4+1;
           end    
        end   
    end
end
sa=sum(lcb-1)/W;
sr=sum(W-lre)/H;
sl=sum(lrb-1)/H;
sb=sum(H-lce)/W;
st=sa+sb+sl+sr;
h2=H-2;
re=lre(h2:H)-lrb(h2:H);
bot=mean(re)/W;
if (wu>4*w4)fl7=1;
elseif (w4>4*wu)fl5=1;
elseif(sb<1.25)fl5=1;elseif (sb>=3.9)fl7=1;
elseif (sl>=4.4)fl7=1;
elseif (sr<0.7)fl7=1;
elseif (sa>8.9)fl7=1;elseif (sa<3.2)fl5=1;
elseif (bot>0.5)fl5=1;elseif (bot<0.35)fl7=1; 
elseif (st<10.8)fl5=1;    
elseif (wu>w4)fl7=1;
else fl5=1;
end
if (fl7)c1=c1+1;;else c2=c2+1;end
%I,disp=[HW,st,wu,w4,sa,sb,sl,sr,k],keyboard,
% 2 mistakes in 5: first (k=722) bcz sl=4.61 (fl7=1) & second (k=976) bcz wu>w4 (fl7=1)
% 1 mistake in 7 (k=566) bcz sb=1 (fl5=1) %also bot =0.62 (fl5=1)
%--------------------------------------------------------------------------
%%%%Features=wu,w4,sb,sa,sl,sr,st,bot
Features=[wu,w4,sb,sa,sl,sr,st,bot];
%wu>4*w4,w4>4*wu,sb<1.25,sb>=3.9,sl>=4.4,sr<0.7,sa>8.9,sa<3.2,bot>0.5,bot<0.35,st<10.8];
featureSet(k,:)=Features;
end
c1,c2