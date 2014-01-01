import java.util.*;
public class aoj0223 {
	static final Scanner stdin = new Scanner(System.in);
	static final int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1}, D = 4;
	static class T {
		int x1, y1, x2, y2, cost;
		T(int x1, int y1, int x2, int y2, int cost) {
			this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2; this.cost = cost;
		}
	}
	public static void main(String[] args) {
		while (true) {
			int w = stdin.nextInt(), h = stdin.nextInt();
			if ((w|h) == 0) break;
			boolean map[][] = new boolean[h+2][w+2], visited[][][][] = new boolean[h+2][w+2][h+2][w+2];
			T p = new T(stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), 0);
			for (int i = 1; i <= h; ++i) for (int j = 1; j <= w; ++j) map[i][j] = stdin.nextInt() == 0;
			Queue<T> q = new LinkedList<T>();
			q.add(p);
			String ans = "NA";
			visited[p.y1][p.x1][p.y2][p.x2] = true;
			while (!q.isEmpty()) {
				T t = q.poll();
				if (t.cost > 100) continue;
				if (t.x1 == t.x2 && t.y1 == t.y2) { ans = t.cost + ""; break; }
				for (int i = 0; i < D; ++i) {
					T n = new T(t.x1 + dx[i], t.y1 + dy[i], t.x2 + dx[(i+2)%D], t.y2 + dy[(i+2)%D], t.cost + 1);
					if (!map[n.y1][n.x1]) { n.x1 = t.x1; n.y1 = t.y1; }
					if (!map[n.y2][n.x2]) { n.x2 = t.x2; n.y2 = t.y2; }
					if (!visited[n.y1][n.x1][n.y2][n.x2]) { 
						q.offer(n); 
						visited[n.y1][n.x1][n.y2][n.x2] = true;
					}
				}
			}
			System.out.println(ans);
		}
	}
}
