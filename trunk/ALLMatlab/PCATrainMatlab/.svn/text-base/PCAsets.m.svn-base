function y=PCAsets(training_set,testing_set,FeatName ,mode)

[trainCounts,testCount]=getMaxSampleCount();

warning off

if (mode==0||mode==1)
%load arabic_reduced_local_concavity_training_set
training_set(training_set<0)=0;
training_set= training_set.^(0.5);

%clear full_training_set

mu= mean(training_set,1);
training_set= training_set - repmat(mu,trainCounts,1);
save mu mu


N=25;
disp('applying PCA...')
[PCcoeff, PCvec] = pca(training_set);
save PCvec PCvec


PCA_training_set= training_set*PCvec;

save(['PCA_' FeatName '_training_set'], 'PCA_training_set');

%save arabic_PCA_reduced_local_concavity_training_set PCA_training_set

end 
%clear training_set
%clear PCcoeff
 warning off

if (mode==0||mode==2)
%N=25;

%load arabic_reduced_local_concavity_testing_set testing_set


%load mu mu
%load PCvec PCvec

testing_set(testing_set<0)=0;
testing_set=testing_set.^(0.5);

testing_set= (testing_set-repmat(mu,testCount,1)); %./repmat(vars,10e3,1);
PCA_testing_set= (testing_set*PCvec);


%save arabic_PCA_reduced_local_concavity_testing_set PCA_testing_set

save(['PCA_' FeatName 'testing_set'], 'PCA_testing_set');
end 
warning on

