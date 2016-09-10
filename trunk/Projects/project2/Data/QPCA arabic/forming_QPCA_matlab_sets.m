clear
clc

load('C:\Documents and Settings\ezzat\Desktop\training_set_labels');


load training_set training_set


load mu mu
load PCvec PCvec

new_training_set= PCvec'*training_set;

save new_training_set new_training_set


QPCA_training_set=zeros(820,60e3);

start=1;
for i=1:40
    i
    A=new_training_set(1:end-i+1,:).*new_training_set(i:end,:);
    QPCA_training_set(start:start+40-i,:)=A;
    start=(start+40-i)+1;
end

save QPCA_training_set QPCA_training_set

clear
clc

load('C:\Documents and Settings\ezzat\Desktop\testing_set_labels');
load testing_set testing_set


load mu mu
load PCvec PCvec

new_testing_set= PCvec'*testing_set;

save new_testing_set new_testing_set


QPCA_testing_set=zeros(820,10e3);

start=1;
for i=1:40
    i
    A=new_testing_set(1:end-i+1,:).*new_testing_set(i:end,:);
    QPCA_testing_set(start:start+40-i,:)=A;
    start=(start+40-i)+1;
end

save QPCA_testing_set QPCA_testing_set



