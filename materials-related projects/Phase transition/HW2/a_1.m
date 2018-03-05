function a_1
T = 1550+273.15; 
T_Mg = 1890+273.15; 
T_Fe = 1205+273.15;
as = 10600;
al = -10000;
R = 8.31;
S_Mg = 52.7;
S_Fe = 59.9;
Gs_Fe = 0;
Gs_Mg = 0;
Gl_Mg = Gs_Mg + S_Mg * (T_Mg - T);
Gl_Fe = Gs_Fe + S_Fe * (T_Fe - T);

x = 0.001:0.01:0.999;
G_mix = R*T*(x.*log(x)+(1-x).*log(1-x));
Gl_mix = al*x.*(1-x) + R*T*(x.*log(x)+(1-x).*log(1-x));
Gs_mix = as*x.*(1-x) + R*T*(x.*log(x)+(1-x).*log(1-x));
G_s = Gs_mix;
G_l = Gl_Fe*x + Gl_Mg*(1-x) + Gl_mix;
plot(x, G_s, x, G_l)
hold on;
x1 = 0.0819;
x2 = 0.6320;
G1 = as*x1.*(1-x1)+R*T*(x1.*log(x1)+(1-x1).*log(1-x1));
G2 = al*x2.*(1-x2)+Gl_Fe*x2 + Gl_Mg*(1-x2)+ R*T*(x2.*log(x2)+(1-x2).*log(1-x2));
k = (G2-G1)/(x2-x1);
Common = k*(x-x1)+G1;
plot(x,Common,'--');
xlabel("x(Fe_2SiO_4)");
ylabel("Free energy(J/mol)");
title("Free Energy for Soid and Liquid Phases(T = 1550^oC)");
legend("solid","liquid","common tangent");
end


