 %%
 t=-3:0.01:3
 t2=-6:0.01:6
 x=(t>=-2 & t <=2) 
 
 h=sin(t*pi);

 y=conv(h,x);
 
 tlenght=length(t)
  xlenght=length(x)
   ylenght=length(y)
    t2lenght=length(t2)
 figure;
 subplot(3,1,1);
 plot(t,x);
  subplot(3,1,2);
 plot(t,h);
 
 
  subplot(3,1,3);
 plot(t2,y)
 
 %%%%%%%%%%%%%%5555
 
 %%
t= -2:0.01:2;
dt=0.01;
x=(t<=1 & t>=-1);
w0= pi/2;
T=4;
 
figure
plot (t,x);
 
for k=-100:100;
    a(k+101)=(1/T)*sum(x.*exp(-j*k*w0*t)*dt);
end
 
figure
stem (abs(a));
 
%% 
 
 
%k=-100:100;
 w0= pi/2;
 t=-100:0.01:100;
x2=zeros(size(t));

 
    for k=-100:100
      x2=x2+ (a(k+101).* exp(j*t*w0*k));
    end 
 
plot(t,x2)
axis([-5 5 -2 2])

 
%% 
k=-100:100;
 i=1;
 t3=-300:0.01:300;
 
for N=-300:0.01:300
    
x3(i)= sum(a.* exp(j*N*w0*k));
i=i+1;
end


plot(t3,x3)
%%
k=-5:5;
 
for N=-500:500
x3(N+501)= sum(a.* exp(j*N*w0*k));
end
 
plot(x3)

k=-5:5;
i=1;
t4=-100:0.01:100;
a5=[a(100-5:100+5)];
i=1;
for N=-100:0.01:100
    x4(i)=sum(exp(j*w0*N*k).*a5);
i= i+1;
end 
plot(t4,x4);
