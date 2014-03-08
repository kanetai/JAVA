package algorithm;
public class SyntacticAnalysis {
	private SyntacticAnalysis(){}
	static String eq;
	static int pos = 0;
	private static char next() { return eq.charAt(pos++); }
	/*
	 * ex. SyntacticAnalysis.calc("-(1+2+3)*(4+5+6)")) -> -90
	 * AOJ No. 0109, 0264 (partial modification)
	 * <equation> ::= <factor> {<operator1> <factor>}
	 * <factor> ::= <term> {<operator2> <term>}
	 * <term> ::= <operator1> <equation> | '(' <equation> ')' | <number>
	 * <operator1> ::= '+' | '-'
	 * <operator2> ::= '*' | '/'
	 */
	public static int calc(String equation) {
		eq = equation + "$";
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
	/*
	 * ex. equation("(1+2+3)*(4+5+6)=".toCharArray(), 0).value <br>
	 * = is delimiter (final character) <br>
	 * AOJ No. 0109 
	 */
	public static class Result{
		int value, pos;
		Result(int value, int pos){this.value = value; this.pos = pos;}
	}
	public static Result equation(char[] str, int pos){
		Result left = factor(str, pos);
		while(str[left.pos] == '+' || str[left.pos] == '-'){
			Result right = factor(str, left.pos+1);
			if(str[left.pos] == '+') left.value += right.value;
			else left.value -= right.value;
			left.pos = right.pos;
		}
		return left;
	}
	private static Result factor(char[] str, int pos){
		Result left = term(str, pos);
		while(str[left.pos] == '*' || str[left.pos] == '/'){
			Result right = term(str, left.pos+1);
			if(str[left.pos] == '*') left.value *= right.value;
			else left.value /= right.value;
			left.pos = right.pos;
		}
		return left;
	}
	private static Result term(char[] str, int pos){
		if(str[pos] == '('){
			Result res = equation(str, pos+1);
			assert str[res.pos] == ')';
			res.pos += 1; //skip ')'
			return res;
		} else {
			int value = 0;
			while(Character.isDigit(str[pos]))
				value = value * 10 + (str[pos++] - '0');
			return new Result(value, pos);
		}
	}
	public static void main(String[] args) {
		System.out.println(equation("(1+2+3)*(4+5+6)=".toCharArray(), 0).value);
		System.out.println(SyntacticAnalysis.calc("-(1+2+3)*(4+5+6)"));
	}
}
