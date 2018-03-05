function plot_graphics(t)
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
    a = -2;
    b = -1;
    c = 4;
    k = 80;
    u = 30;
    
    Shear = 0.01*[0,a*t,0;a*t,0,0;0,0,0];
    Elongation = 0.01*[a*t,0,0;0,b*t,0;0,0,c*t];
    Compaction = 0.01*[0,0,0;0,b*t,0;0,0,0];
    Combined = 0.01*[a*t,b*t,0;b*t,c*t,0;0,0,1];
    
    
    %Shear
    figure(2);
    title('Deformation under Shear Stress (t = 10)');
    A = ((eye(3)+Shear)*[0 0 0]')';
    B = ((eye(3)+Shear)*[0.001 0 0]')';
    C = ((eye(3)+Shear)*[0.001 0.001 0]')';
    D = ((eye(3)+Shear)*[0 0.001 0]')';
    E = ((eye(3)+Shear)*[0 0 0.001]')';
    F = ((eye(3)+Shear)*[0.001 0 0.001]')';
    G = ((eye(3)+Shear)*[0.001 0.001 0.001]')';
    H = ((eye(3)+Shear)*[0 0.001 0.001]')';
    vertices_1 = [A;B;C;D;E;F;G;H];
    faces = [1 2 6 5; 2 3 7 6; 3 4 8 7; 4 1 5 8; 1 2 3 4; 5 6 7 8];
    p2 = patch('Vertices',vertices_1,'Faces',faces, 'FaceColor','y'); 
    view(-37.5, 30); 
    axis([0 0.0015 0 0.0015 0 0.0015]) ;
    grid on;
    
    p2.FaceVertexAlphaData = 0.3;    % Set constant transparency 
    p2.FaceAlpha = 'flat' ;          % Interpolate to find face transparency
 
    %Elongation/Shrinking
    figure(3);
    title('Deformation under Elongation Stress (t = 10)');
    A = ((eye(3)+Elongation)*[0 0 0]')';
    B = ((eye(3)+Elongation)*[0.001 0 0]')';
    C = ((eye(3)+Elongation)*[0.001 0.001 0]')';
    D = ((eye(3)+Elongation)*[0 0.001 0]')';
    E = ((eye(3)+Elongation)*[0 0 0.001]')';
    F = ((eye(3)+Elongation)*[0.001 0 0.001]')';
    G = ((eye(3)+Elongation)*[0.001 0.001 0.001]')';
    H = ((eye(3)+Elongation)*[0 0.001 0.001]')';
    vertices_1 = [A;B;C;D;E;F;G;H];
    faces = [1 2 6 5; 2 3 7 6; 3 4 8 7; 4 1 5 8; 1 2 3 4; 5 6 7 8];
    p3 = patch('Vertices',vertices_1,'Faces',faces, 'FaceColor','y'); 
    view(-37.5, 30); 
    axis([0 0.0015 0 0.0015 0 0.0015]) ;
    grid on;
    
    p3.FaceVertexAlphaData = 0.3;    % Set constant transparency 
    p3.FaceAlpha = 'flat' ;          % Interpolate to find face transparency
    
    % Compaction
    figure(4);
    title('Deformation under Compaction Stress (t = 10)');
    A = ((eye(3)+Compaction)*[0 0 0]')';
    B = ((eye(3)+Compaction)*[0.001 0 0]')';
    C = ((eye(3)+Compaction)*[0.001 0.001 0]')';
    D = ((eye(3)+Compaction)*[0 0.001 0]')';
    E = ((eye(3)+Compaction)*[0 0 0.001]')';
    F = ((eye(3)+Compaction)*[0.001 0 0.001]')';
    G = ((eye(3)+Compaction)*[0.001 0.001 0.001]')';
    H = ((eye(3)+Compaction)*[0 0.001 0.001]')';
    vertices_1 = [A;B;C;D;E;F;G;H];
    faces = [1 2 6 5; 2 3 7 6; 3 4 8 7; 4 1 5 8; 1 2 3 4; 5 6 7 8];
    p4 = patch('Vertices',vertices_1,'Faces',faces, 'FaceColor','y'); 
    view(-37.5, 30); 
    axis([0 0.0015 0 0.0015 0 0.0015]) ;
    grid on;
    
    p4.FaceVertexAlphaData = 0.3;    % Set constant transparency 
    p4.FaceAlpha = 'flat' ;          % Interpolate to find face transparency
    
    % Combined
    figure(5);
    title('Deformation under Combined Stress (t = 10)');
    A = ((eye(3)+Combined)*[0 0 0]')';
    B = ((eye(3)+Combined)*[0.001 0 0]')';
    C = ((eye(3)+Combined)*[0.001 0.001 0]')';
    D = ((eye(3)+Combined)*[0 0.001 0]')';
    E = ((eye(3)+Combined)*[0 0 0.001]')';
    F = ((eye(3)+Combined)*[0.001 0 0.001]')';
    G = ((eye(3)+Combined)*[0.001 0.001 0.001]')';
    H = ((eye(3)+Combined)*[0 0.001 0.001]')';
    vertices_1 = [A;B;C;D;E;F;G;H];
    faces = [1 2 6 5; 2 3 7 6; 3 4 8 7; 4 1 5 8; 1 2 3 4; 5 6 7 8];
    p5 = patch('Vertices',vertices_1,'Faces',faces, 'FaceColor','y'); 
    view(-37.5, 30); 
    axis([0 0.0015 0 0.0015 0 0.0015]) ;
    grid on;
    
    p5.FaceVertexAlphaData = 0.3;    % Set constant transparency 
    p5.FaceAlpha = 'flat' ;          % Interpolate to find face transparency

  end

