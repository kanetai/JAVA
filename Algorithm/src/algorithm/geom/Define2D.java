package algorithm.geom;
import java.awt.geom.Point2D;
import static algorithm.Utility.equal;
import static algorithm.Utility.less;
import static algorithm.Utility.greater;
import static algorithm.Utility.EPS;
public final class Define2D {
	private Define2D(){}
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
		public final double cross(Point p){ return x * p.y - y * p.x; }
		public final double dot(Point p){ return x * p.x + y * p.y; }

		/** 
		 * Returns integer value that indicates positional relation of Points a(this point), b, and c.<br>
		 * Positive return value indicates counter clockwise.<br>
		 * AOJ No. 0035 0068
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
		/**
		 * Calculates the orthogonal projection onto the specified line l.
		 * AOJ No. 0081
		 * @param p point
		 * @return  orthogonal projection
		 */
		public final Point projection(Line l){
			Point a = l.end.sub(l.start);
			Point b = this.sub(l.start);
			return l.start.add(a.mul(a.dot(b)/a.normsq()));
		}
		/**
		 * Calculates the reflection over the specified line l.<br>
		 * AOJ No. 0081
		 * @param p point
		 * @return  reflection
		 */
		public final Point reflection(Line l){ return projection(l).mul(2).sub(this); }
	} //class Point

	public static class Line{
		private final Point start;
		private final Point end;
		public Line(double sx, double sy, double ex, double ey){ start = new Point(sx,sy); end = new Point(ex,ey); }
		public Line(Point start, Point end){ this.start = new Point(start); this.end = new Point(end); }
		public void set(double sx, double sy, double ex, double ey){ start.x = sx; start.y = sy; end.x = ex; end.y = ey; }
		public void set(Point start, Point end){ set(start.x, start.y, end.x, end.y); }
		public void set(Line l){ set(l.start, l.end); }
		/** 
		 * Returns integer value that indicates positional relation of Points a(this.start), b(this.end), and c.
		 * Positive return value indicates counter clockwise.<br>
		 * AOJ No. 0059
		 * @param c	Target Point
		 * @return 	 1:	ab → ac counter clockwise<br>
		 * 			-1:	ab → ac clockwise<br>
		 * 			 2:	(c--a--b or b--a--c) on line<br>
		 * 			-2:	(a--b--c or c--b--a) on line (b≠c, includes a=b)<br>
		 * 			 0: (a--c--b or b--c--a) on line (includes c=b, a=c, a=b=c)
		 */
		public final int ccw(Point p){ return start.ccw(end, p); }
		/**
		 * Tests whether segment s(this) intersects with segment t or not. <br>
		 * AOJ No. 0059
		 * @param t Target Segment
		 * @return  true -> s intersects with t. false -> s doesn't intersect with t.
		 */
		public final boolean intersectsSS(Line t) {
			return ccw(t.start) * ccw(t.end) <= 0 && t.ccw(start) * t.ccw(end) <= 0;
		}
		/**
		 * Calculates point at the intersection of segment s(this) with segment t
		 * @param t Target Segment
		 * @return  Point intersection point. null -> Intersection point doesn't exists. 
		 */
		public final Point intersectionSSPoint(Line t) {
			return intersectsSS(t) ? intersectionLLPoint(t) : null;
		}
		/**
		 * Tests whether line l(this) intersects with line m or not.  
		 * @param m Target Line
		 * @return  true -> l intersects with m. false -> l doesn't intersect with m.
		 */
		public final boolean intersectsLL(Line m) {
			return Math.abs(end.sub(start).cross(m.end.sub(m.start))) > EPS || // non-parallel
					Math.abs(end.sub(start).cross(m.start.sub(end))) < EPS;     // same line
		}
		/**
		 * Calculates point at the intersection of line l(this) with line m.
		 * @param m Target Line
		 * @return  Point intersection point. null -> Intersection point doesn't exists. 
		 */
		public final Point intersectionLLPoint(Line m){
			Point mp = m.end.sub(m.start), lp = end.sub(start);
			double A = m.end.sub(start).cross(mp);
			double B = lp.cross(mp);
			if(equal(A, 0) && equal(B, 0)) return m.start; //same line
			if(equal(B, 0)) return null; //parallel
			return start.add(lp.mul(A/B));
		}
		/**
		 * Calculates the orthogonal projection of specified point p onto the line(this).
		 * @param p point
		 * @return  orthogonal projection
		 */
		public final Point projection(Point p){ return p.projection(this); }
		/**
		 * Calculates a reflection of specified point p over the line(this).
		 * @param p point
		 * @return  reflection
		 */
		public final Point reflection(Point p){ return p.reflection(this); }
	} //class Line

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
