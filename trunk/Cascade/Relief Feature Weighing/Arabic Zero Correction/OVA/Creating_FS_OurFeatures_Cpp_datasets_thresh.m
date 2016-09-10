clear
clc


load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
training_labels=training_set_labels;

load OVA_selected_features OVA_selected_features


% Pairwise Training Sets

for a=0:9
    a
    current_set=[];
    current_binary_labels=[];
    selection= OVA_selected_features{a+1};
    
    load arabic_ZC_grad_proj_training_set
    training_set=training_set.^0.5;


    set=training_set(:,selection);
    binary_labels=training_labels;
    binary_labels(training_labels==a)=1;
    binary_labels(training_labels~=a)=-1;
    current_set=[current_set; set];
    current_binary_labels=[current_binary_labels; binary_labels];

    fid= fopen(['arabic_ZC_ova_ourfeatures_training_set_fs_digit_' int2str(a) '.txt'],'wt');
    fprintf(fid,'%d %d \n',size(current_set,1),length(selection)+1);
    format=[repmat('%d ',1,length(selection)+1) '\n'];
    fprintf(fid,format,[current_set current_binary_labels]');
    fclose(fid);


end



% clear
% clc
%
% load OVO_selected_features OVO_selected_features
%
%
% fid2=fopen('C:\MNIST\t10k-labels.idx1-ubyte','r');
% B=fread(fid2);
% testing_labels=B(9:end);
%
% load grad_proj_testing_set testing_set
% testing_set=testing_set.^0.5;
%
% for a=0:8
%     for b=a+1:9
%
%         selection= OVO_selected_features{a+1,b+1};
%         current_set= testing_set(:,selection);
%
%
%         fid= fopen(['ourfeatures_latin_testing_set_fs_' int2str(a) 'vs' int2str(b) '.txt'],'wt');
%         fprintf(fid,'%d %d \n',10e3,length(selection)+1);
%         format=[repmat('%d ',1,length(selection)+1) '\n'];
%         fprintf(fid,format,[current_set testing_labels]');
%
%     end
% end
%
%
%