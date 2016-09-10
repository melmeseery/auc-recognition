function Feat=splitFeatures( L, SizeORSplit, sConstant )
[h  w] =size(L);

 Feat=[];
 splits=[];

if ( SizeORSplit ==1) %% use same sConstant to be the number of splits..
    
    NumberOfSplits=sConstant;
     SplitSize=floor(w/NumberOfSplits);
	
	if (SplitSize<1) 
	%%% this condition is used when the image width is soooo smaall that
	%%% it will not be divide by this number of splits (ex w=3 number split =9 )
	%%% use split size > w try first 4 then 2 then 1 
    
    SplitSize=1;
   splits={};
       index=1;
    
 %while (index<w)
    for i=1:NumberOfSplits
     % index
     if (index<w)
        part=L(:,index);
        
     else 
         part=zeros(h,1);
     end 
       % splits{i+1}=part;
       splits{i}=part;
     
         index=index+1;
    end
 
 
    
    else 
    	 
    cols=ones(1,NumberOfSplits);
    cols=cols.*SplitSize;
    ss=sum(cols);
    if (ss<w)
        cend=length(cols);
        %%%% i need to add one from back col to start 
        for jj=cend:-1:1
            if (sum(cols)<w)
                cols(jj)=cols(jj)+1;
            end
            
        end
       
    end 
    cols;
    rows=[h];
    % this will convert the matrix into cells of matriceess... each with 
    splits=mat2cell(L,rows,cols);
    end  %% if splitsize <1 

end

    if ( SizeORSplit ==2)
        
        SplitSize=sConstant;
        
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
        part=L(:,index:index+SplitSize-1);
       % splits{i+1}=part;
       splits{i}=part;
        index=index+SplitSize;
        i=i+1;
    end
    
if (index<w)
    indexOut=index;
    part=L(:,index:end);
    % splits={splits part};
        splits{i}=part;
end 
    end 
    
    
    
    for j=1:length(splits)
        sp=splits{j};
      [r c]=size(sp);
     %Feat(j)=  sum(sum(sp));
          Feat(j)=  sum(sum(sp))/(r*c);
    end 
    
