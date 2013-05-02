import java.util.*;
public class aoj0117 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2;
	public static void main(String[] args) {
		int n = stdin.nextInt(), m = stdin.nextInt(); stdin.nextLine();
		int[][] G = new int[n][n];
		for(int i = 0; i < n; ++i){
			Arrays.fill(G[i], INF);
			G[i][i] = 0;
		}
		for(int i = 0; i < m; ++i){
			String[] line = stdin.nextLine().split(",");
			int a1 = Integer.parseInt(line[0])-1, b1 = Integer.parseInt(line[1])-1;
			G[a1][b1] = Integer.parseInt(line[2]);
			G[b1][a1] = Integer.parseInt(line[3]);
		}
		String[] line = stdin.nextLine().split(",");
		int x1 = Integer.parseInt(line[0])-1, x2 = Integer.parseInt(line[1])-1;
		int y1 = Integer.parseInt(line[2]), y2 = Integer.parseInt(line[3]);
		APSPResult ret = FloydWarshall(G);
		System.out.println(y1 - ret.getDist(x1, x2) - ret.getDist(x2,x1) - y2);
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
