function f= ConcavityVectorFE(I)

%finding white pixels locations
[y x]= find(~I);
% imshow(I)

N=length(y);

f=zeros(16,1);

if(N~=0)
    directions=zeros(1,4);
    for n=1:N
        directions=zeros(1,4);
        if(~isempty(find(I(y(n),x(n)+1:end))))
            directions(1)=1;
        end
        if(~isempty(find(I(y(n),1:x(n)-1))))
            directions(2)=1;
        end
        if(~isempty(find(I(1:y(n)-1,x(n)))))
            directions(3)=1;
        end
        if(~isempty(find(I(y(n)+1:end,x(n)))))
            directions(4)=1;
        end

        %     directions

        if(directions==[1 0 0 0])
            f(1)=f(1)+1;
        elseif(directions==[0 1 0 0])
            f(2)=f(2)+1;
        elseif(directions==[0 0 1 0])
            f(3)=f(3)+1;
        elseif(directions==[0 0 0 1])
            f(4)=f(4)+1;
        elseif(directions==[1 1 0 0])
            f(5)=f(5)+1;
        elseif(directions==[0 1 1 0])
            f(6)=f(6)+1;
        elseif(directions==[0 0 1 1])
            f(7)=f(7)+1;
        elseif(directions==[1 0 1 0])
            f(8)=f(8)+1;
        elseif(directions==[0 1 0 1])
            f(9)=f(9)+1;
        elseif(directions==[1 0 0 1])
            f(10)=f(10)+1;
        elseif(directions==[1 1 1 0])
            f(11)=f(11)+1;
        elseif(directions==[0 1 1 1])
            f(12)=f(12)+1;
        elseif(directions==[1 0 1 1])
            f(13)=f(13)+1;
        elseif(directions==[1 1 0 1])
            f(14)=f(14)+1;
        elseif(directions==[1 1 1 0])
            f(15)=f(15)+1;
        elseif(directions==[1 1 1 1])
            f(16)=f(16)+1;
        else
            continue;
        end
    end

    f=f/N;
end
