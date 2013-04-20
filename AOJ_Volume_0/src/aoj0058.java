import java.awt.geom.Point2D;
import java.util.*;

public class aoj0058 {
	static final Scanner stdin = new Scanner(System.in);
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static void main(String[] args) { 
		while(stdin.hasNext()){
			double xA = stdin.nextDouble(), yA = stdin.nextDouble();
			double xB = stdin.nextDouble(), yB = stdin.nextDouble();
			double xC = stdin.nextDouble(), yC = stdin.nextDouble();
			double xD = stdin.nextDouble(), yD = stdin.nextDouble();
			System.out.println(
					equal( new Point(xA-xB, yA-yB).dot(new Point(xC-xD, yC-yD)), 0.0 ) ? "YES" : "NO");
		}
	}
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public final double dot(Point p){ return x * p.x + y * p.y; }
	} //class Point
}
