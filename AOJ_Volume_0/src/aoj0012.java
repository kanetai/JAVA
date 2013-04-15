import java.awt.geom.Point2D;
import java.util.*;

public class aoj0012 {
	static final Scanner stdin = new Scanner(System.in);
	static double EPS = 1e-10;
	static final Solver solver = Solver.OuterProduct;
	public static void main(String[] args) {
		while( stdin.hasNext() ){
			Point p1 = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point p2 = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point p3 = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point pp = new Point(stdin.nextDouble(), stdin.nextDouble());
			System.out.println( solver.solve(p1, p2, p3, pp) ? "YES" : "NO");
		}
	}
	enum Solver{
		OuterProduct { @Override boolean solve(Point p1, Point p2, Point p3, Point pp){
			Point p12 = p2.sub(p1), p23 = p3.sub(p2), p31 = p1.sub(p3);
			Point p1p = pp.sub(p1), p2p = pp.sub(p2), p3p = pp.sub(p3);
			double s1 = p12.cross(p1p), s2 = p23.cross(p2p), s3 = p31.cross(p3p);
			return ( s1 >= 0 && s2 >= 0 && s3 >= 0 || s1 <= 0 && s2 <= 0 && s3 <= 0 );
		}}, Heron { @Override boolean solve(Point p1, Point p2, Point p3, Point pp){
			double S1 = Heron(p1, p2, pp), S2 = Heron(p2, p3, pp), S3 = Heron(p3, p1, pp);
			double SA = Heron(p1, p2, p3);
			return equal(SA, S1+S2+S3);
		}}, Square { @Override boolean solve(Point p1, Point p2, Point p3, Point pp){
			double S1 = square(new Point[]{p1, p2, pp}), S2 = square(new Point[]{p2, p3, pp}), S3 = square(new Point[]{p3, p1, pp});
			double SA = square(new Point[]{p1, p2, p3});
			return equal(SA, S1+S2+S3);
		}};
		boolean solve(Point p1, Point p2, Point p3, Point pp){ return true; }
	}

	static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }
	static double Heron(Point p1, Point p2, Point p3){
		double a = p1.distance(p2), b = p2.distance(p3), c = p3.distance(p1);
		double s = (a+b+c)/2;
		return Math.sqrt( s*(s-a)*(s-b)*(s-c) );
	}
	@SuppressWarnings("serial")
	public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final double cross(Point p){ return x * p.y - y * p.x; }
	}
	public static double square(Point[] polygon){
		int n = polygon.length;
		double res = 0.0;
		for(int i = 0; i < n; ++i){
			Point cur = polygon[i], next = polygon[(i+1)%n];
			res += (cur.x + next.x)*(cur.y - next.y);
		}
		return Math.abs(res/2.0);
	}
}
