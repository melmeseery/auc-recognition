clc
clear all
close all
%load 'DataTrain'


%  Load the data  ===> (1(train 1 or test 0), 1, 0 ( load 0 or read data 1
%  ));
 [Data,label]=readStrokesDataSet(1,1,0);
 
 %%%% this line is used to 
 [maxNpoints ,minNpoints ,mean,variance] =  getMaxMinFromData(Data);
 % save 
 ComputeFeature=1;
 
 
 if ( ComputeFeature )
  i=1;
  dataTrains=[];
  for d=1:10
      
      Digit=Data{d};
       
      d
      for n=1:length(Digit)
         % n
         Stroke=Digit{n};
         Feat=ComputeZeroFeatures(Stroke);
        % fea=getNormalizedFeature(Stroke,maxNpoints ,minNpoints ,mean,variance);
         %Feat=[Feat fea];
          dataTrains=[dataTrains; Feat];
          if (d==1)
          groups(i)=0;
          else
          groups(i)=1;
          end 
          i=i+1;
      end 
      
  end 
  
  i

  [r  c]=size(  dataTrains);
  
  
  save dataFeatures  dataTrains groups
 else
     load dataFeatures 
 end 
  %  writeTrainSet('TrainSetProjection12.arff',c,r,  dataTrains,  groups,[0  1]);
  %save dataForSVM 
%linear
%Parameters(1)= 1;%using RBF kernel
%Parameters(3)= 0.0078;%setting Gamma
%Parameters(5)= 152.2185;%setting C
%clear all 
%load  dataForSVM
disp('Training the SVM ... ')
%%%%%%MATLAB SVM
opts = svmsmoset('Display','final','MaxIter',50000,'KernelCacheLimit',1000);
%[AlphaY, SVs, Bias, Parameters, nSV, nLabel] = SVMTrain(training_set, labels_set,Parameters );
svmStruct = svmtrain(dataTrains,groups,'Method','SMO','SMO_Opts',opts);

save model svmStruct






%%%%%%%%%%%%%% All other traiin.... 
h=0;
%
for i=2:length(Data)
    h=h+1;
    data{h}=Data{i};
end
% data{10}=Data{1};
for i=1:length(data)
    i
    h=0;
    for j=1:length(data{i})
       if length(data{i}{j}(:,1))>3
           h=h+1;
           feature1{i}{h} = mixing([data{i}{j}(:,1) data{i}{j}(:,2)],11);
            feature{i}{h} =  [feature1{i}{h};addedfeatures([data{i}{j}(:,1) data{i}{j}(:,2)])];
           
%            feature{i}{h}=chainCode64(data{i}{j})';
       end
    end
end

for l=1:length(feature)
    
   for ll=1:length(feature{l})
    
         set{l}(:,ll)=feature{l}{ll}(:,1);
       
     
   end
end 
setfinal{1}=set;
net1=svmeachtwo(setfinal);
