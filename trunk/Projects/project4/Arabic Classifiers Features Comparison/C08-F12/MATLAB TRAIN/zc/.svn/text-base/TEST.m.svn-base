clear
clc

load testing_labels testing_labels

load arabic_low_dim_concat_testing_set testing_set
set=testing_set;
load arabic_bbox_area_testing_set testing_set
load m m
load v v
testing_set= (testing_set-m)/v;
testing_set=[set testing_set];

load MU_ MU_
load SIGMA_ SIGMA_

load mu mu
load PCvec PCvec


detSIGMA=cell(10,1);
invSIGMA=cell(10,1);
for i=1:10
    detSIGMA{i}=det(SIGMA_{i});
    invSIGMA{i}=inv(SIGMA_{i});
end

testing_set=(testing_set-repmat(mu,10e3,1))*PCvec;

fid= fopen('C08-F04_full_results.txt','wt');
format=[repmat('%d ',1,10) '\n'];

results=zeros(10e3,10);
accuracy=0;
for n=1:10e3
    x= testing_set(n,:);
    d= testing_labels(n);

    decision_scores=zeros(10,1);
    for i=0:9
        decision_scores(i+1)= -0.5*log(detSIGMA{i+1})-0.5*(x-MU_{i+1})*invSIGMA{i+1}*(x-MU_{i+1})';
    end
    [v ind]= sort(decision_scores,'descend');
    results(n,:)=ind-1;

    if(ind(1)-1==d)
        accuracy=accuracy+1;
    end
end

%fprintf(fid,format,results');

accuracy=accuracy/10e3*100

fclose(fid)



