import java.util.*;
public class aoj0270 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 300001, LB[] = new int[N];
	static final boolean C[] = new boolean[N];
	public static void main(String[] args) {
		int n = stdin.nextInt(), Q = stdin.nextInt(), m = -1;
		for (int i = 0; i < n; ++i) { int c = stdin.nextInt(); C[c] = true; m = Math.max(m, c); }
		for (int i = 0, j = 0; i < m+1; ++i) { LB[i] = j; if (C[i]) j = i; }
		while (Q-- > 0) {
			int q = stdin.nextInt(), ans = 0, p;
			for (int i = m; i > 0; i = LB[i-p]) {
				p = i % q;
				ans = Math.max(ans, p);
				if (i - p < 0) break;
			}
			System.out.println(ans);
		}
	}
}
