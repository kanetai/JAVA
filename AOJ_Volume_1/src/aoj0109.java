import java.util.*;
public class aoj0109 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		int n = stdin.nextInt();
		while(n-->0)
			System.out.println(equation(stdin.next().toCharArray(), 0).value);
	}
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
		}else{
			int value = 0;
			while(Character.isDigit(str[pos]))
				value = value * 10 + (str[pos++] - '0');
			return new Result(value, pos);
		}
	}
}
