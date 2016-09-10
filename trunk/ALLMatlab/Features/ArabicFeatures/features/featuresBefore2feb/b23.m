function [featureSet]=b23(A,Base,digit)
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
wu=0;fl2=0;im=0;hb=H-H2+1;fl3=0;w2r=0;sswc=0;
for i=1:H
    for j=W:-1:1
        if (~m(i,j))
            if(j>lrb(i)&j<lre(i)&i<lcb(j))wu=wu+1;im=i;
            elseif(j>lre(i)&i>lcb(j)&i<lce(j))w2r=w2r+1;jm=j;
            end
        end   
    end
end
xlcb=std(lcb);mlcb=mean(lcb);
lrb1=lrb(1:H_1);lrb2=lrb(2:H);lrb3=lrb1-lrb2;
[mxlrb,lxrb]=max(lrb3);
lre1=lre(1:H2-1);lre2=lre(2:H2);lre3=lre2-lre1;
[mxlre,lxre]=max(lre3);
[mnlcb,lnlcb]=min(lcb(W:-1:1));mncl=W-lnlcb+1;
swc1=sum(lcb(mncl:-1:1)>1);swc=swc1/W;
%if(swc1)sswc=sum(lcb(mncl:-1:1)-1)/swc1;end
lre4=lre(1:H_1);lre5=lre(2:H);lre6=lre4-lre5;
[mxlre6,lxre6]=max(lre6);
fli=0;fld=0;u1=0;swa=0;fli2=0;sj=0;flb=0;
for j=2:W
    if (lcb(j)>H2)flb=1;break;end
    if (lcb(j)>lcb(j-1))fli=1;fli2=1;
    elseif (lcb(j)<lcb(j-1)&fli)u1=u1+1;fli=0;
    end
     if(fli2)swa=swa+lcb(j)-1;sj=sj+1;end
end  

%if (sj)swa=swa/sj;end
swa=swa/(j-1);
sh1=sum(lrb(1:H1)>W2);
wlrb=sum(lrb-1)/H/W;
slcb=sum(lcb==0);


%if (wu>16)fl3=1;
%elseif (w2r>94)fl2=1;
%elseif(xlcb<0.5)fl2=1;
%elseif(mxlrb>6&lxrb>1)fl3=1;
%elseif(mxlre>6&lxre<=mlcb)fl3=1;
%elseif(swc>0.8&mnlcb)fl3=1;
%elseif(mxlre6<3&wu)fl3=1;elseif(mxlre6<3&~wu)fl2=0;
%elseif(wu>w2r)fl3=1;elseif(slcb)fl2=1;
if(u1>1)fl3=1;elseif(u1==1&wu>8)fl3=1;
    %elseif(u1&swa>0.9)fl3=1;elseif (flb&sum(lcb(j:W)<H2))fl2=1;
    %elseif(lcb(1)==1&wu)fl3=1;
    %elseif(lcb(1)==1&~sum(lcb>H2)&mlcb>2.75)fl3=1;
    %elseif(sh1>3)fl3=1;elseif(wlrb>0.25&sh1==3)fl3=1;
    %elseif(wlrb>=0.37&sh1==2)fl3=1;
end


if (fl3)c1=c1+1;else c2=c2+1;end
% 6 mistakes in 2
%10 mistakes in 3
%--------------------------------------------------------------------------
Features=[wu,w2r,u1];
featureSet(k,:)=Features;
end
c1,c2