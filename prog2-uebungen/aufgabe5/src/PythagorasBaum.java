import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class PythagorasBaum {
    private static double leanAngle = 30;
    private static double limit = 7;
    private static double colorLimit = 8;
    private static Color tree = new Color(102, 65, 32);
    private static Color leaf = new Color(0, 204, 0);
    private static final Random random = new Random();

    public static void main(String[] args) {
        Point2D a = new Point2D.Double(-200,-800);
        Point2D b = new Point2D.Double(200, -800);

        StdDraw.setXscale(-2000, 2000);
        StdDraw.setYscale(-2000, 2000);
        StdDraw.setPenColor(tree);

        drawTree(a, b, true);
    }

    private static void drawTree(Point2D a, Point2D b, boolean r) {
        double w = a.distance(b);
        double u, h = 0;

        if (w < colorLimit)
            StdDraw.setPenColor(leaf);
        else
            StdDraw.setPenColor(tree);


        Point2D c = rotatePoint(b, a, Math.toRadians(-90));
        Point2D d = rotatePoint(a, b, Math.toRadians(90));

        if (r) {
            leanAngle = 70 * random.nextDouble();
            double distance = d.distance(a);
            h = 1 + (2 * distance - 1) * random.nextDouble();
            c = scale(b, c, h);
            d = scale(a, d, h);
        }

        u = w * Math.cos(Math.toRadians(leanAngle));
        Point2D e = scale(d, rotatePoint(d, c, Math.toRadians(leanAngle)), u);

        StdDraw.line(a.getX(), a.getY(), d.getX(), d.getY());
        StdDraw.line(b.getX(), b.getY(), c.getX(), c.getY());

        if (w > limit) {
            drawTree(d, e, r);
            StdDraw.show(0);
            drawTree(e, c, r);
        }
    }

    private static Point2D scale(Point2D p1, Point2D p2, double factor) {
        //Vector = subtract p2 with p1
        Point2D vector = new Point2D.Double(
                p2.getX() - p1.getX(),
                p2.getY() - p1.getY()
        );

        //Calculate new normalizer and create new factor
        double normalizer = Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
        factor = (1 / normalizer) * factor;

        //New vector by multiplying current vector and calculated vector
        vector = new Point2D.Double(
                vector.getX() * factor,
                vector.getY() * factor
        );

        //Add calculated vector to first point
        return new Point2D.Double(
                p1.getX() + vector.getX(),
                p1.getY() + vector.getY()
        );
    }

    private static Point2D rotatePoint(Point2D center, Point2D p, double angle)
    {
        double x = p.getX() - center.getX();
        double y = p.getY() - center.getY();

        double xNew = x * Math.cos(angle) - y * Math.sin(angle);
        double yNew = x * Math.sin(angle) + y * Math.cos(angle);

        x = xNew + center.getX();
        y = yNew + center.getY();
        return new Point2D.Double(x, y);
    }
}
