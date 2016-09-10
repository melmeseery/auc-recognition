function f= Local_Gradient_proj(I)


sublayer= cell(8,1);
for i=1:8
    sublayer{i}=zeros(20,20);
end



for x=5:24
    for y=5:24

        Gy= I(x-1,y-1)+2*I(x-1,y)+I(x-1,y+1)-I(x+1,y-1)-2*I(x+1,y)-I(x+1,y+1);
        Gx= I(x+1,y+1)+2*I(x,y+1)+I(x-1,y+1)-I(x+1,y-1)-2*I(x,y-1)-I(x-1,y-1);


        if(and(Gy,Gx)==0)
            continue;
        end
        theta= real_arctan(Gy,Gx);

        if(and(theta>=0,theta<=45))
            ux=1; uy=0; vx=1/sqrt(2); vy=1/sqrt(2); i=1; j=2;
        elseif(and(theta>=45,theta<=90))
            ux=1/sqrt(2); uy=1/sqrt(2); vx=0; vy=1; i=2; j=3;
        elseif(and(theta>=90,theta<=135))
            ux=0; uy=1; vx=-1/sqrt(2); vy=1/sqrt(2); i=3; j=4;
        elseif(and(theta>=135,theta<=180))
            ux=-1/sqrt(2); uy=1/sqrt(2); vx=-1; vy=0; i=4; j=5;
        elseif(and(theta>=180,theta<=225))
            ux=-1; uy=0; vx=-1/sqrt(2); vy=-1/sqrt(2); i=5; j=6;
        elseif(and(theta>=225,theta<=270))
            ux=-1/sqrt(2); uy=-1/sqrt(2); vx=0; vy=-1; i=6; j=7;
        elseif(and(theta>=270,theta<=315))
            ux=0; uy=-1; vx=1/sqrt(2); vy=-1/sqrt(2); i=7; j=8;
        elseif(and(theta>=315,theta<=360))
            ux=1/sqrt(2); uy=-1/sqrt(2); vx=1; vy=0; i=8; j=1;
        else
            error('error!!!')
        end

        %         if(or(uy*vx-ux*vy==0,ux==0))
        %             continue;
        %         end
        %
        %         b=(uy*Gx-ux*Gy)/(uy*vx-ux*vy);
        %         a=(Gx-b*vx)/ux;

        sol= inv([ux vx; uy vy])*[Gx; Gy];

        sublayer{i}(x-4,y-4)=sol(1);
        sublayer{j}(x-4,y-4)=sol(2);


    end
end


% Z=1-I;
% Z=Z(5:24,5:24);
% for p=5:4:20
%     Z(p,:)=0.5;
%     Z(:,p)=0.5;
% end
% subplot 341
% imshow(Z)

f=zeros(1,200);
counter=1;
for i=1:8

%     %     imshow(sublayer{i})
%     Z=1-sublayer{i};
%     for p=5:4:20
%         Z(p,:)=0.5;
%         Z(:,p)=0.5;
%     end
%     subplot(3,4,4+i)
%     imshow(Z)
%     title(int2str(i))

    for x=1:4:17
        for y=1:4:17
            F=mean(mean(sublayer{i}(x:x+3,y:y+3)));
            f(counter)= F;
            counter=counter+1;
        end
    end
end
% input('zeo:')
% close all


