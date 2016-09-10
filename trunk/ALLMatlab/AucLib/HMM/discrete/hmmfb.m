function [A,B,pi1,loglike] = hmmfb(ytrain,S,K,maxiter,tol,varargin)
%  hmmfb --> F-B reestimation algorithm for training of HMM.
%
%  <Synopsis>
%    [A,B,pi1,loglike] = hmmfb(ytrain,S,K,maxiter,tol)
%
%  <Description>
%    The function implements the F-B reestimation algorithm used
%    to train a HMM based word classifier. The input arguments are
%    the L training sequences (same word), i.e., vectors in the
%    cell-array ytrain, the HMM parameters, S and K, which are the
%    number of states and the number of symbols (codebook vectors),
%    and maxiter and tol are stopping criteria in the F-B algorithm,
%    e.g., 5000 and 1e-3.
%
%    The output arguments are the S-by-S state transition matrix A,
%    the K-by-S observation probability matrix B, and the initial state
%    probability vector pi1. Finally, the output vector loglike is
%    the log-likelihood as function of the iteration number in the
%    F-B reestimation algorithm.
%
%  <See Also>
%    hmmtrain --> Train HMMs with multiple observation sequences.

%  <References>
%  [1] J.R Deller, J.G. Proakis and F.H.L. Hansen, "Discrete-Time
%      Processing of Speech Signals", IEEE Press, chapter 12, (2000).
%
%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------

L = length(ytrain);                     % Number of training sequences.

% A = rand(S,S);
% %A(2,1)=0;;A(3,1)=0;A(3,2)=0;   to change topology . 
%  A = A./repmat(sum(A),S,1); % Initial random model
% %B = rand(K,S);  % the 
% %B = B./repmat(sum(B),K,1); % with probability sum
% B=A;
% pi1 = rand(S,1); pi1 = pi1/sum(pi1);      % normalized to one.
[A,B,pi1]=unPackHmmVariables(S,K,varargin{:})
loglike = zeros(maxiter,1);             % Log-likelihood measures.
loglike_old = -inf;                     % From previous iteration.
loglike_dif = inf;                      % Difference.

k = 0;
while (k < maxiter) & (loglike_dif >= tol)
  k = k + 1;                            % Iteration counter.
  pi_c = 0; A_c = 0; B_c = 0;           % Initialize sum over
  loglike_c = zeros(L,1);               % sequences.

  for (l = 1:L)
    y = ytrain{l};                      % l'th sequence.
    T = length(y);                      % Length of l'th seq.

    alpha = zeros(S,T);                 % Forward recursion.
    beta  = zeros(S,T);                 % Backward recursion.
    scale = zeros(T,1);                 % Scale factors.

    alpha(:,1) = pi1.*B(y(1),:)';       % Alpha for t=1.
    scale(1)   = sum(alpha(:,1));       % Scale factor for t=1.
    alpha(:,1) = alpha(:,1)/scale(1);   % Scaled alpha for t=1.
    for (t = 2:T)
      alpha(:,t) = A*alpha(:,t-1).*B(y(t),:)';
      scale(t)   = sum(alpha(:,t));     % Scale factor for t.
      alpha(:,t) = alpha(:,t)/scale(t); % Scaled alpha for t.
    end

    beta(:,T) = 1/scale(T);             % Beta for t=T.
    for (t = (T-1):-1:1)                % Scaled beta for t.
      beta(:,t) = A'*(beta(:,t+1).*B(y(t+1),:)')/scale(t);
    end

    loglike_c(l) = sum(log10(scale));   % Log-likelihood.

    gamma_c = (alpha.*beta).*repmat(scale',S,1);
    A_c  = A_c  + (alpha(:,1:T-1)*(B(y(2:T),:)'.*beta(:,2:T))').*A';
    B_c  = B_c  + (gamma_c*(repmat([1:K],T,1)==repmat(y(:),1,K)))';
    pi_c = pi_c + gamma_c(:,1);
  end
  
  loglike(k)  = sum(loglike_c);
  loglike_dif = loglike(k) - loglike_old;
  loglike_old = loglike(k);
    
  pi1 = pi_c/sum(pi_c);
  A   = A_c./repmat(sum(A_c),S,1);
  B   = B_c./repmat(sum(B_c),K,1);

  fprintf(1,'... Log likelihood: %6.3f\n',loglike(k));
end

loglike = loglike(2:k);

%-----------------------------------------------------------------------
% End of function hmmfb
%-----------------------------------------------------------------------
