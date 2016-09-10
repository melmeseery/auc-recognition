% num=[5923 6742 5958 6131 5842 5421 5918 6265 5851 5949 ];
% for k=1:num(1)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\0\',file);
% f{1}{k}= h_image(filepath);
% end
% for k=1:num(2)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\1\',file);
% f{2}{k}=[ h_image(filepath)];
% end
% for k=1:num(3)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\2\',file);
% f{3}{k}=[ h_image(filepath)];
% end
% for k=1:num(4)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\3\',file);
% f{4}{k}=[ h_image(filepath)];
% end
% for k=1:num(5)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\4\',file);
% f{5}{k}=[ h_image(filepath)];
% end
% for k=1:num(6)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\5\',file);
% f{6}{k}=[ h_image(filepath)];
% end
% for k=1:num(7)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\6\',file);
% f{7}{k}=[ h_image(filepath)];
% end
% for k=1:num(8)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\7\',file);
% f{8}{k}=[ h_image(filepath)];
% end
% for k=1:num(9)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\8\',file);
% f{9}{k}=[ h_image(filepath)];
% end
% for k=1:num(10)
%         
% str_k=num2str(k);
% file=strcat(str_k,'.jpg');
% filepath=strcat('D:\D\ocr\number_0fdb\trainning','\9\',file);
% f{10}{k}=[ h_image(filepath)];
% end
% data=f;
%  clc
%  clear all
%  load data
%  load data_Y

%%% computer features and concatenate all data into single matrix
% R = length(data);               % Number of words to be recognized.
% Y = [];
% for (i = 1:R)  % for each category 
%     i
%   L = length(data{i});                % Number of occurrences of i'th word.
%   for (j = 1:L)
%     s = data{i}{j};              % Get file name from data structure.
%     y = hmmfeatures(s);  % Extract feature vectors.
%     Y = [Y y];                        % Concatenate vectors for all sequences.
%   end
% end
%



%   [cb,K,T] = hmmcodebook(Y,4);  do not use this 

clc 
clear 
%load maha 
 %   [A_m,B_m,pi_m,loglike_m] = hmmtrain(data,8,5000,.00000001,cb); % 4: states 16 : observations

%test
num=[980  1135 1032 1010 982 892  958  1028  922  1009  ];
for k=1:num(1)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\0\',file);
f{1}{k}= h_image(filepath);
end
for k=1:num(2)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\1\',file);
f{2}{k}=[ h_image(filepath)];
end
for k=1:num(3)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\2\',file);
f{3}{k}=[ h_image(filepath)];
end
for k=1:num(4)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\3\',file);
f{4}{k}=[ h_image(filepath)];
end
for k=1:num(5)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\4\',file);
f{5}{k}=[ h_image(filepath)];
end
for k=1:num(6)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\5\',file);
f{6}{k}=[ h_image(filepath)];
end
for k=1:num(7)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\6\',file);
f{7}{k}=[ h_image(filepath)];
end
for k=1:num(8)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\7\',file);
f{8}{k}=[ h_image(filepath)];
end
for k=1:num(9)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\8\',file);
f{9}{k}=[ h_image(filepath)];
end
for k=1:num(10)
        
str_k=num2str(k);
file=strcat(str_k,'.jpg');
filepath=strcat('D:\AUC\tasks\HMM\test','\9\',file);
f{10}{k}=[ h_image(filepath)];
end
datatest=f;
break; 
clc 
clear 
load datatest
load maha_model
for i= 1 :10

   [logp,guess{i}] = hmmrecog(datatest{i},A_m,B_m,pi_m,cb);
i
correct=   length(find(guess{i}==i))
result{i}=correct/length(datatest{i})
%correctAll=correct+correctAll
end 



% d1=[];d2=d1;d3=d1;d4=d1;d5=d1;d6=d1;d7=d1;d8=d1;
% for i=1:(60000)
%  d1=[d1 Y(:,(i-1)*8+1)];
%  d2=[d2 Y(:,8*(i-1)+2)];
%  d3=[d3 Y(:,8*(i-1)+3)];
%  d4=[d4 Y(:,8*(i-1)+4)];
%  d5=[d5 Y(:,8*(i-1)+5)];
%  d6=[d6 Y(:,8*(i-1)+6)];
%  d7=[d7 Y(:,8*(i-1)+7)];
%  d8=[d8 Y(:,8*(i-1)+8)];
% end
