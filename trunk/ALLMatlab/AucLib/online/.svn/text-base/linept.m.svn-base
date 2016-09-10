function result=linept(matrice, X1, Y1, X2, Y2,StartX,StartY)%, MaxY)
% Connect two pixels in a matrice with 1
%
% Command line
% ------------
% result=linept(matrice, X1, Y1, X2, Y2)
%   matrice : matrice where I'll write
%   (X1, Y1), (X2, Y2) : points to connect
%   result : matrix + the line
%
% Note
% ----
%   matrice can contents anything
%   (X1, Y1), (X2, Y2) can be out of the matrice
%
% Example
% -------
% a = linept(zeros(5, 10), 2, 2, 3, 9)
% a =
% 
%      0     0     0     0     0     0     0     0     0     0
%      0     1     1     1     1     0     0     0     0     0
%      0     0     0     0     0     1     1     1     1     0
%      0     0     0     0     0     0     0     0     0     0
%      0     0     0     0     0     0     0     0     0     0
%
% Georges Cubas 20/11/03
% georges.c@netcourrier.com
% Version 1.0
;
X1 ;
Y1 ;
X2 ;
Y2;
result = matrice;
for x=max(1, X1):sign(X2 - X1):max(1, X2)
    y = round(f(x, X1, Y1, X2, Y2));
    if y > 0
        xin=round(x-StartX);
             yin=round((y-StartY));
      %  yin=round(MaxY-(y-StartY));
        result(xin,yin) = 1;
    end
end
for y=max(1, Y1):sign(Y2 - Y1):max(1, Y2)
    x = round(f2(y, X1, Y1, X2, Y2));
    if x > 0
             xin=round(x-StartX);
          %      yin=round(MaxY-(y-StartY));
       yin=round((y-StartY));
        result(xin,yin) = 1;
    end
end

function y=f(x, X1, Y1, X2, Y2)
a = (Y2 - Y1)/(X2 - X1);
b = Y1 - X1 * a;
y = a * x + b;

function x=f2(y, X1, Y1, X2, Y2)
if X1==X2
    x = X1;
else
	a = (Y2 - Y1)/(X2 - X1);
	b = Y1 - X1 * a;
	x = (y - b)/a;
end