package algorithm.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import algorithm.graph.GraphElement.Edge;

public class EulerGraph {
	public static class UndirectedEG {
		private static class Result {
			int terminal1 = -1, terminal2 = -1, edgeNum = 0;
			public boolean isEulerGraph() { return terminal1 == -1 /*&& terminal2 == -1*/; }
		}
		/**
		 * Calculates undirected (semi) Euler path. O(|E|)
		 * @param adjList 	adjacency list
		 * @param s			start node
		 * @return			Euler Path. emptyIntegerList -> Euler Path (with start node s) doesn't exists.
		 */
		public static final List<Integer> eulerPath(List<List<Edge>> adjList, int s) {
			return buildEulerPath(adjList, s, isEulerGraph(adjList));
		}
		/**
		 * Calculates undirected (semi) Euler path.O(|E|)<br>
		 * AOJ No. 0086
		 * @param adjList	adjacency List
		 * @param s			start node
		 * @param g			goal node
		 * @return			Euler Path. emptyList -> Euler Path (with start node s, goal node g) doesn't exists.
		 */
		public static final List<Integer> eulerPath(List<List<Edge>> adjList, int s, int g) {
			List<Integer> path = buildEulerPath(adjList, s, isEulerGraph(adjList));
			return !path.isEmpty() && path.get(path.size()-1) == g ? path : Collections.<Integer>emptyList();
		}
		/**
		 * Calculates undirected (semi) Euler path.O(|E|)
		 * @param adjList	adjacency List
		 * @return			Euler Path. emptyList -> Euler Path (with start node s, goal node g) doesn't exists.
		 */
		public static List<Integer> eulerPath(List<List<Edge>> adjList) {
			Result res = isEulerGraph(adjList);
			return buildEulerPath(adjList, (res != null && !res.isEulerGraph() ? res.terminal1 : 0), res);
		}
		/**
		 * Analyzes whether the undirected graph represented by specified adjacency list is (semi) Euler Graph or not.
		 * @param adjList	adjacency list
		 * @return			Result object or null if specified graph is not (semi) Euler graph.<br>
		 * 					※If target graph has Euler cycle, Result.terminal1 and Resultret.terminal2 are -1.
		 */
		public static final Result isEulerGraph(List<List<Edge>> adjList){
			int odd = 0, n = adjList.size();
			Result ret = new Result();
			for(int i = 0; i < n; ++i){
				int deg = 0;
				for (Edge e: adjList.get(i)) deg += (e.s == e.d ? 2 : 1);
				ret.edgeNum += deg;
				if((deg&1) == 1){
					++odd;
					if (ret.terminal1 == -1) ret.terminal1 = i;
					else ret.terminal2 = i;
				}
			}
			if (odd == 0 || odd == 2) {
				ret.edgeNum /= 2; //because include outer and inner degree.
				return ret;
			}
			return null;
		}
		/**
		 * Creates undirected (semi) Euler path.
		 * @param adjList	adjacency list
		 * @param s			source node
		 * @param result	result of isEulerGraph()
		 * @return			(semi) Euler path or Empty list if Euler path (with start node s) doesn't exist.
		 */
		private static List<Integer> buildEulerPath(List<List<Edge>> adjList, int s, Result result){
			int n = adjList.size();
			if (result != null && (result.isEulerGraph() || (result.terminal1 == s || result.terminal2 == s))) {
				LinkedList<Integer> path = new LinkedList<Integer>();
				int[][] adj = new int[n][n];
				for(List<Edge> l: adjList) for(Edge e: l) ++adj[e.s][e.d];
				visit(adjList, adj, s, path);
				if(path.size() == result.edgeNum + 1) return path; //connected
			}
			return Collections.emptyList();
		}
		private static void visit(List<List<Edge>> adjList, int[][] adjMatrix, int s, LinkedList<Integer> path) {
			for(final Edge e: adjList.get(s)) if(adjMatrix[e.s][e.d] != 0) {
				--adjMatrix[e.s][e.d];
				if (e.d != e.s) --adjMatrix[e.d][e.s];
				visit(adjList, adjMatrix, e.d, path);
			}
			path.addFirst(s);
		}	
	}
	
	public static class DirectedEG {
		private static class Result {
			int src = -1, dst = -1, arcNum = 0, deg[];
			private Result(int numNode) { deg = new int[numNode]; }
			private boolean isEulerGraph() { return src == -1 /*&& dst == -1*/; }
		}
		/**
		 * Calculates (semi) Euler path. O(|A|)
		 * @param adjList 	adjacency list
		 * @param src		start node
		 * @return			Euler Path. emptyIntegerList -> Euler Path (with start node s) doesn't exists.
		 */
		public static List<Integer> eulerPath(List<List<Edge>> adjList, int src) {
			return buildEulerPath(adjList, src, isEulerGraph(adjList));
		}
		/**
		 * Calculates (semi) Euler path.O(|A|)
		 * @param adjList	adjacency List
		 * @param s			start node
		 * @param g			goal node
		 * @return			Euler Path. emptyList -> Euler Path (with start node s, goal node g) doesn't exists.
		 */
		public static List<Integer> eulerPath(List<List<Edge>> adjList, int src, int dst) {
			List<Integer> path = buildEulerPath(adjList, src, isEulerGraph(adjList));
			return !path.isEmpty() && path.get(path.size() - 1) == dst ? path : Collections.<Integer>emptyList();
		}
		/**
		 * Calculates (semi) Euler path.O(|A|)<br>
		 * AOJ No. 0225
		 * @param adjList	adjacency List
		 * @return			Euler Path. emptyList -> Euler Path (with start node s, goal node g) doesn't exists.
		 */
		public static List<Integer> eulerPath(List<List<Edge>> adjList) {
			Result res = isEulerGraph(adjList);
			return buildEulerPath(adjList, (res != null && !res.isEulerGraph() ? res.src : 0), res);
		}
		/**
		 * Analyzes whether the digraph represented by specified adjacency list is (semi) Euler Graph or not.
		 * @param adjList	adjacency list
		 * @return			Result object or null if specified graph is not (semi) Euler graph.<br>
		 * 					※If target graph has Euler cycle, Result.src and Result.dst are -1.
		 */
		private static Result isEulerGraph(List<List<Edge>> adjList) {
			int n = adjList.size();
			Result ret = new Result(n);
			for (int i = 0; i < n; ++i) {
				int outDeg = adjList.get(i).size();
				ret.arcNum += outDeg;
				for (Edge e : adjList.get(i)) ret.deg[e.d]--; //in-deg
				ret.deg[i] += outDeg; //out-deg
			}
			boolean isEG = true, isSemiEG = true;
			for (int i = 0, k = 0; i < n && isSemiEG; ++i) {
				if (ret.deg[i] == -1) {
					if (ret.dst == -1) ret.dst = i;
					else isSemiEG = false;
				} else if (ret.deg[i] == 1) {
					if (ret.src == -1) ret.src = i;
					else isSemiEG = false;
				} else if (ret.deg[i] != 0) {
					++k;
					isEG = false;
				}
				if (k > 2) isSemiEG = false;
			}
			return (isEG || isSemiEG) ? ret : null;
		}
		/**
		 * Creates (semi) Euler path.
		 * @param adjList	adjacency list
		 * @param s			source node
		 * @param result	result of isEulerGraph()
		 * @return			(semi) Euler path or Empty list if Euler path (with start node s) doesn't exist.
		 */
		private static List<Integer> buildEulerPath(List<List<Edge>> adjList, int s, Result result){
			int n = adjList.size();
			if (result != null && (result.isEulerGraph() || result.src == s )) {
				LinkedList<Integer> path = new LinkedList<Integer>();
				int[][] adj = new int[n][n];
				for(List<Edge> l: adjList) for(Edge e: l) ++adj[e.s][e.d];
				visit(adjList, adj, s, path);
				if(path.size() == result.arcNum + 1) return path; //connected
			}
			return Collections.emptyList();
		}
		private static void visit(List<List<Edge>> adjList, int[][] adjMatrix, int s, LinkedList<Integer> path) {
			for(final Edge e: adjList.get(s)) if(adjMatrix[e.s][e.d] != 0) {
				--adjMatrix[e.s][e.d];
				visit(adjList, adjMatrix, e.d, path);
			}
			path.addFirst(s);
		}
	}
}
