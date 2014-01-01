package algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import static algorithm.graph.GraphElement.INF;
import algorithm.graph.GraphElement.Edge;

public class SSSPAlgorithm {
	private SSSPAlgorithm(){}
	/** Results of Single-Source Shortest Path (SSSP) Algorithm */
	public static class SSSPResult{
		private int[] dist; // dist[d]:= shortest path to d
		private int[] prev; // back pointers
		private boolean hasNegativeCycle;
		public SSSPResult(int[] dist, int[] prev, boolean hasNegativeCycle){ set(dist, prev, hasNegativeCycle); }
		final private void set(int[] dist, int[] prev, boolean hasNegativeCycle){ this.dist = dist; this.prev = prev; this.hasNegativeCycle = hasNegativeCycle; }
		final public int getDist(int i){ return dist[i]; }
		final public int getPrev(int i){ return prev[i]; }
		final public boolean hasNegativeCycle(){ return hasNegativeCycle; }
		/**
		 * Builds the shortest path from results of Single-Source Shortest Path (SSSP) Algorithm<br>
		 * @param results	results of SSSP Algorithm
		 * @param d		destination node
		 * @return		the shortest path to d
		 */
		public List<Integer> buildPath(int d){
			LinkedList<Integer> path = new LinkedList<Integer>();
			if(dist[d] < INF) for(int u = d; u >= 0; u = prev[u]) path.addFirst(u);
			return path;
		}
	}
	/**
	 * Gets single-source shortest distances and back pointers for building a shortest path 
	 * via Dijkstra's algorithm(naive O(|V|^2)).<br>
	 * @param G	adjacency matrix(|V|×|V|) (※edge weights must be positive)
	 * @param s	source node
	 * @return	SSSPResult
	 */
	public static SSSPResult Dijkstra(int[][] G, int s){
		int n = G.length;
		int[] dist = new int[n]; Arrays.fill(dist, INF);
		int[] prev = new int[n]; Arrays.fill(prev, -1);
		boolean[] visited = new boolean[n]; Arrays.fill(visited, false);

		dist[s] = 0;
		while(true){
			int v = -1;
			for(int u = 0; u < n; ++u)
				if(!visited[u] && (v == -1 || dist[u] < dist[v])) v = u;
			if(v < 0) break;
			visited[v] = true;

			for(int u = 0; u < n; ++u){
				int newDist = dist[v] + G[v][u];
				if(dist[u] > newDist){
					dist[u] = newDist;
					prev[u] = v;
				}
			}
		}
		return new SSSPResult(dist, prev, false);
	}
	/**
	 * Gets single-source shortest distances and back pointers for building a shortest path 
	 * via Dijkstra's algorithm O(|E|log|V|).<br>
	 * AOJ No. 0212, 0215(partial modification)
	 * @param list	adjacency list (※edge weights must be positive)
	 * @param s	source node
	 * @return	SSSPResult
	 */
	public static SSSPResult Dijkstra(List<List<Edge>> list, int s){
		int n = list.size();
		int[] dist = new int[n]; Arrays.fill(dist, INF);
		int[] prev = new int[n]; Arrays.fill(prev, -1);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(n*n);

		dist[s] = 0;
		q.add(new Edge(-1, s, 0));
		while(!q.isEmpty()){
			Edge p = q.poll();
			int v = p.d;
			if(prev[v] != -1) continue; //dist[v] < p.weight
			prev[v] = p.s;
			for(final Edge u: list.get(v)){ //※u.s = v
				int newDist = dist[v] + u.w;
				if(dist[u.d] > newDist){
					dist[u.d] = newDist;
					q.add(new Edge(u.s, u.d, dist[u.d]));
				}
			}
		}
		return new SSSPResult(dist, prev, false);
	}
	/**
	 * Gets single-source shortest distance and back pointers for building a shortest path 
	 * via Bellman-Ford algorithm O(|V||E|).<br>
	 * AOJ No. 0157
	 * @param list	adjacency list (※edge weights can be negative)
	 * @param s	source node
	 * @return	SSSPResult
	 */
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
