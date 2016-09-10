function [VerticalTransitions, HorizontalTransitions ,DigRLTransitions,DigLRTransitions]=Transitions(I)
VerticalTransitions=[];
HorizontalTransitions =[];

[h,w]=size(I);


 VerticalTransitions=RowTransitions(I);
 HorizontalTransitions=RowTransitions(I');
 
 %%%% get the diagonal matrix....
 Diagonals=(2*h)-1;
 DMat=zeros( Diagonals);
  r=1;
  c=1;
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
               DMat(r,c)= I(i+1,j+1);
               c=c+1;
			  i=i+1;
			  j=j-1;
          end
          
          ProjectionRightDLeft(di+1)=sum(TempRDL);
         %dig
         r=r+1;
         c=1;
  end
   DigRLTransitions=RowTransitions(DMat);
  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \\\\\\\ %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  
   DMat=zeros( Diagonals);
  r=1;
  c=1;
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

			 DMat(r,c)= I(i+1,j+1);
              TEmpLDR=[TEmpLDR  I(i+1,j+1)];
              c=c+1;
			  i=i+1;
			  j=j+1;
          end
%           dig
           ProjectionLeftDRight(di+1)=sum(TEmpLDR);
           
         r=r+1;
         c=1;
  end
	   
 	      DigLRTransitions=RowTransitions(DMat);
 

