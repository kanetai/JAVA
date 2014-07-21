import java.util.*;
import java.awt.Point;
public class aoj0273 {
	static final Scanner stdin = new Scanner(System.in);
	static Point[] points;
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), W = stdin.nextInt();
			if ((n|W) == 0) break;
			points = new Point[n];
			for (int i = 0; i < n; ++i) points[i] = new Point(stdin.nextInt(), stdin.nextInt());
			List<List<Edge>> adjList = new ArrayList<List<Edge>>(n);
			for (int i = 0; i < n; ++i) adjList.add(new ArrayList<Edge>());
			while (W-- > 0) {
				int src = stdin.nextInt()-1, dst = stdin.nextInt()-1;
				adjList.get(src).add(new Edge(src, dst)); adjList.get(dst).add(new Edge(dst, src));
			}
			boolean[][] adjMatrix = buildDualGraph(adjList, n);
			int mi = 0, mx = points[mi].x;
			for (int i = 1; i < n; ++i) if (mx > points[i].x) { mi = i; mx = points[i].x; }
			int out = adjList.get(mi).get(0).region;
			
			n = adjMatrix.length;
			int[] dist = new int[n];
			Arrays.fill(dist, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(out);
			dist[out] = 0;
			while (!q.isEmpty()) {
				int p = q.poll();
				for (int r = 0; r < n; ++r) if (adjMatrix[p][r] && dist[r] == -1) {
					dist[r] = dist[p] + 1;
					q.add(r);
				}
			}
			int ans = -1;
			for (int d : dist) ans = Math.max(ans, d);
			System.out.println(ans);
		}
	}
	static boolean[][] buildDualGraph(List<List<Edge>> adjList, int n) {
		for (List<Edge> l : adjList) Collections.sort(l);
		for (int i = 0; i < n; ++i) for (Edge e : adjList.get(i)) {
			int idx = -1;
			while (adjList.get(e.dst).get(++idx).dst != i);
			e.rev = idx;
		}
		int regionID = 0;
		for (int i = 0; i < n; ++i) for (int j = 0; j < adjList.get(i).size(); ++j) 
			if (adjList.get(i).get(j).region == -1) setRegion(adjList, i, j, regionID++);
		boolean[][] adjMatrix = new boolean[regionID][regionID];
		for (List<Edge> l : adjList) for (int j = 0, k = l.size()-1; j < l.size(); k = j++) {
			int src = l.get(k).region, dst = l.get(j).region;
			adjMatrix[src][dst] = adjMatrix[dst][src] = true;
		}
		return adjMatrix;
	}
	static void setRegion(List<List<Edge>> adjList, int i, int j, int regionID) {
		while (true) {
			Edge e = adjList.get(i).get(j);
			int ni = e.dst, nj = (e.rev + 1) % adjList.get(ni).size();
			Edge ne = adjList.get(ni).get(nj);
			if (ne.region != -1) break;
			ne.region = regionID;
			i = ni; j = nj;
		}
	}
	static class Edge implements Comparable<Edge> {
		int region = -1, dst, rev;
		double arg;
		Edge(int src, int dst) { 
			this.dst = dst;
			this.arg = Math.atan2(points[dst].y - points[src].y, points[dst].x - points[src].x);
		}
		@Override public int compareTo(Edge o) { return Double.compare(this.arg, o.arg); }
	}
}
