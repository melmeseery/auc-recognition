
function [featureSet]=a47(A,Base,digit)

%digit=4;
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
wu=0;wb=0;wul=zeros(1,H);wulf=0;fl4=0;hb=H-H2+1;pt=0;st34=0;sb4=10;mxfe4=0;spt=10;mxt34=0;
for i=1:H
    for j=1:W
        if (~m(i,j)&j>lrb(i)&j<lre(i))
           if(i<lcb(j))wu=wu+1;wul(i)=1;
           end
        end   
    end
end
re=lre-lrb;
wdbot=mean(re(H-2:H))/W;
refb=lre(H);
wr=sum(refb-lre(H2:H))/hb;
t1=lre(1:H_1);t2=lre(2:H);
t12=t2-t1;
fe4=sum(t12>1);
if (fe4>=1)
    mxfe4=max(t12);
    tg=find(t12>1);
    pt=tg(length(tg));
end    
t3=lrb(1:H_1);t4=lrb(2:H);t34=t4-t3;
st34=sum(t34>1);
if (st34)ts=find(t34>1);lts=ts(1);
    if (lts<=H3)sb4=sum(t34(lts+2:H-2));end
end
mxt34=max(t34);%if(mxt34>2&pt>=4&sb4<3)I,disp=[mxt34,sb4,k],keyboard,end
if (pt>=4)spt=sum(t12(1:pt-1));end

if (wu<2)fl4=1;;end
if (wr>2.5)fl4=1;end
if (wdbot>0.5&wr>-0.9)fl4=1;end
if (fe4>=2&pt>5)fl4=1;end
if (mxfe4>5&pt>=4&spt<-2)fl4=1;end
if (mxfe4>5&pt>=4&wu<12)fl4=1;end
if (pt>=4&st34>=1&lts>=H1&(sb4<0|(sb4<3&(wu<5|H-lts<7))))fl4=1;end
if (fl4)c1=c1+1;else c2=c2+1;end
% one mistake in 7 at k=627 wdbot=0.5167&wr=0.44
%--------------------------------------------------------------------------
%I,disp=[spt,mxfe4,sb4,st34,pt,fe4,wdbot,wr,wu,k],disp2=[t34],keyboard,
Features= [wu,wr,wdbot,fe4,spt,sb4,mxt34];
 featureSet(k,:)=Features;
end
c1,c2