import java.util.*;
public class aoj0040 {
	static final Scanner stdin = new Scanner(System.in);
	static ArrayList<key> list = new ArrayList<key>();
	static int M = 26;
	public static void main(String[] args) {
		makeList();
		int n = stdin.nextInt(); stdin.nextLine();
		while(n-- >0){
			String[] in = stdin.nextLine().split(" ");
			System.out.println(solve(in));
		}
	}
	static class key{
		int a, b;
		String encoded;
		key(int a, int b, String in){ this.a = a; this.b = b; this.encoded = encode(in, a, b); }
		String encode(String in, int a, int b){
			StringBuilder res = new StringBuilder(in);
			for(int i=0; i<res.length(); ++i)
				res.setCharAt(i, (char)((a*(res.charAt(i)-'a') + b)%M + 'a') );
			return res.toString();
		}
	}
	static void makeList(){
		for(int a=1; a<M; ++a)
			for(int b = 0; b<M; ++b)
				if(isCoprime(a,M) ){
					list.add(new key(a, b, "that")); list.add(new key(a, b, "this"));
				}
	}
	static String decode(String out, int a, int b){
		StringBuilder res = new StringBuilder(out);
		for(int i=0; i<res.length(); ++i)
			res.setCharAt(i, (char)( (res.charAt(i)-'a' - b + M )*modInverse(a,M) % M + 'a' ) );
		return res.toString();
	}
	static String solve(String[] in){
		int a=0, b=0;
		StringBuilder res = new StringBuilder();
		RET: for(String str: in){
			if(str.length() == 4)
				for(key k: list)
					if(k.encoded.equals(str)){
						a = k.a; b = k.b;
						break RET;
					}
		}
		for(int i=0; i<in.length; ++i) res.append(" " + decode(in[i], a, b));
		return res.substring(1);
	}
	public static final int[] swap(int[] x, int i, int j){ int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x; }
	public static final int GCD(int a, int b){ return b == 0 ? a : GCD(b, a%b); }
	public static int extGCD(int a, int b, int x[]){
		int g = a;
		x[0] = 1; x[1] = 0;
		if (b != 0){
			g = extGCD(b, a % b, x);
			swap(x, 0, 1);
			x[1] -= (a / b) * x[0];
		}
		return g;
	}
	public static int modInverse(int a, int m){
		int[] x = new int[2];
		return (extGCD(a, m, x) == 1) ? (x[0] + m) % m : 0;
	}
	public static boolean isCoprime(int a, int b){ return GCD(a,b) == 1; }

}
