function d_1
t = [1650:10:1800];
x = [1.650:0.01:1.800];
A1 = 38.91103; B1 = 0; C1 = 0; D1 = 0; E1 = 0;G1 = 74.47143;
y_1 = A1*log(x) +B1.*x + (1/2)*C1*x.^2 + (1/3)*D1*x.^3 - E1./(2*x.^2) + G1;
A2 = 16.49839; B2 = 18.74913; C2 = -6.639841; D2 = 1.717278; E2 = 1.872051;
G2 = 51.67575;
y_2 = A2*log(x) +B2.*x + (1/2)*C2*x.^2 + (1/3)*D2*x.^3 - E2./(2*x.^2) + G2;
plot(t,y_1,t,y_2)
legend("liquid","solid");
title("The molar entropy for solid and liquid phase of Ni");
xlabel("Temperature(K)");
ylabel("Entropy (J/mol*K)")


end

