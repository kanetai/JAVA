import java.util.*;
public class aoj0039 {
	static final Scanner stdin = new Scanner(System.in);
	@SuppressWarnings("serial") static HashMap<Character, Integer> map = new HashMap<Character, Integer>() {
		{ put('I', 1); put('V', 5); put('X', 10); put('L', 50); put('C', 100); put('D', 500); put('M', 1000); }
	};
	public static void main(String[] args) {
		while(stdin.hasNext()) System.out.println(solve(stdin.nextLine().toCharArray()));
	}
	static int solve(char[] input){
		int res = 0;
		int pre = map.get(input[0]);
		for(char ch: input){
			int cur = map.get(ch);
			res += (pre >= cur ? cur : (cur-pre-pre));
			pre = cur;
		}
		return res;
	}
}
