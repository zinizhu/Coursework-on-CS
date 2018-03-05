function  [Delta_M_abs,Delta_X,Delta_C] = Monte_Carlo(Iteration,MatrixSize)


%initialization
J = 1;
      

%initialize the required parameters
X = [];                                   % record the magnetic susceptibility per site                     
C = [];                                   % record the heat capacity
M = [];                                   % record the m
M_abs = [];                               % record the absolute values of m
Delta_M_abs = [];                         % record the uncertainty of m_abs
Delta_X = [];                             % record the uncertainty of X
Delta_C = [];                             % record the uncertainty of C
t_0 = cputime;
T_1 = [];                                 % record the time
T_2 = [];
T_3 = [];

for kT = 1:0.5:3.5  

    
    %create the initial matrix
    Spin = sign(0.5 - rand(MatrixSize,MatrixSize)); 
   
    %Metropolis algorithm
    m = [];                               % record m at different iteration
    m_abs = [];                           % record m_abs at different iteration
    m_square = [];                        % record m_square at different iteration
    E = [];                               % record  E at different iteration
    E_square = [];                        % record E^2 at different iteration
    reportInterval = 100000;
    
    for i = 1 : Iteration
    %choose a random spin
        random_index = randi(numel(Spin));
        [row, col] = ind2sub(size(Spin), random_index);
    %calculate the energy change and total energy
        Right = circshift(Spin, [0,-1]);
        Below = circshift(Spin, [-1,0]);
        Left = circshift(Spin, [0,1]);
        Above = circshift(Spin, [1,0]);
        Total_energy_delta = Spin .* (Right + Below + Left + Above);
        dE = 2 * J * Total_energy_delta(row, col);
        
        if i > 500000
            Total_energy_matrice = Spin .* (Right + Below);
            Total_energy = - J * sum(Total_energy_matrice(:));  
            E = [E,Total_energy];
            E_square = [E_square, Total_energy^2];
        end
        
    % judgement
        prob = exp(-dE/(kT));
        if dE <= 0 || rand() <= prob
            Spin(row, col) = -Spin(row, col);
        end
        
    % record the new m in each iteration 
        if i > 500000
            m_1 = sum(Spin(:))/(MatrixSize^2);
            m_1_square = m_1^2;
            m = [m,m_1];
            m_abs = [m_abs, abs(m_1)];
            m_square = [m_square,m_1_square];
        end
        
    % monitoring the process
        if ~mod(i,reportInterval) 
                sprintf('present temperature: kT = %d, present iteration: %d \n',kT,i)
        end
        
    end
    
    T_1 = [T_1,(cputime-t_0)]
        
    
    % calculate the expectation values after all iterations under certain
    % temperature
    m_result = sum(m(:))/(Iteration-500000);
    m_abs_result = sum(m_abs(:))/(Iteration-500000);
    m_square_result = sum(m_square(:))/(Iteration-500000);
    E_result = sum(E(:))/(Iteration-500000);
    E_square_result = sum(E_square(:))/(Iteration-500000);
    

    M_abs = [M_abs, m_abs_result];
    M = [M,m_result];

    %calculate the uncertainty of m_abs and energy
    % the uncertainty of m_abs
    gm_0 = xcorr(m_abs,'unbiased');                                        % correlated statistics
    gm = (gm_0 - (m_abs_result)^2)/(m_square_result - (m_abs_result)^2);   % g matrix
    P = [];           % find out where g intersect x axis
    t = [-(Iteration-500000-1):(Iteration-500000-1)];
    plot([0:500],gm([(Iteration-500000):(Iteration-500000+500)]))    
    for i = [(Iteration-500000):(2*(Iteration-5000000)-10)]
        if gm(i+1) <= 0 && gm(i) >= 0
            P = [P,i];
        end   
    end
    p = P(1);         %pick up the first interception
    G = gm((Iteration-500000):(Iteration-700000+1000));  
    tao_m = abs(trapz(G));  %calculate the tao time
    trial_m = round(Iteration/(2*tao_m));  %n _trials
    m_abs_1 = m_abs - m_abs_result;
    m_1_square = m_abs_1 .* m_abs_1;
    delta_m_abs = sqrt(sum(m_1_square(:))/(Iteration*(trial_m-1)));
    Delta_M_abs = [Delta_M_abs,delta_m_abs];
        
    
    
    % the value and uncertainty of X
    m_boot = bootstrp((Iteration-500000),@(x)[mean(x) mean(x.^2)],m);      %expectation value of m and m^2 for each sample
    x = (1/kT)*(m_boot(:,2)-(m_boot(:,1).*m_boot(:,1)));               %x value for each sample 
    x_2 = x.*x;                                                            %x^2 value for each sample
    x_result = sum(x)/(Iteration-500000);                                  %mean of x under this temperature
    x_2_result = sum(x_2)/(Iteration -500000);                             %mean of x^2 under this temperature
    delta_x = sqrt(x_2_result-(x_result)^2);                               %delta of x under this temperature
    X = [X,x_result];                                                      %record x
    Delta_X = [Delta_X,delta_x];                                           %record delta_x   
    
    T_2 = [T_2, (cputime - t_0)];
    
    % the value and uncertainty of C
    
    %expectation value of E and E^2 for each sample
    E_boot = bootstrp((Iteration-500000),@(x)[mean(x) mean(x.^2)],E);  
    %c value for each sample 
    c = (1/kT^2)*(E_boot(:,2)-(E_boot(:,1).*E_boot(:,1)))/(MatrixSize^2); 
    %c^2 value for each sample
    c_2 = c.*c;    
    %mean of c under this temperature
    c_result = sum(c)/(Iteration-500000);  
    %mean of c^2 under this temperature
    c_2_result = sum(c_2)/(Iteration -500000); 
    %delta of c under this temperature
    delta_c = sqrt(c_2_result-(c_result)^2);
    %record c
    C = [C,c_result];      
    %record delta_c
    Delta_C = [Delta_C,delta_c];                                           
    
    
    T_3 = [T_3, (cputime - t_0)];
    
end
   
% plotting
  T = [1:0.5:3.5];
%  M_abs - T
  figure(1);
  errorbar(T,M_abs,Delta_M_abs)
  xlabel('temperature');
  ylabel('absolute magnetization per spin');
  hold on
  y = [1:0.02:3.5];
  Tc = 2/(log(1+sqrt(2)));
  t = Tc*ones(size(y));
  plot(t,y)
  legend('experiment','theory');
  
%   X - T
  figure(2);
  errorbar(T,X,Delta_X)
  xlabel('temperature');
  ylabel('susceptibilities');
  hold on
  
%   C - T
  figure(3);
  errorbar(T,C,Delta_C)
  xlabel('temperature');
  ylabel('heat capacity');
  hold on
  c_t = (2/pi)*(2/Tc)^2*(-log(1-y/Tc)+log(Tc/2)-(1+pi/4));
  plot(y,c_t)
  legend('experiment','theory')

end

