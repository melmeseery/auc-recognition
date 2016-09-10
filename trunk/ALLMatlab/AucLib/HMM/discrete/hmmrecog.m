function [logp,guess] = hmmrecog(data,A_m,B_m,pi_m,cb,isOnline,varargin)
%  hmmrecog --> HMM based word classifier.
%
%  <Synopsis>
%    [logp,guess] = hmmrecog(data,A_m,B_m,pi_m,cb,N,deltaN,M,Q)
%
%  <Description>
%    The function implements HMM based recognition, where the cell-array
%    data contains filenames for the spoken words to be recognized.
%    For each word, a frame based analysis is performed using the
%    function y = hmmfeatures(s,N,deltaN,M,Q) to give observation
%    vectors, which are then vector quantized into the possible
%    codebook vectors given by cb. The resulting observation sequence
%    and the probability measures for all the HMMs, given by the
%    cell-arrays A_m, B_m, and pi_m, are then used to calculate the
%    log-likelihoods for the HMMs (column of logp). The word
%    associated with the HMM of highest log-likelihood is declared
%    to be the recognized word, and the index is returned in guess.
%    This procedure is repeated for all the words in data.
%
%  <See Also>
%    hmmfeatures --> Feature extraction for HMM recognizer.
%    hmmlogp     --> Log-likelihood for given observation sequence and HMM.
%    hmmtrain    --> Train HMMs with multiple observation sequences.

%  <References>
%  [1] J.R Deller, J.G. Proakis and F.H.L. Hansen, "Discrete-Time
%      Processing of Speech Signals", IEEE Press, chapter 12, (2000).
%
%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------

[dim,K] = size(cb);            % Number of observation symbols.
R = length(A_m);               % Number of trained words.
L = length(data);              % Number of words to be recognized.
logp = zeros(R,L);

for (i = 1:L)
  s = data{i};            % Get file name from data structure.
  %fprintf(1,'... Reading file:  %s\n',file_s);
  %s = load(file_s);                 % Load word signal from file.
  y = hmmfeatures(s,isOnline,varargin{:});  % Extract feature vectors.
  [dim,T] = size(y);                % Number of vectors T.

  yk = zeros(T,1);
  for (t = 1:T)                     % Vector quantization.
    [dist,k] = min(sum((cb-repmat(y(:,t),1,K)).^2));
    yk(t) = k;
  end

  for (j = 1:R)          % Estimate log-likelihood for each model.
    logp(j,i) = hmmlogp(yk,A_m{j},B_m{j},pi_m{j});
  end
end
[loglike,guess] = max(logp);

%-----------------------------------------------------------------------
% End of function hmmrecog
%-----------------------------------------------------------------------
