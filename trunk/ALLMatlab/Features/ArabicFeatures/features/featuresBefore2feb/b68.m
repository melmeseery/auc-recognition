function [featureSet]=b68(A,Base,digit)
%digit=6;
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
wd=0;fl6=0;im=0;hb=H-H2+1;fl8=0;w2r=0;jm=0;
W4=W/4;W23=fix(2*W/3);
for i=1:H
    for j=W:-1:1
        if (~m(i,j))
            if(j>lrb(i)&j<lre(i)&i>lce(j))wd=wd+1;im=i;
                %elseif(j>lre(i)&i>lcb(j)&i<lce(j))w2r=w2r+1;jm=j;
            end
        end   
    end
end
xlcb=std(lcb);
%xlcb2=std(lcb(1:W2));
rbe=lre-lrb;
sr=sum(rbe>=W4)/H;
srb=sum(rbe(H2:H)<2)/hb;

mxcb2=max(lcb(1:W23));
b1=lrb(1:H_1);b2=lrb(2:H);b12=b2-b1;
[mxb,lxb]=max(b12);mxb=mxb/W;lxb=lxb/H;


if(wd<3)fl6=1;
elseif(im<H1)fl6=1;
elseif(xlcb<=1.49)fl6=1;
elseif (sr<0.3)fl6=1;
elseif (mxb>0.25&lxb<0.5)fl6=1;
elseif (mxb>0.25&mxcb2<4&lxb<0.7)fl6=1;
end

if (fl6)c1=c1+1;else c2=c2+1;end
%3 mistakes in 6
%k=163 xlcb=5.8 , mxcb=10
%k=307 same reasons as above
%k=622 looks like 8 so violated almost all rules
%--------------------------------------------------------------------------
Features=[wd,im,sr];
featureSet(k,:)=Features;
end
c1,c2