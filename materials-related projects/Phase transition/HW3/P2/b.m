function b(T)
R = 8.31;
x = [0.005:0.005:0.995];

Gs = R*T*(x.*log(x)+(1-x).*log(1-x)) + 5000*x.*(1-x);
plot(x,Gs)
hold on
title("Gibbs Free Energy of A-B System for Solid(T=150K)");
xlabel("x_B");
ylabel("Gibbs free energy(J/mol)");
xs1 = 0.0210;    
xs2 = 0.9790;
Gs0 = R*T*(xs1*log(xs1)+(1-xs1)*log(1-xs1)) + 5000*xs1*(1-xs1);
y1 = Gs0*ones(size(x));
plot(x,y1,'--');
y2 = -500:0.01:Gs0;
x2 = xs1*ones(size(y2));
plot(x2,y2,'--');
y3 = -500:0.01:Gs0;
x3 = xs2*ones(size(y2));
plot(x3,y3,'--');
end

