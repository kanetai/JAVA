import java.util.*;
public class aoj0238 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int t = stdin.nextInt();
			if (t == 0) break;
			int n = stdin.nextInt();
			while (n-- > 0) t += (stdin.nextInt() - stdin.nextInt());
			System.out.println(t <= 0 ? "OK" : t);
		}
	}
}
