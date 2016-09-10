clear
clc

load training_set
load mu mu

for i=1:60e3
    training_set(:,i)=training_set(:,i)- mu;
end

disp('applying PCA...')
[PCcoeff, PCvec] = pca(training_set(:,1:10e3)',100);

PCA_training_set= PCvec'*training_set;

large_N_PCcoeff=PCcoeff;
save large_N_PCcoeff large_N_PCcoeff

plot(PCcoeff)

% accumulated_PCcoeff=[];
% for n=1:length(PCcoeff)
%     accumulated_PCcoeff=[accumulated_PCcoeff sum(PCcoeff(1:n))];
% end
% 
% hold on
% plot(accumulated_PCcoeff)
% hold off
% 
