function [featureSet]=b89(A,Base,digit)%digit=8;
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
wd=0;fl9=0;im=0;hb=H-H2+1;fl8=0;w2r=0;jm=0;w4=0;wcbe=0;wratio=1;
for i=1:H
    for j=W:-1:1
        if (~m(i,j))
            if(j>lrb(i)&j<lre(i)&i>lce(j))wd=wd+1;im=i;
            elseif(j>lrb(i)&j<lre(i)&i>lcb(j)&i<lce(j))w4=w4+1;    
            end
        end   
    end
end
b1=lrb(1:H_1);b2=lrb(2:H);b12=b2-b1;
[mxb,lxb]=max(b12);mxb=mxb/W;
wdlce=sum(lxb-lce(1:W2))/W2;
lxb=lxb/H;
for j=1:W2
   if (lcb(j))wcbe=wcbe+sum(~m(lcb(j):lce(j),j));end
end    
if (wd)wratio=(wd-wcbe)/wd;end
rbe=lre-lrb;
wdb=sum(rbe(H2:H)<W5)/hb;


if(wd<3)fl9=1;
elseif(w4>wd)fl9=1;elseif(w4>=3&wd>10*w4)fl8=1;   
elseif (mxb>=0.25&lxb<0.5)fl9=1;
elseif (mxb>=0.25&wratio<0.5)fl9=1;elseif(wd>55)fl8=1;
elseif (mxb>=0.25&lxb<=0.9&wdlce<=2.6&wcbe>=1)fl9=1;
end


if (fl9)c1=c1+1;else c2=c2+1;end
%5 mistakes in 8
%k=172,289,307 same numbers as 9. vaguely look like 9
%k=583&586 w4>wd
%--------------------------------------------------------------------------
Features=[wd,w4,wdb];
featureSet(k,:)=Features;
end
c1,c2