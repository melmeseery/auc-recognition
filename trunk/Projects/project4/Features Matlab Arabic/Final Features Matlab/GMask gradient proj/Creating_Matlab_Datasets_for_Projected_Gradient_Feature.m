clear
clc
warning off





%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
MAHD=[databpath '\MAHDBase_TrainingSet_pathes' ];
load(MAHD);
trainLabels=[databpath '\training_set_labels'];
load(trainLabels);
MAHDBase=[databpath '\MAHDBase\']
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%





%%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');

variance = (sqrt(2)*4/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);

training_set=zeros(trainCounts,200);
for n=1:trainCounts
    if(mod(n,100)==0)
        n
    end
    %path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    path=[MAHDBase MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;


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
    training_set(n,:)= gmask_Local_Gradient_proj(I,h);



end

save arabic_gmask_grad_proj_training_set training_set





clear
clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

databpath=getADDataBasePath();
[trainCounts,testCount]=getMaxSampleCount()
%MAHD=[databpath '\MAHDBase_TrainingSet_pathes' ];
%load(MAHD);
%trainLabels=[databpath '\training_set_labels'];
%load(trainLabels);
testLabels=[databpath '\MAHDBase_TestingSet_pathes'];
load(testLabels);
MAHDBase=[databpath '\MAHDBase\']

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TestingSet_pathes');

variance = (sqrt(2)*4/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);

testing_set=zeros(testCount,200);
for n=1:testCount
    if(mod(n,100)==0)
        n
    end
    path=[MAHDBase MAHDBase_TestingSet_pathes{n}];
    I=double(255-imread(path))/255;


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

    testing_set(n,:)= gmask_Local_Gradient_proj(I,h);



end

save arabic_gmask_grad_proj_testing_set testing_set

warning on

