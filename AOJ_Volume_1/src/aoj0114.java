import java.util.*;
public class aoj0114 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int[] a = new int[3], m = new int[3];
		long[] p = new long[3];
		while(true){
			for(int i = 0; i < 3; ++i){
				a[i] = stdin.nextInt();
				m[i] = stdin.nextInt();
			}
			if(a[0] == 0) break;
			for(int i = 0; i < 3; ++i) p[i] = getPeriod(a[i], m[i]);
			System.out.println( LCM(p) );
		}
	}
	static int getPeriod(int a, int m){
		int x = 1, res = 0;
		do{
			x = (x*a) % m;
			res++;
		}while(x != 1);
		return res;
	}
	public static final long GCD(long a, long b){ return b == 0 ? a : GCD(b, a%b); }
	public static final long LCM(long a, long b){ return a / GCD(a, b) * b; }
	public static final long GCD(long[] x){
		long ret = x[0];
		for(int i = 1; i < x.length; ++i) ret = GCD(ret, x[i]);
		return ret;
	}
	public static final long LCM(long[] x){
		long ret = x[0];
		for(int i = 1; i < x.length; ++i) ret = LCM(ret, x[i]);
		return ret;
	}
}
