import java.util.*;
public class aoj0218 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			while (n-- > 0) {
				int m = stdin.nextInt(), e = stdin.nextInt(), j = stdin.nextInt(), ave = (m + e + j) / 3;
				System.out.println(((m == 100 || e == 100 || j == 100) || ((m + e) / 2 >= 90) || ave >= 80) ? "A" :
					(ave >= 70 || (ave >= 50 && (m >= 80 || e >= 80))) ? "B" : "C");
			}
		}
	}
}
