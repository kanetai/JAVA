import java.util.*;
public class aoj0217 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), id = -1, d = -1;
			if (n == 0) break;
			while (n-- > 0) {
				int tmpid = stdin.nextInt(), tmpd = stdin.nextInt() + stdin.nextInt();
				if (d < tmpd) { id = tmpid; d = tmpd; }
			}
			System.out.println(id + " " + d);
		}
	}
}
