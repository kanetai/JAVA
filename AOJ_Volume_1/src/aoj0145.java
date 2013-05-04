import java.util.*;
public class aoj0145 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2;
	static int n, a[], b[], dp[][];
	static Solver solver = Solver.SOLVE1;
	public static void main(String[] args) {
		n = stdin.nextInt(); dp = new int[n][n];
		a = new int[n]; b = new int[n];
		for(int i = 0; i < n; ++i){
			a[i] = stdin.nextInt();
			b[i] = stdin.nextInt();
			for(int j = 0; j < n; ++j) dp[i][j] = (i == j ? 0 : INF);
		}
		System.out.println(solver.solve());
	}
	static enum Solver {
		SOLVE1 { @Override int solve(){
			for(int d = 0; d < n; ++d){
				for(int i = 0; i+d < n; ++i){
					int j = i + d;
					for(int k = i; k < j; ++k){
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + a[i]*b[k]*a[k+1]*b[j] );
					}
				}
			}
			return dp[0][n-1];
		}}, SOLVE2 { @Override int solve(){ return rec(0, n-1); }
		int rec(int i, int j){
			if(i == j) return 0;
			if(dp[i][j] < INF) return dp[i][j];
			for(int k = i; k < j; ++k) dp[i][j] = Math.min(dp[i][j], rec(i, k) + rec(k+1, j) + a[i]*b[k]*a[k+1]*b[j]);
			return dp[i][j];
		}
		};
		int solve(){ return 0;}
	}
}
