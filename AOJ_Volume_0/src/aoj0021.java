import java.awt.geom.Point2D;
import java.util.*;

public class aoj0021 {
	static final Scanner stdin = new Scanner(System.in);
	static final int A = 0, B = 1, C = 2, D = 3, N = 4;
	static final double EPS = 1e-10;
	public static void main(String[] args) {
		int n = stdin.nextInt();
		Point[] p = new Point[N];
		while( n-- > 0 ){
			for(int i = 0; i < N; ++i ) p[i] = new Point(stdin.nextDouble(), stdin.nextDouble());
			System.out.println( solve(p) ? "YES" : "NO" );
		}
	}
	static boolean solve(Point[] p){ return equal(p[B].sub(p[A]).cross(p[D].sub(p[C])), 0); }
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
}
