import java.util.Scanner;
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
	public static void main(String[] args) {
		int maxPoints = 70;
                double xVal[] = new double[maxPoints];
                double yVal[] = new double[maxPoints];

		int pointCount = loadPoints(maxPoints, xVal, yVal);
		System.out.println("The pointCount value is: " + pointCount + ".");

		for(int i = 0; i < pointCount; i++) {
			System.out.println("(" + xVal[i] + "," + yVal[i] + ")");
		}
	}
}
