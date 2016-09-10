function  [data,label]=readStrokesDataSet(isTrain,Database,ReReadData)


if (ReReadData==0)
    if (isTrain==1)
       % load './Data/DataTrain'
       load 'DataTrain'
    else
      %  load './Data/DataTest'
         load 'DataTest'
    end
   % data=Data;
    return 
end 


%load StrokesData;
    disp(' using online data......   ');
    for i=1:10 
         data{i}={};
        % SizesF(i)=0;
    end

       %path=[ './Data/' ];
       for i = 0: 261
                i
                FilesAll{i+1}=[ './Data/' 'Writer_' num2str(i,'%03.0f') '_new']
       end 
    
    [r c] = size(FilesAll);
    
    TrainSize=floor(0.80*c);
    TrainSize
    
if (isTrain==1)
 %   k=1;
%    % Files=[];
%     for i=1:TrainSize
%            Files(k)=[];
%            k=k+1;
%     end 
    k=1;
    for i=1:TrainSize
        Files{k}= FilesAll{i};
        k=k+1;
    end 
   
else
        k=1;
    for i=TrainSize+1:c
        Files{k}=FilesAll{i};
        k=k+1;
        
    end
%        Files=['PAGE_062_new' ; 'PAGE_063_new' ;'PAGE_068_new' ; 'PAGE_070_new';
%                 'PAGE_073_new';  'PAGE_077_new'];%  'PAGE_071_new';
  
end 

[c rr] = size(Files);

for f=1:rr
    f
    Files{f}
load(Files{f});

 for i=1:10
     data2{i}={};
     label2(i)=0;
 end

for i=1:length(Data)
data2{indeces(i)+1}=Data{i};
label2(indeces(i)+1)=indeces(i);
%SizesF(indeces(i)+1)=    SizesF(indeces(i)+1)+ Sizes(i);
end 
% Sizes
for i=1:length(data2)
    
    data{i}=[ data{i}  data2{i} ];
    label(i)= label2(i);
    
end 

     
end 