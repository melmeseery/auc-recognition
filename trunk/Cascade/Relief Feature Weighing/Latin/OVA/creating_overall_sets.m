clear
clc


for k=1:6
    k
    load(['redcorn_training_set_part_' int2str(k)]);
    training_set_x= training_set;
    load(['dir_training_set_part_' int2str(k)]);
    training_set_xx= training_set;
    load(['grad_proj_training_set_part_' int2str(k)]);
    
    training_set=[training_set_x training_set_xx training_set];
    
    save(['training_set_part_' int2str(k)],'training_set');
          
end

clear
clc

load redcorn_testing_set
testing_set_x= testing_set;
load dir_testing_set
testing_set_xx= testing_set;
load grad_proj_testing_set

testing_set=[testing_set_x testing_set_xx testing_set];
save('testing_set','testing_set');

