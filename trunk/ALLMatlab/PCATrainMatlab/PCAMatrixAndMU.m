function y=PCAMatrixAndMU(FeatName)
 
 
load PCvec PCvec

N=50;

fid= fopen(['pca_' FeatName '_pca_matrix.txt'],'wt');
format=[repmat('%d ',1,N) '\n'];
fprintf(fid,format,PCvec');  

fclose(fid);

load mu mu
fid= fopen(['pca_' FeatName '_pca_mu.txt'],'wt');
format=repmat('%d ',1,length(mu));
fprintf(fid,format,mu');

fclose(fid);
