function  y=ModifyDatabase(InDataFile,trainCounts,OutDataFile,Objectsize,GrayLevelthreshold)
%%%%%
%Remove all objects smaller then a sepeccific size from the MNIST database.
%
%Details:
%
%This function removes objects smaller than size "Objectsize" from the
%mnist database that is located in "InDataFile". The function saves the
%resulted image in the same format in "OutDataFile". 
%
%Input paramters:
%  InDataFile         | Path and file name of the MNIST image file ( the file can be either test
%                          or trian data). (Input file)
%  trainCounts        | number of images/training samples  to  process. 
%
%  OutDataFile        | The path and file name of the result file. (Output file)
%
%  Objectsize         | The remove size. All objects that has smaller area then
%  `                     this size will be removed from the image.  This
%                         number must be positive and larger then 1.
%                         Note that if this size is large the function may
%                         remove all objects from the image. 
%     
% GrayLevelthreshold  |  The gray level threshold. A decimal number between 0 and 1. 
%                       [Images of MNIST are in gray level and need to convert into binary to process]
%Return:
% this function dose not return any thing but write a new file with the
% final image to file system. 
%%%%%

if (GrayLevelthreshold==0 || GrayLevelthreshold >1)
    GrayLevelthreshold=0.5;
end   
colcount=28;

%

 %Read the input file        
fid= fopen(InDataFile,'r');

%A=fread(fid);
%disp('Finish reading MNIST Input file ');
fidO= fopen(OutDataFile,'wt','ieee-be');


%init the first value of 
%image_start=17;
%start_of_row=17;

%copy and write the header of the file 
%Itemp=A(1:image_start-1);
%fid2=fopen(InDataFile,'r');
tempA=fread(fid, 4 , 'int32=>int32');
fwrite(fidO,tempA,'int32');%,'ieee-be'
% figure ;
%j=1;
ImageSizecols=colcount*colcount ;
%loop on all trianing counts 
for n=1:trainCounts
    if(mod(n,trainCounts/500)==0)  
        n
    end
    
 A=uint8(fread(fid,ImageSizecols, 'uchar')); 
 

%get image from the MNIST matrix.  //image_start:image_start+colcount*colcount-1

   % convert the vector into and image matrix vec2mat
  
    I=vec2mat(A,colcount);
       
       %  convert gray scale image into binary image. 
     Image=im2bw(I,GrayLevelthreshold);
     %remove object from teh image. 
     Im=uint8(removeObjects(Image,Objectsize)); % Im will have background as 0 and objects as 1
     
     % Multiply old gray level image with the new Im "all object that are removed will be 0*intensity= 0 "
     IinMnistFormat=Im.*I;
  %convert the matrix into a single vector.  
  Ivec=reshape(IinMnistFormat',ImageSizecols,1);
     %write the finihsed vector
     fwrite(fidO,Ivec,'uchar');
  
end
fclose(fid);
% colse image. 
fclose(fidO);