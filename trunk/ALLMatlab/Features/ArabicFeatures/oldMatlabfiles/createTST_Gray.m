clear
clc
A=uint8(zeros(10000*28,28));l=1;%th=100;
for i=0:9  %digit
    s='D:\prof\NEW\Features\MATLAB\ShowingGradient_Arabic\MAHDBase_TestingSet\part';
    for j=1:2 %part
        if (j<10)sj=['0' int2str(j)];else sj=int2str(j);end
        s1=[s sj '\'];
        for k=1:50 %writer
            writer=(j+11)*50+k;
            if (writer>=100)sw=['writer' int2str(writer)];
            elseif (writer>=10)sw=['writer0' int2str(writer)];
            else sw=['writer00' int2str(writer)];
            end
            s2=[s1 sw '_'];
            for p=1:10 %pass
                if (p<10)s3=[s2 'pass0' int2str(p) '_digit' int2str(i) '.bmp'];
                else s3=[s2 'pass' int2str(p) '_digit' int2str(i) '.bmp'];
                end
                a=imread(s3);a=255-double(a);a=uint8(a);%a=uint8(a>th);
                A(l:l+27,1:28)=a;
                l=l+28;
            end    
        end    
    end
end    
save 'D:\prof\MyPrograms\Arabic\Testing_Set\TestingData_Gray' A
