import java.util.*;
public class aoj0086 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 101;
	public static void main(String[] args) {
		while(stdin.hasNext()){
			List<List<Edge>> list = new ArrayList<List<Edge>>(N);
			for(int i = 0; i < N; ++i) list.add(new ArrayList<Edge>(N));
			while(true){
				int src = stdin.nextInt(), dst = stdin.nextInt();
				if(src==0 && dst==0) break;
				list.get(src).add(new Edge(src, dst, 1)); 
				if (src != dst) list.get(dst).add(new Edge(dst, src, 1));
			}
			System.out.println(eulerPath(list, 1, 2).isEmpty() ? "NG" : "OK" );
		}
	}
	public static class Edge {
		protected int s, d, w;
		Edge(int s, int d, int w){ set(s, d, w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
	}
	private static class Result {
		int terminal1 = -1, terminal2 = -1, edgeNum = 0;
		public boolean isEulerGraph() { return terminal1 == -1 /*&& terminal2 == -1*/; }
	}
	public static final List<Integer> eulerPath(List<List<Edge>> adjList, int s) {
		return buildEulerPath(adjList, s, isEulerGraph(adjList));
	}
	public static final List<Integer> eulerPath(List<List<Edge>> adjList, int s, int g) {
		List<Integer> path = buildEulerPath(adjList, s, isEulerGraph(adjList));
		return !path.isEmpty() && path.get(path.size()-1) == g ? path : Collections.<Integer>emptyList();
	}
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
	private static List<Integer> buildEulerPath(List<List<Edge>> adjList, int s, Result result){
		int n = adjList.size();
		if (result != null && (result.isEulerGraph() || (result.terminal1 == s || result.terminal2 == s))) {
			LinkedList<Integer> path = new LinkedList<Integer>();
			int[][] adj = new int[n][n];
			for(List<Edge> l: adjList) for(Edge e: l) ++adj[e.s][e.d];
			visit(adjList, adj, s, path);
			if(path.size() == result.edgeNum + 1) return path; //connected
		}
		return Collections.<Integer>emptyList();
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
