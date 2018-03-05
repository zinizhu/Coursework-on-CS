function a_solve
x = fsolve(@myfun,[0.9,0.9],optimset('Display','off'))
end

