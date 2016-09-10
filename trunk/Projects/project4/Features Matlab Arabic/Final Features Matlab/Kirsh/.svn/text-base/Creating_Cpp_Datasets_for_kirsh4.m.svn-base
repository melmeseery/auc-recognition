% clear
% clc
% 
% fid= fopen('kirsh4_ourfeatures_training_set_full.txt','wt');
% fprintf(fid,'%d %d \n',60e3,100+1);
% 
% load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
% 
% format=[repmat('%d ',1,100+1) '\n'];
% 
% 
% load arabic_kirsh4_training_set training_set
% training_set= training_set.^(0.5);
% fprintf(fid,format,[training_set training_set_labels]');
% 
% 
% fclose(fid);


clear
clc

fid= fopen('arabic_kirsh4_ourfeatures_testing_set_full.txt','wt');
fprintf(fid,'%d %d \n',10e3,100+1);

load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');


format=[repmat('%d ',1,100+1) '\n'];
load arabic_kirsh4_testing_set testing_set

testing_set= testing_set.^(0.5);

fprintf(fid,format,[testing_set testing_set_labels]');

fclose(fid);

