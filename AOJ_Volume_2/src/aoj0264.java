import java.util.*;
public class aoj0264 {
	static final Scanner stdin = new Scanner(System.in);
	static int mod;
	static String str;
	public static void main(String[] args) { 
		while (true) {
			String[] l = stdin.nextLine().split(":");
			mod = Integer.parseInt(l[0]);
			if (mod == 0) break;
			str = l[1].replace(" ", "");
			try {
				int ans = SyntacticAnalysis.calc(str);
				System.out.printf("%s = %d (mod %d)\n", str, ans, mod);
			} catch (ArithmeticException e) {
				System.out.println("NG");
			}
		}
	}
	static int negative(int a) { return (mod - a); }
	static int add(int a, int b) { return (a + b) % mod; }
	static int sub(int a, int b) { return  add(a, negative(b)); }
	static int mul(int a, int b) { return (a * b) % mod; }
	static int div(int a, int b) {
		int inverse = modInverse(b, mod);
		if (inverse == -1) throw new ArithmeticException();
		return (a * inverse) % mod;
	}
	static public class SyntacticAnalysis {
		static String eq;
		static int pos = 0;
		private static char next() { return eq.charAt(pos++); }
		public static int calc(String equation) {
			eq = equation + "$";
			pos = 0;
			return equation();
		}
		private static int equation() {
			int value = factor();
			for (char c = next(); c == '+' || c == '-'; c = next()) {
				if(c == '+') value = add(value, factor());
				else value = sub(value, factor());
			}
			--pos;
			return value;
		}
		private static int factor() {
			int value = term();
			for (char c = next(); c == '*' || c == '/'; c = next()) {
				if(c == '*') value = mul(value, term());
				else value = div(value, term());
			}
			--pos;
			return value;
		}
		private static int term() {
			char c = next();
			int value = 0;
			switch (c) {
			case '(':
				value = equation();
				c = next(); assert c == ')'; //skip ')'
				break;
			case '+':
				value = equation(); break;
			case '-':
				value = negative(equation()); break;
			default:
				if (Character.isDefined(c)) {
					while(Character.isDigit(c)) {
						value = value * 10 + (c - '0');
						c = next();
					}
					--pos;
				}
			}
			return value;
		}
	}
	public static final int modInverse(int a, int m){
		int[] x = new int[2];
		return (a > 0 && extGCD(a, m, x) == 1) ? (x[0] + m) % m : -1;
	}
	public static final int extGCD(int a, int b, int x[]){
		int g = a;
		x[0] = 1; x[1] = 0;
		if (b != 0){
			g = extGCD(b, a % b, x);
			swap(x, 0, 1);
			x[1] -= (a / b) * x[0];
		}
		return g;
	}
	public static final int[] swap(int[] x, int i, int j){
		int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x;
	}
}
