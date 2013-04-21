import java.awt.geom.Point2D;
import java.util.*;
public class aoj0079 {
	static final Scanner stdin = new Scanner(System.in);
	static Solver solver = Solver.Square;
	public static void main(String[] args) { 
		ArrayList<Point> polygon = new ArrayList<Point>(); 
		while(stdin.hasNext()){
			String[] p = stdin.nextLine().split(",");
			polygon.add(new Point(Double.parseDouble(p[0]), Double.parseDouble(p[1])));
		}
		System.out.println(solver.solve((Point[])polygon.toArray(new Point[polygon.size()])));
	}
	static enum Solver {
		Square { @Override double solve(Point[] polygon) {
			Point p = (polygon[0].add(polygon[1]).add(polygon[2])).div(3.0);
			int n = polygon.length;
			double ret = 0.0;
			for(int i = 0; i < n; ++i)
				ret += Math.abs(polygon[i].sub(p).cross(polygon[(i+1)%n].sub(p)))/2.0;
			return ret;
		}}, Cross { @Override double solve(Point[] polygon) {
			return square(polygon);
		}};
		double solve(Point[] polygon){ return 0.0; }
	}
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point div(double k){ return new Point( x/k, y/k ); }
		public final double cross(Point p){ return x * p.y - y * p.x; }
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
