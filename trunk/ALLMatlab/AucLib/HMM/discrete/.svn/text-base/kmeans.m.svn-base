function [Yc,c,errlog] = kmeans(Y,K,maxiter)
%  kmeans --> Trains a k-means cluster model.
%
%  <Synopsis>
%    [Yc,c,errlog] = kmeans(Y,K,maxiter)
%
%  <Description>
%    The function uses the k-means algorithm to set the centroids of
%    a cluster model. The matrix Y represents the data which is being
%    clustered, with each row corresponding to a vector, and K is the
%    desired number of clusters. The cluster centroids are returned in
%    the matrix Yc (rows), and the cluster number for each data vector
%    are returned in the vector c. The sum of squares error function is
%    used in the algorithm, and a log of the error values after each
%    iteration is returned in errlog. The maximum number of iterations
%    is specified by maxiter.

%  <References>
%  [1] J.R Deller, J.G. Proakis and F.H.L. Hansen, "Discrete-Time
%      Processing of Speech Signals", IEEE Press, p. 71, (2000).
%
%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------

[M,N] = size(Y);              % Number of vectors and vector dim.
if (K > M)
  error('More centroids than data vectors.')
end

errlog = zeros(maxiter,1);    % Log of error value after each iteration.

% Initialize centroids Yc by random selection of K vectors in Y.
perm = randperm(M);
Yc   = Y(perm(1:K),:);

% Constant term in squared Euclidean distance between rows in Y and Yc.
d2y = (ones(K,1)*sum((Y.^2)'))';

for (i = 1:maxiter)
  % Save old centroids to check for termination.
  Yc_old = Yc;

  % Squared Euclidean distance (M-by-K matrix) between rows in Y and Yc.
  d2 = d2y + ones(M,1)*sum((Yc.^2)') - 2*Y*Yc';

  % Assign each vector in Y to nearest centroid.
  [errvals,c] = min(d2');

  % Adjust the centroids based on the new assignments.
  for (k = 1:K)
    if (sum(c==k)>0)
      Yc(k,:) = sum(Y(c==k,:))/sum(c==k);
    end
  end

  % Error value is the total squared distance from cluster centroids.
  errlog(i) = sum(errvals);
  fprintf(1,'... Iteration %4d --- Error %11.6f\n',i,errlog(i));

  % Test for termination.
  if (max(max(abs(Yc - Yc_old))) < 10*eps)
    errlog = errlog(1:i);
    return
  end
end

%-----------------------------------------------------------------------
% End of function kmeans
%-----------------------------------------------------------------------
