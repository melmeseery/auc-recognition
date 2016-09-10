function kappa = lpc2rf(a)
%  lpc2rf --> Convert prediction polynomial to reflection coefficients.
%
%  <Synopsis>
%    kappa = lpc2rf(a)
%
%  <Description>
%    The function computes the reflection coefficients, kappa, based
%    on the prediction polynomial, a.
%
%  <See Also>
%    durbin --> Levinson-Durbin Recursion.
%    rf2lpc --> Convert reflection coefficients to prediction polynomial.

%  <References>
%  [1] J.R Deller, J.G. Proakis and F.H.L. Hansen, "Discrete-Time
%      Processing of Speech Signals", IEEE Press, p. 345, (2000).
%
%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------

M     = length(a);
kappa = zeros(M,1);

for (j=M:-1:1)
  kappa(j) = a(j);
  a(1:j-1) = (a(1:j-1) + a(j)*a(j-1:-1:1))/(1 - kappa(j)^2);
end

%-----------------------------------------------------------------------
% End of function lpc2rf
%-----------------------------------------------------------------------
