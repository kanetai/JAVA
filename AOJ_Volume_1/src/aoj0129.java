import java.awt.geom.Point2D;
import java.util.*;
public class aoj0129 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		while(true){
			int n = stdin.nextInt();
			if(n == 0) break;
			boolean[] A = new boolean[n], B = new boolean[n];
			Circle[] c = new Circle[n];
			for(int i = 0; i < n; ++i) c[i] = new Circle(stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble());
			int m = stdin.nextInt();
			for(int i = 0; i < m; ++i){
				boolean flag = true;
				Arrays.fill(A, false); Arrays.fill(B, false);
				Line s = new Line(stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble());
				for(int j = 0; j < n; ++j){
					if(s.start.intersectsPC(c[j])) A[j] = true;
					if(s.end.intersectsPC(c[j])) B[j] = true;
					if(A[j] == B[j]) flag = false;
				}
				if(flag){ System.out.println("Safe"); continue; }
				flag = false;
				for(int j = 0; j < n; ++j){
					if(A[j] && B[j]) continue;
					if(s.intersectsSC(c[j])){ flag = true; break; }
				}
				System.out.println(flag ? "Safe" : "Danger");
			}
		}
	}
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean leq(double a, double b){ return a - b < EPS; }			// a <= b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	public static boolean geq(double a, double b){ return leq(b,a); }				// a >= b
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public final double norm(){ return Math.sqrt( normsq() ); }
		public final double normsq(){ return x*x + y*y; }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final double dot(Point p){ return x * p.x + y * p.y; }
		public final Point projection(Line l){
			Point a = l.end.sub(l.start);
			Point b = this.sub(l.start);
			return l.start.add(a.mul(a.dot(b)/a.normsq()));
		}
		public final boolean intersectsPC(Circle c){ return leq(distanceSq(c.o), c.r*c.r); }
		public final double distancePS(Line s){
			Point proj = projection(s);
			return proj.intersectsPS(s) ? distance(proj) : Math.min(distance(s.start), distance(s.end));
		}
		public final boolean intersectsPS(Line s) {
			return equal(s.start.distance(this) + this.distance(s.end), s.end.distance(s.start)); //三角不等式で等号が成り立つとき
		}
	} //class Point
	public static class Line{
		private final Point start, end;
		public Line(double sx, double sy, double ex, double ey){ start = new Point(sx,sy); end = new Point(ex,ey); }
		public final double distanceSP(Point p){ return p.distancePS(this); }
		public final boolean intersectsSC(Circle c) { return geq(c.r, distanceSP(c.o)); }
	} //class Line
	public static class Circle{
		public final Point o;
		public double r;
		public Circle(double x, double y, double r){ this.o = new Point(x,y); this.r = r; }
	}
}
