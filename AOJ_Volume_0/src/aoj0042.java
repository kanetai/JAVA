import java.util.*;
public class aoj0042 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		int W;
		for(int i=1; (W = stdin.nextInt()) != 0; ++i){
			int N = stdin.nextInt(); stdin.nextLine();
			int[] v = new int[N], w = new int[N];
			for(int j=0; j<N; ++j){
				String[] s = stdin.nextLine().split(",");
				v[j] = Integer.parseInt(s[0]); w[j] = Integer.parseInt(s[1]);
			}
			System.out.println("Case " + i +":");
			int[] res = solve(W, N, v, w);
			System.out.println(res[0]); System.out.println(res[1]);
		}
	}
	static int[] solve(int W, int N, int[] v, int[] w){
		int[][] VDP = new int[N+1][W+1]; Arrays.fill(VDP[0], 0);
		int[][] WDP = new int[N+1][W+1]; Arrays.fill(WDP[0], 0);
		for(int i=1; i <= N; ++i){
			for(int j=0; j <= W; ++j){
				if(j < w[i-1] || VDP[i-1][j] > VDP[i-1][j-w[i-1]]+v[i-1]){
					VDP[i][j] = VDP[i-1][j];
					WDP[i][j] = WDP[i-1][j];
				}else if(VDP[i-1][j] == VDP[i-1][j-w[i-1]]+v[i-1]){
					VDP[i][j] = VDP[i-1][j];
					WDP[i][j] = Math.min(WDP[i-1][j], WDP[i-1][j-w[i-1]]+w[i-1]);
				}else{
					VDP[i][j] = VDP[i-1][j-w[i-1]]+v[i-1];
					WDP[i][j] = WDP[i-1][j-w[i-1]]+w[i-1];
				}
			}
		}
		return new int[]{ VDP[N][W], WDP[N][W] };
	}
}
