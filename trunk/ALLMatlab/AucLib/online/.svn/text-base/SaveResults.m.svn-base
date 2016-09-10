function y=SaveResults(filename,correct,result, correctAll,SizeAll )




RecognitionRate=(correctAll/SizeAll)*100;



fid= fopen([filename],'wt');

fprintf(fid,'%s  ',' The recognition rate is ');

fprintf(fid,'  %d   \n',RecognitionRate);


fprintf(fid,' %s   %d  %s  %d ', ' number of correct is ',correctAll, ' out of  ', SizeAll);


%format=[repmat('%d ',1,numFeat+1) '\n'];
%sizeOFy=size(Y)
%silbaels=size(labels)
%silabelPerSample=size(labelPerSample)
%matFile=[ Y' labelPerSample]';
%fprintf(fid,format,matFile);
y=fclose(fid);
disp('Finish writing the file');