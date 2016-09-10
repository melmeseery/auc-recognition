clear
clc

load training_set
[trainCounts,testCount]=getMaxSampleCount()

mu=mean(training_set,2);
save mu mu
load mu mu
for i=1:trainCounts
    training_set(:,i)=training_set(:,i)- mu;
end


disp('applying PCA...')
[PCcoeff, PCvec] = pca(training_set(:,1:testCount)',40);
save PCvec PCvec

load mu mu
load PCvec PCvec

fid= fopen('mahdbase_pca_matrix.txt','wt');
format=[repmat('%d ',1,40) '\n'];
fprintf(fid,format,PCvec');
fclose(fid)

fid= fopen('mahdbase_pca_mu.txt','wt');
format=[repmat('%d ',1,40) '\n'];
fprintf(fid,format,mu);
fclose(fid)

