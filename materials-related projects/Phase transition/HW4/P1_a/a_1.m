function  a_1( Tc )
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

xc = 0.001:0.01:0.999;
dx = 1 - 2*xc;
H_mix = xc.*(1-xc).*(A0+A1*dx+A2*dx.^2+A3*dx.^3);
S_mixp = -( R*(xc.*log(xc)+(1-xc).*log(1-xc)) - xc.*(1-xc).*(B0+B1*dx+B2*dx.^2+B3*dx.^3) ); 
G_mixm = H_mix - T*S_mixp;


figure(1)
plot(xc,H_mix);
xlabel('X_B');
ylabel('Mixing Enthalpy (J/mol)');
title('Mixing Enthalpy vs Concentration(T = 1100^oC)');

figure(2)
plot(xc,S_mixp);
xlabel('X_B');
ylabel('Mixing Entropy (J/molK)');
title('Mixing Entropy vs Concentration(T = 1100^oC)');

figure(3)
plot(xc,G_mixm);
xlabel('X_B');
ylabel('Mixing Gibbs Free Energy (J/mol)');
title('Mixing Gibbs Free Energy vs Concentration(T = 1100^oC)');

figure(4)
plot(xc,G_mixm);
xlabel('X_B');
ylabel('Mixing Gibbs Free Energy (J/mol)');
title('Mixing Gibbs Free Energy vs Concentration(T = 1100^oC)');
hold on;
x1 = 0.1518;
x2 = 0.7527;
xm = 0.2442;
xp = 0.6013;
H_mix1 = x1*(1-x1)*(A0+A1*(1-2*x1)+A2*(1-2*x1)^2+A3*(1-2*x1)^3);
S_mix1 = -( R*(x1*log(x1)+(1-x1)*log(1-x1)) - x1*(1-x1)*(B0+B1*(1-2*x1)+B2*(1-2*x1)^2+B3*(1-2*x1)^3) ); 
G_mix1 = H_mix1 - T*S_mix1;
xcc = 0.7527;
dxc = 1 - 2*xcc;
H_mixc = xcc*(1-xcc)*(A0+A1*dxc+A2*dxc^2+A3*dxc^3);
S_mixc = -( R*(xcc*log(xcc)+(1-xcc)*log(1-xcc)) - xcc*(1-xcc)*(B0+B1*dxc+B2*dxc^2+B3*dxc^3) ); 
G_mixc = H_mixc - T*S_mixc;
H_mixm = xm*(1-xm)*(A0+A1*(1-2*xm)+A2*(1-2*xm)^2+A3*(1-2*xm)^3);
S_mixm = -( R*(xm*log(xm)+(1-xm)*log(1-xm)) - xm*(1-xm)*(B0+B1*(1-2*xm)+B2*(1-2*xm)^2+B3*(1-2*xm)^3) ); 
G_mixm = H_mixm - T*S_mixm
H_mixp = xp*(1-xp)*(A0+A1*(1-2*xp)+A2*(1-2*xp)^2+A3*(1-2*xp)^3);
S_mixp = -( R*(xp*log(xp)+(1-xp)*log(1-xp)) - xp*(1-xp)*(B0+B1*(1-2*xp)+B2*(1-2*xp)^2+B3*(1-2*xp)^3) ); 
G_mixp = H_mixp - T*S_mixp
slope = (G_mix1-G_mixc)/(x1-xcc);
y = slope*(xc-x1)+G_mix1;
plot(xc,y,'--');
yy3 = -1500:0.5:G_mixm;
xx3 = xm*ones(size(yy3));
yy4 = -1500:0.5:G_mixp;
xx4 = xp*ones(size(yy4));
plot(xx3,yy3,'--');
plot(xx4,yy4,'--');
yy1 = -1500:0.5:G_mix1;
xx1 = x1*ones(size(yy1));
yyc = -1500:0.5:G_mixc;
xxc = xcc*ones(size(yyc));
plot(xxc,yyc,'--');
plot(xx1,yy1,'--');
legend('Gibbs Free Energy','common tangent','spinodal composition 1','spinodal composition 2','miscibility gap 1','miscibility gap 2');

end

