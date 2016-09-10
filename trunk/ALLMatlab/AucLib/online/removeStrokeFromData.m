function DataFinal=removeStrokeFromData(Data,indeces , Display, Transpose)

% i=indeces(:,1);
% j=indeces(:,2);
   DataFinal=[];
[r c]=size(indeces);
if (r==1)
     Data{indeces(1)}{indeces(2)}=[];
else 
for i=1:length(indeces)
  Data{indeces(i,1)}{indeces(i,2)}=[];
end 
end

%% check column wise or row wise ..

% cw=0; rw=0;
%  column=1;
% for n=2:length(Data)
%      len=ceil(length(Data{n})/2);
%   for s=1:len
%         
%     
%        [r c]=size(  Data{n}{s});
%        if (r>c)
%            rw=rw+1;
%        else if (c>r)
%                cw=cw+1;
%            end 
%        end 
%            
%        
%  
%   end 
% end
% if (cw>rw)
%      column=1;
%           disp('     Column wise.....(horizontal..)');
% else 
%      column=0;
%           disp('      Row wise.....  (vertical) ');
% end 
 column=DetectStrokeStoreDirection(Data);
 %column
 ff=1;
figure (ff) ;
ff=ff+1;
set(gcf,'Position',[10 ,10,1200,800]);
i=1;
for n=1:length(Data)
    if (Display==1)

    end 
%n;
%i=1;
  ss=1;
  len=length(Data{n});
  for s=1:len
%          if (Display==1)
%           if(i>=30) 
%            %   set(800,800,800,800,h)
%             figure 
%             
%             i=1
% 
%           end
%          end
 %   for s=1:len
      
   Stroke=Data{n}{s};
   if (~isempty(Stroke))
       
       if ( column==1) 
          %%Stroke=Stroke
             x=Stroke(1,:);  %% x =  ------  row 1 all columns (column wise ) 
             y=Stroke(2,:); %%%  y=-----
       else  
             
            x=Stroke(:,1);  %% row wise   x   y
            y=Stroke(:,2);  %%%           |   |
    
       end 
      
    %x=Stroke(1,:);
    %y=Stroke(2,:);
%     x=Stroke(:,1);
%     y=Stroke(:,2);
   
   
   if (n==1 || length(x)>=3 )
        if (Display==1)
            
            if (i>36)
                figure(ff);
                ff=ff+1;
                i=1;
               set(gcf,'Position',[10 ,10,1200,800]);
            end 
            
                subplot(6,6,i);
            plot(x,y,'*');
         title([' Stroke ' int2str(n-1) ' _ ' int2str(s)]);  
         i=i+1;  
        end
  if (Transpose)
   DataFinal{n}{ss}=addPinUpColumn(Stroke' , 0);
  else
   DataFinal{n}{ss}=addPinUpColumn(Stroke , 0);
  end 
   
   ss=ss+1;
   end
   end
  end 
  end 
