function [I]=processResizeImage(Image)


            

 
%             % get normalize hight or width to 20 
%             if(height>width)  % 
%                 r=20;
%                 c= floor(width/height*20);
%           
%             end              

            c=20;
            %[height width]=size(Image)
            Image=double(Image);
            % now resize image to the new using  Bicubic interpolation the
            % result will be new with new 20*20
            Ir=imresize(Image,[NaN  c],'bicubic');
            I=~dither(Ir);
            %[r c]=size(I);
           % level = graythresh(Ir)
            %BW = dither(I) converts the intensity image in the matrix I to
            %then invert its color 
            %then change it to double 
            %I=im2bw(Ir,level);

          %  [hi wi]=size(I);
           % hi
           % wi
%c1=0;c2=0;
%th=20;
 
%variance = (sqrt(2)*3/pi)^2;
%h= Generate_Gaussian_Mask([5 5],variance);
%--------------------------------------------

  %  this isn5
%---------------------------------------------
            I;
        %m28=I;
      %  m28=I>th;  % 28*28 binary matrix of the digit
        %I=double(I).*m28;
%         for i=1:hi
%             r=m28(i,:);
%             if (sum(r)>0)
%                 rb=i;break;
%             end
%         end   
%         for i=hi:-1:1
%             r=m28(i,:);
%             if (sum(r)>0)
%                 re=i;break;
%             end
%         end   
%         for i=1:wi
%             r=m28(:,i);
%             if (sum(r)>0)
%                 cb=i;break;
%             end
%         end   
%         for i=wi:-1:1
%             r=m28(:,i);
%             if (sum(r)>0)
%                 ce=i;break;
%             end
%         end   
%         
%         rb
%         re
%         cb
%         ce
%         m28
        