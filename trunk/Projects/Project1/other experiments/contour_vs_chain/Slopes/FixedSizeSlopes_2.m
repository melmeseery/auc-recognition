function profile=FixedSizeSlopes_2(I,n,m)

% pro=GetProfile(I);
pro=GetMatlabSlopes(I);
x_profile=pro(:,1);
y_profile=pro(:,2);


rem= ceil(length(x_profile)/n)*n-length(x_profile);
for r=1:rem
    L=length(x_profile);
    a=ceil(rand*(L-2))+1;
    x_profile=[x_profile(1:a); mean([x_profile(a) x_profile(a+1)]); x_profile(a+1:end)];
end

rem= ceil(length(y_profile)/m)*m-length(y_profile);
for r=1:rem
    L=length(y_profile);
    a=ceil(rand*(L-2))+1;
    y_profile=[y_profile(1:a); mean([y_profile(a) y_profile(a+1)]); y_profile(a+1:end)];
end

x_segment_length=length(x_profile)/n;
y_segment_length=length(y_profile)/m;

profile=[];
for k=1:n
    elem= mean(x_profile((k-1)*x_segment_length+1:k*x_segment_length));
    profile=[profile; elem];
end

for k=1:m
    elem= sum(y_profile((k-1)*y_segment_length+1:k*y_segment_length))/y_segment_length;
    profile=[profile; elem];
end


profile=(profile+1)/2;
