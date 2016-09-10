clc
clear

load PCvec PCvec

N=50;

fid= fopen('zc_arabic_gmask_gradient_pca_matrix.txt','wt');
format=[repmat('%d ',1,N) '\n'];
fprintf(fid,format,PCvec');  

fclose(fid);

load mu mu
fid= fopen('zc_arabic_gmask_gradient_pca_mu.txt','wt');
format=repmat('%d ',1,201);
fprintf(fid,format,mu');

fclose(fid);
