clear
clc

load testing_labels testing_labels
load arabic_kirsh4_testing_set testing_set

load MU_ MU_
load SIGMA_ SIGMA_

testing_set(testing_set<0)=0;
testing_set=testing_set.^(0.5);

% Z=cell(10,10);
% for a=0:8
%     for b=a+1:9
%         Z{a+1,b+1}=inv(0.5*(SIGMA_{a+1}+SIGMA_{b+1}));
%     end
% end

Z=cell(10,10);
s=1e-5;
for a=0:8
    for b=a+1:9
        Z{a+1,b+1}=inv(2*s*eye(size(SIGMA_{1}))+(1-s)*(SIGMA_{a+1}+SIGMA_{b+1}));
    end
end



accuracy=0;
for n=1:10e3
    if(mod(n,100)==0)
        n
    end
    x= testing_set(n,:)';
    d= testing_labels(n);
    
    votes=zeros(10,1);
    for a=0:8
        for b=a+1:9
            decision=  (Z{a+1,b+1}*(MU_{a+1}-MU_{b+1})')'*x-0.5*(MU_{a+1}+MU_{b+1})*Z{a+1,b+1}*(MU_{a+1}-MU_{b+1})';
            if(decision>0)
                votes(a+1)=votes(a+1)+1;
            else
                votes(b+1)=votes(b+1)+1;
            end
        end
    end


    [v ind]= sort(votes,'descend');
    results(n,:)=ind-1;

    if(ind(1)-1==d)
        accuracy=accuracy+1;
    end
end
accuracy=accuracy/10e3*100




