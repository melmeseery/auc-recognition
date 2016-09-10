clear
clc

load ('C:\Documents and Settings\ezzat\Desktop\AHDBase_TestingSet_pathes') 
load ('C:\Documents and Settings\ezzat\Desktop\testing_set_labels') 

load avg_bbox_area 
load FS_net 
load SVM_Classifier 
AlphaY= SVM_Classifier{1};
SVs= SVM_Classifier{2};
Bias= SVM_Classifier{3};
Parameters= SVM_Classifier{4};
nSV= SVM_Classifier{5};
nLabel= SVM_Classifier{6};

confusion_matrix=zeros(10,10);

th_1=.1;
th_2=.8;

tic
% Testing
for n=1:10000
    if(mod(n,100)==0)
        n
    end

    d=testing_set_labels(n);

    path=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' AHDBase_TestingSet_pathes{n}];
    I=~imread(path);

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Feature Extraction for First Stage (ANN)  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    [width height]= size(I);
    
    x=[FixedSizeProfile_2(I,10,10);  width*height/avg_bbox_area];
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    decision_scores = mlpfwd(FS_net, x');
    [v_1 index_of_max]=max(decision_scores);
    decision_scores(index_of_max)=0;
    [v_2 index_of_second_max]=max(decision_scores);
    

    if(and(v_1>th_1,v_2<th_2))
        confusion_matrix(d+1,index_of_max)= confusion_matrix(d+1,index_of_max)+1;
        continue
    end


    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Feature Extraction for Second Stage (SVM)  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    [height width]=size(I);
    P=regionprops(double(I),'Area');
    a=P.Area;
    P=regionprops(double(I),'Eccentricity');
    ecc=P.Eccentricity;
    
    Iup=I(1:floor(height/2),:);
    P=regionprops(double(Iup),'Image');
    Iup=P.Image;
    
    Idown=I(floor(height/2):end,:);
    P=regionprops(double(Idown),'Image');
    Idown=P.Image;
    

    x1=[FixedSizeProfile_2(I,10,10);  width*height/avg_bbox_area;  ...
        a/(width*height);  width/height; ecc];
    x2=[ConcavityVectorFE(I); NumOfTrnsFE(I,10,10); NumOfPxlsFE(I,5,5); Centroid16FE(I)];
    x3=[ConcavityVectorFE(Iup); NumOfTrnsFE(Iup,10,10); NumOfPxlsFE(Iup,5,5); Centroid16FE(Iup)];
    x4=[TotalNumOfTrnsFE_for6vs9(Idown)];
    
    x=[x1; x2; x3; x4];
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%         
 
    [decision, DecisionValue]= SVMClass(x, AlphaY, SVs, Bias, Parameters, nSV, nLabel);
    confusion_matrix(d+1,decision+1)= confusion_matrix(d+1,decision+1)+1;

end
recognition_time=toc
accuracy=sum(sum(confusion_matrix.*eye(10,10)))/sum(sum(confusion_matrix))

save recognition_time recognition_time 
save confusion_matrix confusion_matrix
save accuracy accuracy

