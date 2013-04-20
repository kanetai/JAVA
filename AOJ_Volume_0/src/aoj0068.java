import java.awt.geom.Point2D;
import java.util.*;

public class aoj0068 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt();
			if(n==0) break;
			Point[] V = new Point[n];
			for(int i = 0; i < n; ++i){
				String[] coord = stdin.next().split(",");
				V[i] = new Point(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
			}
			System.out.println(n-convexHull(V).length);
		}
	}
	public static final double EPS = 1e-10;
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	@SuppressWarnings("serial")
	public static class Point extends Point2D.Double implements Comparable<Point>{
		public Point(double x, double y){ super(x,y); }
		public final double normsq(){ return x*x + y*y; }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final int compareTo(Point o){
			return	(this.x != o.x) ?
					( (this.x < o.x) ? -1 : 1 ) : 
						( (this.y < o.y) ? -1 : (this.y > o.y) ? 1 : 0 );
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
	} //class Point
	public static final Point[] convexHull(Point[] V) {
		int n = V.length;
		if(n < 3) return null;
		Arrays.sort(V); //sorts based on x coordinate in ascending order
		int k = 0; //index of C
		Point[] C = new Point[2*n];
		/* lower-hull */
		for(int i = 0; i < n; C[k++] = V[i++])
			while(k >= 2 && C[k-2].ccw(C[k-1], V[i]) <= 0) --k;
		/* upper-hull */
		// t=|lower-hull|+1
		for(int i = n-2, t = k + 1; i >= 0; C[k++] = V[i--])
			while(k >= t && C[k-2].ccw(C[k-1], V[i]) <= 0) --k;
		return Arrays.copyOf(C, k-1); //C[k-1] is start point of lower-hull.
	}
}
