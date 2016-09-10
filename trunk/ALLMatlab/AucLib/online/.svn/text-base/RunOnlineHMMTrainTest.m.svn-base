%function y=RunOnlineHMMTrainTest(CodeBookSize, train, test,ReadImages, ReadImagesTest,States , Feature, NormalizedSize ,Order)

clear all
clc
finalResultForaAll=[];


filename2= ['All_Result_HMM_New_WriteCorrect.txt'] ;   
fid= fopen([filename2],'wt');

for n=1:23
CodeBookSize=256;
States=3;  %number of states


PrePareData=1;
ReadImages=0;
ReadImagesTest=0;
RecomputeFeatures=1;
createCodeBook=1;

maxiter=500;
tol=0.0001;
train =1;
test=1;

SizeCategories=10;
R=SizeCategories;
Feature=n;
Order=8;
NormalizedSize=60;
ReSample=1;
isOnline=true;

%% Get Data for code boook 
if (PrePareData)
       disp('get data ');
    if(RecomputeFeatures==1)
            disp('Start Comptue the datat ');
         data_Hmm=getHmmOberservations(Database,ReadImages,isOnline,'Feature',Feature,'NormalizedSize',NormalizedSize,'Order',Order,'ReSample',ReSample );
       % data_Hmm=getHmmOberservations(Database,ReadImages);

        
        %dataHmm=dataHmm+1;
   
            save data_Features data_Hmm;
       
        disp('finish reading the file');
        
         disp(' end computing data ');
    else
    load data_Features
         disp(' end reading data ');
    end
    %break;
%% CODE BOOK
    if (createCodeBook)
            disp('start creating the code book');
        %data_Hmm=data_Hmm+1;
        [cb,sse] =vgg_kmeans(data_Hmm,CodeBookSize);
        [N,K] = size(cb);                     % Codebook symbol size K.
        [N,T] = size(data_Hmm);                      % Vector dim and number of vectors.
        %[cb,K,T] = hmmcodebook(data_Hmm,CodeBookSize); % Do not use this
       
        save CodeBookData cb K T
    else
        load CodeBookData
    end

    disp('finish creating codebook');
end;
%break ;


%%Train

if (train)
    disp('Start training ');
 
    load data_Online;
    
    load CodeBookData;

    A_m  = cell(R,1);          % Output arguments in cell-arrays.
    B_m  = cell(R,1);
    pi_m = cell(R,1);
    for l=1:R
        A = rand(States,States);
        
        A(1,3)=0;
        A(2,1)=0;
        A(3,1)=0;
        A(3,2)=0; %  to change topology .
        A = A./repmat(sum(A),States,1); % Initial random model
        B = rand(K,States);  % the
        B = B./repmat(sum(B),K,1); % with probability sum
        % B=A;
        pi1 = rand(States,1);
        pi1 = pi1 / sum(pi1);      % normalized to one.

        A_m(l)  = {A};
        B_m(l)  = {B};
        pi_m(l) = {pi1};
    end

    %dataFor

    %[A,B,pi1,loglike_m] = hmmtrain2(data,States,maxiter,tol,cb,R,A_m,B_m,pi_m); % 4: states 16 : observations
     [A,B,pi1,loglike_m] = hmmtrain2(data,States,maxiter,tol,cb,R,A_m,B_m,pi_m,isOnline,'Feature',Feature,'NormalizedSize',NormalizedSize,'Order',Order,'ReSample',ReSample); % 4: states 16 : observations


    save HmmModel A B pi1
    
        disp('end training ');
end

%%Test

if (test)
        disp('Start testing ');
    load CodeBookData
    load HmmModel A B pi1

    %%Read the test set...
    dataTest=getTestSet(Database,isOnline,ReadImagesTest);
    %%%
    correctAll=0;
    SizeAll=0;
    for i= 1 :SizeCategories
        i
        %[logp,guess{i}] = hmmrecog( dataTest{i},A, B, pi1,cb);
         [logp,guess{i}] = hmmrecog( dataTest{i},A, B, pi1,cb,isOnline,'Feature',Feature,'NormalizedSize',NormalizedSize,'Order',Order,'ReSample',ReSample);

        correct=  length(find(guess{i}==i))
       
        result{i}=correct/length(dataTest{i})
        correctAll=correct+correctAll;
        SizeAll=SizeAll+length(dataTest{i});
    for xx=1:length(guess{i})
        if (guess{i}(xx)==i)
            
        else
         %   writeErrorFile(dataTest{i}{xx}, xx, i,guess{i}(xx),Feature);
        end 
    end 
        
    end
    RecognitionRate=correctAll/SizeAll
    finalResultForaAll(n)=  RecognitionRate*100.0;
    fprintf(fid,'Recognition rate for feature  %f  is  %f \n',n,    finalResultForaAll(n));
    correctAll
    disp(' out of ');
    SizeAll
    
    SaveResults(['Feature'  int2str(Feature)  '_Result_HMM_New.txt'],correct,result, correctAll,SizeAll);
    
        disp(' end testing');
end

end 

y=fclose(fid);
disp('Finish writing the file');


%end 