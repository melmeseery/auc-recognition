function [A_m,B_m,pi_m,loglike_m] = hmmtrain2(data,S,maxiter,tol,cb,R,Ai_m,Bi_m, pi_m ,isOnline,varargin)
%  hmmtrain --> Train HMMs with multiple observation sequences.
%
%  <Synopsis>
%    [A_m,B_m,pi_m,loglike_m] = hmmtrain(data,N,deltaN,...
%                                            M,Q,cb,S,maxiter,tol)
%
%  <Description>
%    The function implements training of multiple HMMs. The training
%    sequences are defined by the cell-array data. The length of the
%    cell-array corresponds to the number of words, and each cell is a
%    new cell array with filenames for occurrences of this spoken word.
%    For each sequence, a frame based analysis is performed using the
%    function y = hmmfeatures(s,N,deltaN,M,Q) to give observation
%    vectors, which are then vector quantized into the possible
%    codebook vectors given by cb. For each word, the (quantized)
%    observation sequences are then used to train a HMM for this word,
%    using the F-B reestimation algorithm. Here, S is the number of
%    states in the HMM, and maxiter and tol are stopping criteria in
%    the F-B algorithm, e.g., 5000 and 1e-3. The output probability
%    measures for the HMMs are returned in the cell-arrays A_m, B_m,
%    pi_m, and loglike_m.
%
%  <See Also>
%    hmmfeatures --> Feature extraction for HMM recognizer.
%    hmmfb       --> F-B reestimation algorithm for training of HMM.
%    hmmrecog    --> HMM based word classifier.

%  <References>
%  [1] J.R Deller, J.G. Proakis and F.H.L. Hansen, "Discrete-Time
%      Processing of Speech Signals", IEEE Press, chapter 12, (2000).
%
%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------

[dim,K] = size(cb);        % Get symbol size K from the codebook.
%R = 10;          % Number of words R to be recognized.

% A_m  = cell(R,1);          % Output arguments in cell-arrays.
% B_m  = cell(R,1);
% pi_m = cell(R,1);
 loglike_m = cell(R,1);

for (i = 1:R)              % Loop words.
  L = length(data{i});     % Number of occurrences of i'th word.

  ytrain = cell(L,1);      % Generate cell-array for train seq.
  for (j = 1:L)
    s = data{i}{j};             % Get file name from data.
%     fprintf(1,'... Reading file:  %s\n',file_s);
%     s = load(file_s);                % Load word signal from file.

    y = hmmfeatures(s,isOnline,varargin{:}) % Extract feature vectors.
    [dim,T] = size(y);               % Number of vectors T.
  
    yk = zeros(T,1);
    for (t = 1:T)          % Vector quantization.
       %s
       %j
       % size(repmat(y(:,t),1,K))
      [dist,k] = min(sum((cb-repmat(y(:,t),1,K)).^2));
      yk(t) = k;           % Symbol at time t.
    end;
    ytrain(j) = {yk};
  end
disp('Training the category ...  i ')
i
   [A,B,pi1,loglike] = hmmfb(ytrain,S,K,maxiter,tol,  Ai_m(i),  Bi_m(i) ,pi_m(i));  % when using Vertibi

  A_m(i)  = {A};
  B_m(i)  = {B};
  pi_m(i) = {pi1};
  loglike_m(i) = {loglike};
end

%-----------------------------------------------------------------------
% End of function hmmtrain
%-----------------------------------------------------------------------
