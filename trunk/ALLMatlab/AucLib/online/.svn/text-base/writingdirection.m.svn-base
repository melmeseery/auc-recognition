% clc
% clear all
% close all

% data=rand(10,2);
% Writing Direction
 function [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(c)
% c=rand(500,2);
% [newPAWPointsWOResampling]=preprocessing(c,1, 1);
% L(1)=0;
% for i=2:length(newPAWPointsWOResampling(:,1))
%     L(i)=L(i-1)+sqrt((newPAWPointsWOResampling(i,1)-newPAWPointsWOResampling(i-1,1))^2+(newPAWPointsWOResampling(i,2)-newPAWPointsWOResampling(i-1,2))^2);
% end
% [newPAWPoints]=resam(newPAWPointsWOResampling, (L(length(newPAWPointsWOResampling(:,1)))/99));
% 
% c=[smooth((smooth(newPAWPoints(:,1)))) smooth((smooth(newPAWPoints(:,2))))];
% plot(c(:,1),c(:,2),'.')
% vc=1:10:100;
% data(:,1)=c(vc,1).*1000;data(:,2)=c(vc,2).*1000;

datan=c(:,1)+sqrt(-1).*c(:,2);
for ill=2:length(datan)-1  % alpha(t)
    
cos_alpha(ill)=cos((angle((datan(ill-1,1)-datan(ill+1,1)))));

sin_alpha(ill)=sin((angle((datan(ill-1,1)-datan(ill+1,1)))));
% cos_alpha(ill)=cos(alpha(ill));
%/sqrt((data(ill-1,1)-data(ill+1,1)).^2+(data(ill-1,2)-data(ill+1,2)).^2);
end

for ill=3:length(cos_alpha)-1  % alpha(t)
cos_beta(ill)= 1.*cos_alpha(ill-1).*cos_alpha(ill+1)+1.*sin_alpha(ill-1).*sin_alpha(ill+1);
sin_beta(ill)=1.*sin_alpha(ill-1).*cos_alpha(ill+1)- 1.*cos_alpha(ill-1).*sin_alpha(ill+1);
% beta(ill)=acos(cos_beta(ill));
end
cos_alphaa=cos_alpha(2:end-1)';
sin_alphaa=sin_alpha(2:end-1)';
cos_betaa=cos_beta(3:end)';
sin_betaa=sin_beta(3:end)';
alphaa=acos(cos_alphaa);
betaa=acos(cos_betaa);

dalpha=alphaa(1:end-1)-alphaa(2:end);
cos_dalpha=cos(dalpha);
sin_dalpha=sin(dalpha);
% diffalpha=alpha(2:end,1)-alpha(1:end-1,1);
% diffbeta=beta(2:end,1)-beta(1:end-1,1);