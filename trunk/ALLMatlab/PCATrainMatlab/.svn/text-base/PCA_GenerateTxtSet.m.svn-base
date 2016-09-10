function y= PCA_GenerateTxtSet(FeatName ,mode)
warning off

if (mode==0||mode==1)
[trainLabels,testLabels]= LoadMinistTrainLabels();
 [trainCounts,testCount]=getMaxSampleCount();

load(trainLabels);

load(['PCA_' FeatName '_training_set'], 'PCA_training_set');

N=50;

fid_train= fopen(['pca_' FeatName '_train.txt'],'wt');
fprintf(fid_train,'%d %d \n',60e3,N+1);
format=[repmat('%d ',1,N+1) '\n'];
for n=1:trainCounts
    if(mod(n,100)==0)
        n
    end
    fprintf(fid_train,format,[PCA_training_set(n,:)'; training_set_labels(n)']);  
end
% 

fclose(fid_train);

%clear
%N=50;


end 



if (mode==0||mode==2)
 
 load(testLabels);
%load arabic_PCA_reduced_local_concavity_testing_set PCA_testing_set
load(['PCA_' FeatName 'testing_set'], 'PCA_testing_set');


fid_test= fopen(['pca_' FeatName '_test.txt'],'wt');
fprintf(fid_test,'%d %d \n',testCount,N+1);
format=[repmat('%d ',1,N+1) '\n'];
fprintf(fid_test,format,[PCA_testing_set'; testing_set_labels']);


fclose(fid_test);
end 
warning on

