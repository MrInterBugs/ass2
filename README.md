# Ass2

INSTRUCTIONS

To compile and run the Graph.java using javafx the commands are:

  javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls ConvexHull.java

  java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls ConvexHull

CRITERIA

Java Assessment Project 2

Hunting hedgehogs

In 1994, one of us was approached by a world authority on hedgehogs to help
write a program for his student labs. The idea was to find out the range
hedgehogs cover in the course of a night. The experiment involved releasing a
hedgehog into the wild after it had been fitted with a small radio transmitter.
Readings of the hedgehog’s position were taken at regular intervals as coordinate
pairs. Back in the lab, the students plotted the points on graph paper, and then
drew the enclosing convex polygon. The area of the polygon was taken as the
range for that particular hedgehog.

In computational geometry this problem is called the Convex Hull problem,
and there are a variety of algorithms available to solve it. We are going to
introduce you to the simplest one, which takes time cubic in the number of
observations to run. We call it an O(n^3) solution.

In Parts 1-3 we are concentrating on reading the list of coordinate pairs into
the computer. In Part 4 you will calculate the convex hull of the points.
B.2 Setting up

Create a directory ass2 in your javaProgramming directory and begin editing
a program file containing the class Ass2

teaching$ cd javaProgramming
teaching$ mkdir ass2
teaching$ cd ass2
teaching$ emacs Ass2.java &

B.3 Preparing the data [10 marks]

Edit a new Java source file which will hold class ConvexHull.

teaching$ emacs ConvexHull.java &

In the class ConvexHull write a method loadPoints() which takes two
arrays xVal and yVal of doubles and an integer maxPoints and returns an
integer, numPoints.
 
 loadPoints should read values from the input line alternately into xVal
 and yVal until a negative value is read or until greater than 2∗maxPoints
 are read. In the latter case, print out a message warning the user that
 the maximum capacity of the array has been reached.
 
 The method should return the number of points read into yVal.

Here is a skeleton starting point for the required class ConvexHull.java.

import java.util.Scanner;

class ConvexHull {
static int loadPoints(
 int maxPoints, double[] xVal, double[] yVal) {
  /* put your code in here */
 }
}

When you have finished, make sure that your class compiles without errors.
Test your loadPoints method by writing a main method in the class ConvexHull.
The main method should
 Declare an integer variable maxPoints and initialise it to 70.
 Declare two arrays xVal and yVal of size maxPoints which contain doubles.

 Call loadPoints(maxPoints, xVal, yVal) and write the value it re-
turns to an integer variable pointCount and then print out this value.

 Print out the points entered as pairs in the form (xV al[i], yV al[i]).
Compile and run your program and enter the values 1 5 6 8 3 -4
B.4 Finding a convex hull
Recall that every pair, (p1, p2) of distinct points in the plane lies on a unique
line, which has slope m =
(y1−y2)
(x1−x2)
.

If p = (x1, y1) and p2 = (x2, y2) then the equation of this line is either
1. x = c, where c = x1, if x1 = x2, or
2. y = mx + c, where c = y1 − mx1, if x1 6= x2.
For the purposes of this assignment, the Convex Hull of a set P of points
in the plane is the set of edges (p1, p2) between pairs of points in P such that
either none of the points in P lie above the line defined by p1 and p2, or none
of the points lie below the line defined by p1 and p2.
For example, for the following set of points, no points lie below the line
defined by p1 and p2, and no points lie above the line defined by p3 and p4.

1 12 23 34 45 56 67 78 89 910 1011 1112 12
1 1
2 2
3 3
4 4
5 5
6 6
7 7 ✜

✜
✜
✜
✜
✜
✜

✜✜

p1 p1

p2 p2

p3 p3
p4 p4

✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘

✜
✜
✜
✜ ❡❡
✓
✓✓
✓
✓
✘✓
✘✘✘
✘

p0
p5

p6
p7

156 ASSIGNMENT TWO
For this example the convex hull is the set of edges
(p0, p3),(p3, p4),(p4, p5),(p5, p6),(p4, p6),(p6, p7),(p2, p7),(p1, p2),(p0, p1)
Note that by our definition of convex hull, any line segment on the hull is
part of the hull. When we have three points in a line (such as p4, p5 and p6)
above, we expect three lines to be listed ((p4, p5),(p5, p6),(p4, p6) in this case).
If we wanted a minimal hull, we would like just the line (p4, p6) to be listed,
but for this exercise we are content with the non-minimal listing.
To calculate whether a point p = (a, b) lies above or below a line we use the
equation of the line.
For the line x = c, p is above the line if a > c and below the line if a < c.
For the line y = mx + c, if b > ma + c then p is above the line and if
b < ma + c then p is below the line.
For this assignment you will write a method that computes the convex hull
of any sequence of distinct points in the plane by looking at each pair of points
and deciding whether or not there are points both above and below the line
they define. We will extend the class ConvexHull that you wrote in Part 3 of
this assignment, so open this file for editing.
teaching$ cd javaProgramming
teaching$ cd Ass1
teaching$ emacs ConvexHull.java &
B.5 The checkDuplicates method [20 marks]

It turns out that a polygon in which two points overlay each other (or equiv-
alently, a polygon with a repeated point) can cause some nasty problems. For

instance, we are going to find the gradient of a line from some (x1, y1) to (x2, y2).

What would that mean if they have the same coordinates? Clearly, it’s impor-
tant to go through the data the user has given us, and check for duplicates.

Your class ConvexHull should already have a method loadPoints and a
main method.
Add a method, checkDuplicates that tests to see whether the same point
was read in twice by loadPoints. A skeleton for the method is given below.
The method should take as input an integer pointCount and two arrays of
doubles, xVal and yVal, and it should return a boolean.
The method should check whether there is a pair of integers i, j, that
lie between 0 and pointCount, such that i 6= j and xV al[i] = xV al[j] and
yV al[i] = yV al[j]. As soon as it finds such a pair the method should print
out an error message, return true and terminate. Otherwise, if there are no
duplicates, the method should return false.
static boolean checkDuplicates(int pointCount, double xVal[], double yVal[])
{
for (int i=pointCount; i>=1; i--) {
for (int j=0; j<i; j++) {

The computeConvexHull method [40 marks] 157

/* put your code in here */
}
}
return false;
}
Test your method by adding the following if statement to the end of your
main method. (The statement terminates the program if a duplicate input entry
is found.)
if ( checkDuplicates(pointCount, xVal, yVal) ) return;
Run the program with input which contains duplicates and with input which
does not contain duplicates.
B.6 The computeConvexHull method [40 marks]
Write a method
static void computeConvexHull(int pointCount, double xVal[], double yVal[])
{
double m, c;
/* put your code here */
}
which begins by declaring two variables m and c of type double.
1. The method should then consider each pair of points pi = (xV al[i], yV al[i]),
pj = (xV al[j], yV al[j]), for 0 ≤ i < j < pointCount, using two nested
for statements like those used in your checkDuplicates method. For
each pair, pi

, pj , the method should proceed as follows.

2. Declare two variables above and below of type int, both of which are
initialised to 0.
3. Set m to be the slope of the line defined by pi and pj .
4. The method should consider two possibilities:
(a) a near vertical line, for which the value of m is Double.POSITIVE INFINITY
or Double.NEGATIVE INFINITY; and
(b) other lines for which m a conventional finite numeric value.
5. In both cases the method should test all the points pk = (xV al[k], yV al[k]),
for 0 ≤ k < pointCount and increase above by 1 if pk is above the line
defined by pi and pj , and increase below by 1 if pk is below the line defined
by pi and pj .

158 ASSIGNMENT TWO
6. When all the points pk have been considered, if only one of above and
below is greater than 0 then the edge (pi

, pj ) is on the convex hull and a

message reporting this should be printed out.
Now add a final line to your main method which calls the computeConvexHull
method as follows.
computeConvexHull(pointCount, xVal, yVal);
Compile, run and test your program.
B.7 Visualisation [30 marks]
Using Java FX, draw a map of the hedgehog’s path and the range, as defined
by the convex hull of the path.
