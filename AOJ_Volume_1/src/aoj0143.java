import java.util.*;
import java.awt.geom.*;
public class aoj0143 {
	static final Scanner stdin = new Scanner(System.in);
	static final double EPS = 1e-10;
	static final Solver solver = Solver.CONTAINS;
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while(n-- > 0){
			Point[] p = new Point[3];
			for(int i = 0; i < 3; ++i) p[i] = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point k = new Point(stdin.nextDouble(), stdin.nextDouble());
			Point s = new Point(stdin.nextDouble(), stdin.nextDouble());
			System.out.println(solver.solve(p, k, s) ? "OK" : "NG");
		}
	}
	static enum Solver {
		CONTAINS {
			@Override boolean solve(Point[] p, Point k, Point s){ return contains(p, k) != contains(p, s);}
		}, SQUARE {
			@Override boolean solve(Point[] p, Point k, Point s){
				double S = square(p), Sk = 0, Ss = 0;
				for(int i = 0; i < 3; ++i){
					Point temp = p[i];
					p[i] = k; Sk += square(p);
					p[i] = s; Ss += square(p);
					p[i] = temp;
				}
				return equal(S, Sk) != equal(S, Ss);
			}
		};
		boolean solve(Point[] p, Point k, Point s){ return true; }
	}
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final double cross(Point p){ return x * p.y - y * p.x; }
		public final double dot(Point p){ return x * p.x + y * p.y; }
	} //class Point
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
