function theta= real_arctan(dy,dx)

if(and(dx>=0,dy>=0))      
    theta= 180/pi*atan(dy/dx);
elseif(and(dx<=0,dy>=0))
    theta= 180- 180/pi*atan(abs(dy)/abs(dx));
elseif(and(dx<=0,dy<=0))
    theta= 180+ 180/pi*atan(abs(dy)/abs(dx));
elseif(and(dx>=0,dy<=0))
    theta= 360- 180/pi*atan(abs(dy)/abs(dx));
else
    error('error!!!')
end



