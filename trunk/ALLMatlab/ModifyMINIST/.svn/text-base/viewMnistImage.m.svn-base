function y=viewMnistImage(file,index,digit,trainLabels,max,r,i)
colcount=28;
GrayLevelthreshold=0.5;
    %search for any digit 
    fid= fopen(file,'r');

%disp('Finish reading MNIST Input file ');



if (digit>-1)
    disp('searching for digits')
    fid2=fopen(trainLabels ,'r');
    B=fread(fid2);
     label_index=9;
     count=0;
    for k = 1:60000
    % search for a specific digit 
   
     d=B(label_index+k);
 
     if (d==digit)
         count=count+1;
     end 
    
        if (count+1>=index)
            index2=k;
            break;
        end 
    end 
     else    
        index2=index;
    end 
     
    
    

 S=fread(fid, 4 , 'int32');  %advance the  file pointer
   
skip=colcount*colcount*index2;
 fseek(fid, skip, 'cof');
%image_start=image_start+skip;
A = fread(fid, colcount*colcount , 'uchar');  
%get image from the MNIST matrix.  //Itemp=A(image_start:image_start+colcount*colcount-1);
  Itemp=A;
   % convert the vector into and image matrix 
    I=vec2mat(Itemp,colcount);

     subplot(max,r,i);
     %imshow(I);
        imshow(I);
     
%      
%   %remove object from teh image. 
%   Im=removeObjects(Image,Objectsize); % Im will have background as 0 and objects as 1
%      
%   % Multiply old gray level image with the new Im "all object that are removed will be 0*intensity= 0 "
%   IinMnistFormat=Im.*I;
%   %convert the matrix into a single vector. 
%   Ivec=reshape(IinMnistFormat,colcount*colcount,1);
fclose(fid);
  
    
