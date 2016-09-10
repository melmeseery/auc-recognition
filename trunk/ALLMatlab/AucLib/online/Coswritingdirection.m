 function [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=Coswritingdirection(Stroke)
% function [cos_alphaa sin_alphaa  cos_betaa sin_betaa alphaa betaa ]=writingdirection(c)
c=Stroke;
datan=c(:,1)+sqrt(-1).*c(:,2);



for ill=2:length(datan)-1  % alpha(t)
   
cos_alpha(ill)=cos((angle((datan(ill-1,1)-datan(ill+1,1)))));

sin_alpha(ill)=sin((angle((datan(ill-1,1)-datan(ill+1,1)))));

end

for ill=3:length(cos_alpha)-1  % alpha(t)
cos_beta(ill)= 1.*cos_alpha(ill-1).*cos_alpha(ill+1)+1.*sin_alpha(ill-1).*sin_alpha(ill+1);
sin_beta(ill)=1.*sin_alpha(ill-1).*cos_alpha(ill+1)- 1.*cos_alpha(ill-1).*sin_alpha(ill+1);

end
cos_alphaa=cos_alpha(3:end-1)';
sin_alphaa=sin_alpha(3:end-1)';


cos_betaa=cos_beta(3:end)';
sin_betaa=sin_beta(3:end)';

alphaa=real(acos(cos_alphaa));
betaa=real(acos(cos_betaa));

dalpha=alphaa(1:end-1)-alphaa(2:end);%% this is delta alpha and i can do the same for 
cos_dalpha=cos(dalpha);
sin_dalpha=sin(dalpha);
% if (~isreal(alphaa)  )
%     Stroke
% end 
% if (~isreal(betaa)  )
%     Stroke
% end
