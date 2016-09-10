digit=7; %% digit is either 7 or 4
labels_d=labels==digit;
places=find(labels_d);
LP=length(places);
I=zeros(28,28);r=zeros(28);c=zeros(28);
%---------------------------------------------
th=100;
HC=14;WC=14;HW=zeros(1,LP);
c1=0;
%---------------------------------------------
k1=1;
for k=k1:LP
%---------------------------------------------------------------------
start_of_row=17+784*(places(k)-1);
        for n=1:28
            I(n,1:end)=A(start_of_row:start_of_row+27);
            start_of_row=start_of_row+28;
        end
        m28=I>th;  % 28*28 binary matrix of the digit
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
        W_1=W-1;H_1=H-1;rb2=rb-1;cb2=cb-1;H2=fix(H/2);H3=fix(2*H/3);W2=fix(W/2);H1=fix(H/3);
        H4=fix(3*H/4);
        I=I.*m28;
%--------------------------------------------------------------------------
area=H*W;
HW(k)=H/W;
bp=sum(sum(m));
wp=area-bp;
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
mx4=1;rx4=1;cx41=1;cx42=1;mi=zeros(1,H);
for i=1:H
    tf=find(~m(i,:));
    if (length(tf))
        tf=[tf W+1];
        tf2=[0 tf(1:length(tf)-1)];
        tt=tf-tf2;
        [mt,tc]=max(tt);mi(i)=mt;
        if (mt>=mx4)mx4=mt;rx4=i;cx41=tf2(tc)+1;cx42=tf(tc)-1;end
    else mt=W;mi(i)=mt;
         if (mt>=mx4)mx4=mt;rx4=i;cx41=1;cx42=W;end
    end     
end    
%--------------------------------------------------------------------------
fl74=0;  
btop=sum(sum(m(1:4,:)))/W;
mix=max(mi(1:4))/W;
slc=sum(lce(1:W2)<H1)/W2;   
wtop=sum(lcb)/H/W;
lrbe=lre-lrb;slrbe=sum(lrbe>2); 
lc1=lcb(1:W-1);lc2=lcb(2:W);
lc=lc2-lc1;
alc=sum(abs(lc))/W;
 
clrb=0;ic=[];
 for i=1:H_1
     if ((lrb(i+1)-lrb(i))>2)clrb=clrb+1;ic=[ic i];end
 end    
 if (clrb>1&(ic(clrb)-ic(1))>3)fl74=1;end%61 7s and 5 4s
 if (clrb)i2=ic(1);else i2=H;end
 wabv=0;
 if (clrb)
        if (rx4>i2+4&rx4<17)fl74=1;end%
        for j=lrb(i2)+1:lrb(i2+1)
           tfm=find(m(i2:-1:1,j));       
           if (length(tfm))wabv=wabv+tfm(1)-1;else wabv=wabv+i2;end
        end
        dlrb=lrb(i2+1)-lrb(i2);if (dlrb)wabv=wabv/dlrb;end    
 end   
    if (wabv>1.7&i2<10&i2-rx4>1)fl74=1;end%44 7s and 3 4s
    if(wabv>4&i2<=13)fl74=1;end   
    if (i2<H&rx4<=H1&i2-rx4>4)fl74=1;end 
%--------------------------------------------------------------------------
% features = rx4,btop,mix,slc,wtop,slrbe,alc,i2,wabv,fl74
end
