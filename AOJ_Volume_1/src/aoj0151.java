import java.util.*;
public class aoj0151 {
	static final Scanner stdin = new Scanner(System.in);
	static int dx[] = {1, 0, 1, -1};
	static int dy[] = {0, 1, 1, 1};
	static int search(boolean[][] map, int y, int x, int d){
		int ret = 0;
		while(true){
			if(map[y][x]) ++ret;
			else break;
			y += dy[d]; x += dx[d];
		}
		return ret;
	}
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			boolean[][] map = new boolean[n+2][n+2];
			for(boolean[] x: map) Arrays.fill(x, false);
			for(int i = 1; i <= n; ++i){
				String l = stdin.next();
				for(int j = 0; j < l.length(); ++j) if(l.charAt(j) == '1') map[i][j+1] = true;
			}
			int ans = 0;
			for(int i = 1; i <= n; ++i)
				for(int j = 1; j <= n; ++j)
					for(int d = 0; d < 4; ++d) ans = Math.max(ans, search(map, i, j, d));
			System.out.println(ans);
		}
	}
}
