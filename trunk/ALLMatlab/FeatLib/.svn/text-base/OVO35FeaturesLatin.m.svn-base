function Features=OVO35FeaturesLatin(I)
th=0.20;
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
          %feat= tic; 
        m=m28(rb:re,cb:ce);             % binary matrix of the digit with H rows and W columns
        H=re-rb+1; % hieght of the 
        W=ce-cb+1; % width of the 
        W_1=W-1;  % beside the wall to the right 
        H_1=H-1; % beside wall to bottom 
        rb2=rb-1; 
        cb2=cb-1;
        H2=fix(H/2); % half the height 
        H3=fix(2*H/3); % 2/3 of the hight 
        W2=fix(W/2);
        H1=fix(H/3);
        H4=fix(3*H/4);
%--------------------------------------------------------------------------
HW=H/W; % ratio of hieght to width  % feature 1 
%area=H*W;
%bp=sum(sum(m));
%wp=area-bp;

lcb=zeros(1,W); % initalize lcb to size of width 
lce=lcb;
lc2b=lcb;
lc2e=lce;

lrb=zeros(1,H); %  initalize lrb with size of hieght. 
lre=lrb;
lr2b=lrb;
lr2e=lre;


for i=1:W   % for size of width 
    lc=find(m(:,i)); % find number and location of 1's in column  i. 
    llc=length(lc); % count number of 1s ( pixels )  
    if (llc)% if there is black pixel >0 
        lcb(i)=lc(1);  % lcb of column i is locattion of first black pixel in the column  
        lce(i)=lc(length(lc)); % lce of column i  location of  last  pixel in the column. 
    end
end 

for i=1:H  % for each row till hieght
    lr=find(m(i,:)); % find number and location of 1's in row i. 
    llr=length(lr);% count number of pixels 
    if (llr)
        lrb(i)=lr(1);  % lrb of row i is location of first black pixel in the row 
        lre(i)=lr(length(lr));% lce of row i  location of  last  pixel in the row. 
    end
end
%--------------------------------------------------------------------------


%--------------------------------------------------------------------------
Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,mxb,wd,u1,smallwd,sre,mce69,sr,im,wce,wcb,wre,btop,bbot,sle3,sle,snv,cbe,wcbe,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
