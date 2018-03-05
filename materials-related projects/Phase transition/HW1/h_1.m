function h_1
t = [1650:10:1800];
x = [1.650:0.01:1.800];
dS = 9.9292;
Tm = 1728;
dG_1 = dS * (Tm - t)/1000;
A1 = 38.91103; B1 = 0; C1 = 0; D1 = 0; E1 = 0;F1 = -2.722630;G1 = 74.47143;
A2 = 16.49839; B2 = 18.74913; C2 = -6.639841; D2 = 1.717278; E2 = 1.872051;F2 = -0.467675;G2 = 51.67575;
H_1 = A1*x +0.5*B1.*x.^2 + (1/3)*C1*x.^3 + (1/4)*D1*x.^4 - E1./x + F1;
H_2 = A2*x +0.5*B2.*x.^2 + (1/3)*C2*x.^3 + (1/4)*D2*x.^4 - E2./x + F2;
S_1 = A1*log(x) +B1.*x + (1/2)*C1*x.^2 + (1/3)*D1*x.^3 - E1./(2*x.^2) + G1;
S_2 = A2*log(x) +B2.*x + (1/2)*C2*x.^2 + (1/3)*D2*x.^3 - E2./(2*x.^2) + G2;
G_1 = H_1 - t.*S_1/1000;
G_2 = H_2 - t.*S_2/1000;
dG_2 = G_1 - G_2;
plot(t,dG_1,t,dG_2);
title("The deviation in Gibbs free energy of l/s phases");
xlabel("Temperature(K)");
ylabel("dG(s-l)");
legend("approximate results","exact results");


