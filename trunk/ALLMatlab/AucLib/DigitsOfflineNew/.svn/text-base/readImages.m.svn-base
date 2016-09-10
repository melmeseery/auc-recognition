function [data,labels]=readImages(mode , Database )
%%Read the data from The digit file system and store it in data....
% mode is the mode of the read image Test or Train 
% mode = 1 ==> training set 
% mode = 2 testing 
% ---------------------
% Datebase to choose the database set from arabic to latin
% Database= 0    Madbase
% Database = 2  Adbase
% Database = 1   Latin 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 disp('Reading Images............................................................');
mode 
Database 
MaxCat=10;
labelSizes=ones(MaxCat,1);

   constantsize=28*28;
    colcount=28;    
data=[];
labels=[];
warning off

if (Database==0|Database==2)
% get the relative path of data.... 
databpath=getADDataBasePath(0)
else
   
databpath=getADDataBasePath(Database)
end 
%% get the count of the test and train sample to use
[trainCounts,testCount]=getMaxSampleCount()
%%%%%%%%%%%%%% mode  validation .... 
if (mode==111)
    startn=trainCounts;
    endn=testCount; 
    mode=1; 
else 
    startn=1; 
    endn=trainCounts;

end 


if (mode==1) % trainig.............

%%%%
%%% database choice 1 = minist 0= ad base or mad base. 
%%
    if (Database==0)
        Datapatch=[databpath '\MAHDBase_TrainingSet_pathes' ];
        load(Datapatch);
        trainLabels=[databpath '\training_set_labels'];
        load(trainLabels);
        DataBasePath=[databpath '\MAHDBase\']
        
    elseif (Database==2)
        Datapatch=[databpath '\AHDBase_TrainingSet_pathes' ];
        load(Datapatch);
        trainLabels=[databpath '\training_set_labels'];
        load(trainLabels);
        DataBasePath=[databpath '\AHDBase\']
    elseif (Database==1)
        DataBasePath=[databpath '\train-images.idx3-ubyte' ]
        fid= fopen(DataBasePath,'r')
        A=fread(fid);
        trainLabels=[databpath '\train-labels.idx1-ubyte'];
        fid2=fopen(trainLabels ,'r');
        B=fread(fid2);
        end 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%
    image_start=17;
    
    label_index=9;
    %total=tic;
 
    if (startn>1)
         image_start= image_start  + (  constantsize*(startn-1));
             label_index=    label_index+(startn-1);
    end 
    
    
    for n=startn:endn
     %   n

   
 
      % if ( ImageLabel==digit)% if label is same as the required digit Read from file system. 
           

       if (Database==1)        
            Itemp=[A(image_start:image_start+constantsize-1)'/255 ];
     
            I2= reshape(Itemp,colcount,colcount);
            I= rot90(fliplr(I2),1);
           ImageLabel=B(label_index)+1; %% to make labels from 1 to 10 
        elseif (Database==2)
            path=[DataBasePath AHDBase_TrainingSet_pathes{n}];
            ImageLabel=training_set_labels(n)+1; %% to make labels from 1 to 10 
            I=imread(path);
         %   I=double(255-imread(path))/255;
       elseif(Database==0)     
            path=[DataBasePath MAHDBase_TrainingSet_pathes{n}];
            ImageLabel=training_set_labels(n)+1; %% to make labels from 1 to 10 
            %I=imread(path);
            Itemp=double(255-imread(path))/255;
            I=~dither(Itemp);
       end    
        
        data{ImageLabel}{ labelSizes(ImageLabel)}= postProcessImage(I);
         labels{ImageLabel}{ labelSizes(ImageLabel)}=ImageLabel;
        labelSizes(ImageLabel)= labelSizes(ImageLabel)+1;  % increment the aprociate size
  
       if(mod(n,5000)==0) 
           n
           datasize=size(data)
           datasizeImageLabel=size(data{ImageLabel})
       end
        
        %end;
        image_start= image_start+ constantsize;
        label_index=label_index+1;
        % addd Image to data.... 
    end  
%clear
%%
elseif (mode==2) % testing  
    %clc
    if (Database==0)
        Datapatch=[databpath '\MAHDBase_TestingSet_pathes' ];
        load(Datapatch);
        DataBasePath=[databpath '\MAHDBase\']
        testLabels=[databpath '\testing_set_labels'];
        load(testLabels);
       
    elseif (Database==2)
        Datapatch=[databpath '\AHDBase_TestingSet_pathes' ];
        load(Datapatch);
        DataBasePath=[databpath '\AHDBase\']
        testLabels=[databpath '\testing_set_labels'];
        load(testLabels);
        
    elseif (Database==1)
        DataBasePath=[databpath '\t10k-images.idx3-ubyte' ]
        testLabels=[databpath '\t10k-labels.idx1-ubyte'];
        fid= fopen(DataBasePath,'r');
        A=fread(fid);

    end 
    image_start=17;
    label_index=9;
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    for n=1:testCount
            
        if (Database==1)  
            ImageLabel=B(label_index)+1; 
     %           d=B(label_index);
     
        Itemp=[A(image_start:image_start+constantsize-1)'/255 ];
     
            I2= reshape(Itemp,colcount,colcount);
            I= rot90(fliplr(I2),1);
     
%       % I=[A(image_start:image_start+28*28-1)'/255 d];
%           Itemp=[A(image_start:image_start+28*28-1)'/255 ];
%           % colcount=28;    
%         %I=vec2mat(Itemp,colcount);
%         %I=reshape(Itemp,colcount,colcount);
%            I=rot90(reshape(Itemp,colcount,colcount));
         elseif (Database==2) 
            ImageLabel=testing_set_labels(n)+1;
         path=[DataBasePath AHDBase_TestingSet_pathes{n}];
         I=imread(path);
        %I=double(255-imread(path))/255;
        elseif (Database==0) 
            ImageLabel=testing_set_labels(n)+1;
         path=[DataBasePath MAHDBase_TestingSet_pathes{n}];
       %   I=imread(path);
          Itemp=double(255-imread(path))/255;
            I=~dither(Itemp)
        end 
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%time was
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%here 
        data{ImageLabel}{ labelSizes(ImageLabel)}=   postProcessImage(I);
         labels{ImageLabel}{ labelSizes(ImageLabel)}=ImageLabel;  
         labelSizes(ImageLabel)= labelSizes(ImageLabel)+1;
       if(mod(n,5000)==0) 
           n
           datasize=size(data)
            datasizeImageLabel=size(data{ImageLabel})
       end
    
        %end;
        image_start= image_start+ constantsize;
        label_index=label_index+1;
    end 
end

%FeatString='OVO_Test';
 warning on