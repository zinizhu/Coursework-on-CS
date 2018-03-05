function a_solve
x = fsolve(@myfun,[0.95,0.5],optimset('Display','off'))
end

