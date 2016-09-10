function [feature]=chainCode64(strokes)

if length(strokes(:,1))==1
    feature=zeros(1,64);
    return;
end

k=1;
for i=1:4
    for j=1:4
        eval(['strokes_' int2str(k) '=[];']);
        k=k+1;
    end
end
k=1;
for i=1:4
    threshod1=max(strokes(:,2))-(i-1)*((max(strokes(:,2))-min(strokes(:,2)))/4);
    threshod2=max(strokes(:,2))-i*((max(strokes(:,2))-min(strokes(:,2)))/4);
    for j=1:4
        threshod3=min(strokes(:,1))+(j-1)*((max(strokes(:,1))-min(strokes(:,1)))/4);
        threshod4=min(strokes(:,1))+j*((max(strokes(:,1))-min(strokes(:,1)))/4);
        l=1;
        for s=1:length(strokes)
            if ( strokes(s,1)>=threshod3 && strokes(s,1)<=threshod4 && strokes(s,2)<=threshod1 && strokes(s,2)>=threshod2 )
                eval(['strokes_' int2str(k) '(l,:)=strokes(s,:);']);
                l=l+1;
            end
        end
        k=k+1;
    end
end



for i=1:16
    eval(['tempStroke=strokes_' int2str(i) ';']);
    if (isempty(tempStroke) || length(tempStroke(:,1))==1)
        eval(['feature_' int2str(i) '=zeros(1,4);']);
        continue;
    end
%     if i==13
%         keyboard
%     end
    tempFeatures=zeros(1,4);
    for j=1:length(tempStroke)-1
        angle=atan2((tempStroke(j+1,2)-tempStroke(j,2)),(tempStroke(j+1,1)-tempStroke(j,1)))*180/pi;
        if (angle<0)
            angle=angle+360;
        end
        if(angle<135&&angle>=45)
            tempFeatures(1)=tempFeatures(1)+1;
        elseif((angle>=0&&angle<45) || angle>=315)
            tempFeatures(2)=tempFeatures(2)+1;
        elseif(angle>=225 || angle<315)
            tempFeatures(3)=tempFeatures(3)+1;
        elseif(angle>=135 || angle<225)
            tempFeatures(4)=tempFeatures(4)+1;
        end
    end
    eval(['feature_' int2str(i) '=tempFeatures;']);
end

for i=1:16
    if (i==1)
        feature=feature_1;
    else
        eval(['feature=[feature feature_' int2str(i) '];']);
    end
end

end