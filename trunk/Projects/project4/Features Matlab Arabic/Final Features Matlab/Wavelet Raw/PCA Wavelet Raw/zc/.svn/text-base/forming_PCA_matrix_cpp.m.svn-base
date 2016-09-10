clc
clear

load PCvec PCvec

N=50;

fid= fopen('zc_arabic_wavelet_raw_pca_matrix.txt','wt');
format=[repmat('%d ',1,N) '\n'];
fprintf(fid,format,PCvec');  

fclose(fid);

load mu mu
fid= fopen('zc_arabic_wavelet_raw_pca_mu.txt','wt');
format=repmat('%d ',1,length(mu));
fprintf(fid,format,mu');

fclose(fid);
