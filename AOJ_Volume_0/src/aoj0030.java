import java.util.*;
public class aoj0030 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), s = stdin.nextInt();
			if(n==0 && s==0) break;
			System.out.println( solve(n,s,0) );
		}
	}
	static int solve(int n, int s, int c){
		if(n == 0) return (s==0 ? 1 : 0);
		int d = Math.min(10, s+1);
		int res = 0;
		for(int i = c; i < d; ++i) res += solve(n-1, s-i, i+1);
		return res;
	}
}
