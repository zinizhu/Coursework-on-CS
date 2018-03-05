function [x] = monitor(Iteration)
% monitoring
x = 0;
for kT = 1:10
    reportInterval = 100000;
    for i = 1:Iteration
        x = x + i;
        
        if ~mod(i,reportInterval) && mod(kT,2) == 0
            sprintf('present temperature: kT = %d, present iteration: %d \n',kT,i)
        end  
    end
end

        
