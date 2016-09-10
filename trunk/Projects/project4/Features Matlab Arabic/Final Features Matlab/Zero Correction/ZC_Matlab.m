clear
clc
warning off

load('C:\Documents and Settings\ezzat\Desktop\AHDBase_TrainingSet_pathes');

load avg_bbox_area avg_bbox_area

training_set=zeros(60e3,1);
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    
    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TrainingSet_pathes{n}];
    I_adbase=1-imread(path);
    P=regionprops(double(I_adbase),'Image');
    [r c]= size(P.Image);
    bbox_area= r*c;
    
    training_set(n)= bbox_area/avg_bbox_area;
   

end

save arabic_bbox_area_training_set training_set



clear
clc
warning off

load('C:\Documents and Settings\ezzat\Desktop\AHDBase_TestingSet_pathes');

load avg_bbox_area avg_bbox_area

testing_set=zeros(10e3,1);
for n=1:10e3
    if(mod(n,100)==0)
        n
    end
    
    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TestingSet_pathes{n}];
    I_adbase=1-imread(path);
    P=regionprops(double(I_adbase),'Image');
    [r c]= size(P.Image);
    bbox_area= r*c;
    
    testing_set(n)= bbox_area/avg_bbox_area;
    

end

save arabic_bbox_area_testing_set testing_set






