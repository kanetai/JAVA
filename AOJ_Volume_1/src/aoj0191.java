import java.util.*;
public class aoj0191 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if((n|m) == 0) break;
			double[][] factor = new double[n][n];
			for(int i = 0; i < n; ++i) for(int j = 0; j < n; ++j) factor[i][j] = stdin.nextDouble();
			double[][] DP = new double[2][n]; Arrays.fill(DP[0], 1.0);
			for(int i = 0; i < m-1; ++i){
				Arrays.fill(DP[(i+1)%2], -1);
				for(int j = 0; j < n; ++j) //j:current state
					for(int k = 0; k < n; ++k) //k:previous state
						DP[(i+1)%2][j] = Math.max(DP[(i+1)%2][j], factor[k][j]*DP[i%2][k]);
			}
			double ans = -1;
			for(int j = 0; j < n; ++j) ans = Math.max(ans, DP[(m-1)%2][j]);
			System.out.printf("%.2f\n", ans);
		}
	}
}
