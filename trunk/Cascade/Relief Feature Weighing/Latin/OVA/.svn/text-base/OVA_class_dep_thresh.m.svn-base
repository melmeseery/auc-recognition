clear
clc

fid2=fopen('C:\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);


load Sc Sc
OVA_selected_features=cell(10,1);

fid= fopen('OVA_number_of_features_selected.txt','wt');
fid2= fopen('OVA_selected_features.txt','wt');


th=0.078;
for a=0:9


    %         [rows columns]= find(Scc{a+1,b+1}/1000>th);
    %         OVO_selected_features{a+1,b+1}= columns;
    %         format = '%d \n';
    %         fprintf(fid,format,length(columns));
    %
    %         [V IX]= sort(Scc{a+1,b+1},'descend');
    %         OVO_selected_features{a+1,b+1}=IX;%(1:100);
    %         format = '%d \n';
    %         fprintf(fid,format,200);

    [V IX]= sort(Sc(a+1,:),'descend');
    OVA_selected_features{a+1}=IX;
    format = '%d \n';
    fprintf(fid,format,200);
    format = [repmat('%d ',1,200) '\n'];
    fprintf(fid2,format,IX);

end

fclose(fid);
fclose(fid2);

save OVA_selected_features OVA_selected_features

% count=0;
% for a=0:8
%     for b=a+1:9
%         count=count+length(OVO_selected_features{a+1,b+1});
%     end
% end
% 
% count



