clc
clear all
close all
% load data_Y
% load data
load dataclass
% for j=1:10
%  for kk=1:length(data{j})
% % for kk=1:2
% dataa{j}(:,:,kk) = Y(:,8*(kk-1)+1:8*kk)';
% end
% end
% for ii=1:10
% 
%     if 1
%     disp('x')
%   O = 8; % number obesrvation
%   T = 25;   
% %    length of one columns of observation
%    nex = length(dataa{1,ii,:});    % length data 
%   M = 2; 
% %   Number of mixtures
%   Q = 5; %number of states
% else
%   O = 8;          %Number of coefficients in a vector 
%   T = 25;         %Number of vectors in a sequence 
%    nex = length(dataa{1,ii,:});        %Number of sequences 
%   M = 1;          %Number of mixtures 
%   Q = 8;          %Number of states 
% end
% cov_type = 'full';
% 
% 
%     ii
% data=dataa{ii};
% % data = randn(O,T,nex);
% 
% % initial guess of parameters
% prior0 = normalise(rand(Q,1));
% x =rand(Q,Q) ;
% for i=2:length(x(:,1))
% x(i,1:i-1)=0;
% end
% transmat0=mk_stochastic(x);
% 
% if 0
%   Sigma0 = repmat(eye(O), [1 1 Q M]);
%   % Initialize each mean to a random data point
%   indices = randperm(T*nex);
%   mu0 = reshape(data(:,indices(1:(Q*M))), [O Q M]);
%   mixmat0 = mk_stochastic(rand(Q,M));
% else
%     disp('x')
%   [mu0, Sigma0] = mixgauss_init(Q*M, data, cov_type);
%   mu0 = reshape(mu0, [O Q M]);
%   Sigma0 = reshape(Sigma0, [O O Q M]);
%   mixmat0 = mk_stochastic(rand(Q,M));
% end
% 
% [LL, prior1, transmat1, mu1, Sigma1, mixmat1] = ...
%     mhmm_em(data, prior0, transmat0, mu0, Sigma0, mixmat0, 'max_iter', 100);
% prior{ii}= prior1;transmat{ii}= transmat1;mu{ii}=mu1; sigma{ii}= Sigma1;mixmat{ii}=mixmat1;
% end
load mixture2
for jjj=1:100
    for iii=1:10
    loglik(jjj,iii) = mhmm_logprob(dataa{1,1}(:,:,jjj), prior{iii}, transmat{iii}, mu{1,iii}, sigma{1,iii},100*ones(8,1));
    end
end