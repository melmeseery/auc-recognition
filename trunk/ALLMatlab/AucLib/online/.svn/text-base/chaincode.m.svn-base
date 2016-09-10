%%% this function compute the chin code of the set of points given to it.
%%% it takes the stroke and poi=lenght of normalized chaincode
%%% it returns both the chain code and the normalized chain code
function [chain, norma] =chaincode(Stroke,poi)
data=Stroke;
% poi=20;
%cc=Stroke;
% load strokes
% data=strokes.stroke_2;
sized=size(data);
for i=1:length(data)-1
startpoint=data(i,:);
nextpoint=data(i+1,:);
diff=nextpoint-startpoint;

if diff(1,1)>0&diff(1,2)>0
    cc(i)=2;
elseif diff(1,1)>0&diff(1,2)==0
    cc(i)=1;
elseif diff(1,1)>0&diff(1,2)<0
    cc(i)=8;
elseif diff(1,1)==0&diff(1,2)<0
    cc(i)=7;
elseif diff(1,1)<0&diff(1,2)<0
    cc(i)=6;
elseif diff(1,1)<0&diff(1,2)==0
    cc(i)=5;
elseif diff(1,1)<0&diff(1,2)>0
    cc(i)=4;
elseif diff(1,1)==0&diff(1,2)>0

    cc(i)=3;
elseif diff(1,1)==0&diff(1,2)==0
    i=i+1;
%         cc(i)=cc(i-1);
end
end
cc=cc(find(cc~=0));
chain=cc';  % this is free man chain code
%%%% from here i will start the normalization code 
cc_delta= cc(1:end-1)-cc(2:end);
cc_acc=cc_delta(1:end-1)-cc_delta(2:end);
% plot(data(:,1),data(:,2))
% NORMALIZATION
% NORMALIZATION
% if(length(codechain)>10)
    codesub=abs(cc(2:end)-cc(1:end-1));
    changestate=find(codesub~=0)+1;
    deletedonly=abs(changestate(2:end)-changestate(1:end-1));
    deletestate=find(deletedonly==1);
    new=changestate(deletestate);
    cc(new)=0;
    if(cc(1)~=cc(2))
        cc=[0 cc(2:end)];
    end
    dd=find(cc~=0);
    codechainnew1=cc(dd);
     if codechainnew1(end)~=codechainnew1(end-1);
     codechainnew2=codechainnew1(1:end-1);
     else
         codechainnew2=codechainnew1;
     end
     len_chain_2=length(codechainnew2);
     codesub_2=abs(codechainnew2(2:end)-codechainnew2(1:end-1));
     changestate_2=find(codesub_2~=0);
     if length(changestate_2)>0
     ch_2=[changestate_2(1) abs(changestate_2(2:end)-changestate_2(1:end-1))  ];
     ch_2_new=[ch_2 length(codechainnew2)-sum(ch_2)];
     changestatefinally(1)=ch_2_new(1);
     for jj=2:length(ch_2_new)
     changestatefinally(1,jj)=changestatefinally(jj-1)+ch_2_new(jj);
     end
     
     nor=round((ch_2_new./sum(ch_2_new))*poi);

     rep=codechainnew2(changestatefinally);
     norma=[];
     for jj=1:length(nor)
   norma=[norma ones(1,nor(jj))*rep(jj)];
     end
     else
         norma=codechainnew2;
     end
%      length(norma)
     if length(norma)>poi
         norma=norma(1:poi);
     end
     if length(norma)<poi
         lenreq=poi-length(norma);
         norma=[norma ones(1,lenreq)*norma(end)];
     end
norma=norma';
