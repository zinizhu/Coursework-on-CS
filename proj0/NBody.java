public class NBody {

    public static double readRadius(String d) {
        // read the radius from the file.
        In in = new In(d);
        double number = in.readDouble();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String d) {
        //read the planets from the file.
        In in = new In(d);

        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanets = new Planet[number];
        int i = 0;

        while (i < number) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();

            Planet newplanet = new Planet(xPos, yPos, xVel, yVel, mass, name);


            allPlanets[i] = newplanet;
            i += 1;
        }

        return allPlanets;

    }

    public static void main(String[] args) {
        // read in the input arguments.

        Double T = Double.parseDouble(args[0]);
        Double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        In in = new In(filename);
        int number = in.readInt();

        Planet[] allPlanets = readPlanets(filename);
        Double radius = readRadius(filename);

        String imageToDraw = "images/starfield.jpg";
        //set up the background

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show();

        //draw all planets using draw method in Planet.java.
        int i = 0;
        while (i < number) {
            Planet singlePlanet = allPlanets[i];
            singlePlanet.draw();
            i += 1;
        }

        StdDraw.show();

        // Animation.
        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time <= T) {
            double[] xForce = new double[number];
            double[] yForce = new double[number];

            for (int j = 0; j < number; j += 1) {
                Planet singlePlanet = allPlanets[j];
                xForce[j] = singlePlanet.calcNetForceExertedByX(allPlanets);
                yForce[j] = singlePlanet.calcNetForceExertedByY(allPlanets);
            }

            for (int j = 0; j < number; j += 1) {
                Planet singlePlanet = allPlanets[j];
                singlePlanet.update(dt, xForce[j], yForce[j]);
            }

            // draw the universe.
            StdDraw.picture(0, 0, imageToDraw);


            int m = 0;
            while (m < number) {
                Planet singlePlanet = allPlanets[m];
                singlePlanet.draw();
                m += 1;
            }

            StdDraw.show();

            StdDraw.pause(10);

            Time = Time + dt;
        }

        //print out the final result.
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int q = 0; q < allPlanets.length; q++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[q].xxPos, allPlanets[q].yyPos, allPlanets[q].xxVel,
                    allPlanets[q].yyVel, allPlanets[q].mass, allPlanets[q].imgFileName);
        }
    }
}
