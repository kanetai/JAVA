import java.util.*;
import java.awt.Point;
public class aoj0213 {
	static final Scanner stdin = new Scanner(System.in);
	static final int MAX = 10, N_MAX = 15, k[] = new int[N_MAX];
	static final int[][] map = new int[MAX][MAX], ans = new int[MAX][MAX];
	static final Point[] in = new Point[N_MAX];
	static int X, Y, N;
	static boolean ansFlag;
	public static void main(String[] args) {
		for(int i = 0; i < N_MAX; ++i) in[i] = new Point();
		while(true) {
			X = stdin.nextInt(); Y = stdin.nextInt(); N = stdin.nextInt();
			if((X|Y|N) == 0) break;
			for(int i = 0; i < N; ++i) k[stdin.nextInt()-1] = stdin.nextInt();
			for(int i = 0; i < Y; ++i) for(int j = 0; j < X; ++j) {
				map[i][j] = stdin.nextInt();
				if(map[i][j] != 0) in[map[i][j]-1].setLocation(j, i);
			}
			ansFlag = false;
			solve(0);
			if(ansFlag) {
				for(int i = 0; i < Y; ++i)
					for(int j = 0; j < X; ++j) System.out.print(ans[i][j] + (j==X-1 ? "\n" : " "));
			} else {
				System.out.println("NA");
			}
		}
	}
	static boolean isAns() {
		for(int i = 0; i < Y; ++i) for(int j = 0; j < X; ++j) if(map[i][j] == 0) return false;
		return true;
	}
	static boolean settable(int y, int x, int h, int w) {
		for(int i = y; i < y+h; ++i) for(int j = x; j < x+w; ++j) if(map[i][j] != 0) return false;
		return true;
	}
	static void set(int y, int x, int h, int w, int v) {
		for(int i = y; i < y+h; ++i) for(int j = x; j < x+w; ++j) map[i][j] = v;
	}
	static boolean solve(int b) {
		if(b == N) {
			if(isAns()) {
				if(ansFlag){
					ansFlag = false;
					return true;
				}
				ansFlag = true;
				for(int i = 0; i < Y; ++i) System.arraycopy(map[i], 0, ans[i], 0, X);
			}
			return false;
		}
		for(int h = 1; h <= k[b]; ++h) {
			if(k[b] % h != 0) continue;
			int w = k[b] / h;
			for(int i = in[b].y - h+1; i < in[b].y+1; ++i) {
				for(int j = in[b].x - w+1; j < in[b].x+1; ++j) {
					if(!(0<=i && 0<=j && i+h<=Y && j+w<=X)) continue;
					map[in[b].y][in[b].x] = 0; //色付き->色なし
					if(settable(i,j,h,w)) {
						set(i,j,h,w,b+1);
						if(solve(b+1)) return true;
						set(i,j,h,w,0); //roll back
					}
					map[in[b].y][in[b].x] = b+1; //他の色が塗れないように戻す
				}
			}
		}
		return false;
	}
}
