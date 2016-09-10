function y=writeFeatures(filename,trainTest,Database,varargin )


 disp('Reading data...');
 [data,labels]=readImages(trainTest,Database);
 disp('Compute features... ');
 [Y,Samples,FeatureSize]=computeHmmFeatures( data,0,varargin{:});


numFeat=FeatureSize;

trainCounts=Samples;


fid= fopen([filename],'wt');
fprintf(fid,'%d %d \n',trainCounts,numFeat+1);
format=[repmat('%d ',1,numFeat+1) '\n'];
matFile=[ Y' labels]';
fprintf(fid,format,matFile);
fclose(fid);
disp('Fisish writing the file');
y=1