function Fp = stpitch(x,N,Pth,Zth,NFFT,R,Fs)
%  stpitch --> Short-time pitch estimation.
%
%  <Synopsis>
%    Fp = stpitch(x,N,Pth,Zth,NFFT,R,Fs)
%
%  <Description>
%    An initial voiced/unvoiced segmentation based on short-time
%    power Px and zero crossing Zx measures obtained in sliding
%    windows of length N. The middle sample in the window is labeled
%    voiced if Px/max(Px) > Pth and Zx < Zth.
%
%    Then the pitch is estimated in half overlapping segments based
%    on the harmonic product spectrum, where the frequency axis is
%    compressed by integer factors (1 to R), and NFFT is the
%    number of points in the FFT. Fs is the sampling frequency.
%    The pitch in overlapping segments is combined based on the Hanning
%    window also used in the HPS function. Finally, only segments
%    covering voiced regions returns a pitch frequency.
%
%  <See Also>
%    voiunvoi   --> Voiced/unvoiced segmentation using a sliding window.
%    hpspectrum --> Harmonic product spectrum.

%  <Revision>
%    Peter S.K. Hansen, IMM, Technical University of Denmark
%
%    Last revised: September 30, 2000
%-----------------------------------------------------------------------

% Check the required input arguments.
if (nargin < 7)
  error('Not enough input arguments.')
end

% Number of data points.
M = length(x);

% Make sure the window length is even.
N = 2*fix(N/2);

% Hanning window.
win = hann(N);

% Initialize output vector (pitch frequencies).
Fp = zeros(M,1);

% Voiced/unvoiced segmentation.
voi = voiunvoi(x,N,Pth,Zth);
invoi = 0;                % Flag is 1 if current segment is voiced.

% Short-time pitch estimation in half overlapping segments.
for (m=N:N/2:M)
  % Time index vector of current speech segment.
  n1 = m-N+1:m-N/2;       % First half.
  n2 = m-N/2+1:m;         % Second half.
  n  = [n1 n2];           % Full segment.

  if (any(~voi(n)))       % We do not have full voiced frame.
    if (invoi)            % Move out of voiced region.
      Fp(n1) = Fmax;      % Use rect window on last part of previous frame.
      invoi  = 0;         % Current segment is not voiced.
    end
  else                    % We have full voiced frame.
    % Peak value of harmonic product spectrum.
    [HPSmax,Fmax] = max(abs(hpspectrum(x(n),NFFT,R)));

    if (invoi)            % Previous frame was also voiced.
      % Overlap-add pitch estimates based on the window used to find HPS.
      Fp(n) = Fp(n) + win*Fmax;
    else                  % Move into voiced region.
      Fp(n) = Fmax*[ones(N/2,1) win(N/2+1:N)]; % Rect. win. on half frame.
      invoi = 1;          % Current segment is voiced.
    end
  end
end

% Convert pitch to absolute frequency, and set non-voiced parts to NaN.
wsave = warning; warning('off');
Fp = (Fp*Fs/NFFT).*(Fp./Fp);
warning(wsave);

%-----------------------------------------------------------------------
% End of function stpitch
%-----------------------------------------------------------------------
