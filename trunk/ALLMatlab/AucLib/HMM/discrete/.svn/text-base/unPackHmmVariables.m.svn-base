function [A,B,pi1]=unPackHmmVariables(States,K,varargin)
if(nargin==3)
    
A=varargin{1};
B=varargin{2};
pi1=varargin{3};
 
else
    %%%default states...........
    A = rand(States,States);
%A(2,1)=0;;A(3,1)=0;A(3,2)=0;   to change topology . 
 A = A./repmat(sum(A),States,1); % Initial random model
B = rand(K,States);  % the 
B = B./repmat(sum(B),K,1); % with probability sum
%B=A;
pi1 = rand(States,1);
pi1 = pi1 / sum(pi1);      % normalized to one.
end     