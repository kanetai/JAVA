package algorithm.graph;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import algorithm.graph.GraphElement.Edge;
public final class MinimumSpanningTree {
	private MinimumSpanningTree(){}
	/** Result of Minimum Spanning Tree Algorithm */
	public static class MSTResult{
		private int totalCost;
		private List<Edge> edgeList;
		public MSTResult(int totalCost, List<Edge> edgeList){ this.totalCost = totalCost; this.edgeList = edgeList; }
		public final int getTotalCost(){ return totalCost; }
		public final List<Edge> getEdgeList(){ return edgeList; }
	}
	/**
	 * Calculates total edge cost and edge list of minimum spanning tree via Prim's algorithm O(|E|log|V|).<br>
	 * @param adjList	adjacency list (※edge weights can be negative)
	 * @param s	source 	node
	 * @return	MSTResult
	 */
	public static MSTResult Prim(List<List<Edge>> adjList, int s) {
		int n = adjList.size(), totalCost = 0;
		List<Edge> T = new LinkedList<Edge>();
		boolean[] visited = new boolean[n];

		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.add( new Edge(-1, s, 0) );
		while(!q.isEmpty()){
			Edge e = q.poll();
			if(visited[e.d]) continue;
			T.add(e);
			totalCost += e.w;
			visited[e.d] = true;
			for(Edge f: adjList.get(e.d)) if(!visited[f.d]) q.add(f);
		}
		return new MSTResult(totalCost, T);
	}
	/**
	 * Calculates total edge cost and edge list of minimum spanning tree via Prim's algorithm O(|E|log|V|)<br>
	 * AOJ No. 0072
	 * @param adjList	adjacency list (※edge weights can be negative)
	 * @return	MSTResult
	 */
	public static MSTResult Prim(List<List<Edge>> list){ return Prim(list, 0); }
}
