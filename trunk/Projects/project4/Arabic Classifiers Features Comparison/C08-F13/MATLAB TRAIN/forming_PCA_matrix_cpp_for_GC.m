clc
clear

load PCvec PCvec


[n_features N]=size(PCvec);

fid= fopen('arabic_wavelet_raw_pca_matrix_for_GC.txt','wt');
format=[repmat('%d ',1,N) '\n'];
fprintf(fid,format,PCvec');  

fclose(fid);

load mu mu
fid= fopen('arabic_wavelet_raw_pca_mu_for_GC.txt','wt');
format=repmat('%d ',1,n_features);
fprintf(fid,format,mu');

fclose(fid);
