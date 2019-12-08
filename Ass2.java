import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Ass2 extends Application {

  private static int maxPoints = 70;
  private static int pointCount;
  private static double xVal[] = new double[maxPoints];
  private static double yVal[] = new double[maxPoints];
  private static double maxX = 0;
  private static double maxY = 0;

  @Override
   public void start(Stage stage) throws Exception {

    List<String> cords = ConvexHull.computeConvexHull(pointCount, xVal, yVal);
 		String[] values = new String[cords.size()];
 		cords.toArray(values);
 		double[] xCord = new double[cords.size()];
 		double[] yCord = new double[cords.size()];

 		for (int i = 0; i < values.length; i++) {
 			String tempcords = values[i];
 			String[] cordsarray = tempcords.split(",");
 			double[] doubleCords = Arrays.stream(cordsarray).mapToDouble(Double::parseDouble).toArray(); //https://stackoverflow.com/questions/9101301/how-to-convert-string-array-to-double-array-in-one-line

 			if (maxX < doubleCords[0]) {
 				maxX = doubleCords[0];
 			}
 			if (maxY < doubleCords[1]) {
 				maxY = doubleCords[1];
 			}

 			xCord[i] = doubleCords[0];
 			yCord[i] = doubleCords[1];

 		}

    stage.setTitle("Aedan Lawrence");

    final NumberAxis x = new NumberAxis();
    final NumberAxis y = new NumberAxis();

    x.setLabel("X Axis");
    final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(x,y);
		stage.show();
    lineChart.setTitle("Convex Hull");
    XYChart.Series series1 = new XYChart.Series();
    series1.setName("Convex Hull Points");

    XYChart.Series series2 = new XYChart.Series();
    series2.setName("Path of the Hedgehog");

    for (int i = 0; i < xVal.length; i++) {
      if (! (xVal[i] == 0 && yVal[i] == 0)) {
        series2.getData().add(new XYChart.Data(xVal[i], yVal[i]));
      }
    }

    for (int i = 0; i < xCord.length; i++) {
       series1.getData().add(new XYChart.Data(xCord[i], yCord[i]));
    }


    Scene scene  = new Scene(lineChart,1920,1080);

    lineChart.getData().addAll(series2, series1);

    scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm()); https://stackoverflow.com/questions/26803380/how-to-combine-scatter-chart-with-line-chart-to-show-line-of-regression-javafx

    stage.setScene(scene);
    stage.show();
	 }

   public static void main(String[] args) {

 		 pointCount = ConvexHull.loadPoints(maxPoints, xVal, yVal);

     if (ConvexHull.checkDuplicates(pointCount, xVal, yVal)) {
 			 System.exit(0);
 		 }

     launch(args);
   }
}
