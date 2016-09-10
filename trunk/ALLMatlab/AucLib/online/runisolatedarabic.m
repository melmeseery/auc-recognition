% clc
% clear all
% close all
%  load datadata
%  isolated=datadata{1,1};
% for i=1:length(isolated)
%    
%    for j=1:length(isolated{i})
%        
%        feature{i}{j}=chaincode(isolated{i}{j},60) ;   
%        
%    end
%      
% end
% data=[];
% labels=[];
% for jj=1:length(feature)
%     if length(feature{jj})>1
%     for kk=1:length(feature{jj})
%         
%         data=[data feature{jj}{kk}];
%         labels=[labels jj];
%     end
%     end
% end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%55
%gradient
%%%%%%%%%%%%%%%%%%%%%%%%%%%
% for i=1:length(isolated)
%    
%    for j=1:length(isolated{i})
%        
%        feature{i}{j}=offline(isolated{i}{j}) ;   
%        
%    end
%      
% end
% data=[];
% labels=[];
% for jj=1:length(feature)
%     if length(feature{jj})>1
%     for kk=1:length(feature{jj})
%         
%         data=[data feature{jj}{kk}];
%         labels=[labels jj];
%     end
%     end
% end









% P=data;
% Tc=labels;
% T = ind2vec(Tc) ;
% net = newpnn(P,T);
class=5;
Yc=[];
for kl=1:length(feature{class})
 Y = sim(net,feature{class}{kl});
 Yc = [Yc vec2ind(Y)];
end

length(find(Yc==class))/length(feature{class})
