import java.util.Scanner; //Used to get the user input and to then process it.
import java.util.Arrays;

class ConvexHull {

	static int loadPoints(int maxPoints, double[] xVal, double[] yVal) {

                System.out.println("Please enter a list of coordinates: ");
                Scanner scan = new Scanner(System.in);
                String input = scan.nextLine();
                scan.close();

                Scanner numlist = new Scanner(input);
                int i = 0; int numPoints = 0;
                boolean first = true;
                double current = 0.0;
                while (numlist.hasNextDouble() && ((current = numlist.nextDouble()) >= 0) && (i+numPoints) < (2*maxPoints)) {
                        if (first) {
                                xVal[i] = current;
                                first = false;
                                i++;
                        } else {
                                yVal[numPoints] = current;
                                first = true;
                                numPoints++;
                        }
                }
		if ((i+numPoints) == (2*maxPoints)) {
			System.out.println("You have reached the limit of points!");
		}
		return(numPoints);
	}

	static boolean checkDuplicates(int pointCount, double xVal[], double yVal[]) {
		for (int i=pointCount-1; i>=1; i--) {
			for (int j=0; j<i; j++) {
				if(xVal[i] == xVal[j] && i != j && yVal[i] == yVal[j]) {
					System.out.println("There is a matching pair, the pair is: (" + xVal[i] + "," + yVal[i] + ") and (" + xVal[j] + "," + yVal[j] + ")");
					return true;
				}
			}
		}
		return false;
	}

	static void computeConvexHull(int pointCount, double xVal[], double yVal[]) {
		double m, c;
		print("computeConvexHull");
		for (int i=0; i < pointCount-1; i++) {
		        for (int j=i+1; j< pointCount; j++) {
							int above = 0, below = 0;
							print("computeConvexHull");
							m = (yVal[j]]-yVal[i])/(xVal[j]-xVal[i]);
							if (m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY) {
								print("NEGATIVE_INFINITY");
								if (z != i & z != j) {
									if (xVal[z] < xVal[i]) {
										below++;
									} if (xVal[z] < xVal[i]) {
										below++;
									}
								}
							} else {
								c = yVal[i] - m*xVal[i];
								for(z = 0; z < pointCount; z++) {
									if (yVal[z] < (m *  xVal[z]) + c) {
										below++;
									} if (yVal[z] > (m *  xVal[z]) + c) {
										above++;
									}
								}
							}
							if (above == 0 || below == 0) {
								System.out.println("(" + i + "," + j + ") is on the convex hull!");
							}
						}
				}

	public static void main(String[] args) {
		int maxPoints = 70;
                double xVal[] = new double[maxPoints];
                double yVal[] = new double[maxPoints];

		int pointCount = loadPoints(maxPoints, xVal, yVal);
		System.out.println("The pointCount value is: " + pointCount + ".");

		for(int i = 0; i < pointCount; i++) {
			System.out.println("(" + xVal[i] + "," + yVal[i] + ")");
		}

		if (checkDuplicates(pointCount, xVal, yVal)) {
			System.exit(0);
		} else {
			System.out.println("There are no duplicates.");
		}

		computeConvexHull(pointCount, xVal, yVal);
	}
}
