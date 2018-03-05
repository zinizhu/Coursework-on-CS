function q = myfun(p)
T = 1850+273.15; 
T_Mg = 1890+273.15; 
T_Fe = 1205+273.15;
R = 8.31;
as = 10600;
al = -10000;
S_Mg = 52.7;
S_Fe = 59.9;
Gs_Fe = 0;
Gs_Mg = 0;
Gl_Mg = Gs_Mg + S_Mg * (T_Mg - T);
Gl_Fe = Gs_Fe + S_Fe * (T_Fe - T);

% fx = R*T*(x*log(x)+(1-x)*log(1-x));
% gy = Gl_Fe*p(2) + Gl_Mg*(1-p(2))+R*T*((y*log(p(2)))+(1-p(2))*log(1-p(2)));
% ffx = R*T*(log(x)-log(1-x));
% ggy = Gl_Fe-Gl_Mg + R*T*(log(y)-log(1-y));

q(1) = as*(1-2*p(1)) + R*T*(log(p(1))-log(1-p(1)))-(al*(1-2*p(2)) + Gl_Fe-Gl_Mg + R*T*(log(p(2))-log(1-p(2))));
q(2) = as*(1-2*p(1)) + R*T*(log(p(1))-log(1-p(1)))-   (as*p(1)*(1-p(1))+R*T*(p(1)*log(p(1))+(1-p(1))*log(1-p(1)))  -  (al*p(2)*(1-p(2)) + Gl_Fe*p(2) + Gl_Mg*(1-p(2))+R*T*((p(2)*log(p(2)))+(1-p(2))*log(1-p(2)))))/(p(1)-p(2));
end