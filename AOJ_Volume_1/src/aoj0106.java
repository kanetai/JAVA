import java.util.*;
public class aoj0106 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int[] DP = new int[51];
		while(true){
			int n = stdin.nextInt()/100;
			if(n == 0)break;
			Arrays.fill(DP, Integer.MAX_VALUE/2);
			DP[0] = 0;
			for(int i = 2; i <= n; ++i){
				int m = Integer.MAX_VALUE/2;
				if(i-2 >= 0) m = Math.min(m, DP[i-2] + 380);
				if(i-3 >= 0) m = Math.min(m, DP[i-3] + 550);
				if(i-5 >= 0) m = Math.min(m, DP[i-5] + 850);
				if(i-10 >= 0) m = Math.min(m, DP[i-10] + 1520);
				if(i-12 >= 0) m = Math.min(m, DP[i-12] + 1870);
				if(i-15 >= 0) m = Math.min(m, DP[i-15] + 2244);
				DP[i] = m;
			}
			System.out.println(DP[n]);
		}
	}
}
