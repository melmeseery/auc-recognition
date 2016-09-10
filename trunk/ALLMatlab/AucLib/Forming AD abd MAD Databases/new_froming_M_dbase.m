clc
clear
databaseloc=getADDataBasePath
AHDdatabase=[getADDataBasePath '\AHDBase\'] ;
MADdatabase=[getADDataBasePath '\MAHDBase\M'] ;
%%this program is to create the modified ahddatabase in the same formate as
%%of the miniset 
for w=1:700% for all writers 700
    w
    for p=1:10 % for all passes 
        for d=0:9 % for all digits 
            % convert the w number into three digit string 
            if(w<=9)
                writer_no=['00' int2str(w)];
            elseif(w<=99)
                writer_no=['0' int2str(w)];
            else
                writer_no=int2str(w);
            end % end if for   w 


            if(p<=9) % convert the 10 passes into two digit string 
                pass_no=['0' int2str(p)];
            else
                pass_no=int2str(p);
            end % end of if p 

            if(w<=50)  % switch on the part folder 
                % every 50 wirter are in different folder 
                part_no='Part01';
            elseif(w<=100)
                part_no='Part02';
            elseif(w<=150)
                part_no='Part03';
            elseif(w<=200)
                part_no='Part04';
            elseif(w<=250)
                part_no='Part05';
            elseif(w<=300)
                part_no='Part06';
            elseif(w<=350)
                part_no='Part07';
            elseif(w<=400)
                part_no='Part08';
            elseif(w<=450)
                part_no='Part09';
            elseif(w<=500)
                part_no='Part10';
            elseif(w<=550)
                part_no='Part11';
            elseif(w<=600)
                part_no='Part12';
            elseif(w<=650)
                part_no='Part01';
            else
                part_no='Part02';
            end
            % to change train and test set. 
            if(w<=600)
                set='AHDBase_TrainingSet';
            else
                set='AHDBase_TestingSet';
            end
            % now add create the fiel name 
           
     %       file=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' set '\'...
               file=[ AHDdatabase  set '\'...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
            
                % now read the image and store in image i 
            I=double(imread(file));
            % get the height and width of the image 
            [height width]=size(I);
            
            % get normalize hight or width to 20 
            if(height>width)  % 
                r=20;
                c= floor(width/height*20);
            elseif(height<width)
                c=20;
                r= floor(height/width*20);
            else
                r=20;
                c=20;
            end              
            % now resize image to the new using  Bicubic interpolation the
            % result will be new with new 20*20
            I=imresize(I,[r c],'bicubic');
            %[r c]=size(I);
            
            %BW = dither(I) converts the intensity image in the matrix I to
            %then invert its color 
            %then change it to double 
            Ic=double(~dither(I));
            %Measure properties (centroid) of image regions  
            P=regionprops(Ic,'Centroid');
            % get the cengtroid from the statics
            centroid=P.Centroid;
            %now get xc and yc 
            xc=round(centroid(1));
            yc=round(centroid(2));
            
%             Ic(yc,xc)=0.5;
%             subplot 211
%             imshow(Ic)
            
            
            distance_from_centroid_to_right= c-xc;
            distance_from_centroid_to_left= xc;
            distance_from_centroid_to_up= yc;
            distance_from_centroid_to_bottom= r-yc;
                       
            %
            I=[ones(r,14-distance_from_centroid_to_left) I ones(r,14-distance_from_centroid_to_right)];
            if(distance_from_centroid_to_right>14)
                for n=1:distance_from_centroid_to_right-14
                    I(:,end)=[];
                end
            end
            if(distance_from_centroid_to_left>14)
                for n=1:distance_from_centroid_to_left-14
                    I(:,1)=[];
                end
            end

            I=[ones(14-distance_from_centroid_to_up,28); I; ones(14-distance_from_centroid_to_bottom,28)];            
            if(distance_from_centroid_to_up>14)
                for n=1:distance_from_centroid_to_up-14
                    I(1,:)=[];
                end
            end
            if(distance_from_centroid_to_bottom>14)
                for n=1:distance_from_centroid_to_bottom-14
                    I(end,:)=[];
                end
            end           
             % file=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\M' set '\'...
             file=[MADdatabase set '\'...                        
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];

            %write the new file after modification 
            imwrite(I,file);

            [r c]=size(I);
            if(~and(r==28,c==28))
                error('wrong size!')
            end
            
%             size(I)
%             P=regionprops(double(~dither(I)),'Image');
%             size(P.Image)
%             %subplot 212
%             imshow(I)
%             input('zeo:')
%             close all
        end % end for digit 
    end % for passes 
end  % for the writers 

