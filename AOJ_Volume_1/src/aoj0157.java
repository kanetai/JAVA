import java.util.*;
public class aoj0157 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2;
	static class T implements Comparable<T>{
		int h, r;
		T(int h, int r){ this.h = h; this.r = r; }
		T(T t){ h = t.h; r = t.r; }
		@Override public int compareTo(T o) { return (h != o.h ? h - o.h : r - o.r); }
		public final boolean less(T o){ return r < o.r && h < o.h; }
	}
	static Solver solver = Solver.LIS;
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			List<Integer> h = new ArrayList<Integer>(), r = new ArrayList<Integer>();
			while(n-- > 0){ h.add(stdin.nextInt()); r.add(stdin.nextInt()); }
			n = stdin.nextInt();
			while(n-- > 0){ h.add(stdin.nextInt()); r.add(stdin.nextInt()); }
			System.out.println(solver.solve(h,r));
		}
	}
	static enum Solver {
		LIS { @Override public int solve(List<Integer> h, List<Integer> r){
			int n = h.size();
			T[] x = new T[n];
			for(int i = 0; i < n; ++i) x[i] = new T(h.get(i), r.get(i));
			Arrays.sort(x);
			return _LIS(x).size();
		}}, FloydWarshall { @Override public int solve(List<Integer> h, List<Integer> r){
			initGraph(h,r);
			APSPResult res = FloydWarshall(G);
			for(int i = 0; i < n; ++i) for(int j = 0; j < n; ++j) ans = Math.min(ans, res.getDist(i, j));
			return 1-ans;
		}}, Johnson { @Override public int solve(List<Integer> h, List<Integer> r){
			initGraph(h,r);
			APSPResult res = Johnson(list);
			for(int i = 0; i < n; ++i) for(int j = 0; j < n; ++j) ans = Math.min(ans, res.getDist(i, j));
			return 1-ans;
		}}, BellmanFord { @Override public int solve(List<Integer> h, List<Integer> r){
			initGraph(h,r);
			for(int i = 0; i < n; ++i){
				SSSPResult res = BellmanFord(list, i);
				for(int j = 0; j < n; ++j) ans = Math.min(ans, res.getDist(j));
			}
			return 1-ans;
		}};
		static private int n, G[][], ans;
		static private List<List<Edge>> list;
		public int solve(List<Integer> h, List<Integer> r){ return 0; }
		static private void initGraph(List<Integer> h, List<Integer> r){
			n = h.size();
			G = new int[n][n]; //adjacency matrix
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					G[i][j] = (h.get(i) < h.get(j) && r.get(i) < r.get(j) ? -1 : INF);
			ans = INF;
			list = convertToAdjacencyList(G);
		}
	}
	public static List<T> _LIS(T[] x, int begin, int end){
		int n = end - begin;
		int[] DP = new int[n]; Arrays.fill(DP, 1);
		int[] prev = new int[n]; Arrays.fill(prev, -1);
		int lidx = 0;
		for(int i = 1; i < n; ++i){
			for(int j = 0; j < i; ++j)
				if(x[begin+j].less(x[begin+i]) && DP[i] < DP[j] + 1){ DP[i] = DP[j] + 1; prev[i] = j; }
			if(DP[lidx] < DP[i]) lidx = i;
		}
		LinkedList<T> lis = new LinkedList<T>();
		for(int i = lidx; i >= 0; i = prev[i]) lis.addFirst(new T(x[begin+i]));
		return lis;
	}
	public static List<T> _LIS(T[] x){ return _LIS(x, 0, x.length); }
	public static List<List<Edge>> convertToAdjacencyList(int[][] G){
		int n = G.length;
		List<List<Edge>> list = new ArrayList<List<Edge>>(n);
		for(int i = 0; i < n; ++i){
			list.add(new ArrayList<Edge>());
			for(int j = 0; j < n; ++j) if(G[i][j] < INF) list.get(i).add(new Edge(i, j, G[i][j]));
		}
		return list;
	}
	@SuppressWarnings("unused") public static class APSPResult{
		private int[][] dist; // dist[d]:= shortest path to d
		private int[][] prev; // back pointers
		private boolean hasNegativeCycle;
		public APSPResult(int[][] dist, int[][] prev, boolean hasNegativeCycle){ set(dist, prev, hasNegativeCycle); }
		final private void set(int[][] dist, int[][] prev, boolean hasNegativeCycle){ this.dist = dist; this.prev = prev; this.hasNegativeCycle = hasNegativeCycle; }
		final public int getDist(int i, int j){ return dist[i][j]; }
	}
	public static class Edge implements Comparable<Edge>{
		protected int s; //source node		
		protected int d; //destination node
		protected int w; //edge weight
		Edge(int s, int d, int w){ set(s, d, w); }
		Edge(Edge o){ set(o.s, o.d, o.w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
		@Override public int compareTo(Edge o) { return w < o.w ? -1 : w == o.w ? 0 : 1; }
	}
	public static APSPResult FloydWarshall(int[][] G){
		int n = G.length;
		int[][] prev = new int[n][n], dist = new int[n][n];
		for(int i = 0; i < n; ++i){
			System.arraycopy(G[i], 0, dist[i], 0, n);
			dist[i][i] = 0;
			for(int j = 0; j < n; ++j) prev[i][j] = (G[i][j] != INF ? i : -1);
		}
		for(int k = 0; k < n; ++k){
			for(int i = 0; i < n; ++i){
				if(dist[i][k] >= INF) continue;
				for(int j = 0; j < n; ++j){
					int newDist = dist[i][k] + dist[k][j];
					if(dist[i][j] > newDist){
						dist[i][j] = newDist;
						prev[i][j] = prev[k][j];
					}
				}
			}
		}
		boolean hasNegativeCycle = false;
		for(int i = 0; i < n; ++i) if(dist[i][i] < 0){ hasNegativeCycle = true; break; }
		return new APSPResult(dist, prev, hasNegativeCycle);
	}
	public static APSPResult Johnson(List<List<Edge>> list){
		int n = list.size();
		int[] h = new int[n]; Arrays.fill(h, 0);
		//Via Bellman-Ford, detect negative cycle and bias(w'(i,j) = w(i,j) + h(i) - h(j))
		boolean hasNegativeCycle = false, updated = true;
		for(int k = 1; k <= n && updated; ++k){
			hasNegativeCycle = (k==n);
			updated = false;
			for(int i = 0; i < n; ++i){
				for(Edge e: list.get(i)){
					int newDist = h[e.s] + e.w;
					if(h[e.d] > newDist){
						h[e.d] = newDist;
						updated = true;
						if(hasNegativeCycle) return new APSPResult(null, null, true);
					}
				}
			}
		}
		//Dijkstra
		int[][] dist = new int[n][n], prev = new int[n][n];
		for(int i = 0; i < n; ++i){ Arrays.fill(dist[i], INF); Arrays.fill(prev[i], -1); }
		for(int s = 0; s < n; ++s){
			PriorityQueue<Edge> q = new PriorityQueue<Edge>(n*n);
			dist[s][s] = 0;
			q.add(new Edge(-1, s, 0));
			while(!q.isEmpty()){
				Edge p = q.poll();
				int v = p.d;
				if(prev[s][v] != -1) continue;
				prev[s][v] = p.s;
				for(final Edge u: list.get(v)){
					int newDist = dist[s][v] + u.w + (h[u.s] - h[u.d]); //h[u.s] - h[u.d]: bias
					if(dist[s][u.d] > newDist){
						dist[s][u.d] = newDist;
						q.add(new Edge(u.s, u.d, dist[s][u.d]));
					}
				}
			}
			for(int d = 0; d < n; ++d) dist[s][d] -= (h[s] - h[d]); //convert into non-biased distances.
		}
		return new APSPResult(dist, prev, true);
	}
	@SuppressWarnings("unused") public static class SSSPResult{
		private int[] dist; // dist[d]:= shortest path to d
		private int[] prev; // back pointers
		private boolean hasNegativeCycle;
		public SSSPResult(int[] dist, int[] prev, boolean hasNegativeCycle){ set(dist, prev, hasNegativeCycle); }
		final private void set(int[] dist, int[] prev, boolean hasNegativeCycle){ this.dist = dist; this.prev = prev; this.hasNegativeCycle = hasNegativeCycle; }
		final public int getDist(int i){ return dist[i]; }
	}
	public static SSSPResult BellmanFord(List<List<Edge>> list, int s) {
		int n = list.size();
		int[] dist = new int[n]; Arrays.fill(dist, INF);
		int[] prev = new int[n]; Arrays.fill(prev, -1);
		boolean hasNegativeCycle = false, updated = true;
		dist[s] = 0;

		for(int k = 1; k <= n && updated; ++k){
			hasNegativeCycle = (k==n);
			updated = false;
			for(int i = 0; i < n; ++i){
				for(final Edge e: list.get(i)){
					int newDist = dist[e.s] + e.w;
					if(dist[e.d] > newDist){
						dist[e.d] = (hasNegativeCycle ? -INF : newDist);
						prev[e.d] = e.s;
						updated = true;
					}
				}
			}
		}
		return new SSSPResult(dist, prev, hasNegativeCycle);
	}
}