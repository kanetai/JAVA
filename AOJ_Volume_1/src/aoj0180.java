import java.util.*;
public class aoj0180 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if((n|m) == 0) break;
			Map<Integer, Integer> name = new HashMap<Integer, Integer>(n<<1);
			int id = 0;
			List<List<Edge>> list = new ArrayList<List<Edge>>(n);
			for(int i = 0; i < n; ++i) list.add(new ArrayList<Edge>());
			for(int i = 0; i < m; ++i){
				int a = stdin.nextInt(), b = stdin.nextInt(), cost = stdin.nextInt();
				if(!name.containsKey(a)) name.put(a, id++);
				if(!name.containsKey(b)) name.put(b, id++);
				int aid = name.get(a), bid = name.get(b);
				list.get(aid).add(new Edge(aid, bid, cost));
				list.get(bid).add(new Edge(bid, aid, cost));
			}
			System.out.println(Prim(list).getTotalCost());
		}
	}
	public static class Edge implements Comparable<Edge>{
		protected int s; //source node		
		protected int d; //destination node
		protected int w; //edge weight
		Edge(int s, int d, int w){ set(s, d, w); }
		Edge(Edge o){ set(o.s, o.d, o.w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
		@Override public int compareTo(Edge o) { return w < o.w ? -1 : w == o.w ? 0 : 1; }
	}
	public static class MSTResult{
		private int totalCost;
		private List<Edge> edgeList;
		public MSTResult(int totalCost, List<Edge> edgeList){ this.totalCost = totalCost; this.edgeList = edgeList; }
		public final int getTotalCost(){ return totalCost; }
		public final List<Edge> getEdgeList(){ return edgeList; }
	}
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
	public static MSTResult Prim(List<List<Edge>> list){ return Prim(list, 0); }
}
