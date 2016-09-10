function  transition=RowTransitions(I)
[h,w]=size(I);

for r=1:h
     transition(r)=0;
    for c=1:w
     if (c == 1) % first pixlel in row...
        prev = I(r,c);
		startB = c;
        count=1;
     end
     		if (prev ~= I(r,c)) 
					% there is a chage in blocksss..
					% change in block...
					% end of block
					endB = c - 1;
					% create a new block from start to end.. only if it is a
					% black..
					if (prev == 1) 
						 %%% add new block to the s
                         transition(r)=   transition(r)+1;
						 %%% check the size of block.. 
                    end
					% this is also a start of a new block
					startB = c;
					count = 1;
				else 
					count=count+1;
            end 
				   prev = I(r,c);
    end 
    
                if (prev == 1) 
						 %%% add new block to the s
                         transition(r)=   transition(r)+1;
						 %%% check the size of block.. 
                    end
    
end 