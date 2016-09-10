
clear
clc


disp('interclass distance analysis ... ')

load arabic_interclass_distance_measurements
load latin_interclass_distance_measurements

lillietest(latin_interclass_distance_measurements)
lillietest(arabic_interclass_distance_measurements)


latin_interclass_distance_measurements
arabic_interclass_distance_measurements

[h,significance,ci] = ttest2(latin_interclass_distance_measurements,arabic_interclass_distance_measurements,0.01,'both') 


disp('scattering analysis ... ')

load latin_scattering_measurements
load arabic_scattering_measurements

lillietest(latin_scattering_measurements)
lillietest(arabic_scattering_measurements)


latin_scattering_measurements
arabic_scattering_measurements

[h,significance,ci] = ttest2(latin_scattering_measurements,arabic_scattering_measurements,0.01,'both') 



