clear
clc
warning off

load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TrainingSet_pathes');
load('C:\Documents and Settings\ezzat\Desktop\AHDBase_TrainingSet_pathes');


% avg_bbox_area=0;
% for n=1:10e3
%     if(mod(n,100)==0)
%         n
%     end
%     path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TrainingSet_pathes{n}];
%     I_adbase=1-imread(path);
%     P=regionprops(double(I_adbase),'Image');
%     [r c]= size(P.Image);
%     avg_bbox_area= avg_bbox_area+ r*c;
% end
% avg_bbox_area= avg_bbox_area/60e3;
% save avg_bbox_area avg_bbox_area



load avg_bbox_area avg_bbox_area
training_set=zeros(60e3,201);
for n=1:60e3
    if(mod(n,100)==0)
        n
    end
    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TrainingSet_pathes{n}];
    I=double(255-imread(path))/255;

    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TrainingSet_pathes{n}];
    I_adbase=1-imread(path);
    P=regionprops(double(I_adbase),'Image');
    [r c]= size(P.Image);
    bbox_area= r*c;


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
    training_set(n,:)= [Local_Gradient_proj(I) bbox_area/avg_bbox_area];



end

save arabic_ZC_grad_proj_training_set training_set




clear
clc
load('C:\Documents and Settings\ezzat\Desktop\MAHDBase_TestingSet_pathes');
load('C:\Documents and Settings\ezzat\Desktop\AHDBase_TestingSet_pathes');


load avg_bbox_area avg_bbox_area
testing_set=zeros(10e3,201);
for n=1:10e3
    if(mod(n,100)==0)
        n
    end
    path=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\' MAHDBase_TestingSet_pathes{n}];
    I=double(255-imread(path))/255;

    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TestingSet_pathes{n}];
    I_adbase=1-imread(path);
    P=regionprops(double(I_adbase),'Image');
    [r c]= size(P.Image);
    bbox_area= r*c;


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
    testing_set(n,:)= [Local_Gradient_proj(I) bbox_area/avg_bbox_area];



end

save arabic_ZC_grad_proj_testing_set testing_set
