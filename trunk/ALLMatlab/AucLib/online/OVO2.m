function [Features]=OVO2(Image,w,h)%digit=0;digit=3;digit=3;digit=3;

%kdgt=digit*Base*28;
%for k=1:Base
  
%  kb=kb+1;
%    k2=kdgt+(k-1)*28+1;
    I=Image;

   % get the height and width of the image 
           %  [height width]=size(Image);
%             
% %             % get normalize hight or width to 20 
% %             if(height>width)  % 
% %                 r=20;
% %                 c= floor(width/height*20);
% %           
% %             end              
% 
%             c=20
%             % now resize image to the new using  Bicubic interpolation the
%             % result will be new with new 20*20
%             Ir=imresize(I,[NaN  c],'bicubic');
%            [r c]=size(I);
%             level = graythresh(Ir);
%             %BW = dither(I) converts the intensity image in the matrix I to
%             %then invert its color 
%             %then change it to double 
%             I=im2bw(Ir,level);
% 
%             [hi wi]=size(I);
% %c1=0;c2=0;
% %th=20;
%  
% variance = (sqrt(2)*3/pi)^2;
% h= Generate_Gaussian_Mask([5 5],variance);
% %--------------------------------------------
% 
%   %  this isn5
% %---------------------------------------------
%            m28=I;
%         %m28=I;
%       %  m28=I>th;  % 28*28 binary matrix of the digit
%         %I=double(I).*m28;
%         for i=1:20
%             r=m28(i,:);
%             if (sum(r)>0)
%                 rb=i;break;
%             end
%         end   
%         for i=20:-1:1
%             r=m28(i,:);
%             if (sum(r)>0)
%                 re=i;break;
%             end
%         end   
%         for i=1:20
%             r=m28(:,i);
%             if (sum(r)>0)
%                 cb=i;break;
%             end
%         end   
%         for i=20:-1:1
%             r=m28(:,i);
%             if (sum(r)>0)
%                 ce=i;break;
%             end
%         end   

       % [height width]=size(Image);
       m28=Image;
%       [m28 ]=processResizeImage(Image);
       [hi wi]=size(m28);
        m=m28;             % binary matrix of the digit with H rows and W columns
        %feat= tic; 
        H=hi;
        W=wi;
        W_1=W-1;
        H_1=H-1;
%         rb2=0;
%         cb2=0;
        H2=fix(H/2);
        H3=fix(2*H/3);
        W2=fix(W/2);
%         H1=fix(H/3);
%         H4=fix(3*H/4);
%--------------------------------------------------------------------------
HW=H/W;
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
wu=0;w2r=0;w4lft=0;wd=0;w4=0;W4=0.25*W;im=0;
fli=0;fld=0;u1=0;fli2=0;flb=0;wcbe=0;
for i=1:H % for each colummn from top to button 
    for j=W:-1:1 % loop from right column to left.  
        if (~m(i,j))%  if the pixel is ==0 then do 
            if(j>lrb(i)&j<lre(i)&i<lcb(j)) % if current 0 pixel between first and last black bixel 
                %( row wise) and less than first in columne wise 
                % check surrond by black from down and left and right  \|/ 
                wu=wu+1;  % count wight up 
                im=i;     % mark position as im 
            elseif(j>lrb(i)&j<lre(i)&i>lce(j))
                    % check surrond by black from up and left and right.
                    wd=wd+1; % count number of pixel wight down 
                elseif(j>lre(i)&i>lcb(j)&i<lce(j))
                    % check between up and down and left   
                        w2r=w2r+1;  % white to the right 
                        %and last 
                elseif(j>lrb(i)&j<lre(i)&i>lcb(j)&i<lce(j))
                            % left and right and 
                            w4=w4+1; % white surronded by 4 walls of black 
                elseif(i>lcb(j)&i<lce(j)&j<lrb(i))
                          % up and down and right. 
                          w4lft=w4lft+1;% white surronded by 4 from the lefet
            end
        end   
    end
end
%Ssize(kb,:)
Vol=double([h w]); 
vol2=Vol(1)*Vol(2);  % are of the digit 
wrb=sum(lrb-1); % sum location of the first pixel in each row 
%(count of white pixel from left till first black moving left to right row wise)
wrb=wrb/H;  % average of the count

wce=sum(H-lce); % sum of location of last pixel in each column. 
% represent the count of white pixel from down to the last black pixel in
% column. 
wce=wce/W;

wre=sum(W-lre);
% count of pixel from right 
wre=wre/H;
wcb=sum(lcb-1);
% count of pixel from the top to bottom
wcb=wcb/W;

% % lrb1 hold the first location of pixel in each row fro the upper 1/3 of
% % hieigth 
% lrb1=lrb(1:H3-1); 
% % lrb2 holds the location of first pixel in each row for the upper 1/3 (-1)
% lrb2=lrb(2:H3);
% %% computing the transition by supptracting  locations of i - (i-1)
% lrb3=lrb2-lrb1;
% [mxb,lxrb]=max(lrb3); % locating max left  transition 
% [mne,lne]=min(lre(1:H2)); %  location of last number of pixels in upper half of the digit. 
% le1=lre(lne:H_1-1); 
% le2=lre(lne+1:H_1);
% le12=le2-le1; % suptracting the right transition. 

% computing the negative \ 
% snv=sum(le12<0); 
% % computing the zero transiitons 
% szero=sum(le12==0);
% % computing positive transisiotn 
% sle=sum(le12>=0);
% 
% sle=sle/length(le12);
% % compute the large positive transition. 
% sle3=sum(le12>=3);
% 
% lre4=lre(1:H_1);
% lre5=lre(2:H);
% lre6=lre4-lre5;
% [mxlre6,lxre6]=max(lre6);
%%%???????????????????????????
% for j=2:W % loop from column 1 to w 
%     if (lcb(j)>H2) % if colmns begins in lower half (upper have dose not have black pixels)
%         flb=1;   % flb =1 and break 
%         break;
%     end
%     if (lcb(j)>lcb(j-1)) 
%         fli=1;
%         fli2=1;
%     elseif (lcb(j)<lcb(j-1)&fli)
%         u1=u1+1;
%         fli=0;
%     end
% end  


% %%% count of pixels in the bottom 3 rows 
% bbot=sum(sum(m(H-2:H,:)))/3;
% %% count of pixels in the top 3 rows. 
% btop=sum(sum(m(1:3,:)))/3;
% 
% if (W>3)% condition to assure that the image is more than 3 rows 
%     
%     % count of pixels in the left 3 columns
% blft=sum(sum(m(:,1:3)))/3;
% % count of pixels in the right 3 columns. 
% bright=sum(sum(m(:,W-2:W)))/3;
% else
%    
%     blft=H;
% bright=H;
% end

% array carry how wide is the pixels are scattered (wide of image)
% wdbe=lre-lrb;
% % small width ( sum of rows that width is lower than five )
% smallwd=sum(wdbe(H2:H)<5);
% % width lower than half the width of the  digit 
% re=wdbe<W2;
% 
% % count 
% sre=sum(re)/H;
% sr=sum(wdbe>=W4)/H; % try make sr same as smallwd or sre later
% 
% mce69=mean(lce(1:W2));
cbe=mean(lce(1:W2)-lcb(1:W2))/H;
% for j=1:W2
%    if (lcb(j))
%        wcbe=wcbe+sum(~m(lcb(j):lce(j),j));
%    end
% end    
% lce1=lce(1:W-1);
% lce2=lce(2:W);
% lce3=lce2-lce1;
% [mxce,lxce]=max(lce3);
% lxce=lxce/lre(H);
% if (isempty(mxce))
%     mxce=0;
% end 
% if (isempty(lxce))
%    lxce=0;
% end 
 % Alltime(k)=toc(feat);    
%--------------------------------------------------------------------------
Features=[w2r,wu,w4,wrb,wd,cbe];%%%   99.6180
%Features=[w2r,wu,w4lft,w4];%ovo2
%Features=[w2r,wu,w4lft,w4,wrb,wd,wre,wce,wcb];%ovo1 Best result ever... 
  % 99.5224  with den ,h/w,  h*w feat1
%Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,im,wd,cbe,wcbe];%ovo
%Features=[HW,vol2,Vol(1),Vol(2),w2r,wu,w4lft,w4,wrb,im,wd,cbe,wcbe,mxb,smallwd,sre,mce69,u1,sr,wce,wcb,wre,btop,bbot,sle3,sle,snv,szero,mxlre6,lxre6,lxrb,mxce,lxce,blft,bright];
