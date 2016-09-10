clear
%clc

N=6;
classifiers_names= cell(6,1);
classifiers_names{1}= 'OVO_Linear';
classifiers_names{2}= 'Linear';
classifiers_names{3}= 'OVO_SVM_Linear';
classifiers_names{6}= 'PCA+Quad';
classifiers_names{5}= 'PCA+ANN';
classifiers_names{4}= 'ANN';

classifiers_recog_time= [5 1 5 9 10 46];
classifiers_RR= [43 41 27 7 5 4]/100;

front_classifier_recog_time= 99;
complexities_of_all_configurations=zeros(1,N);
for i=0:N-1
    complexities_of_all_configurations(i+1)= classifiers_recog_time(i+1)+classifiers_RR(i+1)*front_classifier_recog_time;    
end

[B IX]= sort(complexities_of_all_configurations);
classifiers_names(IX)
B


