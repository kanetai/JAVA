import java.util.*;
public class aoj0155 {
	static final Scanner stdin = new Scanner(System.in);
	static final double INF = 1e10;
	static final double EPS = 1e-10;
	static boolean leq(double a, double b){ return a - b < EPS; }	// a <= b
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			int[] x = new int[n], y = new int[n], i2n = new int[n];
			Map<Integer, Integer> n2i = new HashMap<Integer, Integer>(n+n);
			for(int i = 0; i < n; ++i){
				i2n[i] = stdin.nextInt();
				n2i.put(i2n[i], i);
				x[i] = stdin.nextInt();
				y[i] = stdin.nextInt();
			}
			double[][] G = new double[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j){
					double dist = Math.hypot(x[i]-x[j], y[i]-y[j]);
					G[i][j] = (i != j && leq(dist, 50) ? dist : INF);
				}
			int m = stdin.nextInt();
			APSPResult res = FloydWarshall(G);
			while(m-- > 0){
				int s = n2i.get(stdin.nextInt()), t = n2i.get(stdin.nextInt());
				List<Integer> path = res.buildPath(s, t);
				if(path.isEmpty()){
					System.out.println("NA");
				}else{
					for(int i = 0; i < path.size(); ++i)
						System.out.print(i2n[path.get(i)] + (i < path.size() - 1 ? " " : "\n"));
				}
			}
		}
	}
	public static class Edge {
		protected int s; //source node		
		protected int d; //destination node
		protected int w; //edge weight
		Edge(int s, int d, int w){ set(s, d, w); }
		public final void set(int s, int d, int w){ this.s = s; this.d = d; this.w = w; }
	}
	public static class APSPResult{
		private double[][] dist; // dist[d]:= shortest path to d
		private int[][] prev; // back pointers
		@SuppressWarnings("unused") private boolean hasNegativeCycle;
		public APSPResult(double[][] dist, int[][] prev, boolean hasNegativeCycle){ set(dist, prev, hasNegativeCycle); }
		final private void set(double[][] dist, int[][] prev, boolean hasNegativeCycle){ this.dist = dist; this.prev = prev; this.hasNegativeCycle = hasNegativeCycle; }
		public List<Integer> buildPath(int s, int d){
			LinkedList<Integer> path = new LinkedList<Integer>();
			if(dist[s][d] < INF) for(int u = d; u >= 0; u = prev[s][u]) path.addFirst(u);
			return path;
		}
	}
	public static APSPResult FloydWarshall(double[][] G){
		int n = G.length;
		int[][] prev = new int[n][n];
		double[][] dist = new double[n][n];
		for(int i = 0; i < n; ++i){
			System.arraycopy(G[i], 0, dist[i], 0, n);
			dist[i][i] = 0;
			for(int j = 0; j < n; ++j) prev[i][j] = (G[i][j] != INF ? i : -1);
		}
		for(int k = 0; k < n; ++k){
			for(int i = 0; i < n; ++i){
				if(dist[i][k] >= INF) continue;
				for(int j = 0; j < n; ++j){
					double newDist = dist[i][k] + dist[k][j];
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
