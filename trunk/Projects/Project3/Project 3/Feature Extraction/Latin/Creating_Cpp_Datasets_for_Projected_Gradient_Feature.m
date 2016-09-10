clear
clc

fid= fopen('gradproj_ourfeatures_latin_training_set_full.txt','wt');
fprintf(fid,'%d %d \n',60e3,200+1);

fid2=fopen('C:\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);

format=[repmat('%d ',1,200+1) '\n'];

for k=1:6
    k
    load(['grad_proj_training_set_part_' int2str(k)]);
    start= 9+ (k-1)*10e3;
    training_set= training_set.^(0.5);
    fprintf(fid,format,[training_set B(start:start+10e3-1)]');
end

fclose(fid);


clear
clc

fid= fopen('gradproj_ourfeatures_latin_testing_set_full.txt','wt');
fprintf(fid,'%d %d \n',10e3,200+1);

fid2=fopen('C:\MNIST\t10k-labels.idx1-ubyte','r');
B=fread(fid2);

format=[repmat('%d ',1,200+1) '\n'];
load grad_proj_testing_set testing_set

testing_set= testing_set.^(0.5);

fprintf(fid,format,[testing_set B(9:end)]');

fclose(fid);




