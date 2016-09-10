clear
clc

fid= fopen('arabic_low_dim_concat_ourfeatures_latin_training_set_full.txt','wt');
fprintf(fid,'%d %d \n',60e3,156+1);

load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');


format=[repmat('%d ',1,156+1) '\n'];


full_training_set=zeros(60e3,156);
   
load arabic_down_sample_training_set
full_training_set(:,1:25)=training_set.^(0.5);

load arabic_projections_training_set
full_training_set(:,26:65)=training_set.^(0.5);

load arabic_num_of_crossings_training_set
full_training_set(:,66:105)=training_set.^(0.5);

load arabic_centroid16_training_set
full_training_set(:,106:121)=training_set.^(0.5);

load arabic_concavity_training_set
full_training_set(:,122:136)=training_set.^(0.5);

load arabic_n_chain_training_set
full_training_set(:,137:156)=training_set.^(0.5);

mu=mean(full_training_set,1);
save mu mu
v= sqrt(var(full_training_set,1));
v(v==0)=1;
save v v
full_training_set=(full_training_set-repmat(mu,60e3,1))./repmat(v,60e3,1);
training_set=full_training_set;

save arabic_low_dim_concat_training_set training_set


fprintf(fid,format,[training_set training_set_labels]');
fclose(fid);

clear
clc

fid= fopen('arabic_low_dim_concat_ourfeatures_latin_testing_set_full.txt','wt');
fprintf(fid,'%d %d \n',10e3,156+1);

load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');


format=[repmat('%d ',1,156+1) '\n'];

full_testing_set=zeros(10e3,156);

load arabic_down_sample_testing_set testing_set
full_testing_set(:,1:25)=testing_set.^(0.5);

load arabic_projections_testing_set testing_set
full_testing_set(:,26:65)=testing_set.^(0.5);

load arabic_num_of_crossings_testing_set testing_set
full_testing_set(:,66:105)=testing_set.^(0.5);

load arabic_centroid16_testing_set testing_set
full_testing_set(:,106:121)=testing_set.^(0.5);

load arabic_concavity_testing_set testing_set
full_testing_set(:,122:136)=testing_set.^(0.5);

load arabic_n_chain_testing_set testing_set
full_testing_set(:,137:156)=testing_set.^(0.5);

load mu mu
load v v
full_testing_set=(full_testing_set-repmat(mu,10e3,1))./repmat(v,10e3,1);

testing_set=full_testing_set;

save arabic_low_dim_concat_testing_set testing_set


fprintf(fid,format,[testing_set testing_set_labels]');

fclose(fid);





