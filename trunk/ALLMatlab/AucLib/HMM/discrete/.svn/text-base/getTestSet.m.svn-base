function    dataTest=getTestSet(Database,isOnline,ReReadData)
if(isOnline)
       [dataTest,labels]=readStrokesDataSet(0,Database,ReReadData);
else 
 if(ReReadData==1)
%for i=0:9
   [dataTest,labels]=readImages(2,Database);
       if (Database==0)
   save data_Raw_Test_Arabic dataTest ;
       else   save data_Raw_Test dataTest ;
       end 
%end 
 else
        if (Database==0)
        load    data_Raw_Test_Arabic;
        else     load data_Raw_Test;
        end 
 end 
end 