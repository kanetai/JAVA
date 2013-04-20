import java.awt.geom.*;
import java.util.*;
public class aoj0059 {
	static final Scanner stdin = new Scanner(System.in);
	static final Point[] A = new Point[4], B = new Point[4];
	static final Line[] LA = new Line[4], LB = new Line[4];
	static Solver solver = Solver.Solution1;
	public static void main(String[] args) { 
		while(stdin.hasNext()){
			Point a1 = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point a3 = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point b1 = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point b3 = new Point(stdin.nextDouble(), stdin.nextDouble());
			System.out.println( solver.solve(a1, a3, b1, b3) ? "YES" : "NO");
		}
	}
	enum Solver {
		Solution1 { @Override boolean solve(Point a1, Point a3, Point b1, Point b3){ 
			B[0] = b1; B[2] = b3;
			B[1] = new Point(b1.x, b3.y); B[3] = new Point(b3.x, b1.y);
			A[0] = a1; A[2] = a3;
			A[1] = new Point(a1.x, a3.y); A[3] = new Point(a3.x, a1.y);

			for(Point b: B) if(contains(A, b)) return true;
			for(Point a: A) if(contains(B, a)) return true;

			for(int i = 0; i < 4; ++i){
				LA[i] = new Line(A[i], A[(i+1)%4]);
				LB[i] = new Line(B[i], B[(i+1)%4]);
			}
			for(Line lb: LB)
				for(Line la: LA)
					if(la.intersectsSS(lb)) return true;
			return false;
		}}, Solution2 { @Override boolean solve(Point a1, Point a3, Point b1, Point b3){
			return intersectsRR(new Point[]{a1, a3}, new Point[]{b1, b3});
		}}, Solution3{ @Override boolean solve(Point a1, Point a3, Point b1, Point b3){
			double ax = a1.x - EPS, bx = b1.x;
			double ay = a1.y - EPS, by = b1.y;
			double aw = a3.x - ax + EPS, bw = b3.x - bx;
			double ah = a3.y - ay + EPS, bh = b3.y - by;
			return new Rectangle2D.Double(ax, ay, aw, ah).intersects(bx, by, bw, bh);
		}};
		boolean solve(Point a1, Point a3, Point b1, Point b3){ return false; }
	}
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean leq(double a, double b){ return a - b < EPS; }			// a <= b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		public final double normsq(){ return x*x + y*y; }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
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
		public Line(Point start, Point end){ this.start = new Point(start); this.end = new Point(end); }
		public final int ccw(Point p){ return start.ccw(end, p); }
		public final boolean intersectsSS(Line t) {
			return ccw(t.start) * ccw(t.end) <= 0 && t.ccw(start) * t.ccw(end) <= 0;
		}
	} //class Line
	public static final boolean contains(Point[] polygon, Point p) {
		boolean in = false;
		for (int i = 0, n = polygon.length; i < n; ++i) {
			Point a = polygon[i].sub(p), b = polygon[(i+1)%n].sub(p);
			if (a.y > b.y){ Point temp = b; b = a; a = temp; }
			if (a.y <= 0 && 0 < b.y) //点pからxの正方向への半直線が多角形の頂点をとおるとき、最終的に交差数を偶数回にするためどちらかを<=ではなく、<にする
				if (a.cross(b) < 0) in = !in; //=0 -> a//b -> on 
			if (a.cross(b) == 0 && a.dot(b) <= 0) return true; //on edge
		}
		return in ? true : false; //in out
	}
	public static final boolean intersectsRR(Point[] a, Point[] b){
		return leq(a[0].x, b[1].x) && leq(a[0].y, b[1].y) && leq(b[0].x, a[1].x) && leq(b[0].y, a[1].y);
	}
}
