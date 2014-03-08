import java.util.*;
public class aoj0258 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		L: while (true) {
			int n = stdin.nextInt(), a[] = new int[n+1], tmp[] = new int[n];
			if (n == 0) break;
			for (int i = 0; i <= n; ++i) a[i] = stdin.nextInt();
			LOOP: for (int s = 0; s <= n; ++s) {
				for (int i = 0, j = 0; i <= n; ++i) if (i != s) tmp[j++] = a[i];
				int diff = tmp[1] - tmp[0];
				for (int i = 2; i < n; ++i) if (diff != tmp[i] - tmp[i-1]) continue LOOP;
				System.out.println(a[s]);
				continue L;
			}
		}
	}
}
