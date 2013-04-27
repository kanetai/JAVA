package algorithm.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import algorithm.graph.GraphElement.Edge;
import static algorithm.Utility.emptyIntArray;
public class EulerGraph {
	public static enum DirectedEG { SRC_NODE, DST_NODE, INDEG, OUTDEG; }
	public static enum UndirectedEG { TERMINAL_NODE1, TERMINAL_NODE2, DEGREE; } //index of array returned by isUndirectedEulerGraph
	/**
	 * Calculates undirected (semi) Euler path. O(|E|)
	 * @param adjList 	adjacency list
	 * @param s			start node
	 * @return			Euler Path. emptyIntegerList -> Euler Path (with start node s) doesn't exists.
	 */
	public static final List<Integer> undirectedEulerPath(List<List<Edge>> adjList, int s) {
		return buildUndirectedEulerPath(adjList, s, isUndirectedEulerGraph(adjList));
	}
	/**
	 * Calculates undirected (semi) Euler path.O(|E|)<br>
	 * AOJ No. 0086
	 * @param adjList	adjacency List
	 * @param s			start node
	 * @param g			goal node
	 * @return			Euler Path. emptyList -> Euler Path (with start node s, goal node g) doesn't exists.
	 */
	public static final List<Integer> undirectedEulerPath(List<List<Edge>> adjList, int s, int g) {
		LinkedList<Integer> path = buildUndirectedEulerPath(adjList, s, isUndirectedEulerGraph(adjList));
		return !path.isEmpty() && path.getLast() == g ? path : Collections.<Integer>emptyList();
	}
	/**
	 * Analyzes whether the undirected graph represented by specified adjacency list is Euler Graph or not.
	 * If the Graph is not Euler Graph, returns emptyIntArray.
	 * In other case, returns an array(ret) containing item of enum UndirectedEG.
	 * If target graph has Euler cycle, ret[TERMINAL_NODE1] and ret[TERMINAL_NODE2] is -1.
	 * @param adjList	adjacency list
	 * @return			ret. emptyIntArray -> not Euler graph. 
	 */
	public static final int[] isUndirectedEulerGraph(List<List<Edge>> adjList){
		int odd = 0, n = adjList.size();
		int ret[] = {-1, -1, 0}, n1 = UndirectedEG.TERMINAL_NODE1.ordinal();
		int n2 = UndirectedEG.TERMINAL_NODE2.ordinal(), deg = UndirectedEG.DEGREE.ordinal();
		for(int i = 0; i < n; ++i){
			int size = adjList.get(i).size();
			ret[deg] += size;
			if((size&1) == 1){ ++odd; ret[ret[n1] == -1 ? n1 : n2] = i; }
		}
		if(odd != 0 && odd != 2) return emptyIntArray;
		ret[deg] >>= 1;
		return ret;
	}
	/**
	 * Creates undirected (semi) Euler path.
	 * @param adjList	adjacency list
	 * @param s			source node
	 * @param result	result of isUndirectedEulerGraph
	 * @return			Euler path. empty list -> Euler path (with start node s) doesn't exist.
	 */
	private static LinkedList<Integer> buildUndirectedEulerPath(List<List<Edge>> adjList, int s, int[] result){
		int n1 = UndirectedEG.TERMINAL_NODE1.ordinal(), n2 = UndirectedEG.TERMINAL_NODE2.ordinal(), n = adjList.size();
		LinkedList<Integer> path = new LinkedList<Integer>();
		if (result.length != 0 && (result[n1] == -1 || (result[n1] == s || result[n2] == s))) {
			int[][] adj = new int[n][n];
			for(List<Edge> l: adjList) for(Edge e: l) ++adj[e.s][e.d];
			undirectedVisit(adjList, adj, s, path);
			if(path.size() == result[UndirectedEG.DEGREE.ordinal()] + 1) return path; //connected
		}
		path.clear(); return path; //empty list
	}
	private static void undirectedVisit(List<List<Edge>> adjList, int[][] adjMatrix, int s, LinkedList<Integer> path) {
		for(Edge e: adjList.get(s)) if(adjMatrix[e.s][e.d] != 0) {
			--adjMatrix[e.s][e.d]; --adjMatrix[e.d][e.s];
			undirectedVisit(adjList, adjMatrix, e.d, path);
		}
		path.addFirst(s);
	}
}
