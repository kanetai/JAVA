package algorithm.graph.networkFlow;
import java.util.*;
import algorithm.graph.GraphElement.Edge;
public class NetworkFlowAlgorithm {
	private NetworkFlowAlgorithm() {}
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
		/**
		 * Gets maximum flow of specified flow network(G, s, t).
		 * @param adjList  (in)  adjacency list of graph G. Edge weight indicates capacity.
		 * @param s        (in)  source node.
		 * @param t        (in)  sink node.
		 * @return         Result.maximumFlow:= maximum flow, Result.flow[u][v] = flow of edge(u,v).
		 */
		public Result solve(final List<List<Edge>> adjList, int s, int t);
	}
}
