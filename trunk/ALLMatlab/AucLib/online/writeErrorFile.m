function y=writeErrorFile(Stroke, i,required, perdicted,Feature)
im=Stroke2Image(Stroke);
filename=['./Error/'  'Feat_' int2str(Feature) '_Er_'  int2str(i)   'is'  int2str(required)  'perdicted as' int2str(perdicted) '.bmp'];
imwrite(im,filename);
%%writing the stroke image.....
%filename2=['Error_' int2str(required)  'perdicted as' int2str(perdicted) '.txt'];

% fid= fopen([filename2],'wt');
% [r c]=size(im);
% 
% fprintf(fid,'%s  \n','  The Stroke image....  ' );
% 
% format=[repmat(' %d ',1,c) '\n'];
% 
% matFile=[ im ]';
% fprintf(fid,format,matFile);
% fclose(fid);
