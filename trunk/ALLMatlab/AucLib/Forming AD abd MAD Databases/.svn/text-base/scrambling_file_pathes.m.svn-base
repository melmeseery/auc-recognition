clc
clear
disp('Start the scrabbline files ')

AHDBase_TrainingSet_pathes=cell(60000,1);
AHDBase_TestingSet_pathes=cell(10000,1);

MAHDBase_TrainingSet_pathes=cell(60000,1);
MAHDBase_TestingSet_pathes=cell(10000,1);

training_set_labels=zeros(60000,1);
testing_set_labels=zeros(10000,1);


training_files_positions=1:60000;
random_training_files_positions=[];
disp('Now creating random positions')
% the loop create a map for (i , random_index ) to be used to scrample the
% training files name 
for n=1:60000  % for all training samples
    % create random index 
    r=ceil(rand*length(training_files_positions));  
    %create the pair  i , randome index 
    random_training_files_positions=[random_training_files_positions,  training_files_positions(r)]; 
    %remove the used position 
    training_files_positions(r)=[];
end


%Training Sets
count=0;
%creating the writer number 
for w=1:600  % for all 600 writer 
    w
    for p=1:10 % for all passes 
        for d=0:9 % for all digits 
            count=count+1;
             % this code to generate the id of writer as in files name 
			 % because 1 is written as 001 and 10 is written as 010  and 100 is written as 100. 
            if(w<=9)
                writer_no=['00' int2str(w)];
            elseif(w<=99)
                writer_no=['0' int2str(w)];
            else
                writer_no=int2str(w);  % only need to convert w to string . 
            end % end if w 
			
             % this code to generate the id of pases as in files name 
               % because 1 is written as 001 and 10 is written as 010 
            if(p<=9)  % for number of passes 
                pass_no=['0' int2str(p)];
            else
                pass_no=int2str(p);
            end  % end if p 
			% if conditions to  set part no.  each 50 writer are in same part
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

               % now create the file name for both adb and mahdbase 
			   % name is divided format is as following "AHDBase_TrainingSet\part_no\writer_003_pass_no_01_digit5.bmp"
         
            file_1=['AHDBase_TrainingSet\'...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];

            
            file_2=['MAHDBase_TrainingSet\'...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];
          
			% now set patches to  file locations to training set.	
            % random location used from the array filled earlier. 
            AHDBase_TrainingSet_pathes{random_training_files_positions(count)}=file_1;

            MAHDBase_TrainingSet_pathes{random_training_files_positions(count)}=file_2;

			% add this digits to the labels 
            training_set_labels(random_training_files_positions(count))=d;


        end % for d 0 to 9 
    end % for p : 1 to 10
end% end of for writers 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Now test set %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Testing Sets
count=0;
for w=601:700% using lest of writers from 600 to 700 as test set 
    w
    for p=1:10 % for all passes 
        for d=0:9% for all digit 
            count=count+1;
      % this code to generate the id of writer as in files name 
			 % because 1 is written as 001 and 10 is written as 010  and 100 is written as 100. 
            if(w<=9)% if 
                writer_no=['00' int2str(w)];
            elseif(w<=99)
                writer_no=['0' int2str(w)];
            else
                writer_no=int2str(w);
            end % end if w


            if(p<=9)
                   % this code to generate the id of pases as in files name 
               % because 1 is written as 001 and 10 is written as 010 
                pass_no=['0' int2str(p)];
            else
                pass_no=int2str(p);
            end % end if passes 

            if(w<=50)		% if conditions to  set part no.  each 50 writer are in same part
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
   % now create the file name for both adb and mahdbase 
			   % name is divided format is as following "AHDBase_TrainingSet\part_no\writer_003_pass_no_01_digit5.bmp"
 
            file_1=['AHDBase_TestingSet\'...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];

            file_2=['MAHDBase_TestingSet\'...
                part_no '\writer' writer_no '_pass' pass_no '_digit' int2str(d) '.bmp'];

            	% now set patches to  file locations to test set.	
            AHDBase_TestingSet_pathes{count}=file_1;

            MAHDBase_TestingSet_pathes{count}=file_2;

            testing_set_labels(count)=d;


        end  % for all digit 
    end % for passes 
end  % for w 

%save the data into fiels 
save('AHDBase_TrainingSet_pathes','AHDBase_TrainingSet_pathes');
save('AHDBase_TestingSet_pathes','AHDBase_TestingSet_pathes');

save('MAHDBase_TrainingSet_pathes','MAHDBase_TrainingSet_pathes');
save('MAHDBase_TestingSet_pathes','MAHDBase_TestingSet_pathes');

save('training_set_labels','training_set_labels');
save('testing_set_labels','testing_set_labels');




