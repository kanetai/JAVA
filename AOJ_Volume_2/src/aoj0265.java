import java.awt.geom.Point2D;
import java.util.*;
public class aoj0265 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			Point[] polygon = new Point[n];
			for (int i = 0; i < n; ++i) polygon[i] = new Point(stdin.nextDouble(), stdin.nextDouble());
			List<Point> seg = new ArrayList<Point>(n*n);
			for (Point p : polygon) seg.add(new Point(p));
			for (int k = 0; k < n; ++k) {
				Line r = new Line(polygon[k], polygon[(k+1)%n]);
				List<Object[]> crosspoints = new ArrayList<Object[]>();
				for(int i = 0; i < n; ++i) for(int j = i+1; j < n; ++j) {
					Line l = new Line(polygon[i], polygon[j]);
					if (!l.parallel(r)) {
						Point p = l.intersectionLLPoint(r);
						if (equal(p.distancePS(r), 0)) 
							crosspoints.add(new Object[]{ l.start.distance(p), p});
					}
				}
				Collections.sort(crosspoints, new Comparator<Object[]>() {
					@Override public int compare(Object[] o1, Object[] o2) {
						int d = Double.compare((Double)o1[0], (Double)o2[0]);
						return d != 0 ? d : ((Point)o1[1]).compareTo((Point)o2[1]);
					}
				});
				for(int i = 0; i < crosspoints.size()-1; ++i)
					seg.add((((Point)crosspoints.get(i)[1]).add((Point)crosspoints.get(i+1)[1])).div(2));
			}
			int reachability[] = new int[seg.size()];
			for(int i = 0; i < n; ++i) for(int j = 0; j < seg.size(); ++j) {
				Line l = new Line(polygon[i], seg.get(j));
				if (contains(polygon, l)) reachability[j] |= bit(i);
			}
			int ans = n;
			LOOP: for(int S = 0; S < bit(n); ++S) {
				int popCount = Integer.bitCount(S);
				if (ans <= popCount) continue;
				for (int reachable : reachability) if ((reachable & S) == 0) continue LOOP;
				ans = popCount;
			}
			System.out.println(ans);
		}
	}
	static String toString(int field, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) sb.append((field & bit(i)) != 0 ? "1" : "0");
		return sb.toString();
	}
	static void print(int field, int n) { System.out.println(toString(field, n)); }
	static int bit(int bit) { return (1 << bit); }
	public static final double EPS = 1e-2;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean leq(double a, double b){ return a - b < EPS; }			// a <= b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	public static boolean geq(double a, double b){ return leq(b,a); }				// a >= b
	@SuppressWarnings("serial") public static class Point extends Point2D.Double implements Comparable<Point>{
		public Point(double x, double y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		public final double norm(){ return Math.sqrt( normsq() ); }
		public final double normsq(){ return x*x + y*y; }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final Point div(double k){ return new Point( x/k, y/k ); }
		//compareTo ascending order. priority 1. The x coordinate, 2. The y coordinate.
		@Override public final int compareTo(Point o){
			return (this.x != o.x) ? java.lang.Double.compare(this.x, o.x) : java.lang.Double.compare(this.y, o.y);
		}
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
		public final Point projection(Line l){
			Point a = l.dirV();
			Point b = this.sub(l.start);
			return l.start.add(a.mul(a.dot(b)/a.normsq()));
		}
		public final double distancePS(Line s){
			Point proj = projection(s);
			return proj.intersectsPS(s) ? distance(proj) : Math.min(distance(s.start), distance(s.end));
		}
		public final boolean intersectsPS(Line s) {
			//return s.ccw(this) == 0; //これでもおk
			return equal(s.start.distance(this) + this.distance(s.end), s.end.distance(s.start)); //三角不等式で等号が成り立つとき
		}
	} //class Point

	public static class Line{
		private final Point start;
		private final Point end;
		public Line(Point start, Point end){ this.start = new Point(start); this.end = new Point(end); }
		public Point dirV() { return end.sub(start); } //directional vector
		public double distance() { return end.distance(start); }
		public final int ccw(Point p){ return start.ccw(end, p); }
		public final boolean parallel(Line m) { return equal(this.dirV().cross(m.dirV()), 0); }
		public final boolean intersectsSS(Line t) {
			return ccw(t.start) * ccw(t.end) <= 0 && t.ccw(start) * t.ccw(end) <= 0;
		}
		public final Point intersectionSSPoint(Line t) {
			return intersectsSS(t) ? intersectionLLPoint(t) : null;
		}
		public final Point intersectionLLPoint(Line m){
			Point mp = m.dirV(), lp = this.dirV();
			double A = m.end.sub(start).cross(mp);
			double B = lp.cross(mp);
			if(equal(A, 0) && equal(B, 0)) return m.start; //same line
			if(equal(B, 0)) return null; //parallel
			return start.add(lp.mul(A/B));
		}
	} //class Line
	public static boolean contains(Point[] polygon, Point p) {
		boolean in = false;
		for (int i = 0, n = polygon.length; i < n; ++i) {
			Point a = polygon[i].sub(p), b = polygon[(i+1)%n].sub(p);
			if (a.y > b.y){ Point temp = b; b = a; a = temp; }
			if (a.y <= 0 && 0 < b.y) //点pからxの正方向への半直線が多角形の頂点をとおるとき、最終的に交差数を偶数回にするためどちらかを<=ではなく、<にする
				if (a.cross(b) < 0) in = !in; //=0 -> a//b -> on 
			if (equal(a.cross(b), 0) && leq(a.dot(b), 0)) return true; //on edge
		}
		return in; //in out
	}
	public static boolean contains(Point[] polygon, Line s) {
		final int n = polygon.length;
		List<Object[]> numl = new ArrayList<Object[]>();
		numl.add(new Object[]{0., s.start});
		numl.add(new Object[]{s.distance(), s.end});
		for (int i = 0; i < n; ++i) {
			Point p = s.intersectionSSPoint(new Line(polygon[i], polygon[(i+1)%n]));
			if (p != null) numl.add(new Object[]{p.distance(s.start), p});
		}
		Collections.sort(numl, new Comparator<Object[]>(){
			@Override public int compare(Object[] o1, Object[] o2) {
				int d = Double.compare((Double)o1[0], (Double)o2[0]);
				return d != 0 ? d : ((Point)o1[1]).compareTo((Point)o2[1]);
			}
		});
		for (int i = 0; i < numl.size()-1; ++i) 
			if(!contains(polygon, ((Point)numl.get(i)[1]).add((Point)numl.get(i+1)[1]).div(2))) return false;
		return true;
	}
}
