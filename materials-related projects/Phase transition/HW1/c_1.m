function  c_1
t = [1650:10:1800];
x = [1.650:0.01:1.800];
A1 = 38.91103; B1 = 0; C1 = 0; D1 = 0; E1 = 0;F1 = -2.722630;
y_1 = A1*x +0.5*B1.*x.^2 + (1/3)*C1*x.^3 + (1/4)*D1*x.^4 - E1./x + F1;
A2 = 16.49839; B2 = 18.74913; C2 = -6.639841; D2 = 1.717278; E2 = 1.872051;
F2 = -0.467675;
y_2 = A2*x +0.5*B2.*x.^2 + (1/3)*C2*x.^3 + (1/4)*D2*x.^4 - E2./x + F2;
plot(t,y_1,t,y_2)
legend("liquid","solid");
title("The molar enthalpy for solid and liquid phase of Ni");
xlabel("Temperature(K)");
ylabel("Enthalpy (kJ/mol)")
 


end

