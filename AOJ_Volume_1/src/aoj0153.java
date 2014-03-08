import java.awt.geom.Point2D;
import java.util.*;
public class aoj0153 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		Point[] tri = {new Point(0,0), new Point(0,0), new Point(0,0)};
		Circle c = new Circle(0,0,0);
		char ans = 0;
		while(true){
			tri[0].set(stdin.nextDouble(), stdin.nextDouble());
			if(tri[0].x == 0 && tri[0].y == 0) break;
			tri[1].set(stdin.nextDouble(), stdin.nextDouble());
			tri[2].set(stdin.nextDouble(), stdin.nextDouble());
			c.set(stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble());
			switch(c.positionalRelation(tri)){
			case Separated: ans = 'd'; break;
			case PolygonInCircle: ans = 'b'; break;
			case Circumcircle: ans = 'b'; break;
			case PartialCross: ans = 'c'; break;
			case Incircle: ans = 'a'; break;
			case CircleInPolygon: ans = 'a'; break;
			//default: break;
			}
			System.out.println(ans);
		}
	}
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean leq(double a, double b){ return a - b < EPS; }			// a <= b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	public static enum PosRelation {
		Separated, PolygonInCircle, Circumcircle, PartialCross, Incircle, CircleInPolygon;
	}
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		public final void set(double x, double y){ this.x = x; this.y = y; }
		public final double normsq(){ return x*x + y*y; }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final double cross(Point p){ return x * p.y - y * p.x; }
		public final double dot(Point p){ return x * p.x + y * p.y; }
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
			return equal(s.start.distance(this) + this.distance(s.end), s.end.distance(s.start)); //三角不等式で等号が成り立つとき
		}
	} //class Point
	public static class Line{
		private final Point start;
		private final Point end;
		public Line(Point start, Point end){ this.start = new Point(start); this.end = new Point(end); }
		public Point dirV() { return end.sub(start); } //directional vector
	} //class Line
	public static class Circle{
		public final Point o;
		public double r;
		public Circle(double x, double y, double r){ this.o = new Point(x,y); this.r = r; }
		public void set(double x, double y, double r){ o.x = x; o.y = y; this.r = r; }
		public PosRelation positionalRelation(final Point[] polygon){
			final int n = polygon.length;
			double[] dists = new double[n];
			boolean flag = true, eq = true;
			for(int i = 0; i < n; ++i){
				dists[i] = o.distance(polygon[i]);
				if(!equal(dists[i], r)) eq = false;
				if(greater(dists[i], r)) flag = false;
			}
			if(eq) return PosRelation.Circumcircle; //外接円
			if(flag) return PosRelation.PolygonInCircle; //円が多角形を内包する

			for(int i = 0; i < n; ++i) dists[i] = o.distancePS(new Line(polygon[i], polygon[(i+1)%n]));

			eq = flag = true;
			if(contains(polygon, o)){
				for(double d: dists){
					if(!equal(d, r)) eq = false;
					if(less(d, r)) flag = false;
				}
				if(eq) return PosRelation.Incircle; //内接円
				if(flag) return PosRelation.CircleInPolygon; //多角形が円を内包する
			}else{
				for(double d: dists) if(leq(d, r)) flag = false;
				if(flag) return PosRelation.Separated; //分離
			}
			return PosRelation.PartialCross; //一部交差
		}
	}
	public static boolean contains(Point[] polygon, Point p) {
		boolean in = false;
		for (int i = 0, n = polygon.length; i < n; ++i) {
			Point a = polygon[i].sub(p), b = polygon[(i+1)%n].sub(p);
			if (a.y > b.y){ Point temp = b; b = a; a = temp; }
			if (a.y <= 0 && 0 < b.y) 
				if (a.cross(b) < 0) in = !in; //=0 -> a//b -> on 
			if (equal(a.cross(b), 0) && leq(a.dot(b), 0)) return true; //on edge
		}
		return in; //in out
	}
}
