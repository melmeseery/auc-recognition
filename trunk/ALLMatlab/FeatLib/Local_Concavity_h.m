function f=Local_Concavity_h(I,h)

concavity_map = Generate_Concavity_Map(I);

sublayer= cell(9,1);
for i=1:9
    sublayer{i}=zeros(size(concavity_map));
    sublayer{i}(concavity_map==i)=1;   
end

f=zeros(1,225);
counter=1;
for i=1:9
    sublayer{i}=conv2(sublayer{i}(5:24,5:24),h,'same');
    for x=3:4:20
        for y=3:4:20
            f(counter)= sublayer{i}(x,y);
            counter=counter+1;
        end
    end
end





