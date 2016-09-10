function y=RunFeature( feat, numFeat, Database,mode,FeatString )
warning off

if (mode==0||mode==1)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% database choice 1 = minist 0= ad base or mad base. 
%%%%%%%%%%%%55
%Database=1;
databpath=getADDataBasePath(Database)
%%5 get the count of the test and train sample to use
[trainCounts,testCount]=getMaxSampleCount()
%%%

%%%
if (Database==0)
Datapatch=[databpath '\MAHDBase_TrainingSet_pathes' ];
load(Datapatch);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
DataBasePath=[databpath '\MAHDBase\']
end 
if (Database==1)
    %Datapatch=[databpath '\MAHDBase_TrainingSet_pathes' ];
%load(Datapatch);
%trainLabels=[databpath '\training_set_labels'];
%load(trainLabels);
%DataBasePath=[databpath '\MAHDBase\']
DataBasePath=[databpath '\train-images.idx3-ubyte' ]

fid= fopen(DataBasePath,'r');
A=fread(fid);
trainLabels=[databpath '\train-labels.idx1-ubyte'];
%fid2=fopen('Z:\MNIST\train-labels.idx1-ubyte','r');
fid2=fopen(trainLabels ,'r');
B=fread(fid2);
end 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

image_start=17;

label_index=9;
%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');

variance = (sqrt(2)*3/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);

training_set=zeros(trainCounts,numFeat);

total=tic;
for n=1:trainCounts
    if(mod(n,100)==0)
        n
    end
%     path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
%     I=double(255-imread(path))/255;

  if (Database==1)
        
        d=B(label_index);

    Itemp=[A(image_start:image_start+28*28-1)'/255 ];
       colcount=28;    
    I=vec2mat(Itemp,colcount);
    %if (n<=10)
     %   [r c]= size(I)
    %end
    %for r=1:28
    %fprintf(fid,format,x');

    

    end 
    if(Database==0)
        
    %path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    path=[DataBasePath MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;
    end 
    
timeGmask=tic;
    
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

  training_set(n,:)=ComputeFeatures(feat,I,h);
timeG(n)=toc(timeGmask);



    image_start= image_start+ 28*28;
    label_index=label_index+1;
end
 
AverageGmaskTime=sum(timeG)/length(timeG);
TotalFeatureOnlyTime=AverageGmaskTime*length(timeG);
totalTime=toc(total)
totalTimeAVG=totalTime/trainCounts




%save arabic_kirsh4_training_set training_set   save latin_kirsh4_training_set training_set  
if (Database==0)
    asaveFile=['arabic_' FeatString  '_training_set' ]
    save( asaveFile, 'training_set');

end 
if (Database==1)
    lsaveFile=['latin_' FeatString  '_training_set' ]
%    save lsaveFile_kirsh4_training_set training_set   
     save(lsaveFile,'training_set');
end



TimeFilename=[FeatString  '_Train_time.txt'];
 fid2= fopen( TimeFilename,'wt');
  fprintf(fid2,' %s \n',['-----Time  for the features  60,000 samples  ' FeatString  ]);
 fprintf(fid2,' %s  %8.3f   \n',' TotalTime (computed without reading)=  ',TotalFeatureOnlyTime);

 fprintf(fid2,' %s  %8.3f   \n',' TotalTime measured with reading =  ',totalTime);
y=timeG;
end 

%clear
if (mode==0||mode==2)
%clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% database choice 1 = minist 2= ad base or mad base. 
%%%%%%%%%%%%55
%Database=1;
databpath=getADDataBasePath(Database)
%%5 get the count of the test and train sample to use
[trainCounts,testCount]=getMaxSampleCount()
%%%

%%%
if (Database==0)
Datapatch=[databpath '\MAHDBase_TestingSet_pathes' ];
load(Datapatch);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
DataBasePath=[databpath '\MAHDBase\']
end 
if (Database==1)
    %Datapatch=[databpath '\MAHDBase_TrainingSet_pathes' ];
%load(Datapatch);
%trainLabels=[databpath '\training_set_labels'];
%load(trainLabels);
%DataBasePath=[databpath '\MAHDBase\']
DataBasePath=[databpath '\t10k-images.idx3-ubyte' ]
%testLabels=[databpath '\t10k-labels.idx1-ubyte'];

fid= fopen(DataBasePath,'r');
A=fread(fid);
%fid2=fopen('Z:\MNIST\train-labels.idx1-ubyte','r');
%fid2=fopen(testLabels ,'r');
%B=fread(fid2);

end 


image_start=17;
label_index=9;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TestingSet_pathes');

variance = (sqrt(2)*3/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);

testing_set=zeros(testCount,numFeat);
totalTest=tic;
for n=1:testCount
    if(mod(n,100)==0)
        n
    end
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
 
     timeGmask=tic;
     timeForcpu=cputime;  
     
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
 
  %  testing_set(n,:)= Kirsh4_g(I,h);
   testing_set(n,:)= ComputeFeatures(feat,I,h);   
timeGTest(n)=toc(timeGmask);
timeTestCPU(n)=cputime-     timeForcpu;
  image_start= image_start+ 28*28;


end
GmaskTimeTest=timeGTest(1:100);
AverageGmaskTimeTest=sum(timeGTest)/length(timeGTest);
TotalFeatureOnlyTimeTest=AverageGmaskTimeTest*length(timeGTest);
totalTimeTeset=toc(totalTest);
totalTimeAVG=totalTimeTeset/testCount;

timeTestCPUAvg=sum(timeTestCPU)/length(timeTestCPU);
timeTestCPUFinal=timeTestCPUAvg*length(timeTestCPU);




if (Database==0)
     asaveFile=['arabic_' FeatString  '_testing_set' ]
%save arabic_kirsh4_testing_set testing_set

        save (asaveFile, 'testing_set')
end 
if (Database==1)
     lsaveFile=['latin_' FeatString  '_testing_set' ]
    save(lsaveFile, 'testing_set'  )
end

y=timeGTest;

TimeFilename=[FeatString  '_Test_time.txt'];
 fid2= fopen( TimeFilename,'wt');
  fprintf(fid2,' %s \n',['-----Time  for the features  Testing 10,000 samples  ' FeatString  ]);

 fprintf(fid2,' %s  %8.3f   \n',' TotalTime (computed without reading)=  ',TotalFeatureOnlyTimeTest);
fprintf(fid2,' %s  %8.3f   \n',' TotalTime (computed without reading using CPUtime)=  ',  timeTestCPUFinal);
 fprintf(fid2,' %s  %8.3f   \n',' TotalTime measured with reading =  ',totalTimeTeset);
end
%FeatString='OVO_Test';

 warning on

