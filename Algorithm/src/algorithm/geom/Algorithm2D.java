package algorithm.geom;
import algorithm.geom.Define2D;
public class Algorithm2D extends Define2D{
	/**
	 * Returns the area of a polygon.<br>
	 * n = polygon.length, polygon[0]→polygon[1]→...→polygon[n-1]→polygon[0]<br>
	 * O(n)<br>
	 * AOJ No. 0012
	 * @param polygon(n>=3) 
	 * @return The area of a polygon
	 */
	public static double square(Point[] polygon){
		int n = polygon.length;
		double res = 0.0;
		for(int i = 0; i < n; ++i){
			Point cur = polygon[i], next = polygon[(i+1)%n];
			res += (cur.x + next.x)*(cur.y - next.y);
		}
		return Math.abs(res/2.0);
	}

	/**
	 * Tests whether polygon[0]→polygon[1]→...→polygon[polygon.length-1]→polygon[0] is convex polygon or not.<br>
	 * ※ Assumes that (adjacency) 3 points on same line doesn't exists.<br>
	 * AOJ No. 0035
	 * @param polygon	vertex set of target polygon
	 * @return　		true -> polygon is convex. false -> not convex.
	 */
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
	
	/**
	 * Tests whether polygon[0]→polygon[1]→...→polygon[polygon.length-1]→polygon[0] contains point p or not. <br>
	 * AOJ No. 0059
	 * @param Polygon	vertex set of target polygon
	 * @param p			target point
	 * @return			true -> polygon contains p. false -> polygon doesn't contain p.
	 */
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
	
	/**
	 * Tests whether rectangle a intersects with rectangle b or not.<br>
	 * AOJ No. 0059
	 * @param a Rectangle = {(ax, ay), (ax+aw, ay+ah)}
	 * @param b Rectangle = {(bx, by), (bx+bw, by+bh)}
	 * @return true -> a intersects with b. false -> a doesn't intersect with b.
	 */
	public static final boolean intersectsRR(Point[] a, Point[] b){
		return leq(a[0].x, b[1].x) && leq(a[0].y, b[1].y) && leq(b[0].x, a[1].x) && leq(b[0].y, a[1].y);
	}
}
