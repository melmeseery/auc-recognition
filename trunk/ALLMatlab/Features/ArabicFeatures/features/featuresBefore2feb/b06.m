function [featureSet]=b06(A,Base,Ssize,digit)%digit=0;
c1=0;c2=0;
th=20;
kb=digit*Base;
%--------------------------------------------
kdgt=digit*Base*28;
for k=1:Base
    kb=kb+1;
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
area=H*W;
bp=sum(sum(m));
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
wu=0;fl0=0;fl7=0;blk=zeros(1,H);blc=zeros(1,W);w2r=0;w4lft=0;wd=0;w4=0;
for i=1:H
    for j=W:-1:1
        if (~m(i,j))
            if(j>lrb(i)&j<lre(i)&i<lcb(j))wu=wu+1;
            elseif(j>lrb(i)&j<lre(i)&i>lce(j))wd=wd+1;
            elseif(j>lre(i)&i>lcb(j)&i<lce(j))w2r=w2r+1;
            elseif(j>lrb(i)&j<lre(i)&i>lcb(j)&i<lce(j))w4=w4+1;  
            elseif(i>lcb(j)&i<lce(j)&j<lrb(i))w4lft=w4lft+1;
            end
        end   
    end
end

Vol=double(Ssize(kb,:));
vol2=Vol(1)*Vol(2);
wrb=sum(lrb-1);
lrb1=lrb(1:H3-1);lrb2=lrb(2:H3);lrb3=lrb2-lrb1;
mxb=max(lrb3);



%if (vol2<100)I,disp=[wu,k],disp2=[vol2,Ssize(kb,1),Ssize(kb,2)],keyboard,end

%--------------------------------------------------------------------------
Features=[vol2,wrb,mxb];
featureSet(k,:)=Features;
end
c1,c2