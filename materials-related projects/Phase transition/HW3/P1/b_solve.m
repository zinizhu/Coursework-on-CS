function b_solve
x = fsolve(@myfunb,[0.001,0.999],optimset('Display','off'))
end

