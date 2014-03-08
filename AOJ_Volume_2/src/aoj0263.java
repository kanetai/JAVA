import java.util.*;
public class aoj0263 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 16, DP[][] = new int[2][1<<N];
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), c = stdin.nextInt(), i = 0;
			if ((n|c) == 0) break;
			int[] a = new int[n], b = new int[c];
			for (i = 0; i < n; ++i) for (int j = 0; j < N; ++j) a[i] = (a[i]<<1) + stdin.nextInt();
			for (i = 0; i < c; ++i) for (int j = 0; j < N; ++j) b[i] = (b[i]<<1) + stdin.nextInt();
			Arrays.fill(DP[0], -1); DP[0][0] = 0;
			for (i = 0; i < n; ++i) {
				Arrays.fill(DP[(i+1)%2], -1);
				for (int j = 0; j < (1<<N); ++j) if (DP[i%2][j] != -1) {
					int on = j | a[i];
					for (int k = 0; k < c; ++k) {
						int off = on & b[k], next = on & ~off;
						DP[(i+1)%2][next] = Math.max(DP[(i+1)%2][next], DP[i%2][j] + Integer.bitCount(off));
					}
				}
			}
			int ans = 0;
			for (int x : DP[i%2]) ans = Math.max(ans, x);
			System.out.println(ans);
		}
	}
}
