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

configurations= cell(2^N,1);
complexities_of_all_configurations=zeros(1,2^N);
for i=0:2^N-1
    code=dec2bin(i);
    
    configurations{i+1}=[];
    current_RR=1;
    total_complexity=0;
    for n=1:length(code)      
        if(bin2dec(code(end-n+1)))
            total_complexity= total_complexity + current_RR* classifiers_recog_time(n);
            current_RR= classifiers_RR(n);
            configurations{i+1}=[configurations{i+1} ' ' classifiers_names{n}];
        end       
    end
    
    total_complexity= total_complexity+ current_RR* front_classifier_recog_time;
    complexities_of_all_configurations(i+1)= total_complexity;
    
end

complexities_of_all_configurations


