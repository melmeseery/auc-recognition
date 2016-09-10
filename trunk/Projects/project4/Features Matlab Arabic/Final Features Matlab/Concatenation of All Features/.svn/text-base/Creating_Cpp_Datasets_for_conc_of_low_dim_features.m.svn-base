clear
clc

fid= fopen('arabic_all_concat_ourfeatures_latin_training_set_full.txt','wt');
fprintf(fid,'%d %d \n',60e3,945+1);

load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');


format=[repmat('%d ',1,945+1) '\n'];

mu_all=[];
v_all=[];
full_training_set=zeros(60e3,945);

load arabic_grad_proj_training_set
training_set(training_set<0)=0;
full_training_set(:,1:200)=training_set.^(0.5);

load arabic_kirsh4_training_set
training_set(training_set<0)=0;
full_training_set(:,201:300)=training_set.^(0.5);

load arabic_reduced_local_concavity_training_set
training_set(training_set<0)=0;
full_training_set(:,301:525)=training_set.^(0.5);

load arabic_wavelet_raw_training_set
training_set(training_set<0)=0;
full_training_set(:,526:589)=training_set.^(0.5);

load arabic_local_chain_training_set
training_set(training_set<0)=0;
full_training_set(:,590:789)=training_set.^(0.5);


load arabic_low_dim_concat_training_set training_set
full_training_set(:,790:end)=training_set;
clear training set;

mu=mean(full_training_set,1);
save mu mu
v=[];
for n=1:9
    v=[v sqrt(var(full_training_set(:,(n-1)*100+1:n*100),1))];
end
v=[v sqrt(var(full_training_set(:,901:end),1))];
%v= sqrt(var(full_training_set,1));
v(v==0)=1;
save v v

for n=1:9
    full_training_set(:,(n-1)*100+1:n*100)=...
        (full_training_set(:,(n-1)*100+1:n*100)-repmat(mu(:,(n-1)*100+1:n*100),60e3,1))./repmat(v(:,(n-1)*100+1:n*100),60e3,1);
end
full_training_set(:,901:end)=...
    (full_training_set(:,901:end)-repmat(mu(:,901:end),60e3,1))./repmat(v(:,901:end),60e3,1);
training_set=full_training_set;


save arabic_all_concat_training_set training_set

for k=1:6
    fprintf(fid,format,[training_set((k-1)*10e3+1:k*10e3,:) training_set_labels((k-1)*10e3+1:k*10e3)]');
end

fclose(fid);


clear
clc

fid= fopen('arabic_all_concat_ourfeatures_latin_testing_set_full.txt','wt');
fprintf(fid,'%d %d \n',10e3,945+1);

load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');

format=[repmat('%d ',1,945+1) '\n'];

full_testing_set=zeros(10e3,945);


load arabic_grad_proj_testing_set testing_set
testing_set(testing_set<0)=0;
full_testing_set(:,1:200)=testing_set.^(0.5);

load arabic_kirsh4_testing_set testing_set
training_set(testing_set<0)=0;
full_testing_set(:,201:300)=testing_set.^(0.5);


load arabic_reduced_local_concavity_testing_set testing_set
testing_set(testing_set<0)=0;
full_testing_set(:,301:525)=testing_set.^(0.5);

load arabic_wavelet_raw_testing_set testing_set
testing_set(testing_set<0)=0;
full_testing_set(:,526:589)=testing_set.^(0.5);

load arabic_local_chain_testing_set testing_set
testing_set(testing_set<0)=0;
full_testing_set(:,590:789)=testing_set.^(0.5);

load arabic_low_dim_concat_testing_set testing_set
full_testing_set(:,790:end)=testing_set;

load mu mu
load v v
full_testing_set=(full_testing_set-repmat(mu,10e3,1))./repmat(v,10e3,1);
testing_set=full_testing_set;

save arabic_all_concat_testing_set testing_set

fprintf(fid,format,[testing_set testing_set_labels]');
fclose(fid);



