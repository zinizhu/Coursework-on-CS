public class Planet {
    /**
     * parameters
     */
    private double xxPos;
    private double yyPos;
    private double xxVel;
    private double yyVel;
    private double mass;
    private String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        // create a planet object
        xxPos = xP;
        xxVel = xV;
        yyPos = yP;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet p) {
        // create the same planet object
        xxPos = p.xxPos;
        xxVel = p.xxVel;
        yyPos = p.yyPos;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;

    }

    public double calcDistance(Planet p) {
        // calculate the distance between the two allPlanets
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double d = Math.sqrt(dx * dx + dy * dy);
        return d;
    }

    public double calcForceExertedBy(Planet p) {
        // calculate the force exerted on the planet.
        double G = 6.67 * Math.pow(10, -11);
        double d = calcDistance(p);
        double F = G * mass * p.mass / Math.pow(d, 2);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        // calculate the force on x-direction exerted on the planet.
        double dx = p.xxPos - xxPos;
        double Fx = dx * calcForceExertedBy(p) / calcDistance(p);
        return Fx;
    }

    public double calcForceExertedByY(Planet p) {
        // calculate the force on y-direction exerted on the planet.
        double dy = p.yyPos - yyPos;
        double Fy = dy * calcForceExertedBy(p) / calcDistance(p);
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        //calculate the force on x-direction exerted on the planet from all planets.
        double FxNet = 0;
        for (int i = 0; i < allPlanets.length; i += 1) {
            if (equals(allPlanets[i])) {
                continue;
            } else {
                FxNet = FxNet + calcForceExertedByX(allPlanets[i]);
            }
        }

        return FxNet;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        // calculate the force on y-direction exerted on the planet from all planets.
        double FyNet = 0;
        for (int i = 0; i < allPlanets.length; i += 1) {
            if (equals(allPlanets[i])) {
                continue;
            } else {
                FyNet = FyNet + calcForceExertedByY(allPlanets[i]);
            }
        }

        return FyNet;
    }

    public void update(double dt, double fX, double fY) {
        //  updata the velocity and position of the planet.
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + aX * dt;
        yyVel = yyVel + aY * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    public void draw() {
        // draw the planets.
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);

    }


}
