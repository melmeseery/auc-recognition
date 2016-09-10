clear
clc
warning off

load training_labels training_labels

N =200;
load arabic_grad_proj_training_set training_set

training_set(training_set<0)=0;
training_set=(training_set).^(0.5);
temp= training_set;
training_set=temp(1:50e3,:);
validation_set=temp(50e3+1:end,:);
clear full_training_set

temp=training_labels;
training_labels=temp(1:50e3);
validation_labels=temp(50e3+1:end);
clear temp;

mu= mean(training_set,1);
training_set= training_set - repmat(mu,50e3,1);
validation_set = validation_set - repmat(mu,10e3,1);

save mu mu

Optimizing_of_P_results=[];
Ps=[];
for P=N:-0.1*N:5
    P

    disp('applying PCA...')
    [PCcoeff, PCvec] = pca(training_set,ceil(P));
    training_set=training_set*PCvec;
    validation_set=validation_set*PCvec;

    MU_ = cell(10,1);
    SIGMA_ = cell(10,1);

    for i=0:9
        set_= training_set(training_labels==i,:);
        MU_{i+1} = mean(set_,1);
        SIGMA_{i+1}= (set_ - repmat(MU_{i+1},size(set_,1),1))'*(set_ - repmat(MU_{i+1},size(set_,1),1))/size(set_,1);
    end

    accuracy=0;
    for n=1:10e3
        x= validation_set(n,:);
        d= validation_labels(n);

        decision_scores=zeros(10,1);
        for i=0:9
            decision_scores(i+1)= -0.5*log(det(SIGMA_{i+1}))-0.5*(x-MU_{i+1})*inv(SIGMA_{i+1})*(x-MU_{i+1})';
        end
        [v ind]= max(decision_scores);
        if(ind-1==d)
            accuracy=accuracy+1;
        end
    end
    accuracy=accuracy/10e3*100
    Optimizing_of_P_results=[Optimizing_of_P_results accuracy];
    Ps=[Ps P];

end

Ps=ceil(Ps);
Ps
Optimizing_of_P_results

save Ps Ps
save Optimizing_of_P_results Optimizing_of_P_results

warning on
