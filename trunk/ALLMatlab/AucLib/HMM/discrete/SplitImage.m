function splits=SplitImage(I,SplitSize,NumberOfSplits,OverlapSize)
%Split the image into vertical splits....
%I=I'
%[Width, height] = size (I)
[h,w]=size(I);  % number rows / colums




if(OverlapSize==0)
    SplitSize=floor(w/NumberOfSplits);
	
	if (SplitSize<1) 
	%%% this condition is used when the image width is soooo smaall that
	%%% it will not be divide by this number of splits (ex w=3 number split =9 )
	%%% use split size > w try first 4 then 2 then 1 

	if (w>4)
	SplitSize=3;
	NumberOfSplits=floor(w/SplitSize);
	end
	if (w<4)
	SplitSize=w;
	NumberOfSplits=0;
	end 
	end 
	
	
	cols=ones(1,NumberOfSplits+1);
    cols=ones(1,NumberOfSplits+1);
    cols=cols.*SplitSize;
    ss=sum(cols);
    if (ss~=w)
        cols(end)=SplitSize+w-ss;
    end 
    cols;
    rows=[h];
    % this will convert the matrix into cells of matriceess... each with 
    splits=mat2cell(I,rows,cols);
   
    
    %%%%%%%%%%%%%%If the pervious did not work then use this code 
%      splits=cell(NumberOfSplits,h);
% 
%     index=1;
%     for i=0:NumberOfSplits
%         part=I(index:index+SplitSize,:);
%         splits{i+1}=part;
%         index=SplitSize;
%     end
    
    
else
    NumberOfSplits=ceil(w/SplitSize);

	
	if (NumberOfSplits<=1)
	%% this is used when the w is smaller than the split size... 
	%% in this case we make the split size half the width ..
	SplitSize=ceil(w/2.0);
	%NumberOfSplits=ceil(w/SplitSize);
	
    end 
    
  
    %% different overlap then i will need to make create cell of matricess..
%    splits=cell(1, count);
    splits={};

    index=1;
    
    i=1;
    while ((index+SplitSize-1)<w)
%    for i=0:NumberOfSplits
     % index
        part=I(:,index:index+SplitSize-1);
       % splits{i+1}=part;
       splits{i}=part;
        index=index+SplitSize-OverlapSize;
        i=i+1;
    end
    
if (index<w)
    indexOut=index
    part=I(:,index:end);
    % splits={splits part};
        splits{i}=part;
end 
    
end 