function deviation
%Deformation: initializing
    a = -2;
    b = -1;
    c = 4;
    k = 80;
    u = 30;
    Identity = eye(3);
    c1 = k + (4/3)*u;
    c2 = k - (2/3)*u;
    IE = [c1,c2,c2,0,0,0;c2,c1,c2,0,0,0;c2,c2,c1,0,0,0;0,0,0,u,0,0;0,0,0,0,u,0;0,0,0,0,0,u];

    %Shear
    D_Shear = [];
    for t = 1:10
        A_Shear = 0.01*[0,a*t,0;a*t,0,0;0,0,0];
        F_Shear = A_Shear + Identity;
        E_Shear = 0.5*(F_Shear'*F_Shear - Identity);
        E_V = [E_Shear(1,1),E_Shear(2,2),E_Shear(3,3),2*E_Shear(1,2),2*E_Shear(2,3),2*E_Shear(1,3)]';
        S_V = IE * E_V;
        S11 = S_V(1);
        S22 = S_V(2);
        S33 = S_V(3);
        S12 = S_V(4);
        S23 = S_V(5);
        S13 = S_V(6);
        S = [S11,S12,S13;S12,S22,S23;S13,S23,S33];
        J = det(F_Shear);
        Stress = (1/J)*(F_Shear*S*F_Shear');
        p = (1/3)*(Stress(1,1)+Stress(2,2)+Stress(3,3));
        D = Stress - p*Identity;
        d = norm(D,'fro');
        D_Shear = [D_Shear,d];
    end

    
    %Elongation
    D_Elong = [];
    for t = 1:10
        A_Elongation = 0.01*[a*t,0,0;0,b*t,0;0,0,c*t];
        F_Elongation = A_Elongation + Identity;
        E_Elongation = 0.5*(F_Elongation'*F_Elongation - Identity);
        E_V = [E_Elongation(1,1),E_Elongation(2,2),E_Elongation(3,3),2*E_Elongation(1,2),2*E_Elongation(2,3),2*E_Elongation(1,3)]';
        S_V = IE * E_V;
        S11 = S_V(1);
        S22 = S_V(2);
        S33 = S_V(3);
        S12 = S_V(4);
        S23 = S_V(5);
        S13 = S_V(6);
        S = [S11,S12,S13;S12,S22,S23;S13,S23,S33];
        J = det(F_Elongation);
        Stress = (1/J)*(F_Elongation*S*F_Elongation');
        p = (1/3)*(Stress(1,1)+Stress(2,2)+Stress(3,3));
        D = Stress - p*Identity;
        d = norm(D,'fro');
        D_Elong = [D_Elong,d];
    end
    
    
    %Compaction
    D_Comp = [];
    for t = 1:10
        A_Compaction = 0.01*[0,0,0;0,b*t,0;0,0,0];
        F_Compaction = A_Compaction + Identity;
        E_Compaction = 0.5*(F_Compaction'*F_Compaction - Identity);
        E_V = [E_Compaction(1,1),E_Compaction(2,2),E_Compaction(3,3),2*E_Compaction(1,2),2*E_Compaction(2,3),2*E_Compaction(1,3)]';
        S_V = IE * E_V;
        S11 = S_V(1);
        S22 = S_V(2);
        S33 = S_V(3);
        S12 = S_V(4);
        S23 = S_V(5);
        S13 = S_V(6);
        S = [S11,S12,S13;S12,S22,S23;S13,S23,S33];
        J = det(F_Compaction);
        Stress = (1/J)*(F_Compaction*S*F_Compaction');
        p = (1/3)*(Stress(1,1)+Stress(2,2)+Stress(3,3));
        D = Stress - p*Identity;
        d = norm(D,'fro');
        D_Comp = [D_Comp,d];
    end
    
    
    %Combined
    D_Comb = [];
    for t = 1:10
        A_Combined = 0.01*[a*t,b*t,0;b*t,c*t,0;0,0,1];
        F_Combined = A_Combined + Identity;
        E_Combined = 0.5*(F_Combined'*F_Combined - Identity);
        E_V = [E_Combined(1,1),E_Combined(2,2),E_Combined(3,3),2*E_Combined(1,2),2*E_Combined(2,3),2*E_Combined(1,3)]';
        S_V = IE * E_V;
        S11 = S_V(1);
        S22 = S_V(2);
        S33 = S_V(3);
        S12 = S_V(4);
        S23 = S_V(5);
        S13 = S_V(6);
        S = [S11,S12,S13;S12,S22,S23;S13,S23,S33];
        J = det(F_Combined);
        Stress = (1/J)*(F_Combined*S*F_Combined');
        p = (1/3)*(Stress(1,1)+Stress(2,2)+Stress(3,3));
        D = Stress - p*Identity;
        d = norm(D,'fro');
        D_Comb = [D_Comb,d];
    end


    t = 1:10;
    plot(t,D_Shear);
    hold on;
    plot(t,D_Elong);
    plot(t,D_Comp);
    plot(t,D_Comb);
    title('Norm of The Deviation Stress at Different Time');
    xlabel('Time');
    ylabel('Norm');
    legend('Shear','Elongation','Compaction','Combined');

end

