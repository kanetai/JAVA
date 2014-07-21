import java.util.*;
public class uva_0820 {
	static final Scanner stdin = new Scanner(System.in);
	static final MaximumFlowAlgorithm solver = MaximumFlowAlgorithm.Dinic;
	public static void main(String[] args) {
	    for (int id = 1; ;++id) {
	    	int n = stdin.nextInt();
	    	if (n == 0) break;
	    	List<List<Edge>> adjList = new ArrayList<>(n);
	    	for (int i = 0; i < n; ++i) adjList.add(new ArrayList<Edge>());
	    	int s = stdin.nextInt()-1, t = stdin.nextInt()-1, c = stdin.nextInt();
	        while (c-- > 0) {
	        	int src = stdin.nextInt()-1, dst = stdin.nextInt()-1, cap = stdin.nextInt();
	            adjList.get(src).add(new Edge(src, dst, cap));
	            adjList.get(dst).add(new Edge(dst, src, cap));
	        }
	        System.out.println("Network " + id);
	        System.out.println("The bandwidth is " + solver.solve(adjList, s, t).maximumFlow + ".\n");
	    }
	}
	public enum MaximumFlowAlgorithm implements MaximumFlowSolver {
		EdmondsKarp { /** O(|V||E|^2) */
			@Override public Result solve(List<List<Edge>> adjList, int s, int t) {
				Result res = new Result(adjList);
				while (augmentingPathExists(res, adjList, s, t)) {
					int cp = INF;
					for (int j = t; res.trace[j] != j; j = res.trace[j])
						cp = Math.min(cp, res.residualCapacity(res.trace[j], j));
					for (int j = t; res.trace[j] != j; j = res.trace[j]) {
						res.flow[res.trace[j]][j] += cp;
						res.flow[j][res.trace[j]] -= cp;
					}
					res.maximumFlow += cp;
				}
				return res;
			}
			private boolean augmentingPathExists(final Result res, final List<List<Edge>> adjList, int s, int t) { //BFS
				Arrays.fill(res.trace, -1);
				Queue<Integer> q = new LinkedList<Integer>();
				q.add(s); res.trace[s] = s;
				while (!q.isEmpty() && res.trace[t] == -1) {
					int u = q.poll();
					for(final Edge e : adjList.get(u)) if (res.trace[e.d] == -1 && res.residualCapacity(u, e.d) > 0) {
						res.trace[e.d] = u;
						q.add(e.d);
					}
				}
				return (res.trace[t] >= 0); // trace[x] == -1 <=> t-side
			}
		}, Dinic { /** O(|V|^2|E|) */
			@Override public Result solve(List<List<Edge>> adjList, int s, int t) {
				Result res = new Result(adjList);
				int n = adjList.size();
				boolean[] used = new boolean[n];
				for (boolean augmented = true; augmented; ) {
					augmented = false;
					calcLayreLevel(res, adjList, s, t); //make layered network
					Arrays.fill(used, false);
					while (true) {
						int f = augment(used, res, adjList, s, t, INF);
						if (f == 0) break;
						res.maximumFlow += f; augmented = true;
					}
				}
				return res;
			}
			private void calcLayreLevel(Result res, final List<List<Edge>> adjList, int s, int t) {
				Arrays.fill(res.trace, -1); res.trace[s] = 0; //trace[v] indicates level of v.
				Queue<Integer> q = new LinkedList<Integer>(); q.add(s);
				for (int lm = adjList.size(); !q.isEmpty() && res.trace[q.peek()] < lm; ) { //BFS
					int u = q.poll();
					if (u == t) lm = res.trace[u];
					for (Edge e : adjList.get(u)) if (res.trace[e.d] == -1 && res.residualCapacity(u, e.d) > 0) {
						res.trace[e.d] = res.trace[u] + 1;
						q.add(e.d);
					}
				}
			}
			private int augment(boolean[] used, Result res, final List<List<Edge>> adjList, int u, int t, int minC) { //DFS
				if (u == t) return minC;
				if (minC == 0 || used[u]) return 0;
				used[u] = true;
				for(Edge e : adjList.get(u)) if (res.trace[e.d] > res.trace[u]) { //level(e.d) > level(u)
					int f = augment(used, res, adjList, e.d, t, Math.min(minC, res.residualCapacity(u, e.d)));
					if (f > 0) {
						res.flow[u][e.d] += f; res.flow[e.d][u] -= f;
						used[u] = false;
						return f;
					}
				}
				return 0;
			}
		};
	}
	public static final int INF = Integer.MAX_VALUE/2;
	public interface MaximumFlowSolver {
		public class Result {
			final int[][] capacity, flow;
			final int[] trace;
			public int maximumFlow;
			public Result(final List<List<Edge>> adjList) {
				int n = adjList.size();
				capacity = new int[n][n]; flow = new int[n][n]; trace = new int[n];
				maximumFlow = 0;
				for (final List<Edge> row : adjList) for (final Edge e : row) capacity[e.s][e.d] += e.w;
			}
			protected int residualCapacity(int src, int dst) {
				return capacity[src][dst] - flow[src][dst];
			}
		}
		public Result solve(final List<List<Edge>> adjList, int s, int t);
	}
	public static class Edge {
		public int s, d, w;
		Edge(int s, int d, int w) { this.s = s; this.d = d; this.w = w; }
	}
}
