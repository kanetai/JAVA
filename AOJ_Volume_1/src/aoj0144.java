import java.util.*;
public class aoj0144 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2;
	static int idx = 0;
	static Map<String, Integer> name;
	static int[][] M;
	static int get(String s){
		if(name.containsKey(s)) return name.get(s);
		name.put(s, idx);
		return idx++;
	}
	public static void main(String[] args) {
		int n = stdin.nextInt(); stdin.nextLine();
		name = new HashMap<String, Integer>(n+n);
		M = new int[n][n];
		for(int i = 0; i < n; ++i){
			Arrays.fill(M[i], INF);
			M[i][i] = 0;
		}
		for(int i = 0; i < n; ++i){
			String[] s = stdin.nextLine().split(" ");
			int src = get(s[0]);
			for(int j = 2; j < s.length; ++j) M[src][get(s[j])] = 1;
		}
		APSPResult res = FloydWarshall(M);
		int p = stdin.nextInt(); stdin.nextLine();
		for(int i = 0; i < p; ++i){
			String[] s = stdin.nextLine().split(" ");
			int ans = res.getDist(get(s[0]), get(s[1])) + 1;
			System.out.println(ans >= INF || ans > Integer.parseInt(s[2]) ? "NA" : ans );
		}
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
