import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * Created by Ashish.Am.Singh on 19-06-2017.
 */
public class Point implements Comparable<Point> {


    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString()                           // string representation
    {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that)       // the slope between this point and that point
    {
        double slope ;
        if(this.x != that.x) {
            if(that.y == this.y) slope = 0.0 ;
            else slope = (that.y - this.y) / (that.x - this.x);
        }else {
            if(that.y == this.y){
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY ;
        }

        return slope ;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */

    public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
    {
        return new MyComparator();
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */

    @Override
    public int compareTo(Point o) {
        if (o.y > this.y || o.y < this.y) {
            return (o.y - this.y);
        }
        if (o.y == this.y) {
            if (o.x > this.x || o.x < this.x) {
                return (o.x - this.x);
            }
        }
        return 0;
    }

    private class MyComparator implements Comparator<Point>{

        @Override
        public int compare(Point o1, Point o2) {

            double p1 = slopeTo(o1);
            double p2 = slopeTo(o2);

            if (p1 == p2) return 0;
            if (p1 == Double.POSITIVE_INFINITY || p1 > p2 || p2 == Double.NEGATIVE_INFINITY) return 1;
            if (p2 == Double.POSITIVE_INFINITY || p2 > p1 || p1 == Double.NEGATIVE_INFINITY) return -1;

            return -1;
        }

    }

    public static void main (String[] args){
        Point point = new Point(0,0);
       StdOut.print(point.compareTo(new Point(0,1)));
    }
}
