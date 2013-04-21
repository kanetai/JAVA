import java.util.*;
public class aoj0078 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt();
			if(n == 0) break;
			int k = 0;
			int[][] a = new int[n][n];
			for(int i = -n/2; i <= n/2; ++i)
				for(int j = 0; j < n; ++j)
					a[(j+i+n)%n][(j-i+n)%n] = ++k;
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					System.out.printf("%4d%s", a[i][j], (j == n-1 ? "\n" : "") );
		}
	}
}
