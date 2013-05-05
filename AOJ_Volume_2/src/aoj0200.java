import java.util.*;
public class aoj0200 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if((n|m) == 0) break;
			int[][] T = new int[m][m], C = new int[m][m];
			for(int i = 0; i < m; ++i){ Arrays.fill(T[i], INF); Arrays.fill(C[i], INF); }
			for(int i = 0; i < n; ++i){
				int a = stdin.nextInt()-1, b = stdin.nextInt()-1;
				int cost = stdin.nextInt(), time = stdin.nextInt();
				T[a][b] = T[b][a] = time;
				C[a][b] = C[b][a] = cost;
			}
			int k = stdin.nextInt();
			APSPResult t = FloydWarshall(T), c = FloydWarshall(C);
			for(int i = 0; i < k; ++i){
				int p = stdin.nextInt()-1, q = stdin.nextInt()-1, r = stdin.nextInt();
				System.out.println(r == 0 ? c.getDist(p, q) : t.getDist(p, q));
			}
		}
	}
	static final int INF = Integer.MAX_VALUE/2;
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
