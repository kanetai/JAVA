import java.util.*;
public class aoj0193 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2, X = 0, Y = 1, D = 2, HEX = 6;
	static final int dx[][] = { {-1, 0, 1, 0,-1,-1}, {0,  1, 1, 1, 0,-1} };
	static final int dy[][] = { {-1,-1, 0, 1, 1, 0}, {-1,-1, 0, 1, 1, 0} };
	static int m, n;
	static int[][] d;
	public static void main(String[] args) {
		while(true){
			m = stdin.nextInt(); n = stdin.nextInt();
			if((m|n) == 0) break;
			d = new int[n][m]; for(int i = 0; i < n; ++i) Arrays.fill(d[i], INF);
			int s = stdin.nextInt();
			for(int i = 0; i < s; ++i) BFS(stdin.nextInt()-1, stdin.nextInt()-1, true);
			int ans = 0, t = stdin.nextInt();
			for(int i = 0; i < t; ++i) ans = Math.max(ans, BFS(stdin.nextInt()-1, stdin.nextInt()-1, false));
			System.out.println(ans);		
		}
	}
	static int BFS(int x, int y, boolean calcMinDist){
		int res = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] closed = new boolean[n][m]; for(int i = 0; i < n; ++i) Arrays.fill(closed[i], false); 
		q.add(new int[]{x, y, 0});
		while(!q.isEmpty()){
			int[] e = q.poll();
			if(closed[e[Y]][e[X]]) continue;
			closed[e[Y]][e[X]] = true;
			if(d[e[Y]][e[X]] > e[D]){
				if(calcMinDist) d[e[Y]][e[X]] = e[D];
				res++;
			}
			for(int i = 0; i < HEX; ++i){
				int nx = e[X] + dx[e[Y]%2][i], ny = e[Y] + dy[e[Y]%2][i];
				if(0 <= nx && nx < m && 0 <= ny && ny < n) q.add(new int[]{nx, ny, e[D]+1});
			}
		}
		return res;
	}
}
