import java.util.*;
public class aoj0225 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			int ID = 0;
			Map<Character, Integer> chtoid = new HashMap<Character, Integer>();
			String[] words = new String[n];
			for (int i = 0; i < n; ++i) {
				String w = stdin.next();
				words[i] = w;
				char prefix = w.charAt(0), postfix = w.charAt(w.length()-1);
				if (!chtoid.containsKey(prefix)) chtoid.put(prefix, ID++);
				if (!chtoid.containsKey(postfix)) chtoid.put(postfix, ID++);
			}
			int N = chtoid.size(); // == ID
			List<List<Edge>> adjList = new ArrayList<List<Edge>>();
			for (int i = 0; i < N; ++i) adjList.add(new ArrayList<Edge>());
			for (String word: words) {
				int prefix = chtoid.get(word.charAt(0)), postfix = chtoid.get(word.charAt(word.length()-1));
				adjList.get(prefix).add(new Edge(prefix, postfix, 1));
			}
			List<Integer> path = eulerPath(adjList);
			System.out.println(!path.isEmpty() && path.get(0) == path.get(path.size()-1) ? "OK" : "NG");
		}
	}
	public static class Edge {
		protected int s, d, w;
		Edge(int s, int d, int w){ set(s, d, w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
		@Override public String toString() { return "(" + s +"," + d + ")"; }
	}
	private static class Result {
		int src = -1, dst = -1, arcNum = 0, deg[];
		private Result(int numNode) { deg = new int[numNode]; }
		private boolean isEulerGraph() { return src == -1 /*&& dst == -1*/; }
	}
	public static List<Integer> eulerPath(List<List<Edge>> adjList, int src) {
		return buildEulerPath(adjList, src, isEulerGraph(adjList));
	}
	public static List<Integer> eulerPath(List<List<Edge>> adjList) {
		Result res = isEulerGraph(adjList);
		return buildEulerPath(adjList, (res != null && !res.isEulerGraph() ? res.src : 0), res);
	}
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
	private static List<Integer> buildEulerPath(List<List<Edge>> adjList, int s, Result result){
		int n = adjList.size();
		if (result != null && (result.isEulerGraph() || result.src == s )) {
			LinkedList<Integer> path = new LinkedList<Integer>();
			int[][] adj = new int[n][n];
			for(List<Edge> l: adjList) for(Edge e: l) ++adj[e.s][e.d];
			visit(adjList, adj, s, path);
			if(path.size() == result.arcNum + 1) return path; //connected
		}
		return Collections.emptyList(); //empty list
	}
	private static void visit(List<List<Edge>> adjList, int[][] adjMatrix, int s, LinkedList<Integer> path) {
		for(final Edge e: adjList.get(s)) if(adjMatrix[e.s][e.d] != 0) {
			--adjMatrix[e.s][e.d];
			visit(adjList, adjMatrix, e.d, path);
		}
		path.addFirst(s);
	}
}
