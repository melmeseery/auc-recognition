clc
clear all 
close all
load strokes

% c=strokes2.stroke_14;
%  [norm]=newchaincode(c);
data={};
for x=1:76
eval(['data{' int2str(1) ',' int2str(x) '}=strokes.stroke_' int2str(x) ';']);
% figure 
% plot(data{1,x}(:,1),data{1,x}(:,2),'.')
% eval(['data{' int2str(2) ',' int2str(x) '}=strokes2.stroke_' int2str(x) ';']);
end
datafinal={};
x=0;
for i=1:76
    if length(data{1,i}(:,1))>10
        x=x+1;
        datafinal{1,x}=data{1,i};
%         figure
%         plot(datafinal{1,x}(:,1),datafinal{1,x}(:,2),'.')
        
    end
end
    
 f=[];
 for i=1:72
     i
 f=[f newchaincode(datafinal{1,i}(:,1:2),50,6)'];
 end
 for i=1:4
     data1{i} =f(:,i)';
 end
 for i=5:8
     data2{i-4} =f(:,i)';
 end
 for i=9:12
     data3{i-8} =f(:,i)';
 end
 for i=13:16
     data4{i-12} =f(:,i)';
 end
 for i=17:20
     data5{i-16} =f(:,i)';
 end
 for i=21:24
     data6{i-20} =f(:,i)';
 end
 for i=25:28
     data7{i-24} =f(:,i)';
 end
 for i=29:32
     data8{i-28} =f(:,i)';
 end
 for i=33:36
     data9{i-32} =f(:,i)';
 end
 for i=36:40
     data10{i-35} =f(:,i)';
 end
 for i=41:44
     data11{i-40} =f(:,i)';
 end
 for i=45:48
     data12{i-44} =f(:,i)';
 end
 for i=49:52
     data13{i-48} =f(:,i)';
 end
 for i=53:56
     data14{i-52} =f(:,i)';
 end
 for i=57:60
     data15{i-56} =f(:,i)';
 end
 for i=61:64
     data16{i-60} =f(:,i)';
 end
 for i=65:68
     data17{i-64} =f(:,i)';
 end
 for i=69:72
     data18{i-68} =f(:,i)';
 end
%  data{2} = [8 8 7 7 7 6 6 5 5 5 5 4 4 3 2 1 1 16 15 15 15 15 15 14 14 14 ...
% 14 13 12 11 10 10 9 9 9 ];
%  data0{3} = [7 6 6 6 6 6 6 5 5 5 4 3 2 1 16 16 16 15 15 15 15 14 14 14 14 ...
% 14 14 13 11 10 9 9 8 8 8 8 7 7 6 6 ];
% data1{1} = [5 5 5 5 5 5 5 5 5 5 5 4 ];
% data1{2} = [5 6 6 6 6 6 6 6 6 5 5 4 ];
% data1{3} = [5 5 5 6 6 6 6 6 6 7 6 4 3];
hmm0.prior = [1 0 0   ];
hmm0.transmat = rand(3,3); % 3 by 3 transition matrix
hmm0.transmat(2,1) =0; hmm0.transmat(3,1) = 0; hmm0.transmat(3,2) = 0;
%  hmm0.transmat(4,1) = 0; hmm0.transmat(4,2) = 0;hmm0.transmat(4,3) = 0;
hmm0.transmat = mk_stochastic(hmm0.transmat);
hmm0.transmat;
hmm0.obsmat = rand(3, 6); % # of states * # of observation
hmm0.obsmat = mk_stochastic(hmm0.obsmat);
hmm0.obsmat;
%HMM for class ‘1’ and randomly initialization
hmm1.prior=hmm0.prior;hmm1.transmat=hmm0.transmat;hmm1.obsmat=hmm0.obsmat;
hmm2.prior=hmm0.prior;hmm2.transmat=hmm0.transmat;hmm2.obsmat=hmm0.obsmat;
hmm3.prior=hmm0.prior;hmm3.transmat=hmm0.transmat;hmm3.obsmat=hmm0.obsmat;
hmm4.prior=hmm0.prior;hmm4.transmat=hmm0.transmat;hmm4.obsmat=hmm0.obsmat;
hmm5.prior=hmm0.prior;hmm5.transmat=hmm0.transmat;hmm5.obsmat=hmm0.obsmat;
hmm6.prior=hmm0.prior;hmm6.transmat=hmm0.transmat;hmm6.obsmat=hmm0.obsmat;
hmm7.prior=hmm0.prior;hmm7.transmat=hmm0.transmat;hmm7.obsmat=hmm0.obsmat;
hmm8.prior=hmm0.prior;hmm8.transmat=hmm0.transmat;hmm8.obsmat=hmm0.obsmat;
hmm9.prior=hmm0.prior;hmm9.transmat=hmm0.transmat;hmm9.obsmat=hmm0.obsmat;
hmm10.prior=hmm0.prior;hmm10.transmat=hmm0.transmat;hmm10.obsmat=hmm0.obsmat;
hmm11.prior=hmm0.prior;hmm11.transmat=hmm0.transmat;hmm11.obsmat=hmm0.obsmat;
hmm12.prior=hmm0.prior;hmm12.transmat=hmm0.transmat;hmm12.obsmat=hmm0.obsmat;
hmm13.prior=hmm0.prior;hmm13.transmat=hmm0.transmat;hmm13.obsmat=hmm0.obsmat;
hmm14.prior=hmm0.prior;hmm14.transmat=hmm0.transmat;hmm14.obsmat=hmm0.obsmat;
hmm15.prior=hmm0.prior;hmm15.transmat=hmm0.transmat;hmm15.obsmat=hmm0.obsmat;
hmm16.prior=hmm0.prior;hmm16.transmat=hmm0.transmat;hmm16.obsmat=hmm0.obsmat;
hmm17.prior=hmm0.prior;hmm17.transmat=hmm0.transmat;hmm17.obsmat=hmm0.obsmat;
hmm18.prior=hmm0.prior;hmm18.transmat=hmm0.transmat;hmm18.obsmat=hmm0.obsmat;
 [LL1, hmm1.prior, hmm1.transmat, hmm1.obsmat] = dhmm_em(data1,hmm1.prior, hmm1.transmat, hmm1.obsmat,'max_iter',10,'thresh',1e-4);
[LL2, hmm2.prior, hmm2.transmat, hmm2.obsmat] = dhmm_em(data2,hmm2.prior, hmm2.transmat, hmm2.obsmat,'max_iter',10,'thresh',1e-4);
[LL3, hmm3.prior, hmm3.transmat, hmm3.obsmat] = dhmm_em(data3,hmm3.prior, hmm3.transmat, hmm3.obsmat,'max_iter',10,'thresh',1e-4);
[LL4, hmm4.prior, hmm4.transmat, hmm4.obsmat] = dhmm_em(data4,hmm4.prior, hmm4.transmat, hmm4.obsmat,'max_iter',10,'thresh',1e-4);
[LL5, hmm5.prior, hmm5.transmat, hmm5.obsmat] = dhmm_em(data5,hmm5.prior, hmm5.transmat, hmm5.obsmat,'max_iter',10,'thresh',1e-4);
[LL6, hmm6.prior, hmm6.transmat, hmm6.obsmat] = dhmm_em(data6,hmm6.prior, hmm6.transmat, hmm6.obsmat,'max_iter',10,'thresh',1e-4);
[LL7, hmm7.prior, hmm7.transmat, hmm7.obsmat] = dhmm_em(data7,hmm7.prior, hmm7.transmat, hmm7.obsmat,'max_iter',10,'thresh',1e-4);
[LL8, hmm8.prior, hmm8.transmat, hmm8.obsmat] = dhmm_em(data8,hmm8.prior, hmm8.transmat, hmm8.obsmat,'max_iter',10,'thresh',1e-4);
[LL9, hmm9.prior, hmm9.transmat, hmm9.obsmat] = dhmm_em(data9,hmm9.prior, hmm9.transmat, hmm9.obsmat,'max_iter',10,'thresh',1e-4);
[LL10, hmm10.prior, hmm10.transmat, hmm10.obsmat] = dhmm_em(data10,hmm10.prior, hmm10.transmat, hmm10.obsmat,'max_iter',10,'thresh',1e-4);
[LL11 hmm11.prior, hmm11.transmat, hmm11.obsmat] = dhmm_em(data11,hmm11.prior, hmm11.transmat, hmm11.obsmat,'max_iter',10,'thresh',1e-4);
[LL12, hmm12.prior, hmm12.transmat, hmm12.obsmat] = dhmm_em(data12,hmm12.prior, hmm12.transmat, hmm12.obsmat,'max_iter',10,'thresh',1e-4);
[LL13, hmm13.prior, hmm13.transmat, hmm13.obsmat] = dhmm_em(data13,hmm13.prior, hmm13.transmat, hmm13.obsmat,'max_iter',10,'thresh',1e-4);
[LL14, hmm14.prior, hmm14.transmat, hmm14.obsmat] = dhmm_em(data14,hmm14.prior, hmm14.transmat, hmm14.obsmat,'max_iter',10,'thresh',1e-4);
[LL15, hmm15.prior, hmm15.transmat, hmm15.obsmat] = dhmm_em(data15,hmm15.prior, hmm15.transmat, hmm15.obsmat,'max_iter',10,'thresh',1e-4);
[LL16, hmm16.prior, hmm16.transmat, hmm16.obsmat] = dhmm_em(data16,hmm16.prior, hmm16.transmat, hmm16.obsmat,'max_iter',10,'thresh',1e-4);
[LL17, hmm17.prior, hmm17.transmat, hmm17.obsmat] = dhmm_em(data17,hmm17.prior, hmm17.transmat, hmm17.obsmat,'max_iter',10,'thresh',1e-4);
[LL18, hmm18.prior, hmm18.transmat, hmm18.obsmat] = dhmm_em(data18,hmm18.prior, hmm18.transmat, hmm18.obsmat,'max_iter',10,'thresh',1e-4);
load strokest
for x=1:20
eval(['datatest{' int2str(1) ',' int2str(x) '}=strokest.stroke_' int2str(x) ';']);
% figure 
% plot(data{1,x}(:,1),data{1,x}(:,2),'.')
% eval(['data{' int2str(2) ',' int2str(x) '}=strokes2.stroke_' int2str(x) ';']);
end
datafinaltest={};
 x=0;
for i=1:20
    if length(datatest{1,i}(:,1))>2
         x=x+1;
         datafinaltest{1,x}=datatest{1,i};
%          figure
%         plot(datafinaltest{1,x}(:,1),datafinaltest{1,x}(:,2),'.')
        
    end
end

ft=[];
for i=1:18
     
 ft=[ft newchaincode(datafinaltest{1,i}(:,1:2),50,6)'];
end
 
for dt=1:18
% for dt =1:length(data2)

 loglike1 = dhmm_logprob(ft(:,dt)', hmm1.prior, hmm1.transmat,hmm1.obsmat);
 loglike2 = dhmm_logprob(ft(:,dt)', hmm2.prior, hmm2.transmat,hmm2.obsmat);
 loglike3 = dhmm_logprob(ft(:,dt)', hmm3.prior, hmm3.transmat,hmm3.obsmat);
 loglike4 = dhmm_logprob(ft(:,dt)', hmm4.prior, hmm4.transmat,hmm4.obsmat);
 loglike5 = dhmm_logprob(ft(:,dt)', hmm5.prior, hmm5.transmat,hmm5.obsmat);
 loglike6 = dhmm_logprob(ft(:,dt)', hmm6.prior, hmm6.transmat,hmm6.obsmat);
 loglike7 = dhmm_logprob(ft(:,dt)', hmm7.prior, hmm7.transmat,hmm7.obsmat);
 loglike8 = dhmm_logprob(ft(:,dt)', hmm8.prior, hmm8.transmat,hmm8.obsmat);
 loglike9 = dhmm_logprob(ft(:,dt)', hmm9.prior, hmm9.transmat,hmm9.obsmat);
 loglike10 = dhmm_logprob(ft(:,dt)', hmm10.prior, hmm10.transmat,hmm10.obsmat);
 loglike11 = dhmm_logprob(ft(:,dt)', hmm11.prior, hmm11.transmat,hmm11.obsmat);
 loglike12 = dhmm_logprob(ft(:,dt)', hmm12.prior, hmm12.transmat,hmm12.obsmat);
 loglike13 = dhmm_logprob(ft(:,dt)', hmm13.prior, hmm13.transmat,hmm13.obsmat);
 loglike14 = dhmm_logprob(ft(:,dt)', hmm14.prior, hmm14.transmat,hmm14.obsmat);
 loglike15 = dhmm_logprob(ft(:,dt)', hmm15.prior, hmm15.transmat,hmm15.obsmat);
 loglike16 = dhmm_logprob(ft(:,dt)', hmm16.prior, hmm16.transmat,hmm16.obsmat);
 loglike17 = dhmm_logprob(ft(:,dt)', hmm17.prior, hmm17.transmat,hmm17.obsmat);
 loglike18 = dhmm_logprob(ft(:,dt)', hmm18.prior, hmm18.transmat,hmm18.obsmat);
 de(1,:)=[loglike1 loglike2 loglike3 loglike4 loglike5 loglike6 loglike7 loglike8 loglike9 loglike10 loglike11 loglike12 loglike13 loglike14 loglike15 loglike16 loglike17...
    loglike18 ];
num(dt)=find(de(1,:)==max(de(1,:)));
end
% disp(sprintf('[class 1: %d-th data] model 0: %.3f, model 1: %.3f',dt,...
% loglike0, loglike1))';
% disp(sprintf('[class 1: %d-th data] model 0: %.3f, model 1: %.3f',dt,...
% loglike0, loglike1));
% end

% de(dt,:)=[loglike1 loglike2 loglike3 loglike4 loglike5 loglike6 loglike7 loglike8 loglike9 loglike10 loglike11 loglike12 loglike13 loglike14 loglike15 loglike16 loglike17...
%     loglike18 ];
%  
% 
% 
% for dt =1:length(data0)
% B = multinomial_prob(data0{dt}, hmm0.obsmat);
% path = viterbi_path(hmm0.prior, hmm0.transmat, B);
% disp(sprintf('%d', path));
% end