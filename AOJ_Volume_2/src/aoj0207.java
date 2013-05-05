import java.util.*;
public class aoj0207 {
	static final Scanner stdin = new Scanner(System.in);
	static final int X = 0, Y = 1, F = 2;
	static final int delta[][] = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static final int ManhattanDistance(int[] s, int[] g){ return Math.abs(s[X] - g[X]) + Math.abs(s[Y] - g[Y]); }
	public static void main(String[] args) {
		while(true){
			int w = stdin.nextInt(), h = stdin.nextInt();
			if((w|h) == 0) break;
			int s[] = {stdin.nextInt(), stdin.nextInt()}, g[] = {stdin.nextInt(), stdin.nextInt()};
			int n = stdin.nextInt();
			int[][] map = new int[h+2][w+2]; for(int i = 0; i < h+2; ++i) Arrays.fill(map[i], 0);
			for(int i = 0; i < n; ++i){
				int c = stdin.nextInt(), d = stdin.nextInt(), x = stdin.nextInt(), y = stdin.nextInt();
				for(int j = 0; j < 2; ++j)
					for(int k = 0; k < 4; ++k)
						if(d == 0) map[y+j][x+k] = c;
						else map[y+k][x+j] = c;

			}
			System.out.println(BFS(map, s, g) ? "OK" : "NG");
		}
	}
	static boolean BFS(int[][] map, int[] s, int[] g){
		int color;
		if((color = map[s[Y]][s[X]]) == 0) return false;
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(map.length*map[0].length, new Comparator<int[]>(){
			@Override public int compare(int[] o1, int[] o2) { return o1[F] - o2[F]; }
		});
		q.add(new int[]{s[X], s[Y], ManhattanDistance(s,g)});
		while(!q.isEmpty()){
			int[] e = q.poll();
			int x = e[X], y = e[Y];
			if(map[y][x] != color) continue;
			if(y == g[Y] && x == g[X]) return true;
			map[y][x] = 0;
			for(int[] d: delta){
				int n[] = {x + d[X], y + d[Y], 0};
				if(map[n[Y]][n[X]] != color) continue;
				n[F] = ManhattanDistance(e, n);
				q.add(n);
			}
		}
		return false;
	}
}
