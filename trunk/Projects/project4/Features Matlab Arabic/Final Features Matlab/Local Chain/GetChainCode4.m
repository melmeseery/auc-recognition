function f= GetChainCode4(I,h)

I=I(5:24,5:24);
B=GetMatlabProfile(I);
d_B=diff(B,1);
sublayer=cell(8,1);
for i=1:8
    sublayer{i}=zeros(size(I));
end

for n=1:size(d_B,1)
    d_r=d_B(n,1);
    d_c=d_B(n,2);
    r=B(n,1);
    c=B(n,2);
    if(d_r==0 && d_c==1)
        sublayer{1}(r,c)=1;
    elseif(d_r==-1 && d_c==1)
        sublayer{2}(r,c)=1;
    elseif(d_r==-1 && d_c==0)
        sublayer{3}(r,c)=1;
    elseif(d_r==-1 && d_c==-1)
        sublayer{4}(r,c)=1;
    elseif(d_r==0 && d_c==-1)
        sublayer{5}(r,c)=1;
    elseif(d_r==1 && d_c==-1)
        sublayer{6}(r,c)=1;
    elseif(d_r==1 && d_c==0)
        sublayer{7}(r,c)=1;
    elseif(d_r==1 && d_c==1)
        sublayer{8}(r,c)=1;
    end
end
% close all
% imshow(I)
% for i=1:8
%     figure
%     imshow(sublayer{i})
% end
% input('zeo:')
    
%     
% f=zeros(1,200);
% counter=1;
% for i=1:8
%     sublayer{i}=conv2(sublayer{i},h,'same');
%     for x=3:4:20
%         for y=3:4:20
%             f(counter)= sublayer{i}(x,y);
%             counter=counter+1;
%         end
%     end
% end


    
f=zeros(1,200);
counter=1;
for i=1:8
    for x=1:4:17
        for y=1:4:17
            F=mean(mean(sublayer{i}(x:x+3,y:y+3)));
            f(counter)= F;
            counter=counter+1;
        end
    end
end


