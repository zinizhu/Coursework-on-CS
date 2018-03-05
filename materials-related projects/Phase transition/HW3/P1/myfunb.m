function q = myfunb(p)
Tm_A = 1000;
Tm_B = 1200;
Hm_A = 8000;
Hm_B = 9600;
R = 8.31;
as = 5000;
T =150;

Sm_A = Hm_A/Tm_A;
Sm_B = Hm_B/Tm_B;
GA_l = Sm_A*(Tm_A - T);
GB_l = Sm_B*(Tm_B - T);


q = R*T*(log(p)-log(1-p))+as*(1-2*p);
end

