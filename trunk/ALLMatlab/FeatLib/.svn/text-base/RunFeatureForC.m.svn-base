function y=RunFeatureForC(feat,Database,mode)
[FeatString,numFeat]=getFeatureNameCount(feat)

if (mode==0||mode==1||mode==2)
    tic ;
timeArray=RunFeature( feat, numFeat, Database,mode,FeatString);

totaltimeREading=toc;
AverageGmaskTime=sum(timeArray)/length(timeArray);
totalTime=AverageGmaskTime*length(timeArray);

TimeFilename=[FeatString  '_time.txt'];
 fid2= fopen( TimeFilename,'wt');
  fprintf(fid2,' %s \n',['-----Time  for the features  ' FeatString  ]);
 fprintf(fid2,' %s  %8.3f   \n',' TotalTime=  ',totalTime);
  fprintf(fid2,' %s  %8.3f   \n',' totaltimeREading=  ',totaltimeREading);
 

end 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

if (mode==0||mode==1||mode==3)
databpath=getADDataBasePath(Database);
[trainCounts,testCount]=getMaxSampleCount();
if (Database==0)
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
end 
if (Database==1)
   [trainLabels,testLabels]= LoadMinistTrainLabels();
   load(trainLabels);
end

FileName=[ FeatString '_ourfeatures_training_set_full.txt' ]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
if (Database==0)
fid= fopen(['arabic_' FileName],'wt');
end 
if (Database==1)
    fid= fopen(['latin_' FileName],'wt');
end 
fprintf(fid,'%d %d \n',trainCounts,numFeat+1);


format=[repmat('%d ',1,numFeat+1) '\n'];


loadname= [FeatString   '_training_set']
if (Database==0)

load( ['arabic_' loadname  ], 'training_set')

end 
if (Database==1)
 load( ['latin_' loadname  ], 'training_set')  
%load latin_gmask_grad_proj_training_set training_set 
    
    
end 




training_set= training_set.^(0.5);
%%%%%%%%%%%%%%%%%%%%%%%%%  i have changed this 
ltrain=length(training_set_labels)
size(ltrain)
training_set_labels_small=training_set_labels(1:trainCounts);

l1=length (training_set)
size(training_set)
l2=length (training_set_labels_small)
size (training_set_labels_small)

matFile=[training_set training_set_labels_small]';
fprintf(fid,format,matFile);
fclose(fid);

end 
%clear

if (mode==0||mode==1||mode==4)
%clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%55

%MAHD=[databpath '\MAHDBase_TrainingSet_pathes' ];
%load(MAHD);

%testLabels=[databpath '\MAHDBase_TestingSet_pathes'];
%Database=1;
databpath=getADDataBasePath(Database)
[trainCounts,testCount]=getMaxSampleCount();
if (Database==0)
testLabels=[databpath '\testing_set_labels'];
load(testLabels);
end 
if (Database==1)
    [ trainLabels,testLabels]= LoadMinistTrainLabels()
  load(testLabels)
end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
FileName=[ FeatString '_ourfeatures_testing_set_full.txt' ]

if (Database==0)
fid= fopen(['arabic_' FileName],'wt');
end 
if (Database==1)
    fid= fopen(['latin_' FileName],'wt');
end 

 
fprintf(fid,'%d %d \n',testCount,numFeat+1);

%load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');


format=[repmat('%d ',1,numFeat+1) '\n'];

loadname= [FeatString   '_testing_set']

if (Database==0)
load (['arabic_' loadname] ,'testing_set')
end 
if (Database==1)
    load (['latin_' loadname] ,'testing_set')
   % load latin_gmask_grad_proj_testing_set testing_set
    
end 

testing_set= testing_set.^(0.5);

testing_set_labels_small=testing_set_labels(1:testCount);
fprintf(fid,format,[testing_set testing_set_labels_small]');

fclose(fid);

end 