function b(Tc)
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
T = Tc + 273;

H_mix(x) = x*(1-x)*(A0+A1*(1-2*x)+A2*(1-2*x)^2+A3*(1-2*x)^3);
S_mix(x) = -( R*(x*log(x)+(1-x)*log(1-x)) - x*(1-x).*(B0+B1*(1-2*x)+B2*(1-2*x)^2+B3*(1-2*x)^3) ); 
G_mix(x) = H_mix(x) - T*S_mix(x);
Gd_mix(x) = diff(G_mix(x),x);
f(x) = (G_mix(x)-x*Gd_mix(x))/(R*T);
A(x) = exp(f(x));

c = 0.001:0.01:0.999;
a = A(c);
plot(c,a);
hold on;
y = 1-c;
plot(c,y,'--');

xlabel('X_B');
ylabel('activity');
title('Activity of Cu');
legend('non-ideal','ideal')


end

