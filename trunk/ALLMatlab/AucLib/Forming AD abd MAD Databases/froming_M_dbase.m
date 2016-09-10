clc
clear
databaseloc=getADDataBasePath
AHDdatabase=[databaseloc '\AHDBase\'] ;
MADdatabase=[databaseloc '\MAHDBase\M'] ;
for w=500:700
    w
    for p=1:10
        for d=0:9
            
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
            elseif(w<=650)%%%%%%%%% they are in test set not training. 
                part_no='Part01';
            else
                part_no='Part02';
            end
            
            if(w<=600)
                set='AHDBase_TrainingSet';
            else
                set='AHDBase_TestingSet';
            end  
            % file=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' set '\'...
               % file=['C:\Documents and Settings\ezzat\Desktop\AHDBase\' set '\'...
               file=[ AHDdatabase  set '\'...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
            I=double(imread(file));
            
            [height width]=size(I);
            c=ceil(width/height*20);
            if(c>20)
                c=20;
            end
            I=imresize(I,[20 c],'bicubic');
            [r c]=size(I);
            
            Ic=~dither(I);
            P=regionprops(double(Ic),'Centroid');
            centroid=P.Centroid;
            xc=round(centroid(1));
            yc=round(centroid(2));
            


             Inew=ones(28,28);
             %i do not understand these lines ?????????????
             a=14-yc;
             if(a<1)
                 a=1;
             end
             b=a+r-1;
             if(b>28)
                 b=28;
             end
             e=14-xc;
             if(e<1)
                 e=1;
             end
             f=e+c-1;
             if(f>28)
                 f=28;
             end
             
             delta=28-(b-a+1);
             if(delta<0)
                 I(end-delta:end,:)=[];
             end
             delta=28-(f-e+1);
             if(delta<0)
                 I(:,end-delta:end)=[];
             end
             % this produce and error !!!!!!!!!!!!!!!!!!!!!!!!!!!
             Inew(a:b,e:f)=I;
              %   file=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\M' set '\'...                      
            % file=['C:\Documents and Settings\ezzat\Desktop\MAHDBase\M' set '\'...
             file=[MADdatabase set '\' part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];          
%                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
             
           
            imwrite(Inew,file);
        end
    end
end

