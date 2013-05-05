import java.util.*;
public class aoj0168 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 31, D = 10, Y = 365;
	static final int[] DP = new int[N];
	public static void main(String[] args) {
		DP[0] = 1; DP[1] = 1; DP[2] = 2;
		for(int i = 3; i < N; ++i) DP[i] = DP[i-1]+DP[i-2]+DP[i-3];
		for(int i = 0; i < N; ++i) DP[i] = ((DP[i] + D-1)/D + Y-1)/Y;
		int n;
		while((n = stdin.nextInt()) != 0) System.out.println(DP[n]);
	}
}
