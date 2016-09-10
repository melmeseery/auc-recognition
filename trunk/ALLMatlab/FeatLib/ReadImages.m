function data=ReadImages(mode , database , digit )
%%Read the data from The digit file system and store it in data....

warning off
% get the relative path of data.... 
databpath=getADDataBasePath(Database)
%% get the count of the test and train sample to use
[trainCounts,testCount]=getMaxSampleCount()

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
    end
    else if (Database==1)
        DataBasePath=[databpath '\train-images.idx3-ubyte' ]
        fid= fopen(DataBasePath,'r');
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
    k=1;
    for n=1:trainCounts
        if(mod(n,100)==0)
            n
        end
        if (Database==1)  Image_Label=B(label_index); end
        if (Database==0)  Image_Label=trainLabels(n);   end 
       if ( Image_Label==digit)% if label is same as the required digit Read from file system. 
       if (Database==1)        
            Itemp=[A(image_start:image_start+28*28-1)'/255 ];
            colcount=28;    
            I=vec2mat(Itemp,colcount);
        end 
        if(Database==0)     
            path=[DataBasePath MAHDBase_TrainingSet_pathes{n}];
            I=double(255-imread(path))/255;
        end     
        data{k}=   postProcessImage(I);
        k=k+1;
        end;
        image_start= image_start+ 28*28;
        label_index=label_index+1;
        % addd Image to data.... 
    end  
end
%clear
%%
if (mode==2) % testing  
    %clc
    if (Database==0)
        Datapatch=[databpath '\MAHDBase_TestingSet_pathes' ];
        load(Datapatch);
        DataBasePath=[databpath '\MAHDBase\']
        testLabels=[databpath '\testing_set_labels'];
    end 
    if (Database==1)
        DataBasePath=[databpath '\t10k-images.idx3-ubyte' ]
        testLabels=[databpath '\t10k-labels.idx1-ubyte'];
        fid= fopen(DataBasePath,'r');
        A=fread(fid);

    end 
    image_start=17;
    label_index=9;
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
   k=1;
    for n=1:testCount
        if(mod(n,100)==0)
            n
        end
        if (Database==1)  Image_Label=B(label_index); end
        if (Database==0)  Image_Label=testLabels(n);   end 
        if ( Image_Label==digit)% if label is same as the required digit Read from file system. 
        if (Database==1)
     %           d=B(label_index);
      % I=[A(image_start:image_start+28*28-1)'/255 d];
          Itemp=[A(image_start:image_start+28*28-1)'/255 ];
           colcount=28;    
        I=vec2mat(Itemp,colcount);
        end 
        if (Database==0)
         path=[DataBasePath MAHDBase_TestingSet_pathes{n}];
        I=double(255-imread(path))/255;
        end 
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%time was
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%here 
        data{k}=   postProcessImage(I);
        k=k+1;
        end;
        image_start= image_start+ 28*28;
        label_index=label_index+1;
    end 
end

%FeatString='OVO_Test';
 warning on