 clc 
clear all 

load 'ResultTestAllFourMajoursTestSet'
method =55; 

 correct=0; 
 error=0;
  DS=DecisionValues;
  FR=FinalResult;
%  
 DS= DecisionValues(:,[2 4]) ;
 
 FR=FinalResult(:,[ 2  4 ]);
%  
 %DecisionValues(DecisionValues==Inf)=-Inf; 
 t=2; 
 
 for s=1:length(ActualLabels)

     if (method==1)
       [v c]=min(DS(s,:));
        label=FR(s,c);
%      
     %%%%% get max voting.. 
     else 
    % dec=DS(s,:);
     labels=FR(s,:);
     votes=zeros(1,10);
     for kk=1:length(labels)
         votes(labels(kk))= votes(labels(kk))+1; 
     end
     [m c]=max(votes);
     label=c; 
     end 
%      if (m==t)
%      
%           [v c]=min(DS(s,:));
%         label=FR(s,c);
%          
%      end 
     
     if (label==ActualLabels(s))
         correct=correct+1; 
     else 
         ActualLabels(s)
          ds=DS(s,:)
          f=FR(s,:)
          l=label
         error=error+1 
         
     end 
 end 
 
 result=correct/length(ActualLabels)*100






% %  image_start= image_start+ constantsize;
% %       Itemp=[A(image_start:image_start+constantsize-1)'/255 ];
% %    I= reshape(Itemp,colcount,colcount);
% %     I2=rot90(fliplr(I),1);
% %     
% %     subplot(2,1,1);
% %     imshow(I)
% %         subplot(2,1,2);
% %     imshow(I2)
% % %  load 'Images_Data_Arabic' ;
% % clear all 
% %  clc
% %  i=4;
% %  j=50;
% %  Image=data{i}{j};
% %  imshow(Image);
% %  size(Image)
% %     rowColumn=1;
% %     constantSize=20;
% 
%  load 'Images_Data_Arabic' 
%  [dataTrains, groups] = getData(data,labels);
%   save dataForSVM dataTrains  groups
% 
% %load dataForSVM 
% Data=dataTrains;
% filename='trainSetTryallNodivide.arff';
% %  filename2='testSet.arff';
% [SamplesCounts   FeatureSize]=size(Data);
% for i=1:10
% labelL(i)=i;
% end 
% y=writeTrainSet(filename,FeatureSize,SamplesCounts,Data, groups,  labelL);
% 
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% 
% 
%  load 'Images_Data_Arabic_Test' 
% [dataTest, groups] = getData(data,labels);
%  save dataForSVM_Test dataTest  groups
% % clear
% %load dataForSVM_Test
% Data=dataTest; 
% filename='testSet.arff';
% [SamplesCounts   FeatureSize]=size(Data);
% for i=1:10
% labelL(i)=i;
% end 
% y=writeTrainSet(filename,FeatureSize,SamplesCounts,Data, groups,  labelL);
%  
% 
% 
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% 
% 
%  load 'Images_Data_Arabic' 
% 
%  rowt=0;
%  colt=0;
%  rowtmx=0;
%  coltmx=0;
%  rowtmi=22220;
%  coltmi=22220;
%  num=0;
% for i=1:length(data)
% 
%  rowi=0;
%  coli=0;
%  rowimx=0;
%  colimx=0;
%  rowimi=22220;
%  colimi=22220;
%     numi=0;
%     for j=1:length(data{i})
%         [r c]=size(data{i}{j});
%         %  the mean..........
%          rowt= rowt+r;
%          colt=colt+c;
%          rowi= rowi+r;
%          coli=coli+c;
%          num=num+1; 
%          numi=numi+1; 
%         % the max.. 
%         rowimx=max(rowimx, r);
%         rowtmx=max(rowtmx, r);
%         colimx=max(colimx, c);
%         coltmx=max(coltmx, c);
%         
%         %%%% min 
%          rowimi=min( rowimi, r);
%          colimi=min(colimi,c);
%              rowtmi=min( rowtmi, r);
%          coltmi=min(coltmi,c);
%         
%     end 
%     
%     
%  Rowi(i)=rowi/numi;
%  Coli(i)= coli/numi;
%  Rowimx(i)= rowimx;
%  Colimx(i)=colimx;
%  Rowimi(i)=rowimi;
%  Colimi(i)=colimi;
%    
% end 
% 
% 
% RowAvg= rowt/num
% ColAvg= colt/num
%  rowtmx
%  coltmx
%  rowtmi
%  coltmi
% 
% 
% % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Train test ... 
% %    rowColumn=2;  %% 1 column, 2 rows fixed,
% %    %  0 do not resize 
% %    %%%%%%%   
% %    constantSize=30;  %%5 the size of image (row, column or size 
% %    ByColumn=0;  % 2  rows only, 1  columns only , 0 both.. 
% %     NoSplits=1;  %% 0 no splits use all features ( must be rowcolumn 1 or 2)   and 1 use splits
% %     SizeORSplit=1;   %% 1 use the sconstant as NumberOfSplits  2 use sconstant as split size
% %    sConstant=10;
% % 
% %  
% % [result,correct,sampleSize,conf]=RunTest(TestName,rowColumn,constantSize,NoSplits,ByColumn,SizeORSplit,sConstant)
% %    
% % FinalResult=[FinalResult ;  result correct sampleSize ];
% % confusions{i}=conf;
% 
%  
%  
% 
% % %clc
% % 
% % %clear all
% % 
% % %creating and training the neural network
% % % disp('training the neural network ...' );
% % % FS_net = mlp(21,50,10,'logistic');
% % % [FS_net, error] = mlptrain(FS_net, training_set', training_labels', 500)
% % % save('FS_net','FS_net');
% % warning off all
% % clc
% % clear all 
% % close all 
% % disp('  Reading Train  features.. .....  ');
% %  
% %  
% % %[Data,label]=readStrokesDataSet(1,1,0);
% %  load 'Images_Data_Arabic' 
% % %[maxNpoints ,minNpoints ,mean,variance] =  getMaxMinFromData(Data);
% %  
% %   
% %   i=1;
% %   dataTrains=[];
% %   for d=1:10
% %     
% %       Digit=Data{d};
% %    
% %       d
% %       for n=1:length(Digit)
% %          % n
% %         im=Digit{n};
% %          Feat=ComputeFeatures(im);
% %         % fea=getNormalizedFeature(Stroke,maxNpoints ,minNpoints ,mean,variance);
% %          %Feat=[Feat fea];
% %           dataTrains=[dataTrains; Feat];
% % %           if (d==1)
% %            groups(i)=d;
% % %           else
% % %           groups(i)=1;
% % %           end 
% %           i=i+1;
% %       end 
% %       
% %   end 
% %   
% %   i
% %   
% %   [r  c]=size(  dataTrains);
% %   %  writeTrainSet('TrainSetProjection12.arff',c,r,  dataTrains,  groups,[0  1]);
% %   save dataForSVM dataTrains  groups
% % %linear
% % %Parameters(1)= 1;%using RBF kernel
% % %Parameters(3)= 0.0078;%setting Gamma
% % %Parameters(5)= 152.2185;%setting C
% % clear all 
% % load  dataForSVM
% % disp('Training the SVM ... ')
% % %%%%%%MATLAB SVM
% % opts = svmsmoset('Display','final','MaxIter',500000,'KernelCacheLimit',5000);
% % %[AlphaY, SVs, Bias, Parameters, nSV, nLabel] = SVMTrain(training_set, labels_set,Parameters );
% % svmStruct = svmtrain(dataTrains,groups,'Method','SMO','SMO_Opts',opts);
% % 
% % save model svmStruct
% % 
% % 
% % clear all 
% % 
% % load model;
% % %%%LIBSVM
% % load 'DataState' 
% % %model_linear = svmtrain(groups', dataTrains, '-t 0');
% % 
% %  disp('  Reading Test  features.. .....  ');
% % 
% % 
% %    %[Data,label]=readStrokesDataSet(0,1,0);
% %    
% %    
% %      load model 
% %   i=1;
% %  dataTest=[];
% %  
% %   for d=1:length(Data)
% %       Digit=Data{d};
% %       d
% %       for n=1:length(Digit)
% %          % n
% %          Feat=ComputeZeroFeatures(Data{d}{n});
% %         % fea=getNormalizedFeature(Data{d}{n},maxNpoints ,minNpoints ,mean,variance);
% %         % Feat=[Feat fea];
% %           dataTest=[dataTest; Feat];
% %           DataStrokes{i}=Data{d}{n};
% %           if (d==1)
% %              labels(i)=0;
% %           else
% %              labels(i)=1;
% %           end 
% %           
% %           CorrectLabel(i)=d-1;
% %           i=i+1;
% %       end 
% %       
% %   end
% % i
% % 
% %   [r  c]=size(dataTest);
% % 
% %  % writeTrainSet('TrainSetProjection12.arff',c,r,dataTest,labels,[0  1]);
% % 
% %  disp('  classifiying........ .....  ');
% %  %%%%%MATLAB SM
% %  classes = svmclassify(svmStruct,dataTest);
% % %[classes, accuracy_L, dec_values_L] = svmpredict(labels',dataTest, model_linear);
% % %%%%%LIBSVM
% % %accuracy_L
% % correct=0;
% %  error=0;
% %  correctTwoStage=0;
% % for i=1:length(classes)
% %     
% %     if (classes(i)==labels(i))
% %         correct=correct+1;
% % %         if (classes(i)==0)
% % %          correctTwoStage= correctTwoStage+1;
% % %         end
% %     else 
% %  
% %      %   writeErrorFile(DataStrokes{i}, i,labels(i),classes(i), CorrectLabel(i));
% %          
% %       %  imshow()
% %     end
% % %       if(classes(i)==1)
% % %            result=check_alf_delayed1(DataStrokes{i});
% % %         if (result==labels(i))
% % %             %disp('corrected..........');
% % %               correctTwoStage= correctTwoStage+1;
% % %         else
% % %             error=error+1;
% % %         end 
% % %       else 
% % %           
% % %       end
% %     
% %     
% %     
% % end 
% %  disp(' Result is ........ .....  ');
% % result=(correct/length(classes))*100
% % 
% % testcasses=length(classes)
% % errors=length(classes)-correct
% % 
% % 
% % %%
% % 
% % 
% % 
% % 
% %   clear all 
% % %[maxNpoints ,minNpoints ,mean,variance] =  getMaxMinFromData(Data);
% %  [Data,label]=readStrokesDataSet(0,1,0);
% %  
% %      samples=zeros(1,10);
% %   for d=1:10  
% %       Digit=Data{d};
% %       d
% %          samples(d)=0;
% %       for n=1:length(Digit)
% %          % n
% %       %   Stroke=Digit{n};
% %         samples(d)=   samples(d)+1;
% % 
% %       end 
% %   end 
% % 
% % 
% % 
% % 
% % All=sum(samples);
% % 
% % 
% % 
% % 
% % 
% % 
% % 
% % 
% % 
% % 
% % %  resultTwoStage=(correctTwoStage/length(classes))*100
% % %  errors=length(classes)-correctTwoStage
% % %   error
% % 
% % %SVM_Classifier{1}=AlphaY;
% % %SVM_Classifier{2}=SVs;
% % %SVM_Classifier{3}=Bias;
% % %SVM_Classifier{4}=Parameters;
% % %SVM_Classifier{5}=nSV;
% % %SVM_Classifier{6}=nLabel;
% % 
% % 
% % %%%%%%%%%%%%%%%%%%two stage ... classifiers...
% % 
% % 
% % % for n=1:10000
% % %     x=FS_testing_set(:,n);
% % %     d=testing_set_labels(n);
% % %     
% % %     decision_scores = mlpfwd(FS_net, x');
% % %     [v_1 index_of_max]=max(decision_scores);
% % %     decision_scores(index_of_max)=0;
% % %     [v_2 index_of_second_max]=max(decision_scores);
% % %     
% % % 
% % %     if(and(v_1>th_1,v_2<th_2))
% % %         confusion_matrix(d+1,index_of_max)= confusion_matrix(d+1,index_of_max)+1;
% % %     else
% % %         x=SS_testing_set(:,n);
% % %         [decision, DecisionValue]= SVMClass(x, AlphaY, SVs, Bias, Parameters, nSV, nLabel);
% % %         confusion_matrix(d+1,decision+1)= confusion_matrix(d+1,decision+1)+1;
% % %     end
% % %     
% % % end
% % 
% % 
% % %  %[I,h,w]=PreProcessStrokeOffline(Data{7}{63});
% % % x=[ 0 1 2 3 ;4 5  6 7 ;8 9 10 11; 12  13  14  15; ]
% % % 
% % % [h w]=size(x)
% % % 
% % %  max=(h*2)
% % %  Diagonals=(2*h)-1;
% % % 
% % %   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ///////////
% % %   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % %   for di = 0:Diagonals
% % % dig=[];
% % % 		  if (di>=h)
% % % 
% % %              start=di-h+1;
% % %              
% % %           else
% % %           
% % % 		  start=0;
% % %           end
% % % 
% % %           endi=di-start;
% % %              i =start;
% % %              j=endi;
% % %        	  
% % % 		  for  si = start:endi
% % % 
% % % 				 
% % %                dig=[dig  x(i+1,j+1)];
% % % 			  i=i+1;
% % % 			  j=j-1;
% % %           end
% % %          dig
% % %   end
% % %   
% % %   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \\\\\\\ %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % %   for di = 0:Diagonals
% % % dig=[];
% % % 		  if (di>=h)
% % % 
% % %              start=di-h+1;
% % %               j=0;
% % %           else
% % %           
% % % 		  start=0;
% % %           	  j=(h-1)-di;
% % %           end
% % % 
% % %           endi=di-start;
% % %            i =start;
% % %             % j=endi;
% % %        	  
% % % 		  for  si = start:endi
% % % 
% % % 			 
% % %                dig=[dig  x(i+1,j+1)];
% % % 			  i=i+1;
% % % 			  j=j+1;
% % %           end
% % %            dig
% % %   end
% % % 	   
% % %  
% % 
% % 
% %    
% %  
% % % for di=0:max
% % %      di
% % %     if (di>h)
% % %         start=di-h;
% % %     else
% % %         start=0;
% % %     end 
% % %  
% % %     
% % %     dig=[];
% % %     for el=start:di-start
% % %           i=el;
% % %           j=di-i;
% % %           [i+1    j+1 ]
% % %         dig=[dig  x(i+1,j+1)];
% % %         
% % %     end
% % %    
% % %     dig
% % %     
% % % end 
% % % 
% % % 
% % % 
% % 
% % 
% % % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % % %s=18; Stroke=Strokes{17}';
% % % %%
% % % 
% % % clear all 
% % % close all 
% % %  
% % %   [Data,label]=readStrokesDataSet(0,1,1);
% % %      i=2
% % %   Digit=Data{i};
% % %   for n=1:10
% % %  % n=1;
% % %   figure
% % %    Digit=Data{n};
% % %   i=1;
% % %   for s=1:length(Digit)
% % %     s
% % %     n
% % %     i
% % %    Stroke=Digit{s}';
% % %    x=Stroke(:,1);
% % %    y=Stroke(:,2);
% % %    subplot(6,6,i);
% % %    plot(x,y,'*');
% % %    title([' Stroke ' int2str(s)]);  
% % %    i=i+1;
% % %   end 
% % %   
% % %   end 
% % %    % n=n+1;
% % % 
% % % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % % 
% % % 
% % % 
% % % 
% % % %%
% % % clc
% % % clear all 
% % % load StrokesData;
% % % 
% % %  
% % % i=4;
% % % j=7;
% % % Stroke = Data{i}{j}; 
% % % im=Stroke2Image(Stroke);
% % % imshow(im);
% % % [ResampledOrginal,ResampledTransformed]=PreProcessStroke(Stroke,50,1);
% % %  
% % % Stroke=ResampledOrginal;
% % % %Stroke=s;
% % %  [chain, norma] =chaincode(Stroke,40);
% % % 
% % % %%
% % % clc;
% % % clear all 
% % % close all 
% % % i=1; 
% % % figure ;
% % % for s=1:1:36
% % %     s
% % % Stroke=Strokes{s}';
% % %  if(mod(s,8/2)==0) 
% % %     figure 
% % %     i=1
% % %     s
% % %  end
% % % Ior=Stroke2Image(Stroke);
% % % subplot(2,4,i);
% % % imshow((Ior'));
% % % [newStroke  mean]=PreProcessStroke(Stroke, 90);
% % % Iresample=Stroke2Image(newStroke);
% % % subplot(2,4,i+1);
% % % imshow(Iresample');
% % % i=i+2
% % % end 
% % % 
% % % 
% % % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % % 
% % % %%
% % % %try the features
% % % clear all 
% % % close all 
% % % clc 
% % %  [data,label]=readStrokesDataSet(1,0,0);
% % %  i=3;
% % %  j=25;
% % %  Feature=7;
% % % %NumberOfSplits=8;
% % % OverlapSize=0;
% % % Order=10;
% % % NormalizedSize=50;
% % % isOnline=true;
% % % ReSample=0;
% % % %    for i =1:9
% % % %       for j=25:35
% % %           Stroke=data{i}{j}';
% % %           x=Stroke(:,1);
% % %           y=Stroke(:,2);
% % %           plot(x,y,'*');
% % %           y=OnlineFeatures(Stroke','Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
% % %           y
% % % %       end 
% % % %    end 
% % % 
% % % 
% % % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % % %%
% % % clear all
% % % clear all 
% % % close all 
% % % clc
% % % Feature=7;
% % % %NumberOfSplits=8;
% % % OverlapSize=0;
% % % Order=10;
% % % NormalizedSize=50;
% % % isOnline=true;
% % % ReSample=1;
% % % 
% % % y=writeFeatures('TrainStrokecos.txt',0,0,'Feature',Feature,'Order',Order,'NormalizedSize',NormalizedSize,'ReSample',ReSample);
% % % 
% % % 
% % % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
% % % 
% % % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % % function y=SaveResults(filename,correct,result, correctAll,SizeAll )
% % % 
% % % 
% % % 
% % % 
% % % RecognitionRate=correctAll/SizeAll
% % % 
% % % 
% % % 
% % % fid= fopen([filename],'wt');
% % % 
% % % fprintf(fid,'%s  ',' The recognition rate is ');
% % % 
% % % fprintf(fid,'  %d   \n',RecognitionRate);
% % % 
% % % 
% % % fprintf(fid,' %s   %d  %s  %d ', ' number of correct is ',correctAll, ' out of  ', SizeAll);
% % % 
% % % 
% % % %format=[repmat('%d ',1,numFeat+1) '\n'];
% % % %sizeOFy=size(Y)
% % % %silbaels=size(labels)
% % % %silabelPerSample=size(labelPerSample)
% % % %matFile=[ Y' labelPerSample]';
% % % %fprintf(fid,format,matFile);
% % % fclose(fid);
% % % disp('Finish writing the file');
% % % 
% % % Stroke =[
% % % 
% % %     38   183;
% % %     33   183;
% % %     29   177;
% % %     25   170;
% % %     21   163;
% % %     17   156;
% % %     13   149;
% % %     10   143;
% % %      9   136;
% % %      8   129;
% % %      9   123;
% % %     15   117;
% % %     23   111;
% % %     31   106;
% % %     37   102;
% % %     38    96;
% % %     35    90;
% % %     29    83;
% % %     19    75;
% % %     11    65;
% % %      5    54;
% % %      2    40;
% % %      0    29;
% % %      4    19;
% % %     14    11;
% % %     28     5;
% % %     44     2;
% % %     58     1;
% % %     68     0;
% % %     74     0;
% % %     74     0;
% % % ]
% % % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% % 
% %  