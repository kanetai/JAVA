import java.util.*;
public class aoj0214 {
	static final Scanner stdin = new Scanner(System.in);
	static final int V = 4;
	public static void main(String[] args) {
		while(true) {
			int m = stdin.nextInt();
			if (m == 0) break;
			while (m-- > 0) {
				int n = stdin.nextInt();
				Point[][] sqs = new Point[n][V];
				for (int i = 0; i < n; i++) for (int j = 0; j < V; j++) 
					sqs[i][j] = new Point(stdin.nextInt(), stdin.nextInt());
				UnionFind groups = new UnionFind(n);
				for (int i = 0; i < n; i++) for (int j = i+1; j < n; j++) 
					if(!groups.find(i, j) && intersects(sqs[i], sqs[j])) groups.union(i, j); 
				System.out.println(groups.size());
			}
		}
	}
	static boolean intersects(Point[] sq1, Point[] sq2) {
		for (int i = 0; i < V; ++i) if (contains(sq1, sq2[i])) return true;
		for (int i = 0; i < V; ++i) for (int j = 0; j < V; ++j) 
			if(new Line(sq1[i], sq1[(i+1)%V]).intersectsSS(new Line(sq2[j], sq2[(j+1)%V]))) return true;
		return false;
	}
	//union-find
	public static final int[] swap(int[] x, int i, int j){
		int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x;
	}
	static public class UnionFind {
		private int num; // number of disjoint sets (group)
		private final int[] parent;	//parent[x] >= 0 -> parent[x] = parent of x
									//parent[x]	< 0  -> x is root and -parent[x] = size of set containing x 
		public UnionFind(int size) {
			parent = new int[size];
			num = size;
			Arrays.fill(parent, -1);
		}
		public final int root(int x) { return parent[x] < 0 ? x : (parent[x] = root(parent[x])); }
		public final int size() { return num; }
		public final int size(int x) { return -parent[root(x)]; }
		public final boolean union(int x, int y) {
			x = root(x); y = root(y);
			if (x != y) {
				if (parent[y] < parent[x]) swap(parent, x, y);
				parent[x] += parent[y];
				parent[y] = x;
				--num;
			}
			return x != y;
		}
		public final boolean find(int x, int y) { return root(x) == root(y); }
	}
	//point2d
	@SuppressWarnings("serial") public static class Point extends java.awt.Point{
		//constructor
		public Point(int x, int y){ super(x,y); }
		public Point(Point p){ super(p.x, p.y); }
		public final int normsq(){ return x*x + y*y; }
		public final Point add(Point p){ return new Point( x + p.x, y + p.y ); }
		public final Point sub(Point p){ return new Point( x - p.x, y - p.y ); }
		public final int cross(Point p){ return x * p.y - y * p.x; }
		public final int dot(Point p){ return x * p.x + y * p.y; }
		public final int ccw(Point b, Point c) {
			Point ab = b.sub(this);
			Point ac = c.sub(this);
			if (ab.cross(ac) > 0)			return +1;	// counter clockwise
			if (ab.cross(ac) < 0)			return -1;	// clockwise
			if (ab.dot(ac) < 0)				return +2;	// (c--a--b or b--a--c) on line
			if (ab.normsq() < ac.normsq())	return -2;	// (a--b--c or c--b--a) on line (b≠c, includes a=b)
			return 0;									// (a--c--b or b--c--a) on line (includes c=b, a=c, a=b=c)
		}
	} //class Point
	public static final boolean contains(Point[] polygon, Point p) {
		boolean in = false;
		for (int i = 0, n = polygon.length; i < n; ++i) {
			Point a = polygon[i].sub(p), b = polygon[(i+1)%n].sub(p);
			if (a.y > b.y){ Point temp = b; b = a; a = temp; }
			if (a.y <= 0 && 0 < b.y) 
				if (a.cross(b) < 0) in = !in; //=0 -> a//b -> on 
			if (a.cross(b) == 0 && a.dot(b) <= 0) return true; //on edge
		}
		return in;
	}
	public static class Line{
		private final Point start;
		private final Point end;
		public Line(Point start, Point end){ this.start = new Point(start); this.end = new Point(end); }
		public final int ccw(Point p){ return start.ccw(end, p); }
		public final boolean intersectsSS(Line t) {
			return ccw(t.start) * ccw(t.end) <= 0 && t.ccw(start) * t.ccw(end) <= 0;
		}
	} //class Line
}
