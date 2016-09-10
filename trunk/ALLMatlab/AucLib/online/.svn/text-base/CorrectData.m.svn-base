% % 
 %clc
  %close all
  %clear all 
% load  './Data/Writer_118' 
 

 % load -mat 'D:\AUC\Programs\OnlineRuns\TrainTest\FinalAllpages\final\New Folder (2)\Writer_282.dhw.mat'
  %load -mat 'D:\AUC\Programs\OnlineRuns\TrainTest\FinalAllpages\final\Writer_284.dhw.mat'
% %missing=[  2  ];
% %[Data, indeces]=addMissingClasses(Data, indeces, missing ,10 );
% %%Data =removeStrokeFromData( Data , [2  1; 2  2 ; 2  3; 2  4; 2  8; 2  9; 2  10; ] ,1,1);
 %Data =removeStrokeFromData( Data , [  ],1,1);
 numberOfMerges=2;
 m=cell(numberOfMerges,1);
% 
%   m{1}=[5  9; 5  10;];
%   m{2}=[9  4; 9   5;];
%   m
  
 Data=ProcessData(Data,m,[ ],1,0);
%  
%  save  './Data/Writer_261_new' 
%  
%   clc
%   close all
%  clear all 
% clc ;
% clear all ;
% 
%   [data,label]=readStrokesDataSet(1,1,1)
%   save './Data/DataTrain' 
% % % 
% % % 
%   [data,label]=readStrokesDataSet(0,1,1)
%   save './Data/DataTest' 

  
  
   [data,label]=readStrokesDataSet(0,1,0)


% % 

% % 
% % load PAGE_028
% % % j=2:13; 
% % % i=ones(1,length(j))*9;
% % % 
% % % ind=[i ;j]'
% % %  ind=[ 8  7; 
% % %      8  9; 
% % % ]
% % %        8  13;
% % %        8   14;
% % %         8   18 ;
% % %         4  8;
% % %         4  9;
% % %         4   10;
% % %         4   11;
% % %         4    12 ;
% % %         4     13 ;
% % %         4    14;
% % %         4    15;
% % %         4     16;
% % %         4     17;
% % %         4    18;
% % %          4   19 ;
% % %          4   20;
% % %  
% % %        ];
% % %     7  20; 
% % %     7  22; 
% % %     7  26;
% % %     6  19;
% % %     4  16;
% % %     4   19 ;];
% % 
% % % ind=[ 8 6; 8 7 ;
% % %    4 13; 4 2; 3 7; 4 3 ];
% % 
% clear all 
% 
% load './Data/PAGE_174'
% 
% 
%    missing=[0  4  9  ];
%  [DataNew, indecesNew]=addMissingClasses(Data, indeces, missing ,10 );
%  
%  
%  
%  
%  
%  
%  
%  
%  
%  
% 
%  
%  
% % 
% 
%   c=1:9;
%  r=ones(1,length(c))*8;
%  removeFrom=[r  ;c ];
%   Addto=ones(1,length(r)).*10;
% %  
% c=1:11;
%  r=ones(1,length(c))*7;
%  removeFrom=[removeFrom [r  ;c ]];
%   Addto=[  Addto  ones(1,length(r)).*9];
% %  
%   c=1:10;
% r=ones(1,length(c))*5;
%  removeFrom=[removeFrom [r  ;c ]];
%   Addto=[  Addto  ones(1,length(r)).*6];
% %  
% %  
%    c=1:8;
%    r=ones(1,length(c))*4;
%  removeFrom=[removeFrom [r  ;c ]];
%   Addto=[  Addto  ones(1,length(r)).*5];
%   
%   
%      c=1:8;
%    r=ones(1,length(c))*3;
%  removeFrom=[removeFrom [r  ;c ]];
%   Addto=[  Addto  ones(1,length(r)).*4];
% %  
% %    c=1:13;
% % r=ones(1,length(c))*4;
% % removeFrom=[removeFrom [r  ;c ]];
% %  Addto=[  Addto  ones(1,length(r)).*5];
% %  
% %    c=1:13;
% % r=ones(1,length(c))*3;
% % removeFrom=[removeFrom [r  ;c ]];
% %  Addto=[  Addto  ones(1,length(r)).*4];
% %  
% %  
% %  removeFrom
% %  Addto
%  
%  
% %   %removeFrom9=[6 1;9    6;9    3;9   4;9  5; 9   6;  9   7;  9   8;   9  9;  9  10;  9  11; 9  12; ];
% 
% %  
% %   removeFrom=[  8  1;8    2 ;8   3;8  4;8   5; 8   6;  8   7;  8   8;  8 9;  8  10;  8  11; 8  12; ];
% %  Addto=ones(1,length(removeFrom)).*10;
%  
%  
% %   removeFrom=[  9  1;9     2 ;9    3;9   4;9  5; 9   6;  9   7;  9   8;   9  9;  9  10;  9  11; 9  12; ];
% %  Addto=ones(1,length(removeFrom)).*0;
% % 
% 
% [DataFinal] =replace(DataNew,removeFrom',Addto)
% 
% 
%  
%  Data =removeStrokeFromData( DataNew , [ ] ,1,0);
% 
% 
% save './Data/PAGE_173_new'
% 
% %
% 
% 
% %path=[ './Data/' ];
% 
% % names=[ 'PAGE_163_new'; 'PAGE_164_new';  'PAGE_165_new';  
% %     'PAGE_166_new';  'PAGE_167_new';  'PAGE_170_new';  'PAGE_171_new';  
% %     'PAGE_167_new';  'PAGE_168_new';  'PAGE_169_new';    ]
% 
% files=[path names ];
% load (files)
% 
%   missing=[];
% [DataFinal,NewLabel]=addMissingClasses(Data, indeces, missing  )
% 
% 
% removeFrom=[ 2 3; 3 1; ];
% Addto=[3  2 ];
%  [DataFinal] =replace(Data,removeFrom,Addto);
%  
%  
%  
% %clear all 
% %   [Data,label]=readStrokesDataSet(1,0,1);
% %   
% %  
% % DataTest=removeStrokeFromData( Data , [2   46; 2  43;  2  40; 3  34 ; 3  35;  10  12 ; 10  2;  3 39;   2  45;  2 38; ] ,1);
% % 
% % DataTest=removeStrokeFromData( Data , [ 2   177; 2  121;  2   126; 2  141;  2  140;   3    136; 4  112;   5  74; 5   75; 7   111;    ] ,0);
% 
% 
% 
% 
% %   Data{1}=DataFinal{1};
% %    Data{2}=DataFinal{2};
% %   Data{3}=[];
% %     Data{4}=[DataFinal{3} DataFinal{4}];
% %     for n=5:10;
% %       Data{n}=DataFinal{n};
% %     end 
%  
% %  Data=DataFinal;
% %  
% %  save PAGE_028_new  Data Labels indeces Sizes
% 
%  
%  
%  
%  
%  
% 
% 
% 
% %close all 
% 
% % for n=1:length(Data)
% %  % n=1;
% %   figure
% %   i=1;
% %   for s=1:length(Data{n})
% %     
% %    Stroke=Data{n}{s}';
% %    
% %    x=Stroke(:,1);
% %    y=Stroke(:,2);
% %    
% %    
% %    if (n==1 | length(x)>3 )
% %    subplot(6,6,i);
% %    plot(x,y,'*');
% %    title([' Stroke ' int2str(n) '_' int2str(s)]);  
% %    i=i+1;
% %    end
% %    
% %    
% %   end 
% %   end 
% 
% 
% 
% 
% 
% 
% 
% 
% 
% 
% 
% 
% 
%     
% clear all ;
% 
%     for i=1:10 
%          data{i}={};
%          SizesF(i)=0;
%     end
%      load './Data/DataTrain'
%     indeces=label;
%     
%     
%     
%     for i=1:10
%      data2{i}={};
%      label2(i)=0;
%  end
% 
%     
% for i=1:length(Data)
% data2{indeces(i)+1}=Data{i};
% label2(indeces(i)+1)=indeces(i);
% %SizesF(indeces(i)+1)=    SizesF(indeces(i)+1)+ Sizes(i);
% end 
% % Sizes
% for i=1:length(data2)
%     
%     data{i}=[ data{i}  data2{i} ];
%     label(i)= label2(i);
%     
% end 
% 
% 
% 
% 
% 
% load './Data/DataTest'
%     indeces=label;
%     
%     
%     
%     for i=1:10
%      data2{i}={};
%      label2(i)=0;
%  end
% 
%     
% for i=1:length(Data)
% data2{indeces(i)+1}=Data{i};
% label2(indeces(i)+1)=indeces(i);
% %SizesF(indeces(i)+1)=    SizesF(indeces(i)+1)+ Sizes(i);
% end 
% % Sizes
% for i=1:length(data2)
%     
%     data{i}=[ data{i}  data2{i} ];
%     label(i)= label2(i);
%     
% end 
%     save './Data/DataTrainNew'