import java.util.*;
public class aoj0131 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 10;
	static final int[][] map = new int[N][N];
	static final int[][] ans = new int[N][N];
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while(n-- > 0){
			for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j) map[i][j] = stdin.nextInt();
			for(int i = 0; i < (1<<N); ++i) if(solve(i)) break;
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					System.out.print(ans[i][j] + (j == N-1 ? "\n" : " "));
		}
	}
	static boolean solve(int pat){
		int[][] m = new int[N][];
		for(int i = 0; i < N; ++i){
			m[i] = Arrays.copyOf(map[i], N);
			Arrays.fill(ans[i], 0);
		}
		for(int i = 0; i < N; ++i)
			if(((pat >> i) & 1) != 0) on(0, i, m);
		for(int i = 1; i < N; ++i)
			for(int j = 0; j < N; ++j)
				if(m[i-1][j] == 1) on(i,j,m);
		return check(N-1, m);
	}
	static void on(int i, int j, int[][] m){
		if(i-1 >= 0) m[i-1][j] ^= 1;
		if(j-1 >= 0) m[i][j-1] ^= 1;
		m[i][j] ^= 1;
		if(j+1 < N) m[i][j+1] ^= 1;
		if(i+1 < N) m[i+1][j] ^= 1;
		ans[i][j] = 1;
	}
	static boolean check(int i, int[][] m){
		for(int j = 0; j < N; ++j) if(m[i][j] == 1) return false;
		return true;
	}
}
