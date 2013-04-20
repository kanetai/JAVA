import java.util.*;
public class aoj0054 {
	static final Scanner stdin = new Scanner(System.in);
	static final Solver solver = Solver.solve1;
	public static void main(String[] args) {
		while(stdin.hasNext())
			System.out.println(solver.solve(stdin.nextInt(), stdin.nextInt(), stdin.nextInt()));
	}
	enum Solver{ solve1 { @Override int solve(int a, int b, int n){
			int res = 0;
			a %= b;
			for(int i=0; i<n; ++i){
				a *= 10;
				res += a/b;
				a %= b;
			}
			return res;
		}}, solve2 { @Override int solve(int a, int b, int n){
			int res = 0;
			String s = String.format("%.1000f", (double)a/b);
			int p = s.indexOf('.');
			char[] c = s.substring(p+1,p+1+n).toCharArray();
			for(char i: c) res += (i-'0');
			return res;
		}};
		int solve(int a, int b, int n){ return 0; }
	}
}
