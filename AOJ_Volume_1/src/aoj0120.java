import java.util.*;
public class aoj0120 {
	static final Scanner stdin = new Scanner(System.in);
	static final double INF = 1e8;
	public static void main(String[] args) {
		while(stdin.hasNext()){
			String[] line = stdin.nextLine().split(" ");
			int l = Integer.parseInt(line[0]), n = line.length;
			int[] r = new int[n];
			r[0] = 0;
			for(int i = 1; i < n; ++i) r[i] = Integer.parseInt(line[i]);
			double[][] d = new double[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					d[i][j] = (i == j ) ? 0 : 
							(i == 0) ? r[j] : 
							(j == 0) ? r[i] : 
							Math.sqrt(Math.pow(r[i] + r[j], 2) - Math.pow(r[i] - r[j], 2));
			System.out.println(TSP(d,0).getLength() <= l ? "OK" : "NA");
		}
	}
	@SuppressWarnings("unused") public static class TSPResult{
		private int s;		//source node (※= destination node)
		private double length;	//length of shortest Hamilton cycle
		private int[][] prev;	//back pointers
		public TSPResult(int s, double length, int[][] prev){ set(s, length, prev); }
		final private void set(int s, double length, int[][] prev){ this.s = s; this.length = length; this.prev = prev; }
		final public double getLength(){ return length; }
	}
	static TSPResult TSP(double[][] G, int s){
		int n = G.length, N = 1 << n;
		double[][] DP = new double[N][n];
		int[][] prev = new int[N][n];
		for(int i = 0; i < N; ++i) Arrays.fill(DP[i], INF);
		for(int i = 0; i < n; ++i){
			DP[1<<i][i] = G[s][i];
			prev[1<<i][i] = s;
		}
		for (int S = 1; S < N; ++S) {
			for (int i = 0; i < n; ++i) {
				if ((S & (1<<i)) == 0) continue;
				for (int j = 0; j < n; ++j) {
					if ((S & (1<<j)) != 0 ) continue;
					//i in S, j not in S 
					double newDist = DP[S][i]+G[i][j];
					if(DP[S|(1<<j)][j] > newDist) {
						DP[S|(1<<j)][j] = newDist;
						prev[S|(1<<j)][j] = i;
					}
				}
			}
		} 
		return new TSPResult(s, DP[N-1][s], prev);
	}
}
