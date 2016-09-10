function [HProjection VProjection,ProjectionRightDLeft, ProjectionLeftDRight]=Projections(I)
VProjection=sum(I')';
HProjection=sum(I);

[h,w]=size(I);
 
%TempRDL =[]; 
%TEmpLDR=[];
%ProjectionRightDLeft=[];
%ProjectionLeftDRight=[];

Diagonals=(2*h)-1;

  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ///////////
  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  for di = 0:Diagonals
TempRDL =[];
		  if (di>=h)

             start=di-h+1;
             
          else
          
		  start=0;
          end

          endi=di-start;
             i =start;
             j=endi;
       	  
		  for  si = start:endi

				 
               TempRDL =[TempRDL   I(i+1,j+1)];
			  i=i+1;
			  j=j-1;
          end
          ProjectionRightDLeft(di+1)=sum(TempRDL);
         %dig
         
  end
  
  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \\\\\\\ %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  for di = 0:Diagonals
TEmpLDR=[];
		  if (di>=h)

             start=di-h+1;
              j=0;
          else
          
		  start=0;
          	  j=(h-1)-di;
          end

          endi=di-start;
           i =start;
            % j=endi;
       	  
		  for  si = start:endi

			 
              TEmpLDR=[TEmpLDR  I(i+1,j+1)];
			  i=i+1;
			  j=j+1;
          end
         
           ProjectionLeftDRight(di+1)=sum(TEmpLDR);
  end
	   
 	   
 


   
 