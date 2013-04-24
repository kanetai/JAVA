import java.util.*;

public class aoj0086 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 101, n1 = UndirectedEG.TERMINAL_NODE1.ordinal(), n2 = UndirectedEG.TERMINAL_NODE2.ordinal();
	public static void main(String[] args) {
		while(stdin.hasNext()){
			List<List<Edge>> list = new ArrayList<List<Edge>>(N);
			for(int i = 0; i < N; ++i) list.add(new ArrayList<Edge>(N));
			while(true){
				int src = stdin.nextInt(), dst = stdin.nextInt();
				if(src==0 && dst==0) break;
				list.get(src).add(new Edge(src, dst, 1)); list.get(dst).add(new Edge(dst, src, 1));
			}
			System.out.println(undirectedEulerPath(list, 1, 2) != null ? "OK" : "NG" );
		}
	}
	public static class Edge {
		protected int s, d, w;
		Edge(int s, int d, int w){ set(s, d, w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
	}
	public static enum UndirectedEG { TERMINAL_NODE1, TERMINAL_NODE2, DEGREE; } //index of returned array
	public static final List<Integer> undirectedEulerPath(List<List<Edge>> adjList, int s, int g) {
		LinkedList<Integer> path = buildUndirectedEulerPath(adjList, s, isUndirectedEulerGraph(adjList));
		return path != null && path.getLast() == g ? path : null;
	}
	public static final int[] isUndirectedEulerGraph(List<List<Edge>> adjList){
		int odd = 0, n = adjList.size();
		int ret[] = {-1, -1, 0}, n1 = UndirectedEG.TERMINAL_NODE1.ordinal();
		int n2 = UndirectedEG.TERMINAL_NODE2.ordinal(), deg = UndirectedEG.DEGREE.ordinal();
		for(int i = 0; i < n; ++i){
			int size = adjList.get(i).size();
			ret[deg] += size;
			if((size&1) == 1){ ++odd; ret[ret[n1] == -1 ? n1 : n2] = i; }
		}
		if(odd != 0 && odd != 2) return null;
		ret[deg] >>= 1;
		return ret;
	}
	private static LinkedList<Integer> buildUndirectedEulerPath(List<List<Edge>> adjList, int s, int[] result){
		int n1 = UndirectedEG.TERMINAL_NODE1.ordinal(), n2 = UndirectedEG.TERMINAL_NODE2.ordinal(), n = adjList.size();
		if (result != null && (result[n1] == -1 || (result[n1] == s || result[n2] == s))) {
			int[][] adj = new int[n][n];
			for(List<Edge> l: adjList) for(Edge e: l) ++adj[e.s][e.d];
			LinkedList<Integer> path = new LinkedList<Integer>();
			undirectedVisit(adjList, adj, s, path);
			if(path.size() == result[UndirectedEG.DEGREE.ordinal()] + 1) return path; //connected
		}
		return null;
	}
	private static void undirectedVisit(List<List<Edge>> adjList, int[][] adjMatrix, int s, LinkedList<Integer> path) {
		for(Edge e: adjList.get(s)) if(adjMatrix[e.s][e.d] != 0) {
			--adjMatrix[e.s][e.d]; --adjMatrix[e.d][e.s];
			undirectedVisit(adjList, adjMatrix, e.d, path);
		}
		path.addFirst(s);
	}
}
