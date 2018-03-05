function a_1(T)
Tm_A = 1000;
Tm_B = 1200;
Hm_A = 8000;
Hm_B = 9600;
R = 8.31;
x = [0.005:0.005:0.995];
as = 5000;

Sm_A = Hm_A/Tm_A;
Sm_B = Hm_B/Tm_B;
GA_l = Sm_A*(Tm_A - T);
GB_l = Sm_B*(Tm_B - T);

Gs = R*T*(x.*log(x)+(1-x).*log(1-x)) + 5000*x.*(1-x);
Gl = GA_l*(1-x) + GB_l*x + R*T*(x.*log(x)+(1-x).*log(1-x));

plot(x,Gs,x,Gl);
hold on;
xs =0.9538;    
xl =0.9158 ;
G_s = R*T*(xs*log(xs)+(1-xs)*log(1-xs)) + 5000*xs*(1-xs);
G_l = GA_l*(1-xl) + GB_l*xl + R*T*(xl*log(xl)+(1-xl)*log(1-xl));
k = (G_l-G_s)/(xl-xs);
Gll = k*(x-xl) + G_l;
plot(x,Gll)
xss = 0.1345;    
xll = 0.1678;
G_s2 = R*T*(xss*log(xss)+(1-xss)*log(1-xss)) + 5000*xss*(1-xss);
G_l2 = GA_l*(1-xll) + GB_l*xll + R*T*(xll*log(xll)+(1-xll)*log(1-xll));
k = (G_l2-G_s2)/(xll-xss);
Glll = k*(x-xll) + G_l2;
plot(x,Glll)
title("Gibbs Free Energy of A-B System(T=1050K)");
xlabel("x_B");
ylabel("Gibbs free energy(J/mol)");

y_1 = -14000:1:G_s2;
x_1 = xss*ones(size(y_1));
plot(x_1,y_1,'--')
y_2 = -14000:1:G_l2;
x_2 = xll*ones(size(y_2));
plot(x_2,y_2,'--')
y_3 = -25000:1:G_s;
x_3 = xs*ones(size(y_3));
plot(x_3,y_3,'--')
y_4 = -25000:1:G_l;
x_4 = xl*ones(size(y_4));
plot(x_4,y_4,'--')

legend("solid","liquid","common tangent")
end


