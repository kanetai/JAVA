import java.util.*;
import java.awt.geom.Point2D;

public class aoj0023 {
	static Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while( n-- > 0 ){
			Circle a = new Circle(stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble());
			Circle b = new Circle(stdin.nextDouble(), stdin.nextDouble(), stdin.nextDouble());
			int ans;
			switch(a.positionalRelation(b)){
			case Separated:		ans = 0; break;
			case FullIncluded:	ans = (a.r > b.r ? 2 : -2); break;
			default:			ans = 1;
			}
			System.out.println( ans );
		}
	}
	enum PosRelation { Separated, Circumscribed, CrossAt2Point, Inscribed, FullIncluded; }
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
		public Point(double x, double y){ super(x,y); }
	} //class Point

	public static class Circle{
		public final Point o;
		public double r;
		public Circle(double x, double y, double r){ this.o = new Point(x,y); this.r = r; }
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
