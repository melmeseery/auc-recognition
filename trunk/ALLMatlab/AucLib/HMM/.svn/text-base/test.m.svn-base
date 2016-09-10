
%% i need hmm for each digit -----  then each hmm will be feed using a
%% sequence of observation based on the chain code of digit


Categories=['one','two','three','four','five','six'];
[seq,states] = hmmgenerate(100,trans,emis, 'Symbols',Categories , 'Statenames',{'fair';'loaded'})


%% I will try the hmm train... 
trans = [0.95,0.05;
      0.10,0.90];
  
emis = [1/6, 1/6, 1/6, 1/6, 1/6, 1/6;
   1/10, 1/10, 1/10, 1/10, 1/10, 1/2];

seq1 = hmmgenerate(100,trans,emis);
seq2 = hmmgenerate(200,trans,emis);
seqs = {seq1,seq2};
[estTR,estE] = hmmtrain(seqs,trans,emis);
%%then estimate 