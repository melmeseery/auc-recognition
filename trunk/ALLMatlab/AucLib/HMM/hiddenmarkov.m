
%for i=1:22  remove 
%eval([  'data' int2str(i) ' =f1 {' int2str(i) ' } ;']);
%end

hmm0.prior = [1 0 0  ];
hmm0.transmat = rand(3,3); % 3 by 3 transition matrix
 hmm0.transmat(2,1) =0; hmm0.transmat(3,1) = 0; hmm0.transmat(3,2) = 0;
%    hmm0.transmat(4,1) = 0; hmm0.transmat(4,2) = 0;hmm0.transmat(4,3) = 0;
hmm0.transmat = mk_stochastic(hmm0.transmat);
hmm0.transmat;
hmm0.obsmat = rand(3, 16); % # of states * # of observation
hmm0.obsmat = mk_stochastic(hmm0.obsmat);
hmm0.obsmat;
%init alizze all the hmm with obst. and transmat 
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
hmm19.prior=hmm0.prior;hmm19.transmat=hmm0.transmat;hmm19.obsmat=hmm0.obsmat;
hmm20.prior=hmm0.prior;hmm20.transmat=hmm0.transmat;hmm20.obsmat=hmm0.obsmat;
hmm21.prior=hmm0.prior;hmm21.transmat=hmm0.transmat;hmm21.obsmat=hmm0.obsmat;
hmm22.prior=hmm0.prior;hmm22.transmat=hmm0.transmat;hmm22.obsmat=hmm0.obsmat;

 [LL1, hmm1.prior, hmm1.transmat, hmm1.obsmat] = dhmm_em(data1,hmm1.prior, hmm1.transmat, hmm1.obsmat);
[LL2, hmm2.prior, hmm2.transmat, hmm2.obsmat] = dhmm_em(data2,hmm2.prior, hmm2.transmat, hmm2.obsmat);
[LL3, hmm3.prior, hmm3.transmat, hmm3.obsmat] = dhmm_em(data3,hmm3.prior, hmm3.transmat, hmm3.obsmat);
[LL4, hmm4.prior, hmm4.transmat, hmm4.obsmat] = dhmm_em(data4,hmm4.prior, hmm4.transmat, hmm4.obsmat);
[LL5, hmm5.prior, hmm5.transmat, hmm5.obsmat] = dhmm_em(data5,hmm5.prior, hmm5.transmat, hmm5.obsmat);
[LL6, hmm6.prior, hmm6.transmat, hmm6.obsmat] = dhmm_em(data6,hmm6.prior, hmm6.transmat, hmm6.obsmat);
[LL7, hmm7.prior, hmm7.transmat, hmm7.obsmat] = dhmm_em(data7,hmm7.prior, hmm7.transmat, hmm7.obsmat);
[LL8, hmm8.prior, hmm8.transmat, hmm8.obsmat] = dhmm_em(data8,hmm8.prior, hmm8.transmat, hmm8.obsmat);
[LL9, hmm9.prior, hmm9.transmat, hmm9.obsmat] = dhmm_em(data9,hmm9.prior, hmm9.transmat, hmm9.obsmat);
[LL10, hmm10.prior, hmm10.transmat, hmm10.obsmat] = dhmm_em(data10,hmm10.prior, hmm10.transmat, hmm10.obsmat);
[LL11 hmm11.prior, hmm11.transmat, hmm11.obsmat] = dhmm_em(data11,hmm11.prior, hmm11.transmat, hmm11.obsmat);
[LL12, hmm12.prior, hmm12.transmat, hmm12.obsmat] = dhmm_em(data12,hmm12.prior, hmm12.transmat, hmm12.obsmat);
[LL13, hmm13.prior, hmm13.transmat, hmm13.obsmat] = dhmm_em(data13,hmm13.prior, hmm13.transmat, hmm13.obsmat);
[LL14, hmm14.prior, hmm14.transmat, hmm14.obsmat] = dhmm_em(data14,hmm14.prior, hmm14.transmat, hmm14.obsmat);
[LL15, hmm15.prior, hmm15.transmat, hmm15.obsmat] = dhmm_em(data15,hmm15.prior, hmm15.transmat, hmm15.obsmat);
[LL16, hmm16.prior, hmm16.transmat, hmm16.obsmat] = dhmm_em(data16,hmm16.prior, hmm16.transmat, hmm16.obsmat);
[LL17, hmm17.prior, hmm17.transmat, hmm17.obsmat] = dhmm_em(data17,hmm17.prior, hmm17.transmat, hmm17.obsmat);
[LL118, hmm18.prior, hmm18.transmat, hmm18.obsmat] = dhmm_em(data18,hmm18.prior, hmm18.transmat, hmm18.obsmat);
[LL19, hmm19.prior, hmm19.transmat, hmm19.obsmat] = dhmm_em(data19,hmm19.prior, hmm19.transmat, hmm19.obsmat);
[LL20, hmm20.prior, hmm20.transmat, hmm20.obsmat] = dhmm_em(data20,hmm20.prior, hmm20.transmat, hmm20.obsmat);
[LL21, hmm21.prior, hmm21.transmat, hmm21.obsmat] = dhmm_em(data21,hmm21.prior, hmm21.transmat, hmm21.obsmat);
[LL22, hmm22.prior, hmm22.transmat, hmm22.obsmat] = dhmm_em(data22,hmm22.prior, hmm22.transmat, hmm22.obsmat);


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   testing.........................................

ft=f1;

% for i=1:22
% eval([  'ft' int2str(i) ' =f1 {1,' int2str(i) ' } ;']);
% end

for dt=1:22
% for dt =1:length(data2)
for k=1:10
 loglike1 = dhmm_logprob(ft{1,dt}{1,k}, hmm1.prior, hmm1.transmat,hmm1.obsmat);
 loglike2 = dhmm_logprob(ft{1,dt}{1,k}, hmm2.prior, hmm2.transmat,hmm2.obsmat);
 loglike3 = dhmm_logprob(ft{1,dt}{1,k}, hmm3.prior, hmm3.transmat,hmm3.obsmat);
 loglike4 = dhmm_logprob(ft{1,dt}{1,k}, hmm4.prior, hmm4.transmat,hmm4.obsmat);
 loglike5 = dhmm_logprob(ft{1,dt}{1,k}, hmm5.prior, hmm5.transmat,hmm5.obsmat);
 loglike6 = dhmm_logprob(ft{1,dt}{1,k}, hmm6.prior, hmm6.transmat,hmm6.obsmat);
 loglike7 = dhmm_logprob(ft{1,dt}{1,k}, hmm7.prior, hmm7.transmat,hmm7.obsmat);
 loglike8 = dhmm_logprob(ft{1,dt}{1,k}, hmm8.prior, hmm8.transmat,hmm8.obsmat);
 loglike9 = dhmm_logprob(ft{1,dt}{1,k}, hmm9.prior, hmm9.transmat,hmm9.obsmat);
 loglike10 = dhmm_logprob(ft{1,dt}{1,k}, hmm10.prior, hmm10.transmat,hmm10.obsmat);
 loglike11 = dhmm_logprob(ft{1,dt}{1,k}, hmm11.prior, hmm11.transmat,hmm11.obsmat);
 loglike12 = dhmm_logprob(ft{1,dt}{1,k}, hmm12.prior, hmm12.transmat,hmm12.obsmat);
 loglike13 = dhmm_logprob(ft{1,dt}{1,k}, hmm13.prior, hmm13.transmat,hmm13.obsmat);
 loglike14 = dhmm_logprob(ft{1,dt}{1,k}, hmm14.prior, hmm14.transmat,hmm14.obsmat);
 loglike15 = dhmm_logprob(ft{1,dt}{1,k}, hmm15.prior, hmm15.transmat,hmm15.obsmat);
 loglike16 = dhmm_logprob(ft{1,dt}{1,k}, hmm16.prior, hmm16.transmat,hmm16.obsmat);
 loglike17 = dhmm_logprob(ft{1,dt}{1,k}, hmm17.prior, hmm17.transmat,hmm17.obsmat);
 loglike18 = dhmm_logprob(ft{1,dt}{1,k}, hmm18.prior, hmm18.transmat,hmm18.obsmat);
 loglike19 = dhmm_logprob(ft{1,dt}{1,k}, hmm19.prior, hmm19.transmat,hmm19.obsmat);
 loglike20 = dhmm_logprob(ft{1,dt}{1,k}, hmm20.prior, hmm20.transmat,hmm20.obsmat);
 loglike21 = dhmm_logprob(ft{1,dt}{1,k}, hmm21.prior, hmm21.transmat,hmm21.obsmat);
 loglike22 = dhmm_logprob(ft{1,dt}{1,k}, hmm22.prior, hmm22.transmat,hmm22.obsmat);
 de(1,:)=[loglike1 loglike2 loglike3 loglike4 loglike5 loglike6 loglike7 loglike8 loglike9 loglike10 loglike11 loglike12 loglike13 loglike14 loglike15 loglike16 loglike17...
    loglike18 loglike19 loglike20 loglike21 loglike22];
numm(dt,k)=find(de(1,:)==max(de(1,:)));
end
end
