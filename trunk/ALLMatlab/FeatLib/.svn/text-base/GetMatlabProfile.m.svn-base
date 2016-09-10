function B =GetMatlabProfile(I)

% %I=imdilate(dither(I),ones(2,2));
% for n=1:6
%     num_of_objects=max(max(bwlabel(I)));
%     if(num_of_objects>1)
%         %         'zeo'
%         I=imdilate(I,ones(2,2));
%     else
%         break;
%     end
% end
% % imshow(I)
% % figure

L=bwlabel(I);
N=max(max(L));
if(N>1)
    areas=[];
    for n=1:N
        M=(L==n).*L;
        areas=[areas bwarea(M)];
    end
    [v ind_of_max]=max(areas);
    I=I.*(L==ind_of_max);
    P= regionprops(bwlabel(I),'Image');
    I=imresize(P.Image,[20 20]);
end
% imshow(I)
% input('zeo:')

% P= regionprops(double(I),'Image');
% I=P.Image;
%imshow(I)
c=size(I,2);
initial_pos=[find(I(:,floor(c/2)),1) floor(c/2)];

if(length(initial_pos)==1)
    initial_pos=[1 find(I(1,:),1)];
end

B=bwtraceboundary(I,initial_pos,'N');

