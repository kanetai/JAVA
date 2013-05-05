import java.util.*;
public class aoj0189 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n;
		while((n=stdin.nextInt()) != 0){
			Map<Integer, Integer> name2id = new HashMap<Integer, Integer>(n<<1), id2name = new HashMap<Integer, Integer>(n<<1);
			List<Edge> edge = new ArrayList<Edge>(n<<1);
			for(int i = 0, id = 0; i < n; ++i){
				int a = stdin.nextInt(), b = stdin.nextInt(), c = stdin.nextInt();
				if(!name2id.containsKey(a)) name2id.put(a, id++);
				if(!name2id.containsKey(b)) name2id.put(b, id++);
				int aid = name2id.get(a), bid = name2id.get(b);
				id2name.put(aid, a); id2name.put(bid, b);
				edge.add(new Edge(aid, bid, c));
			}
			n = name2id.size();
			int[][] G = new int[n][n];
			for(int i = 0; i < n; ++i) Arrays.fill(G[i], INF);
			for(Edge e: edge) G[e.s][e.d] = G[e.d][e.s] = e.w;
			APSPResult res = FloydWarshall(G);
			int mi = -1, mw = INF;
			for(int i = 0; i < n; ++i){
				int S = 0;
				for(int j = 0; j < n; ++j) S += res.getDist(i, j);
				if(mw > S){ mi = i; mw = S; }
			}
			System.out.println(id2name.get(mi) + " " + mw);
		}
	}
	static final int INF = Integer.MAX_VALUE/2;
	public static class Edge implements Comparable<Edge>{
		protected int s; //source node		
		protected int d; //destination node
		protected int w; //edge weight
		Edge(int s, int d, int w){ set(s, d, w); }
		Edge(Edge o){ set(o.s, o.d, o.w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
		@Override public int compareTo(Edge o) { return w < o.w ? -1 : w == o.w ? 0 : 1; }
	}
	@SuppressWarnings("unused") public static class APSPResult{
		private int[][] dist; // dist[d]:= shortest path to d
		private int[][] prev; // back pointers
		private boolean hasNegativeCycle;
		public APSPResult(int[][] dist, int[][] prev, boolean hasNegativeCycle){ set(dist, prev, hasNegativeCycle); }
		final private void set(int[][] dist, int[][] prev, boolean hasNegativeCycle){ this.dist = dist; this.prev = prev; this.hasNegativeCycle = hasNegativeCycle; }
		final public int getDist(int i, int j){ return dist[i][j]; }
	}
	public static APSPResult FloydWarshall(int[][] G){
		int n = G.length;
		int[][] prev = new int[n][n], dist = new int[n][n];
		for(int i = 0; i < n; ++i){
			System.arraycopy(G[i], 0, dist[i], 0, n);
			dist[i][i] = 0;
			for(int j = 0; j < n; ++j) prev[i][j] = (G[i][j] != INF ? i : -1);
		}
		for(int k = 0; k < n; ++k){
			for(int i = 0; i < n; ++i){
				if(dist[i][k] >= INF) continue;
				for(int j = 0; j < n; ++j){
					int newDist = dist[i][k] + dist[k][j];
					if(dist[i][j] > newDist){
						dist[i][j] = newDist;
						prev[i][j] = prev[k][j];
					}
				}
			}
		}
		boolean hasNegativeCycle = false;
		for(int i = 0; i < n; ++i) if(dist[i][i] < 0){ hasNegativeCycle = true; break; }
		return new APSPResult(dist, prev, hasNegativeCycle);
	}
}

