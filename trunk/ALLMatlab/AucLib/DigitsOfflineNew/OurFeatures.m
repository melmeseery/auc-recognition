



function [Features]=OurFeatures(Image,rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)%digit=0;digit=3;digit=3;digit=3;
            
         % Image=preprocess(Image);
            [Orh Orw]=size(Image);
            
  
if (  rowColumn==1)
             c=constantSize;
%             % now resize image to the new using  Bicubic interpolation the
%             % result will be new with new 20*20
             Im=imresize(Image,[NaN  c],'bicubic');
elseif  (rowColumn==2)
        r=constantSize;
%             % now resize image to the new using  Bicubic interpolation the
%             % result will be new with new 20*20
             Im=imresize(Image,[r NaN],'bicubic');
 
 elseif (rowColumn==0)
          Im=Image;
    
end 

       m28=~Im;

       [hi wi]=size(m28);
        m=m28;% binary matrix of the digit with H rows and W columns
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
    for j=1:W % for each rwo 
        if (~m(i,j))% if white ()
                                            %              _ _
            if(j>lrb(i)&j<lre(i)&i<lcb(j)) %% for 3 and 7   |
                wu=wu+1;
                im=i;
                L3(i,j)=1;
            end
            if(j>lrb(i)&j<lre(i)&i>lce(j))%% for 8 _|_
                 wd=wd+1;
                 L8(i,j)=1;
            end
            if(j>lre(i)&i>lcb(j)&i<lce(j))  % for 2 -|
                 w2r=w2r+1;
                 L2(i,j)=1;
            end
            if(j>lrb(i)&j<lre(i)&i>lcb(j)&i<lce(j))  %%% for 5  -|-
                 w4=w4+1;  
                 L5(i,j)=1;
                 if(j<W2 )
                     L9(i,j)=1;
                 end 
            end
            if(i>lcb(j)&i<lce(j)&j<lrb(i)) %% 4 |-
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
            
            if(r>lcb(c) &   r<lce(c)) %% | in between row start and end 
              Ltd(r,c)=1;  
            end
            if(c>lrb(r) &   c<lre(r))
            % -- in between column start and end. 
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
 draw=0;
if (draw==1)
figure
subplot(2,10,1)
imshow(Image)
title('origanal')
 
subplot(2,10,2)
imshow(m)
title('maped');
subplot(2,10,3)
imshow(L2);title('L2 -|')
subplot(2,10,4)
imshow(L3);title('L3 T ')
subplot(2,10,5)
imshow(L4);title('L4  |-')
subplot(2,10,6)
imshow(L5);title('L5  -|-')
subplot(2,10,7)
imshow(L6);title('L6  ')
subplot(2,10,8)
imshow(L8);title('L8 _|_')
subplot(2,10,9)
imshow(L9);title('L9  ')
subplot(2,10,10)
imshow(Lrt);title('Lrt  |_' )
subplot(2,10,11)
imshow(Ltl);title('Ltl _|')
subplot(2,10,12)
imshow(Ltd);title('Ltd  | ')
subplot(2,10,13)
imshow(Lld);title('Lld  7 ')
subplot(2,10,14)
imshow(Lrl);title('Lrl -- ')
subplot(2,10,15)
imshow(Lrd);title('Lrd  [ ')



subplot(2,10,16)
imshow(Lce);title('Lce  ')
subplot(2,10,17)
imshow(Lcb);title('Lcb   ')
subplot(2,10,18)
imshow(Lre);title('Lre   ')
subplot(2,10,19)
imshow(Lrb);title('Lrb ')
end
  cbe=lce-lcb;
  % cbe=cbe/H;   
L2Feat =getMultiFeatures(  L2 ,ByColumn,NoSplits,SizeORSplit,sConstant);
L3Feat =getMultiFeatures(  L3 ,ByColumn,NoSplits,SizeORSplit,sConstant);
L4Feat =getMultiFeatures(  L4 ,ByColumn,NoSplits,SizeORSplit,sConstant);
L5Feat =getMultiFeatures(  L5 ,ByColumn,NoSplits,SizeORSplit,sConstant);
L6Feat =getMultiFeatures(  L6 ,ByColumn,NoSplits,SizeORSplit,sConstant);
L8Feat =getMultiFeatures(  L8 ,ByColumn,NoSplits,SizeORSplit,sConstant);
L9Feat =getMultiFeatures(  L9 ,ByColumn,NoSplits,SizeORSplit,sConstant);

LrbFeat =getMultiFeatures(  Lrb ,ByColumn,NoSplits,SizeORSplit,sConstant);
LreFeat =getMultiFeatures(  Lre ,ByColumn,NoSplits,SizeORSplit,sConstant);
LcbFeat =getMultiFeatures(  Lcb ,ByColumn,NoSplits,SizeORSplit,sConstant);
LceFeat =getMultiFeatures(  Lce ,ByColumn,NoSplits,SizeORSplit,sConstant);

LrtFeat =getMultiFeatures(  Lrt ,ByColumn,NoSplits,SizeORSplit,sConstant);
LtlFeat =getMultiFeatures(  Ltl ,ByColumn,NoSplits,SizeORSplit,sConstant);
LtdFeat =getMultiFeatures(  Ltd ,ByColumn,NoSplits,SizeORSplit,sConstant);
LldFeat =getMultiFeatures(  Lld ,ByColumn,NoSplits,SizeORSplit,sConstant);
LrlFeat =getMultiFeatures(  Lrl ,ByColumn,NoSplits,SizeORSplit,sConstant);
LrdFeat =getMultiFeatures(  Lrd ,ByColumn,NoSplits,SizeORSplit,sConstant);
 
%%%%%%%%%%%%%%%%%%%%%%%%%5
%%%%% L2Feat2=splitFeatures( L2, SizeORSplit, sConstant )

black=sum(sum(m));
white= (wi*hi)-black;
 
w2r=sum(sum(L2));
wu=sum(sum(L3));
w4lft=sum(sum(L4));
w4=sum(sum(L5));
wrb=sum(sum(L6));
wd=sum(sum(L8));
% % extract features ... for each column 

  %  feats=[L2Feat ];
    HOverW=Orh/Orw;
    area=Orh* Orw;
%%%%%  Exp 1 98.54
%feats=[  L4Feat L2Feat  L3Feat   L5Feat  L8Feat   LrtFeat  LtlFeat   LldFeat   LrdFeat];
% %%%%%  Exp 2 98.54
   feats=[  HOverW  area L4Feat L2Feat  L3Feat   L5Feat  L8Feat   LrtFeat  LtlFeat   LldFeat   LrdFeat];
% %%%%%  Exp 3 
%     feats=[  w2r wu  w4 w4lft wrb wd L4Feat L2Feat  L3Feat   L5Feat  L8Feat   LrtFeat  LtlFeat   LldFeat   LrdFeat];
% %%%%%  Exp 4 
%  feats=[  HOverW  area    L4Feat L2Feat  L3Feat   L5Feat  L8Feat  ];
% %%%%%  Exp 5 
%      feats=[  L4Feat L2Feat  L3Feat   L5Feat  L8Feat    ];
% %%%%%  Exp 6 
%   feats=[        LrtFeat  LtlFeat   LldFeat   LrdFeat];
         
    
  %feats=[L2Feat  L3Feat  L4Feat L5Feat  L6Feat L8Feat  L9Feat LrbFeat   LreFeat  LcbFeat  LceFeat LrtFeat  LtlFeat LtdFeat  LldFeat LrlFeat  LrdFeat];
  
 
  
      %
%     feats=[L2Feat' L3Feat' L4Feat' L5Feat' L6Feat' L8Feat' L9Feat'
%          LrbFeat'   LreFeat'    LcbFeat'  LceFeat'
%          LrtFeat'  LtlFeat' LtdFeat'  LldFeat' LrlFeat'  LrdFeat' ];
 
%--------------------------------------------------------------------------
 
%  feats=[L2Feat' L3Feat' L4Feat' L5Feat' L6Feat' L8Feat' L9Feat' cbe' LreFeat'  LrbFeat'];
  
 Features=feats;
 
