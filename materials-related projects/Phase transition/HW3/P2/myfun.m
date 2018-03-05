function q = myfun(p)
Tm_A = 1000;
Tm_B = 1200;
Hm_A = 8000;
Hm_B = 9600;
R = 8.31;
as = 25000;
T = 650;

Sm_A = Hm_A/Tm_A;
Sm_B = Hm_B/Tm_B;
GA_l = Sm_A*(Tm_A - T);
GB_l = Sm_B*(Tm_B - T);


q(1) = R*T*(log(p(1))-log(1-p(1)))+as*(1-2*p(1)) - (-GA_l+GB_l+R*T*(log(p(2))-log(1-p(2))));
q(2) = R*T*(log(p(1))-log(1-p(1)))+as*(1-2*p(1)) - (R*T*(p(1)*log(p(1))+(1-p(1))*log(1-p(1))) + as*p(1)*(1-p(1))-(GA_l*(1-p(2)) + GB_l*p(2) + R*T*(p(2)*log(p(2))+(1-p(2))*log(1-p(2)))))/(p(1)-p(2));
end