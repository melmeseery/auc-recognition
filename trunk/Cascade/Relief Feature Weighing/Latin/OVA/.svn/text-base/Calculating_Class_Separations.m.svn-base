clear
clc

fid2=fopen('C:\MNIST\train-labels.idx1-ubyte','r');
B=fread(fid2);
B=B(9:end);

n_features= 200;

Features_means=cell(10,1);
Features_variances=cell(10,1);


full_training_set=zeros(60e3,n_features);
for k=1:6
    k
    load(['grad_proj_training_set_part_' int2str(k)]);
    full_training_set((k-1)*10e3+1:k*10e3,:)=training_set;

end
clear training_set


disp('calculating Scc ... ')
Scc=cell(10,10);
for a=0:8
    for b=a+1:9
        a
        b
        Scc{a+1,b+1}= zeros(1,200);
        current_set_a= full_training_set(B==a,:);
        current_set_b= full_training_set(B==b,:);
        for n=1:1000
            if(mod(n,100)==0)
                n
            end
            x_a=current_set_a(n,:);
            diff_hit= (repmat(x_a,size(current_set_a,1),1)-current_set_a).^2;
            diff_miss= (repmat(x_a,size(current_set_b,1),1)-current_set_b).^2;
            sum_diff_hit=sum(diff_hit,2); [v ind]= min(sum_diff_hit); sum_diff_hit(ind)=inf;
            sum_diff_miss=sum(diff_miss,2);

            [v nearest_hit_index]=min(sum_diff_hit);
            [v nearest_miss_index]=min(sum_diff_miss);

            Scc{a+1,b+1}= Scc{a+1,b+1}+ diff_miss(nearest_miss_index,:)- diff_hit(nearest_hit_index,:);
        end

        for n=1:1000
            if(mod(n,100)==0)
                n
            end
            x_b=current_set_b(n,:);
            diff_hit= (repmat(x_b,size(current_set_b,1),1)-current_set_b).^2;
            diff_miss= (repmat(x_b,size(current_set_a,1),1)-current_set_a).^2;
            sum_diff_hit=sum(diff_hit,2); [v ind]= min(sum_diff_hit); sum_diff_hit(ind)=inf;
            sum_diff_miss=sum(diff_miss,2);

            [v nearest_hit_index]=min(sum_diff_hit);
            [v nearest_miss_index]=min(sum_diff_miss);

            Scc{a+1,b+1}= Scc{a+1,b+1}+ diff_miss(nearest_miss_index,:)- diff_hit(nearest_hit_index,:);
        end

    end
end
save Scc Scc

clear
load Scc Scc

disp('calculating Sc ... ')
n_features=200;
Sc=zeros(10,n_features);
for a=0:9
    for b=0:9
        if(a==b)
            continue
        end
        Sc(a+1,:)=Sc(a+1,:)+Scc{min([a b])+1,max([a b])+1};
    end
end
save Sc Sc

disp('calculating S ... ')
S=sum(Sc,1);
save S S










