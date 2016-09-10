clear
clc

N=6;
classifiers_names= cell(6,1);
classifiers_names{1}= 'Linear';
classifiers_names{2}= 'OVO_Linear';
classifiers_names{3}= 'OVO_SVM_Linear';
classifiers_names{4}= 'ANN';
classifiers_names{5}= 'PCA+ANN';
classifiers_names{6}= 'PCA+Quad';

classifiers_recog_time= [1 5 5 16 10 7];
classifiers_RR= [13 10 8 5 4 3]/100;

front_classifier_recog_time= 99;
complexities_of_all_configurations=zeros(1,N);
for i=0:N-1
    complexities_of_all_configurations(i+1)= classifiers_recog_time(i+1)+classifiers_RR(i+1)*front_classifier_recog_time;    
end

[B IX]= sort(complexities_of_all_configurations);
classifiers_names(IX)
B


