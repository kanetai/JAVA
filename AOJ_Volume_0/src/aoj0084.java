import java.util.*;
public class aoj0084 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		String[] line = stdin.nextLine().split(",|\\.| ");
		for(String s: line)
			if(3 <= s.length() && s.length() <= 6){
				sb.append(' ');
				sb.append(s);
			}
		System.out.println(sb.substring(1));
	}
}
