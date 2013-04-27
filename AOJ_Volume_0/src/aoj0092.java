import java.util.*;
public class aoj0092 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N_MAX = 1001;
	public static void main(String[] args) {
		int[][] map = new int[N_MAX][N_MAX], dot = new int[N_MAX][N_MAX]; //dot[i+1][j+1], 四角形map[0][0]～map[i][j]の.の数
		while(true){
			int n = stdin.nextInt();
			if(n==0) break;
			for(int i = 0; i<n; ++i){
				char[] line = stdin.next().toCharArray();
				for(int j = 0; j < n; ++j){
					map[i][j] = (line[j] == '.') ? 1 : 0;
				}
			}
			dot[1][1] = map[0][0];
			for(int i = 2; i <= n; ++i){
				dot[1][i] = map[0][i-1] + dot[1][i-1];
				dot[i][1] = map[i-1][0] + dot[i-1][1];
			}
			for(int i = 2; i <= n; ++i)
				for(int j = 2; j <= n; ++j)
					dot[i][j] = map[i-1][j-1] + dot[i-1][j] + dot[i][j-1] - dot[i-1][j-1];
			int max = 0, l = 1, u = n, k = (l+u)/2;
			while(l<=u){
				LOOP: for(int i=k; i<=n; ++i){
					for(int j=k; j<=n; ++j){
						if(dot[i][j] - dot[i-k][j] - dot[i][j-k] + dot[i-k][j-k] == k*k){
							max = k;
							break LOOP;
						}
					}
				}
				if(max == k) l = k + 1;
				else u = k - 1;
				k = (l+u)/2;
			}
			System.out.println(max);
		}
	}
}
