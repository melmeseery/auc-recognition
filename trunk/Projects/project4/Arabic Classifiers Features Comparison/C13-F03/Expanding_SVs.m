clear
clc


num_of_features=200;

fid = fopen('SVs.txt', 'r');
SVs=[];
while 1
    SVs= [SVs fscanf(fid,'%d ')];
    if(feof(fid))
        break
    end
end
fclose(fid);


load('D:\WORK\AUC Research\Databases\Arabic Digits Databases\MAHDBase_TrainingSet_pathes');

load('D:\WORK\AUC Research\Databases\Arabic Digits Databases\training_set_labels');


load arabic_bbox_area_training_set training_set
zc_training_set= training_set;


variance = (sqrt(2)*4/pi)^2;
h= Generate_Gaussian_Mask([5 5],variance);

expanded_SVs_labels=[];

rep=5;
training_set=zeros(length(SVs)*rep,num_of_features);
count=1;
for n=1:length(SVs)
    if(mod(n,100)==0)
        n
    end

    d=training_set_labels(SVs(n));
    expanded_SVs_labels=[expanded_SVs_labels; repmat(d,[rep,1])];

    path=['D:\WORK\AUC Research\Databases\Arabic Digits Databases\MAHDBase\' MAHDBase_TrainingSet_pathes{SVs(n)}];
    I=double(255-imread(path))/255;



%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(I),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imrotate(I,10)),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imrotate(I,20)),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imrotate(I,30)),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imrotate(I,-10)),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imrotate(I,-20)),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imrotate(I,-30)),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imdilate(I,ones([2 2]))),h) zc_training_set(SVs(n))]; count=count+1;
%     training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(imerode(I,ones([2 2]))),h) zc_training_set(SVs(n))]; count=count+1;
    
%         alpha=20; sigma=10; % alpha=50, sigma=20
%         training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(I),h) zc_training_set(SVs(n))]; count=count+1;
%         training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h) zc_training_set(SVs(n))]; count=count+1;
% %         training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h) zc_training_set(SVs(n))]; count=count+1;
% %         training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h) zc_training_set(SVs(n))]; count=count+1;
% %         training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h) zc_training_set(SVs(n))]; count=count+1;

        alpha=20; sigma=10; % alpha=50, sigma=20
        training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(I),h)]; count=count+1;
        training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h)]; count=count+1;
        training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h)]; count=count+1;
        training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h)]; count=count+1;
        training_set(count,:)= [gmask_Local_Gradient_proj(Adjust_Sample(elastic_distortion(I,alpha,sigma)),h)]; count=count+1;


%         subplot(311)
%         V=Adjust_Sample(I);
%         imshow(V(5:24,5:24))
% %         
% %         
% %     
% %         alpha=50; sigma=20;
%         subplot(312)
%         V=Adjust_Sample(elastic_distortion(I,alpha,sigma));
%         imshow(V(5:24,5:24))
%     %
%         subplot(313)
%         %imshow(imrotate(I,-30,'bilinear'))
%         V=Adjust_Sample(elastic_distortion(I,alpha,sigma));
%         imshow(V(5:24,5:24))
%     
%         input('zeo:')
%         close all


end

save arabic_expanded_SV_gmask_gradproj_training_set training_set


fid= fopen('arabic_expanded_SVs_gmask_gradproj_training_set.txt','wt');
fprintf(fid,'%d %d \n',length(SVs)*rep,num_of_features+1);

format=[repmat('%d ',1,num_of_features+1) '\n'];


training_set= training_set.^(0.5);
fprintf(fid,format,[training_set expanded_SVs_labels]');


fclose(fid);















