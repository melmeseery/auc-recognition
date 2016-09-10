
function TestFeature(Digit1,Digit2,FeatName,featNam,DB,TrainTest,format)

if (TrainTest==0)
    TestORTrain='Train';
end 
if (TrainTest==1)
    TestORTrain='Test';
end 

[Data,labels,Ssizes]=LoadMNISTDB( DB, TrainTest);


%run the feature s
[featureSetDigit1,AvgTime,TotalTime,AvgTotalTime]=ComputeFeatures(Data,labels,Ssizes,Digit1,FeatName);

 fid2= fopen(['FeatTime' featNam '_' TestORTrain '.txt'],'wt');
  fprintf(fid2,' %s \n',['-----Time  for first digit ' num2str(Digit1) ]);
 fprintf(fid2,' %s  %8.3f   \n','TotalTime',TotalTime );

% fprintf(fid2,' %s  %d  %s  %d \n','TotalTime',TotalTime,'AvgTotalTime',AvgTotalTime );




[featureSetDigit2,AvgTime,TotalTime,AvgTotalTime]=ComputeFeatures(Data,labels,Ssizes,Digit2,FeatName);
 fprintf(fid2,' %s \n', ['-----Time  for second digit ' num2str(Digit2) ]);
 fprintf(fid2,' %s  %8.3f  \n','TotalTime',TotalTime );

% fprintf(fid2,' %s  %d  %s  %d  %s  %d \n',' AvgTime',AvgTime,'TotalTime',TotalTime,'AvgTotalTime',AvgTotalTime );


%%%% now write featuer to the  c file 
 fclose(fid2);
CountSamples=0;
sizesOFfeatDigit1=size(featureSetDigit1)
CountSamples=sizesOFfeatDigit1(1);
NumFeat=sizesOFfeatDigit1(2);
sizesOFfeatDigit2=size(featureSetDigit2)
CountSamples=CountSamples+sizesOFfeatDigit2(1);

%sizesOFfeatDigit1(1)
% zeros(sizesOFfeatDigit1(1));
 
 testing_set=[featureSetDigit1; featureSetDigit2];
 labelDigit1=ones(1,sizesOFfeatDigit1(1));

 %labelDigit1= labelDigit1*Digit1;
 labelDigit2=ones(1,sizesOFfeatDigit2(1));
 labelDigit2=labelDigit2*-1;
 labels_set = [  labelDigit1  ,  labelDigit2];
 
 name='';
 if(TrainTest==0)
     name=['train_' featNam '.txt']
 else 
     name=['test_' featNam '.txt']
 end 
     
 fid= fopen(name,'wt');
 
 if (format==0)
 
 fprintf(fid,'%d %d \n',CountSamples,NumFeat+1);
 %testing_set= testing_set.^(0.5);
format=[repmat('%d ',1,NumFeat+1) '\n'];
 
 else
     
     
     format=[repmat('%d, ',1,NumFeat) ' %d  \n'];
     
 end 
%testing_set_labels_small=testing_set_labels(1:testCount);
fprintf(fid,format,[testing_set  labels_set']');

fclose(fid);