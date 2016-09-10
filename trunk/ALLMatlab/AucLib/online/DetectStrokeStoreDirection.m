function Transpose=DetectStrokeStoreDirection(Data)
 cw=0; rw=0;
  column=1;

for n=2:length(Data)
     len=ceil(length(Data{n})/2);
  for s=1:len
        
    
       [r c]=size(  Data{n}{s});
       if (r>c)
           rw=rw+1;
       else if (c>r)
               cw=cw+1;
           end 
       end 
           
       
 
  end 
end
if (cw>rw)
     column=1;
          disp('     Column wise.....(horizontal..)');
else 
     column=0;
          disp('      Row wise.....  (vertical) ');
end 
Transpose=column;
