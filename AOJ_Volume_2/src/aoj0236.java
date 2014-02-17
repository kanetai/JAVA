import java.util.*;
public class aoj0236 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 7, K = 4;
	static final int[] dx = new int[]{1, 0, -1, 0};
	static final int[] dy = new int[]{0, 1, 0, -1};
	static int W, H, data[][] = new int[N][N];
	static final int PX = 0, PY = 1, NX = 2, NY = 3;
	static final int pattern[] = { bit(NX)|bit(PX), bit(NY)|bit(PY), bit(NY)|bit(PX), bit(PX)|bit(PY), bit(NX)|bit(PY), bit(NX)|bit(NY) };
	static boolean[][] block;
	static boolean inRange(int y, int x) { return 0 <= x && x < W && 0 <= y && y < H; }
	static int bit(int n) { return 1<<n; }
	static boolean bitTrue(int bitset, int n) { return (bitset & bit(n)) != 0; }
	public static void main(String[] args) {
		while (true) {
			W = stdin.nextInt(); H = stdin.nextInt();
			if ((W|H) == 0) break;
			block = new boolean[H][W];
			for (int i = 0; i < H; ++i) for (int j = 0; j < W; ++j) block[i][j] = (stdin.nextInt() == 1);
			System.out.println(rec(0) ? "Yes" : "No");
		}
	}
	static boolean rec(int p) {
	    if(p == W * H) return validate();
	    int x = p % W, y = p / W;
	    if(!block[y][x]) {
	        for (final int pat : pattern) {
	            if(bitTrue(pat, PX) && (x+1 >= W || block[y][x+1])) continue;
	            if(bitTrue(pat, PY) && (y+1 >= H || block[y+1][x])) continue;
	            if(bitTrue(pat, NX) != (x-1 >= 0 && (bitTrue(data[y][x-1], PX)))) continue;
	            if(bitTrue(pat, NY) != (y-1 >= 0 && (bitTrue(data[y-1][x], PY)))) continue;
	            data[y][x] = pat;
	            if(rec(p+1)) return true;
	        }
	    } else {
	        data[y][x] = 0;
	        return rec(p+1);
	    }
	    return false;
	}
	static boolean validate() {
	    boolean ret = false;
	    boolean[][] visited = new boolean[H][W];
	    for (int i = 0; i < H; ++i) for (int j = 0; j < W; ++j) if(!block[i][j] && !visited[i][j]) {
	        if (ret) return false; //multiple lines
	        ret = true;
	        visited[i][j] = true;
	        dfs(i, j, visited);
	    }
	    return ret;
	}
	static void dfs(int y, int x, boolean[][] visited) {
	    for (int k = 0; k < K; ++k) {
	        if(bitTrue(data[y][x], k)) {
	            int nx = x + dx[k], ny = y + dy[k];
	            if(!inRange(ny, nx) || visited[ny][nx]) continue;
	            visited[ny][nx] = true;
	            dfs(ny, nx, visited);
	        }
	    }
	}
}
