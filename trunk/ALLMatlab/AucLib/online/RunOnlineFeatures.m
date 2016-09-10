
function y=RunOnlineFeatures()

Train=0;
Test=1;

clear all 



for  n = 1:23
  close all   
clc
Feature=n;
OverlapSize=0;
Order=10;
NormalizedSize=50;
isOnline=true;
ReSample=1;

disp([' feature ' int2str(n) '............. ']);  
filename=['TrainStrokeTrainF' int2str(n)  '.txt' ];

y=writeFeatures(filename,1,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
 
disp([' feature ' int2str(n) '............. ']);  
filename=['TrainStrokeTestF' int2str(n)  '.txt' ];

y=writeFeatures(filename,0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
 

end 
% clear all 
% close all 
% clc
% Feature=2;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 2............. ');  
% y=writeFeatures('TrainStrokeF2.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=3;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 3............. ');  
% y=writeFeatures('TrainStrokeF3.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=4;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 4............. ');  
% y=writeFeatures('TrainStrokeF4.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=5;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 5............. ');  
% y=writeFeatures('TrainStrokeF5.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=6;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 6............. ');  
% y=writeFeatures('TrainStrokeF6.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=7;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 7............. ');  
% y=writeFeatures('TrainStrokeF7.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=8;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 8............. ');  
% y=writeFeatures('TrainStrokeF8.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%   
% clear all 
% close all 
% clc
% Feature=9;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 9............. ');  
% y=writeFeatures('TrainStrokeF9.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% 
% 
% clear all 
% close all 
% clc
% Feature=10;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 10............. ');  
% y=writeFeatures('TrainStrokeF10.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% 
% clear all 
% close all 
% clc
% Feature=11;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% 
% disp(' feature 11............. ');  
% y=writeFeatures('TrainStrokeF11.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% 
% clear all 
% close all 
% clc
% Feature=12;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 12............. ');  
% y=writeFeatures('TrainStrokeF12.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=13;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 13............. ');  
% y=writeFeatures('TrainStrokeF13.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=14;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 14............. ');  
% y=writeFeatures('TrainStrokeF14.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=15;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 15............. ');  
% y=writeFeatures('TrainStrokeF15.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=16;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 16............. ');  
% y=writeFeatures('TrainStrokeF16.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
% %y=writeFeatures('TrainStrokeF16.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=17;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 17............. ');  
% y=writeFeatures('TrainStrokeF17.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
% %y=writeFeatures('TrainStrokeF17.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
%  
% clear all 
% close all 
% clc
% Feature=18;
% OverlapSize=0;
% Order=10;
% NormalizedSize=50;
% isOnline=true;
% ReSample=1;
% disp(' feature 18............. ');  
% y=writeFeatures('TrainStrokeF18.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
% %y=writeFeatures('TrainStrokeF18ChainCode64.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
% 
% %   clear all 
% % close all 
% % clc
% % Feature=19;
% % OverlapSize=0;
% % Order=10;
% % NormalizedSize=50;
% % isOnline=true;
% % ReSample=1;
% % disp(' feature 19............. ');  
% % y=writeFeatures('TrainStrokeF19.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
% %   