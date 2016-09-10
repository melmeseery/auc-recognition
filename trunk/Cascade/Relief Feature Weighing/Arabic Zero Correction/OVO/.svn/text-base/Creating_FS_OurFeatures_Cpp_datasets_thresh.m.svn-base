% clear
% clc
% 
% 
% load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');
% training_labels= training_set_labels;
% 
% load OVO_selected_features OVO_selected_features
% 
% 
% % Pairwise Training Sets
% load arabic_ZC_grad_proj_training_set training_set
% training_set= training_set.^0.5;
% for a=0:8
%     for b=a+1:9
%         selection= OVO_selected_features{a+1,b+1};
%         
%         current_set=training_set(find(or(training_labels==a,training_labels==b)),selection);
%         current_binary_labels=training_labels;
%         current_binary_labels(training_labels==a)=1;
%         current_binary_labels(training_labels==b)=-1;
%         current_binary_labels(~or(training_labels==a,training_labels==b))=[];
% 
%         fid= fopen(['arabic_ZC_ourfeatures_training_set_fs_' int2str(a) 'vs' int2str(b) '.txt'],'wt');
%         fprintf(fid,'%d %d \n',size(current_set,1),length(selection)+1);
%         format=[repmat('%d ',1,length(selection)+1) '\n'];
%         fprintf(fid,format,[current_set current_binary_labels]');  
%         fclose(fid);
%         
%     end
% end



clear
clc

load OVO_selected_features OVO_selected_features


load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');
testing_labels= testing_set_labels;

load arabic_ZC_grad_proj_testing_set testing_set

testing_set=testing_set.^0.5;

for a=0:8
    for b=a+1:9

        selection= OVO_selected_features{a+1,b+1};
        current_set= testing_set(:,selection);
        
       
        fid= fopen(['arabic_ZC_ourfeatures_testing_set_fs_' int2str(a) 'vs' int2str(b) '.txt'],'wt');
        fprintf(fid,'%d %d \n',10e3,length(selection)+1);
        format=[repmat('%d ',1,length(selection)+1) '\n'];
        fprintf(fid,format,[current_set testing_labels]');
        fclose(fid);

    end
end



