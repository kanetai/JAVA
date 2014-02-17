import java.util.*;
public class aoj0234 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2, dx[] = {0,1,-1}, dy[] = {1,0,0};
	static class T implements Comparable<T>{
		int c, y, x, o, s;
		public T(int X, int Y, int O, int C, int S) { x = X; y = Y; o = O; c = C; s = S; }		
		@Override public int compareTo(T o) { return c - o.c; }
	}

	public static void main(String[] args) {
		while(true){
			final int W = stdin.nextInt(), H = stdin.nextInt();
			if((W|H) == 0) break;
			final int f = stdin.nextInt(), m = stdin.nextInt(), o = stdin.nextInt();
			int [][] air = new int[W][H], cost = new int[W][H];
			for(int y = 0; y < H; ++y) for(int x = 0; x < W; ++x) {
				int tmp = stdin.nextInt();
				if (tmp >= 0) air[x][y] = tmp;
				else cost[x][y] = -tmp;
			}
			System.out.println(solve(W,H,m,o,f,cost,air));
		}
	}
	static String solve(int W, int H, int m, int o, int f, int[][] cost, int[][] air) {
		int [][][][] minCost = new int[W][H][m + 1][1 << W];
		for(int x = 0; x < W; ++x) for(int y = 0; y < H; ++y) for(int o2 = 0; o2 <= m; ++o2) Arrays.fill(minCost[x][y][o2], INF);
		PriorityQueue<T> open = new PriorityQueue<T>();
		for(int x = 0; x < W; ++x) open.add(new T(x, -1, o, 0, 1 << x));

		String ans = "NA";
		while(!open.isEmpty()) {
			final T p = open.poll();
			if(p.y == H - 1) { ans = "" + p.c; break; }

			for(int i = 0; i < 3; i++){
				int ny = p.y + dy[i], nx = p.x + dx[i];
				if(ny == -1) continue;
				if(nx < 0 || nx >= W) continue;

				int nc = p.c, no = p.o - 1;
				if(no <= 0) continue;
				int ns = ((i == 0 ? 0 : p.s) | (1<<nx));
				if(i == 0 || ns != p.s) {
					nc += cost[nx][ny];
					no = Math.min(no + air[nx][ny], m);
				}
				if(nc > f) continue;
				if(minCost[nx][ny][no][ns] <= nc) continue;
				open.add(new T(nx, ny, no, nc, ns));
				minCost[nx][ny][no][ns] = nc;
			}
		}
		return ans;
	}
}