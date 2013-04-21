package algorithm.graph;
import java.util.List;
import java.util.ArrayList;
import algorithm.Utility;
public class GraphElement extends Utility{
	static final int INF = Integer.MAX_VALUE/2;
	/** Edge of Graph */
	public static class Edge implements Comparable<Edge>{
		protected int s; //source node		
		protected int d; //destination node
		protected int w; //edge weight
		Edge(int s, int d, int w){ set(s, d, w); }
		Edge(Edge o){ set(o.s, o.d, o.w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
		/**
		 * Converts adjacency matrix into adjacency list.<br>
		 * @param G adjacency matrix
		 * @return  adjacency list
		 */
		public static List<Edge>[] convertToAdjacencyList(int[][] G){
			int n = G.length;
			@SuppressWarnings("unchecked") List<Edge>[] list = new ArrayList[n];
			for(int i = 0; i < n; ++i){
				list[i] = new ArrayList<Edge>();
				for(int j = 0; j < n; ++j) if(G[i][j] < INF) list[i].add(new Edge(i, j, G[i][j]));
			}
			return list;
		}
		@Override public int compareTo(Edge o) { return w < o.w ? -1 : w == o.w ? 0 : 1; }
	}
}
