clc
clear
databaseloc=getADDataBasePath
AHDdatabase=[getADDataBasePath '\AHDBase\'] ;


avg_zero_bbox_area=0
counter=0;
for w=1:600% for all writer 600 in training set only 
    w
    for p=1:10
        for d=0:0
            
            if(w<=9)
                writer_no=['00' int2str(w)];
            elseif(w<=99)
                writer_no=['0' int2str(w)];
            else
                writer_no=int2str(w);
            end


            if(p<=9)
                pass_no=['0' int2str(p)];
            else
                pass_no=int2str(p);
            end

            if(w<=50)
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
            
            set='AHDBase_TrainingSet';
                    % file=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' set '\'...
               file=[AHDdatabase  set '\'...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
            
            
            I=double(imread(file)); % read image 
   
            [height width]=size(I);
            height*width 
            input('zeo:')
            % sum of box area 
            avg_zero_bbox_area= avg_zero_bbox_area + height*width; 
            counter=counter+1;

        end
    end
end
% save the average 
avg_zero_bbox_area= avg_zero_bbox_area/counter
% save avg_zero_bbox_area avg_zero_bbox_area


