/*
* Line chart needs these to work...
* My general base for this came from https://docs.oracle.com/javafx/2/charts/line-chart.htm
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/*
* These were imported as due to an unknown amount of convex hull points being made I wanted to be able add them to a list imiditatly.
*/
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Ass2 extends Application {

  private static int maxPoints = 70;
  private static int pointCount;
  private static double xVal[] = new double[maxPoints];
  private static double yVal[] = new double[maxPoints];


   /*
   * Method to display the grapical output. Using a line scatter + scatter chart combo.
   */
   @Override
   public void start(Stage stage) throws Exception {

    /*
    * Fixing the data from computeConvexHull to usable x and y cords.
    */
    List<String> cords = ConvexHull.computeConvexHull(pointCount, xVal, yVal);
 		String[] values = new String[cords.size()];
 		cords.toArray(values);
 		double[] xCord = new double[cords.size()];
 		double[] yCord = new double[cords.size()];

 		for (int i = 0; i < values.length; i++) {

      /*
      * I know this is not the best code but i could not think of another way to do it,
      */
 			String tempcords = values[i];
 			String[] cordsarray = tempcords.split(",");
 			double[] doubleCords = Arrays.stream(cordsarray).mapToDouble(Double::parseDouble).toArray(); //https://stackoverflow.com/questions/9101301/how-to-convert-string-array-to-double-array-in-one-line

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

    XYChart.Series series = new XYChart.Series();

    List<XYChart.Series> SeriesList = new ArrayList<XYChart.Series>();

    for (int i = 0; i < xVal.length; i++) {
      if (! (xVal[i] == 0 && yVal[i] == 0)) {
        series.getData().add(new XYChart.Data(xVal[i], yVal[i]));
      }
    }
    lineChart.getData().add(series);
    lineChart.lookup(".default-color0.chart-series-line").setStyle("-fx-opacity: 0;"); //https://stackoverflow.com/questions/47947447/javafx-css-linechart

    for (int i = 0; i<xCord.length; i++) {
      XYChart.Series series1 = new XYChart.Series();
      series1.getData().add(new XYChart.Data(xCord[i], yCord[i]));
      series1.getData().add(new XYChart.Data(xCord[(i+1)], yCord[(i+1)]));
      SeriesList.add(series1);
      i++;
    }

    Scene scene  = new Scene(lineChart,1920,1080);

    for (int i = 0; i<SeriesList.size(); i++) {
      lineChart.getData().add(SeriesList.get(i));
    }

    stage.setScene(scene);
    stage.show();
	 }


   /*
   * Start of the program used to get user inputs.
   */
   public static void main(String[] args) {

 		 pointCount = ConvexHull.loadPoints(maxPoints, xVal, yVal);

     if (ConvexHull.checkDuplicates(pointCount, xVal, yVal)) {
 			 System.exit(0);
 		 }

     launch(args);
   }
}
