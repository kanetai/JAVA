import java.util.*;
public class aoj0227 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), m = stdin.nextInt();
			if ((n|m) == 0) break;
			int p[] = new int[n];
			for (int i = 0; i < n; ++i) p[i] = stdin.nextInt();
			Arrays.sort(p);
			int ans = 0;
			for (int i = 1; i <= n; ++i) {
				if (i % m == 0) continue;
				ans += p[n-i];
			}
			System.out.println(ans);
		}
	}
}
