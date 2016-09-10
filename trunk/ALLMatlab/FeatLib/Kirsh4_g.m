function f= Kirsh4_g(I,h)

sublayer= cell(4,1);
% for i=1:4
%     sublayer{i}=zeros(20,20);
% end


horizontal_edge_mask_1= [5 5 5; -3 0 -3; -3 -3 -3];
horizontal_edge_mask_2= [-3 -3 -3; -3 0 -3; 5 5 5];
vertical_edge_mask_1= [5 -3 -3; 5 0 -3; 5 -3 -3];
vertical_edge_mask_2= [-3 -3 5; -3 0 5; -3 -3 5];
right_diagonal_edge_mask_1= [-3 -3 -3; 5 0 -3; 5 5 -3];
right_diagonal_edge_mask_2= [-3 5 5; -3 0 5; -3 -3 -3];
left_diagonal_edge_mask_1= [-3 -3 -3; -3 0 5; -3 5 5];
left_diagonal_edge_mask_2= [5 5 -3; 5 -3 -3; -3 -3 -3];

sublayer{1}= max(abs(conv2(I,horizontal_edge_mask_1,'same')),abs(conv2(I,horizontal_edge_mask_2,'same')));
sublayer{2}= max(abs(conv2(I,vertical_edge_mask_1,'same')),abs(conv2(I,vertical_edge_mask_2,'same')));
sublayer{3}= max(abs(conv2(I,right_diagonal_edge_mask_1,'same')),abs(conv2(I,right_diagonal_edge_mask_2,'same')));
sublayer{4}= max(abs(conv2(I,left_diagonal_edge_mask_1,'same')),abs(conv2(I,left_diagonal_edge_mask_2,'same')));


f=zeros(1,100);
counter=1;
for i=1:4
    sublayer{i}=conv2(sublayer{i}(5:24,5:24),h,'same');
    for x=3:4:20
        for y=3:4:20
            f(counter)= sublayer{i}(x,y);
            counter=counter+1;
        end
    end
end



% f=zeros(1,100);
% counter=1;
% for i=1:4
%     sublayer{i}=conv2(sublayer{i}(5:24,5:24),h,'same');
%     for x=4:3:16
%         for y=4:3:16
%             f(counter)= sublayer{i}(x,y);
%             counter=counter+1;
%         end
%     end
% end



