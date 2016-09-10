function [ SVM]=svmeachtwo(set)

for i=1:length(set)
    i
    opts = svmsmoset('Display','final','MaxIter',1500000000,'KernelCacheLimit',50,'TolKKT',.0000001);  
%    
    for k=1:length(set{i})
%       opts = svmsmoset('Display','final','MaxIter',1500000000,'KernelCacheLimit',50,'TolKKT',.0000001);  
%      for k = 1:length(set{i}{j})
      
        for l = k:length(set{i})
        if (k == l)
            continue
        end
        ss1=[];
        ss2=[];
         group=[];
         group1=[];
         group2 = [];
         group1(1:length(set{i}{k}(1,:)))=k;
         group2(1:length(set{i}{l}(1,:)))=l;
         group = [group1' ; group2'];
         ss1=set{i}{k}';
         ss2=set{i}{l}';
         SVM_t = svmtrain([ss1 ; ss2],group,'Kernel_Function','rbf','RBF_Sigma',10,'Method','SMO','SMO_opts',opts);
         SVM{i}(k,l)= SVM_t;
%         'BoxConstraint',100
       
        end  
    end 
    end

return
% counter=5400*ones(1,10);
% counter_all = 0;
opts = svmsmoset('Display','final','MaxIter',1500000000,'KernelCacheLimit',50,'TolKKT',.0000001);
%  for i = 1:10
%      counter_all(i+1)= (counter_all(i)+counter(i));
%  end
%SVM = SVM_0;
counter=[5923 6742 5958 6131 5842 5421 5918 6265 5851 5949 ];
counter_all=[0 sum(counter(1,1:1)) sum(counter(1,1:2)) sum(counter(1,1:3)) sum(counter(1,1:4)) sum(counter(1,1:5)) sum(counter(1,1:6)) sum(counter(1,1:7))...
    sum(counter(1,1:8)) sum(counter(1,1:9)) sum(counter(1,1:10)) ];

for k = 1:10
    k
    for j = k:10
        if (k == j)
            continue
        end
        j
        group1=[];
        group2 = [];
        group1(1:counter(k))=k-1;
        group2(1:counter(j))=j-1;
        group = [group1' ; group2'];
          SVM_t = svmtrain([f(counter_all(k)+1:counter_all(k+1),:) ; f(counter_all(j)+1:counter_all(j+1),:)],group,'Kernel_Function','rbf',...
         'RBF_Sigma',10,...
          'Method','SMO','SMO_opts',opts,'BoxConstraint',100) ;
     
%         SVM(k,j)= SVM_t;
% %          SVM_tt = svmtrain([f(counter_all(k)+1:counter_all(k+1),:) ; f(counter_all(j)+1:counter_all(j+1),:)],group,'Method','SMO','SMO_opts',opts,'Kernel_Function','polynomial','Polyorder',5,'autoscale','true');   
%  SVM_t = svmtrain([f(counter_all(k)+1:counter_all(k+1),:) ; f(counter_all(j)+1:counter_all(j+1),:)],group,'Method','SMO','SMO_opts',opts,'Kernel_Function','polynomial','Polyorder',3,'autoscale','true');       
SVM(k,j)= SVM_t;
      
    
%     SVM(k,j)= SVM_t;
    end
end



return

%  SVM_t = svmtrain([f(counter_all(k)+1:counter_all(k+1),:) ; f(counter_all(j)+1:counter_all(j+1),:)],group,'Method','SMO','SMO_opts',opts,'Kernel_Function','polynomial','Polyorder',3,'autoscale','true');




ft=ft';
% load MEANS5        %loading the 5120 centroids   
% load svmh2-p %loading the matrix of the SVM trainning data
% Confmat=[];
% accuracy=[];
% Number=[];
% output=[];
mm=[];
num=[980 1135 1032 1010 982 892 958 1028 974 1009 ];
xx0=[];
xx1=xx0;xx2=xx0;xx3=xx0;xx4=xx0;xx5=xx0;xx6=xx0;xx7=xx0;xx8=xx0;xx9=xx0;
for j=1:1           %looping on the 10 classes (10 digits)
%     c_0=0;
%     c_1=0;
%     c_2=0;
%     c_3=0;
%     c_4=0;
%     c_5=0;
%     c_6=0;
%     c_7=0;
%     c_8=0;
%     c_9=0;

  for k=1:10000 % looping on the 800 sample of each class (digit)
   
      if mod(k,100)==0
          k
      end
%     i=0;
%     for i=1:5120
%         distance_test(i)=sqrt(sum((centroids(i,:)-ft(k,:)).^2));
%     end
%     % getting the distances between each feature and the 5120 centroid
% [t u]=min(distance_test);     %getting the minimum distance
% Number(k,1)=floor((u-1)/(512)); %getting the corresponding class(digit) to this distance
% distance_test(u)=1000;
% 
% [t u]=min(distance_test);
% Number(k,2)=floor((u-1)/(512));
% distance_test(u)=1000;
% 
% [t u]=min(distance_test);
% Number(k,3)=floor((u-1)/(512));
% distance_test(u)=1000;
%     %getting the least 3 distances and the corresponding 3 classes (digits)
% A=Number(k,1);
% B=Number(k,2);
% C=Number(k,3);

% if ((A==B) && (A==C))
%     OK=A;
% elseif (A==C)
%     OK=svmclassify(SVM(A+1,B+1),ft(k,:));
%     
% elseif (B==C)
%     OK=svmclassify(SVM(A+1,B+1),ft(k,:));
%     
% elseif (A==B)
%     OK=svmclassify(SVM(A+1,C+1),ft(k,:));
    % if there is 2 equal numbers and the third is different use the SVM classifier to
    % get the the true number
% else
D=[];

for kk = 1:10
    for jj = kk:10
        if (kk == jj)
            continue
        end
        D=[D svmclassify(SVM(kk,jj),ft(k,:))];
    end
end
%   xx0=[xx0 length(find(D==0))] ;
%   xx1=[xx1 length(find(D==1))] ;
%   xx2=[xx2 length(find(D==2))] ;
%   xx3=[xx3 length(find(D==3))] ;
%   xx4=[xx4 length(find(D==4))] ;
%   xx5=[xx5 length(find(D==5))] ;
%   xx6=[xx6 length(find(D==6))] ;
%   xx7=[xx7 length(find(D==7))] ;
%   xx8=[xx8 length(find(D==8))] ;
%   xx9=[xx9 length(find(D==9))] ;
  x0=[ length(find(D==0))] ;
  x1=[ length(find(D==1))] ;
  x2=[ length(find(D==2))] ;
  x3=[ length(find(D==3))] ;
  x4=[ length(find(D==4))] ;
  x5=[ length(find(D==5))] ;
  x6=[ length(find(D==6))] ;
  x7=[ length(find(D==7))] ;
  x8=[ length(find(D==8))] ;
  x9=[ length(find(D==9))] ;
    hn=[x0 ;x1 ;x2; x3 ;x4 ;x5 ;x6 ;x7 ;x8 ;x9];
     
    [rr cc]=find(hn==max(hn));
     hn(rr,cc)=0;
     [rn cn]=find(hn==max(hn));
     tr=[rr ;rn];
     trr=sort(tr);
     if length(tr)==2
      mm=[mm tr(1)-1];
     else
         
      A=svmclassify(SVM(trr(1),trr(2)),ft(k,:));
%       B=svmclassify(SVM(trr(2),trr(3)),ft(k,:));
%       C=svmclassify(SVM(trr(1),trr(3)),ft(k,:));
%       if A==B
%          mm=[mm trr(2)-1];
%      
%      elseif B==C
%          mm=[mm trr(3)-1];
%      
%      elseif A==C
%          mm=[mm trr(1)-1];
%      else
%          mm=[mm tr(1)-1];
%      end
%      end
mm=[mm A];
     end
     
%       if length(rr)==2
%           
%           
%           mm=[mm svmclassify(SVM(rr(1),rr(2)),ft(k,:))];
%       end
%      if length(rr)==1  
%          mm=[mm rr-1];
%      end
%      
%      if length(rr)==3
%          
%      A=svmclassify(SVM(rr(1),rr(2)),ft(k,:));
%      B=svmclassify(SVM(rr(2),rr(3)),ft(k,:));
%      C=svmclassify(SVM(rr(1),rr(3)),ft(k,:));
%      
%      if A==B
%          mm=[mm rr(2)-1];
%      
%      elseif B==C
%          mm=[mm rr(3)-1];
%      
%      elseif A==C
%          mm=[mm rr(1)-1];
%      else
%          mm=[mm rr(1)-1];
%      end
%      end
%     hn(rr,cc)=0;
%     [rn cn]=find(hn==max(hn));
%     B=rn;
% %   
%   if length(B)==1
%       z=[A(1) B];
%        mm=[mm svmclassify(SVM(min(z),max(z)),ft(k,:))];
%   end
%   if length(B)==2
%       z=[A(1) B(1)];
%        E=svmclassify(SVM(min(z),max(z)),ft(k,:));
%       z=[A(1) B(2)];
%        F=svmclassify(SVM(min(z),max(z)),ft(k,:));
%        
%        if E==F
%            mm=[mm E];
%        else 
%            mm=[mm A(1)];
%        end
%   end
% %    D   
% %   mm=[mm c-1];
%  xxx=[xx0;xx1;xx2;xx3;xx4;xx5;xx6;xx7;xx8;xx9];
%     E=svmclassify(SVM(A+1,C+1),ft(k,:));
%     F=svmclassify(SVM(B+1,C+1),ft(k,:));
%     if (D==E)
%         OK=D;
%     elseif (D==F)
%         OK=D;
%     elseif (E==F)
%         OK=E;
%     else
%         OK=A;
%     end
    % if the 3 numbers are differnet use svmclassify between each 2 numbers
    % and if there is an identical number from the 3 output of the
    % svmclassify take it,else the output is the first number give from the
    % K-means.
  end
%   f=mm(1,800*(j-1)+1:800*j);
%   answer(j)=length(find(f==(j-1)))/800;
% switch OK
%     case 0
%         c_0=c_0+1;
%     case 1
%         c_1=c_1+1;
%     case 2
%         c_2=c_2+1;
%     case 3
%         c_3=c_3+1;
%     case 4
%         c_4=c_4+1;
%     case 5
%         c_5=c_5+1;
%     case 6
%         c_6=c_6+1;
%     case 7
%         c_7=c_7+1;
%     case 8
%         c_8=c_8+1;
%     case 9
%         c_9=c_9+1;
% end
%     end
% Confmat(:,j)=[c_0;c_1;c_2;c_3;c_4;c_5;c_6;c_7;c_8;c_9];
% accuracy(j)=(Confmat(j,j)/800)*100

%Calculating confusion matric and accuracies
end
% Confmat
% accuracy
toc






% ft=ft';
counter=[980 1135 1032 1010 982 892 958 1028 974 1009 ];
counter_all=[0 sum(counter(1,1:1)) sum(counter(1,1:2)) sum(counter(1,1:3)) sum(counter(1,1:4)) sum(counter(1,1:5)) sum(counter(1,1:6)) sum(counter(1,1:7))...
    sum(counter(1,1:8)) sum(counter(1,1:9)) sum(counter(1,1:10)) ];
%  mm=mmpoly83';
 mmm=mm';
%  D=D';
% mmk=mmrad8_10';
%  OK=[];
%  for k=1:10000;
%      A=mm(k);
%      B=mmm(k);
%      xx=[A B];
%      
%      if A~=B
%     
%          OK=[OK svmclassify(SVM(min(xx)+1,max(xx)+1),ft(k,:))];
%      else
%         
%          OK=[OK A];
%     end
%     
%  end
% 
%  OK=OK';

for kn=1:10
tt(:,kn)=length(find(mmm(counter_all(kn)+1:counter_all(kn+1),:)==kn-1))/length(mmm(counter_all(kn)+1:counter_all(kn+1),:))
end

mean(tt)