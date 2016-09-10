clear
clc
databpath=getADDataBasePath();


MAHD=[databpath '\MAHDBase_TrainingSet_pathes' ];
load(MAHD);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
MAHDBase=[databpath '\MAHDBase\']

%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');
%load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');

fid= fopen('mahdbase32by32_training_set.txt','wt');
fprintf(fid,'%d %d \n',60e3,32*32+1);

format=[repmat('%d ',1,32*32+1) '\n'];


for n=1:60e3
    if(mod(n,100)==0)
        n
    end

   % path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
     path=[MAHDBase MAHDBase_TrainingSet_pathes{n}];
    I=(255-double(imread(path)))/255;
    d=training_set_labels(n);
    
    
    I=[zeros(2,28); I; zeros(2,28)];
    I=[zeros(32,2) I zeros(32,2)];
%     imshow(I);
%     d
%     input('zeo:');
    
    I_=I';
    x=[I_(:)' d];
    fprintf(fid,format,x');

    
    
end


fclose(fid);





clear
clc


MAHD_Test=[databpath '\AHDBase_TestingSet_pathes' ];
load(MAHD_Test);
testLabels=[databpath '\testing_set_labels'];
load(testLabels);

%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TestingSet_pathes');
%load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');

fid= fopen('mahdbase32by32_testing_set.txt','wt');
fprintf(fid,'%d %d \n',10e3,32*32+1);

format=[repmat('%d ',1,32*32+1) '\n'];

for n=1:10e3
    if(mod(n,100)==0)
        n
    end

   % path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TestingSet_pathes{n}];
    path=[MAHDBase  MAHDBase_TestingSet_pathes{n}];

    I=(255-double(imread(path)))/255;
    d=testing_set_labels(n);
    
    I=[zeros(2,28); I; zeros(2,28)];
    I=[zeros(32,2) I zeros(32,2)];
%     imshow(I);
%     d
%     input('zeo:');
    
    I_=I';
    x=[I_(:)' d];
    fprintf(fid,format,x');

    
    
end


fclose(fid);
