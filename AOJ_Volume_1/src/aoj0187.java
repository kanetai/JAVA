import java.awt.geom.Point2D;
import java.util.*;
public class aoj0187 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		Line[] l = new Line[3];
		Point[] p = new Point[3];
		while(true){
			int a = stdin.nextInt(), b = stdin.nextInt(), c = stdin.nextInt(), d = stdin.nextInt();
			if((a|b|c|d) == 0) break;
			l[0] = new Line(a,b,c,d);
			l[1] = new Line(stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble());
			l[2] = new Line(stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble());
			p[0] = l[0].intersectionSSPoint(l[1]);
			p[1] = l[1].intersectionSSPoint(l[2]);
			p[2] = l[2].intersectionSSPoint(l[0]);
			boolean flag = true;
			for(Point q: p) if(q == null) flag = false;
			if(!flag){
				System.out.println("kyo");
			}else{
				double S = square(p);
				System.out.println(
						1900000<=S ? "dai-kichi" :
						1000000<=S ? "chu-kichi":
						100000<=S  ? "kichi" : 
						0 < S      ? "syo-kichi" : "kyo");
			}
		}
	}
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	public static double square(Point[] polygon){
		int n = polygon.length;
		double res = 0.0;
		for(int i = 0; i < n; ++i){
			Point cur = polygon[i], next = polygon[(i+1)%n];
			res += (cur.x + next.x)*(cur.y - next.y);
		}
		return Math.abs(res/2.0);
	}
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		public final double normsq(){ return x*x + y*y; }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final double cross(Point p){ return x * p.y - y * p.x; }
		public final double dot(Point p){ return x * p.x + y * p.y; }
		public final int ccw(Point b, Point c) {
			Point ab = b.sub(this);
			Point ac = c.sub(this);
			if (greater(ab.cross(ac), 0))		return +1;	// counter clockwise
			if (less(ab.cross(ac), 0))			return -1;	// clockwise
			if (less(ab.dot(ac), 0))			return +2;	// (c--a--b or b--a--c) on line
			if (less(ab.normsq(), ac.normsq()))	return -2;	// (a--b--c or c--b--a) on line (b≠c, includes a=b)
			return 0;									// (a--c--b or b--c--a) on line (includes c=b, a=c, a=b=c)
		}
	} //class Point
	public static class Line{
		private final Point start;
		private final Point end;
		public Line(double sx, double sy, double ex, double ey){ start = new Point(sx,sy); end = new Point(ex,ey); }
		public Point dirV() { return end.sub(start); } //directional vector
		public final int ccw(Point p){ return start.ccw(end, p); }
		public final boolean intersectsSS(Line t) {
			return ccw(t.start) * ccw(t.end) <= 0 && t.ccw(start) * t.ccw(end) <= 0;
		}
		public final Point intersectionSSPoint(Line t) {
			return intersectsSS(t) ? intersectionLLPoint(t) : null;
		}
		public final boolean intersectsLL(Line m) {
			Point lv = this.dirV(), mv = m.dirV();
			return Math.abs(lv.cross(mv)) > EPS || // non-parallel
					Math.abs(lv.cross(mv.mul(-1))) < EPS;     // same line
		}
		public final Point intersectionLLPoint(Line m){
			Point mp = m.end.sub(m.start), lp = end.sub(start);
			double A = m.end.sub(start).cross(mp);
			double B = lp.cross(mp);
			if(equal(A, 0) && equal(B, 0)) return m.start; //same line
			if(equal(B, 0)) return null; //parallel
			return start.add(lp.mul(A/B));
		}
	} //class Line
}
