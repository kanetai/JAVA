import java.awt.geom.Point2D;
import java.util.*;
public class aoj0204 {
	static final Scanner stdin = new Scanner(System.in);
	static final double INF = 1e10;
	public static void main(String[] args) {
		while(true){
			int R = stdin.nextInt(), N = stdin.nextInt();
			if((R|N) == 0) break;
			T[] data = new T[N];
			boolean[] ignore = new boolean[N]; Arrays.fill(ignore, false);
			for(int i = 0; i < N; ++i) {
				data[i] = new T(stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt());
				data[i].move();
			}
			int ans = 0;
			while(true){
				double m = INF;
				int mi = -1;
				for(int i = 0; i < N; ++i){
					if(ignore[i]) continue;
					double d = data[i].dist;
					if(d <= R){
						ignore[i] = true;
						ans++;
					} else if(d < m){
						m = d;
						mi = i;
					}
				}
				if(mi == -1) break;

				Point laser = data[mi].c.o.unit();
				for(int i = 0; i < N; ++i){
					if(ignore[i]) continue;
					double a = laser.normsq();
					double b = -2*laser.dot(data[i].c.o);
					double c = data[i].c.o.normsq() - data[i].c.r * data[i].c.r;
					double D = b*b - 4*a*c;
					if(D >= 0){
						double d = Math.sqrt(D);
						double t1 = (-b + d)/(2*a), t2 = (-b - d)/(2*a);
						if(t1 > R || t2 > R) ignore[i] = true;
					}
					data[i].move();
				}
			}
			System.out.println(ans);
		}
	}
	static class T {
		Circle c;
		final Point dv;
		final double v;
		double dist;
		T(int x, int y, int r, int v){
			c = new Circle(x,y,r);
			this.v = v;
			double norm = c.o.norm();
			dv = c.o.mul(-v/norm);
			dist = norm;
		}
		void move(){
			c.o.set(c.o.add(dv));
			dist -= v;
		}
	}
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
		public final void set(Point p){ this.x = p.x; this.y = p.y; }
		public final double norm(){ return Math.sqrt( normsq() ); }
		public final double normsq(){ return x*x + y*y; }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final Point div(double k){ return new Point( x/k, y/k ); }
		public final Point unit(){ return this.div(this.norm()); }
		public final double cross(Point p){ return x * p.y - y * p.x; }
		public final double dot(Point p){ return x * p.x + y * p.y; }
	}
	public static class Circle{
		public final Point o;
		public double r;
		public Circle(double x, double y, double r){ this.o = new Point(x,y); this.r = r; }
	}
}
