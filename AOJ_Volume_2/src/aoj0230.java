import java.util.*;
public class aoj0230 {
	static final Scanner stdin = new Scanner(System.in);
	static final int NORMAL = 0, LADDER = 1, SLIPPY = 2, ID = 0, FLOOR = 1, COST = 2;
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			int b[][] = new int[2][n];
			for (int i = 0; i < 2; ++i) for (int j = 0; j < n; ++j) b[i][j] = stdin.nextInt();
			System.out.println(solve(b, n));
		}
	}
	static String solve(int[][] b, int n) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] closed = new boolean[2][n];
		q.add(new int[]{0, 0, 0});
		q.add(new int[]{1, 0, 0});
		while (!q.isEmpty()) {
			int[] p = q.poll();
			if (b[p[ID]][p[FLOOR]] == LADDER) {
				while (p[FLOOR] < n && b[p[ID]][p[FLOOR]] == LADDER) ++p[FLOOR];
				--p[FLOOR]; //行き過ぎた分戻る
			} else if (b[p[ID]][p[FLOOR]] == SLIPPY) {
				while (b[p[ID]][p[FLOOR]] == SLIPPY) --p[FLOOR];
			}
			
			if (closed[p[ID]][p[FLOOR]]) continue;
			closed[p[ID]][p[FLOOR]] = true;
			if (p[FLOOR] == n - 1) return ""+p[COST];
			
			for (int i = 0; i < 3; ++i) if (n > p[FLOOR] + i) q.add(new int[]{ (p[ID]+1)%2, p[FLOOR] + i, p[COST] + 1 });
		}
		return "NA";
	}
}