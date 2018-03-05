function [delta_X] = sim_boot

kT_sys = 2;
t = cputime;

rng default;
y = [1:300000];
m = bootstrp(1000,@(x)[mean(x) mean(x.^2)],y);
X = (1/kT_sys)*(m(:,2)-(m(:,1).*m(:,1)));
X_2 = X.*X;
X_result = sum(X)/1000;
X_2_result = sum(X_2)/1000;
delta_X = sqrt(X_2_result-(X_result)^2);

T = cputime - t


end

