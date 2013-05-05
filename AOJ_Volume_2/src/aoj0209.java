import java.util.*;
public class aoj0209 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 4, INF = Integer.MAX_VALUE/2;
	static class T {
		int[][] p;
		int ax, ay;
		T(int[][] x){
			p = x;
			int n = x.length;
			for(int i = 0; i < n; ++i) for(int j = 0; j < n; ++j) if(x[i][j] != -1){ ay = i; ax = j; return; }
		}
	}
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), m = stdin.nextInt();
			if((n|m) == 0) break;
			int[][] map = new int[n][n], piece = new int[m][m];
			T[] p = new T[N];
			for(int i = 0; i < n; ++i) for(int j = 0; j < n; ++j) map[i][j] = stdin.nextInt();
			for(int i = 0; i < m; ++i) for(int j = 0; j < m; ++j) piece[i][j] = stdin.nextInt();
			p[0] = new T(piece);
			for(int i = 1; i < N; ++i) p[i] = new T(rotate(p[i-1].p));
			int ans[] = {INF,INF};
			for(int i = -m+1; i < n; ++i)
				for(int j = -m+1; j < n; ++j)
					SKIP: for(int k = 0; k < N; ++k){
						for(int I = 0; I < m; ++I)
							for(int J = 0; J < m; ++J){
								int P = p[k].p[I][J];
								if(P == -1) continue;
								if(!(i+I >= 0 && i+I < n && j+J >= 0 && j+J < n)) continue SKIP;
								int M = map[i+I][j+J];
								if(P != M) continue SKIP;
							}
						int ay = i + p[k].ay + 1, ax = j + p[k].ax + 1;
						if(ans[0] > ay || ans[0] == ay && ans[1] > ax){ ans[0] = ay; ans[1] = ax; }
					}
				System.out.println(ans[0] == INF ? "NA" : ans[1] + " " + ans[0]);	
		}
	}
	static final int[][] rotate(int[][] p){
		int n = p.length;
		int[][] ret = new int[n][n];
		for(int i = 0; i < n; ++i) for(int j = 0; j < n; ++j) ret[i][j] = p[n-j-1][i];
		return ret;
	}
}
