function [featureSet]=b34(A,Base,digit)
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
wu=0;fl2=0;im=0;hb=H-H2+1;fl4=0;w2r=0;bend4=0;w4lft=0;wcol=0;sbck=0;bcol=0;
w2r=0;
for i=2:H3
    for j=1:W
        if (~m(i,j))
            if(i>lcb(j)&i<lce(j)&j<lrb(i))w4lft=w4lft+1;
            elseif(j>lre(i)&i>lcb(j)&i<lce(j))w2r=w2r+1;
            end    
        end
    end
end    
                

lre1=lre(1:H_1);lre2=lre(2:H);lre3=lre2-lre1;
spve=sum(lre3>3);snve=sum(lre3<-3);
mxlre3=max(lre3);
%for i=3:H-3
%    if(lre(i)>lre(i-1)&lre(i)>lre(i+1))bend4=1;break;end
%end    
[mne,lne]=min(lre(1:H2));
le1=lre(lne:H_1-1);le2=lre(lne+1:H_1);le12=le2-le1;
snv=sum(le12<0);szero=sum(le12==0);
sle=sum(le12>=0);sle=sle/length(le12);
lrb1=lrb(1:H3-1);lrb2=lrb(2:H3);lrb3=lrb2-lrb1;
[mxb,lxb]=max(lrb3);lxb=lxb+1;
if (mxb>=2)
    %for j=lrb(lxb):W
    j=lrb(lxb);hb2=H-lxb+1;    
    while j<=W&m(lxb,j)   
        if (m(lxb,j))
            swht=sum(~m(lxb:lce(j),j));
            if (swht)wcol=wcol+1;
            else sbck=lce(j)-lxb+1;if (lxb<H3&sbck/hb2>0.7)bcol=bcol+1;end    
            end
        end
        j=j+1;
    end    
end    
bbot=sum(sum(m(H-2:H,:)))/3;
btop=sum(sum(m(1:3,:)))/3;
lce1=lce(1:W-1);lce2=lce(2:W);lce3=lce2-lce1;
[mxce,lxce]=max(lce3);lxce=lxce/lre(H);
    
if(w4lft>4)fl4=1;
elseif(sle<0.7)fl4=1;
elseif(mxb>3)fl4=1;
elseif(mxb>=2&wcol>1)fl4=1;
elseif(mxb>=2&bcol)fl4=1;
elseif(snv&lne>2&w4lft)fl4=1;
elseif(w2r<10&bbot>0.5)fl4=1;
elseif(mxce>=3&bbot>=btop&lxce<0.5&lxce>=0.1)fl4=1;
elseif(btop<=3)fl4=1;
elseif(mxce>=5&lxce<0.5&bbot>=btop)fl4=1;
end

%if (~fl2&~fl4)
%    c2=c2+1;
%    if(snve>=2)I,disp=[spve,snve,mxce,lxce,bbot,btop,w2r,snv,bcol,wcol,mxb,w4lft,sle,k],disp2=[lre3],keyboard,end
%end    


%if (fl3)c1=c1+1;else c2=c2+1;end
%--------------------------------------------------------------------------
Features=[w4lft,mxb,wu];
featureSet(k,:)=Features;

end
c1,c2