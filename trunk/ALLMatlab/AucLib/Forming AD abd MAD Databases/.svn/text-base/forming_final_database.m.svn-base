clc
clear
databaseloc=getADDataBasePath
inonelist=[getADDataBasePath '\one_list_digits\'] ;
newonelist=[getADDataBasePath '\new_one_list_digits\'] ;
% train_writers_list=1:700;
% test_writers_list=[];
% 
% for n=1:100
%     chosen=ceil(rand*length(train_writers_list));
%     test_writers_list=[test_writers_list train_writers_list(chosen)];
%     train_writers_list(chosen)=[];
% end
% 
% save('train_writers_list','train_writers_list');
% save('test_writer_list','train_writers_list');

%forming training set
for n=1:600
    n
    w=train_writers_list(n);
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

            if(w<=67)
                part_no='Part01';
            elseif(w<=115)
                part_no='Part02';
            elseif(w<=155)
                part_no='Part03';
            elseif(w<=200)
                part_no='Part04';
            elseif(w<=225)
                part_no='Part05';
            elseif(w<=300)
                part_no='Part06';
            elseif(w<=350)
                part_no='Part07';
            elseif(w<=400)
                part_no='Part08';
            elseif(w<=440)
                part_no='Part09';
            elseif(w<=500)
                part_no='Part10';
            elseif(w<=550)
                part_no='Part11';
            elseif(w<=600)
                part_no='Part12';
            elseif(w<=650)
                part_no='Part13';
            else
                part_no='Part14';
            end
            
          %source_file=['C:\Documents and Settings\ezzat\Desktop\one_list_digits\'
            source_file=[ inonelist ...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
            
            if(n<=9)
                writer_no=['00' int2str(n)];
            elseif(n<=99)
                writer_no=['0' int2str(n)];
            else
                writer_no=int2str(n);
            end


            if(n<=50)
                part_no='Part01';
            elseif(n<=100)
                part_no='Part02';
            elseif(n<=150)
                part_no='Part03';
            elseif(n<=200)
                part_no='Part04';
            elseif(n<=250)
                part_no='Part05';
            elseif(n<=300)
                part_no='Part06';
            elseif(n<=350)
                part_no='Part07';
            elseif(n<=400)
                part_no='Part08';
            elseif(n<=450)
                part_no='Part09';
            elseif(n<=500)
                part_no='Part10';
            elseif(n<=550)
                part_no='Part11';
            elseif(n<=600)
                part_no='Part12';
            elseif(n<=650)
                part_no='Part13';
            else
                part_no='Part14';
            end
            
            %destination_file=['C:\Documents and Settings\ezzat\Desktop\new_one_list_digits\'...
             destination_file=[ newonelist ...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
            
            copyfile(source_file,destination_file);
        
        end
    end
end


%forming testing set
for n=1:100
    w=test_writers_list(n);
    n=n+600
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

            if(w<=67)
                part_no='Part01';
            elseif(w<=115)
                part_no='Part02';
            elseif(w<=155)
                part_no='Part03';
            elseif(w<=200)
                part_no='Part04';
            elseif(w<=225)
                part_no='Part05';
            elseif(w<=300)
                part_no='Part06';
            elseif(w<=350)
                part_no='Part07';
            elseif(w<=400)
                part_no='Part08';
            elseif(w<=440)
                part_no='Part09';
            elseif(w<=500)
                part_no='Part10';
            elseif(w<=550)
                part_no='Part11';
            elseif(w<=600)
                part_no='Part12';
            elseif(w<=650)
                part_no='Part13';
            else
                part_no='Part14';
            end
            
          %  source_file=['C:\Documents and Settings\ezzat\Desktop\one_list_digits\'...
              source_file=[ inonelist ...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
            
            if(n<=9)
                writer_no=['00' int2str(n)];
            elseif(n<=99)
                writer_no=['0' int2str(n)];
            else
                writer_no=int2str(n);
            end


            if(n<=50)
                part_no='Part01';
            elseif(n<=100)
                part_no='Part02';
            elseif(n<=150)
                part_no='Part03';
            elseif(n<=200)
                part_no='Part04';
            elseif(n<=250)
                part_no='Part05';
            elseif(n<=300)
                part_no='Part06';
            elseif(n<=350)
                part_no='Part07';
            elseif(n<=400)
                part_no='Part08';
            elseif(n<=450)
                part_no='Part09';
            elseif(n<=500)
                part_no='Part10';
            elseif(n<=550)
                part_no='Part11';
            elseif(n<=600)
                part_no='Part12';
            elseif(n<=650)
                part_no='Part13';
            else
                part_no='Part14';
            end
            
        %    destination_file=['C:\Documents and Settings\ezzat\Desktop\new_one_list_digits\'...
              destination_file=[ newonelist ...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
            
            copyfile(source_file,destination_file);
        
        end
    end
end

            
