function chain_code=ChainCode(I)

% I=imdilate(I,ones(2,2));
for n=1:6
    num_of_objects=max(max(bwlabel(I)));
    if(num_of_objects>1)
        %         'zeo'
        I=imdilate(I,ones(n,n));
    else
        break;
    end
end
% imshow(I)
% figure

[r c]=size(I);
initial_pos=[find(I(:,floor(c/2)),1) floor(c/2)];
B=bwtraceboundary(I,initial_pos,'N');

diff_B=diff(B);

chain_code=[];
[r c]=size(diff_B);
for n=1:r
    loc=[diff_B(n,1) diff_B(n,2)];
        
    if(loc==[0 1])
        code=0;
    elseif(loc==[-1 1])
        code=1;
    elseif(loc==[-1 0])
        code=2;
    elseif(loc==[-1 -1])
        code=3;
    elseif(loc==[0 -1])
        code=4;
    elseif(loc==[1 -1])
        code=5;
    elseif(loc==[1 0])
        code=6;
    elseif(loc==[1 1])
        code=7;
    else
        error('error in chain code');
    end
    
    chain_code=[chain_code; code];
    
end
    
    
    
    
    
    
    
    
    
    
    
    
    


