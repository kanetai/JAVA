import java.util.*;
public class aoj0154 {
	static final Scanner stdin = new Scanner(System.in);
	static final int J = 1000;
	public static void main(String[] args) {
		int m;
		while((m = stdin.nextInt()) != 0){
			int[] a = new int[m+1], b = new int[m+1];
			for(int i = 1; i <= m; ++i){
				a[i] = stdin.nextInt();
				b[i] = stdin.nextInt();
			}
			int[][] LUT = solve(m, a, b);
			int g = stdin.nextInt();
			while(g-- >0) System.out.println(LUT[m][stdin.nextInt()]);
		}
	}
	static int[][] solve(int m, int[] a, int[] b){
		int[][] DP = new int[m+1][J+1];
		for(int i = 0; i <= m; ++i) Arrays.fill(DP[i], 0);
		DP[0][0] = 1;
		for(int i = 1; i <= m; ++i)
			for(int j = 0; j <= J; ++j)
				for(int k = 0; k <= b[i]; ++k)
					if(j-k*a[i] >= 0) DP[i][j] += DP[i-1][j-k*a[i]];
		return DP;
	}
}
