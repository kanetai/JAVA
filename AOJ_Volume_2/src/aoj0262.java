import java.util.*;
public class aoj0262 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int max = stdin.nextInt();
			if (max == 0) break;
			int n = stdin.nextInt(), map[] = new int[n+2];
			for (int i = 1; i <= n; ++i) map[i] = stdin.nextInt();
			boolean M[][] = new boolean[n+2][n+2], visited[] = new boolean[n+2], visited2[] = new boolean[n+2];
			for (int i = 0; i <= n; ++i) {
				for (int ii = 1; ii <= max; ++ii) {
					int j = i + ii;
					if (j >= n+1) {
						M[i][n+1] = true;
					} else {
						j += map[j];
						M[i][j <= 0 ? 0 : n+1 <= j ? n+1 : j] = true;
					}
				}
			}
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(n+1); visited[n+1] = true;
			while (!q.isEmpty()) {
				int p = q.poll();
				for (int i = 0; i <= n+1; ++i) if (M[i][p] && !visited[i]) {
					q.add(i); visited[i] = true;
				}
			}
			q.add(0); visited2[0] = true;
			String ans = "OK";
			while (!q.isEmpty()) {
				int p = q.poll();
				if (!visited[p]) {
					ans = "NG";
					break;
				}
				for (int i = 0; i <= n+1; ++i) if (M[p][i] && !visited2[i]) {
					q.add(i); visited2[i] = true;
				}
			}
			System.out.println(ans);
		}
	}
}
