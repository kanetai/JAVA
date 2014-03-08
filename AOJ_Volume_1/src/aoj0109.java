import java.util.*;
public class aoj0109 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		int n = stdin.nextInt();
		while(n-->0) System.out.println(SyntacticAnalysis.calc(stdin.next()));
	}
	public static class SyntacticAnalysis {
		static String eq;
		static int pos = 0;
		private static char next() { return eq.charAt(pos++); }
		public static int calc(String equation) {
			eq = equation;// + "$";
			pos = 0;
			return equation();
		}
		private static int equation() {
			int value = factor();
			for (char c = next(); c == '+' || c == '-'; c = next()) {
				if(c == '+') value += factor();
				else value -= factor();
			}
			--pos;
			return value;
		}
		private static int factor() {
			int value = term();
			for (char c = next(); c == '*' || c == '/'; c = next()) {
				if(c == '*') value *= term();
				else value /= term();
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
				value = -1 * equation(); break;
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
}
