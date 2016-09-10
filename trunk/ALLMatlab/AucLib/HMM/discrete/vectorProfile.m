function [lcb,lce,lrb,lre]=vectorProfile(m)
[H,W]=size(m)
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