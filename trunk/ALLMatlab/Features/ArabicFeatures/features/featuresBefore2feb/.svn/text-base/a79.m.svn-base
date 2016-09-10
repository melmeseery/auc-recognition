%digit=7;

function [featureSet]=a79(A,Base,digit)
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
        %I=I.*m28;
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
wu=0;W5=0.2*W;im=0;w4=0;fl9=0;wcbe=0;
hb=H-H2+1;
for i=1:H
    for j=1:W
        if (~m(i,j)&j>lrb(i)&j<lre(i))
           if(i<lcb(j))wu=wu+1;im=i;
           elseif (i>lcb(j)&i<lce(j))w4=w4+1;
           end    
        end   
    end
end
rbe=lre-lrb;
wdb=sum(rbe(H2:H)<W5)/hb;
lrb1=lrb(1:H_1);lrb2=lrb(2:H);
dlrb=lrb2-lrb1;
[mxdlrb,lxdlrb]=max(dlrb);t2=lxdlrb+1;
wd2=rbe(t2)/W;
pcb=sum(lcb(1:W2)<H1)/W2;
%de=lre(1)-lre(H);
pce=sum(lce(1:W2)<H_1)/W2;
bot9=std(lce(1:W2));
wdcbe=sum(lce(1:W2)-lcb(1:W2))/W2;
for j=1:W2
    if (lce(j)>lcb(j))
       xt=find(~m(lcb(j):lce(j),j));
       if (length(xt))wcbe=wcbe+length(xt);end
    end
end    

if (wu<2)fl9=1;end
if (wdb>=0.64)fl9=1;end
if (mxdlrb>4&wd2<0.2&t2<H_1&lrb(t2)>W2)fl9=1;end
if (pcb>0.7&wu<12)fl9=1;end
if (bot9<=2&wdcbe>4.1&wcbe>1&(pce>0.9|wcbe>15)&pcb>=0.5&wu<30)fl9=1;end
if (fl9)c2=c2+1;else c1=c1+1;end
% one mistake in 9 (k=199) bcz bot9 is 2.133 although wcbe,wdcbe are
% clearly 9's
% 3 mistakes in 7: 1- at k=363, pcb>0.7&wu=9
% k=583 again pcb=0.9&wu=11
% k=585 again pcb=1&wu=7
%I,disp=[wu,wdb,mxdlrb,wd2,t2,bot9,wdcbe,wcbe,pce,pcb],keyboard
%--------------------------------------------------------------------------
Features=[wu,wdb,mxdlrb,wd2,lrb(t2),pcb,bot9,wdcbe,wcbe];
featureSet(k,:)=Features;
end
c1,c2