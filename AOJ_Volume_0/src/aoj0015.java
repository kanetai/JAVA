import java.util.*;
import java.math.*;
public class aoj0015 {
	static final Scanner stdin = new Scanner(System.in);
	static final Solver solver = Solver.BigInteger;
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while( n-- > 0 ) System.out.println( solver.solve(stdin.next(), stdin.next() ) );
	}
	enum Solver {
		BigInteger { @Override String solve(String a, String b){
			String ans = new BigInteger(a).add( new BigInteger(b) ).toString();
			return ans.length() > 80 ? "overflow" : ans;
		}}, Naive { @Override String solve(String a, String b){
			StringBuilder A = new StringBuilder(a);
			StringBuilder B = new StringBuilder(b);
			int m = Math.max(A.length(), B.length());
			A.reverse(); B.reverse();
			while( A.length() < m ) A.append('0');
			while( B.length() < m ) B.append('0');
			int carry = 0;
			StringBuilder ans = new StringBuilder();
			for(int i=0; i<m; ++i){
				int s = (A.charAt(i) - '0') + (B.charAt(i) - '0') + carry;
	            //int s = Character.digit( A.charAt(i), 10 ) + Character.digit( B.charAt(i), 10 ) + carry;
				carry = s/10;
				s%=10;
				ans.append(s);
			}
			if( carry > 0 ) ans.append(carry);
			return ans.length() > 80 ? "overflow" : ans.reverse().toString();
		}};
		String solve(String a, String b){ return null; }
	}
}
