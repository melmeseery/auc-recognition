

%for n=1:21
    
   
clear all
clc
n=1; 
SizeCategories=10;
PrePareData=1;
ReadImages=1;
ReadImagesTest=1;
%RecomputeFeatures=1;
%createCodeBook=1;
%maxiter=500;
%tol=0.0001;
train =1;
test=0;
Feature=n;
Order=8;
NormalizedSize=50;
CodeBookSize=512;
States =3;
y=RunOnlineHMMTrainTest(CodeBookSize, train, test,ReadImages, ReadImagesTest,States , Feature, NormalizedSize ,Order);




%end 
%%%%%%%%%%%
% This code will run to
% train the data...
% initalization and settings..
%%%%%%%
% clear all
% clc
% Database=1;
% CodeBookSize=512;
% States=3;  %number of states
% 
% 
% PrePareData=1;
% ReadImages=1;
% ReadImagesTest=1;
% RecomputeFeatures=1;
% createCodeBook=1;
% 
% maxiter=500;
% tol=0.0001;
% train =0;
% test=1;
% 
% 
% SizeCategories=10;
% 
% 
% Feature=4;
% Order=8;
% NormalizedSize=0;
%  
% isOnline=true;
% 
% %% Get Data for code boook 
% if (PrePareData)
%        disp('get data ');
%     if(RecomputeFeatures==1)
%             disp('Start Comptue the datat ');
%          data_Hmm=getHmmOberservations(Database,ReadImages,isOnline,'Feature',Feature,'NormalizedSize',NormalizedSize,'Order',Order);
%        % data_Hmm=getHmmOberservations(Database,ReadImages);
% 
%         
%         %dataHmm=dataHmm+1;
%         if (Database==0)
%             save data_Features_Arabic data_Hmm;
%         else
%             save data_Features data_Hmm;
%         end
%         disp('finish reading the file');
%         
%          disp(' end computing data ');
%     else
%         if (Database==0)
%             load data_Features_Arabic
%         else
%             load data_Features
%         end
%         
%               disp(' end reading data ');
%     end
%     %break;
% %% CODE BOOK
% 
%     if (createCodeBook)
%             disp('start creating the code book');
%         %data_Hmm=data_Hmm+1;
%         [cb,sse] =vgg_kmeans(data_Hmm,CodeBookSize);
%         [N,K] = size(cb);                     % Codebook symbol size K.
%         [N,T] = size(data_Hmm);                      % Vector dim and number of vectors.
%         %[cb,K,T] = hmmcodebook(data_Hmm,CodeBookSize); % Do not use this
%        
%         save CodeBookData cb K T
%     else
%         load CodeBookData
%     end
% 
%     disp('finish creating codebook');
% end;
% %break ;
% 
% 
% %%Train
% 
% if (train)
%     disp('Start training ');
%     if (Database==0)
%         load data_Raw_Arabic;
%     else
%         load data_Raw;
% 
%     end
% 
%     load CodeBookData
% 
% 
%     R=10;
% 
%     A_m  = cell(R,1);          % Output arguments in cell-arrays.
%     B_m  = cell(R,1);
%     pi_m = cell(R,1);
%     for l=1:R
%         A = rand(States,States);
%         A(2,1)=0;;A(3,1)=0;A(3,2)=0; %  to change topology .
%         A = A./repmat(sum(A),States,1); % Initial random model
%         B = rand(K,States);  % the
%         B = B./repmat(sum(B),K,1); % with probability sum
%         % B=A;
%         pi1 = rand(States,1);
%         pi1 = pi1 / sum(pi1);      % normalized to one.
% 
%         A_m(l)  = {A};
%         B_m(l)  = {B};
%         pi_m(l) = {pi1};
%     end
% 
%     %dataFor
% 
%     %[A,B,pi1,loglike_m] = hmmtrain2(data,States,maxiter,tol,cb,R,A_m,B_m,pi_m); % 4: states 16 : observations
%      [A,B,pi1,loglike_m] = hmmtrain2(data,States,maxiter,tol,cb,R,A_m,B_m,pi_m,isOnline,'Feature',Feature,'NormalizedSize',NormalizedSize,'Order',Order); % 4: states 16 : observations
% 
% 
%     save HmmModel A B pi1
%     
%         disp('end training ');
% end
% 
% %%Test
% 
% if (test)
%         disp('Start testing ');
%     load CodeBookData
%     load HmmModel A B pi1
% 
%     %%Read the test set...
%     dataTest=getTestSet(Database,isOnline,ReadImagesTest);
%     %%%
%     correctAll=0;
%     SizeAll=0;
%     for i= 1 :SizeCategories
%         i
%         %[logp,guess{i}] = hmmrecog( dataTest{i},A, B, pi1,cb);
%          [logp,guess{i}] = hmmrecog( dataTest{i},A, B, pi1,cb,isOnline,'Feature',Feature,'NormalizedSize',NormalizedSize,'Order',Order);
% 
%         correct=  length(find(guess{i}==i))
%         result{i}=correct/length(dataTest{i})
%         correctAll=correct+correctAll;
%         SizeAll=SizeAll+length(dataTest{i});
%     end
%     RecognitionRate=correctAll/SizeAll
%     correctAll
%     disp(' out of ');
%     SizeAll
%     
%         disp(' end testing');
% end
% 
% 
% 
% 
