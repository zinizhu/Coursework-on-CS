function  f_2(Tc)
syms x
T = Tc + 273;
Tx = 1830 + 273;
R = 8.31;
W_l = 0;
Pd_l = 0;
u(x) = R*Tx*(log(x));
v(x) = R*Tx*(log(1-x));
W_fcc = double(u(0.08)-u(0.23));
Pd_fcc = double(v(0.08)-v(0.23));
W_bcc = double(u(0.08)-u(0.95));
Pd_bcc = double(v(0.08)-v(0.95));

c = 0.001:0.01:0.999;
G_l = R*T*(c.*log(c)+(1-c).*log(1-c));
G_Pd = c*W_fcc + (1-c).*Pd_fcc + R*T*(c.*log(c)+(1-c).*log(1-c));
G_W = c*W_bcc + (1-c).*Pd_bcc + R*T*(c.*log(c)+(1-c).*log(1-c));

plot(c,G_l);
hold on;
plot(c,G_Pd);
plot(c,G_W);
end

