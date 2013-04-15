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
}
