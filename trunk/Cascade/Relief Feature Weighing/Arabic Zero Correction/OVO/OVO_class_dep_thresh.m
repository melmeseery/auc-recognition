clear
clc

fid2=fopen('C:\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);


load Scc Scc
OVO_selected_features=cell(10,10);

fid= fopen('OVO_number_of_features_selected.txt','wt');
fid2= fopen('OVO_selected_features.txt','wt');


%th=0.078;
for a=0:8
    for b=a+1:9

%         [rows columns]= find(Scc{a+1,b+1}/1000>th);
%         OVO_selected_features{a+1,b+1}= columns;
%         format = '%d \n';       
%         fprintf(fid,format,length(columns));
% 
%         [V IX]= sort(Scc{a+1,b+1},'descend');
%         OVO_selected_features{a+1,b+1}=IX;%(1:100);
%         format = '%d \n';
%         fprintf(fid,format,200);

        [V IX]= sort(Scc{a+1,b+1},'descend');
        OVO_selected_features{a+1,b+1}=IX;
        format = '%d \n';
        fprintf(fid,format,201);
        format = [repmat('%d ',1,201) '\n'];
        fprintf(fid2,format,IX);

    end
end

fclose(fid);
fclose(fid2);

save OVO_selected_features OVO_selected_features

% count=0;
% for a=0:8
%     for b=a+1:9
%         count=count+length(OVO_selected_features{a+1,b+1});
%     end
% end
% 
% count



