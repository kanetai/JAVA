import java.awt.geom.*;
import java.util.*;

public class aoj0035 {
	final static Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		while(stdin.hasNext()){
			Point[] polygon = new Point[4];
			String[] in = stdin.nextLine().split(",");
			for(int i=0; i<4; ++i)
				polygon[i] = new Point(Double.parseDouble(in[i+i]), Double.parseDouble(in[i+i+1]));
			System.out.println( isConvex(polygon) ? "YES" : "NO" );
		}
	}
	static final double EPS = 1e-10;
	static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	static boolean greater(double a, double b){ return less(b,a); }			// a > b
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
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
	public static final boolean isConvex(Point[] polygon) {
		int n = polygon.length;
		if(n < 3) return false;
		//clockwise at all-points or counter clockwise at all-points → true
		int sign = polygon[n-1].ccw(polygon[0], polygon[1]);
		for (int i = 1; i < n; ++i){
			int prev = (i + n-1)% n;
			int curr = i;
			int next = (i + 1) % n; 
			if (sign != polygon[prev].ccw(polygon[curr], polygon[next])) return false;
		}
		return true;
	}
}
