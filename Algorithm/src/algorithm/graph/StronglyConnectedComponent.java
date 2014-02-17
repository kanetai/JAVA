package algorithm.graph;
import java.util.*;
import algorithm.graph.GraphElement.Edge;
import static algorithm.graph.GraphElement.inverseAdjacencyList;
public class StronglyConnectedComponent {
	private static class SCCDFSArg {
		int num = 0;
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] used;
		List<List<Edge>> adjList;
		SCCDFSArg(int numV, List<List<Edge>> adjList) { used = new boolean[numV]; this.adjList = adjList; }
	}
	/** 
	 * Gets list of SCC(Strongly Connected Components) via Kosaraju's algorithm. (O(|V|+|E|))<br>
	 * AOJ 0237
	 * @param adjList AdjacencyList
	 * @return SCC
	 */
	public static List<Set<Integer>> Kosaraju(List<List<Edge>> adjList) {
		int n = adjList.size();
		SCCDFSArg arg = new SCCDFSArg(n, adjList);
		for (int v = 0; v < n; ++v) if (!arg.used[v]) postOrderNumbering(v, arg);
		
		Arrays.fill(arg.used, false);
		arg.adjList = inverseAdjacencyList(adjList);
		List<Set<Integer>> ret = new ArrayList<Set<Integer>>();
		while (!arg.stack.isEmpty()) {
			int v = arg.stack.pop();
			if (!arg.used[v]) {
				Set<Integer> scc = new HashSet<Integer>();
				addSCC(v, arg, scc);
				ret.add(scc);
				arg.num++; //group number
			}
		}
		return ret;
	}
	private static void postOrderNumbering(int v, SCCDFSArg arg) {
		arg.used[v] = true;
		for (Edge e : arg.adjList.get(v)) if (!arg.used[e.d]) postOrderNumbering(e.d, arg);
	    arg.stack.push(v);
	}
	private static void addSCC(int v, SCCDFSArg arg, Set<Integer> scc) {
		arg.used[v] =  true;
		scc.add(v);
		for (Edge e : arg.adjList.get(v)) if (!arg.used[e.d]) addSCC(e.d, arg, scc);
	}
	/** 
	 * Gets list of SCC(Strongly Connected Components). (O(|V|+|E|))<br>
	 * AOJ 0237
	 * @param adjList AdjacencyList
	 * @return SCC
	 */
	public static List<Set<Integer>> getStronglyConnectedComponents(List<List<Edge>> adjList) {
		ArrayList<Set<Integer>> ret = new ArrayList<Set<Integer>>();
		int n = adjList.size();
		int[] num = new int[n], low = new int[n];
		SCCDFSArg arg = new SCCDFSArg(n, adjList);
		for (int v = 0; v < n; ++v) if (num[v] == 0) sccDFS(v, arg, low, num, ret);
		return ret;
	}
	private static void sccDFS(int v, SCCDFSArg arg, int[] low, int[] num, List<Set<Integer>> SCCs) {
		low[v] = num[v] = ++arg.num; //num[v]:= topological number of v. low[v]:= minimum topological number in SCC including v. 
		arg.stack.push(v); arg.used[v] = true; //used[v] := whether arg.S contains v or not.
		for (Edge e: arg.adjList.get(v)) {
			if (num[e.d] == 0) { //case: e.d is non visited vertex
				sccDFS(e.d, arg, low, num, SCCs);
				low[v] = Math.min(low[v], low[e.d]);
			} else if (arg.used[e.d]) { //arg.used[e.d] == false -> has already created scc containing e.d. 
				low[v] = Math.min(low[v], num[e.d]);
			}
		}
		if (low[v] == num[v]) { 
			HashSet<Integer> scc = new HashSet<Integer>();
			int sccV = 0;
			do {
				sccV = arg.stack.pop(); arg.used[sccV] = false;
				scc.add(sccV);
			} while (v != sccV);
			SCCs.add(scc);
		}
	}

	static void showAdjList(List<List<Edge>> list) {
		for (int i = 0; i < list.size(); ++i) {
			System.out.print(i + " -> ");
			for (Edge e: list.get(i)) System.out.print(e.d + ", ");
			System.out.println();
		}
	}
	static List<List<Edge>> list = new ArrayList<List<Edge>>();
	public static void main(String[] args) {
		int N = 12;
		for (int i = 0; i < N; ++i) list.add(new ArrayList<Edge>());
		add(0, 1);
		add(1, 2); add(1, 7);
		add(2, 7);
		add(3, 2); add(3, 8);
		add(4, 5); add(4, 9); add(4, 10);
		add(5, 6);
		add(6, 5);
		add(7, 3);
		add(8, 4);
		add(9, 8);
		add(10, 5); add(10, 11);
		
		showAdjList(list); System.out.println();
		List<Set<Integer>> sccs = Kosaraju(list);
		System.out.println(sccs.size());
		System.out.print("\n[");
		for (Set<Integer> scc: sccs) System.out.print(scc.toString() + ", ");
		System.out.println("]");
		//[[5, 6], [11], [10], [4, 8, 9], [2, 3, 7], [1], [0], ]
	}
	static void add(int src, int dst) { list.get(src).add(new Edge(src, dst, 0)); }
}
