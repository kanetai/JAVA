import java.util.*;
public class aoj0233 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			String s = stdin.next();
			if (s.charAt(0) == '0') break;
			int sign = 1;
			if (s.charAt(0) == '-') {
				sign = -1;
				s = s.substring(1, s.length());
			}
			int[] a = new int[s.length()+2];
			for (int i = 0; i < s.length(); ++i, sign *= -1) a[i] = sign * (s.charAt(s.length()-1-i) - '0');
			for (int i = 0; i < a.length; ++i) {
				if (a[i] < 0) {
					a[i] += 10;
					a[i+1]++;
					if (a[i+1] >= 10) {
						a[i+1] -= 10;
						a[i+2]--;
					}
				}
			}
			int ans = 0;
			for (int i = 0, base = 1; i < a.length; ++i, base*=10) ans += base*a[i];
			System.out.println(ans);
		}
	}
}
