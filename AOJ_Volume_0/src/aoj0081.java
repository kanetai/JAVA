import java.awt.geom.Point2D;
import java.util.*;
public class aoj0081 {
	static final Scanner stdin = new Scanner(System.in);
	static Solver solver = Solver.Reflection;
	public static void main(String[] args) {
		while(stdin.hasNext()){
			String[] line = stdin.nextLine().split(",");
			double x1 = Double.parseDouble(line[0]), y1 = Double.parseDouble(line[1]);
			double x2 = Double.parseDouble(line[2]), y2 = Double.parseDouble(line[3]);
			double xq = Double.parseDouble(line[4]), yq = Double.parseDouble(line[5]);
			System.out.println(solver.solve(x1, y1, x2, y2, xq, yq));
		}
	}
	enum Solver {
		Reflection { @Override String solve(double x1, double y1, double x2, double y2, double xq, double yq) {
			Point ans = new Point(xq, yq).reflection(new Line(x1, y1, x2, y2));
			return ans.x + " " + ans.y;
		}}, AffineTransformation { @Override String solve(double x1, double y1, double x2, double y2, double xq, double yq) {
			double xr, yr;
			if(x1 == x2){
				xr = xq + 2*(x1-xq); yr = yq;
			}else if(y1 == y2){
				xr = xq; yr = yq + 2*(y1-yq);
			}else{
				double alpha = (y2-y1)/(x2-x1);
				double beta = y1 - alpha*x1;
				double coef = 1/(alpha*alpha + 1);
				xr = coef*((1-alpha*alpha)*xq + 2*alpha*yq - 2*alpha*beta);
				yr = coef*(2*alpha*xq + (alpha*alpha-1)*yq + 2*beta);
			}
			return xr + " " + yr;
		}};
		String solve(double x1, double y1, double x2, double y2, double xq, double yq) { return ""; }
	}
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final double dot(Point p){ return x * p.x + y * p.y; }
		public final double normsq(){ return x*x + y*y; }
		public final Point projection(Line l){
			Point a = l.end.sub(l.start);
			Point b = this.sub(l.start);
			return l.start.add(a.mul(a.dot(b)/a.normsq()));
		}
		public final Point reflection(Line l){ return projection(l).mul(2).sub(this); }
	} //class Point
	public static class Line{
		private final Point start;
		private final Point end;
		public Line(double sx, double sy, double ex, double ey){ start = new Point(sx,sy); end = new Point(ex,ey); }
	}
}
