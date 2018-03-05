function [a,b] = c2
syms x
R = 8.31;
T = 950 + 273;
u(x) = R*T*(log(x));
v(x) = R*T*(log(1-x));
lb = u(0.95)-u(0.08);
fb = u(0.95)-u(0.23);
a = double(lb);
b = double(fb);
p_lb = v(0.08)-v(0.95);
p_lf = v(0.08)-v(0.23);
c = double(p_lb);
d = double(p_lf);
end

