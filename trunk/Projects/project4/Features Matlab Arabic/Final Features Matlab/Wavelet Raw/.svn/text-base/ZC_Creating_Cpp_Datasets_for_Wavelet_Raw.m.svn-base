clear
clc

fid= fopen('zc_arabic_wavelet_raw_ourfeatures_training_set_full.txt','wt');
fprintf(fid,'%d %d \n',60e3,64+1+1);

load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');

format=[repmat('%d ',1,64+1+1) '\n'];

load arabic_wavelet_raw_training_set training_set
A= training_set.^(0.5);

load arabic_bbox_area_training_set training_set
B= training_set.^(0.5);

training_set=[A B];

fprintf(fid,format,[training_set training_set_labels]');


fclose(fid);



clear
clc

fid= fopen('zc_arabic_wavelet_raw_ourfeatures_testing_set_full.txt','wt');
fprintf(fid,'%d %d \n',10e3,64+1+1);

load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');

format=[repmat('%d ',1,64+1+1) '\n'];

load arabic_wavelet_raw_testing_set testing_set
A= testing_set.^(0.5);

load arabic_bbox_area_testing_set testing_set
B= testing_set.^(0.5);

testing_set=[A B];

fprintf(fid,format,[testing_set testing_set_labels]');


fclose(fid);





