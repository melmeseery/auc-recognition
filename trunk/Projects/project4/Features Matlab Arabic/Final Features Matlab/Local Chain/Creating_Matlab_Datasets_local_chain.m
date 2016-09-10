clear
clc
warning off

load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');

variance = (sqrt(2)*4/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);

training_set=zeros(60e3,200);
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;


    P=regionprops(double(dither(I)),'BoundingBox');
    x= P.BoundingBox(1); y=P.BoundingBox(2);
    width_x= P.BoundingBox(3); width_y= P.BoundingBox(4);
    I=I(floor(y)+1:floor(y+width_y),floor(x)+1:floor(x+width_x));
    [r c]=size(I);
    if(r<20)
        residue= 20-r;
        if(mod(residue,2)==0);
            I=[zeros(residue/2,c); I; zeros(residue/2,c)];
        else
            I=[zeros(floor(residue/2),c); I; zeros(floor(residue/2)+1,c)];
        end
    end
    [r c]=size(I);
    if(c<20)
        residue= 20-c;
        if(mod(residue,2)==0);
            I=[zeros(r,residue/2) I zeros(r,residue/2)];
        else
            I=[zeros(r,floor(residue/2)) I zeros(r,floor(residue/2)+1)];
        end
    end

    I=[zeros(20,4) I zeros(20,4)];
    I=[zeros(4,28); I; zeros(4,28)];
    [r c]= size(I);
    if(~(and(r==28,c==28)))
        error('sizing error')
    end

%     imshow(I)
%     input('zeo:')
    training_set(n,:)=GetChainCode4(I,h);



end

save arabic_local_chain_training_set training_set





clear
clc

load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TestingSet_pathes');

variance = (sqrt(2)*4/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);

testing_set=zeros(10e3,200);
for n=1:10e3
    if(mod(n,100)==0)
        n
    end
    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TestingSet_pathes{n}];
    I=double(255-imread(path))/255;


    P=regionprops(double(dither(I)),'BoundingBox');
    x= P.BoundingBox(1); y=P.BoundingBox(2);
    width_x= P.BoundingBox(3); width_y= P.BoundingBox(4);
    I=I(floor(y)+1:floor(y+width_y),floor(x)+1:floor(x+width_x));
    [r c]=size(I);
    if(r<20)
        residue= 20-r;
        if(mod(residue,2)==0);
            I=[zeros(residue/2,c); I; zeros(residue/2,c)];
        else
            I=[zeros(floor(residue/2),c); I; zeros(floor(residue/2)+1,c)];
        end
    end
    [r c]=size(I);
    if(c<20)
        residue= 20-c;
        if(mod(residue,2)==0);
            I=[zeros(r,residue/2) I zeros(r,residue/2)];
        else
            I=[zeros(r,floor(residue/2)) I zeros(r,floor(residue/2)+1)];
        end
    end

    I=[zeros(20,4) I zeros(20,4)];
    I=[zeros(4,28); I; zeros(4,28)];
    [r c]= size(I);
    if(~(and(r==28,c==28)))
        error('sizing error')
    end

    testing_set(n,:)= GetChainCode4(I,h);



end

save arabic_local_chain_testing_set testing_set

warning on



% clear
% clc
% warning off
% 
% fid= fopen('C:\MNIST\train-images.idx3-ubyte','r');
% A=fread(fid);
% 
% variance = (sqrt(2)*4/pi)^2;
% h= Generate_Gaussian_Mask([5 5],variance);
% 
% training_set=zeros(10e3,200);
% start_of_row=17;
% for k=1:6
%     for i=1:10e3
%         m=(k-1)*10e3+i;
%         if(mod(m,100)==0)
%             m
%         end
%         I=zeros(28,28);
% 
%         for n=1:28
%             I(n,1:end)=A(start_of_row:start_of_row+27);
%             start_of_row=start_of_row+28;
%         end
% 
%         I=(double(I))/255;
%         P=regionprops(double(dither(I)),'BoundingBox');
%         x= P.BoundingBox(1); y=P.BoundingBox(2);
%         width_x= P.BoundingBox(3); width_y= P.BoundingBox(4);
%         I=I(floor(y)+1:floor(y+width_y),floor(x)+1:floor(x+width_x));
%         [r c]=size(I);
%         if(r<20)
%             residue= 20-r;
%             if(mod(residue,2)==0);
%                 I=[zeros(residue/2,c); I; zeros(residue/2,c)];
%             else
%                 I=[zeros(floor(residue/2),c); I; zeros(floor(residue/2)+1,c)];
%             end
%         end
%         [r c]=size(I);
%         if(c<20)
%             residue= 20-c;
%             if(mod(residue,2)==0);
%                 I=[zeros(r,residue/2) I zeros(r,residue/2)];
%             else
%                 I=[zeros(r,floor(residue/2)) I zeros(r,floor(residue/2)+1)];
%             end
%         end
% 
%         I=[zeros(20,4) I zeros(20,4)];
%         I=[zeros(4,28); I; zeros(4,28)];
%         [r c]= size(I);
%         if(~(and(r==28,c==28)))
%             error('sizing error')
%         end
% 
%         training_set(i,:)= GetChainCode4(I,h);
% 
%     end
%     save(['local_chain_training_set_part_' int2str(k)],'training_set')
%     training_set=zeros(10e3,200);
% end
% 
% fclose(fid);
%  
% 
% clear
% clc
% 
% warning off
% 
% fid= fopen('C:\MNIST\t10k-images.idx3-ubyte','r');
% A=fread(fid);
% 
% variance = (sqrt(2)*4/pi)^2;
% h= Generate_Gaussian_Mask([5 5],variance);
% 
% 
% testing_set=zeros(10e3,200);
% start_of_row=17;
% label_index=9;
% for m=1:10e3
%     if(mod(m,100)==0)
%         m
%     end
%     I=zeros(28,28);
%     
%     for n=1:28
%         I(n,1:end)=A(start_of_row:start_of_row+27);
%         start_of_row=start_of_row+28;
%     end
% 
%     I=(double(I))/255;
%     P=regionprops(double(dither(I)),'BoundingBox');
%     x= P.BoundingBox(1); y=P.BoundingBox(2);
%     width_x= P.BoundingBox(3); width_y= P.BoundingBox(4);
%     I=I(floor(y)+1:floor(y+width_y),floor(x)+1:floor(x+width_x));  
%     [r c]=size(I);
%     if(r<20)
%         residue= 20-r;
%         if(mod(residue,2)==0);
%             I=[zeros(residue/2,c); I; zeros(residue/2,c)];
%         else
%             I=[zeros(floor(residue/2),c); I; zeros(floor(residue/2)+1,c)];
%         end
%     end
%     [r c]=size(I);
%     if(c<20)
%         residue= 20-c;
%         if(mod(residue,2)==0);
%             I=[zeros(r,residue/2) I zeros(r,residue/2)];
%         else
%             I=[zeros(r,floor(residue/2)) I zeros(r,floor(residue/2)+1)];
%         end
%     end  
% 
% 
%     I=[zeros(20,4) I zeros(20,4)];
%     I=[zeros(4,28); I; zeros(4,28)];
%     [r c]= size(I);
%     if(~(and(r==28,c==28)))
%         error('sizing error')
%     end
%       
%     
%     testing_set(m,:)=GetChainCode4(I,h);
%     %input('zeo:')
% end
% 
% fclose(fid);
% 
% save local_chain_testing_set testing_set
% 
% warning on
% % 
% 
