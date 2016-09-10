function x=FixedSizeChain_OrdinaryAvg_2(I,N)

chain_code= ChainCode(I);

% while 1
%     if(length(chain_code)<N)
%         temp=[];
%         for i=1:length(x_profile)-1
%             temp=[temp; chain_code(i); mean([chain_code(i); chain_code(i+1)])];
%         end
%         chain_code=temp;
%     else
%         break;
%     end
% end

rem= ceil(length(chain_code)/N)*N-length(chain_code);
for r=1:rem
    L=length(chain_code);
    a=ceil(rand*(L-2))+1;
    chain_code=[chain_code(1:a); mean([chain_code(a) chain_code(a+1)]); chain_code(a+1:end)];
end
    

segment_length=length(chain_code)/N;

x=[];
for k=1:N
    elem= mean(chain_code((k-1)*segment_length+1:k*segment_length));
    x=[x; elem];
end


x=x/7;
