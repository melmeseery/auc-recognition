function x=RawImage_Wavelet_FE(I)

I=I(5:24,5:24);
I=imresize(I,[64 64]);

[C,S] = wavedec2(I,3,'db1');

x=C(1:64);

