import java.util.*;
public class aoj0072 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args){
		int n;
		while((n = stdin.nextInt()) != 0){
			if(n == 0) break;
			int m = stdin.nextInt();
			List<List<Edge>> list = new ArrayList<List<Edge>>(n);
			for(int i = 0; i < n; ++i) list.add(new ArrayList<Edge>());
			for(int i = 0; i < m; ++i){
				String[] line = stdin.next().split(",");
				int src = Integer.parseInt(line[0]);
				int dst = Integer.parseInt(line[1]);
				int weight = Integer.parseInt(line[2]); 
				list.get(src).add( new Edge(src, dst, weight/100-1) );
				list.get(dst).add( new Edge(dst, src, weight/100-1) );
			}
			System.out.println(Prim(list).getTotalCost());
		}
	}
	public static class MSTResult{
		private int totalCost;
		private List<Edge> edgeList;
		public MSTResult(int totalCost, List<Edge> edgeList){ this.totalCost = totalCost; this.edgeList = edgeList; }
		public final int getTotalCost(){ return totalCost; }
		public final List<Edge> getEdgeList(){ return edgeList; }
	}
	public static MSTResult Prim(List<Edge>[] list, int s) {
		int n = list.length, totalCost = 0;
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
			for(Edge f: list[e.d]) if(!visited[f.d]) q.add(f);
		}
		return new MSTResult(totalCost, T);
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
	public static class Edge implements Comparable<Edge>{
		protected int s; //source node		
		protected int d; //destination node
		protected int w; //edge weight
		Edge(int s, int d, int w){ set(s, d, w); }
		Edge(Edge o){ set(o.s, o.d, o.w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
		@Override public int compareTo(Edge o) { return w < o.w ? -1 : w == o.w ? 0 : 1; }
	}
}
