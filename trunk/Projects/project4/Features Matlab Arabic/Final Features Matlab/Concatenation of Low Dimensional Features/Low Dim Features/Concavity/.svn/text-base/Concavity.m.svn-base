function f=Concavity(I)

concavity_map = Generate_Concavity_Map(I);
concavity_map= concavity_map(5:24,5:24);

f=zeros(1,15);
for i=1:15
    f(i)=sum(sum(concavity_map==i))/(20*20);
end



