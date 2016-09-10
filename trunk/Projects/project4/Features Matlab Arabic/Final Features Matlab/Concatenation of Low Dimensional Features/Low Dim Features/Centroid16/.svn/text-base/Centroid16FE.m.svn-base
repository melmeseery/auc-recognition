function x = Centroid16FE(window)

P= regionprops(double(dither(window)),'Image');
window=P.Image;

%f2=imfeature(uint8(window),'Centroid');
f2=regionprops(uint8(window),'Centroid');
Xmean= uint8(f2.Centroid(1));
Ymean= uint8(f2.Centroid(2));
[Ymax,Xmax]=size(window);

mask{1}=roipoly(window,[Xmean,Xmax,Xmax],[Ymean,Ymean,floor(Ymean/2)]);
mask{2}=roipoly(window,[Xmean,Xmax,Xmax],[Ymean,floor(Ymean/2),0]);
mask{3}=roipoly(window,[Xmean,Xmax,floor((Xmax-Xmean)/2)],[Ymean,0,0]);
mask{4}=roipoly(window,[Xmean,floor((Xmax-Xmean)/2),Xmean],[Ymean,0,0]);
mask{5}=roipoly(window,[Xmean,Xmean,floor(Xmean/2)],[Ymean,0,0]);
mask{6}=roipoly(window,[Xmean,floor(Xmean/2),0],[Ymean,0,0]);
mask{7}=roipoly(window,[Xmean,0,0],[Ymean,0,floor(Ymean/2)]);
mask{8}=roipoly(window,[Xmean,0,0],[Ymean,floor(Ymean/2),Ymean]);
mask{10}=roipoly(window,[Xmean,0,0],[Ymean,floor(Ymax/2),Ymax]);
mask{11}=roipoly(window,[Xmean,0,floor(Xmean/2)],[Ymean,Ymax,Ymax]);
mask{12}=roipoly(window,[Xmean,floor(Xmean/2),Xmean],[Ymean,Ymax,Ymax]);
mask{13}=roipoly(window,[Xmean,Xmean,floor((Xmax-Xmean)/2)],[Ymean,Ymax,Ymax]);
mask{14}=roipoly(window,[Xmean,floor((Xmax-Xmean)/2),Xmax],[Ymean,Ymax,Ymax]);
mask{15}=roipoly(window,[Xmean,Xmax,Xmax],[Ymean,Ymax,floor((Ymax-Ymean)/2)]);
mask{16}=roipoly(window,[Xmean,Xmax,Xmax],[Ymean,floor((Ymax-Ymean)/2),Ymean]);



for n=1:16
    if(isempty(mask{n}))
        iXmean(n)=Xmean;
        iYmean(n)=Ymean;
    else
        mwindow{n}=double(mask{n}).*double(window);
%         figure
%         n
%         imshow(mwindow{n})
%         zeo=input('zeo')
        % f=imfeature(uint8(mwindow{n}),'Centroid');
        f=regionprops(uint8(mwindow{n}),'Centroid');


        if isempty(f)
            iXmean(n)=Xmean;
            iYmean(n)=Ymean;
        else
            iXmean(n)=uint8(f.Centroid(1));
            iYmean(n)=uint8(f.Centroid(2));

        end

    end
        Xmean=double(Xmean);
        Ymean=double(Ymean);
        iXmean=double(iXmean);
        iYmean=double(iYmean);

        diameter=sqrt(Xmax^2+Ymax^2);

        x(n,1)=(sqrt((Xmean-iXmean(n))^2 + (Ymean-iYmean(n))^2))/diameter;

end % end of n loop