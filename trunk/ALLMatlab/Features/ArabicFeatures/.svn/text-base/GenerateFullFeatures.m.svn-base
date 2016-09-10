function y=GenerateFullFeatures(TrainTest)
 
time=0;
totalFeatureTimes=0;
Features=[];
feat=0;
%[Data4,labels4,Ssizes4]=LoadMNISTDB( 4, TrainTest);

[Data,labels,Ssizes]=LoadMNISTDB( 4, TrainTest);

  timeUsingcput=        cputime;

for digit1=0:9
%    for digit2=digit1+1:9
        
      digit1  
%Featname=[text num2str(digit1) num2str(digit2)];

% if (text=='a')
%     
% Featnum=digit1*10+digit2;
% end 
% if (text=='b')  
% 
% 
% end

%Featnum=(digit1*10+digit2)+100
%if (digit1==0)
    
%Featvectors=FeatureVector(Data4,labels4,Ssizes4,Featnum);


 
%else 

%t1 is the list of times of all features for this digit 
%t2 is the total time of all with the reading . 
   
    [Featvectors,timeArray,t2,t3]=ComputeFeatures( Data,labels,Ssizes,digit1,0) ;
    
AverageGmaskTime=sum(timeArray)/length(timeArray)
totalTime=AverageGmaskTime*length(timeArray)
    %end 

 time=totalTime+time
 t2
 totalFeatureTimes=t2+ totalFeatureTimes
totalFeauretimeAverage=t3



if (digit1==0)
 Features=[ Featvectors];

else 
     
  FeatvectorsSize= size(Featvectors)
   
FeaturesSize= size(Features)
    Features=[Features; Featvectors];
end






   % end
end 

FeatString='OVO_Test';
TimeFilename=[FeatString  '_time.txt'];
 fid2= fopen( TimeFilename,'wt');
  fprintf(fid2,' %s \n',['-----Time  for the features  ' FeatString  ]);
 fprintf(fid2,' %s  %8.3f   \n',' TotalTime (computed without reading)=  ',time);

 fprintf(fid2,' %s  %8.3f   \n',' TotalTime measured with reading =  ',totalFeatureTimes);
finaltimeUsingCPU=cputime-  timeUsingcput
sizesOFfeatDigit1=size( Features)
CountSamples=sizesOFfeatDigit1(1);
NumFeat=sizesOFfeatDigit1(2);


base=labels;
labelDigits=ones(1,base*10);

for digit=0:9
   labelDigits( (base*digit)+1:(base*(digit+1)))=1*digit;
end 

testing_set=[Features];
labelsize=size(labelDigits)
labels_set=labelDigits;


%%%%%%%%%%%%%%%%% now add the 

 if(TrainTest==0)
     name=['train_AllFeatures.txt'];
 else 
     name=['test_AllFeatures.txt'];
 end 

 
sizeOFFeatures=size(Features) 
CountSamples=sizeOFFeatures(1);
NumFeat=sizeOFFeatures(2);

if(TrainTest==0)

train_labels=labels_set';
trainset=testing_set;
save('trainSet','trainset');
save('trainlabels','train_labels');
else
    test_labels=labels_set';
    test_set=testing_set;
    save('testSet','test_set');
save('testlabels','test_labels');
    
end 
fid= fopen(name,'wt');
 
  
 fprintf(fid,'%d %d \n',CountSamples,NumFeat+1);
 %testing_set= testing_set.^(0.5);
format=[repmat('%d ',1,NumFeat+1) '\n'];
 
  

%testing_set_labels_small=testing_set_labels(1:testCount);
fprintf(fid,format,[testing_set  labels_set']');

fclose(fid);
time
 