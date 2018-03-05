function multiphysical

% Define all constant parameters
A1 = 2.8*(10^-4);       %Densification rate parameter
Td0 = 1.5*10^6;         %Densification threshold parameter
A2 = 1.2;               %Plasticity rate parameter
Ty0 = 42*10^6;          %Plasticity threshold parameter
k0 = 81*10^9;           %Dense bulk modulus
u0 = 24*10^9;           %Dense shear modulus
sigma0 = 10^5;          %Dense conductivity
P1 = 10^(-3);           %Thermal dependence softening parameter
P2 = 10^(-3);           %Thermal dependence conductivity parameter
P3 = 10^(-3);           %Thermal dependence densitification parameter
P4 = 10^(-3);           %Thermal dependence plastic yield parameter
a = 0.8;                %Joule heating absorption coefficient
b = 2 * 10^(-5);        %Coefficient of thermal expansion
p0 = 2323;              %Mass density
Ca = 875;               %Heat capacity
I = 10^7;               %Current
Ti = 300;               %Initial temp
di = 0.67;              %Initial densification parameter

% Define time parameters
dt = 10^(-4);
tmax = 1;

% Initialize variables for t = 0
Ep = 0;
d = di;                 %Densitification parameter
T = Ti;                 %Temperature
c11 = k0 + 4/3*u0;
c12 = k0 - 2/3*u0;
Ey0 = [c11 c12 c12 0 0 0;c12 c11 c12 0 0 0;c12 c12 c11 0 0 0; 0 0 0 u0 0 0;0 0 0 0 u0 0; 0 0 0 0 0 u0];     %Young's modulus
E_r = [0];
Et_r = [0];
Ee_r = [0];
T_r = [Ti];                    %Store temperature
Cauchy_r = [0];               %Store Cauchy Stress
Tr_r = [0];
Tx_r = [0];
d_r = [d];
Ep_r = [0];

% for loop
for t = dt:dt:tmax
    
    % Update variables
    sigma = (1-d)*sigma0*exp(-P2*(T-Ti)/Ti);    %Dense conductivity
    Td = (1-d)*Td0*exp(-P3*(T-Ti)/Ti);          %Densification threshold parametery
    Ty = (1-d)*Ty0*exp(-P4*(T-Ti)/Ti);          %Plasticity threshold parameter
    k = (1-d)*k0*exp(-P1*(T-Ti)/Ti);            %Bulk modulus
    u = (1-d)*u0*exp(-P1*(T-Ti)/Ti);            %Shear modulus
    %c11 = k + 4/3*u;
    %c12 = k - 2/3*u;
    %Ey = [c11 c12 c12 0 0 0;c12 c11 c12 0 0 0;c12 c12 c11 0 0 0; 0 0 0 u0 0 0;0 0 0 0 u0 0; 0 0 0 0 0 u0];     %Young's modulus
    Ey = (1-d)*Ey0*exp(-P1*(T-Ti)/Ti);
    
    % Calculate B,F,J
    B = [0 0 0;0 0 0;0 0 -0.15*t];              
    F = eye(3) + B;                             %Deformation coefficient
    J = det(F);                                 %Jacobian coefficient
    
    %Calculate E,Et,Ee
    E = 1/2*(F'*F-eye(3));                      %Theoretical strain
    Et = b*(T-Ti)*eye(3);                       %Thermal strain
    Ee = E - Ep - Et;                           %Real strain
    if mod(t,0.1) == 0
      E_r = [E_r,norm(E,'fro')];
      Ee_r = [Ee_r,norm(Ee,'fro')];
      Et_r = [Et_r,norm(Et,'fro')];
    end
    %Convert Ee
    Ee_v = [Ee(1,1),Ee(2,2),Ee(3,3),2*Ee(1,2),2*Ee(2,3),2*Ee(1,3)]';
    
    %Calculate S
    S_v = Ey*Ee_v;
    
    %Convert S
    S11 = S_v(1);
    S22 = S_v(2);
    S33 = S_v(3);
    S12 = S_v(4);
    S23 = S_v(5);
    S13 = S_v(6);
    
    S = [S11 S12 S13;S12 S22 S23;S13 S23 S33];
    
    %Calculate Cauchy Stress, Tr,Tx
    Cauchy = (1/J)*F*S*F';
    trc = trace(Cauchy)/3;
    Tr = trc*eye(3);
    Tx = Cauchy - Tr;
    if mod(t,0.1) == 0
      Cauchy_r = [Cauchy_r, norm(Cauchy,'fro')];
      Tr_r = [Tr_r, trc];
      Tx_r = [Tx_r, norm(Tx,'fro')];
    end
    
    %Determine&Calculate d_div
    if abs(trc) >= Td && trc <= 0 && d ~= 0
        d_div = -A1*(abs(trc)/Td - 1);
    else
        d_div = 0;
    end
    
    %Determine&Calculate Ep_div
    if norm(Tx,'fro') >= Ty
        pp = A2*(norm(Tx,'fro')/Ty - 1);
    else
        pp = 0;
    end
    e_div = pp*Tx/norm(Tx,'fro');
    Ep_div = J*inv(F)*e_div*inv(F');
    
    
    %Calculate temperature change
    M = S.*Ep_div;
    numerator1 = sum(M(:));
    numerator2 = 0.5*d_div*exp(-P1*(T-Ti)/Ti)*Ee_v'*Ey0*Ee_v;    
    numerator3 = -30;
    numerator4 = a*(J/sigma)*(I^2);
    numerator = numerator1 + numerator2 - numerator3 + numerator4;
    denominator1 = p0*Ca;
    denominator2 = b*trace(S);
    denominator3 = (1-d)/(2*Ti)*P1*exp(-P1*(T-Ti)/Ti)*Ee_v'*Ey0*Ee_v;
    denominator = denominator1 - denominator2 - denominator3;
    T_div = numerator/denominator;
          %Store d and Ep
    T =T + T_div*dt; 
    Ep = Ep + Ep_div*dt;
    d = d + d_div*dt;
    if d < 0
        d = 0;
    end
    if mod(t,0.1) == 0
      T_r = [T_r, T];
      d_r = [d_r, d];
      Ep_r = [Ep_r, norm(Ep,'fro')];
    end    
end

x = [0:0.1:1];
figure(1);
plot(x,Cauchy_r);
xlabel("Time(s)");
ylabel("Norm of Cauchy Stress(Pa)");
title("Norm of Cauchy Stress");

figure(2);
plot(x,Tr_r);
xlabel("Time(s)");
ylabel("Pressure(Pa)");
title("Pressure of the System");

figure(3);
plot(x,Tx_r);
xlabel("Time(s)");
ylabel("Norm of Deviatoric Stress(Pa)");
title("Norm of Deviatoric Stress");
        
       
figure(4);
plot(x,Ep_r);
xlabel("Time(s)");
ylabel("Norm of Plastic Strain");
title("The Norm of Plastic Strain");

figure(5);
plot(x,d_r);
xlabel("Time(s)");
ylabel("Densification Parameter");
title("Densification Parameter");

figure(6);
plot(x,T_r);
xlabel("Time(s)");
ylabel("Temperature(K)");
title("Temperature");

figure(7);
plot(x,Et_r);
xlabel("Time(s)");
ylabel("Norm of Thermal Strain");
title("The Norm of the Thermal Strain");
end

