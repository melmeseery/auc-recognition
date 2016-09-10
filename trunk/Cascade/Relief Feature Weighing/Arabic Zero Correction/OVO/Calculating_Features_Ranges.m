clear
clc


for k=1:1
    k
    load(['training_set_part_' int2str(k)]);
    max_features_values= max(training_set,[],1); 
end

for k=1:6
    k
    load(['training_set_part_' int2str(k)]);
    training_set= training_set./repmat(max_features_values,10e3,1);
    training_set(isnan(training_set))=0;
    training_set(training_set==inf)=1;
    save(['training_set_part_' int2str(k)],'training_set');
end


load testing_set
testing_set= testing_set./repmat(max_features_values,10e3,1);
testing_set(isnan(testing_set))=0;
testing_set(testing_set==inf)=1;
save testing_set testing_set




