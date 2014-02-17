import java.util.*;
public class aoj0243 {
	static final Scanner stdin = new Scanner(System.in);
	static int W, H, lim;
	static final char map[][] = new char[20][20];
	static final int dx[] = {-1, 1, 0, 0};
	static final int dy[] = {0, 0, -1, 1};
	public static void main(String[] args) { 
		while (true) {
			W = stdin.nextInt(); H = stdin.nextInt();
			if ((W|H) == 0) break;
			for (int i = 0; i < H; ++i) for (int j = 0; j < W; ++j) map[i][j] = stdin.next().charAt(0);
			for (lim = 0; !rec(0); ++lim);
			System.out.println(lim);
		}
	}
	static boolean rec(int depth) {
		char precolor = map[0][0];
		if (check()) return true;
		if (depth >= lim) return false;
		for (char color : "RGB".toCharArray()) {
			if (color == precolor) continue;
			List<Integer> list = new ArrayList<Integer>();
			changeColor(0, 0, color, list);
			if (rec(depth + 1)) return true;
			for (int p: list) map[p/W][p%W] = precolor;
		}
		return false;
	}
	static boolean check() {
		char c = map[0][0];
		for (int i = 0; i < H; ++i) for (int j = 0; j < W; ++j) if (map[i][j] != c) return false;
		return true;
	}
	static void changeColor(int y, int x, char color, List<Integer> list) {
		char precolor = map[y][x];
		map[y][x] = color;
		list.add(y*W+x);
		for (int k = 0; k < 4; ++k) {
			int nx = x + dx[k], ny = y + dy[k];
			if (0 <= nx && nx < W && 0 <= ny && ny < H && map[ny][nx] == precolor) changeColor(ny, nx, color, list);
		}
	}
}
