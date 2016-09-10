clear
clc

fid= fopen('RejectionsDiff_vs_NumOfViolations.txt','r');

format=['%d %d/n'];
A=[];
while(1)
    a=fscanf(fid,format)';
    if(isempty(a))
        break
    end
    A=[A; a];
end

scatter(A(:,1)/10e3,A(:,2),'o')
xlabel('Rejection Rate Difference')
ylabel('Number of Violations')