clear
clc

N=6;
classifiers_names= cell(6,1);
classifiers_names{1}= 'OVO_Linear';
classifiers_names{2}= 'Linear';
classifiers_names{3}= 'OVO_SVM_Linear';
classifiers_names{4}= 'PCA+Quad';
classifiers_names{5}= 'PCA+ANN';
classifiers_names{6}= 'ANN';

classifiers_recog_time= [5 1 5 8 10 46];
classifiers_RR= [43 41 27 7 5 4]/100;

front_classifier_recog_time= 99;

configurations= cell(2^N,1);
complexities_of_all_configurations=zeros(1,2^N);
for i=0:2^N-1
    code=fliplr(dec2bin(i));
    
    configurations{i+1}=[];
    current_RR=1;
    total_complexity=0;
    for n=1:length(code)      
        if(bin2dec(code(n)))
            total_complexity= total_complexity + current_RR* classifiers_recog_time(n);
            current_RR= classifiers_RR(n);
            configurations{i+1}=[configurations{i+1} ' ' classifiers_names{n}];
        end       
    end
    
    total_complexity= total_complexity+ current_RR* front_classifier_recog_time;
    complexities_of_all_configurations(i+1)= total_complexity;
%     configurations
%     input('zeo:')
    
end

complexities_of_all_configurations


