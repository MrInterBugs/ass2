public class Ass2 {
  public static void main(String[] args) {

    int maxPoints = 70;
    double xVal[] = new double[maxPoints];
    double yVal[] = new double[maxPoints];

		int pointCount = ConvexHull.loadPoints(maxPoints, xVal, yVal);

    if (ConvexHull.checkDuplicates(pointCount, xVal, yVal)) {
			System.exit(0);
		}

    ConvexHull.computeConvexHull(pointCount, xVal, yVal);
  }
}
