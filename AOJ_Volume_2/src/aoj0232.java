import java.util.*;
public class aoj0232 {
	static final Scanner stdin = new Scanner(System.in);
	static final int NONE = 0, MOVE = 1, GET = 2, PAY = 3, MAX = 5001; // = 50 * 100 + 1
	public static void main(String[] args) {
		while (true) {
			int X = stdin.nextInt(), Y = stdin.nextInt(), Z = stdin.nextInt();
			if ((X|Y|Z) == 0) break;
			int[] V = new int[X];
			for (int i = 0; i < X; ++i) V[i] = stdin.nextInt();
			T[] map = new T[Y+1];
			for (int i = 0; i < map.length; ++i) map[i] = new T();
			for (int i = 0; i < Z; ++i) map[stdin.nextInt()].set(stdin.nextInt(), stdin.nextInt());
			double[][] DP = new double[Y+1][MAX];
			DP[0][0] = 1;
			for (int i = 0; i < Y; ++i) {
				for (int j = 0; j < MAX; ++j) {
					if (DP[i][j] == 0) continue;
					for (int k = 0; k < X; ++k) {
						int ni = Math.min(Y, i+V[k]), nj = j;
						T next = map[ni];
						switch(next.E) {
						case MOVE: ni = Math.min(Y, ni+next.A); break;
						case GET: nj += next.A; break;
						case PAY: nj = Math.max(0, nj-next.A); break;
						case NONE: default: break;
						}
						DP[ni][nj] += DP[i][j]/X;
					}
				}
			}
			double ans = 0;
			for (int j = 0; j < MAX; ++j) ans += j*DP[Y][j];
			System.out.println((int)ans);
		}
	}
	static class T {
		int E = NONE, A = 0;
		void set(int e, int a) { E = e; A = a; }
	}
}
