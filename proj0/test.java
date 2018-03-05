public class test{
  public static Planet[] readPlanets(String d){
    /** read the planets from the file.
    */
    In in = new In(d);

    int number = in.readInt();
    double radius = in.readDouble();
    Planet[] allPlanets = new Planet[number];
    for (int i = 1; i < number; i += 1){

      double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double mass = in.readDouble();
      String name = in.readString();

      Planet newplanet = new Planet(xPos, yPos, xVel, yVel, mass, name);
  /** newplanet.xxPos = xPos;
      newplanet.yyPos = yPos;;
      newplanet.xxVel = xVel;
      newplanet.yyVel = yVel;
      newplanet.mass = mass;
      newplanet.imgFileName = name; */

      allPlanets[i] = newplanet;
    }

    return allPlanets;
}
  public static void main(String[] args) {

    String d = "testfile.txt";

    System.out.println(readPlanets(d));
  }

}
