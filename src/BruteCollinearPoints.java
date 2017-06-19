/**
 * Created by Ashish.Am.Singh on 19-06-2017.
 */
public class BruteCollinearPoints {
    private Point p ,q, r ,s ;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if(points == null || points[0] == null || points[1] == null || points[2] == null || points[3] == null){
            throw new java.lang.NullPointerException("null points");
        }
        p = points[0];
        q = points[1];
        r = points[2];
        s = points[3];

    }

    public int numberOfSegments()        // the number of line segments
    {

        double p1 ,p2 ,p3 ,p4 ;
        return 0;
    }

    public LineSegment[] segments()                // the line segments
    {
        LineSegment [] lineSegments = new LineSegment[4];
        return lineSegments;
    }
}
