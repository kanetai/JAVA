import java.util.*;
public class aoj0089 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = 1;
		int[][] DP = new int[2][101];
		Arrays.fill(DP[0], 0); Arrays.fill(DP[1], 0);
		DP[0][0] = stdin.nextInt(); stdin.nextLine();//改行
		int j = 1, k = 0;
		while(stdin.hasNext()){
			String[] instr = stdin.nextLine().split(",");
			int[] in = new int[instr.length];
			for(int i=0; i < instr.length; ++i) in[i] = Integer.parseInt(instr[i]);
			if(n < in.length){
				DP[j][0] = DP[k][0] + in[0];
				DP[j][n] = DP[k][n-1] + in[n];
				for(int i = 1; i < n; ++i) DP[j][i] = in[i] + Math.max(DP[k][i], DP[k][i-1]);
			}else{
				for(int i = 0; i < n-1; ++i) DP[j][i] = in[i] + Math.max(DP[k][i], DP[k][i+1]);
			}
			n = in.length;
			j = k;
			k = (k+1)%2;
		}
		System.out.println(DP[k][0]);
	}
}
