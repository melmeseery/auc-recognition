function [Features]=OurFeatures(Image)%digit=0;digit=3;digit=3;digit=3;

            [height width]=size(Image);
%             
% %             % get normalize hight or width to 20 
% %             if(height>width)  % 
% %                 r=20;
% %                 c= floor(width/height*20);
% %           
% %             end              
% 
             c=20
%             % now resize image to the new using  Bicubic interpolation the
%             % result will be new with new 20*20
             Ir=imresize(I,[NaN  c],'bicubic');
%             %[r c]=size(I);
             level = graythresh(Ir);
             %BW = dither(I) converts the intensity image in the matrix I to
%             %then invert its color 
%             %then change it to double 
             I=im2bw(Ir,level);
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
       m28=I;
%       [m28 ]=processResizeImage(Image);
       [hi wi]=size(m28);
        m=m28;             % binary matrix of the digit with H rows and W columns
        %feat= tic; 
        H=hi;
        W=wi;
        W_1=W-1;
        H_1=H-1;
        H2=fix(H/2);
        H3=fix(2*H/3);
        W2=fix(W/2);

%--------------------------------------------------------------------------
HW=H/W;


lcb=zeros(1,W);
lce=lcb;
lrb=zeros(1,H);
lre=lrb;
lr2b=lrb;
lr2e=lre;
lc2b=lcb;
lc2e=lce;

for i=1:W % for column 
    lc=find(m(:,i));
    llc=length(lc); % count number of zeros ( white in row )  
    if (llc)% if there is white pixel >0 
        lcb(i)=lc(1);  % first pixel  in the row  
        lce(i)=lc(length(lc)); % last  pixel in the row. 
    end
end 
for i=1:H  % same for row...
    lr=find(m(i,:));
    llr=length(lr);
    if (llr)
        lrb(i)=lr(1);  % first column in row 
        lre(i)=lr(length(lr));  %% last colum with white in row. 
    end
end
%--------------------------------------------------------------------------
%%% create a single matrix for each feature...

Lrb=zeros(size(m)); %% same as  wrb
Lre=zeros(size(m)); %% same as  wre
Lcb=zeros(size(m));%% same as  wcb
Lce=zeros(size(m));%% same as  wce
%
L2=zeros(size(m));  % old w2r  % for 2 -|
                    %              _ _
L3=zeros(size(m)); %% for 3 and 7   |    old wu
L4=zeros(size(m));  %% 4 |-            old w4lft
L5=zeros(size(m));%%% for 5  -|-       old w4
L6=zeros(size(m));  %% old wrb   
L8=zeros(size(m));%% for 8 _|_      old wd
L9=zeros(size(m));  %% old wcbe  same as l5 but in upper half

Lrt=zeros(size(m));
Ltl=zeros(size(m));
Ltd=zeros(size(m));
Lld=zeros(size(m));
Lrl=zeros(size(m));
Lrd=zeros(size(m));

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Now get the first 5 main feature
 
wu=0;w2r=0;w4lft=0;wd=0;w4=0;W4=0.25*W;im=0;
fli=0;fld=0;u1=0;fli2=0;flb=0;wcbe=0;
for i=1:H % for each columen 
    for j=W:-1:1 % for each rwo 
        if (~m(i,j))% if white ()
                                            %              _ _
            if(j>lrb(i)&j<lre(i)&i<lcb(j)) %% for 3 and 7   |
                wu=wu+1;
                im=i;
                L3(i,j)=1;
            elseif(j>lrb(i)&j<lre(i)&i>lce(j))%% for 8 _|_
                 wd=wd+1;
                 L8(i,j)=1;
            elseif(j>lre(i)&i>lcb(j)&i<lce(j))  % for 2 -|
                 w2r=w2r+1;
                 L2(i,j)=1;
            elseif(j>lrb(i)&j<lre(i)&i>lcb(j)&i<lce(j))  %%% for 5  -|-
                 w4=w4+1;  
                 L5(i,j)=1;
                 if(j<W2 )
                     L9(i,j)=1;
                 end 
            elseif(i>lcb(j)&i<lce(j)&j<lrb(i)) %% 4 |-
                w4lft=w4lft+1;
                 L4(i,j)=1;
                
            end
        end   
    end
end
%Vol=double(size(Image));
%vol2=Vol(1)*Vol(2);
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% the old wrb , wce , wcb 
for i=1:H % for each columen 
    for j=W:-1:1 % for each rwo 
          if (~m(i,j))% if white ()
                 if (j<lrb(i)&i>lcb(j))
                   L6(i,j)=1;
               end 
               if (j<lrb(i))
                   Lrb(i,j)=1;
               end 
               if (j>lre(i))
                   Lre(i,j)=1;
               end 
               if (i>lce(j))
                   Lce(i,j)=1;
               end 
                if (i<lcb(j))
                   Lcb(i,j)=1;
               end 
         end 
    end 
end 
%%%%%%%%%%%%%%%%%%%%%%% Compute the new features............
for r=1:H % for each row
    for c=1:W % for each column  
        if (~m(r,c))% if white ()
            if(r>lce(c) & c <lrb(r)) %%  |_ beloww column end and before row begin 
            Lrt(r,c)=1;
            end 
            if(r>lce(c) &   c>lre(r)) %%  _| below column end and after row end
              Ltl(r,c)=1;  
            end 
            
            if(c>lrb(r) &   c<lre(r)) %% | in between row start and end 
              Ltd(r,c)=1;  
            end 
            if(r>lcb(c) &   r<lce(c)) % -- in between column start and end. 
              Lrl(r,c)=1;  
            end 
           %%%%%%%%%%%%%%%%%%%%%%%%%%%  _
            if(r<lcb(c) &  c> lre(r) ) % | before column begin and after row end 
              Lld(r,c)=1;  
            end 
            %%%%%%%%%%%%%%%%%%%%%%%%%%%  _
             if(r<lcb(c) &   c<lrb(r) )%| before column begin and before row begin    
              Lrd(r,c)=1;  
            end 
            
            
        end 
    end 
end 

%%%%%%%%%%%%%%%%%%%%%%%%%
 
  cbe=lce-lcb;
  rowColumn=1;
  % cbe=cbe/H;
   
L2Feat =ExtractRowColumnFeatures(  L2 , rowColumn);
L3Feat =ExtractRowColumnFeatures(  L3 , rowColumn);
L4Feat =ExtractRowColumnFeatures(  L4 , rowColumn);
L5Feat =ExtractRowColumnFeatures(  L5 , rowColumn);
L6Feat =ExtractRowColumnFeatures(  L6 , rowColumn);
L8Feat =ExtractRowColumnFeatures(  L8 , rowColumn);
L9Feat =ExtractRowColumnFeatures(  L9 , rowColumn);

LrbFeat =ExtractRowColumnFeatures(  Lrb , rowColumn);
LreFeat =ExtractRowColumnFeatures(  Lre , rowColumn);
LcbFeat =ExtractRowColumnFeatures(  Lcb , rowColumn);
LceFeat =ExtractRowColumnFeatures(  Lce , rowColumn);

LrtFeat =ExtractRowColumnFeatures(  Lrt , rowColumn);
LtlFeat =ExtractRowColumnFeatures(  Ltl , rowColumn);
LtdFeat =ExtractRowColumnFeatures(  Ltd , rowColumn);
LldFeat =ExtractRowColumnFeatures(  Lld , rowColumn);
LrlFeat =ExtractRowColumnFeatures(  Lrl , rowColumn);
LrdFeat =ExtractRowColumnFeatures(  Lrd , rowColumn);
 
  
% % extract features ... for each column 
% L2Feat=sum(L2);  
% LrbFeat=sum(Lrb);
% LreFeat= sum(Lre) ;
% LcbFeat= sum(Lcb);
% LceFeat=sum(Lce) ;
% L3Feat= sum(L3);
% L4Feat= sum(L4);
% L5Feat= sum(L5);
% L6Feat=sum(L6) ;
% L8Feat= sum(L8);
% L9Feat=  sum(L9) ;
% 
% 
% LrtFeat=sum(Lrt);
% LtlFeat=sum(Ltl );
% LtdFeat=sum(Ltd );
% LldFeat=sum( Lld);
% LrlFeat=sum(Lrl );
% LrdFeat=sum(Lrd );
% 
% 
% %% normalize all features based on height... 
% cbe=cbe/H;
% L2Feat= L2Feat/H;
% LrbFeat=LrbFeat/H;
% LreFeat= LreFeat/H ;
% LcbFeat= LcbFeat/H;
% LceFeat=LceFeat/H ;
% L3Feat= L3Feat/H ;
% L4Feat= L4Feat/H  ;
% L5Feat=L5Feat/H  ;
% L6Feat=L6Feat/H   ;
% L8Feat=L8Feat/H  ;
% L9Feat=L9Feat/H;
 


  
  feats=[L2Feat L3Feat L4Feat L5Feat L6Feat L8Feat L9Feat
         LrbFeat   LreFeat    LcbFeat  LceFeat
         LrtFeat  LtlFeat LtdFeat  LldFeat LrlFeat  LrdFeat 
           ];
       
%     feats=[L2Feat' L3Feat' L4Feat' L5Feat' L6Feat' L8Feat' L9Feat'
%          LrbFeat'   LreFeat'    LcbFeat'  LceFeat'
%          LrtFeat'  LtlFeat' LtdFeat'  LldFeat' LrlFeat'  LrdFeat' ];
 
%--------------------------------------------------------------------------
 
%  feats=[L2Feat' L3Feat' L4Feat' L5Feat' L6Feat' L8Feat' L9Feat' cbe' LreFeat'  LrbFeat'];
  
 Features=feats;
 
