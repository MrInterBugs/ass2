import java.util.Scanner; //Used to get the user input and to then process it.

/*
* These were imported as due to an unknown amount of convex hull points being made I wanted to be able add them to a list imiditatly.
*/
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ConvexHull {

	/*
	* Method to get the user inputed coordinates and add them to the xVal and Yval list.
	*/

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

	/*
	* Method to check the user input had no duplicate coordinates.
	*/

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

	/*
	* Method to get the convex hull points from the list of coordinates.
	*/

	static List<String> computeConvexHull(int pointCount, double xVal[], double yVal[]) {

		double m, c;
		List<String> cords = new ArrayList<String>();
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
						if (z != i && z != j && xVal[z] < pI[0]) {
							below++;
						} else if (z != i & z != j && xVal[z] > pI[0]) {
							above++;
						}
					}

				} else {
						for(int z = 0; z < pointCount; z++) {
							if (z != i && z != j && yVal[z] < (m *  xVal[z] + c)) {
								below++;
							} else if (z != i & z != j && yVal[z] > (m *  xVal[z]) + c) {
								above++;
							}
						}
					}

					if ((above == 0 || below == 0)) {
						printCords(pJ[0], pJ[1], pI[0], pI[1]); //These were used just to make the code cleaner and easier to read.
						printLineEqation(m, c, pI[0]); //These were used just to make the code cleaner and easier to read.
						cords.add(pJ[0] + "," + pJ[1]);
						arraycount++;
						cords.add(pI[0] + "," + pI[1]);
						arraycount++;
					}

				}
			}
			return cords;
		}


	/*
	* Method to output which points go to which on the convex hull.
	*/
	static void printCords(double x2, double y2, double x1, double y1) {
		System.out.println("\nThe point (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ") is a point on the Convex Hull!");
	}

	/*
	* Method to print the equation off the line on the convex hull.
	*/
	static void printLineEqation(double m, double c, double x) {
		if (m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY) {
			System.out.println("With the line connecting them being: x = " + x);
		} else if (m == 0) {
			System.out.println("With the line connecting them being: y = " + c);
		} else {
			System.out.println("With the line connecting them being: y = " + m + "x + " + c);
		}
	}
}
