 function f= gmask_Local_Gradient_proj(I,h)
% I=im2double(image);
% variance = (sqrt(2)*3/pi)^2;
% h= Generate_Gaussian_Mask([5 5],variance);
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
        if(theta==360)
            theta=0;
        end

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
%             theta
            error('error!!!')
        end

        sol= inv([ux vx; uy vy])*[Gx; Gy];

        sublayer{i}(x-4,y-4)=sol(1);
        sublayer{j}(x-4,y-4)=sol(2);

    end
end



f=zeros(1,200);
counter=1;
for i=1:8
    sublayer{i}=conv2(sublayer{i},h,'same');
    for x=3:4:20
        for y=3:4:20
            f(counter)= sublayer{i}(x,y);
            counter=counter+1;
        end
    end
end


% f=zeros(1,200);
% counter=1;
% for i=1:8
%     sublayer{i}=conv2(sublayer{i},h,'same');
%     for x=5:3:17
%         for y=5:3:17
%             f(counter)= sublayer{i}(x,y);
%             counter=counter+1;
%         end
%     end
% end




