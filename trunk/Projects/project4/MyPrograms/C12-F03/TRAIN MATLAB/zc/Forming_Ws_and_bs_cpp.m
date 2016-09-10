clc
clear

load MU_ MU_
load SIGMA_ SIGMA_


Z=cell(10,10);
s=1e-5;
for a=0:8
    for b=a+1:9
        Z{a+1,b+1}=inv(2*s*eye(size(SIGMA_{1}))+(1-s)*(SIGMA_{a+1}+SIGMA_{b+1}));
    end
end

N=length(MU_);

fid_w= fopen('zc_Ws.txt','wt');
fid_b= fopen('zc_bs.txt','wt');
format_w=[repmat('%d ',1,N) '\n'];
format_b=['%d\n'];
for a=0:8
    for b=a+1:9
        W= Z{a+1,b+1}*(MU_{a+1}-MU_{b+1})';
        b= -0.5*(MU_{a+1}+MU_{b+1})*Z{a+1,b+1}*(MU_{a+1}-MU_{b+1})';
        fprintf(fid_w,format_w,W');
        fprintf(fid_b,format_b,b);
    end
end



fclose(fid_w)
fclose(fid_b)













