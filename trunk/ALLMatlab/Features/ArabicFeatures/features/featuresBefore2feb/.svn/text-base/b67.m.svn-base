function [featureSet]=b67(A,Base,digit)
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
wu=0;W4=0.25*W;fl6=0;hb=H-H2+1;im=0;
for i=1:H
    for j=1:W
        if (~m(i,j)&j>lrb(i)&j<lre(i))
           if(i<lcb(j))wu=wu+1;im=i;end
        end   
    end
end
rbe=lre-lrb;
sr=sum(rbe>=W4)/H;
bf5=sum(sum(m(1:5,:)))/5/W;
srb=sum(rbe(H2:H)<2)/hb;

%if (sr<0.3)fl6=1;end
%if (bf5>0.6)fl6=1;end
%if (wu<5)fl6=1;end
%if (srb>=0.75)fl6=1;end
%if (im<H1-1)fl6=1;end
if (fl6)c1=c1+1;else c2=c2+1;end
%100% recog with 1 mistake in Tr.
%I,disp=[wu,im,sr,bf5,srb,k],keyboard,
%--------------------------------------------------------------------------
Features=[wu,im,sr];
featureSet(k,:)=Features;
end
c1,c2