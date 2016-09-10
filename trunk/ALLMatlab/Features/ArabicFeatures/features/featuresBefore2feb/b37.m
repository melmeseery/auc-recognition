function [featureSet]=b37(A,Base,digit)
%digit=3;
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
        %I=double(I).*m28;
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
wu=0;fl3=0;im=0;W3=0.3*W;hb=H-H2+1;hwa=W-W2+1;wbb=0;wb37=0;wr2=-20;
fl7=0;swr7=0;
flj=1;
for i=1:H
    for j=1:W
        if (~m(i,j)&j>lrb(i)&j<lre(i))
           if(i<lcb(j))wu=wu+1;
               if (~im)im=i;elseif (im&i<=(im+2))im=i;end
           end
        end   
    end
end
dre=lre-lrb;
re=dre<W2;
sre0=sum(dre<4)/H;
sre=sum(re)/H;
te1=lre(1:H_1);te2=lre(2:H);
te12=te2-te1;
[mnte,lmn]=min(te12);lmn=lmn+1;hlmn=H-lmn+1;
mnte1=mnte;
mnte2=abs(mnte)/(dre(lmn)+1);
mnte=abs(mnte1)/W;
sre2=sum(dre(lmn:H)<W3)/hlmn;
refb=lre(H);
wr=sum(refb-lre(H2:H))/hb;
if (mnte1<-1&lmn>=H3)wr2=sum(refb-lre(lmn:H))/hlmn;end
wreb=sum(W-lre(H2:H))/hb;
sce=sum(lce(W2:W)<H2)/hwa;
sba=sum(sum(m(1:H2,:)));sbb=sum(sum(m(H2+1:H,:)));
tfa=find(lre>W2);r1=tfa(1);
co1=lre(r1);
if (co1>refb)
    nc12=co1-refb;
    wb37=sum(H-lce(refb:co1));
end 

wdp=(dre>=0.25*W);        
tw=find(wdp);eff=H-tw(1)+1;eff3=0.33*eff;
%mwd=max(wdp);thwd=fix(0.25*mwd);
ss3=sum(dre(tw(1):H)<4);
wlre=sum(W-lre)/H;
[mi11,i1]=max(lre(1:H1));
vb1=lre(H:-1:i1+1);vb2=lre(H_1:-1:i1);vb12=vb2-vb1;
vb120=sum(vb12>0);%svb120=sum((vb12>0).*vb12);
vb121=sum(vb12<0);
ssvb=sum(vb12);
fvb=find(vb12>0);if(length(fvb))ffvb=fvb(1);else ffvb=0;end



if (wu<2)fl3=1;elseif (wu>98)fl7=1;
elseif (im<H1-1)fl3=1;elseif (im>H3+1)fl7=1;
elseif(~mnte1)fl7=1;
elseif (sre0>0.6)fl3=1;
elseif (mnte2>=1.7)fl3=1;
elseif (mnte1<-1&im-lmn>5)fl7=1;
elseif(wr>2.5)fl3=1;elseif (mnte1==-1&wr<=-1.5) fl7=1; 
elseif (lmn<=H3&sre2>0.875)fl3=1;elseif (lmn<=H3&sre2<0.23)fl7=1;
elseif (sba/sbb>2.05)fl3=1;
elseif (wb37>46&wr>0)fl3=1;
elseif (lmn>=H3&mnte1<-1&wr2>0)fl3=1;
elseif (ss3/H>0.45)fl3=1;
elseif (wlre<0.6)fl7=1;  
elseif (vb121>=3&vb120>=3)fl3=1;
elseif (ssvb>10&vb121)fl3=1;
elseif (ffvb>6&ssvb>6)fl3=1;
else fl7=1;
end

if (fl3)c1=c1+1;else c2=c2+1;end
%one mistake in 7 (k=562)
%--------------------------------------------------------------------------
Features=[wu,im,sre];
featureSet(k,:)=Features;
%         
end
c1,c2