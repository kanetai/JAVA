import java.util.*;
public class aoj0211 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n;
		while ((n = stdin.nextInt()) != 0) {
			long d[] = new long[n], v[] = new long[n];
			for (int i = 0; i < n; ++i) {
				d[i] = stdin.nextLong(); v[i] = stdin.nextLong();
				long gcd = GCD(d[i],v[i]);
				d[i] /= gcd; v[i] /= gcd;
			}
			long D = LCM(d), V = GCD(v);
			for (int i = 0; i < n; ++i) System.out.println(D/d[i]*v[i]/V);
		}
	}
	public static final long GCD(long a, long b){ return b == 0 ? a : GCD(b, a%b); }
	public static final long LCM(long a, long b){ return a / GCD(a, b) * b; }
	public static final long GCD(long[] x){
		long ret = x[0]; for(int i = 1; i < x.length; ++i) ret = GCD(ret, x[i]); return ret;
	}
	public static final long LCM(long[] x){
		long ret = x[0]; for(int i = 1; i < x.length; ++i) ret = LCM(ret, x[i]); return ret;
	}
}
