function y=writeFeatures(filename,trainTest,Database,ReReadData,varargin )


       %Y=data;

 disp('Reading data...');  
 [data,labels]=readStrokesDataSet(trainTest,0,ReReadData);
  
 disp('Compute features... ');
 [Y,Samples,FeatureSize,labelPerSample]=computeHmmFeatures( data,1,varargin{:});


numFeat=FeatureSize

trainCounts=Samples

fid= fopen([filename],'wt');
fprintf(fid,'%d %d \n',trainCounts,numFeat+1);
format=[repmat('%d ',1,numFeat+1) '\n'];
sizeOFy=size(Y)
silbaels=size(labels)
silabelPerSample=size(labelPerSample)
matFile=[ Y' labelPerSample]';
fprintf(fid,format,matFile);
fclose(fid);
disp('Finish writing the file');
y=1