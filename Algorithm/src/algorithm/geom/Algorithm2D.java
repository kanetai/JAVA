package algorithm.geom;
import algorithm.geom.Define2D;
public class Algorithm2D extends Define2D{
	/**
	 * Get the area of a polygon.<br>
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
}
