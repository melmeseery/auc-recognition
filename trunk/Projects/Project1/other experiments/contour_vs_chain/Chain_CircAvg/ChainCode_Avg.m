function avg= ChainCode_Avg(chain_code)

% % if( ~isempty(find(chain_code>8)) | ~isempty(find(chain_code<0)) )
% %     error('chain code not valid')
% % end
% % 
% % N=length(chain_code);
% % if(mod(N,2)~=0)
% %     N=N-1;
% % end
% % 
% % avg=0;
% % for n=1:2:N
% %     a=chain_code(n);
% %     b=chain_code(n+1);
% %     
% %     if(abs(a-b)<5)
% %         avg=avg+(a+b)/N;
% %     else
% %         avg=avg+((max(a,b)-8)+min(a,b))/N;
% %     end
% % end
% % 
% % if(avg<0)
% %     avg=avg+8;
% % end
% 
% 
% 
% if(length(chain_code)==1)
%     avg=chain_code;
% else
%     temp=[];
%     for n=1:length(chain_code)-1
%         a=chain_code(n);
%         b=chain_code(n+1);
%         if(abs(a-b)<5)
%             c=(a+b)/2;
%         else
%             c=((max(a,b)-8)+min(a,b))/2;
%         end
%         if(c<0)
%             c=c+8;
%         end
%         temp=[temp; c];
%     end
%     chain_code=temp;
%     
%     avg=ChainCode_Avg(chain_code);
%     
% end
%     

for n=0:7
    chain_code(chain_code==n)=(360-n*45)/180*pi;
end

%calculating circular mean
S=sum(sin(chain_code));
C=sum(cos(chain_code));

avg=atan2(S,C)/pi*180;
% if(C>0)
%     avg=atan(S/C);
% else
%     avg=atan(S/C)+pi;
% end
% avg=avg/pi*180
    

if(avg<0)
    avg=avg+360;
end

avg=8-avg/360*8;
if(avg==8)
    avg=0;
end

% a=45/2;
% if(or((and(avg>=0 , avg<=a)) , (and(avg<=360 , avg>=360-a))) )
%     avg= 0;
% elseif(and(avg>=a , avg<=3*a))
%     avg=7;
% elseif(and(avg>=3*a , avg<=5*a))
%     avg=6;
% elseif(and(avg>=5*a , avg<=7*a))
%     avg=5;
% elseif(and(avg>=7*a , avg<=9*a))
%     avg=4;
% elseif(and(avg>=9*a , avg<=11*a))
%     avg=3;
% elseif(and(avg>=11*a , avg<=13*a))
%     avg=2;
% elseif (and(avg>=13*a , avg<=15*a))
%     avg=1;
% end
    


