function [featureSet,Alltime]=OVO(A,Base,Ssize,digit)%digit=0;digit=3;digit=3;digit=3;
c1=0;c2=0;
th=20;
kb=digit*Base;
variance = (sqrt(2)*3/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);
%--------------------------------------------
kdgt=digit*Base*28;
for k=1:Base
   tic;  %  this is n 5
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
        H=re-rb+1;
        W=ce-cb+1;
        W_1=W-1;
        H_1=H-1;
        rb2=rb-1;
        cb2=cb-1;
        H2=fix(H/2);
        H3=fix(2*H/3);
        W2=fix(W/2);
        H1=fix(H/3);
        H4=fix(3*H/4);
%--------------------------------------------------------------------------
HW=H/W;
%area=H*W;
%bp=sum(sum(m));
%wp=area-bp;

lcb=zeros(1,W);
lce=lcb;
lrb=zeros(1,H);
lre=lrb;
lr2b=lrb;
lr2e=lre;
lc2b=lcb;
lc2e=lce;
for i=1:W
    lc=find(m(:,i));
    llc=length(lc); % count number of zeros ( white in row )  
    if (llc)% if there is white pixel >0 
        lcb(i)=lc(1);  % first pixel  in the row  
        lce(i)=lc(length(lc)); % last  pixel in the row. 
    end
end 
for i=1:H  % same for column
    lr=find(m(i,:));llr=length(lr);
    if (llr)lrb(i)=lr(1);lre(i)=lr(length(lr));end
end
%--------------------------------------------------------------------------
wu=0;w2r=0;w4lft=0;wd=0;w4=0;W4=0.25*W;im=0;
fli=0;fld=0;u1=0;fli2=0;flb=0;wcbe=0;
for i=1:H % for each columen 
    for j=W:-1:1 % for each rwo 
        if (~m(i,j))% invert pixel alue 
            if(j>lrb(i)&j<lre(i)&i<lcb(j))
                wu=wu+1;
                im=i;
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
wrb=sum(lrb-1);wrb=wrb/H;
wce=sum(H-lce);wce=wce/W;
wre=sum(W-lre);wre=wre/H;
wcb=sum(lcb-1);wcb=wcb/W;
lrb1=lrb(1:H3-1);lrb2=lrb(2:H3);lrb3=lrb2-lrb1;
[mxb,lxrb]=max(lrb3);
[mne,lne]=min(lre(1:H2));
le1=lre(lne:H_1-1);le2=lre(lne+1:H_1);le12=le2-le1;
snv=sum(le12<0);szero=sum(le12==0);
sle=sum(le12>=0);sle=sle/length(le12);
sle3=sum(le12>=3);
lre4=lre(1:H_1);lre5=lre(2:H);lre6=lre4-lre5;
[mxlre6,lxre6]=max(lre6);

for j=2:W
    if (lcb(j)>H2)flb=1;break;end
    if (lcb(j)>lcb(j-1))fli=1;fli2=1;
    elseif (lcb(j)<lcb(j-1)&fli)u1=u1+1;fli=0;
    end
end  
bbot=sum(sum(m(H-2:H,:)))/3;
btop=sum(sum(m(1:3,:)))/3;
if (W>3)
blft=sum(sum(m(:,1:3)))/3;
bright=sum(sum(m(:,W-2:W)))/3;
else
    blft=H;
bright=H;
end
wdbe=lre-lrb;
smallwd=sum(wdbe(H2:H)<5);
re=wdbe<W2;
sre=sum(re)/H;
sr=sum(wdbe>=W4)/H; % try make sr same as smallwd or sre later
mce69=mean(lce(1:W2));
cbe=mean(lce(1:W2)-lcb(1:W2))/H;
for j=1:W2
   if (lcb(j))wcbe=wcbe+sum(~m(lcb(j):lce(j),j));end
end    
lce1=lce(1:W-1);lce2=lce(2:W);lce3=lce2-lce1;
[mxce,lxce]=max(lce3);lxce=lxce/lre(H);
if (isempty(mxce))
    mxce=0;
end 
if (isempty(lxce))
   lxce=0;
end 
%--------------------------------------------------------------------------
Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
   featureSet(k,:)=Features;
Alltime(k)=toc();
end