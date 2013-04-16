package algorithm.geom;
import java.awt.geom.Point2D;
import algorithm.Utility;
public class Define2D extends Utility{
	enum PosRelation { Separated, Circumscribed, CrossAt2Point, Inscribed, FullIncluded; }
	@SuppressWarnings("serial")
	public static class Point extends Point2D.Double implements Comparable<Point>{
		//constructor
		public Point(double x, double y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		//setter
		public final void set(double x, double y){ this.x = x; this.y = y; }
		public final void set(Point p){ this.x = p.x; this.y = p.y; }
		//norm
		public final double norm(){ return Math.sqrt( normsq() ); }
		public final double normsq(){ return x*x + y*y; }
		//addition, subtraction, multiplication, division
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final Point mul(double k){ return new Point( k*x, k*y ); }
		public final Point div(double k){ return new Point( x/k, y/k ); }
		//unit vector
		public final Point unit(){ return this.div(this.norm()); }
		//a normal unit vector(±normalVector())
		public final Point normalVector(){
			double d = this.norm();
			return new Point(-y/d, x/d);
		}
		//compareTo ascending order. priority 1. The x coordinate, 2. The y coordinate.
		public final int compareTo(Point o){
			return	(this.x != o.x) ?
					( (this.x < o.x) ? -1 : 1 ) : 
						( (this.y < o.y) ? -1 : (this.y > o.y) ? 1 : 0 );
		}
		/**
		 * Returns outer product |a||b|sinθ. a = this, b = p.<br>
		 * AOJ No.0012, 0021
		 * @param p 
		 * @return |a||b|sinθ
		 */
		public final double cross(Point p){ return x * p.y - y * p.x; }
		/**
		 * Returns inter product |a||b|cosθ. a = this, b = p.
		 * @param p
		 * @return |a||b|cosθ
		 */
		public final double dot(Point p){ return x * p.x + y * p.y; }

		/** 
		 * Returns integer value that indicates positional relation of Points a(this point), b, and c.<br>
		 * Positive return value indicates counter clockwise.<br>
		 * AOJ No. 0035
		 * @param b Target Point 1
		 * @param c	Target Point 2
		 * @return 	 1:	ab → ac counter clockwise<br>
		 * 			-1:	ab → ac clockwise<br>
		 * 			 2:	(c--a--b or b--a--c) on line<br>
		 * 			-2:	(a--b--c or c--b--a) on line (b≠c, includes a=b)<br>
		 * 			 0: (a--c--b or b--c--a) on line (includes c=b, a=c, a=b=c)
		 */
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

	public static class Circle{
		public final Point o;
		public double r;
		public Circle(double x, double y, double r){ this.o = new Point(x,y); this.r = r; }
		public Circle(Point o, double r){ this.o = new Point(o); this.r = r; }
		public void set(double x, double y, double r){ o.x = x; o.y = y; this.r = r; }
		public void set(Point o, double r){ set(o.x, o.y, r); }
		/**
		 * Returns positional relation with circle c<br>
		 * AOJ No.0023
		 * @param c circle
		 * @return	positional relation
		 */
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
