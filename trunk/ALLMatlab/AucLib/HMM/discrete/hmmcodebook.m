function [cb,K,T] = hmmcodebook(Y,Kmax)
%  hmmcodebook --> Codebook generation for HMM recognizer.
%
%  <Synopsis>
%    [cb,K,T,dist] = hmmcodebook(data,N,deltaN,M,Q,Kmax)
%
%  <Description>
%    The function makes a codebook cb containing feature vector prototypes
%    based on training sequences defined by the cell array data. The
%    length of the cell array corresponds to the number of words, and
%    each cell is a new cell array with filenames for occurrences of this
%    spoken word. For each sequence s, a frame based analysis is performed
%    using the function y = hmmfeatures(s,N,deltaN,M,Q) to give observation
%    vectors (columns of y). The feature vectors for all sequences are
%    concatenated and then vector quantized (clustered) into K < Kmax
%    feature vector prototypes (centroids). The number of observation
%    vectors used to generate the codebook is returned in T, and the VQ
%    average distortion is returned in dist.
%
%  <See Also>
%    hmmfeatures --> Feature extraction for HMM recognizer.
%    kmeans      --> Trains a k-means cluster model.

%  <References>
%  [1] J.R Deller, J.G. Proakis and F.H.L. Hansen, "Discrete-Time
%      Processing of Speech Signals", IEEE Press, chapter 12, (2000).
%
%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------

% R = length(data);                     % Number of words to be recognized.
% Y = [];
% for (i = 1:R)
%     i
%   L = length(data{i});                % Number of occurrences of i'th word.
%   for (j = 1:L)
%     s = data{i}{j};              % Get file name from data structure.
%    % fprintf(1,'... Reading file:  %s\n',file_s);
%    % s = load(file_s);                 % Load word signal from file.
%     y = hmmfeatures(s);  % Extract feature vectors.
%     Y = [Y y];                        % Concatenate vectors for all sequences.
%   end
% end
% [Yc,c,errlog] = kmeans(Y, Kmax,'Distance','cityblock');
%  [Yc,c,errlog] = kmeans(Y',Kmax,5000); % Vector quantization.
[cb]=vqlbg(Y,Kmax);
% cb = Yc(unique(c),:)';                % Only use clusters with assignments.

[N,K] = size(cb);                     % Codebook symbol size K.
[N,T] = size(Y);                      % Vector dim and number of vectors.
% dist  = sqrt(errlog(end))/T;          % VQ average distortion measure.

%-----------------------------------------------------------------------
% End of function hmmcodebook
%-----------------------------------------------------------------------
