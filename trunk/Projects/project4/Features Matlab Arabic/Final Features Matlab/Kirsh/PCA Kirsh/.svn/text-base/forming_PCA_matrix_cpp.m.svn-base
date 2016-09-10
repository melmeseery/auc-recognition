clc
clear

load PCvec PCvec

N=50;

fid= fopen('arabic_kirsh_pca_matrix.txt','wt');
format=[repmat('%d ',1,N) '\n'];
fprintf(fid,format,PCvec');  

fclose(fid);

load mu mu
fid= fopen('arabic_kirsh_pca_mu.txt','wt');
format=repmat('%d ',1,length(mu));
fprintf(fid,format,mu');

fclose(fid);
