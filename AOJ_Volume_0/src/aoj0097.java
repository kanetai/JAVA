import java.util.*;
public class aoj0097 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 9, S = 1000, K = 100;
	static final long DP[][] = new long[N+1][S+1];
	public static void main(String[] args) {
		DP[0][0] = 1;
		for(int k = 0; k <= K; ++k)
			for(int i = N; i > 0; --i)
				for(int j = k; j <= S; ++j)
					DP[i][j] += DP[i-1][j-k];
		while(true){
			int n = stdin.nextInt(), s = stdin.nextInt();
			if((n|s) == 0) break;
			System.out.println(DP[n][s]);
		}
	}
}
