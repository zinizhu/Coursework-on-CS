function b_solve
  x = fsolve(@myfun,[0.2,0.8],optimset('Display','off'))


end

