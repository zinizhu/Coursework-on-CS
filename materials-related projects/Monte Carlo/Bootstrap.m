function Bootstrap
%choose n_trials data from the original set (with replacement)({m},{E})
%use this set to calculate perspective values(<m>,<m^2>,<E>,<E^2>)
%and then the properties(X,C)
%repete this process for n_max times(Iteration-500000)and get a group of
%X,C:X},{C}
%each temperautre correspond to an uncertainty

X_set = [];
X_2_set = [];
C_set = [];
C_2_set = [];

%m and X
for loop = 1: (Iteration - 700000)
    m_set = [];
    m_2_set = [];
    %select a set of data m 
    for i = 1:trial_m
        r = randi(trial_m);
        m_set = [m_set,m(r)];
        m_2_set = [m_2_set, (m(r))^2];
    end

    %calculate <m>,<m^2>
    m_set_result = sum(m_set(:))/trial_m;
    m_2_set_result = sum(m_2_set(:))/trial_m;

    %calculate X
    x_set = (1/kT)*(m_2_set_result - (m_set_result)^2);
    X_set = [X_set,x_set];
    X_2_set = [X_2_set,x_set^2];
end

%calculate the expectation of X
X_set_result = sum(X_set(:))/(Iteration - 700000);
X_2_set_result = sum(X_2_set(:))/(Iteration - 700000);
delta_X = sqrt(X_2_set_result-(X_set_result)^2);
Delta_X = [Delta_X,delta_X];
X = [X, X_set_result];


%E and C
for loop = 1: (Iteration_sys - 700000)
    E_set = [];
    E_2_set = [];
    %select a set of data E
    for i = 1:trial_e
        r = randi(trial_e);
        E_set = [E_set,E(r)];
        E_2_set = [E_2_set, (E(r))^2];
    end

    %calculate <E>,<E^2>
    E_set_result = sum(E_set(:))/trial_e;
    E_2_set_result = sum(E_2_set(:))/trial_e;

    %calculate C
    c_set = ((E_2_set_result) - (E_set_result)^2)/((kT)^2*(MatrixSize^2));
    C_set = [C_set,c_set];
    C_2_set = [C_2_set,c_set^2];
end

%calculate the expectation of C
C_set_result = sum(C_set(:))/(Iteration_sys - 500000);
C_2_set_result = sum(C_2_set(:))/(Iteration_sys - 500000);
delta_C = sqrt(C_2_set_result-(C_set_result)^2);
C = [C, C_set_result];
Delta_C = [Delta_C, delta_C];










    
