import java.util.*;
public class aoj0098 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt();
		int[][] M = new int[n+1][n+1];
		for(int i = 1; i <= n; ++i )
			for(int j = 1, s = 0; j <= n; ++j){
				s += stdin.nextInt();
				M[i][j] = s + (i >= 1 ? M[i-1][j] : 0);
			}
		int ans = Integer.MIN_VALUE;
		for(int k = 1; k <= n; ++k)
			for(int l = 1; l <= n; ++l)
				for(int i = k; i <= n; ++i)
					for(int j = l; j <= n; ++j)
							ans = Math.max(ans, M[i][j] + M[i-k][j-l] - M[i][j-l] - M[i-k][j]);
		System.out.println(ans);
	}
}
