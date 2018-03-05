function q = fun(p)
syms x;
A0 = 27571.15;
A1 = 1494.4;
A2 = -7335.15;
A3 = -2151.0;
B0 = 0.340495;
B1 = 0.04114;
B2 = -7.082715;
B3 = -0.68795;
R = 8.31;
T = 1100 + 273;

dx = 1 - 2*x;
H_mix(x) = x*(1-x)*(A0+A1*dx+A2*dx^2+A3*dx^3);
S_mix(x) = -( R*(x*log(x)+(1-x)*log(1-x)) - x*(1-x)*(B0+B1*dx+B2*dx^2+B3*dx^3) ); 
G_mix(x) = H_mix(x) - T*S_mix(x);
Gd_mix(x) = diff(G_mix(x),x);
Gdd_mix(x) = diff(Gd_mix(x),x);

q = double(Gdd_mix(p));



