%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%This function compute the features for both 
%digit1 annd digit2 for all the database. 
% it call TestFeature
%%%%%%%%%%%%%%%%%%%%%%%%%%%5
function RunDigitsFeatures(digit1,digit2,text,Database)
%Database=3;

Train=0;
Test=1;

Digit1=digit1;
Digit2=digit2;
Featname=[text num2str(digit1) num2str(digit2)]

if (text=='a')
    
Featnum=digit1*10+digit2
end 
if (text=='b')  
Featnum=(digit1*10+digit2)+100

end

TestFeature(Digit1,Digit2,Featnum,Featname,Database,Train,0);

TestFeature(Digit1,Digit2,Featnum,Featname,Database,Test,0);