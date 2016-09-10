function  I=preprocess(Image)

th=0.01; 

  m28=Image>th;  % 28*28 binary matrix of the digit
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
          %feat= tic; 
        I=m28(rb:re,cb:ce);             % binary matrix of the digit with H rows and W columns
%         H=re-rb+1; % hieght of the 
%         W=ce-cb+1; % width of the 