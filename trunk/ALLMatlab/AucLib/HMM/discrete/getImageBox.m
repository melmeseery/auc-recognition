function m=getImageBox(I)
%  this isn5
%---------------------------------------------
th=0.25;
        %m28=I;
        m28=I>th;  % 28*28 binary matrix of the digit
        %I=double(I).*m28;
        for i=1:28
            r=m28(i,:);
            if (sum(r)>0)
                rb=i;break;
            end
        end   
        for i=28:-1:1
            r=m28(i,:);
            if (sum(r)>0)
                re=i;break;
            end
        end   
        for i=1:28
            r=m28(:,i);
            if (sum(r)>0)
                cb=i;break;
            end
        end   
        for i=28:-1:1
            r=m28(:,i);
            if (sum(r)>0)
                ce=i;break;
            end
        end   
          feat= tic; 
        m=m28(rb:re,cb:ce);             % binary matrix of the digit with H rows and W columns
        