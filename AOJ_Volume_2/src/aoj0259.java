import java.util.*;
public class aoj0259 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			String s = stdin.next();
			if (s.equals("0000")) break;
			char[] c = s.toCharArray();
			if (c[0] == c[1] && c[1] == c[2] && c[2] == c[3]) { System.out.println("NA"); continue; }
			int ans = 0;
			for (ans = 0; !s.equals("6174"); ++ans) {
				Arrays.sort(c);
				StringBuilder sb = new StringBuilder(new String(c));
				int S = Integer.parseInt(sb.toString()), L = Integer.parseInt(sb.reverse().toString());
				s = String.format("%04d", L-S);
				c = s.toCharArray();
			}
			System.out.println(ans);
		}
	}
}
