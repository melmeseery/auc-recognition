clc
clear

load MU_ MU_
load SIGMA_ SIGMA_


N=length(MU_);

fid= fopen('zc_log_det_SIGMA.txt','wt');
format=[repmat('%d ',1,N) '\n'];
for n=1:10
    fprintf(fid,format,-log(det(SIGMA_{n}))');
end

fclose(fid);


fid= fopen('zc_inv_SIGMA.txt','wt');
format=[repmat('%d ',1,N) '\n'];
for n=1:10
    fprintf(fid,format,-inv(SIGMA_{n})');
end

fclose(fid);


load mu mu
fid= fopen('zc_MU.txt','wt');
format=[repmat('%d ',1,N) '\n'];
for n=1:10
    fprintf(fid,format,MU_{n}');
end

fclose(fid);





