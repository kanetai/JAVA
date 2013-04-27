import java.awt.geom.Point2D;
import java.util.*;
public class aoj0090 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		while(true){
			int n = stdin.nextInt(); stdin.nextLine();
			if(n==0) break;
			TreeMap<Point, Integer> map = new TreeMap<Point, Integer>();
			for(int i = 0; i<n; ++i){
				String[] line = stdin.nextLine().split(",");
				Point p = new Point(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
				if(map.containsKey(p)) map.put(p, map.get(p)+1);
				else map.put(p, 1);
			}
			System.out.println(solve(map));
		}
	}
	static int solve(TreeMap<Point, Integer> map){
		int n = map.size();
		int res = 0;
		Point[] in = new Point[n];
		in = map.keySet().toArray(in);
		for(int i = 0; i < n; ++i){
			if(res < map.get(in[i])) res = map.get(in[i]);
			for(int j = i + 1; j < n; ++j){
				ArrayList<Point> crossPoints = new ArrayList<Point>();
				int r = new Circle(in[i], 1).positionalRelation(new Circle(in[j], 1)).ordinal();
				if( r >= PosRelation.Circumscribed.ordinal()){
					Point ab = in[j].sub(in[i]);
					Point m = ab.div(2.0);
					Point mid = m.add(in[i]); //midpoint
					if(r == PosRelation.Circumscribed.ordinal()){
						crossPoints.add(mid);
					}else{
						Point nv = new Point(-(ab.y), ab.x).div(ab.norm()); //normal vector
						double k = Math.sqrt(1-m.normsq());
						Point v1 = nv.mul(k).add(mid);
						Point v2 = nv.mul(-k).add(mid);
						crossPoints.add(v1);
						crossPoints.add(v2);
					}
					int temp = map.get(in[i]) + map.get(in[j]);
					for(Point p: crossPoints){
						int count = temp;
						for(int k = 0; k < n; ++k){
							if(k==i || k==j) continue;
							if(in[k].distance(p)<=1) count += map.get(in[k]);
						}
						if(res < count) res = count;
					}
				}
			}
		}
		return res;
	}
	enum PosRelation { Separated, Circumscribed, CrossAt2Point, Inscribed, FullIncluded; }
	@SuppressWarnings("serial") public static class Point extends Point2D.Double implements Comparable<Point>{
		//constructor
		public Point(double x, double y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		public final double norm(){ return Math.sqrt( normsq() ); }
		public final double normsq(){ return x*x + y*y; }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final Point div(double k){ return new Point( x/k, y/k ); }
		public final Point normalVector(){
			double d = this.norm();
			return new Point(-y/d, x/d);
		}
		public final int compareTo(Point o){
			return	(this.x != o.x) ?
					( (this.x < o.x) ? -1 : 1 ) : 
						( (this.y < o.y) ? -1 : (this.y > o.y) ? 1 : 0 );
		}
	}
	public static class Circle{
		public final Point o;
		public double r;
		public Circle(Point o, double r){ this.o = new Point(o); this.r = r; }
		public final PosRelation positionalRelation(Circle c){
			double d2 = o.distanceSq(c.o);
			double dp2 = (r + c.r)*(r + c.r);
			if(d2 > dp2) return PosRelation.Separated;
			if(d2 == dp2) return PosRelation.Circumscribed;
			double dn2 = (r - c.r)*(r - c.r);
			if(dn2 < d2) return PosRelation.CrossAt2Point; //d < dp
			if(d2 == dn2) return PosRelation.Inscribed;
			return PosRelation.FullIncluded; //d < dn
		}
	}
}
