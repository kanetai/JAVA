package algorithm.graph;
import java.util.ArrayList;
import java.util.List;
public final class GraphElement{
	static final int INF = Integer.MAX_VALUE/2;
	/** Edge of Graph */
	public static class Edge implements Comparable<Edge>{
		public int s; //source node		
		public int d; //destination node
		public int w; //edge weight
		Edge(int s, int d, int w){ set(s, d, w); }
		Edge(Edge o){ set(o.s, o.d, o.w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
		@Override public int compareTo(Edge o) { return w < o.w ? -1 : w == o.w ? 0 : 1; }
	}

	/**
	 * Converts adjacency matrix into adjacency list.<br>
	 * AOJ No. 0157
	 * @param G adjacency matrix
	 * @return  adjacency list
	 */
	public static List<List<Edge>> convertToAdjacencyList(int[][] G){
		int n = G.length;
		List<List<Edge>> list = new ArrayList<List<Edge>>(n);
		for(int i = 0; i < n; ++i){
			list.add(new ArrayList<Edge>());
			for(int j = 0; j < n; ++j) if(G[i][j] < INF) list.get(i).add(new Edge(i, j, G[i][j]));
		}
		return list;
	}
	/**
	 * Gets inverse graph of specified graph<br>
	 * AOJ No. 0237
	 * @param adjList adjacency list
	 * @return adjacency list of inverse graph
	 */
	public static List<List<Edge>> inverseAdjacencyList(List<List<Edge>> adjList) {
		int n = adjList.size();
		List<List<Edge>> ret = new ArrayList<List<Edge>>();
		for (int i = 0; i < n; ++i) ret.add(new ArrayList<Edge>());
		for (List<Edge> l : adjList) for (Edge e : l) ret.get(e.d).add(new Edge(e.d, e.s, e.w));
		return ret;
	}
}
