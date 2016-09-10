clear
clc

fid= fopen('zc_arabic_low_dim_concat_ourfeatures_training_set_full.txt','wt');
fprintf(fid,'%d %d \n',60e3,156+1+1);

load('C:\Documents and Settings\XPPRESP3\Desktop\training_set_labels');

format=[repmat('%d ',1,156+1+1) '\n'];

load arabic_low_dim_concat_training_set training_set
A= training_set;

load arabic_bbox_area_training_set training_set
m=mean(training_set);
v=var(training_set);
save m m
save v v
B= (training_set.^0.5-m)/v;

training_set=[A B];

fprintf(fid,format,[training_set training_set_labels]');


fclose(fid);



clear
clc

fid= fopen('zc_arabic_low_dim_concat_ourfeatures_testing_set_full.txt','wt');
fprintf(fid,'%d %d \n',10e3, 156+1+1);

load('C:\Documents and Settings\XPPRESP3\Desktop\testing_set_labels');

format=[repmat('%d ',1,156+1+1) '\n'];

load arabic_low_dim_concat_testing_set testing_set
A= testing_set;

load arabic_bbox_area_testing_set testing_set
load m m
load v v
B= (testing_set.^0.5-m)/v;

testing_set=[A B];

fprintf(fid,format,[testing_set testing_set_labels]');


fclose(fid);





