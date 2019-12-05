import java.util.Scanner; //Used to get the user input and to then process it.
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class ConvexHull {

	private static int counter;

	static void addOne() {
		counter++;
	}

	static int returnCount() {
		return counter;
	}

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
					System.out.println("This program will not run with matching pairs, please fix your input.");
					return true;
				}
			}
		}

		return false;
	}

	static Set<String> computeConvexHull(int pointCount, double xVal[], double yVal[]) {

		double m, c;
		Set<String> cords = new HashSet<String>();
		int arraycount = 0;

		for (int i=0; i < pointCount-1; i++) {

			double[] pI = new double[2];
			pI[0] = xVal[i];
			pI[1] = yVal[i];

	  	for (int j=i+1; j< pointCount; j++) {

				double[] pJ = new double[2];
				pJ[0] = xVal[j];
				pJ[1] = yVal[j];
				int above = 0, below = 0;

				m = (pJ[1]-pI[1])/(pJ[0]-pI[0]);
				c = pI[1] - m*pI[0];

				if (m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY) {
					for (int z = 0; z < pointCount; z++) {

						if (z != i & z != j && xVal[z] < pI[0]) {
							below++;
						} else if (z != i & z != j && xVal[z] > pI[0]) {
							above++;
						}
					}

				} else {
						for(int z = 0; z < pointCount; z++) {
							if (z != i & z != j && yVal[z] < (m *  xVal[z] + c)) {
								below++;
							} else if (z != i & z != j && yVal[z] > (m *  xVal[z]) + c) {
								above++;
							}
						}
					}

					if ((above == 0|| below == 0)) {
						printCords(pJ[0], pJ[1], pI[0], pI[1]); //These were used just to make the code cleaner and easier to read.
						printLineEqation(m, c, pI[0]); //These were used just to make the code cleaner and easier to read.
						cords.add(pJ[0] + "," + pJ[1]);
						arraycount++;
						cords.add(pI[0] + "," + pI[1]);
						arraycount++;
					}

				}
			}
			System.out.println("There are " + returnCount() + " unique points that make up to convex hull.");
			return cords;
		}

	static void printCords(double x2, double y2, double x1, double y1) {
		System.out.println("The point (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ") is a point on the Convex Hull!");
	}

	static void printLineEqation(double m, double c, double x) {
		if (m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY) {
			System.out.println("With the line connecting them being: x = " + x+ "\n");
			addOne();
		} else if (m == 0) {
			System.out.println("With the line connecting them being: y = " + c + "\n");
			addOne();
		} else {
			System.out.println("With the line connecting them being: y = " + m + "x + " + c + "\n");
			addOne();
		}
	}

	static double maxX (int pointCount, double[] xVal) {
		double max = 0;
		for (int i=0; i < pointCount; i++) {
			if (xVal[i] > max) {
				max = xVal[i];
			}
		}
		return max;
	}

	static double maxY (int pointCount, double[] yVal) {
		double max = 0;
		for (int i=0; i < pointCount; i++) {
			if (yVal[i] > max) {
				max = yVal[i];
			}
		}
		return max;
	}
}
