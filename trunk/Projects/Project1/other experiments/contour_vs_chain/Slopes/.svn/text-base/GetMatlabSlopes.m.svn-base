function profile=GetMatlabSlopes(I)

% I=imdilate(I,ones(2,2));
for n=1:6
    num_of_objects=max(max(bwlabel(I)));
    if(num_of_objects>1)
        %         'zeo'
        I=imdilate(I,ones(n,n));
    else
        break;
    end
end
% imshow(I)
% figure

[r c]=size(I);
initial_pos=[find(I(:,floor(c/2)),1) floor(c/2)];
B=bwtraceboundary(I,initial_pos,'N');

profile=[B(:,2) B(:,1)];
profile=diff(profile);

% Ib=bwperim(I);
% [r c]=size(B);
% for n=1:r
%     pos=B(n,:);
%     Ib(pos(1),pos(2))=0;
%     imshow(Ib);
% end