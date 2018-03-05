function graphics(t)
    %plot the graphics for the cube at t = 1,5,10;

    %t = 0
    vertices = [0 0 0; 0.001 0 0; 0.001 0.001 0; 0 0.001 0; 0 0 0.001; 0.001 0 0.001; 0.001 0.001 0.001; 0 0.001 0.001];
    faces = [1 2 6 5; 2 3 7 6; 3 4 8 7; 4 1 5 8; 1 2 3 4; 5 6 7 8];
    figure(1);
    p1 = patch('Vertices',vertices,'Faces',faces, 'FaceColor','r'); 
    view(-37.5, 30); 
    axis([0 0.0015 0 0.0015 0 0.0015]) ;
    title("undeformed cube");
    grid on;
   
    p1.FaceVertexAlphaData = 0.3;    % Set constant transparency 
    p1.FaceAlpha = 'flat' ;          % Interpolate to find face transparency

    %Deformation: initializing
    BB = [0 0 0;0 0 0;0 0 -0.15*t];
    
    
    %Shear
    title('Deformation cube');
    A = ((eye(3)+BB)*[0 0 0]')';
    B = ((eye(3)+BB)*[0.001 0 0]')';
    C = ((eye(3)+BB)*[0.001 0.001 0]')';
    D = ((eye(3)+BB)*[0 0.001 0]')';
    E = ((eye(3)+BB)*[0 0 0.001]')';
    F = ((eye(3)+BB)*[0.001 0 0.001]')';
    G = ((eye(3)+BB)*[0.001 0.001 0.001]')';
    H = ((eye(3)+BB)*[0 0.001 0.001]')';
    vertices_1 = [A;B;C;D;E;F;G;H];
    faces = [1 2 6 5; 2 3 7 6; 3 4 8 7; 4 1 5 8; 1 2 3 4; 5 6 7 8];
    p2 = patch('Vertices',vertices_1,'Faces',faces, 'FaceColor','y'); 
    view(-37.5, 30); 
    axis([0 0.0015 0 0.0015 0 0.0015]) ;
    grid on;
    
    p2.FaceVertexAlphaData = 0.3;    % Set constant transparency 
    p2.FaceAlpha = 'flat' ;          % Interpolate to find face transparency
 
end


