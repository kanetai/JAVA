import java.util.*;
public class aoj0257 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int a = stdin.nextInt() + stdin.nextInt(), b = stdin.nextInt();
		System.out.println(a == 2 || b == 1 ? "Open" : "Close");
	}
}
