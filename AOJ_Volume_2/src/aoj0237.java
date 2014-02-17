import java.awt.geom.Point2D;
import java.util.*;
public class aoj0237 {
	static final Scanner stdin = new Scanner(System.in);
	static final double eps = 0.01;
	enum SCCMethod {
		DFS1 { 
			@Override List<Set<Integer>> getSCC(List<List<Edge>> adjList) { return getStronglyConnectedComponents(adjList);} 
		}, DFS2 {
			@Override List<Set<Integer>> getSCC(List<List<Edge>> adjList) { return Kosaraju(adjList);} 
		};
		List<Set<Integer>> getSCC(List<List<Edge>> adjList) { return null; }
	}
	static SCCMethod method = SCCMethod.DFS2;
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), d = stdin.nextInt();
			if ((n|d) == 0) break;
			T[] data = new T[n];
			List<List<Edge>> adjList = new ArrayList<List<Edge>>();
			for (int i = 0; i < n; ++i) {
				data[i] = new T(new Point[]{
						new Point(stdin.nextDouble(), stdin.nextDouble()), 
						new Point(stdin.nextDouble(), stdin.nextDouble()), 
						new Point(stdin.nextDouble(), stdin.nextDouble())
				}, d);
				adjList.add(new ArrayList<Edge>());
			}
			for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) {
				if (i == j) continue;
				if (data[i].canConnect(data[j])) {
					adjList.get(i).add(new Edge(i, j));
				}
			}
			List<Set<Integer>> sccs = method.getSCC(adjList);
			int k = sccs.size(), ans = 0, SCC[] = new int[n], indeg[] = new int[k];
			for (int i = 0; i < k; ++i) for (Integer v: sccs.get(i)) SCC[v] = i;
			for (List<Edge> l : adjList) for (Edge e : l) if (SCC[e.s] != SCC[e.d]) indeg[SCC[e.d]]++;
			for (int deg : indeg) if (deg == 0) ans++;
			System.out.println(ans);
		}
	}
	static class T {
		Line base, top;
		Point vertex;
		T(Point points[], int d) {
			//Line[] l = {new Line(p1, p2), new Line(p2, p3), new Line(p3, p1) };
			for (int i = 0; i < 3; ++i) {
				if (equal(points[i].distance(points[(i+1)%3]), points[i].distance(points[(i+2)%3]))) {
					vertex = points[i];
					base = new Line(points[(i+1)%3], points[(i+2)%3]);
					Point nv = base.end.sub(base.start).normalVector();
					int sign = 1;
					if (base.ccw(vertex) < 0) { sign = -1; }
					nv = nv.mul(sign * d);
					top = new Line(base.end.add(nv), base.start.add(nv));
					return;
				}
			}
		}
		boolean canConnect(T t) {
			Line rect[] = { base, new Line(base.end, top.start), top, new Line(top.end, base.start) };
			Line tri[] = { t.base, new Line(t.base.end, t.vertex), new Line(t.vertex, t.base.start) };
			for (Line l1 : tri) for (Line l2 : rect) if (l1.intersectsSS(l2)) return true;
			Point prect[] = { base.start, base.end, top.start, top.end };
			Point ptri[] = { t.base.start, t.base.end, t.vertex };
			for (Point p: ptri) {
				if (contains(prect, p)) return true;
				for (Line l: rect) if (p.distancePS(l) <= eps) return true;
			}
			for (Point p: prect) {
				if (contains(ptri, p)) return true;
				for (Line l: tri) if (p.distancePS(l) <= eps) return true;
			}
			return false;
		}
	}

	//geom
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	@SuppressWarnings("serial") public static class Point extends Point2D.Double {
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
		public final double cross(Point p){ return x * p.y - y * p.x; }
		public final double dot(Point p){ return x * p.x + y * p.y; }
		public final int ccw(Point b, Point c) {
			Point ab = b.sub(this);
			Point ac = c.sub(this);
			if (greater(ab.cross(ac), 0))		return +1;	// counter clockwise
			if (less(ab.cross(ac), 0))			return -1;	// clockwise
			if (less(ab.dot(ac), 0))			return +2;	// (c--a--b or b--a--c) on line
			if (less(ab.normsq(), ac.normsq()))	return -2;	// (a--b--c or c--b--a) on line (b≠c, includes a=b)
			return 0;									// (a--c--b or b--c--a) on line (includes c=b, a=c, a=b=c)
		}
		public final Point projection(Line l){
			Point a = l.end.sub(l.start);
			Point b = this.sub(l.start);
			return l.start.add(a.mul(a.dot(b)/a.normsq()));
		}
		public final double distancePS(Line s){
			Point proj = projection(s);
			return proj.intersectsPS(s) ? distance(proj) : Math.min(distance(s.start), distance(s.end));
		}
		public final boolean intersectsPS(Line s) {
			//return s.ccw(this) == 0; //これでもおk
			return equal(s.start.distance(this) + this.distance(s.end), s.end.distance(s.start)); //三角不等式で等号が成り立つとき
		}
	} //class Point
	public static class Line{
		private final Point start, end;
		public Line(Point start, Point end){ this.start = new Point(start); this.end = new Point(end); }
		public final int ccw(Point p){ return start.ccw(end, p); }
		public final boolean intersectsSS(Line t) {
			return ccw(t.start) * ccw(t.end) <= 0 && t.ccw(start) * t.ccw(end) <= 0;
		}
	} //class Line
	public static final boolean contains(Point[] polygon, Point p) {
		boolean in = false;
		for (int i = 0, n = polygon.length; i < n; ++i) {
			Point a = polygon[i].sub(p), b = polygon[(i+1)%n].sub(p);
			if (a.y > b.y){ Point temp = b; b = a; a = temp; }
			if (a.y <= 0 && 0 < b.y) //点pからxの正方向への半直線が多角形の頂点をとおるとき、最終的に交差数を偶数回にするためどちらかを<=ではなく、<にする
				if (a.cross(b) < 0) in = !in; //=0 -> a//b -> on 
			if (a.cross(b) == 0 && a.dot(b) <= 0) return true; //on edge
		}
		return in ? true : false; //in out
	}

	//scc
	public static class Edge {
		protected int s, d;
		public Edge(int s, int d){ this.s = s; this.d = d; }
	}
	public static List<List<Edge>> inverseAdjacencyList(List<List<Edge>> adjList) {
		int n = adjList.size();
		List<List<Edge>> ret = new ArrayList<List<Edge>>();
		for (int i = 0; i < n; ++i) ret.add(new ArrayList<Edge>());
		for (List<Edge> l : adjList) for (Edge e : l) ret.get(e.d).add(new Edge(e.d, e.s));
		return ret;
	}
	private static class SCCDFSArg {
		int num = 0;
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] used;
		List<List<Edge>> adjList;
		SCCDFSArg(int numV, List<List<Edge>> adjList) { used = new boolean[numV]; this.adjList = adjList; }
	}
	public static List<Set<Integer>> Kosaraju(List<List<Edge>> adjList) {
		int n = adjList.size();
		SCCDFSArg arg = new SCCDFSArg(n, adjList);
		for (int v = 0; v < n; ++v) if (!arg.used[v]) postOrderNumbering(v, arg);

		Arrays.fill(arg.used, false);
		arg.adjList = inverseAdjacencyList(adjList);
		List<Set<Integer>> ret = new ArrayList<Set<Integer>>();
		while (!arg.stack.isEmpty()) {
			int v = arg.stack.pop();
			if (!arg.used[v]) {
				Set<Integer> scc = new HashSet<Integer>();
				addSCC(v, arg, scc);
				ret.add(scc);
				arg.num++; //group number
			}
		}
		return ret;
	}
	private static void postOrderNumbering(int v, SCCDFSArg arg) {
		arg.used[v] = true;
		for (Edge e : arg.adjList.get(v)) if (!arg.used[e.d]) postOrderNumbering(e.d, arg);
		arg.stack.push(v);
	}
	private static void addSCC(int v, SCCDFSArg arg, Set<Integer> scc) {
		arg.used[v] =  true;
		scc.add(v);
		for (Edge e : arg.adjList.get(v)) if (!arg.used[e.d]) addSCC(e.d, arg, scc);
	}
	public static List<Set<Integer>> getStronglyConnectedComponents(List<List<Edge>> adjList) {
		ArrayList<Set<Integer>> ret = new ArrayList<Set<Integer>>();
		int n = adjList.size();
		int[] num = new int[n], low = new int[n];
		SCCDFSArg arg = new SCCDFSArg(n, adjList);
		for (int v = 0; v < n; ++v) if (num[v] == 0) sccDFS(v, arg, low, num, ret);
		return ret;
	}
	private static void sccDFS(int v, SCCDFSArg arg, int[] low, int[] num, List<Set<Integer>> SCCs) {
		low[v] = num[v] = ++arg.num; //num[v]:= topological number of v. low[v]:= minimum topological number in SCC including v. 
		arg.stack.push(v); arg.used[v] = true; //used[v] := whether arg.S contains v or not.
		for (Edge e: arg.adjList.get(v)) {
			if (num[e.d] == 0) { //case: e.d is non visited vertex
				sccDFS(e.d, arg, low, num, SCCs);
				low[v] = Math.min(low[v], low[e.d]);
			} else if (arg.used[e.d]) { //arg.used[e.d] == false -> has already created scc containing e.d. 
				low[v] = Math.min(low[v], num[e.d]);
			}
		}
		if (low[v] == num[v]) { 
			HashSet<Integer> scc = new HashSet<Integer>();
			int sccV = 0;
			do {
				sccV = arg.stack.pop(); arg.used[sccV] = false;
				scc.add(sccV);
			} while (v != sccV);
			SCCs.add(scc);
		}
	}
}
