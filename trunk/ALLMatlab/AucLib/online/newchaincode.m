% clc
% clear all
% close all

% data=[rand(100,2)];
% c=[6930 10576
% 6927 10575
% 6924 10574
% 6921 10573
% 6918 10572
% 6914 10573
% 6911 10573
% 6908 10574
% 6903 10573
% 6900 10574
% 6897 10574
% 6893 10572
% 6890 10572
% 6887 10570
% 6884 10569
% 6881 10569
% 6878 10567
% 6873 10565
% 6869 10563
% 6866 10561
% 6863 10558
% 6862 10555
% 6860 10552
% 6859 10549
% 6858 10545
% 6857 10542
% 6857 10539
% 6859 10536
% 6863 10534
% 6867 10532
% 6871 10530
% 6875 10529
% 6878 10528
% 6881 10527
% 6884 10526
% 6887 10524
% 6891 10524
% 6894 10522
% 6897 10521
% 6901 10521
% 6905 10520
% 6908 10519
% 6911 10519
% 6915 10519
% 6918 10518
% 6922 10517
% 6926 10517
% 6930 10517
% 6933 10516
% 6937 10516
% 6940 10516
% 6944 10517
% 6948 10517
% 6953 10517
% 6959 10517
% 6963 10516
% 6967 10516
% 6971 10516
% 6968 10515
% 6964 10516
% 6961 10516
% 6957 10515
% 6954 10515
% 6950 10516
% 6945 10516
% 6942 10516
% 6938 10515
% 6933 10515
% 6929 10515
% 6924 10515
% 6920 10515
% 6917 10515
% 6914 10515
% 6911 10514
% 6907 10513
% 6902 10512
% 6896 10512
% 6893 10512
% 6888 10510
% 6882 10510
% 6874 10509
% 6867 10509
% 6862 10507
% 6856 10506
% 6850 10506
% 6844 10506
% 6838 10506
% 6834 10505
% 6831 10505
% 6826 10504
% 6820 10503
% 6814 10503
% 
% 
% ];
% 
% 
% c=[6738 10548
% 6739 10545
% 6742 10541
% 6743 10536
% 6743 10530
% 6745 10525
% 6746 10519
% 6747 10515
% 6747 10511
% 6747 10507
% 6744 10505
% 6741 10505
% 6736 10505
% 6733 10506
% 6730 10509
% 6725 10512
% 6720 10518
% 6718 10523
% 6715 10527
% 6712 10531
% 6713 10527
% 6714 10522
% 6715 10518
% 6717 10514
% 6716 10510
% 6712 10507
% 6706 10504
% 6701 10504
% 6697 10504
% 6691 10506
% 6684 10509
% 6677 10513
% 6670 10517
% 6666 10522
% 6663 10527
% 6660 10531
% 6659 10535
% 6658 10538
% 6658 10541
% 
% ];
%poi=20;slices=16;
% load strokes
% c=strokes2.stroke_14;
% 
% the new chain code based on hany asumptions for angle instead of
% directions 
%poi length of output chain code 
% slice number of divisions of the 360 degreees 
function [chain,norma]=newchaincode(Stroke,poi,slices)

% [newPAWPointsWOResampling]=preprocessing(c,3, 1);
% L(1)=0;
% for i=2:length(newPAWPointsWOResampling(:,1))
%     L(i)=L(i-1)+sqrt((newPAWPointsWOResampling(i,1)-newPAWPointsWOResampling(i-1,1))^2+(newPAWPointsWOResampling(i,2)-newPAWPointsWOResampling(i-1,2))^2);
% end
% 
% 
% [newPAWPoints]=resam(newPAWPointsWOResampling, (L(length(newPAWPointsWOResampling(:,1)))/100));
% 
% 
% % c=[smooth(smooth(newPAWPoints(:,1))) smooth(smooth(newPAWPoints(:,2)))];
% 
% c=[smooth(smooth(newPAWPoints(:,1))) smooth(smooth(newPAWPoints(:,2)))];
% plot(c(:,1),c(:,2),'.')
data=Stroke;
datanew=data(:,1)+sqrt(-1)*data(:,2);
datasub=(angle(datanew(2:end,1)-datanew(1:end-1,1))*180)/pi;
index=find(datasub<0);
datasub(index)=datasub(index)+360;
segmentdirection=datasub;
% div=360/6;   % the number under / is the nuber of divisions or stats.
% cc=ceil(datasub/div)';
% ccold=cc;
% 
% for ii=2:length(cc)
%     
%     if datasub(ii,1)==0
%        
%         cc(:,ii)=ccold(:,ii-1);
%     else 
%         
%          cc(:,ii)=ccold(:,ii);
%     end
%     
% end
% break


% segmentdirection(find(segmentdirection<0))=segmentdirection(find(segmentdirection<0))+360;
% for i=1:length(segmentdirection)
% if segmentdirection(i)>=22.5&&segmentdirection(i)<22.5+45
%     sd(i)=1;
% elseif (segmentdirection(i)>=22.5+45) && (segmentdirection(i)<90+22.5)
%     sd(i)=2;
% elseif segmentdirection(i)>=90+22.5&&segmentdirection(i)<135+22.5
%     sd(i)=3;
% elseif segmentdirection(i)>=135+22.5&&segmentdirection(i)<180+22.5
%     sd(i)=4;
% elseif segmentdirection(i)>=180+22.5&&segmentdirection(i)<225+22.5
%     sd(i)=5;
% elseif segmentdirection(i)>=225+22.5&&segmentdirection(i)<270+22.5
%     sd(i)=6;
% elseif segmentdirection(i)>=270+22.5&&segmentdirection(i)<315+22.5
%     sd(i)=7;
% elseif segmentdirection(i)>=315+22.5||segmentdirection(i)<22.5
%     sd(i)=8;
% end
% end
% 
% cc=sd;

Div=360/slices;
%chain=Div;

for i=1:length(segmentdirection)
    if segmentdirection(i)<360-Div && segmentdirection(i)>(2*slices-1)*Div
        sd(i)=1;
    else
        sd(i)=ceil(segmentdirection(i)/Div);
    end
end

% for i=1:length(segmentdirection)
% if segmentdirection(i)>=30&&segmentdirection(i)<30+60
%     sd(i)=2;
% elseif (segmentdirection(i)>=60+30) && (segmentdirection(i)<90+60)
%     sd(i)=3;
% elseif segmentdirection(i)>=90+60&&segmentdirection(i)<150+60;
%     sd(i)=4;
% elseif segmentdirection(i)>=150+60&&segmentdirection(i)<210+60
%     sd(i)=5;
% elseif segmentdirection(i)>=210+60&&segmentdirection(i)<270+60
%     sd(i)=6;
% elseif segmentdirection(i)>=270+60||segmentdirection(i)<30
%     sd(i)=1;
% % elseif segmentdirection(i)>=270+22.5&&segmentdirection(i)<315+22.5
% %     sd(i)=7;
% % elseif segmentdirection(i)>=315+22.5||segmentdirection(i)<22.5
% %     sd(i)=8;
% end
% end
% % 
%%%% the sd is un normalized chain code 
nonnorma=sd;
cc=sd;
chain=sd;

% for i=1:length(segmentdirection)
% if segmentdirection(i)>=36&&segmentdirection(i)<36+72
%     sd(i)=1;
% elseif (segmentdirection(i)>=72+36) && (segmentdirection(i)<180)
%     sd(i)=2;
% elseif segmentdirection(i)>=180&&segmentdirection(i)<252;
%     sd(i)=3;
% elseif segmentdirection(i)>=252&&segmentdirection(i)<324
%     sd(i)=4;
% elseif segmentdirection(i)>=324||segmentdirection(i)<36
%     sd(i)=5;
% % elseif segmentdirection(i)>=270+60||segmentdirection(i)<30
% %     sd(i)=6;
% % elseif segmentdirection(i)>=270+22.5&&segmentdirection(i)<315+22.5
% %     sd(i)=7;
% % elseif segmentdirection(i)>=315+22.5||segmentdirection(i)<22.5
% %     sd(i)=8;
% end
% end
% 
% cc=sd;























% cc_delta= cc(1:end-1)-cc(2:end);
% cc_acc=cc_delta(1:end-1)-cc_delta(2:end);

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
nonnorma=nonnorma';