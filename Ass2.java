/*import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;*/
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ass2 /*extends Application*/{
  public double maxPointx;
  public double maxPointy;

  public static void main(String[] args) {

    int maxPoints = 70;
    double xVal[] = new double[maxPoints];
    double yVal[] = new double[maxPoints];

		int pointCount = ConvexHull.loadPoints(maxPoints, xVal, yVal);

    if (ConvexHull.checkDuplicates(pointCount, xVal, yVal)) {
			System.exit(0);
		}

    Set<String> cords = ConvexHull.computeConvexHull(pointCount, xVal, yVal);

    System.out.println(cords);

//    maxPointx = ConvexHull.maxX(pointCount, xVal);
//    maxPointy = ConvexHull.maxY(pointCount, yVal);

//    System.out.println(ConvexHull.maxX(pointCount, xVal) + ConvexHull.maxY(pointCount, yVal));

//    launch(args);

  }

/*  @Override //Tells the compiler where the starting point for javafx is.

  public void start(Stage Stage) throws Exception {

    NumberAxis xAxis = new NumberAxis(0, maxPointx, (maxPointx/10));
    xAxis.setLabel("X Axis");
    NumberAxis yAxis = new NumberAxis(0, maxPointy, (maxPointy/10));
    yAxis.setLabel("Y Axis");
  }*/
}
