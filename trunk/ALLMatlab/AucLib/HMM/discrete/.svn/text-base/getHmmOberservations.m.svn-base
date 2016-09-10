function Y=getHmmOberservations(Database,ReReadData,isOnline,varargin)
%ReReadData
if(isOnline)

       [data,label]=readStrokesDataSet(1,Database,ReReadData);
       Y=data;
       
       
          save data_Online data label ;
       
else 

if(ReReadData==1)
%for i=0:9
   [data,label]=readImages(1,Database);
   
        if (Database==0)
         save data_Raw_Arabic  data; 
     else  
   save data_Raw data ;
   
     end 
   %save data_Raw data ;
%end 
else
      if (Database==0)
         load data_Raw_Arabic; 
     else  
   load data_Raw;
   
     end 
    %load data_Raw;
end 
end 
[Y,Samples,FeatureSize]=computeHmmFeatures(data,isOnline,varargin{:});


