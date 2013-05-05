package algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import static algorithm.graph.GraphElement.INF;
import static algorithm.graph.GraphElement.Edge;
public class APSPAlgorithm {
	private APSPAlgorithm(){}
	/** Results of All-Pairs Shortest Path (APSP) Algorithm */
	public static class APSPResult{
		private int[][] dist; // dist[d]:= shortest path to d
		private int[][] prev; // back pointers
		private boolean hasNegativeCycle;
		public APSPResult(int[][] dist, int[][] prev, boolean hasNegativeCycle){ set(dist, prev, hasNegativeCycle); }
		final private void set(int[][] dist, int[][] prev, boolean hasNegativeCycle){ this.dist = dist; this.prev = prev; this.hasNegativeCycle = hasNegativeCycle; }
		final public int getDist(int i, int j){ return dist[i][j]; }
		final public int getPrev(int i, int j){ return prev[i][j]; }
		final public boolean isNegativeCycle(){ return hasNegativeCycle; }
		/**
		 * builds the shortest path from results of All-Pairs Shortest Path (APSP) Algorithm<br>
		 * AOJ No. 0155
		 * @param s	source node
		 * @param d	destination node
		 * @return	the shortest path from s to d
		 */
		public List<Integer> buildPath(int s, int d){
			LinkedList<Integer> path = new LinkedList<Integer>();
			if(dist[s][d] < INF) for(int u = d; u >= 0; u = prev[s][u]) path.addFirst(u);
			return path;
		}
	}
	/**
	 * Gets all-pairs shortest distances and back pointers for building a shortest path 
	 * via Floyd-Warshall algorithm(O(|V|^3)).<br>
	 * AOJ No. 0117, 0144, 0155, 0157, 0189, 0200
	 * @param G	adjacency matrix(|V|×|V|) (※edge weights can be negative)
	 * @return	APSPResult
	 */
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
	/**
	 * Gets all-pairs shortest distances and back pointers for building a shortest path 
	 * Via Johnson's algorithm(O(|V|^2 + |V||E|log|V|)).<br>
	 * AOJ No. 0157
	 * @param list	adjacency list (※edge weights can be negative)
	 * @return	APSPResult
	 */
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
}
