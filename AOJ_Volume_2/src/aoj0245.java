import java.util.*;
public class aoj0245 {
	static final Scanner stdin = new Scanner(System.in);
	static final int dx[] = {0, 0, -1, 1}, dy[] = {-1, 1, 0, 0}, id2idx[] = new int[10];
	static final boolean visited[][][] = new boolean[20][20][(1<<8)];
	static int W, H, N;
	public static void main(String[] args) {
		while (true) {
			W = stdin.nextInt(); H = stdin.nextInt();
			if ((W|H) == 0) break;
			char map[][] = new char[H][W];
			int sx = -1, sy = -1;
			for (int i = 0; i < H; ++i) {
				for (int j = 0; j < W; ++j) {
					Arrays.fill(visited[i][j], false);
					char c = stdin.next().charAt(0);
					if (c == 'P') { sy = i; sx = j; }
					map[i][j] = c;
				}
			}
			N = stdin.nextInt();
			Sale sale[] = new Sale[N];
			for (int i = 0; i < N; ++i) {
				int g = stdin.nextInt();
				sale[i] = new Sale(stdin.nextInt(), stdin.nextInt(), stdin.nextInt());
				id2idx[g] = i;
			}
			System.out.println(solve(sy, sx, map, sale));
		}
	}
	static int solve(int sy, int sx, char[][] map, Sale[] sale) {
		int ans = 0;
		for (int i = 0; i < H; ++i) for (int j = 0; j < W; ++j) Arrays.fill(visited[i][j], false);
		Queue<State> q = new LinkedList<State>();
		q.add(new State(sy, sx, 0, 0, 0));
		visited[sy][sx][0] = true;
		while (!q.isEmpty()) {
			State p = q.poll();
			for (int i = 0; i < 4; ++i) {
				int nextY = p.y + dy[i], nextX = p.x + dx[i];
				State n = new State(p.y, p.x, p.time, p.sum, p.buy);
				if (!(0 <= nextY && nextY < H && 0 <= nextX && nextX < W) || visited[nextY][nextX][p.buy]) continue;
				if (!Character.isDigit(map[nextY][nextX])) { //move
					n.x = nextX; n.y = nextY; n.time++;
					visited[n.y][n.x][n.buy] = true;
					if (n.time <= 100) q.add(n);
					continue;
				} 
				//don't move
				int idx = id2idx[map[nextY][nextX] - '0'];
				if (!n.isPurchased(idx)) {
					switch (n.onSale(idx, sale)) {
					case 0: //on sale
						visited[n.y][n.x][n.buy] = true;
						n.buy(idx, sale);
						visited[n.y][n.x][n.buy] = true;
						ans = Math.max(ans, n.sum);
						if (!n.isAllPurchased()) q.add(n);
						break;
					case -1: //early
						n.wait(idx, sale);
						q.add(n);
						break;
					case 1: default:
					}
				}
			}
		}
		return ans;
	}
	static class State {
		int y, x, time, sum, buy;
		State(int y, int x, int time, int sum, int buy) {
			this.y = y; this.x = x; this.time = time; this.sum = sum; this.buy = buy;
		}
		boolean isPurchased(int idx) { return ((buy & (1 << idx)) != 0); }
		int onSale(int idx, Sale[] sales) {
			Sale s = sales[idx];
			return time < s.s ? -1 : s.s <= time && time < s.e ? 0 : 1;
		}
		void buy(int idx, Sale[] sales) {;
		this.buy |= (1<<idx);
		this.sum += sales[idx].d;
		}
		boolean isAllPurchased() { return buy == (1<<N)-1; }
		void wait(int idx, Sale[] sales) { this.time = sales[idx].s; }
	}
	static class Sale {
		int d = 0, s = 0, e = 0;
		Sale(int d, int s, int e) { this.d = d; this.s = s; this.e = e; }
	}
}