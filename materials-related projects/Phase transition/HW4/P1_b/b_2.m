function b_2(Tc)
syms y;
A0 = 27571.15;
A1 = 1494.4;
A2 = -7335.15;
A3 = -2151.0;
B0 = 0.340495;
B1 = 0.04114;
B2 = -7.082715;
B3 = -0.68795;
R = 8.31;
T = Tc + 273;
H_mix(y) = y*(1-y)*(A0+A1*(1-2*y)+A2*(1-2*y)^2+A3*(1-2*y)^3);
S_mix(y) = -( R*(y*log(y)+(1-y)*log(1-y)) - y*(1-y).*(B0+B1*(1-2*y)+B2*(1-2*y)^2+B3*(1-2*y)^3) ); 
G_mix(y) = H_mix(y) - T*S_mix(y);
Gd_mix(y) = diff(G_mix(y),y);
f(y) = (G_mix(y)+(1-y)*Gd_mix(y))/(R*T);
A(y) = exp(f(y));

c = 0.001:0.01:0.999;
a = A(c);
plot(c,a);
hold on;
m = c;
plot(c,m,'--');

xlabel('X_B');
ylabel('activity');
title('Activity of Pb')
legend('non-ideal','ideal')

end

